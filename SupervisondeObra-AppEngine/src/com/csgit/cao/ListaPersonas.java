package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.business.PersonalBusiness;
import com.csgit.cao.model.Directorio;
import com.csgit.cao.model.DirectorioEndpoint;
import com.csgit.cao.model.Multimedia;
import com.csgit.cao.model.MultimediaEndpoint;
import com.csgit.cao.model.Persona;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.cao.model.Usuario;
import com.csgit.cao.model.UsuarioEndpoint;
import com.csgit.entity.PersonaEntity;
import com.csgit.entity.auxEntity;
import com.csgit.entity.borrarEntity;
import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class ListaPersonas extends HttpServlet
{
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
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/json");
		String parametro = req.getParameter("objectJson");
		if(parametro.contains("peticion"))
		{   //validamos si es la peticion para el catalogo de proyectos 
			out.write(new Gson().toJson(obtenerPersonas()));
			return;
		}else{
			borrarEntity personasborrar = (borrarEntity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new borrarEntity());
			ArrayList<auxEntity> idPersonasBorrar = personasborrar.getBorrar();
			CollectionResponse<Usuario> listaUsuario = new UsuarioEndpoint().listUsuario(null, null);
			Collection<Usuario>listaUsuario1 = listaUsuario.getItems();
			CollectionResponse<Directorio> listaDirectorio = new DirectorioEndpoint().listDirectorio(null, null, 0L, 0L);
			Collection<Directorio>listaDirectorio1 = listaDirectorio.getItems();
			
			PersonaEndpoint personaEndpoint = new PersonaEndpoint();
			ArrayList<auxEntity> borrados = new ArrayList<auxEntity>();
			
			for (auxEntity idPersona : idPersonasBorrar) {
				if(!PersonalBusiness.isPersonaUsuario(idPersona.getId(), listaUsuario1)){
					if(!PersonalBusiness.isPersonaDirectorio(idPersona.getId(), listaDirectorio1)){
						Persona aux_persona = obtenerPersonas(idPersona.getId());
						Persona persona = new Persona();
						
						persona.setId_Persona(aux_persona.getId_Persona());
						persona.setNombre(aux_persona.getNombre());
						persona.setAp_Paterno(aux_persona.getAp_Paterno());
						persona.setAp_Materno(aux_persona.getAp_Materno());
						persona.setIniciales(aux_persona.getIniciales());
						persona.setFecha_Nacimiento(aux_persona.getFecha_Nacimiento());
						persona.setDireccion(aux_persona.getDireccion());
						persona.setTelefonos(aux_persona.getTelefonos());
						persona.setRadios(aux_persona.getRadios());
						persona.setEmails(aux_persona.getEmails());
						persona.setRfc(aux_persona.getRfc());
						persona.setCargo(aux_persona.getCargo());
						persona.setTituloProfesional(aux_persona.getTituloProfesional());
						persona.setCedulaProfesional(aux_persona.getCedulaProfesional());
						persona.setUsuarioSkype(aux_persona.getUsuarioSkype());
						persona.setIdEmpresa(aux_persona.getIdEmpresa());
						persona.setFotografia(aux_persona.getFotografia());
						persona.setVisible(false);
						
						try {
							personaEndpoint.updatePersona(persona);
							borrados.add(idPersona);
						} catch (Exception e) {
							// TODO: handle exception
							out.write(new Gson().toJson("ha ocurrido un error con la persona: "+persona.getNombre()+
									": " + e.getMessage()));
						}
					}
				}
			}
			out.write(new Gson().toJson(borrados));
			return;
		}
	}
	
	private Collection<PersonaEntity> obtenerPersonas() 
	{
		CollectionResponse<Persona> listaRes = new PersonaEndpoint().listPersona(null, null);
		Collection<Persona> lista = listaRes.getItems();
		Collection<PersonaEntity> regresar = new ArrayList<>();
		
		CollectionResponse<Multimedia> listaRes1 = new MultimediaEndpoint().listMultimedia(null, null, new Long(0), new Long(0), new Long(0));
		Collection<Multimedia> listaMultimedia = listaRes1.getItems();
		
		for (Persona elem : lista) 
		{
			if(elem.isVisible()){
				PersonaEntity ue = new PersonaEntity();
				//System.out.println("ListaPersonas "+elem.getNombre()+" IdEmpresa = "+elem.getIdEmpresa()+"\n");
				
				ue.setId(elem.getId_Persona());
				String nombre = elem.getNombre() + " " + elem.getAp_Paterno() + " " + elem.getAp_Materno();
				ue.setNombre(nombre);
				ue.setCargo(elem.getCargo());
				ue.setEmail(elem.getEmails());
				ue.setPath_Imagen(obtenerPathImagen(elem.getId_Persona(),listaMultimedia));
				regresar.add(ue);
			}
		}
		return regresar;
	}
	
	private String obtenerPathImagen(Long id_Persona,Collection<Multimedia> listaMultimedia) {
		// TODO Auto-generated method stub
		String path = "";
		for (Multimedia multimedia : listaMultimedia) {
			if(id_Persona == multimedia.getIdReferencia() && multimedia.getTipoReferencia() == Constants.CatalogoPersonas)
			{
				path = Constants.URL_GET_BLOB_STORE + multimedia.getPath();
				break;
			}
		}
		return path;
	}
	
	private Persona obtenerPersonas(Long idPersona)	{
		CollectionResponse<Persona> lista = new PersonaEndpoint().listPersona(null, null);
		Collection<Persona> lista1 = lista.getItems();
		
		Persona regresar = new Persona();
		for (Persona elem : lista1) {
			if(elem.getId_Persona() == idPersona){
				regresar = elem;
				break;
			}
		}
		return regresar;
	}

}
