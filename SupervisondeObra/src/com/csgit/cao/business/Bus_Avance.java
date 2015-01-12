package com.csgit.cao.business;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.csgit.cao.dao.ConstantesBD;
import com.csgit.cao.model.communicationchannel.model.AvanceFinanciero;
import com.csgit.cao.model.communicationchannel.model.AvanceFisico;

public class Bus_Avance {
	
	private ContentResolver contentRes;
	
	public Bus_Avance (ContentResolver contR){
		this.contentRes = contR;
	}
	
	public void insertNuevoAvanceFisico(AvanceFisico avance){
		ContentValues values = new ContentValues();
		
		values.put(ConstantesBD.ColAvanceFisico[0], avance.getIdAvanceFisico());
		values.put(ConstantesBD.ColAvanceFisico[1], avance.getIdReferencia());
		values.put(ConstantesBD.ColAvanceFisico[2], avance.getPavanceFisico());
		values.put(ConstantesBD.ColAvanceFisico[3], avance.getPorcentajeTendencia());
		values.put(ConstantesBD.ColAvanceFisico[4], avance.getFechaReporte().toString());
		values.put(ConstantesBD.ColAvanceFisico[5], avance.getEstado());
		
	}
	
	public List<AvanceFisico> getAvanceFisico(Long idObra){
		
		List<AvanceFisico> listaFisico = new ArrayList<AvanceFisico>();
		
		Cursor cursorAvanceFisico = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaAvanceFisico), 
				ConstantesBD.ColAvanceFisico, ConstantesBD.ColAvanceFisico[1] + " = " + idObra, null, null);
		while(cursorAvanceFisico.moveToNext()){
			AvanceFisico avanFisico = new AvanceFisico();
			avanFisico.setIdAvanceFisico(cursorAvanceFisico.getLong(0));
			avanFisico.setPavanceFisico(cursorAvanceFisico.getDouble(2));
			avanFisico.setPorcentajeTendencia(cursorAvanceFisico.getDouble(3));
			avanFisico.setFechaReporte(cursorAvanceFisico.getString(4));
			avanFisico.setEstado(cursorAvanceFisico.getInt(5));
			listaFisico.add(avanFisico);
		}
		cursorAvanceFisico.close();
		return listaFisico;
	}
	public List<AvanceFinanciero> getAvanceFinanciero(Long idObra){
		
		List<AvanceFinanciero> listaFinanciero = new ArrayList<AvanceFinanciero>();
		
		Cursor cursorAvanceFinanciero = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaAvanceFinanciero), 
				ConstantesBD.ColAvanceFinanciero, ConstantesBD.ColAvanceFinanciero[1] + " = " + idObra, null, null);
		while(cursorAvanceFinanciero.moveToNext()){
			AvanceFinanciero avanFinanciero = new AvanceFinanciero();
			avanFinanciero.setIdAvanceFinaciero(cursorAvanceFinanciero.getLong(0));
			avanFinanciero.setPavanceFinanciero(cursorAvanceFinanciero.getDouble(2));
			avanFinanciero.setPorcentajeTendencia(cursorAvanceFinanciero.getDouble(3));
			avanFinanciero.setFechaReporte(cursorAvanceFinanciero.getString(4));
			avanFinanciero.setEstado(cursorAvanceFinanciero.getInt(5));
			listaFinanciero.add(avanFinanciero);
		}
		cursorAvanceFinanciero.close();
		return listaFinanciero;
	}
	
}
