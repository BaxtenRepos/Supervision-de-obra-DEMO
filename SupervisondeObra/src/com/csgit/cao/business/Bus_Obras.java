package com.csgit.cao.business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.csgit.cao.dao.ConstantesBD;
import com.csgit.cao.model.Mod_Directorio;
import com.csgit.cao.model.Mod_Minutas;
import com.csgit.cao.model.Mod_Multimedia;
import com.csgit.cao.model.Mod_Obras;
import com.csgit.cao.model.communicationchannel.model.AvanceFinanciero;
import com.csgit.cao.model.communicationchannel.model.AvanceFisico;
import com.csgit.cao.model.communicationchannel.model.Empresa;
import com.csgit.cao.model.communicationchannel.model.Obra;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Fechas;
import com.csgit.cao.utils.Utl_Uri;

public class Bus_Obras {

	private ContentResolver contentRes;
	Bus_Sync bus_sync;
	
	public Bus_Obras(Context context){
		this.contentRes = context.getContentResolver();
		bus_sync =  new Bus_Sync(context);
	}

	public boolean masMinutas(long idObra){
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMinuta),
				ConstantesBD.ColMinutas, ConstantesBD.ColMinutas[1] + " = " + idObra, null, null);
		String[] aux = Utl_Fechas.getFecha().split(" ");
		String actual = aux[0];
		int count = 0;
		while(cursor.moveToNext()){
			String[] aux1 = cursor.getString(4).toString().split(" ");
			String fecha = aux1[0];
			if(actual.trim().equals(fecha.trim()))
				count++;
		}
		cursor.close();
		
		if(count < Utl_Constantes.NUMERO_MINUTAS_DIA)
			return true;
		else
			return false;
	}
	
	public List<Mod_Directorio> getDirectorioFromObra(long idObra){
		List<Mod_Directorio> personas = new ArrayList<Mod_Directorio>();
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaDirectorio),
				ConstantesBD.ColDirectorio, ConstantesBD.ColDirectorio[1] + " = " + idObra + 
				" and " + ConstantesBD.ColDirectorio[17] + " = " + Utl_Constantes.REG_VISIBLE_INT, null, null);
		while(cursor.moveToNext()){
			Mod_Directorio directorio = new Mod_Directorio();
			directorio.setIdDirectorio(cursor.getLong(0));
			directorio.setIdObra(cursor.getLong(1));
			directorio.setRfcEmpresa(cursor.getString(2));
			directorio.setTipoEmpresa(cursor.getLong(3));
			directorio.setNombreEmpresa(cursor.getString(4));
			directorio.setNombre(cursor.getString(5));
			directorio.setApPaterno(cursor.getString(6));
			directorio.setApMaterno(cursor.getString(7));
			directorio.setRfcPersonal(cursor.getString(8));
			directorio.setCargo(cursor.getString(9));
			directorio.setTituloProfesional(cursor.getString(10));
			directorio.setCedulaProfesional(cursor.getString(11));
			directorio.setFotografia(cursor.getString(12));
			directorio.setSkype(cursor.getString(13));
			directorio.setEmail(cursor.getString(14));
			directorio.setTelefonos(cursor.getString(15));
			directorio.setRadios(cursor.getString(16));
			
			personas.add(directorio);
		}
		cursor.close();
		return personas;
	}
	
