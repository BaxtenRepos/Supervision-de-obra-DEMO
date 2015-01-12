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
import com.csgit.cao.model.Mod_Repo_Maquinaria_Cat_Maquinaria;
import com.csgit.cao.model.communicationchannel.model.CatTipoMaquinaria;
import com.csgit.cao.model.communicationchannel.model.Maquinaria;
import com.csgit.cao.model.communicationchannel.model.RepMaquinariaCatMaquinaria;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Uri;

public class Bus_Maquinaria {
	
	private static final String TAG = "Insert Rep. Maquinaria";
	private ContentResolver contentRes;
	
	private Bus_ReporteDiario bus_repoteDiario;
	private Bus_Sync bus_sync;

	public Bus_Maquinaria(Context context) {
		// TODO Auto-generated constructor stub
		this.contentRes = context.getContentResolver();
		bus_repoteDiario = new Bus_ReporteDiario(context);
		bus_sync = new Bus_Sync(context);
	}
	
	public int updatePathImagenMaquinaria(long idMaquinaria, String pathImagen){
		ContentValues values = new ContentValues();
		values.put(ConstantesBD.ColCatMaquinaria[2], pathImagen);
		
		int val = contentRes.update(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaCatMaquinaria), values,
				ConstantesBD.ColCatMaquinaria[0] + " = " + idMaquinaria, null);
		
