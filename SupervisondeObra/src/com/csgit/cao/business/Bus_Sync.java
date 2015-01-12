package com.csgit.cao.business;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.csgit.cao.dao.ConstantesBD;
import com.csgit.cao.model.Mod_Sync_Temp;
import com.csgit.cao.utils.Utl_Constantes;

public class Bus_Sync {

//	private ContentResolver contentRes;
	private Context context;
	
	public Bus_Sync(Context context){
		this.context = context;
	}
	
//	public List<Mod_RepoteDiario_Completo> getReporteCompleto(){
//		List<Mod_RepoteDiario_Completo> reporteCompleto = new ArrayList<Mod_RepoteDiario_Completo>();
////		Obtener por reporte diario:
////		lista de notas
////		lista de evidencias
////		lista conceptos modificados
////		lista reportes de personal y maquinaria
//		
//		Cursor curObras = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaObra),
//				ConstantesBD.ColObras, null, null, null);
//		while(curObras.moveToNext()){ // por cada obra
//			Cursor curRepotesDiarios = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaReporteDiario),
//					ConstantesBD.ColReporteDiario, ConstantesBD.ColReporteDiario[1] + " = " + curObras.getLong(0), null, null);
//			while(curRepotesDiarios.moveToNext()){ // obtenermos los reportes diarios
//				Mod_RepoteDiario_Completo reporteDiario = new Mod_RepoteDiario_Completo();
//				reporteDiario.setIdReporte(curRepotesDiarios.getLong(0));
//				reporteDiario.setIdObra(curObras.getLong(0));
//				
////				Obtener notas
//				Cursor curNotas = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaNota),
//						ConstantesBD.ColNotas, ConstantesBD.ColNotas[1] + " = " + curRepotesDiarios.getLong(0), null, null);
//				List<Notas> notas = new ArrayList<Notas>();
//				while(curNotas.moveToNext()){
//					Notas nota = new Notas();
//					nota.setIdRepoNotas(curNotas.getLong(0));
//					nota.setIdReporteDiario(curNotas.getLong(1));
//					nota.setDescripcion(curNotas.getString(2));
//					nota.setFecha(curNotas.getString(3));
//					nota.setTitulo(curNotas.getString(4));
//					
//					notas.add(nota);
//				}
//				reporteDiario.setNotas(notas);
//				
////				Obtener multimedia
//				Cursor curMultimedia = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMultimedia),
//						ConstantesBD.ColMultimedia, ConstantesBD.ColMultimedia[1] + " = " + curRepotesDiarios.getLong(0), null, null);
//				List<Mod_Multimedia> evidencias = new ArrayList<Mod_Multimedia>();
//				while(curMultimedia.moveToNext()){
//					Mod_Multimedia evidencia = new Mod_Multimedia();
//					
//					evidencia.setIdMultimedia(curMultimedia.getLong(0));
//					evidencia.setIdReporteDiario(curMultimedia.getLong(1));
//					evidencia.setFormato(curMultimedia.getString(2));
//					evidencia.setPath(curMultimedia.getString(3));
//					evidencia.setDescripcion(curMultimedia.getString(4));
//					evidencia.setTipo(curMultimedia.getInt(5));
//					evidencia.setUrlServer(curMultimedia.getString(6));
//					evidencia.setBlobKey(curMultimedia.getString(7));
//					evidencia.setSync(curMultimedia.getInt(8));
//					
//					evidencias.add(evidencia);
//				}
//				reporteDiario.setEvidencias(evidencias);
//				
////				Obtener Rep Maquinaria
//				Cursor curRepMaquinaria = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaRepoMaquinariaCatMaquinaria),
//						ConstantesBD.ColRepoMaquinariaCatMaquinaria, ConstantesBD.ColRepoMaquinariaCatMaquinaria[2] + " = " + 
//				curRepotesDiarios.getLong(0), null, null);
//				
//				List<Mod_ReporteDiario_Cantidades> repMaquinaria = new ArrayList<Mod_ReporteDiario_Cantidades>();
//				while(curRepMaquinaria.moveToNext()){
//					Mod_ReporteDiario_Cantidades maquinaria = new Mod_ReporteDiario_Cantidades();
////					maquinaria.seti
//				}
//				reporteDiario.setReporteMaquinaria(repMaquinaria);;
//				
//				reporteCompleto.add(reporteDiario);
//			}
//			curRepotesDiarios.close();
//		}
//		curObras.close();
//		
//		return reporteCompleto;
//	}

