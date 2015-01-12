package com.csgit.cao.business;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.csgit.cao.dao.ConstantesBD;
import com.csgit.cao.model.communicationchannel.model.Ubicaciones;

public class Bus_Ubicacion {
	
	private ContentResolver contentRes;
	
	public Bus_Ubicacion(ContentResolver contR) {
		// TODO Auto-generated constructor stub
		this.contentRes = contR;
	}
	public Ubicaciones getUbicacionObra(Long idObra){
		String[] col = {"IdUbicacionObra"};
		long idUbicacion = 0;
		
		Ubicaciones ubicacion= new Ubicaciones();
		Cursor cursorObra = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaObra), col, 
				ConstantesBD.ColObras[0] + "=" + idObra, null, null);
		if(cursorObra.moveToFirst())
			idUbicacion = cursorObra.getLong(0);
		cursorObra.close();
		
		Cursor cursoUbicaciones = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaUbicacionObra), ConstantesBD.ColUbicacionObra, 
				ConstantesBD.ColUbicacionObra[0] + "=" + idUbicacion, null, null);
		
		if(cursoUbicaciones.moveToFirst()){			
			ubicacion.setIdUbicacion(cursoUbicaciones.getLong(0));
			ubicacion.setUbicacion(ubicaciones(cursoUbicaciones.getString(1)));
		}else{
			ubicacion = null;
		}
		cursoUbicaciones.close();
		return ubicacion;
	}
	
	public List<String> ubicaciones(String texto){
		List<String> m = new ArrayList<String>();
		
		if(texto.split(":").length > 1){
			String splitUbicacion[] = texto.split(":");	
			 for(String LogLat : splitUbicacion){
			    	m.add(LogLat);
			 }
		}else{
			m.add(texto);
		}
		return m;
	}
	
	
}
