package com.csgit.cao.business;

import com.csgit.entity.PuntoEntity;

public class UbicacionesBusiness {

	/*
	 * Metodo que verifica la existencia de puntos en el arreglo
	 */
	public static boolean arregloNoVacio(PuntoEntity ubicaciones[]) {
		
		if(ubicaciones.length>0)
			 return true;
		else 
		return false;		
	}

}
