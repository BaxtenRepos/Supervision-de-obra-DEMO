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
import com.csgit.cao.model.communicationchannel.model.ReporteDiario;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Fechas;
import com.csgit.cao.utils.Utl_Uri;

public class Bus_ReporteDiario {
	
	private static final String TAG = "Insert Reporte Diario";
//	private ContentResolver contentRes;
	private Context context;
	
	public  Bus_ReporteDiario(Context context){
		this.context = context;
	}
	
	/**
	 * Obtene un reporte diario en especifico
	 * @param idReporteDiario
	 * @return objeto tipo reporte diario
	 */
	public ReporteDiario getThisReporteDiario(long idReporteDiario){
		ReporteDiario reporte = new ReporteDiario();
		
		Cursor cursor = context.
				getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaReporteDiario), ConstantesBD.ColReporteDiario,
				ConstantesBD.ColReporteDiario[0] + " = " + idReporteDiario, null, null);
		if(cursor.moveToFirst()){
//			reporte.setIdReporteDiario(cursor.getLong(0));
			reporte.setIdDispo(cursor.getLong(0));
			reporte.setIdObra(cursor.getLong(1));
			reporte.setFechaReporteDiario(cursor.getString(2));
		}
		cursor.close();
		
		return reporte;
	}
	
	public void updateSyncReporteDiario(long idReporteDiario){
		ContentValues values = new ContentValues();
		values.put(ConstantesBD.ColReporteDiario[3], Utl_Constantes.REG_SINC);
		context.
		getContentResolver().update(Uri.parse(ConstantesBD.CAO_URL +  ConstantesBD.nomTablaReporteDiario),
				values, ConstantesBD.ColReporteDiario[0] + " = " + idReporteDiario, null);
	}
	
	/**
	 * Borra un reporte dirario
	 * @param idReporte
	 */
	public void deleteReporteDiario(long idReporte){
		context.
		getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaReporteDiario),
				ConstantesBD.ColReporteDiario[0] + " = " + idReporte, null);
		
		Bus_Sync sync = new Bus_Sync(context);
		sync.deleteSync(Utl_Constantes.TABLA_REPORTE_DIARIO, idReporte);
	}
	
	/**
	 * Actualiza el numero de elementos del reporte diario
	 * @param idReporte
	 */
	public void sumaElementoToReporte(long idReporte){
		
		String[] project = {ConstantesBD.ColReporteDiario[4]};
		Cursor cursor = context.
				getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaReporteDiario),
				project, ConstantesBD.ColReporteDiario[0] + " = " + idReporte, null, null);
		long elementos = 0;
		if(cursor.moveToFirst()){
			elementos = cursor.getLong(0);
		}
		cursor.close();
		
		ContentValues values = new ContentValues();
		values.put(ConstantesBD.ColReporteDiario[4], elementos+=1);
		
		context.
		getContentResolver().update(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaReporteDiario),
				values, ConstantesBD.ColReporteDiario[0] + " = " + idReporte, null);
	}
	
	/**
	 * Actualiza el numero de elementos del reporte diario
	 * @param idReporte
	 */
	public void restaElementoToReporte(long idReporte){
		
		String[] project = {ConstantesBD.ColReporteDiario[4]};
		Cursor cursor = context.
				getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaReporteDiario),
				project, ConstantesBD.ColReporteDiario[0] + " = " + idReporte, null, null);
		long elementos = 0;
		if(cursor.moveToFirst()){
			elementos = cursor.getLong(0);
		}
		cursor.close();
		
		ContentValues values = new ContentValues();
		values.put(ConstantesBD.ColReporteDiario[4], elementos-=1);
		
		context.
		getContentResolver().update(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaReporteDiario),
				values, ConstantesBD.ColReporteDiario[0] + " = " + idReporte, null);
	}
	
	/**
	 * Verifica si el numero de elementos del reporte es 0
	 * @param idReporte
	 * @return
	 */
	public boolean verificaElementos(long idReporte){
		Cursor reporteDiario = context.
				getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaReporteDiario), ConstantesBD.ColReporteDiario, 
				ConstantesBD.ColReporteDiario[0] + " = " + idReporte, null, null);
		if(reporteDiario.moveToFirst()){
			if(reporteDiario.getLong(4) == 0){
				reporteDiario.close();
				return true;
			}		
		}
		reporteDiario.close();
		return false;
	}

	/**
	 * Genera un nuevo reporte diario
	 * @param idObra
	 * @return
	 */
	public long genearNuevoReporteDiario(Long idObra){
		ContentValues values = new ContentValues();
		values.put(ConstantesBD.ColReporteDiario[1], idObra);
		values.put(ConstantesBD.ColReporteDiario[2], Utl_Fechas.getFecha());
		values.put(ConstantesBD.ColReporteDiario[3], Utl_Constantes.REG_NO_SINC);
		values.put(ConstantesBD.ColReporteDiario[4], 0);

		Uri uri = context.
				getContentResolver().insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaReporteDiario), values);
		Log.i(TAG, uri.toString());

		// temporal		
		long idSync = Utl_Uri.getIdInsert(uri);
		ContentValues valTemp = new ContentValues();
		valTemp.put(ConstantesBD.colSync[1], Utl_Constantes.TABLA_REPORTE_DIARIO);
		valTemp.put(ConstantesBD.colSync[2], idSync);
		valTemp.put(ConstantesBD.colSync[3], Utl_Constantes.ACC_INSERT);
		valTemp.put(ConstantesBD.colSync[4], Utl_Constantes.REG_NO_SINC);
		context.
		getContentResolver().insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), valTemp);
		

		return Utl_Uri.getIdInsert(uri);
	}
	
	/**
	 * Verifica si el reporte diario ya esta sincronizado
	 * @param idReporte
	 * @return true si esta sincronizado, false en caso constrario
	 */
	public boolean reporteDiarioIsSync(long idReporteDiario){
		Cursor reporteDiario = context.
				getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaReporteDiario), ConstantesBD.ColReporteDiario, 
				ConstantesBD.ColReporteDiario[0] + " = " + idReporteDiario, null, null);
		if(reporteDiario.moveToFirst()){
			if(reporteDiario.getInt(3) == Utl_Constantes.REG_SINC){
				reporteDiario.close();
				return true;
			}		
		}
		reporteDiario.close();
		return false;
	}
	
	/**
	 * Verifica si existe un reporte diario no sincronizado de la obra
	 * @param idObra
	 * @return idReporteDiario
	 */
	public Long verificarIdReporteDiario(Long idObra){
		Long idReporteDiario = null;
		Cursor reporteDiario = context.
				getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaReporteDiario), ConstantesBD.ColReporteDiario, 
				ConstantesBD.ColReporteDiario[1] + " = " + idObra, null, null);
		if(reporteDiario.moveToLast()){
			if(reporteDiario.getInt(3) == Utl_Constantes.REG_NO_SINC){
				idReporteDiario = reporteDiario.getLong(0);	
			}		
		}
		reporteDiario.close();
		return idReporteDiario;
	}
	
	/**
	 * Obtiene el id del ultimo reporte diario generado de una obra 
	 * @param idObra
	 * @return idReporteDiario
	 */