//	//Temporal
//	public List<Mod_Multimedia> getMultimediaFromReporte(Long idReporte){
//		List<Mod_Multimedia> lista = new ArrayList<Mod_Multimedia>();
//		
//		long idRep = bus_reporteDiario.getIdReporteDiarioMultimedia(idReporte);
//		
//		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMultimedia),
//				ConstantesBD.ColMultimedia, ConstantesBD.ColMultimedia[1] + " = " + idRep, null, null);
//		while(cursor.moveToNext()){
//			
//       	 File fichero = new File(cursor.getString(3));
//       	 if(fichero.exists()){
//       		 Mod_Multimedia multimedia = new Mod_Multimedia();
//			
//			multimedia.setIdMultimedia(cursor.getLong(0));
//			multimedia.setIdReporteDiario(cursor.getLong(1));
//			multimedia.setFormato(cursor.getString(2));
//			multimedia.setPath(cursor.getString(3));
//			multimedia.setDescripcion(cursor.getString(4));
//			multimedia.setTipo(cursor.getInt(5));
//			
//			lista.add(multimedia); 
//       	 }
//		}
//		cursor.close();
//		
//		return lista;
//	}
	
	//Temporal
	public List<Mod_Multimedia> getMultimediaFromObra(Long idObra){
		List<Mod_Multimedia> lista = new ArrayList<Mod_Multimedia>();

		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMultimedia),
				ConstantesBD.ColMultimedia, ConstantesBD.ColMultimedia[9] + " = " + idObra, null, null);
		while(cursor.moveToNext()){

			File fichero = new File(cursor.getString(3));
			if(fichero.exists()){
				Mod_Multimedia multimedia = new Mod_Multimedia();

				multimedia.setIdMultimedia(cursor.getLong(0));
				multimedia.setIdReporteDiario(cursor.getLong(1));
				multimedia.setFormato(cursor.getString(2));
				multimedia.setPath(cursor.getString(3));
				multimedia.setDescripcion(cursor.getString(4));
				multimedia.setTipo(cursor.getInt(5));
				multimedia.setIdObra(cursor.getLong(9));

				lista.add(multimedia); 
			}
		}
		cursor.close();

		return lista;
	}
	
	public int updateMultimedia(Long idMultimedia, ContentValues values){
		int num = contentRes.update(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMultimedia),
				values, ConstantesBD.ColMultimedia[0] + " = " + idMultimedia, null);
		return num;
	}
	
	public int updateMinuta(Long idMinuta, ContentValues values){
		int num = contentRes.update(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMinuta),
				values, ConstantesBD.ColMinutas[0] + " = " + idMinuta, null);
		return num;
	}

	public List<Mod_Multimedia> getAllMultimediaNoSinc(){
		List<Mod_Multimedia> lista = new ArrayList<Mod_Multimedia>();
		String[] aux = {"IdObra"};
		
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMultimedia),
				ConstantesBD.ColMultimedia, ConstantesBD.ColMultimedia[8] + " = " + Utl_Constantes.REG_NO_SINC 
				+ " and " + ConstantesBD.ColMultimedia[5] + " = " + Utl_Constantes.TIPO_IMAGEN, null, null);
		while(cursor.moveToNext()){
			Mod_Multimedia multimedia = new Mod_Multimedia();
			
			multimedia.setIdMultimedia(cursor.getLong(0));
			multimedia.setIdReporteDiario(cursor.getLong(1));
			multimedia.setFormato(cursor.getString(2));
			multimedia.setPath(cursor.getString(3));
			multimedia.setDescripcion(cursor.getString(4));
			multimedia.setTipo(cursor.getInt(5));
			multimedia.setUrlServer(cursor.getString(6));
			multimedia.setBlobKey(cursor.getString(7));
			multimedia.setSync(cursor.getInt(8));
			
			
			Cursor curObra = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaReporteDiario),
					aux, ConstantesBD.ColReporteDiario[1] + " = " + multimedia.getIdReporteDiario(), null, null);
			if(curObra.moveToFirst())
				multimedia.setIdObra(curObra.getLong(0));
			
			curObra.close();
			
			lista.add(multimedia);
		}
		cursor.close();
		
		return lista;
	}
	
	public List<Mod_Multimedia> getAllMultimedia(){
		List<Mod_Multimedia> lista = new ArrayList<Mod_Multimedia>();
		
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMultimedia),
				ConstantesBD.ColMultimedia, null, null, null);
		while(cursor.moveToNext()){
			Mod_Multimedia multimedia = new Mod_Multimedia();
			
			multimedia.setIdMultimedia(cursor.getLong(0));
			multimedia.setIdReporteDiario(cursor.getLong(1));
			multimedia.setFormato(cursor.getString(2));
			multimedia.setPath(cursor.getString(3));
			multimedia.setDescripcion(cursor.getString(4));
			multimedia.setTipo(cursor.getInt(5));
			multimedia.setUrlServer(cursor.getString(6));
			multimedia.setBlobKey(cursor.getString(7));
			
			lista.add(multimedia);
		}
		cursor.close();
		
		return lista;
	}
	
	public Mod_Multimedia getEvidenciaSync(long idEvidencia){
		Mod_Multimedia multimedia = new Mod_Multimedia();
		
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMultimedia),
				ConstantesBD.ColMultimedia, ConstantesBD.ColMultimedia[0] + " = " + idEvidencia, null, null);
		if(cursor.moveToFirst()){
			multimedia.setIdMultimedia(cursor.getLong(0));
			multimedia.setIdReporteDiario(cursor.getLong(1));
			multimedia.setFormato(cursor.getString(2));
			multimedia.setPath(cursor.getString(3));
			multimedia.setDescripcion(cursor.getString(4));
			multimedia.setTipo(cursor.getInt(5));
			multimedia.setUrlServer(cursor.getString(6));
			multimedia.setBlobKey(cursor.getString(7));
			
			
			Cursor cur_obra = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaReporteDiario),
					ConstantesBD.ColReporteDiario, ConstantesBD.ColReporteDiario[0] + " = " + 
					multimedia.getIdReporteDiario(), null, null);
			if(cur_obra.moveToNext())
				multimedia.setIdObra(cur_obra.getLong(1));
			cur_obra.close();
			
		}
		cursor.close();
		
		return multimedia;
	}
	
	public List<Mod_Obras> getObras(){
		List<Mod_Obras> lista = new ArrayList<Mod_Obras>();
		
		Cursor cursorObras = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaObra),
				ConstantesBD.ColObras, 
				ConstantesBD.ColObras[43] + " = " + Utl_Constantes.REG_VISIBLE_INT, null, null);

		while(cursorObras.moveToNext()){
			Mod_Obras obra = new Mod_Obras();

			obra.setIdObra(cursorObras.getLong(0));
			obra.setNombre(cursorObras.getString(4));
			obra.setEmpresaContratista(cursorObras.getString(38));
			obra.setEmpresaSupervisora(cursorObras.getString(39));
			obra.setLimiteDesvio(cursorObras.getString(41));
			obra.setNumeroContrato(cursorObras.getString(2));
			obra.setEntidadFederativa(cursorObras.getString(37));

			Cursor cursorAvanceFinanciero = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaAvanceFinanciero), 
					ConstantesBD.ColAvanceFinanciero, ConstantesBD.ColAvanceFinanciero[1] + " = " + cursorObras.getLong(0), null, null);
			if(cursorAvanceFinanciero.moveToLast()){
				obra.setPorcentajeFinanciero(cursorAvanceFinanciero.getString(2));
				obra.setEstadoFinanciero(cursorAvanceFinanciero.getInt(5));
			}else {
				obra.setPorcentajeFinanciero("0");
			}
			switch (obra.getEstadoFinanciero()) {
			case Utl_Constantes.ESTADO_ADELANTADO:
				obra.setEstadoFinancieroString(Utl_Constantes.ESTADO_ADELANTADO_STRING);
				break;
			case Utl_Constantes.ESTADO_ATRASADO:
				obra.setEstadoFinancieroString(Utl_Constantes.ESTADO_ATRASADO_STRING);
				break;
			case Utl_Constantes.ESTADO_CONFORME_PROGRAMA:
				obra.setEstadoFinancieroString(Utl_Constantes.ESTADO_CONFORME_PROGRAMA_STRING);
				break;

			default:
				break;
			}
			cursorAvanceFinanciero.close();

			Cursor cursorAvanceFisico = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaAvanceFisico), 
					ConstantesBD.ColAvanceFisico, ConstantesBD.ColAvanceFisico[1] + " = " + cursorObras.getLong(0) , null, null);
			if(cursorAvanceFisico.moveToLast()){
				obra.setPorcentajeFisico(cursorAvanceFisico.getString(2));
				obra.setEstadoFisico(cursorAvanceFisico.getInt(5));
			}else {
				obra.setPorcentajeFisico("0");
			}
			switch (obra.getEstadoFisico()) {
			case Utl_Constantes.ESTADO_ADELANTADO:
				obra.setEstadoFisicoString(Utl_Constantes.ESTADO_ADELANTADO_STRING);
				break;
			case Utl_Constantes.ESTADO_ATRASADO:
				obra.setEstadoFisicoString(Utl_Constantes.ESTADO_ATRASADO_STRING);
				break;
			case Utl_Constantes.ESTADO_CONFORME_PROGRAMA:
				obra.setEstadoFisicoString(Utl_Constantes.ESTADO_CONFORME_PROGRAMA_STRING);
				break;

			default:
				break;
			}
			cursorAvanceFisico.close();

			lista.add(obra);
		}
		cursorObras.close();

		return lista;
	}
	
	public Obra getInfomacionObras(Long idObra){

		Cursor cursorObras = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaObra), ConstantesBD.ColObras, 
				ConstantesBD.ColObras[0] + " = " + idObra, null, null);
		Obra obra = new Obra();

		if(cursorObras.moveToFirst()){
			obra.setIdObra(cursorObras.getLong(0));
			obra.setNoContrato(cursorObras.getString(2));
			obra.setRfc(cursorObras.getString(3));
			obra.setNombre(cursorObras.getString(4));
			obra.setIdGobierno(cursorObras.getLong(5));
			obra.setIdSecretaria(cursorObras.getLong(6));
			obra.setIdDependencia(cursorObras.getLong(7));
			obra.setDireccion(cursorObras.getString(8));
			obra.setSubdireccion(cursorObras.getString(9));
			obra.setArea(cursorObras.getString(10));
			obra.setDescripcion(cursorObras.getString(11));
			obra.setFechaContrato(cursorObras.getString(12));
			obra.setTipoContrato(cursorObras.getString(13));
			obra.setImporteContratoSinIVA(cursorObras.getDouble(14));
			obra.setNombreEjercicioFiscal1(cursorObras.getString(15));
			obra.setImporteFiscal1SinIVA(cursorObras.getDouble(16));
			obra.setImporteConvenioAmpliacion(cursorObras.getDouble(17));
			obra.setImporteConvenioReduccion(cursorObras.getDouble(18));
			obra.setImporteAjusteCostos(cursorObras.getDouble(19));
			obra.setFechaInicioContrato(cursorObras.getString(20));
			obra.setFechaTerminoContrato(cursorObras.getString(21));
			obra.setPartidaPresupuestal(cursorObras.getString(22));
			obra.setAnticipo(cursorObras.getString(23));
			obra.setNoFianzaAnticipo(cursorObras.getString(24));
			obra.setFechaFianzaAnticipo(cursorObras.getString(25));
			obra.setMontoFianzaAnticipo(cursorObras.getDouble(26));
			obra.setNoFianzaCumplimiento(cursorObras.getString(27));
			obra.setFechaFianzaCumplimiento(cursorObras.getString(28));
			obra.setMontoFianzaCumplimiento(cursorObras.getDouble(29));
			obra.setCargoRevision1(cursorObras.getString(30));
			obra.setNombreRevision1(cursorObras.getString(31));
			obra.setCargoRevision2(cursorObras.getString(32));
			obra.setNombreRevision2(cursorObras.getString(33));
			obra.setNombreQuienAutoriza(cursorObras.getString(34));
			obra.setCargoVoBo(cursorObras.getString(35));
			obra.setNombreVoBo(cursorObras.getString(36));
			obra.setEntidadFederativa(cursorObras.getString(37));
			obra.setIdEmpresaContratista(cursorObras.getLong(38));
			obra.setSuperintendente(cursorObras.getString(39));
			obra.setBorrador(cursorObras.getString(40));		
		}
		cursorObras.close();
		return obra;
	}
	
	public Empresa getEmpresa(long idEmpresa){
		Empresa empresa = new Empresa();
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEmpresas),
				ConstantesBD.colEmpresas, ConstantesBD.colEmpresas[0] + " = " + idEmpresa, null, null);
		if(cursor.moveToFirst()){
			empresa.setIdEmpresa(cursor.getLong(0));
			empresa.setIdTipoEmpresa(cursor.getLong(1));
			empresa.setNombre(cursor.getString(2));
			empresa.setCalle(cursor.getString(3));
			empresa.setCodiPostal(cursor.getInt(4));
			empresa.setColonia(cursor.getString(5));
			empresa.setDelOMun(cursor.getString(6));
			empresa.setEntidad(cursor.getString(7));
			empresa.setImss(cursor.getString(8));
			empresa.setNumExt(cursor.getString(9));
			empresa.setNumInt(cursor.getString(10));
			empresa.setRfc(cursor.getString(11));
			empresa.setSiglas(cursor.getString(12));
		}
		cursor.close();
		
		return empresa;
	}
	
	public List<String> getEmpresasObra(long idGobierno, long idSecretaria, long idDependencia){
		List<String> lista = new ArrayList<String>();
		
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEmpresas),
				ConstantesBD.colEmpresas, ConstantesBD.colEmpresas[1] + " = " + Utl_Constantes.idGobierno + " and " + 
						ConstantesBD.colEmpresas[0] + " = " + idGobierno, null, null);
		if(cursor.moveToFirst())
			lista.add(cursor.getString(2));
		else
			lista.add("---");
		cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEmpresas),
				ConstantesBD.colEmpresas, ConstantesBD.colEmpresas[1] + " = " + Utl_Constantes.idSecretariaa + " and " + 
						ConstantesBD.colEmpresas[0] + " = " + idSecretaria, null, null);
		if(cursor.moveToFirst())
			lista.add(cursor.getString(2));
		else
			lista.add("---");
		cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEmpresas),
				ConstantesBD.colEmpresas, ConstantesBD.colEmpresas[1] + " = " + Utl_Constantes.idDependencia + " and " + 
						ConstantesBD.colEmpresas[0] + " = " + idDependencia, null, null);
		if(cursor.moveToFirst())
			lista.add(cursor.getString(2));
		else
			lista.add("---");
		
		cursor.close();
		
		return lista;
	}
	
	public String getEmpresaContratistaFromObra(long idEmpresaContratista){
		String nombreContratista;
		
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEmpresas),
				ConstantesBD.colEmpresas, ConstantesBD.colEmpresas[1] + " = " + Utl_Constantes.idContratista + " and " + 
						ConstantesBD.colEmpresas[0] + " = " + idEmpresaContratista, null, null);
		if(cursor.moveToFirst())
			nombreContratista = cursor.getString(2);
		else
			nombreContratista = "---";
		
		cursor.close();
		
		return nombreContratista;
	}
	
	public String getEmpresaSupervisoraFromObra(long idEmpresaSupervisora){
		String nombreSupervisora;
		
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEmpresas),
				ConstantesBD.colEmpresas, ConstantesBD.colEmpresas[1] + " = " + Utl_Constantes.idSupervisora + " and " + 
						ConstantesBD.colEmpresas[0] + " = " + idEmpresaSupervisora, null, null);
		if(cursor.moveToFirst())
			nombreSupervisora = cursor.getString(2);
		else
			nombreSupervisora = "---";
		
		cursor.close();
		
		return nombreSupervisora;
	}
	
	public AvanceFinanciero getInfomacionAvanceFinanciero(Long idObra){

		Cursor cursorAvanceFinanciero = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaAvanceFinanciero), 
				ConstantesBD.ColAvanceFinanciero, ConstantesBD.ColAvanceFinanciero[1] + " = " + idObra, null, null);
		AvanceFinanciero avanFinanciero = new AvanceFinanciero();

		if(cursorAvanceFinanciero.moveToLast()){
			avanFinanciero.setIdAvanceFinaciero(cursorAvanceFinanciero.getLong(0));
			avanFinanciero.setPavanceFinanciero(cursorAvanceFinanciero.getDouble(2));
			avanFinanciero.setPorcentajeTendencia(cursorAvanceFinanciero.getDouble(3));
			avanFinanciero.setEstado(cursorAvanceFinanciero.getInt(5));
		}
		cursorAvanceFinanciero.close();
		return avanFinanciero;		
	}

	public AvanceFisico getInfomacionAvanceFisico(Long idObra){

		Cursor cursorAvanceFisico = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaAvanceFisico), ConstantesBD.ColAvanceFisico, 
				ConstantesBD.ColAvanceFisico[1] + " = " + idObra, null, null);
		AvanceFisico avanFisico = new AvanceFisico();

		if(cursorAvanceFisico.moveToLast()){
			avanFisico.setIdAvanceFisico(cursorAvanceFisico.getLong(0));
			avanFisico.setPavanceFisico(cursorAvanceFisico.getDouble(2));
			avanFisico.setPorcentajeTendencia(cursorAvanceFisico.getDouble(3));
			avanFisico.setEstado(cursorAvanceFisico.getInt(5));
		}
		cursorAvanceFisico.close();
		return avanFisico;		
	}
	
