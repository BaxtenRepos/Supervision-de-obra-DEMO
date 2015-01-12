package com.csgit.cao.business;

import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.csgit.cao.dao.ConstantesBD;
import com.csgit.cao.model.Mod_Directorio;
import com.csgit.cao.model.communicationchannel.model.AvanceFinanciero;
import com.csgit.cao.model.communicationchannel.model.AvanceFisico;
import com.csgit.cao.model.communicationchannel.model.CatPersonal;
import com.csgit.cao.model.communicationchannel.model.CatTipoMaquinaria;
import com.csgit.cao.model.communicationchannel.model.Concepto;
import com.csgit.cao.model.communicationchannel.model.Empresa;
import com.csgit.cao.model.communicationchannel.model.Maquinaria;
import com.csgit.cao.model.communicationchannel.model.Notas;
import com.csgit.cao.model.communicationchannel.model.Obra;
import com.csgit.cao.model.communicationchannel.model.ReporteDiario;
import com.csgit.cao.model.communicationchannel.model.Ubicaciones;
import com.csgit.cao.utils.Utl_Constantes;


public class Bus_First_Sync {
	
	private ContentResolver contentR;
	
	public Bus_First_Sync(Context context){
		this.contentR = context.getContentResolver();
	}

	@SuppressWarnings("unchecked")
	public boolean insertBD_EndPoint(List<?> datos, int tipo){

		if(datos == null){
			return true;
		}
		
		ContentValues values =  new ContentValues();
		
		try {
			switch (tipo) {

			case Utl_Constantes.TABLA_OBRA:

				List<Obra> lista_obras = (List<Obra>) datos;
				for (Obra obra : lista_obras) {
					
					values.put(ConstantesBD.ColObras[0], obra.getIdObra());
					values.put(ConstantesBD.ColObras[1], obra.getIdPoyecto());
					values.put(ConstantesBD.ColObras[2], obra.getNoContrato());
					values.put(ConstantesBD.ColObras[3], obra.getRfc());
					values.put(ConstantesBD.ColObras[4], obra.getNombre());
					values.put(ConstantesBD.ColObras[5], obra.getIdGobierno());
					values.put(ConstantesBD.ColObras[6], obra.getIdSecretaria());
					values.put(ConstantesBD.ColObras[7], obra.getIdDependencia());
					values.put(ConstantesBD.ColObras[8], obra.getDireccion());
					values.put(ConstantesBD.ColObras[9], obra.getSubdireccion());
					values.put(ConstantesBD.ColObras[10], obra.getArea());
					values.put(ConstantesBD.ColObras[11], obra.getDescripcion());
					values.put(ConstantesBD.ColObras[12], obra.getFechaContrato());
					values.put(ConstantesBD.ColObras[13], obra.getTipoContrato());
					values.put(ConstantesBD.ColObras[14], obra.getImporteContratoSinIVA());
					values.put(ConstantesBD.ColObras[15], obra.getNombreEjercicioFiscal1());
					values.put(ConstantesBD.ColObras[16], obra.getImporteFiscal1SinIVA());
					values.put(ConstantesBD.ColObras[17], obra.getImporteConvenioAmpliacion());
					values.put(ConstantesBD.ColObras[18], obra.getImporteConvenioReduccion());
					values.put(ConstantesBD.ColObras[19], obra.getImporteAjusteCostos());
					values.put(ConstantesBD.ColObras[20], obra.getFechaInicioContrato());
					values.put(ConstantesBD.ColObras[21], obra.getFechaTerminoContrato());
					values.put(ConstantesBD.ColObras[22], obra.getPartidaPresupuestal());
					values.put(ConstantesBD.ColObras[23], obra.getAnticipo());
					values.put(ConstantesBD.ColObras[24], obra.getNoFianzaAnticipo());
					values.put(ConstantesBD.ColObras[25], obra.getFechaFianzaAnticipo());
					values.put(ConstantesBD.ColObras[26], obra.getMontoFianzaAnticipo());
					values.put(ConstantesBD.ColObras[27], obra.getNoFianzaCumplimiento());
					values.put(ConstantesBD.ColObras[28], obra.getFechaFianzaCumplimiento());
					values.put(ConstantesBD.ColObras[29], obra.getMontoFianzaCumplimiento());
					values.put(ConstantesBD.ColObras[30], obra.getCargoRevision1());
					values.put(ConstantesBD.ColObras[31], obra.getNombreRevision1());
					values.put(ConstantesBD.ColObras[32], obra.getCargoRevision2());
					values.put(ConstantesBD.ColObras[33], obra.getNombreRevision2());
					values.put(ConstantesBD.ColObras[34], obra.getNombreQuienAutoriza());
					values.put(ConstantesBD.ColObras[35], obra.getCargoVoBo());
					values.put(ConstantesBD.ColObras[36], obra.getNombreVoBo());
					values.put(ConstantesBD.ColObras[37], obra.getEntidadFederativa());
					values.put(ConstantesBD.ColObras[38], obra.getIdEmpresaContratista());
					values.put(ConstantesBD.ColObras[39], obra.getSuperintendente());
					values.put(ConstantesBD.ColObras[40], obra.getBorrador());
					values.put(ConstantesBD.ColObras[41], obra.getLimiteDesvio());
					values.put(ConstantesBD.ColObras[42], obra.getIdUbicacion());
					if(obra.getVisible())
						values.put(ConstantesBD.ColObras[43], Utl_Constantes.REG_VISIBLE_INT);
					else
						values.put(ConstantesBD.ColObras[43], Utl_Constantes.REG_NO_VISIBLE_INT);
						
					Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaObra), values);

					Log.i("Uri Insert Obra", uri.toString());
				}
				
				break;

			case Utl_Constantes.TABLA_UBICACION_OBRA:

				List<Ubicaciones> lista_ubicaciones = (List<Ubicaciones>) datos;
				
				for (Ubicaciones ubicaciones2 : lista_ubicaciones) {
					
					values.put(ConstantesBD.ColUbicacionObra[0], ubicaciones2.getIdUbicacion());
					
					List<String> puntos = ubicaciones2.getUbicacion();
					String pos = "";
					for (int i = 0; i <puntos.size(); i++) {
						if(i==puntos.size()-1){
							pos += puntos.get(i);
						}else{
							pos += puntos.get(i) + Utl_Constantes.CARAC_SEPARA_PUNTOS_UBICACION;
						}
					}
					values.put(ConstantesBD.ColUbicacionObra[1], pos);

					Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaUbicacionObra), values);

