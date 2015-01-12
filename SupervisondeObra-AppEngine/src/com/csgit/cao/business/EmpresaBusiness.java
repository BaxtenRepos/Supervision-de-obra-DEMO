package com.csgit.cao.business;

import java.util.Collection;

import com.csgit.cao.Constants;
import com.csgit.cao.model.Empresa;
import com.csgit.cao.model.EmpresaEndpoint;
import com.csgit.cao.model.Obra;
import com.csgit.cao.model.Persona;
import com.google.api.server.spi.response.CollectionResponse;



public class EmpresaBusiness {
	
	//retorna id de seleccion
	
	public int returnId(String seleccion){
		int retorno = 0;
		if(seleccion.equalsIgnoreCase(Constants.contratistaTexto)) retorno = Constants.idContratista;
		else if(seleccion.equalsIgnoreCase(Constants.dependenciaTexto)) retorno = Constants.idDependencia;
		else if(seleccion.equalsIgnoreCase(Constants.particularTexto)) retorno = Constants.idParticular;
		else if(seleccion.equalsIgnoreCase(Constants.secretariaTexto)) retorno = Constants.idSecretaria;
		else if(seleccion.equalsIgnoreCase(Constants.supervisoraTexto)) retorno = Constants.idSupervisora;
		else if(seleccion.equalsIgnoreCase(Constants.GobiernoTexto)) retorno = Constants.idGobierno;
		
		
		return retorno;
}
	public  boolean existente(String nombre){
		boolean exists = false;
		CollectionResponse lista;
		Collection<Empresa>lista1;
		EmpresaEndpoint empresaendpoint = new EmpresaEndpoint();
				
		lista =empresaendpoint.listEmpresa(null,null);//traemos la lista de empresas insertadas
		lista1=lista.getItems();
	
	    for (Empresa elem : lista1) {
	        if(elem.getNombre().equalsIgnoreCase(nombre))     		
	        	exists = true;
	    }

		
		return exists;
	}
	
	public static  boolean existente(String nombre, Long idEmpresa){
		boolean exists = false;
		CollectionResponse<Empresa> lista =new EmpresaEndpoint().listEmpresa(null,null);
		Collection<Empresa>lista1 = lista.getItems();
		for (Empresa elem : lista1) {
	        if(elem.getNombre().equalsIgnoreCase(nombre)  && elem.getId_Empresa().longValue() != idEmpresa.longValue())     		
	        	exists = true;
	    }
	    return exists;
	}
	
	public static boolean isEmpresaProyectos(Long idEmpresa, Collection<com.csgit.cao.model.Proyecto>lista1){
		boolean visible = false;
		for (com.csgit.cao.model.Proyecto proyecto : lista1) {
			if(proyecto.getId_dependencia() == idEmpresa || proyecto.getId_secretaria() == idEmpresa){
				visible = true;
				break;
			}
		}
		return visible;
	}
	
	public static boolean isEmpresaObras(Long idEmpresa, Collection<Obra>lista1){
		boolean visible = false;
		for (Obra obra : lista1) {
			if(obra.getId_EmpresaContratista() == idEmpresa || obra.getIdDependencia() == idEmpresa ||
					obra.getIdGobierno() == idEmpresa || obra.getIdSecretaria() == idEmpresa){
				visible = true;
				break;
			}
		}
		return visible;
	}
	
	public static boolean isEmpresaPersonas(Long idEmpresa, Collection<Persona>lista1){
		boolean visible = false;
		for (Persona persona : lista1) {
			if(persona.getIdEmpresa() == idEmpresa && persona.isVisible()){
				visible = true;
				break;
			}
		}
		return visible;
	}

}
