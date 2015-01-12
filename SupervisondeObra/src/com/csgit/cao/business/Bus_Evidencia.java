package com.csgit.cao.business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.csgit.cao.dao.ConstantesBD;
import com.csgit.cao.model.Mod_Multimedia;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Fechas;
import com.csgit.cao.utils.Utl_Uri;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class Bus_Evidencia {

	private static final String TAG = "Insert Evidencia";
	private Bus_ReporteDiario bus_reportediario;
	private ContentResolver contentRes;
	private Bus_Sync bus_sync;
	
	public Bus_Evidencia(Context context){
		this.contentRes = context.getContentResolver();
		bus_reportediario = new Bus_ReporteDiario(context);
		bus_sync =  new Bus_Sync(context);
	}
	
	public boolean masEvidencia(long idObra, boolean tipo){
		
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMultimedia),
				ConstantesBD.ColMultimedia,
				ConstantesBD.ColMultimedia[9] + " = " + idObra, null, null);
		String[] aux = Utl_Fechas.getFecha().split(" ");
		String actual = aux[0];
		int countFoto = 0;
		int countVideo = 0;
		while(cursor.moveToNext()){
			String[] aux1 = cursor.getString(10).toString().split(" ");
			String fecha = aux1[0];
			
			switch (cursor.getInt(5)) {
			case Utl_Constantes.TIPO_IMAGEN:
				if(actual.trim().equals(fecha.trim()))
					countFoto++;	
				break;
			case Utl_Constantes.TIPO_FILE:
				if(actual.trim().equals(fecha.trim()))
					countVideo++;
				break;
				
			default:
				break;
			}
		}
		cursor.close();
		if(tipo){ // foto
			if(countFoto < Utl_Constantes.NUMERO_EVIDENCIA_FOTO_DIA)
				return true;
			else
				return false;
		}else{ // video
			if(countVideo < Utl_Constantes.NUMERO_EVIDENCIA_VIDEO_DIA)
				return true;
			else
				return false;
		}
				
	}
	
	
	/**
	 * Elimina un registro de Evidencia
	 * @param idEvidencia
	 * @param idReporte
	 * @return
	 */
	public int deleteEvidencia(long idEvidencia, long idReporte){
		int op = 0;
		String path = "";
		
		// Obetener path apara borrar el archivo
		Cursor cusorPath = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaMultimedia), ConstantesBD.ColMultimedia,
				ConstantesBD.ColMultimedia[0] + " = " +  idEvidencia, null, null);
		if(cusorPath.moveToFirst())
			path = cusorPath.getString(3);
		cusorPath.close();
		
		
		//		Borrar la evidencia
		int i = contentRes.delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMultimedia),
				ConstantesBD.ColMultimedia[0] + " = "+ idEvidencia, null);
		
		// Borrar archivo fisico
		File file  = new File(path);
		file.delete();
		
		if(i != 0){
			//			Resta numero de elementos
			bus_reportediario.restaElementoToReporte(idReporte);

			//			Verifica Elementos reporte
			if(bus_reportediario.verificaElementos(idReporte)){// ya no tiene elementos asignados
				//				Borrar reporte
				bus_reportediario.deleteReporteDiario(idReporte);
			}
			//			Borrra del la tabla de sicronizacion
			op = bus_sync.deleteSync(Utl_Constantes.TABLA_MULTIMEDIA, idEvidencia);
		}	

		return op;
	} 
	
	public boolean insertarEvidencia(Context context, Mod_Multimedia multimedia, long idObra){
		
		Long idReporteDiario = bus_reportediario.verificarIdReporteDiario(idObra);
		if(idReporteDiario == null){
			idReporteDiario = bus_reportediario.genearNuevoReporteDiario(idObra);
		}
		
		ContentValues values = new ContentValues();
		values.put(ConstantesBD.ColMultimedia[1], idReporteDiario);
		values.put(ConstantesBD.ColMultimedia[2], multimedia.getFormato());
		values.put(ConstantesBD.ColMultimedia[3], multimedia.getPath());
		values.put(ConstantesBD.ColMultimedia[4], multimedia.getDescripcion());
		values.put(ConstantesBD.ColMultimedia[5], multimedia.getTipo());
		values.put(ConstantesBD.ColMultimedia[8], multimedia.getSync());
		values.put(ConstantesBD.ColMultimedia[9], idObra);
		values.put(ConstantesBD.ColMultimedia[10], multimedia.getFecha());
		
		Uri uri = contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMultimedia), values);
		Log.i(TAG, uri.toString());
		
		// temporal
		long idSync = Utl_Uri.getIdInsert(uri);
		ContentValues valTemp = new ContentValues();
		valTemp.put(ConstantesBD.colSync[1], Utl_Constantes.TABLA_MULTIMEDIA);
		valTemp.put(ConstantesBD.colSync[2], idSync);
		valTemp.put(ConstantesBD.colSync[3], Utl_Constantes.ACC_INSERT);
		valTemp.put(ConstantesBD.colSync[4], Utl_Constantes.REG_NO_SINC);
		contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), valTemp);
		
		bus_reportediario.sumaElementoToReporte(idReporteDiario);
		
		return true;
	}
	
	public boolean actualizarEvidencia(ContentResolver contentR, ContentValues values, Long idMultimedia){
		try {
			int id = contentR.update(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMultimedia), values, 
					ConstantesBD.ColMultimedia[0] + " = " + idMultimedia, null);
			Log.i("Update Multimedia", "ID: "+ id);
			
			
			// temporal
			if(!bus_sync.isRegExistForSync(ConstantesBD.nomTablaMultimedia, idMultimedia)){
				ContentValues valTemp = new ContentValues();
				valTemp.put(ConstantesBD.colSync[1], Utl_Constantes.TABLA_MULTIMEDIA);
				valTemp.put(ConstantesBD.colSync[2], idMultimedia);
				valTemp.put(ConstantesBD.colSync[3], Utl_Constantes.ACC_UPDATE);
				valTemp.put(ConstantesBD.colSync[4], Utl_Constantes.REG_NO_SINC);
				contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), valTemp);	
			}
			
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Error", "Error al actualizar multimedia");
			return false;
		}
	}
	
	public long getIdReporteDiarioFromEvidencia(long idEvidencia){
		long idRep = 0;
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMultimedia),
				ConstantesBD.ColMultimedia, ConstantesBD.ColMultimedia[0] + " = " + idEvidencia, null, null);
		if(cursor.moveToFirst())
			idRep = cursor.getLong(1);
		cursor.close();
		
		return idRep;
	}
	
	public static List<Mod_Multimedia> getAllEvidenciaConcepto(ContentResolver contentR, Long idReporte){
		List<Mod_Multimedia> lista = new ArrayList<Mod_Multimedia>();
		
		Cursor cursor = contentR.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMultimedia),
				ConstantesBD.ColMultimedia, ConstantesBD.ColMultimedia[1] + " = " + idReporte, null, null);
		while(cursor.moveToNext()){
			Mod_Multimedia multimedia = new Mod_Multimedia();
			
			multimedia.setIdMultimedia(cursor.getLong(0));
			multimedia.setIdReporteDiario(cursor.getLong(1));
			multimedia.setFormato(cursor.getString(2));
			multimedia.setPath(cursor.getString(3));
			multimedia.setDescripcion(cursor.getString(4));
			multimedia.setTipo(cursor.getInt(5));
			lista.add(multimedia);
		}
		cursor.close();
		
		return lista;
	}
}