					Log.i("Uri Insert Ubicacion", uri.toString());
				}

				break;
			case Utl_Constantes.TABLA_AVANCE_FINANCIERO:

				List<AvanceFinanciero> lista_avanceFinanciero = (List<AvanceFinanciero>) datos;
				
				for (AvanceFinanciero avanceFinanciero2 : lista_avanceFinanciero) {
					
//					if(avanceFinanciero2.getTipoEntidad() !=  Constantes.REF_PROYECTO){
						values.put(ConstantesBD.ColAvanceFinanciero[0], avanceFinanciero2.getIdAvanceFinaciero());
						values.put(ConstantesBD.ColAvanceFinanciero[1], avanceFinanciero2.getIdReferencia());
						values.put(ConstantesBD.ColAvanceFinanciero[2], avanceFinanciero2.getPavanceFinanciero());
						values.put(ConstantesBD.ColAvanceFinanciero[3], avanceFinanciero2.getPorcentajeTendencia());
						values.put(ConstantesBD.ColAvanceFinanciero[4], avanceFinanciero2.getFechaReporte().toString());
						values.put(ConstantesBD.ColAvanceFinanciero[5], avanceFinanciero2.getEstado());

						Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaAvanceFinanciero), values);

						Log.i("Uri Insert AvanceFinanciero", uri.toString());
