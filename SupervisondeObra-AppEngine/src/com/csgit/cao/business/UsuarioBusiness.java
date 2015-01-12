package com.csgit.cao.business;

import java.util.Collection;

import com.csgit.cao.Constants;
import com.csgit.cao.model.Directivo_Proyecto;
import com.csgit.cao.model.Obra;
import com.csgit.cao.model.Proyecto;
import com.csgit.cao.model.Usuario;
import com.csgit.cao.model.UsuarioEndpoint;
import com.google.api.server.spi.response.CollectionResponse;

public class UsuarioBusiness {
	
	/**
	 * 
	 * @param nombre
	 * @return
	 */
	public static boolean existenteUsuario(String nombre){
		boolean exists = false;
		CollectionResponse lista;
		Collection<Usuario>lista1;
		UsuarioEndpoint usuarioEndpoint = new UsuarioEndpoint();
				
		lista =usuarioEndpoint.listUsuario(null, null);//traemos la lista de usuarios insertadas
		lista1=lista.getItems();
	
	    for (Usuario elem : lista1) {
	        if(elem.getUsuario().equalsIgnoreCase(nombre))     		
	        	exists = true;
	    }

		return exists;
	}
	
	public static int returnId(String seleccion) {
		int retorno = 0;
		if (seleccion.equalsIgnoreCase(Constants.administradorTexto))
			retorno = Constants.idAdministrador;
		else if (seleccion.equalsIgnoreCase(Constants.directivoTexto))
			retorno = Constants.idDirectivo;
		else if (seleccion.equalsIgnoreCase(Constants.supervisorTexto))
			retorno = Constants.idSupervisor;

		return retorno;
	}
	public static Long validaUsuario(String usuario, String contrasena){
		Long regresar = new Long(0);
		CollectionResponse lista;
		Collection<Usuario>lista1;
		UsuarioEndpoint usuarioEndpoint = new UsuarioEndpoint();
				
		lista =usuarioEndpoint.listUsuario(null, null);//traemos la lista de usuarios insertadas
		lista1=lista.getItems();
	
	    for (Usuario elem : lista1) {
	        if(elem.getUsuario().equalsIgnoreCase(usuario) && elem.getContrasena().equalsIgnoreCase(contrasena))     		
	        	regresar=elem.getId_Propietario();
	    }
		return regresar;
	}
	
	public static boolean existenteUsuario(String nombre, Long idUsuario){
		boolean exists = false;
		CollectionResponse lista;
		Collection<Usuario>lista1;
		UsuarioEndpoint usuarioEndpoint = new UsuarioEndpoint();
				
		lista =usuarioEndpoint.listUsuario(null, null);//traemos la lista de usuarios insertadas
		lista1=lista.getItems();
	
	    for (Usuario elem : lista1) {
	        if(elem.getUsuario().equalsIgnoreCase(nombre) && elem.getId_Propietario()!=idUsuario)     		
	        	exists = true;
	    }

		return exists;
	}
	
	public static boolean PersonaAsignada(Long idPersona){
		boolean exists = false;
		CollectionResponse<Usuario> lista;
		Collection<Usuario>lista1;
		UsuarioEndpoint usuarioEndpoint = new UsuarioEndpoint();
				
		lista =usuarioEndpoint.listUsuario(null, null);//traemos la lista de usuarios insertadas
		lista1=lista.getItems();
	
	    for (Usuario elem : lista1) {
	        if(elem.getId_Persona() == idPersona){
	        	exists = true;
	        	break;
	        }
	    }

		return exists;
	}
	
	public static boolean PersonaAsignada(Long idPersona, Long id_Usuario){
		boolean exists = false;
		CollectionResponse<Usuario> lista;
		Collection<Usuario>lista1;
		UsuarioEndpoint usuarioEndpoint = new UsuarioEndpoint();
				
		lista =usuarioEndpoint.listUsuario(null, null);//traemos la lista de usuarios insertadas
		lista1=lista.getItems();
	
	    for (Usuario elem : lista1) {
	        if(elem.getId_Persona() == idPersona){
	        	if(elem.getId_Propietario() == id_Usuario){
	        		exists = false;
		        	break;
	        	}else{
	        		exists = true;
		        	break;
	        	}
	        }
	    }

		return exists;
	}
	
	public static Usuario obtenerUsuario(Long id_usuario) {
		// TODO Auto-generated method stub
		CollectionResponse<Usuario> lista = new UsuarioEndpoint().listUsuario(null, null);
		Collection<Usuario>lista1 = lista.getItems();
		Usuario aux_usuario = new Usuario();
	    for (Usuario elem : lista1) {
    		if(elem.getId_Propietario()==id_usuario){
    			aux_usuario = elem;
    			break;
    		}	
	    }
	    return aux_usuario;
	}
	
	public static boolean isDirectivoProyectos(Long idDirectivo, Collection<Directivo_Proyecto>lista1){
		boolean visible = false;
		for (Directivo_Proyecto directivo_Proyecto : lista1) {
			if(directivo_Proyecto.getId_directivo().longValue() == idDirectivo){
				visible = true;
				break;
			}
		}
		return visible;
	}
	public static boolean isSupervisorObra(Long idSupervisor, Collection<Obra>lista1){
		boolean visible = false;
		for (Obra obra : lista1) {
			if(obra.getIdUsuario().longValue() == idSupervisor){
				visible = true;
				break;
			}
		}
		return visible;
	}

}
