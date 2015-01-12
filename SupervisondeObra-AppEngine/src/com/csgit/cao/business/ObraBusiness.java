package com.csgit.cao.business;

import java.util.Collection;

import com.csgit.cao.model.Concepto;
import com.csgit.cao.model.Obra;
import com.csgit.cao.model.ObraEndpoint;
import com.google.api.server.spi.response.CollectionResponse;

public class ObraBusiness {
	
	
	public static boolean ExistNoContrato(String noContrato, Long idProyecto){
		
		boolean regresar=false;
		CollectionResponse lista;
		Collection<Obra>lista1;		
		lista =new ObraEndpoint().listObra(null,null,new Long(0),new Long(0),"");//traemos la lista de empresas insertadas
		lista1=lista.getItems();
	    for (Obra elem : lista1) {
	    	System.out.println(elem.getId_Poyecto().toString());
	    	System.out.println(idProyecto+"es id proyecto");
	    	System.out.println(noContrato+"es numero contrato");
	    	System.out.println(elem.getId_Poyecto().longValue()==idProyecto);
	    	System.out.println(elem.getNoContrato()==noContrato);
	    	
	        if((elem.getId_Poyecto().longValue()==idProyecto)&&(elem.getNoContrato() == noContrato))     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	regresar=true;
	    }
		return regresar;
	}
	
public static boolean ExistNoContrato(String noContrato, Long idProyecto, Long idObra){
		
		boolean regresar=false;
		CollectionResponse lista;
		Collection<Obra>lista1;		
		lista =new ObraEndpoint().listObra(null,null,new Long(0),new Long(0),"");//traemos la lista de empresas insertadas
		lista1=lista.getItems();
	    for (Obra elem : lista1) {
	    	System.out.println(elem.getId_Poyecto().toString());
	    	System.out.println(idProyecto+"es id proyecto");
	    	System.out.println(noContrato+"es numero contrato");
	    	System.out.println(elem.getId_Poyecto().longValue()==idProyecto);
	    	System.out.println(elem.getNoContrato()==noContrato);
	    	
	        if((elem.getId_Poyecto().longValue()==idProyecto)&&(elem.getNoContrato() == noContrato) && (elem.getId_Obra() !=idObra))     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	regresar=true;
	    }
		return regresar;
	}
	
	public static boolean isObrasConceptos(Long idObra, Collection<Concepto>lista1){
		boolean regresar = false;
		for (Concepto concepto : lista1) {
			if(concepto.getId_Obra().longValue() == idObra && concepto.isVisible()){
				regresar = true;
				break;
			}
		}
		return regresar;
	}
}
