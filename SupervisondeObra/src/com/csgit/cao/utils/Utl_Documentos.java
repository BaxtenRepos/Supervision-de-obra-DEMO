package com.csgit.cao.utils;


public class Utl_Documentos {

	public static boolean isMimeTypeValido(String mimeType){
		
		for (String type : Utl_Constantes.MIME_TYPES) {
			if(type.equals(mimeType)){
				return true;
			}
		}
		return false;
	}
	
	public static String getExtensionDoc(String nombreArchivoWithExtension){
		String ex = "";
		if(!nombreArchivoWithExtension.isEmpty()){
			String[] aux = nombreArchivoWithExtension.split("\\.");
			ex = aux[(aux.length -1)];
		}
		return ex;
	}
}