//	public List<Mod_Minutas> getMinutasFromReporte(Long idObra){
//		List<Mod_Minutas> lista = new ArrayList<Mod_Minutas>();
//		
//		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMinuta),
//				ConstantesBD.ColMinutas, ConstantesBD.ColMinutas[1] + " = " + idObra, null, null);
//		while(cursor.moveToNext()){
//			
//			File fichero = new File(cursor.getString(3));
//	       	 if(fichero.exists()){
//	       		Mod_Minutas minutas = new Mod_Minutas();
//				
//				minutas.setIdMinutas(cursor.getLong(0));
//				minutas.setIdObra(cursor.getLong(1));
//				minutas.setFormato(cursor.getString(2));
//				minutas.setPathMinutas(cursor.getString(3));
//				minutas.setFechaMinutas(cursor.getString(4));
//				minutas.setTipo(cursor.getInt(5));
//				minutas.setUrlServer(cursor.getString(6));
//				minutas.setBlobKey(cursor.getString(7));
//				minutas.setIsSync(cursor.getInt(8));
//				
//				lista.add(minutas); 
//	       	 }
//		}
//		cursor.close();
//		
//		return lista;
//	}
	
	public List<Mod_Minutas> getMinutasFromObra(Long idObra){
		List<Mod_Minutas> lista = new ArrayList<Mod_Minutas>();
		
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMinuta),
				ConstantesBD.ColMinutas, ConstantesBD.ColMinutas[1] + " = " + idObra, null, null);
		while(cursor.moveToNext()){
			
			File fichero = new File(cursor.getString(3));
	       	 if(fichero.exists()){
	       		Mod_Minutas minutas = new Mod_Minutas();
				
				minutas.setIdMinutas(cursor.getLong(0));
				minutas.setIdObra(cursor.getLong(1));
				minutas.setFormato(cursor.getString(2));
				minutas.setPathMinutas(cursor.getString(3));
				minutas.setFechaMinutas(cursor.getString(4));
				minutas.setTipo(cursor.getInt(5));
				minutas.setUrlServer(cursor.getString(6));
				minutas.setBlobKey(cursor.getString(7));
				minutas.setIsSync(cursor.getInt(8));
				
				lista.add(minutas); 
	       	 }
		}
		cursor.close();
		
		return lista;
	}
	
	public Mod_Minutas getMinuta(Long idMinuta){
		
		Mod_Minutas minutas = new Mod_Minutas();
		
		Cursor cursor = contentRes.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMinuta),
				ConstantesBD.ColMinutas, ConstantesBD.ColMinutas[0] + " = " + idMinuta, null, null);
		if(cursor.moveToFirst()){
			File fichero = new File(cursor.getString(3));
	       	 if(fichero.exists()){
				minutas.setIdMinutas(cursor.getLong(0));
				minutas.setIdObra(cursor.getLong(1));
				minutas.setFormato(cursor.getString(2));
				minutas.setPathMinutas(cursor.getString(3));
				minutas.setFechaMinutas(cursor.getString(4));
				minutas.setTipo(cursor.getInt(5));
				minutas.setUrlServer(cursor.getString(6));
				minutas.setBlobKey(cursor.getString(7));
				
	       	 }
		}
		cursor.close();
		
		return minutas;
	}

	public static boolean insertarMinutas(ContentResolver contentResolver,
			Mod_Minutas minuta) {
		Uri uri = null;
		try {
			ContentValues values = new ContentValues();
			values.put(ConstantesBD.ColMinutas[1], minuta.getIdObra());
			values.put(ConstantesBD.ColMinutas[2], minuta.getFormato());
			values.put(ConstantesBD.ColMinutas[3], minuta.getPathMinutas());
			values.put(ConstantesBD.ColMinutas[4], minuta.getFechaMinutas());
			values.put(ConstantesBD.ColMinutas[5], minuta.getTipo());
			values.put(ConstantesBD.ColMinutas[6], minuta.getUrlServer());
			values.put(ConstantesBD.ColMinutas[7], minuta.getBlobKey());
			values.put(ConstantesBD.ColMinutas[8], minuta.getIsSync());
			
			uri = contentResolver.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMinuta), values);
			Log.i("URI Minutas", uri.toString());
			
			// temporal		
			long idSync = Utl_Uri.getIdInsert(uri);
			ContentValues valTemp = new ContentValues();
			valTemp.put(ConstantesBD.colSync[1], Utl_Constantes.TABLA_MINUTAS);
			valTemp.put(ConstantesBD.colSync[2], idSync);
			valTemp.put(ConstantesBD.colSync[3], Utl_Constantes.ACC_INSERT);
			valTemp.put(ConstantesBD.colSync[4], Utl_Constantes.REG_NO_SINC);
			contentResolver.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), valTemp);
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Error", "Error al insertar Minutas");
			return false;
		}
	}
	
	public int deleteMinuta(long idMinuta, String path){
		int op = 0;
		int i = contentRes.delete(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaMinuta), 
				ConstantesBD.ColMinutas[0] + " = " + idMinuta, null);
		if(i != 0 ){
			op = bus_sync.deleteSync(Utl_Constantes.TABLA_MINUTAS, idMinuta);	
			
//			Borrar archivo
			File file = new File(path);
			file.delete();
		}
		
		return op;
	}
}
