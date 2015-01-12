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
import com.csgit.cao.model.Mod_ConceptoPadre;
import com.csgit.cao.model.Mod_Historial_Avances;
import com.csgit.cao.model.communicationchannel.model.AditivasDeductivas;
import com.csgit.cao.model.communicationchannel.model.Concepto;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Uri;

public class Bus_Conceptos {
	
	private ContentResolver contentRes;
	
	public Bus_Conceptos(Context context){
		this.contentRes = context.getContentResolver();
	}

	public List<Mod_ConceptoPadre> getLevelConceptos(long idObra){
		List<Mod_ConceptoPadre> padresHijos  = new ArrayList<Mod_ConceptoPadre>();
		List<Concepto> allConceptos = getAllConceptosFromObra(idObra);
		
		for (Concepto concepto : allConceptos) {
			if(concepto.getPadre() == 0){
				Mod_ConceptoPadre padre = new Mod_ConceptoPadre();
				padre.setIdPadre(concepto.getIdConcepto());
				padre.setClave(concepto.getClave());
				padre.setNomConcepto(concepto.getDescripcion());
				
				List<Concepto> item = new ArrayList<Concepto>();
				for (Concepto concepto2 : allConceptos) {	
					if(padre.getIdPadre() == concepto2.getPadre()){
						item.add(concepto2);
					}
				}
				padre.setChildren(item);
				padresHijos.add(padre);
			}
		}	
		return padresHijos;
	}
	
