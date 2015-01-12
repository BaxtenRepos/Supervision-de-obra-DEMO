package com.csgit.cao.business;

import com.csgit.cao.Constants;
import com.csgit.cao.model.Directorio;
import com.csgit.cao.model.DirectorioEndpoint;
import com.google.api.server.spi.response.CollectionResponse;

public class DirectorioBusiness {
	
	
	public static boolean validaDirectorio(Long tipoReferencia, Long id){
		boolean regresa = false;

	
			CollectionResponse<Directorio> directorio = new DirectorioEndpoint().listDirectorio(null, null,new Long(0), new Long(0));
			for (Directorio elemento : directorio.getItems()) {
				if(elemento.getIdReferencia().longValue() == id && elemento.getTipoReferencia() == tipoReferencia)
					regresa=true;
			}
		return regresa;	
		
	}

}
