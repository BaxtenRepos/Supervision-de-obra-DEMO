package com.csgit.cao.business;

import com.csgit.cao.Constants;

public class MultimediaBusiness {
	
	public String tipoFormato(int tipo){
		String formato = "";
		if(tipo == Constants.idAvi)
			formato = Constants.formatAvi;
		else if(tipo == Constants.idJpeg)
			formato = Constants.formatJpeg;
		else if(tipo == Constants.idJpg)
			formato = Constants.formatJpg;
		else if(tipo == Constants.idMp4)
			formato = Constants.formatMp4;
		else if(tipo == Constants.idPng)
			formato = Constants.formatPng;
		
			return formato;
	}
//	private String tipoArchivo(int tipo){
//		String tipoArchivo = "";
//		
//		if(tipo == Constants.imagen)
//			formato = Constants.;
//		else if(tipo == Constants.video)
//			formato = Constants.formatJpeg;
//		
//		
//		
//		return tipoArchivo;
//	}
	
	



}