//					}
				}
				
				break;

			case Utl_Constantes.TABLA_AVANCE_FISICO:

				List<AvanceFisico> lista_avanceFisico = (List<AvanceFisico>) datos;
				
				for (AvanceFisico avanceFisico2 : lista_avanceFisico) {
					
//					if(avanceFisico2.getTipoEntidad() !=  Constantes.REF_PROYECTO){
						values.put(ConstantesBD.ColAvanceFisico[0], avanceFisico2.getIdAvanceFisico());
						values.put(ConstantesBD.ColAvanceFisico[1], avanceFisico2.getIdReferencia());
						values.put(ConstantesBD.ColAvanceFisico[2], avanceFisico2.getPavanceFisico());
						values.put(ConstantesBD.ColAvanceFisico[3], avanceFisico2.getPorcentajeTendencia());
						values.put(ConstantesBD.ColAvanceFisico[4], avanceFisico2.getFechaReporte().toString());
						values.put(ConstantesBD.ColAvanceFisico[5], avanceFisico2.getEstado());

						Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaAvanceFisico), values);

						Log.i("Uri Insert AvanceFisico", uri.toString());	
//					}
					
				}

				break;
				
			case Utl_Constantes.TABLA_AVANCE_FISICO_CALCULO:

				List<AvanceFisico> avance_calculo = (List<AvanceFisico>) datos;
				
				for (AvanceFisico avanceFisico2 : avance_calculo) {
					
					if(avanceFisico2.getTipoEntidad() !=  Utl_Constantes.REF_PROYECTO){
						values.put(ConstantesBD.ColAvanceFisico[0], avanceFisico2.getIdAvanceFisico());
						values.put(ConstantesBD.ColAvanceFisico[1], avanceFisico2.getIdReferencia());
						values.put(ConstantesBD.ColAvanceFisico[2], avanceFisico2.getPavanceFisico());
						values.put(ConstantesBD.ColAvanceFisico[3], avanceFisico2.getPorcentajeTendencia());
						values.put(ConstantesBD.ColAvanceFisico[4], avanceFisico2.getFechaReporte().toString());
						values.put(ConstantesBD.ColAvanceFisico[5], avanceFisico2.getEstado());

						Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaAvanceFisico), values);

						Log.i("Uri Insert AvanceFisico Avance", uri.toString());	
					}
					
				}

				break;

			case Utl_Constantes.TABLA_DIRECTORIO:
				List<Mod_Directorio> lista_directorio = (List<Mod_Directorio>) datos;	
				for (Mod_Directorio mod_Directorio : lista_directorio) {
					
//					values.put(ConstantesBD.ColDirectorio[0], mod_Directorio.getIdDirectorio());
					values.put(ConstantesBD.ColDirectorio[1], mod_Directorio.getIdObra());
					values.put(ConstantesBD.ColDirectorio[2], mod_Directorio.getRfcEmpresa());
					values.put(ConstantesBD.ColDirectorio[3], mod_Directorio.getTipoEmpresa());
					values.put(ConstantesBD.ColDirectorio[4], mod_Directorio.getNombreEmpresa());
					values.put(ConstantesBD.ColDirectorio[5], mod_Directorio.getNombre());
					values.put(ConstantesBD.ColDirectorio[6], mod_Directorio.getApPaterno());
					values.put(ConstantesBD.ColDirectorio[7], mod_Directorio.getApMaterno());
					values.put(ConstantesBD.ColDirectorio[8], mod_Directorio.getRfcPersonal());
					values.put(ConstantesBD.ColDirectorio[9], mod_Directorio.getCargo());
					values.put(ConstantesBD.ColDirectorio[10], mod_Directorio.getTituloProfesional());
					values.put(ConstantesBD.ColDirectorio[11], mod_Directorio.getCedulaProfesional());
					values.put(ConstantesBD.ColDirectorio[12], mod_Directorio.getFotografia());
					values.put(ConstantesBD.ColDirectorio[13], mod_Directorio.getSkype());
					values.put(ConstantesBD.ColDirectorio[14], mod_Directorio.getEmail());
					values.put(ConstantesBD.ColDirectorio[15], mod_Directorio.getTelefonos());
					values.put(ConstantesBD.ColDirectorio[16], mod_Directorio.getRadios());
					if(mod_Directorio.isVisible())
						values.put(ConstantesBD.ColDirectorio[17], Utl_Constantes.REG_VISIBLE_INT);
					else
						values.put(ConstantesBD.ColDirectorio[17], Utl_Constantes.REG_NO_VISIBLE_INT);
					
					Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaDirectorio), values);
					
					Log.i("Uri Insert Directorio", uri.toString());

				}
				break;

			case Utl_Constantes.TABLA_NOTA:
				
				List<Notas> lista_notas = (List<Notas>) datos;
				for (Notas notas2 : lista_notas) {
					
					values.put(ConstantesBD.ColNotas[0], notas2.getIdRepoNotas());
					values.put(ConstantesBD.ColNotas[1], notas2.getIdReporteDiario());
					values.put(ConstantesBD.ColNotas[2], notas2.getDescripcion());
					values.put(ConstantesBD.ColNotas[3], notas2.getFecha().toString());
					
					Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaNota), values);
					
					Log.i("Uri Insert Notas", uri.toString());
				}
				break;

			case Utl_Constantes.TABLA_MINUTAS:
				break;

			case Utl_Constantes.TABLA_DOCUMENTOS_OBRA:
				break;

			case Utl_Constantes.TABLA_CONCEPTO:

				List<Concepto> lista_conceptos = (List<Concepto>) datos;
				
				for (Concepto concepto : lista_conceptos) {
					
					values.put(ConstantesBD.ColConceptos[0], concepto.getIdConcepto());
					values.put(ConstantesBD.ColConceptos[1], concepto.getIdObra());
					values.put(ConstantesBD.ColConceptos[2], concepto.getClave());
					values.put(ConstantesBD.ColConceptos[3], concepto.getDescripcion());
					values.put(ConstantesBD.ColConceptos[4], concepto.getUnidadMedida());
					values.put(ConstantesBD.ColConceptos[5], concepto.getCantidadTotal());
					values.put(ConstantesBD.ColConceptos[6], concepto.getPrecioUnitario());
					values.put(ConstantesBD.ColConceptos[7], concepto.getImporte());
					values.put(ConstantesBD.ColConceptos[8], concepto.getCantidadAvance());
					values.put(ConstantesBD.ColConceptos[9], concepto.getFechaInicio());
					values.put(ConstantesBD.ColConceptos[10], concepto.getFechaFin());
					values.put(ConstantesBD.ColConceptos[11], concepto.getPadre());
					values.put(ConstantesBD.ColConceptos[12], concepto.getCantidadTotal());
					if(concepto.getVisible())
						values.put(ConstantesBD.ColConceptos[13], Utl_Constantes.REG_VISIBLE_INT);
					else
						values.put(ConstantesBD.ColConceptos[13], Utl_Constantes.REG_NO_VISIBLE_INT);

					Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaConcepto), values);

					Log.i("Uri Insert Concepto", uri.toString());
				}
				break;

			case Utl_Constantes.TABLA_REPORTE_DIARIO:

				List<ReporteDiario> lista_reporteDiario = (List<ReporteDiario>) datos;
				
				for (ReporteDiario reporteDiario2 : lista_reporteDiario) {
					values.put(ConstantesBD.ColReporteDiario[0], reporteDiario2.getIdReporteDiario());
					values.put(ConstantesBD.ColReporteDiario[1], reporteDiario2.getIdObra());
					values.put(ConstantesBD.ColReporteDiario[2], reporteDiario2.getIdNotas());
					values.put(ConstantesBD.ColReporteDiario[3], reporteDiario2.getIdRMaquinariaEquipo());
					values.put(ConstantesBD.ColReporteDiario[4], reporteDiario2.getIdRPersonal());
					values.put(ConstantesBD.ColReporteDiario[5], reporteDiario2.getFechaReporteDiario());

					Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaReporteDiario), values);

					Log.i("Uri Insert ReporteDiario", uri.toString());
				}

				break;

			case Utl_Constantes.TABLA_REPO_PERSONAL_CAT_PERSONAL:
				
				break;

			case Utl_Constantes.TABLA_CAT_PERSONAL:
				
				List<CatPersonal> lista_personal = (List<CatPersonal>) datos;
				
				for (CatPersonal catPersonal : lista_personal) {
					values.put(ConstantesBD.ColCatPersonal[0], catPersonal.getIdTipoPersonal());
					values.put(ConstantesBD.ColCatPersonal[1], catPersonal.getTipoPersonal());
					values.put(ConstantesBD.ColCatPersonal[2], catPersonal.getDescipcion());
					if(catPersonal.getVisible())
						values.put(ConstantesBD.ColCatPersonal[3], Utl_Constantes.REG_VISIBLE_INT);
					else
						values.put(ConstantesBD.ColCatPersonal[3], Utl_Constantes.REG_NO_VISIBLE_INT);
					
					Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaCatPersonal), values);
					
					Log.i("Uri Insert Cat Personal", uri.toString());
				}
				
				
				break;

			case Utl_Constantes.TABLA_TIPO_MAQUINARIA:

				List<CatTipoMaquinaria> lista_catTipoMaquinaria = (List<CatTipoMaquinaria>) datos;
				
				for (CatTipoMaquinaria catTipoMaquinaria2 : lista_catTipoMaquinaria) {
					values.put(ConstantesBD.ColCatTipoMaquinaria[0], catTipoMaquinaria2.getIdTipoMaquinaria());
					values.put(ConstantesBD.ColCatTipoMaquinaria[1], catTipoMaquinaria2.getTipoMaquinaria());
					values.put(ConstantesBD.ColCatTipoMaquinaria[2], catTipoMaquinaria2.getDescripcion());

					Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaCatTipoMaquinaria), values);

					Log.i("Uri Insert CatTipoMaquinaria", uri.toString());
				}

				break;

			case Utl_Constantes.TABLA_CAT_MAQUINARIA:

				List<Maquinaria> lista_maquinaria = (List<Maquinaria>) datos;
				
				for (Maquinaria maquinaria2 : lista_maquinaria) {
					values.put(ConstantesBD.ColCatMaquinaria[0], maquinaria2.getIdMaquinaria());
					values.put(ConstantesBD.ColCatMaquinaria[1], maquinaria2.getIdTipoMaquinaria());
					if(maquinaria2.getPathImagen() == null){
						values.put(ConstantesBD.ColCatMaquinaria[2], "");
					}else{
						values.put(ConstantesBD.ColCatMaquinaria[2], maquinaria2.getPathImagen());
					}
					
					values.put(ConstantesBD.ColCatMaquinaria[3], maquinaria2.getDescripcion());
					if(maquinaria2.getVisible())
						values.put(ConstantesBD.ColCatMaquinaria[4], Utl_Constantes.REG_VISIBLE_INT);
					else
						values.put(ConstantesBD.ColCatMaquinaria[4], Utl_Constantes.REG_NO_VISIBLE_INT);

					Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaCatMaquinaria), values);

					Log.i("Uri Insert CatMaquinaria", uri.toString());
				}

				break;
				
			case Utl_Constantes.TABLA_EMPRESAS:
				
				List<Empresa> lista_empresas = (List<Empresa>) datos;
				
				for (Empresa empresa : lista_empresas) {
					values.put(ConstantesBD.colEmpresas[0], empresa.getIdEmpresa());
					values.put(ConstantesBD.colEmpresas[1], empresa.getIdTipoEmpresa());
					values.put(ConstantesBD.colEmpresas[2], empresa.getNombre());
					values.put(ConstantesBD.colEmpresas[3], empresa.getCalle());
					values.put(ConstantesBD.colEmpresas[4], empresa.getCodiPostal());
					values.put(ConstantesBD.colEmpresas[5], empresa.getColonia());
					values.put(ConstantesBD.colEmpresas[6], empresa.getDelOMun());
					values.put(ConstantesBD.colEmpresas[7], empresa.getEntidad());
					values.put(ConstantesBD.colEmpresas[8], empresa.getImss());
					values.put(ConstantesBD.colEmpresas[9], empresa.getNumExt());
					values.put(ConstantesBD.colEmpresas[10], empresa.getNumInt());
					values.put(ConstantesBD.colEmpresas[11], empresa.getRfc());
					values.put(ConstantesBD.colEmpresas[12], empresa.getSiglas());
					
					Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEmpresas), values);
					
					Log.i("Uri Insert Empresa", uri.toString());
				}
				
				break;

			case Utl_Constantes.TABLA_REPO_MAQUINARIA_CAT_MAQUINARIA:
				break;

			case Utl_Constantes.TABLA_AVANCE_CONCEPTO:

				break;

			case Utl_Constantes.TABLA_MULTIMEDIA:

				break;

			case Utl_Constantes.TABLA_ESTIMACION:

				break;

			default:
				break;
			}	
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Insert Fist Sync", "Error al Insertar datos");
			return false;
		}
		
	}

}
