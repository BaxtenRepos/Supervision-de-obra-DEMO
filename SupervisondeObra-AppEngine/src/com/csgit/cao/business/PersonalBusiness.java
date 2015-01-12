/**
 * 
 */
package com.csgit.cao.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.csgit.cao.model.Cat_Personal;
import com.csgit.cao.model.Cat_PersonalEndpoint;
import com.csgit.cao.model.Directorio;
import com.csgit.cao.model.Persona;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.cao.model.Usuario;
import com.google.api.server.spi.response.CollectionResponse;

/**
 * @author tico
 *
 */
public class PersonalBusiness {
	
	public static boolean existenteCatPersonal(String nombre){
		boolean exists = false;
		CollectionResponse<Cat_Personal> lista;
		Collection<Cat_Personal>lista1;
		Cat_PersonalEndpoint cat_PersonalEndpoint = new Cat_PersonalEndpoint();
				
		lista =cat_PersonalEndpoint.listCat_Personal(null, null);//traemos la lista de maquinaria insertadas
		lista1=lista.getItems();
	
	    for (Cat_Personal elem : lista1) {
	        if(elem.getTipo_Personal().equalsIgnoreCase(nombre))     		
	        	exists = true;
	    }

		return exists;
	}
	
	public static boolean existenteCatPersonal(String nombre, Long idCatPersona){
		boolean exists = false;
		CollectionResponse<Cat_Personal> lista;
		Collection<Cat_Personal>lista1;
		Cat_PersonalEndpoint cat_PersonalEndpoint = new Cat_PersonalEndpoint();
				
		lista =cat_PersonalEndpoint.listCat_Personal(null, null);//traemos la lista de maquinaria insertadas
		lista1=lista.getItems();
	
	    for (Cat_Personal elem : lista1) {
	        if(elem.getTipo_Personal().equalsIgnoreCase(nombre) && elem.getId_Tipo_Personal().longValue()!=idCatPersona.longValue())     		
	        	exists = true;
	    }

		return exists;
	}
	
	/**
	 * 
	 * @param correo
	 * @return
	 */
	public static boolean existenteCorreo(String correo){
		boolean exists = false;
		CollectionResponse<Persona> lista;
		Collection<Persona>lista1;
		PersonaEndpoint personaEndpoint = new PersonaEndpoint();
				
		lista =personaEndpoint.listPersona(null, null);//traemos la lista de maquinaria insertadas
		lista1=lista.getItems();
	
	    for (Persona elem : lista1) {
	        if(elem.getEmails().equalsIgnoreCase(correo))     		
	        	exists = true;
	    }

		return exists;

	}
	
	/**
	 * 
	 * @param correo
	 * @return
	 */
	public static boolean existenteCorreo(String correo, Long idPersona){
		boolean exists = false;
		CollectionResponse<Persona> lista;
		Collection<Persona>lista1;
		PersonaEndpoint personaEndpoint = new PersonaEndpoint();
				
		lista =personaEndpoint.listPersona(null, null);//traemos la lista de Personas insertadas
		lista1=lista.getItems();
	
	    for (Persona elem : lista1) 
	    {
	        if(elem.getEmails().equalsIgnoreCase(correo) && elem.getId_Persona().longValue()!=idPersona.longValue())
	        {
	        	exists = true;
	        }	
	        	
	    }

		return exists;

	}
	
	public static boolean isPersonaUsuario(Long idPersona, Collection<Usuario> lista1){
		boolean visible = false;
		for (Usuario usuario : lista1) {
			if(usuario.getId_Persona().longValue()  == idPersona && usuario.isVisible()){
				visible = true;
				break;
			}
		}
		return visible;
	}

	public static boolean isPersonaDirectorio(Long id,Collection<Directorio> listaDirectorio1) {
		// TODO Auto-generated method stub
		boolean visible = false;
		for (Directorio directorio : listaDirectorio1) {
			if(Arrays.asList(directorio.getArregloPersonasId()).contains(id)){
				visible = true;
				break;
			}
		}
		return visible;
	}
	
}
