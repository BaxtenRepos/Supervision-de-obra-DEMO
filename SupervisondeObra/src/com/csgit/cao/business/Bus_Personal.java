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
import com.csgit.cao.model.Mod_Repo_Personal_Cat_Personal;
import com.csgit.cao.model.communicationchannel.model.CatPersonal;
import com.csgit.cao.model.communicationchannel.model.RepoPersonalCatPersonal;
import com.csgit.cao.model.communicationchannel.model.ReporteDiario;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Uri;

public class Bus_Personal {
	
	private static final String TAG = "Insert Rep. Personal";
	private ContentResolver contentRes;
	
	private Bus_ReporteDiario bus_reporteDiario;
	private Bus_Sync bus_sync;
		
	public  Bus_Personal(Context context) {
		// TODO Auto-generated constructor stub
		this.contentRes = context.getContentResolver();
		bus_reporteDiario = new Bus_ReporteDiario(context);
		bus_sync = new Bus_Sync(context);
	}
	
	/**
	 * Obtiene la lista de reportes de personal anteriores de una obra
	 * @param contentR
	 * @param idObra
	 * @return lista de reportes de personla de alguna obra
	 */
	public List<ReporteDiario> getReporteDiarioPersonal(Long idObra){
		List<ReporteDiario> lista = new ArrayList<ReporteDiario>();
		
		Cursor cursorReporteDiario = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaReporteDiario), ConstantesBD.ColReporteDiario, 
				ConstantesBD.ColReporteDiario[1] + " = " + idObra, null, null);
		
		while(cursorReporteDiario.moveToNext()){
			Cursor cursorReporteDiarioPersonal = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
					ConstantesBD.nomTablaRepoPersonalCatPersonal), ConstantesBD.ColRepoPersonalCatPersonal, 
					ConstantesBD.ColRepoPersonalCatPersonal[2] + " = " + cursorReporteDiario.getLong(0),
					null, null);
			if(cursorReporteDiarioPersonal.moveToFirst()){
				ReporteDiario reporte = new ReporteDiario();
				reporte.setIdReporteDiario(cursorReporteDiario.getLong(0));
				reporte.setIdObra(cursorReporteDiario.getLong(1));
				reporte.setFechaReporteDiario(cursorReporteDiario.getString(2));
				reporte.setClave(cursorReporteDiario.getInt(3));
				
				lista.add(reporte);
			}
