package com.csgit.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilFechas {
	
	public static String getFecha(){
		String fecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
		return fecha;
	}
	
	public static String getHora(){
		String hora = new SimpleDateFormat("hh:mm:ss").format(new Date());
		return hora;
	}
	
	

}
