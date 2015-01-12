package com.csgit.cao.business;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.csgit.cao.dao.ConstantesBD;
import com.csgit.cao.model.Mod_Estimacion_Concepto;
import com.csgit.cao.model.communicationchannel.model.Estimacion;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Uri;

public class Bus_Estimacion {

	private ContentResolver contentRes;
	private Bus_Sync bus_sync;
	
	public Bus_Estimacion(Context context){
		this.contentRes = context.getContentResolver();
		bus_sync =  new Bus_Sync(context);
	}
	
	public void insertEstimacion(List<Estimacion> datos){
		int Estimacion = 0;
		Cursor cursorEstimacion = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaEstimacion), ConstantesBD.ColEstimacion, 
			null, null, null);
		if(cursorEstimacion.moveToLast()){
			Estimacion = cursorEstimacion.getInt(1);
			Estimacion ++;
		}else {
			Estimacion = 1;
		}
		cursorEstimacion.close();
		
		for (int i = 0; i < datos.size(); i++) {
			ContentValues values = new ContentValues();
			values.put(ConstantesBD.ColEstimacion[1], Estimacion);
			values.put(ConstantesBD.ColEstimacion[2], datos.get(i).getIdObra());
			values.put(ConstantesBD.ColEstimacion[3], datos.get(i).getIdConcepto());
			values.put(ConstantesBD.ColEstimacion[4], datos.get(i).getFechaInicio());
			values.put(ConstantesBD.ColEstimacion[5], datos.get(i).getFechaFin());
			values.put(ConstantesBD.ColEstimacion[6], datos.get(i).getNumero());
			values.put(ConstantesBD.ColEstimacion[7], datos.get(i).getCantidadAutorizada());
			values.put(ConstantesBD.ColEstimacion[8], datos.get(i).getFechaEstimacion());
			values.put(ConstantesBD.ColEstimacion[9], Utl_Constantes.REG_NO_SINC);

			Uri uri = contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEstimacion), values);
			Log.i("Insert Estimacion ", uri.toString());

			// temporal		
			long idSync = Utl_Uri.getIdInsert(uri);
			ContentValues valTemp = new ContentValues();
			valTemp.put(ConstantesBD.colSync[1], Utl_Constantes.TABLA_ESTIMACION);
			valTemp.put(ConstantesBD.colSync[2], idSync);
			valTemp.put(ConstantesBD.colSync[3], Utl_Constantes.ACC_INSERT);
			valTemp.put(ConstantesBD.colSync[4], Utl_Constantes.REG_NO_SINC);
			contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), valTemp);
		}

	}
	
	public void insertEstimacion(List<Estimacion> datos, Long Estimacion){
		
		for (int i = 0; i < datos.size(); i++) {
			ContentValues values = new ContentValues();
			values.put(ConstantesBD.ColEstimacion[1], Estimacion);
			values.put(ConstantesBD.ColEstimacion[2], datos.get(i).getIdObra());
			values.put(ConstantesBD.ColEstimacion[3], datos.get(i).getIdConcepto());
			values.put(ConstantesBD.ColEstimacion[4], datos.get(i).getFechaInicio());
			values.put(ConstantesBD.ColEstimacion[5], datos.get(i).getFechaFin());
			values.put(ConstantesBD.ColEstimacion[6], datos.get(i).getNumero());
			values.put(ConstantesBD.ColEstimacion[7], datos.get(i).getCantidadAutorizada());
			values.put(ConstantesBD.ColEstimacion[8], datos.get(i).getFechaEstimacion());
			values.put(ConstantesBD.ColEstimacion[9], Utl_Constantes.REG_NO_SINC);

			Uri uri = contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEstimacion), values);
			Log.i("Insert Estimacion ", uri.toString());

			// temporal		
			long idSync = Utl_Uri.getIdInsert(uri);
			ContentValues valTemp = new ContentValues();
			valTemp.put(ConstantesBD.colSync[1], Utl_Constantes.TABLA_ESTIMACION);
			valTemp.put(ConstantesBD.colSync[2], idSync);
			valTemp.put(ConstantesBD.colSync[3], Utl_Constantes.ACC_INSERT);
			valTemp.put(ConstantesBD.colSync[4], Utl_Constantes.REG_NO_SINC);
			contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), valTemp);
		}
		
	}
	
	public ArrayList<Mod_Estimacion_Concepto> getEstimacion_Conceptos(Long Estimacion) {
		// TODO Auto-generated method stub
		ArrayList<Mod_Estimacion_Concepto> listaEstimacionConceptos = new ArrayList<Mod_Estimacion_Concepto>();
		Cursor cursorEstimacionConceptos = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEstimacion), ConstantesBD.ColEstimacion, 
				ConstantesBD.ColEstimacion[1] + "=" + Estimacion, null, null);
		while (cursorEstimacionConceptos.moveToNext()) {
			Mod_Estimacion_Concepto datos = new Mod_Estimacion_Concepto();
			datos.setIdEstimacion(cursorEstimacionConceptos.getLong(0));
			datos.setIdConcepto(cursorEstimacionConceptos.getLong(3));
//			datos.setCantidadAutorizada(cursorEstimacionConceptos.getInt(7));
			datos.setCantidadAutorizada(cursorEstimacionConceptos.getDouble(7));
			Cursor cursorConceptos = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaConcepto), ConstantesBD.ColConceptos, 
					ConstantesBD.ColConceptos[0] + "=" + cursorEstimacionConceptos.getLong(3), null, null);
			if(cursorConceptos.moveToFirst()){
				datos.setDescripcion(cursorConceptos.getString(3));
			}
			cursorConceptos.close();
			listaEstimacionConceptos.add(datos);
		}
		cursorEstimacionConceptos.close();
		
		return listaEstimacionConceptos;
	}

	public List<Estimacion> getEstimaciones(Long idObra) {
		List<Estimacion> listaEstimaciones = new ArrayList<Estimacion>();
		Cursor cursorEstimacion = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEstimacion), ConstantesBD.ColEstimacion, 
				ConstantesBD.ColEstimacion[2] + "=" + idObra, null, null);
		while (cursorEstimacion.moveToNext()) {
			Estimacion datos = new Estimacion();
			datos.setEstimacion(cursorEstimacion.getLong(1));
			datos.setIdObra(cursorEstimacion.getLong(2));
			datos.setIdConcepto(cursorEstimacion.getLong(3));
			datos.setFechaInicio(cursorEstimacion.getString(4));
			datos.setFechaFin(cursorEstimacion.getString(5));
			datos.setNumero(cursorEstimacion.getLong(6));
			datos.setCantidadAutorizada(cursorEstimacion.getLong(7));
			datos.setFechaEstimacion(cursorEstimacion.getString(8));
			listaEstimaciones.add(datos);
		}
		cursorEstimacion.close();

		return listaEstimaciones;
	}
	
	public Estimacion getEstimacionSync(long idRegistro) {
		Estimacion estimacion = new Estimacion();
		
		Cursor cursorEstimacion = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaEstimacion), ConstantesBD.ColEstimacion, 
				ConstantesBD.ColEstimacion[0] + "=" + idRegistro, null, null);
		
		if(cursorEstimacion.moveToFirst()) {
//			estimacion.setIdEstimacion(cursorEstimacion.getLong(0));
			estimacion.setIdDispo(cursorEstimacion.getLong(0));
			estimacion.setEstimacion(cursorEstimacion.getLong(1));
			estimacion.setIdObra(cursorEstimacion.getLong(2));
			estimacion.setIdConcepto(cursorEstimacion.getLong(3));
			estimacion.setFechaInicio(cursorEstimacion.getString(4));
			estimacion.setFechaFin(cursorEstimacion.getString(5));
			estimacion.setNumero(cursorEstimacion.getLong(6));
			estimacion.setCantidadAutorizada(cursorEstimacion.getLong(7));
			estimacion.setFechaEstimacion(cursorEstimacion.getString(8));
		}
		cursorEstimacion.close();

		return estimacion;
	}
	
	public boolean puedeEliminar(long numEstimacion){
		
		Cursor cursor = contentRes.query(Uri.parse(
				ConstantesBD.CAO_URL + ConstantesBD.nomTablaEstimacion),
				ConstantesBD.ColEstimacion, 
				ConstantesBD.ColEstimacion[1] + " = " + numEstimacion, null, null);
		while(cursor.moveToNext()){
			if(cursor.getLong(9) == Utl_Constantes.REG_SINC){
				return false;
			}
		}
		cursor.close();
		
		return true;
	}
	
	public void deleteEstimacion(long idEstimacion){
		int val = contentRes.delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEstimacion),
				ConstantesBD.ColEstimacion[0] + " = " + idEstimacion, null);
		if(val != 0){
			bus_sync.deleteSync(Utl_Constantes.TABLA_ESTIMACION, idEstimacion);
		}
			
	}
	
}