//			while(cursorReporteDiarioPersonal.moveToNext()){
//				ReporteDiario reporte = new ReporteDiario();
//				reporte.setIdReporteDiario(cursorReporteDiario.getLong(0));
//				reporte.setIdObra(cursorReporteDiario.getLong(1));
//				reporte.setFechaReporteDiario(cursorReporteDiario.getString(2));
//				reporte.setClave(cursorReporteDiario.getInt(3));
//				
//				lista.add(reporte);
//			}
			cursorReporteDiarioPersonal.close();
		}
		cursorReporteDiario.close();
		return lista;
	}
	
	
	/**
	 * Obtiene el Catalogo de Personal
	 * @return
	 */
	public ArrayList<CatPersonal> getCatPersonal(){
		ArrayList<CatPersonal> lista = new ArrayList<CatPersonal>();
		
		Cursor cursorPersonal = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaCatPersonal), ConstantesBD.ColCatPersonal, 
				ConstantesBD.ColCatPersonal[3] + " = " + Utl_Constantes.REG_VISIBLE_INT, null, null);
		
		while(cursorPersonal.moveToNext()){
			CatPersonal personal = new CatPersonal();
			
			personal.setIdTipoPersonal(cursorPersonal.getLong(0));
			personal.setTipoPersonal(cursorPersonal.getString(1));
			personal.setDescipcion(cursorPersonal.getString(2));
			
			lista.add(personal);
		}
		cursorPersonal.close();
		return lista;
	}
	
	
	/**
	 * Inserta el un nuevo reporte de personal
	 * @param idObra
	 * @param datos
	 */
	public boolean insertRepoPersonal(long idObra, List<Mod_Repo_Personal_Cat_Personal> datos){
		if(datos.size() != 0){
			Long idReporteDiario = bus_reporteDiario.verificarIdReporteDiario(idObra);
			if(idReporteDiario == null){
				idReporteDiario = bus_reporteDiario.genearNuevoReporteDiario(idObra);
			}
			for (Mod_Repo_Personal_Cat_Personal mod_ReporteDiario_Cantidades : datos) {
				ContentValues values = new ContentValues();
				values.put(ConstantesBD.ColRepoPersonalCatPersonal[1], mod_ReporteDiario_Cantidades.getIdCatPersonal());
				values.put(ConstantesBD.ColRepoPersonalCatPersonal[2], idReporteDiario);
				values.put(ConstantesBD.ColRepoPersonalCatPersonal[3], mod_ReporteDiario_Cantidades.getCantidad());
				
				Uri uri = contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaRepoPersonalCatPersonal), values);
				Log.i(TAG, uri.toString());
				
				// temporal		
				long idSync = Utl_Uri.getIdInsert(uri);
				ContentValues valTemp = new ContentValues();
				valTemp.put(ConstantesBD.colSync[1], Utl_Constantes.TABLA_REPO_PERSONAL_CAT_PERSONAL);
				valTemp.put(ConstantesBD.colSync[2], idSync);
				valTemp.put(ConstantesBD.colSync[3], Utl_Constantes.ACC_INSERT);
				valTemp.put(ConstantesBD.colSync[4], Utl_Constantes.REG_NO_SINC);
				contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), valTemp);
				
				bus_reporteDiario.sumaElementoToReporte(idReporteDiario);
			}
			return true;
		}
		return false;
	}
	
	public void updateReporteMaquinaria(List<Mod_Repo_Personal_Cat_Personal> listPersonal){
		for (Mod_Repo_Personal_Cat_Personal personal : listPersonal) {
			ContentValues values = new ContentValues();
			values.put(ConstantesBD.ColRepoPersonalCatPersonal[3], personal.getCantidad());
			
			contentRes.update(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaRepoPersonalCatPersonal),
					values, ConstantesBD.ColRepoPersonalCatPersonal[0] + " = " + 
			personal.getIdRepoPersonalCatPersonal(), null);
			
			if(!bus_sync.isRegExistForSync(ConstantesBD.nomTablaRepoPersonalCatPersonal, 
					personal.getIdRepoPersonalCatPersonal())){
				
				ContentValues valTemp = new ContentValues();
				valTemp.put(ConstantesBD.colSync[1], Utl_Constantes.TABLA_REPO_PERSONAL_CAT_PERSONAL);
				valTemp.put(ConstantesBD.colSync[2], personal.getIdRepoPersonalCatPersonal());
				valTemp.put(ConstantesBD.colSync[3], Utl_Constantes.ACC_UPDATE);
				valTemp.put(ConstantesBD.colSync[4], Utl_Constantes.REG_NO_SINC);
				contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), valTemp);
			}
		}
	}
	
	
	public int deleteReportePersonal(long idRegistro, long idReporteDiario){
		if(idRegistro == 0)
			return 1;
		
		int op = 0;
		int i = contentRes.delete(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaRepoPersonalCatPersonal),
				ConstantesBD.ColRepoPersonalCatPersonal[0] + " = " + idRegistro, null);
		
		if(i != 0){
//			Resta numero de elementos
			bus_reporteDiario.restaElementoToReporte(idReporteDiario);
			
//			Verifica Elementos reporte
			if(bus_reporteDiario.verificaElementos(idReporteDiario)){// ya no tiene elementos asignados
//				Borrar reporte
				bus_reporteDiario.deleteReporteDiario(idReporteDiario);
			}
//			Borrra del la tabla de sicronizacion
			op = bus_sync.deleteSync(Utl_Constantes.TABLA_REPO_PERSONAL_CAT_PERSONAL, idRegistro);
		}
		
		return op;
	}
	
	public List<Mod_Repo_Personal_Cat_Personal> getReportesPersonal(Long idObra, long idRD){
		List<Mod_Repo_Personal_Cat_Personal> lista = new ArrayList<Mod_Repo_Personal_Cat_Personal>();
		List<Long> idsRD = bus_reporteDiario.getIdsReportesDiariosFromObra(idObra, idRD);
		for (Long long1 : idsRD) {
			Cursor cursorRepoCatPersonal  = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
					ConstantesBD.nomTablaRepoPersonalCatPersonal), ConstantesBD.ColRepoPersonalCatPersonal, 
					ConstantesBD.ColRepoPersonalCatPersonal[2] + "=" + long1, null, null);
			
			while(cursorRepoCatPersonal.moveToNext()){
				Mod_Repo_Personal_Cat_Personal repoPersonal = new Mod_Repo_Personal_Cat_Personal();
				
				repoPersonal.setIdRepoPersonalCatPersonal(cursorRepoCatPersonal.getLong(0));
				repoPersonal.setIdCatPersonal(cursorRepoCatPersonal.getLong(1));
				repoPersonal.setIdReporteDiario(cursorRepoCatPersonal.getLong(2));
				repoPersonal.setCantidad(cursorRepoCatPersonal.getLong(3));
				
				Cursor cursorCatPersonal  = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaCatPersonal), ConstantesBD.ColCatPersonal, 
						ConstantesBD.ColCatPersonal[0] + "=" + repoPersonal.getIdCatPersonal(), null, null);
				
				if(cursorCatPersonal.moveToFirst()) {
					repoPersonal.setNombre(cursorCatPersonal.getString(1));
				}
				repoPersonal.setAuxAccion(Utl_Constantes.TIPO_ACCION_NULL);
				
				cursorCatPersonal.close();			
				lista.add(repoPersonal);
			}
			cursorRepoCatPersonal.close();
		}
		
		return lista;
		
	}
	
	public RepoPersonalCatPersonal getReportesPersonalSync(long idRegistro){
		
		RepoPersonalCatPersonal repo = new RepoPersonalCatPersonal();
		
		Cursor cursorRepoCatPersonal  = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaRepoPersonalCatPersonal), ConstantesBD.ColRepoPersonalCatPersonal, 
				ConstantesBD.ColRepoPersonalCatPersonal[0] + " = " + idRegistro, null, null);
		
		if(cursorRepoCatPersonal.moveToFirst()){
//			repo.setIdPropietario(cursorRepoCatPersonal.getLong(0));
			repo.setIdDispo(cursorRepoCatPersonal.getLong(0));
			repo.setIdCatPersonal(cursorRepoCatPersonal.getLong(1));
			repo.setIdReporteDiario(cursorRepoCatPersonal.getLong(2));
			repo.setCantidad(cursorRepoCatPersonal.getLong(3));
				
		}
		cursorRepoCatPersonal.close();
		return repo;
		
	}

	/**
	 * Obtiene el idReporte diario de un registro.
	 * @param idRegistro propietario del elemento
	 * @return
	 */
	public long getIdReporteDiarioFromPersonal(long idRegistro){
		long idReporte = 0;
		
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaRepoPersonalCatPersonal),
				ConstantesBD.ColRepoPersonalCatPersonal,
				ConstantesBD.ColRepoPersonalCatPersonal[0] + " = " + idRegistro, null, null);
		if(cursor.moveToFirst())
			idReporte = cursor.getLong(2);
		cursor.close();
		
		return idReporte;
	}
	
}
