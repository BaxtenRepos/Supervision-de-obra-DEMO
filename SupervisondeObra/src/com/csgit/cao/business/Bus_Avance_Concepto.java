package com.csgit.cao.business;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.csgit.cao.dao.ConstantesBD;
import com.csgit.cao.model.Mod_Multimedia;
import com.csgit.cao.model.communicationchannel.model.Concepto;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Fechas;
import com.csgit.cao.utils.Utl_Operaciones;

public class Bus_Avance_Concepto {
	
	private ContentResolver contentRes;
	private Bus_Sync bus_sync;
	private Bus_ReporteDiario bus_reporteDiario;
	
	public Bus_Avance_Concepto(Context context){
		this.contentRes = context.getContentResolver();
		bus_sync = new Bus_Sync(context);
		bus_reporteDiario = new Bus_ReporteDiario(context);
	}
	
	public boolean validaCantidadIngresada(String cantidad){
		if(cantidad.trim().equals("") || cantidad.trim().equals(".") || cantidad.trim().equals("0"))
			return true;	
		return false;
	}
	
	public boolean updateAvanceConcepto(Long idObra, Long idConcepto, double cantidadAvance, 
			double precioUnitario, double cantidadTotal, double cantidadAcumuladaActual, String unidadMedida){
		
		double cantidadAcumuladaTotal = cantidadAcumuladaActual + cantidadAvance;
		if(cantidadAcumuladaTotal <= cantidadTotal){
			
			double importe = cantidadAcumuladaTotal * precioUnitario;
			double importeRedondeado = Utl_Operaciones.redondearValor(importe, 2);
			
			ContentValues values =  new ContentValues();
			values.put(ConstantesBD.ColConceptos[7], importeRedondeado);
			values.put(ConstantesBD.ColConceptos[8], cantidadAcumuladaTotal);
	
			int num = contentRes.update(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaConcepto),
					values, ConstantesBD.ColConceptos[0] + " = " + idConcepto 
					+ " and " + ConstantesBD.ColConceptos[1] + " = " + idObra, null);
			if(num != 0){
				Long idReporteDiario = bus_reporteDiario.verificarIdReporteDiario(idObra);
				if(idReporteDiario == null){
					idReporteDiario = bus_reporteDiario.genearNuevoReporteDiario(idObra);
				}
				
				ContentValues valuesAvance = new ContentValues();
				valuesAvance.put(ConstantesBD.ColAvanceConcepto[1], idReporteDiario);
				valuesAvance.put(ConstantesBD.ColAvanceConcepto[2], idConcepto);
				valuesAvance.put(ConstantesBD.ColAvanceConcepto[3], cantidadAvance);
				valuesAvance.put(ConstantesBD.ColAvanceConcepto[4], Utl_Fechas.getFecha());
				valuesAvance.put(ConstantesBD.ColAvanceConcepto[5], unidadMedida);
				contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaAvanceConcepto), valuesAvance);
			}
			
			// temporal
			if(!bus_sync.isRegExistForSync(ConstantesBD.nomTablaConcepto, idConcepto)){
				ContentValues valTemp = new ContentValues();
				valTemp.put(ConstantesBD.colSync[1], Utl_Constantes.TABLA_CONCEPTO);
				valTemp.put(ConstantesBD.colSync[2], idConcepto);
				valTemp.put(ConstantesBD.colSync[3], Utl_Constantes.ACC_UPDATE);
				valTemp.put(ConstantesBD.colSync[4], Utl_Constantes.REG_NO_SINC);
				contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), valTemp);	
			}
			
			return true;
		}else{
			return false;
		}
	}
	
	public void inserMultimedia(List<Mod_Multimedia> datos){
		ContentValues values =  new ContentValues();
		for (int i = 0; i < datos.size(); i++) {
			values.put(ConstantesBD.ColMultimedia[1], datos.get(i).getIdReporteDiario());
			values.put(ConstantesBD.ColMultimedia[2], datos.get(i).getFormato());
			values.put(ConstantesBD.ColMultimedia[3], datos.get(i).getPath());
			values.put(ConstantesBD.ColMultimedia[4], datos.get(i).getDescripcion());
			Uri uri = contentRes.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMultimedia), values);
			Log.i("Insert Multimedia", uri.toString());
		}
				
	}
	
	public double getCantidadProgramada(Concepto concepto){
		double cantidadProgramada = 0;
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al dia
		
//		String Inicio = "09/09/2014 12:13:12";
//		String Fin = "15/09/2014 12:13:12";
		
		if(Utl_Fechas.isFechaValida(concepto.getFechaInicio()) && 
				Utl_Fechas.isFechaValida(concepto.getFechaFin())){
			
			String Inicio = concepto.getFechaInicio();
			String Fin = concepto.getFechaFin();
			
			String[] aux1 = Inicio.split(Utl_Constantes.CARAC_SEPARA_FECHA_COMPLETA);
			String[] aux2 = aux1[0].split(Utl_Constantes.CARAC_SEPARA_FECHA);
			
			String[] aux3 = Fin.split(Utl_Constantes.CARAC_SEPARA_FECHA_COMPLETA);
			String[] aux4 = aux3[0].split(Utl_Constantes.CARAC_SEPARA_FECHA);
			
			int dia1 = Integer.parseInt(aux2[0]);
			int mes1 = Integer.parseInt(aux2[1]);
			int ano1 = Integer.parseInt(aux2[2]);
			int dia2 = Integer.parseInt(aux4[0]);
			int mes2 = Integer.parseInt(aux4[1]);
			int ano2 = Integer.parseInt(aux4[2]);
			
			Calendar cal_inicio = new GregorianCalendar(ano1, mes1-1, dia1);
			Calendar cal_fin = new GregorianCalendar(ano2, mes2-1, dia2);
			java.sql.Date Finicio = new java.sql.Date(cal_inicio.getTimeInMillis());
			java.sql.Date Ffin =  new java.sql.Date(cal_fin.getTimeInMillis());
			
			long diferenciaTotal = (Ffin.getTime() - Finicio.getTime()) / MILLSECS_PER_DAY;
			Date hoy = new Date();
			
			if(diferenciaTotal != 0){
				long difActual = (hoy.getTime() - Finicio.getTime()) / MILLSECS_PER_DAY;
				double cantidadDiara = concepto.getCantidadTotal() / diferenciaTotal;
				
				if(difActual != 0)
					cantidadProgramada = cantidadDiara * difActual;
				else
					cantidadProgramada = cantidadProgramada * 1;
			}else{
				cantidadProgramada = concepto.getCantidadTotal();
			}
		}
		
		return cantidadProgramada;
	}
	
	public Concepto getConcepto(Long idConcepto){
		Concepto concepto = new Concepto();
		
		Cursor cursorConcepto = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaConcepto), 
				ConstantesBD.ColConceptos, ConstantesBD.ColConceptos[0]+ " = " + idConcepto, null, null);
		if(cursorConcepto.moveToFirst()) {
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
	    }
		cursorConcepto.close();
		
		return concepto;

	}
	
	
}