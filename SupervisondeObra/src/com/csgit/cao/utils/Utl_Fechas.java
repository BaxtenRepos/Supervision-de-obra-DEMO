package com.csgit.cao.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.util.Log;

public class Utl_Fechas {
	
	@SuppressLint("SimpleDateFormat")
	public static boolean compararFechas(String fecha1, String fecha2){
		try {
			SimpleDateFormat formateador = new SimpleDateFormat(Utl_Constantes.FOR_FECHA);
			Date fechaDate1 = formateador.parse(fecha1);
			Date fechaDate2 = formateador.parse(fecha2);
			
			if(fechaDate1.before(fechaDate2)){
				return true;
			}else if(fechaDate2.before(fechaDate1)){
				return false;
			}else{
				return true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Log.i("Error Fecha", e.getMessage());
			return false;
		}
	}
	
	@SuppressLint("SimpleDateFormat")
	public static String getFecha(){
		String fecha = new SimpleDateFormat(Utl_Constantes.FOR_FECHA).format(new Date());
		return fecha;
	}
	
	public static boolean isFechaValida(String fecha) {
		if(fecha == null)
			return false;
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat(Utl_Constantes.FOR_FECHA,
            		Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
