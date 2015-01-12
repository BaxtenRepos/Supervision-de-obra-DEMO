package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csgit.cao.model.Directivo_Proyecto;
import com.csgit.cao.model.Directivo_ProyectoEndpoint;
import com.csgit.cao.model.Empresa;
import com.csgit.cao.model.EmpresaEndpoint;
import com.csgit.cao.model.Persona;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.cao.model.Proyecto;
import com.csgit.cao.model.ProyectoEndpoint;
import com.csgit.cao.model.Usuario;
import com.csgit.cao.model.UsuarioEndpoint;
import com.csgit.entity.listaProyectoEntity;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class PruebaProyecto extends HttpServlet{
	
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
		
		if(parametro.contains("peticion")){   //validamos si es la peticion para el catalogo de proyectos 
			out.write(new Gson().toJson(obtenerProyectos()));
			return;
		}
		else if(parametro.contains("validasesion"))
		{
			
			HttpSession sesion = req.getSession (); 
			if (sesion.isNew ()) { 
			sesion.invalidate();
			out.write(new Gson().toJson(false));
			}else {
				
			}
				out.write(new Gson().toJson(true));
			
			
		}
	}

	private Collection<listaProyectoEntity> obtenerProyectos() {
		CollectionResponse<Proyecto> lista = new ProyectoEndpoint().listProyecto(null, null);
		Collection<Proyecto>lista1 = lista.getItems();
		CollectionResponse<Directivo_Proyecto> listaDirectivos = new Directivo_ProyectoEndpoint().listDirectivo_Proyecto(null, null, new Long(0));
		Collection<Directivo_Proyecto> listaDirectivos1 = listaDirectivos.getItems();
		CollectionResponse<Empresa> listaEmpresas =new EmpresaEndpoint().listEmpresa(null,null);
		Collection<Empresa> listaEmpresas1 = listaEmpresas.getItems();
		CollectionResponse<Usuario> listaUsuarios = new UsuarioEndpoint().listUsuario(null, null);
		Collection<Usuario> listaUsuarios1 = listaUsuarios.getItems();
		CollectionResponse<Persona> listaPersonas = new PersonaEndpoint().listPersona(null, null);
		Collection<Persona> listaPersonas1 = listaPersonas.getItems();
		
		Collection<listaProyectoEntity>respuesta = new ArrayList<>();
	    for (Proyecto elem : lista1) {
	    	listaProyectoEntity aux_proyecto = new listaProyectoEntity();
	    	aux_proyecto.setId_Proyecto(elem.getId_Proyecto());
	    	aux_proyecto.setNombre_corto(elem.getNombre_corto());
	    	aux_proyecto.setNombre_largo(elem.getNombre_largo());
	    	aux_proyecto.setDescripcion(elem.getDescripcion());
	    	aux_proyecto.setId_dependencia(elem.getId_dependencia());
	    	aux_proyecto.setId_secretaria(elem.getId_secretaria());
	    	aux_proyecto = obtenerDependencias(aux_proyecto,listaEmpresas1);
	    	respuesta.add(obtenerDirectivos(aux_proyecto,listaDirectivos1, listaUsuarios1,listaPersonas1));
	    }
	    
		return respuesta;
	}

	public listaProyectoEntity obtenerDependencias(listaProyectoEntity proyecto, Collection<Empresa> listaEmpresas){
		
	    for (Empresa elem : listaEmpresas) {
	            		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
        	if(elem.getId_Empresa()== proyecto.getId_dependencia()){
        		proyecto.setDependencia(elem.getNombre());
        	}else if(elem.getId_Empresa() == proyecto.getId_secretaria()){
        		proyecto.setSecretaria(elem.getNombre());
        	} 
	    }
		return proyecto;
	}
	
	private listaProyectoEntity obtenerDirectivos(listaProyectoEntity proyecto,Collection<Directivo_Proyecto> listaDirectivos,
			Collection<Usuario> listaUsuarios,Collection<Persona> listaPersonas) {
		// TODO Auto-generated method stub
		Collection<String> regresar = new ArrayList<>();
		for (Directivo_Proyecto elem : listaDirectivos) {
			if(elem.getId_proyecto() == proyecto.getId_Proyecto()){
				regresar.add(obtenerUsuario(elem.getId_directivo(),listaUsuarios, listaPersonas));
			}
		}
		proyecto.setDirectivo(regresar);		
		return proyecto;
	}
	
	private String obtenerUsuario(Long id_directivo,Collection<Usuario> listaUsuarios,
			Collection<Persona> listaPersonas) {
		String nombre = "";
		for (Usuario user : listaUsuarios) {
			if(user.getId_Tipo_Persona()==Constants.idDirectivo && user.getId_Propietario()==id_directivo){
				nombre = obtenerPersona(user.getId_Persona(),listaPersonas);
				break;
			}
		}
		return nombre;
	}

	private String obtenerPersona(Long id_Persona,Collection<Persona> listaPersonas1) {
		String nombre = "";
		for (Persona elem : listaPersonas1){
			if(elem.getId_Persona() == id_Persona){
				nombre = elem.getNombre() + " " + elem.getAp_Paterno() + " " + elem.getAp_Materno();
				break;
			}
		}
		return nombre;
	}

}
