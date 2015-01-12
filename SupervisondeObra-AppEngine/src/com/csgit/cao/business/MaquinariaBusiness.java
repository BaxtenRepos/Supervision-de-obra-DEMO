package com.csgit.cao.business;

import java.util.Collection;

import com.csgit.cao.Constants;
import com.csgit.cao.model.Cat_Tipo_Maquinaria;
import com.csgit.cao.model.Cat_Tipo_MaquinariaEndpoint;
import com.csgit.cao.model.Maquinaria;
import com.csgit.cao.model.MaquinariaEndpoint;
import com.google.api.server.spi.response.CollectionResponse;

public class MaquinariaBusiness {
	
//	public static Long returnId(String seleccion) {
//		long id=0;
//		CollectionResponse lista;
//		Collection<Cat_Tipo_Maquinaria>lista1;
//		lista =new Cat_Tipo_MaquinariaEndpoint().listCat_Tipo_Maquinaria(null, null);//traemos la lista del catalogo tipo personas insertadas
//		lista1=lista.getItems();
//	
//	    for (Cat_Tipo_Maquinaria elem : lista1) {
//	    	if(elem.getTipo_Maquinaria().equalsIgnoreCase(seleccion))     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
//	        	id = elem.getId_tipo_Maquinaria();
//	    }
//		return id;
//	}
	
	public static boolean existenteMaquinaria(String nombre){
		boolean exists = false;
		CollectionResponse<Maquinaria> lista;
		Collection<Maquinaria>lista1;
		MaquinariaEndpoint maquinariaEndpoint = new MaquinariaEndpoint();
				
		lista =maquinariaEndpoint.listMaquinaria(null, null);//traemos la lista de maquinaria insertadas
		lista1=lista.getItems();
	
	    for (Maquinaria elem : lista1) {
	        if(elem.getNombre().equalsIgnoreCase(nombre))     		
	        	exists = true;
	    }

		return exists;
	}
	
	public static boolean existenteMaquinaria(String nombre, Long id){
		boolean exists = false;
		CollectionResponse<Maquinaria> lista;
		Collection<Maquinaria>lista1;
		MaquinariaEndpoint maquinariaEndpoint = new MaquinariaEndpoint();
				
		lista =maquinariaEndpoint.listMaquinaria(null, null);//traemos la lista de maquinaria insertadas
		lista1=lista.getItems();
	
	    for (Maquinaria elem : lista1) {
	        if(elem.getNombre().equalsIgnoreCase(nombre) && elem.getId_Maquinaria().longValue() != id.longValue())     		
	        	exists = true;
	    }

		return exists;
	}
	
	public static boolean existenteCatTipoMaquinaria(String nombre){
		boolean exists = false;
		CollectionResponse lista;
		Collection<Cat_Tipo_Maquinaria>lista1;
		Cat_Tipo_MaquinariaEndpoint cat_tipo_maquinariaendpoint = new Cat_Tipo_MaquinariaEndpoint();
				
		lista =cat_tipo_maquinariaendpoint.listCat_Tipo_Maquinaria(null, null);//traemos la lista de catalogo de maquinaria insertadas
		lista1=lista.getItems();
	
	    for (Cat_Tipo_Maquinaria elem : lista1) {
	        if(elem.getTipo_Maquinaria().equalsIgnoreCase(nombre))     		
	        	exists = true;
	    }

		return exists;
	}
	
	public static int returnId(String seleccion){
		int retorno = 0;
		if(seleccion.equalsIgnoreCase(Constants.pesadaTexto)) retorno = Constants.idPesada;
		else if(seleccion.equalsIgnoreCase(Constants.ligeraTexto)) retorno = Constants.idLigera;
		else if(seleccion.equalsIgnoreCase(Constants.equipoTexto)) retorno = Constants.idEquipo;
		
		return retorno;
	}

}
