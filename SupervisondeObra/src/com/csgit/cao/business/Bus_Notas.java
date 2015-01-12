package com.csgit.cao.business;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.csgit.cao.dao.ConstantesBD;
import com.csgit.cao.model.communicationchannel.model.Notas;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Uri;

public class Bus_Notas {
	private static final String TAG = "Insert Nota";
	
	private Context context;
	private Bus_ReporteDiario bus_reporte;
	private Bus_Sync bus_sync;
	
	public Bus_Notas(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		bus_reporte = new Bus_ReporteDiario(this.context);
		bus_sync = new Bus_Sync(this.context);
	}
	
	public List<Notas> getAllNotas(){
		List<Notas> lista = new ArrayList<Notas>();
		
		Cursor cursorNotas = context.
				getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaNota), 
						ConstantesBD.ColNotas, null, null, null);
		
		while(cursorNotas.moveToNext()){
			Notas notas = new Notas();
			
			notas.setIdRepoNotas(cursorNotas.getLong(0));
			notas.setIdReporteDiario(cursorNotas.getLong(1));
			notas.setDescripcion(cursorNotas.getString(2));
			notas.setFecha(cursorNotas.getString(3));
			notas.setTitulo(cursorNotas.getString(4));
			
			lista.add(notas);
		}
		cursorNotas.close();
		return lista;
	}
	
//	public List<Notas> getNotas(Long idObra){
//		Long idReporteDiario = bus_reporte.getIdReporteDiario(idObra);
//		List<Notas> lista = new ArrayList<Notas>();
//		
//		Cursor cursorNotas = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
//				ConstantesBD.nomTablaNota), ConstantesBD.ColNotas, 
//				ConstantesBD.ColNotas[1] + " = " + idReporteDiario, null, null);
//		
//		while(cursorNotas.moveToNext()){
//			Notas notas = new Notas();
//			
//			notas.setIdRepoNotas(cursorNotas.getLong(0));
//			notas.setIdReporteDiario(cursorNotas.getLong(1));
//			notas.setDescripcion(cursorNotas.getString(2));
//			notas.setFecha(cursorNotas.getString(3));
//			notas.setTitulo(cursorNotas.getString(4));
//			
//			lista.add(notas);
//		}
//		cursorNotas.close();
//		return lista;
//	}
	
	public List<Notas> getNotas(Long idObra){

		List<Notas> lista = new ArrayList<Notas>();
		
//		Long idReporteDiario = bus_reporte.getIdReporteDiario(idObra);
		List<Long> idsRD = bus_reporte.getIdsRDNotas(idObra);
		for (Long long1 : idsRD) {
			Cursor cursorNotas = context.
					getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + 
					ConstantesBD.nomTablaNota), ConstantesBD.ColNotas, 
					ConstantesBD.ColNotas[1] + " = " + long1, null, null);
			
			while(cursorNotas.moveToNext()){
				Notas notas = new Notas();
				
				notas.setIdRepoNotas(cursorNotas.getLong(0));
				notas.setIdReporteDiario(cursorNotas.getLong(1));
				notas.setDescripcion(cursorNotas.getString(2));
				notas.setFecha(cursorNotas.getString(3));
				notas.setTitulo(cursorNotas.getString(4));
				
				lista.add(notas);
			}
			cursorNotas.close();
		}
		
		return lista;
	}
	
	/**
	 * Inserta una nota
	 * @param idObra
	 * @param nota
	 */
	public void insertNota(long idObra, Notas nota){
		Long idReporteDiario = bus_reporte.verificarIdReporteDiario(idObra);
		if(idReporteDiario == null){
			idReporteDiario = bus_reporte.genearNuevoReporteDiario(idObra);
		}
		
		ContentValues values = new ContentValues();
		values.put(ConstantesBD.ColNotas[1], idReporteDiario);
		values.put(ConstantesBD.ColNotas[2], nota.getDescripcion());
		values.put(ConstantesBD.ColNotas[3], nota.getFecha());
		values.put(ConstantesBD.ColNotas[4], nota.getTitulo());
		
		Uri uri = context.
				getContentResolver().insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaNota), values);
		Log.i(TAG, uri.toString());	
		
		// temporal		
		long idSync = Utl_Uri.getIdInsert(uri);
		ContentValues valTemp = new ContentValues();
		valTemp.put(ConstantesBD.colSync[1], Utl_Constantes.TABLA_NOTA);
		valTemp.put(ConstantesBD.colSync[2], idSync);
		valTemp.put(ConstantesBD.colSync[3], Utl_Constantes.ACC_INSERT);
		valTemp.put(ConstantesBD.colSync[4], Utl_Constantes.REG_NO_SINC);
		context.
		getContentResolver().insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), valTemp);
		