		return val;
	}
	
	public ArrayList<CatTipoMaquinaria> getTipoMaquinaria(){
		ArrayList<CatTipoMaquinaria> lista = new ArrayList<CatTipoMaquinaria>();
		
		Cursor cursorTipoM = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaCatTipoMaquinaria), ConstantesBD.ColCatTipoMaquinaria, 
				null, null, null);
		
		while(cursorTipoM.moveToNext()){
			CatTipoMaquinaria tipoM = new CatTipoMaquinaria();
			
			tipoM .setIdTipoMaquinaria(cursorTipoM.getLong(0));
			tipoM .setTipoMaquinaria(cursorTipoM.getString(1));
			tipoM .setDescripcion(cursorTipoM.getString(2));
			lista.add(tipoM );
		}
		cursorTipoM.close();
		return lista;
	}
	
	public ArrayList<Maquinaria> getCatMaquinaria(){
		ArrayList<Maquinaria> lista = new ArrayList<Maquinaria>();
		
		Cursor cursorMaqui = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaCatMaquinaria), ConstantesBD.ColCatMaquinaria, 
				ConstantesBD.ColCatMaquinaria[4] + " = " + Utl_Constantes.REG_VISIBLE_INT, null, null);
		
		while(cursorMaqui.moveToNext()){
			Maquinaria Maqui = new Maquinaria();
			
			Maqui.setIdMaquinaria(cursorMaqui.getLong(0));
			Maqui.setIdTipoMaquinaria(cursorMaqui.getLong(1));
			Maqui.setPathImagen(cursorMaqui.getString(2));
			Maqui.setDescripcion(cursorMaqui.getString(3));
			lista.add(Maqui);
		}
		cursorMaqui.close();
		return lista;
	}
	
	/**
	 * Inserta un nuevo reporte de maquinaria
	 * @param idObra
	 * @param datos
	 */
	public void insertReporteMaquinaria(long idObra, List<Mod_Repo_Maquinaria_Cat_Maquinaria> datos){
		Long idReporteDiario = bus_repoteDiario.verificarIdReporteDiario(idObra);
		if(idReporteDiario == null){
			idReporteDiario = bus_repoteDiario.genearNuevoReporteDiario(idObra);
		}
		for (Mod_Repo_Maquinaria_Cat_Maquinaria mod_ReporteDiario_Cantidades : datos) {
			ContentValues values = new ContentValues();
			values.put(ConstantesBD.ColRepoMaquinariaCatMaquinaria[1], mod_ReporteDiario_Cantidades.getIdCatMaquinaria());
			values.put(ConstantesBD.ColRepoMaquinariaCatMaquinaria[2], idReporteDiario);
			values.put(ConstantesBD.ColRepoMaquinariaCatMaquinaria[3], mod_ReporteDiario_Cantidades.getCantidad());
			
			Uri uri = contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaRepoMaquinariaCatMaquinaria), values);
			Log.i(TAG, uri.toString());
			
			// temporal		
			long idSync = Utl_Uri.getIdInsert(uri);
			ContentValues valTemp = new ContentValues();
			valTemp.put(ConstantesBD.colSync[1], Utl_Constantes.TABLA_REPO_MAQUINARIA_CAT_MAQUINARIA);
			valTemp.put(ConstantesBD.colSync[2], idSync);
			valTemp.put(ConstantesBD.colSync[3], Utl_Constantes.ACC_INSERT);
			valTemp.put(ConstantesBD.colSync[4], Utl_Constantes.REG_NO_SINC);
			contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), valTemp);
			
			bus_repoteDiario.sumaElementoToReporte(idReporteDiario);
		}
	}
	
	public ArrayList<Mod_Repo_Maquinaria_Cat_Maquinaria> getReportesMaquinaria(Long idObra, long idRD){
		ArrayList<Mod_Repo_Maquinaria_Cat_Maquinaria> lista = new ArrayList<Mod_Repo_Maquinaria_Cat_Maquinaria>();
		List<Long> idsRD = bus_repoteDiario.getIdsReportesDiariosFromObra(idObra, idRD);
		
		for (Long long1 : idsRD) {
			Cursor cursorRepoCatMaquinaria  = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
					ConstantesBD.nomTablaRepoMaquinariaCatMaquinaria), ConstantesBD.ColRepoMaquinariaCatMaquinaria, 
					ConstantesBD.ColRepoMaquinariaCatMaquinaria[2] + "=" + long1, null, null);
			
			while(cursorRepoCatMaquinaria.moveToNext()){
				Mod_Repo_Maquinaria_Cat_Maquinaria repoMaquinaria = new Mod_Repo_Maquinaria_Cat_Maquinaria();
				
				repoMaquinaria.setIdRepoMaquinariaCatMaquinaria(cursorRepoCatMaquinaria.getLong(0));
				repoMaquinaria.setIdCatMaquinaria(cursorRepoCatMaquinaria.getLong(1));
				repoMaquinaria.setIdReporteDiario(cursorRepoCatMaquinaria.getLong(2));
//				repoMaquinaria.setCantidad(cursorRepoCatMaquinaria.getInt(3));
				repoMaquinaria.setCantidad(cursorRepoCatMaquinaria.getLong(3));
				
				Cursor cursorCatMaquinaria  = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaCatMaquinaria), ConstantesBD.ColCatMaquinaria, 
						ConstantesBD.ColCatMaquinaria[0] + "=" + cursorRepoCatMaquinaria.getLong(1), null, null);
				
				if(cursorCatMaquinaria.moveToFirst()) {
					repoMaquinaria.setNombre(cursorCatMaquinaria.getString(3));
				}
				repoMaquinaria.setAuxAccion(Utl_Constantes.TIPO_ACCION_NULL);
				
				cursorCatMaquinaria.close();			
				lista.add(repoMaquinaria);
			}
			cursorRepoCatMaquinaria.close();
		}
		
		return lista;
		
	}
	
	public RepMaquinariaCatMaquinaria getReporteMaquinariaSync(long idRegistro){

		RepMaquinariaCatMaquinaria repMaquinaria = new RepMaquinariaCatMaquinaria();
		
		Cursor cursorRepoCatMaquinaria  = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaRepoMaquinariaCatMaquinaria), ConstantesBD.ColRepoMaquinariaCatMaquinaria, 
				ConstantesBD.ColRepoMaquinariaCatMaquinaria[0] + "=" + idRegistro, null, null);
		
		if(cursorRepoCatMaquinaria.moveToFirst()){
//			repMaquinaria.setIdRepMaquinaria(cursorRepoCatMaquinaria.getLong(0));
			repMaquinaria.setIdDispo(cursorRepoCatMaquinaria.getLong(0));
			repMaquinaria.setIdCatMaquinaria(cursorRepoCatMaquinaria.getLong(1));
			repMaquinaria.setIdReporteDiario(cursorRepoCatMaquinaria.getLong(2));
			repMaquinaria.setCantidad(cursorRepoCatMaquinaria.getLong(3));	
		}
		cursorRepoCatMaquinaria.close();
		return repMaquinaria;
		
	}

	public long getIdReporteDiarioFromMaquinaria(long idRegistro){
		long idReporte = 0;
		
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaRepoMaquinariaCatMaquinaria), 
				ConstantesBD.ColRepoMaquinariaCatMaquinaria,
				ConstantesBD.ColRepoMaquinariaCatMaquinaria[0] + " = " + idRegistro, null, null);
		if(cursor.moveToFirst())
			idReporte = cursor.getLong(2);
		cursor.close();
		
		return idReporte;
	}
	
	public void updateReporteMaquinaria(List<Mod_Repo_Maquinaria_Cat_Maquinaria> listMaquinaria){
		
		for (Mod_Repo_Maquinaria_Cat_Maquinaria maquinaria : listMaquinaria) {
			ContentValues values = new ContentValues();
			values.put(ConstantesBD.ColRepoMaquinariaCatMaquinaria[3], maquinaria.getCantidad());
			
			contentRes.update(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaRepoMaquinariaCatMaquinaria),
					values, ConstantesBD.ColRepoMaquinariaCatMaquinaria[0] + " = " + 
			maquinaria.getIdRepoMaquinariaCatMaquinaria(), null);
			
			if(!bus_sync.isRegExistForSync(ConstantesBD.nomTablaRepoMaquinariaCatMaquinaria, 
					maquinaria.getIdRepoMaquinariaCatMaquinaria())){
				
				ContentValues valTemp = new ContentValues();
				valTemp.put(ConstantesBD.colSync[1], Utl_Constantes.TABLA_REPO_MAQUINARIA_CAT_MAQUINARIA);
				valTemp.put(ConstantesBD.colSync[2], maquinaria.getIdRepoMaquinariaCatMaquinaria());
				valTemp.put(ConstantesBD.colSync[3], Utl_Constantes.ACC_UPDATE);
				valTemp.put(ConstantesBD.colSync[4], Utl_Constantes.REG_NO_SINC);
				contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), valTemp);
			}
		}
	}
	
	public int deleteReporteMaquinaria(long idRegistro, long idReporteDiario){
		int op = 0;
		int i = contentRes.delete(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaRepoMaquinariaCatMaquinaria),
				ConstantesBD.ColRepoMaquinariaCatMaquinaria[0] + " = " + idRegistro, null);
		
		if(i != 0){
//			Resta numero de elementos
			bus_repoteDiario.restaElementoToReporte(idReporteDiario);
			
//			Verifica Elementos reporte
			if(bus_repoteDiario.verificaElementos(idReporteDiario)){// ya no tiene elementos asignados
//				Borrar reporte
				bus_repoteDiario.deleteReporteDiario(idReporteDiario);
			}
//			Borrra del la tabla de sicronizacion
			op = bus_sync.deleteSync(Utl_Constantes.TABLA_REPO_MAQUINARIA_CAT_MAQUINARIA, idRegistro);
		}
		
		return op;
	}
	
	public boolean isMaquinariaExist(long idMaquinaria){
		String[] col = {ConstantesBD.ColCatMaquinaria[0], ConstantesBD.ColCatMaquinaria[2]}; 
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaCatMaquinaria),
				col, col[0] + " = " + idMaquinaria, null, null);
		if(cursor.moveToFirst()){
			if(cursor.getString(1).isEmpty()){
				cursor.close();
				return false;
			}
		}
		cursor.close();
		return true;
	}
}
