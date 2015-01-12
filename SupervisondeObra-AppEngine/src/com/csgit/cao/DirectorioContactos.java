package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.model.Directorio;
import com.csgit.cao.model.DirectorioEndpoint;
import com.csgit.cao.model.Empresa;
import com.csgit.cao.model.EmpresaEndpoint;
import com.csgit.cao.model.Multimedia;
import com.csgit.cao.model.MultimediaEndpoint;
import com.csgit.cao.model.Obra;
import com.csgit.cao.model.ObraEndpoint;
import com.csgit.cao.model.Persona;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.cao.model.Proyecto;
import com.csgit.cao.model.ProyectoEndpoint;
import com.csgit.entity.PersonaEntity;
import com.csgit.entity.UsuarioEntity;
import com.csgit.util.DefaultEntity;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class DirectorioContactos extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		resp.setContentType("text/json");	
		System.out.println(req.getParameter("objectJson"));
		String parametro = req.getParameter("objectJson");
		
		if(parametro.contains("proyectos")){   //validamos si es la peticion para el catalogo de proyectos 
			out.write(new Gson().toJson(obtenerProyectos()));
			return;
		}else if(parametro.contains("obra:")){
			String[] split= parametro.split(":");
			Long id = Long.parseLong(split[1].replace('"', ' ').trim());
			out.write(new Gson().toJson(obtenerObra(id)));
			return;
		}else if(parametro.contains("contactoproyecto:")){
			String[] split= parametro.split(":");
			Long id = Long.parseLong(split[1].replace('"', ' ').trim());
			Long refencia = Long.parseLong(split[2].replace('"', ' ').trim());
			out.write(new Gson().toJson(obtenerDirectorio(id,refencia)));
			return;
		}else if(parametro.contains("detalleContacto:")){
			String[] split= parametro.split(":");
			Long id = Long.parseLong(split[1].replace('"', ' ').trim());
			out.write(new Gson().toJson(obtenerPersona(id)));
			return;
		}
	}
	
	private Collection<DefaultEntity> obtenerProyectos() {
		CollectionResponse<Proyecto> lista = new ProyectoEndpoint().listProyecto(null, null);
		Collection<Proyecto>lista1 = lista.getItems();
		Collection<DefaultEntity> regresar = new ArrayList<>();
		for (Proyecto proyecto : lista1) {
			DefaultEntity aux = new DefaultEntity();
			aux.setId(String.valueOf(proyecto.getId_Proyecto()));
			aux.setNombre(proyecto.getNombre_corto());
			regresar.add(aux);
		}
		return regresar;
	}
	
	private Collection<DefaultEntity> obtenerObra(Long id){
		CollectionResponse<Obra> lista = new ObraEndpoint().listObra(null, null, 0L, 0L, "");
		Collection<Obra>lista1 = lista.getItems();
		Collection<DefaultEntity> regresar = new ArrayList<>();
		for (Obra obra : lista1) {
			if(obra.isVisible() && obra.getId_Poyecto().longValue() == id){
				DefaultEntity aux = new DefaultEntity();
				aux.setId(String.valueOf(obra.getId_Obra()));
				aux.setNombre(obra.getNombre());
				regresar.add(aux);
			}
		}
		return regresar;
	}
	
	private Collection<UsuarioEntity> obtenerDirectorio(Long idReferencia, Long idTipoReferencia){
		CollectionResponse<Directorio> directorio = new DirectorioEndpoint().listDirectorio(null, null,new Long(0), new Long(0));
		Collection<Directorio>lista1 = directorio.getItems();
		CollectionResponse<Multimedia> multi = new MultimediaEndpoint().listMultimedia(null, null, 0L, 0L,0L);
		Collection<Multimedia>listaMulti = multi.getItems();
		String personas[] = null;
		Collection<UsuarioEntity> regresar = new ArrayList<>();
		for (Directorio element : lista1) {
			if(element.getIdReferencia().longValue()==idReferencia && element.getTipoReferencia().longValue()==idTipoReferencia){
				personas=element.getArregloPersonasId();
				break;
			}
		}
		
		CollectionResponse<Persona> listapersonas = new PersonaEndpoint().listPersona(null, null);
		
		if(personas != null){
			for (Persona element : listapersonas.getItems()) {
				if(element.isVisible()){
					for (String string : personas) {
						if(Long.parseLong(string)==element.getId_Persona()){
							UsuarioEntity de = new UsuarioEntity();
							de.setId(element.getId_Persona());
							de.setNombre(element.getNombre()+" " +element.getAp_Paterno()+" "+element.getAp_Materno());
							de.setTipo(element.getCargo());
							de.setPath(obtenerPathImagen(element.getId_Persona(), listaMulti));
							regresar.add(de);
						}
					}
				}
			}
		}
		
		return regresar;
	}
	
	private String obtenerPathImagen(Long idPersona,Collection<Multimedia> listaMultimedia) {
		// TODO Auto-generated method stub
		String path = "";
		for (Multimedia multimedia : listaMultimedia) {
			if(idPersona == multimedia.getIdReferencia() && multimedia.getTipoReferencia() == Constants.CatalogoPersonas){
				path = Constants.URL_GET_BLOB_STORE + multimedia.getPath();
				break;
			}
		}
		return path;
	}
	
	private PersonaEntity obtenerPersona(Long idPersona)
	{
		CollectionResponse<Persona> lista = new PersonaEndpoint().listPersona(null, null);
		Collection<Persona> lista1 = lista.getItems();
		CollectionResponse<Multimedia> listaRes1 = new MultimediaEndpoint().listMultimedia(null, null, new Long(0), new Long(0), new Long(0));
		Collection<Multimedia> listaMultimedia = listaRes1.getItems();
		CollectionResponse<Empresa> listaRes2 = new EmpresaEndpoint().listEmpresa(null, null);
		Collection<Empresa> listaEmpresa = listaRes2.getItems();
		
		PersonaEntity regresar = new PersonaEntity();
		for (Persona elem : lista1) {
			if(elem.getId_Persona().longValue() == idPersona){
				regresar.setNombre(elem.getNombre()+" " +elem.getAp_Paterno()+" "+elem.getAp_Materno());
				regresar.setCargo(elem.getCargo());
				regresar.setEmail(elem.getEmails());
				regresar.setId(elem.getId_Persona());
				regresar.setTelefono(elem.getTelefonos());
				regresar.setPath_Imagen(obtenerPathImagen(idPersona,listaMultimedia));
				regresar.setEmpresa(obtenerEmpresa(elem.getIdEmpresa(),listaEmpresa));
				regresar.setRfc(elem.getRfc());
				break;
			}
		}
		return regresar;
	}

	private String obtenerEmpresa(Long id,Collection<Empresa> listaEmpresa) {
		String empresa = "";
		for (Empresa elem : listaEmpresa) {
			if(elem.getId_Empresa().longValue() == id){
				empresa = elem.getNombre();
				break;
			}
		}
		return empresa;
	}

}