	public boolean isRegExistForSync(String entidad, long idRegistro){
		
		Cursor cursor = context.
				getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync),
				ConstantesBD.colSync, ConstantesBD.colSync[1] + " = '" + entidad + "'" 
		+ " and " + ConstantesBD.colSync[2] + " = " + idRegistro + " and " + 
						ConstantesBD.colSync[3] + " = '" + Utl_Constantes.ACC_UPDATE +"'", null, null);
		
		if(cursor.moveToFirst())
			return true;
		
		return false;
	}

	public List<List<Mod_Sync_Temp>> getRegistros(){
		List<List<Mod_Sync_Temp>> registros = new ArrayList<List<Mod_Sync_Temp>>();
		
		List<Mod_Sync_Temp> list_insert = new ArrayList<Mod_Sync_Temp>();
		List<Mod_Sync_Temp> list_update = new ArrayList<Mod_Sync_Temp>();
		List<Mod_Sync_Temp> list_delete = new ArrayList<Mod_Sync_Temp>();
		
		Cursor cursor = context.
				getContentResolver().query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaSync), ConstantesBD.colSync,
				ConstantesBD.colSync[4] + " = " +  Utl_Constantes.REG_NO_SINC, null, null);
		while(cursor.moveToNext()){
			
			switch (cursor.getInt(3)) {
			case Utl_Constantes.ACC_INSERT:
				Mod_Sync_Temp insert = new Mod_Sync_Temp();
				insert.setEntidad(cursor.getInt(1));
				insert.setIdRegistro(cursor.getLong(2));
				insert.setAccion(cursor.getInt(3));
				insert.setEstado(cursor.getInt(4));
				list_insert.add(insert);
				break;
			case Utl_Constantes.ACC_UPDATE:
				Mod_Sync_Temp update = new Mod_Sync_Temp();
				update.setEntidad(cursor.getInt(1));
				update.setIdRegistro(cursor.getLong(2));
				update.setAccion(cursor.getInt(3));
				update.setEstado(cursor.getInt(4));
				list_update.add(update);
				break;
			case Utl_Constantes.ACC_DELETE:
				Mod_Sync_Temp delete = new Mod_Sync_Temp();
				delete.setEntidad(cursor.getInt(1));
				delete.setIdRegistro(cursor.getLong(2));
				delete.setAccion(cursor.getInt(3));
				delete.setEstado(cursor.getInt(4));
				list_delete.add(delete);
				break;

			default:
				break;
			}
		}
		cursor.close();
		
		registros.add(list_insert);
		registros.add(list_update);
		registros.add(list_delete);
		
		return registros;
	}

	/**
	 * Actualiza el registro que ya fue enviado al backend
	 * @param entidad
	 * @param idRegistro
	 * @param accion
	 * @return
	 */
	public int updateSync(int entidad, long idRegistro, int accion){
		// Actualizar registro
		ContentValues values = new ContentValues();
		values.put(ConstantesBD.colSync[4], Utl_Constantes.REG_SINC);
		
		int uri = context.
				getContentResolver().update(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), values, 
				ConstantesBD.colSync[1] + "=" + entidad+" and "+
				ConstantesBD.colSync[2] + "=" + idRegistro+ " and "+
				ConstantesBD.colSync[3] + "=" + accion, null);
		
		return uri;
	}
	/**
	 * Borra el registro de la tabla de sicronizacion
	 * @param entidad de que tabla es la registro
	 * @param idRegistro 
	 * @return numero de registros borrados
	 */
	public int deleteSync(int entidad, long idRegistro){
		// Actualizar registro
		
		int uri = context.
				getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), 
				ConstantesBD.colSync[1] + "=" + entidad+" and "+
				ConstantesBD.colSync[2] + "=" + idRegistro, null);
		
		return uri;
	}
}
