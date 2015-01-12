package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.csgit.cao.business.PersonalBusiness;
import com.csgit.cao.model.Cat_Personal;
import com.csgit.cao.model.Cat_PersonalEndpoint;
import com.csgit.cao.model.Multimedia;
import com.csgit.cao.model.MultimediaEndpoint;
import com.csgit.cao.model.Persona;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.entity.UsuarioEntity;
//import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
//import com.google.appengine.api.blobstore.BlobKey;
import com.google.gson.Gson;


public class EditPersonal extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PersonaEndpoint personaEndpoint;
	Persona persona;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		personaEndpoint = new PersonaEndpoint();
		persona = new Persona(); 
		PrintWriter out = resp.getWriter();
		
		resp.setContentType("text/json");	
		String parametro = req.getParameter("objectJson");
		System.out.println("objectJson = "+req.getParameter("objectJson"));
		if(parametro.contains("peticion")){   //validamos si es la peticion para el catalogo de personas 
			out.write(new Gson().toJson(obtenerPersonas()));
			return;
		}else if(parametro.contains("opcionpersona:")){
			String[] split= parametro.split(":");
			Long id_persona = Long.parseLong(split[1].replace('"', ' ').trim());
			System.out.println("voy a devolver las personas con idPersona = " + id_persona);
			out.write(new Gson().toJson(obtenerPersonas(id_persona)));
			return;
		}
		/*else if(parametro.contains("opcionempresa")){  
			out.write(new Gson().toJson(obtenerEmpresa()));
			return;
		}*/
		return;
	}
	
	public Collection<Cat_Personal> obtenetCatPersonal(){
		CollectionResponse<Cat_Personal> lista = new Cat_PersonalEndpoint().listCat_Personal(null, null);
		Collection<Cat_Personal>lista1 = lista.getItems();
		return lista1;
	}
	
	private Collection<UsuarioEntity> obtenerPersonas() {
		// TODO Auto-generated method stub
		CollectionResponse<Persona> lista = new PersonaEndpoint().listPersona(null, null);
		Collection<Persona> lista1 = lista.getItems();
		Collection<UsuarioEntity> regresar = new ArrayList<>();
		for (Persona elem : lista1) {
			UsuarioEntity ue = new UsuarioEntity();
			ue.setId(elem.getId_Persona());
			String nombre = elem.getNombre() + " " + elem.getAp_Paterno() + " " + elem.getAp_Materno();
			ue.setNombre(nombre);
			regresar.add(ue);
		}
		return regresar;
	}
	private Persona obtenerPersonas(Long idPersona)
	{
		CollectionResponse<Persona> lista = new PersonaEndpoint().listPersona(null, null);
		Collection<Persona> lista1 = lista.getItems();
		CollectionResponse<Multimedia> listaRes1 = new MultimediaEndpoint().listMultimedia(null, null, new Long(0), new Long(0), new Long(0));
		Collection<Multimedia> listaMultimedia = listaRes1.getItems();
		
		
		Persona regresar = new Persona();
		for (Persona elem : lista1) {
			if(elem.getId_Persona() == idPersona)
			{
				regresar = elem;
				regresar = obtenerPathImagen(idPersona,listaMultimedia, regresar);

				break;
			}
		}
		return regresar;
	}
	
	private Persona obtenerPathImagen(Long idPersona,Collection<Multimedia> listaMultimedia,Persona aux ) {
		// TODO Auto-generated method stub
		for (Multimedia multimedia : listaMultimedia) {
			if(idPersona == multimedia.getIdReferencia() && multimedia.getTipoReferencia() == Constants.CatalogoPersonas){
				aux.setFotografia(Constants.URL_GET_BLOB_STORE + multimedia.getPath());
				break;
			}
		}
		return aux;
	}
	
	/*
	private Collection<empresaEntity> obtenerEmpresa() {
		// TODO Auto-generated method stub
		Collection<empresaEntity>respuesta = new ArrayList<>();
		
		for (int i = 0; i < 6; i++) {
			DependenciasEntity aux_dependencia = new DependenciasEntity();
			if(i==0){
				aux_dependencia.setId(new Long(Constants.idContratista));
				aux_dependencia.setTipoEmpresa(Constants.contratistaTexto);
			}else if(i==1){
				aux_dependencia.setId(new Long(Constants.idSupervisora));
				aux_dependencia.setTipoEmpresa(Constants.supervisoraTexto);
			}else if(i==2){
				aux_dependencia.setId(new Long(Constants.idDependencia));
				aux_dependencia.setTipoEmpresa(Constants.dependenciaTexto);
			}else if(i==3){
				aux_dependencia.setId(new Long(Constants.idParticular));
				aux_dependencia.setTipoEmpresa(Constants.particularTexto);
			}else if(i==4){
				aux_dependencia.setId(new Long(Constants.idSecretaria));
				aux_dependencia.setTipoEmpresa(Constants.secretariaTexto);
			}else if(i==5){
				aux_dependencia.setId(new Long(Constants.idGobierno));
				aux_dependencia.setTipoEmpresa(Constants.GobiernoTexto);
			}
			respuesta.add(aux_dependencia);
		}
		return respuesta;
	}*/
	
	
}