//	public Long getIdReporteDiario(Long idObra){
//		Long idReporteDiario = Long.parseLong("0");
//		Cursor cursorReporteDiario = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
//				ConstantesBD.nomTablaReporteDiario), 
//				ConstantesBD.ColReporteDiario, 
//				ConstantesBD.ColReporteDiario[1] + " = " + idObra, null, null);
//		if(cursorReporteDiario.moveToLast()){
//			idReporteDiario = cursorReporteDiario.getLong(0);			
//		}
//		cursorReporteDiario.close();
//		return idReporteDiario;
//	}
	
	// temporal de getIdReporteDiario
	public List<Long> getIdsReportesDiariosFromObra(long idObra, long idRD){
		List<Long> ids = new ArrayList<Long>();
//		Cursor cursorReporteDiario = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
//				ConstantesBD.nomTablaReporteDiario), 
//				ConstantesBD.ColReporteDiario, 
//				ConstantesBD.ColReporteDiario[1] + " = " + idObra, null, null);
		Cursor cursorReporteDiario = context.
				getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaReporteDiario), 
				ConstantesBD.ColReporteDiario, 
				ConstantesBD.ColReporteDiario[0] + " = " + idRD + " and " + 
				ConstantesBD.ColReporteDiario[1] + " = " + idObra, null, null);
		while(cursorReporteDiario.moveToNext()){
			ids.add(cursorReporteDiario.getLong(0));
		}
		cursorReporteDiario.close();
		return ids;
	}
	
	// temporal de getIdReporteDiario
		public List<Long> getIdsRDNotas(long idObra){
			List<Long> ids = new ArrayList<Long>();
//			Cursor cursorReporteDiario = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
//					ConstantesBD.nomTablaReporteDiario), 
//					ConstantesBD.ColReporteDiario, 
//					ConstantesBD.ColReporteDiario[1] + " = " + idObra, null, null);
			Cursor cursorReporteDiario = context.
					getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + 
					ConstantesBD.nomTablaReporteDiario), 
					ConstantesBD.ColReporteDiario, 
					ConstantesBD.ColReporteDiario[1] + " = " + idObra, null, null);
			while(cursorReporteDiario.moveToNext()){
				ids.add(cursorReporteDiario.getLong(0));
			}
			cursorReporteDiario.close();
			return ids;
		}
	
	/**
	 * Obtiene el id del ultimo reporte diario generado de una obra 
	 * @param idObra
	 * @return idReporteDiario
	 */
	public Long getIdReporteDiarioMultimedia(Long idObra){
		Long idReporteDiario = Long.parseLong("0");
		Cursor cursorReporteDiario = context.
				getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaReporteDiario), 
				ConstantesBD.ColReporteDiario, 
				ConstantesBD.ColReporteDiario[1] + " = " + idObra, null, null);
		if(cursorReporteDiario.moveToLast()){
			idReporteDiario = cursorReporteDiario.getLong(0);			
		}
		cursorReporteDiario.close();
		return idReporteDiario;
	}
	
	
	/**
	 * Obtiene todos los reportes diario de la base de datos
	 * @param contentR contentProvider de la Base de Datos
	 * @return Lista con todos los reportes diarios
	 */
	public List<ReporteDiario> getReporteDiario(ContentResolver contentR){
		
		List<ReporteDiario> lista = new ArrayList<ReporteDiario>();
		
		Cursor cursorReporteDiario = contentR.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaReporteDiario),
				ConstantesBD.ColReporteDiario, null, null, null);
		while(cursorReporteDiario.moveToNext()){
			ReporteDiario reporte = new ReporteDiario();
			reporte.setIdReporteDiario(cursorReporteDiario.getLong(0));
			reporte.setIdObra(cursorReporteDiario.getLong(1));
			reporte.setFechaReporteDiario(cursorReporteDiario.getString(2));
			
			lista.add(reporte);
		}
		cursorReporteDiario.close();
		
		return lista;
	}
	
	/**
	 * Obtiene la lista de reportes de Maquinaria anteriores
	 * @param contentR
	 * @param idObra
	 * @return lista de reporte de maquinaria
	 */
	public List<ReporteDiario> getReporteDiarioMaquinaria(ContentResolver contentR, Long idObra){
		List<ReporteDiario> lista = new ArrayList<ReporteDiario>();
		
		Cursor cursorReporteDiario = contentR.query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaReporteDiario), ConstantesBD.ColReporteDiario, 
				ConstantesBD.ColReporteDiario[1] + " = " + idObra, null, null);
		
		while(cursorReporteDiario.moveToNext()){
			Cursor cursorReporteDiarioMaquinaria = contentR.query(Uri.parse(ConstantesBD.CAO_URL + 
					ConstantesBD.nomTablaRepoMaquinariaCatMaquinaria), ConstantesBD.ColRepoMaquinariaCatMaquinaria, 
					ConstantesBD.ColRepoMaquinariaCatMaquinaria[2] + " = " + cursorReporteDiario.getLong(0),
					null, null);
			if(cursorReporteDiarioMaquinaria.moveToFirst()){
				ReporteDiario reporte = new ReporteDiario();
				reporte.setIdReporteDiario(cursorReporteDiario.getLong(0));
				reporte.setIdObra(cursorReporteDiario.getLong(1));
				reporte.setFechaReporteDiario(cursorReporteDiario.getString(2));
				reporte.setClave(cursorReporteDiario.getInt(3));
				
				lista.add(reporte);
			}
			cursorReporteDiarioMaquinaria.close();
		}
		cursorReporteDiario.close();
		return lista;
	}
	
	
	
	/**
	 * Obtiene la lista de reportes de notas de  una obra
	 * @param contentR
	 * @param idObra
	 * @return lista de reportes de obra
	 */
	public List<ReporteDiario> getReporteDiarioNotas(ContentResolver contentR, Long idObra){
		List<ReporteDiario> lista = new ArrayList<ReporteDiario>();
		
		Cursor cursorReporteDiario = contentR.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaReporteDiario),
				ConstantesBD.ColReporteDiario, ConstantesBD.ColReporteDiario[1] + "=" + idObra, null, null);
		
		while(cursorReporteDiario.moveToNext()){
			Cursor cursorReporteDiarioPersonal = contentR.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaNota),
					ConstantesBD.ColNotas, ConstantesBD.ColNotas[1] + "=" + cursorReporteDiario.getLong(0),
					null, null);
			if(cursorReporteDiarioPersonal.moveToFirst()){
				ReporteDiario reporte = new ReporteDiario();
				reporte.setIdReporteDiario(cursorReporteDiario.getLong(0));
				reporte.setIdObra(cursorReporteDiario.getLong(1));
				reporte.setFechaReporteDiario(cursorReporteDiario.getString(2));
				reporte.setClave(cursorReporteDiario.getInt(3));
				
				lista.add(reporte);
			}
			cursorReporteDiarioPersonal.close();
		}
		cursorReporteDiario.close();
		return lista;
	}
}