	public Concepto getConcepto(Long idConcepto){
		Concepto concepto = new Concepto();
		
		Cursor cursorConcepto = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaConcepto), ConstantesBD.ColConceptos, 
				ConstantesBD.ColConceptos[0] + " = " + idConcepto, null, null);
		
		if(cursorConcepto.moveToFirst()){

			concepto.setIdConcepto(cursorConcepto.getLong(0));
			concepto.setIdObra(cursorConcepto.getLong(1));
			concepto.setClave(cursorConcepto.getString(2));
			concepto.setDescripcion(cursorConcepto.getString(3));
			concepto.setUnidadMedida(cursorConcepto.getString(4));
			concepto.setCantidadTotal(cursorConcepto.getDouble(5));
			concepto.setPrecioUnitario(cursorConcepto.getDouble(6));
			concepto.setImporte(cursorConcepto.getDouble(7));
			concepto.setCantidadAvance(cursorConcepto.getDouble(8));
			concepto.setFechaInicio(cursorConcepto.getString(9));
			concepto.setFechaFin(cursorConcepto.getString(10));
			concepto.setPadre(cursorConcepto.getLong(11));
			if(cursorConcepto.getInt(13) == Utl_Constantes.REG_VISIBLE_INT)
				concepto.setVisible(Utl_Constantes.REG_VISIBLE_BOOL);
			else
				concepto.setVisible(Utl_Constantes.REG_NO_VISIBLE_BOOL);
		}
		cursorConcepto.close();
		return concepto;
	}

	public List<Concepto> getAllConceptosFromObra(Long idObra){
		List<Concepto> lista_conceptos = new ArrayList<Concepto>();
		
		Cursor cursorConcepto = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaConcepto),
				ConstantesBD.ColConceptos, ConstantesBD.ColConceptos[1] + " = " + idObra + 
				" and " + ConstantesBD.ColConceptos[13] + " = " + Utl_Constantes.REG_VISIBLE_INT, null, null);
		while(cursorConcepto.moveToNext()){
			Concepto concepto = new Concepto();
			
			concepto.setIdConcepto(cursorConcepto.getLong(0));
			concepto.setIdObra(cursorConcepto.getLong(1));
			concepto.setClave(cursorConcepto.getString(2));
			concepto.setDescripcion(cursorConcepto.getString(3));
			concepto.setUnidadMedida(cursorConcepto.getString(4));
			concepto.setCantidadTotal(cursorConcepto.getDouble(5));
			concepto.setPrecioUnitario(cursorConcepto.getDouble(6));
			concepto.setImporte(cursorConcepto.getDouble(7));
			concepto.setCantidadAvance(cursorConcepto.getDouble(8));
			concepto.setFechaInicio(cursorConcepto.getString(9));
			concepto.setFechaFin(cursorConcepto.getString(10));
			concepto.setPadre(cursorConcepto.getLong(11));
			
			lista_conceptos.add(concepto);
		}
		
		cursorConcepto.close();
		return lista_conceptos;
	}
	
	public List<Concepto> getAllConceptos(){
		List<Concepto> lista_conceptos = new ArrayList<Concepto>();
		
		Cursor cursorConcepto = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaConcepto),
				ConstantesBD.ColConceptos, null, null, null);
		while(cursorConcepto.moveToNext()){
			Concepto concepto = new Concepto();
			
			concepto.setIdConcepto(cursorConcepto.getLong(0));
			concepto.setIdObra(cursorConcepto.getLong(1));
			concepto.setClave(cursorConcepto.getString(2));
			concepto.setDescripcion(cursorConcepto.getString(3));
			concepto.setUnidadMedida(cursorConcepto.getString(4));
			concepto.setCantidadTotal(cursorConcepto.getDouble(5));
			concepto.setPrecioUnitario(cursorConcepto.getDouble(6));
			concepto.setImporte(cursorConcepto.getDouble(7));
			concepto.setCantidadAvance(cursorConcepto.getDouble(8));
			concepto.setFechaInicio(cursorConcepto.getString(9));
			concepto.setFechaFin(cursorConcepto.getString(10));
			concepto.setPadre(cursorConcepto.getLong(11));
			
			lista_conceptos.add(concepto);
		}
		
		return lista_conceptos;
	}

	public void insertAditivaDeductiva(AditivasDeductivas operacion){
		
		ContentValues values = new ContentValues();
		values.put(ConstantesBD.colAditivaDeductiva[1], operacion.getIdConcepto());
		values.put(ConstantesBD.colAditivaDeductiva[2], operacion.getTipoOperacion());
		values.put(ConstantesBD.colAditivaDeductiva[3], operacion.getCantidad());
		values.put(ConstantesBD.colAditivaDeductiva[4], operacion.getFecha());
		
		Uri uri = contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaAditivaDeductiva), values);
		Log.i("uri opereacion", uri.toString());
		
		// temporal
		long idSync = Utl_Uri.getIdInsert(uri);
		ContentValues valTemp = new ContentValues();
		valTemp.put(ConstantesBD.colSync[1], Utl_Constantes.TABLA_ADITIVAS_DEDUCTIVAS);
		valTemp.put(ConstantesBD.colSync[2], idSync);
		valTemp.put(ConstantesBD.colSync[3], Utl_Constantes.ACC_INSERT);
		valTemp.put(ConstantesBD.colSync[4], Utl_Constantes.REG_NO_SINC);
		contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaSync), valTemp);
		
	}
	
	public AditivasDeductivas getAditivaDeuctiva(long idAditivaDeductiva){
		AditivasDeductivas operacion  = new AditivasDeductivas();
		
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaAditivaDeductiva), ConstantesBD.colAditivaDeductiva,
				ConstantesBD.colAditivaDeductiva[0] + " = " + idAditivaDeductiva, null, null);
		if(cursor.moveToFirst()){
			operacion.setIdAditivasDeductivas(cursor.getLong(0));
			operacion.setIdConcepto(cursor.getLong(1));
			operacion.setTipoOperacion(cursor.getInt(2));
			operacion.setCantidad(cursor.getDouble(3));
			operacion.setFecha(cursor.getString(4));
		}
		cursor.close();
		
		return operacion;
	}
	
	public boolean validarOperacion(int tipoOperacion, double cantidadTotal, double cantidadOperacion){
		
		switch (tipoOperacion) {
		case Utl_Constantes.OPERACION_ADITIVA:
			return true;

		case Utl_Constantes.OPERACION_DEDUCTIVA:
			if(cantidadOperacion <= cantidadTotal)
				return true;
			
		default:
			break;
		}
		
		return false;
	}
	
	public double realizaOperacion(double cantidadTotal, double cantidadOperacion, int tipo){
		double result = 0D;
		switch (tipo) {
		case Utl_Constantes.OPERACION_ADITIVA:
			result = cantidadTotal + cantidadOperacion;
			break;
			
		case Utl_Constantes.OPERACION_DEDUCTIVA:
			result = cantidadTotal - cantidadOperacion;
			break;

		default:
			break;
		}
		return result;
	}
	
	public int updateCantidadConcepto(long idConcepto, double cantidadOperacion, 
			int tipoOperacion, double cantidadTotal){
		
		int i = 0;
		double resultado = 0;
		
		switch (tipoOperacion) {
		case Utl_Constantes.OPERACION_ADITIVA:
			resultado = cantidadTotal + cantidadOperacion;
			break;

		case Utl_Constantes.OPERACION_DEDUCTIVA:
			resultado = cantidadTotal - cantidadOperacion; 
			break;
			
		default:
			break;
		}
		
		ContentValues values = new ContentValues();
		values.put(ConstantesBD.ColConceptos[5], resultado);
		
		i = contentRes.update(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaConcepto), values,
				ConstantesBD.ColConceptos[0] + " = " + idConcepto, null);
		
		return i;
	}

	public double getCantidadTotalActual(long idConcepto){
		double cantidad = 0D;
		
		String[] colum = {ConstantesBD.ColConceptos[5]}; 
		
		Cursor cursor = contentRes.query(
				Uri.parse(ConstantesBD.CAO_URL +  ConstantesBD.nomTablaConcepto),
				colum, ConstantesBD.ColConceptos[0] + " = " + idConcepto, null, null);
		if(cursor.moveToFirst()){
			cantidad = cursor.getDouble(0);
		}
		cursor.close();
		
		return cantidad;
	}
	
	public int getCantidadTotalOriginal(long idConcepto){
		int cantidad = 0;
		
		String[] colum = {ConstantesBD.ColConceptos[12]}; 
		
		Cursor cursor = contentRes.query(
				Uri.parse(ConstantesBD.CAO_URL +  ConstantesBD.nomTablaConcepto),
				colum, ConstantesBD.ColConceptos[0] + " = " + idConcepto, null, null);
		if(cursor.moveToFirst()){
			cantidad = cursor.getInt(0); 
		}
		cursor.close();
		
		return cantidad;
	}

	public List<AditivasDeductivas> getOperacionesFromConcepto(long idConcepto){
		List<AditivasDeductivas> lista = new ArrayList<AditivasDeductivas>();
		
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaAditivaDeductiva), ConstantesBD.colAditivaDeductiva,
				ConstantesBD.colAditivaDeductiva[1] + " = " + idConcepto, null, null);
		while(cursor.moveToNext()){
			AditivasDeductivas operacion = new AditivasDeductivas();
			operacion.setIdAditivasDeductivas(cursor.getLong(0));
			operacion.setIdConcepto(cursor.getLong(1));
			operacion.setTipoOperacion(cursor.getInt(2));
			operacion.setCantidad(cursor.getDouble(3));
			operacion.setFecha(cursor.getString(4));
			
			lista.add(operacion);
		}
		cursor.close();
		
		return lista;
	}
	
	public List<Mod_Historial_Avances> getHistorialAvancesFromConcepto(long idConcepto){
		List<Mod_Historial_Avances> lista = new ArrayList<Mod_Historial_Avances>();
		
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaAvanceConcepto), ConstantesBD.ColAvanceConcepto,
				ConstantesBD.ColAvanceConcepto[2] + " = " + idConcepto, null, null);
		while(cursor.moveToNext()){
			Mod_Historial_Avances operacion = new Mod_Historial_Avances();
			operacion.setIdAvance(cursor.getLong(0));
			operacion.setIdReporteDiario(cursor.getLong(1));
			operacion.setIdConcepto(cursor.getLong(2));
			operacion.setCantidadAvance(cursor.getDouble(3));
			operacion.setFecha(cursor.getString(4));
			operacion.setUnidadMedida(cursor.getString(5));
			lista.add(operacion);
		}
		cursor.close();
		
		return lista;
	}
}