//		Actualiza numero de elementos del reporte
		bus_reporte.sumaElementoToReporte(idReporteDiario);
	}
	
	/**
	 * Borra una nota
	 * @param idNota nota a borrar
	 * @return nuemero de registros borrador
	 */
	public int deleteNota(long idNota){
		int op = 0;
		long idReporte = 0;
		
//		Obtener el id del reporte diario del la nota
		Cursor cursorNotas = context.
				getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaNota),
				ConstantesBD.ColNotas, ConstantesBD.ColNotas[0] + " = " + idNota, null, null);
		if(cursorNotas.moveToFirst()){
			idReporte = cursorNotas.getLong(1);
		}
		cursorNotas.close();
				
//		Borrar la nota
		int i = context.
				getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaNota),
				ConstantesBD.ColNotas[0] + " = "+ idNota, null);
		Bus_Sync sync = new Bus_Sync(context);
		if(i != 0){
//			Resta numero de elementos
			bus_reporte.restaElementoToReporte(idReporte);
			
//			Verifica Elementos reporte
			if(bus_reporte.verificaElementos(idReporte)){// ya no tiene elementos asignados
//				Borrar reporte
				bus_reporte.deleteReporteDiario(idReporte);
			}
//			Borrra del la tabla de sicronizacion
			op = sync.deleteSync(Utl_Constantes.TABLA_NOTA, idNota);
		}
		
		return op;
	}

	/**
	 * Actualiza una nota
	 * @param nuevosDatos
	 */
	public void updateNotas(Notas nuevosDatos) {
		// TODO Auto-generated method stub
		ContentValues values =  new ContentValues();
		values.put(ConstantesBD.ColNotas[2],nuevosDatos.getDescripcion());
		values.put(ConstantesBD.ColNotas[4],nuevosDatos.getTitulo());
		context.
		getContentResolver().update(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaNota), values, 
				ConstantesBD.ColNotas[0] + " = " + nuevosDatos.getIdRepoNotas(), null);
		
		// temporal
		if(!bus_sync.isRegExistForSync(ConstantesBD.nomTablaNota, nuevosDatos.getIdRepoNotas())){
			ContentValues valTemp = new ContentValues();
			valTemp.put(ConstantesBD.colSync[1], Utl_Constantes.TABLA_NOTA);
			valTemp.put(ConstantesBD.colSync[2], nuevosDatos.getIdRepoNotas());
			valTemp.put(ConstantesBD.colSync[3], Utl_Constantes.ACC_UPDATE);
			valTemp.put(ConstantesBD.colSync[4], Utl_Constantes.REG_NO_SINC);
			context.
			getContentResolver().insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), valTemp);
		}
	}

	public long getIdReporteDiarioFromNota(long idNota){
		long idRep = 0;
		Cursor cursor = context.
				getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaNota),
				ConstantesBD.ColNotas, ConstantesBD.ColNotas[0] + " = " + idNota, null, null);
		if(cursor.moveToFirst())
			idRep = cursor.getLong(1);
		cursor.close();
		
		return idRep;
	}
	
	public Notas getNota(Long idNota){
		Notas nota = new Notas();	
		Cursor cursorNota = context.
				getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaNota), ConstantesBD.ColNotas, 
				ConstantesBD.ColNotas[0] + " = " + idNota, null, null);
		
		if(cursorNota.moveToFirst()){			
//			nota.setIdRepoNotas(cursorNota.getLong(0));
			nota.setIdDispo(cursorNota.getLong(0));
			nota.setIdReporteDiario(cursorNota.getLong(1));
			nota.setDescripcion(cursorNota.getString(2));
			nota.setFecha(cursorNota.getString(3));
			nota.setTitulo(cursorNota.getString(4));
		}
		cursorNota.close();
		return nota;
	}
}
