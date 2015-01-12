package com.csgit.cao.syncadapter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.http.util.ByteArrayBuffer;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.csgit.cao.R;
import com.csgit.cao.business.Bus_Conceptos;
import com.csgit.cao.business.Bus_Documentos;
import com.csgit.cao.business.Bus_Estimacion;
import com.csgit.cao.business.Bus_First_Sync;
import com.csgit.cao.business.Bus_Maquinaria;
import com.csgit.cao.business.Bus_Notas;
import com.csgit.cao.business.Bus_Obras;
import com.csgit.cao.business.Bus_Personal;
import com.csgit.cao.business.Bus_ReporteDiario;
import com.csgit.cao.business.Bus_Sync;
import com.csgit.cao.dao.ConstantesBD;
import com.csgit.cao.model.Mod_BlobStore;
import com.csgit.cao.model.Mod_Directorio;
import com.csgit.cao.model.Mod_Documentos;
import com.csgit.cao.model.Mod_Minutas;
import com.csgit.cao.model.Mod_Multimedia;
import com.csgit.cao.model.Mod_Sync_Temp;
import com.csgit.cao.model.communicationchannel.Communicationchannel;
import com.csgit.cao.model.communicationchannel.model.AditivasDeductivas;
import com.csgit.cao.model.communicationchannel.model.AvanceFinanciero;
import com.csgit.cao.model.communicationchannel.model.AvanceFisico;
import com.csgit.cao.model.communicationchannel.model.CatPersonal;
import com.csgit.cao.model.communicationchannel.model.CatTipoMaquinaria;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseAvanceFinanciero;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseAvanceFisico;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseCatPersonal;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseCatTipoMaquinaria;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseConcepto;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseDirectorio;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseEmpresa;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseObra;
import com.csgit.cao.model.communicationchannel.model.Concepto;
import com.csgit.cao.model.communicationchannel.model.Directorio;
import com.csgit.cao.model.communicationchannel.model.Empresa;
import com.csgit.cao.model.communicationchannel.model.Estimacion;
import com.csgit.cao.model.communicationchannel.model.Notas;
import com.csgit.cao.model.communicationchannel.model.Obra;
import com.csgit.cao.model.communicationchannel.model.Persona;
import com.csgit.cao.model.communicationchannel.model.RepMaquinariaCatMaquinaria;
import com.csgit.cao.model.communicationchannel.model.RepoPersonalCatPersonal;
import com.csgit.cao.model.communicationchannel.model.ReporteDiario;
import com.csgit.cao.model.communicationchannel.model.Ubicaciones;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_EndPoints;
import com.csgit.cao.utils.Utl_HttpClient;
import com.csgit.cao.utils.Utl_Imagen;

@SuppressLint("ShowToast")
public class Sync_SyncAdapter extends AbstractThreadedSyncAdapter{

	private static final String TAG = "SyncAdapter";
	private Context context;

	private Bus_Obras buss_obras;
	private Bus_Conceptos bus_conceptos;
	private Bus_First_Sync bus_insertFirstSync;
	private Bus_Notas bus_notas;
	private Bus_Sync bus_sync;
	private Bus_Personal bus_personal;
	private Bus_Maquinaria bus_maquinaria;
	private Bus_Estimacion bus_estimacion;
	private Bus_ReporteDiario bus_reporteDiario;
	private Bus_Documentos bus_documentos;

	private Communicationchannel channel;
	private SharedPreferences preferences;

	private String email = "";
    
	/**
	 * Constructor del SyncAdapter
	 * @param context
	 * @param autoInitialize
	 */
	public Sync_SyncAdapter(Context context, boolean autoInitialize) {
		super(context, autoInitialize);
		// TODO Auto-generated constructor stub
		this.context = context;
		
		buss_obras = new Bus_Obras(this.context);
		bus_conceptos =  new Bus_Conceptos(this.context);
		bus_insertFirstSync = new Bus_First_Sync(this.context);
		bus_notas = new Bus_Notas(this.context);
		bus_sync = new Bus_Sync(this.context);
		bus_personal = new Bus_Personal(this.context);
		bus_maquinaria = new Bus_Maquinaria(this.context);
		bus_estimacion = new Bus_Estimacion(this.context);
		bus_reporteDiario = new Bus_ReporteDiario(this.context);
		bus_documentos = new Bus_Documentos(this.context);
		
		preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
		email = preferences.getString(Utl_Constantes.PRE_ACCOUNT_NAME, "");
		
	}
	
	/**
	 * Se ejecuta siempre se que se realize la petición de sincronización,
	 * ya sea desde la aplicación o directamente desde la cuenta en el dispositivo
	 */
	@Override
	public void onPerformSync(Account account, Bundle extras, String authority,
			ContentProviderClient provider, SyncResult syncResult) {
		// TODO Auto-generated method stub
		
		sicronizarInformacion();	
	}
	
	private void showToast(final boolean result, final int tipo){
		Looper looper = context.getMainLooper();
		if(looper != null){
			new Handler(looper).post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					switch (tipo) {
					case 0:
						if(result){
							Toast.makeText(context, 
									context.getResources().getString(R.string.str_toast_sync_finalizada),
									Toast.LENGTH_SHORT).show();
							Log.i("Sync", "Finalizada");
						}else{
							Toast.makeText(context, 
									context.getResources().getString(R.string.str_toast_sync_error),
									Toast.LENGTH_SHORT).show();
						}
						break;
						
					case 1:
						Toast.makeText(context,
								context.getResources().getString(R.string.str_toast_sync_inicia),
								Toast.LENGTH_SHORT).show();
						break;

					default:
						break;
					}
					
					
				}
			});
		}
		
	}
	
	/**
	 * Realiza el proceso de sincronización de la informacion
	 */
	private void sicronizarInformacion(){

		channel = Utl_EndPoints.initEndPoint();
		showToast(true, 1);
		// Obtener Cambios nuevos
		try {

			List<List<Mod_Sync_Temp>> lista_registros = bus_sync.getRegistros();
			List<Mod_Sync_Temp> insert = lista_registros.get(0);

			//	Enviar Nuevos Datos
			for (Mod_Sync_Temp mod_Sync_Temp : insert) {

				switch (mod_Sync_Temp.getEntidad()) {
				
				case Utl_Constantes.TABLA_ADITIVAS_DEDUCTIVAS:
					AditivasDeductivas aditivaDeductiva = bus_conceptos.getAditivaDeuctiva(mod_Sync_Temp.getIdRegistro());
					channel.insertAditivasDeductivas(aditivaDeductiva).execute();
					int idAditivaDeductiva = bus_sync.updateSync(mod_Sync_Temp.getEntidad(), mod_Sync_Temp.getIdRegistro(),
							mod_Sync_Temp.getAccion());
					Log.i("Update sync", "# reg update: " + idAditivaDeductiva);
					break;

				case Utl_Constantes.TABLA_REPORTE_DIARIO:
					ReporteDiario repDiario = bus_reporteDiario.getThisReporteDiario(mod_Sync_Temp.getIdRegistro());
					repDiario.setEmail(email);
					channel.insertReporteDiario(repDiario).execute();

					//					Actualizar registro
					int idRepDiario = bus_sync.updateSync(mod_Sync_Temp.getEntidad(), 
							mod_Sync_Temp.getIdRegistro(), mod_Sync_Temp.getAccion());
//					bus_reporteDiario.updateSyncReporteDiario(repDiario.getIdReporteDiario());
					bus_reporteDiario.updateSyncReporteDiario(repDiario.getIdDispo());
					
					Log.i("Update sync", "# reg update: " + idRepDiario);
					
					break;

				case Utl_Constantes.TABLA_ESTIMACION:
					Estimacion estimacion = bus_estimacion.getEstimacionSync(mod_Sync_Temp.getIdRegistro());
					estimacion.setEmail(email);
					channel.insertEstimacion(estimacion).execute();

					//					Actualizar registro
					int idEstimacion = bus_sync.updateSync(mod_Sync_Temp.getEntidad(), 
							mod_Sync_Temp.getIdRegistro(), mod_Sync_Temp.getAccion());
					Log.i("Update sync", "# reg update: " + idEstimacion);

					break;

				case Utl_Constantes.TABLA_NOTA:
					Notas nota = bus_notas.getNota(mod_Sync_Temp.getIdRegistro());
					nota.setEmail(email);
					channel.insertNotas(nota).execute();

					//					Actualizar registro
					int idNota = bus_sync.updateSync(mod_Sync_Temp.getEntidad(), 
							mod_Sync_Temp.getIdRegistro(), mod_Sync_Temp.getAccion());
					Log.i("Update sync", "# reg update: " + idNota);
					break;
				case Utl_Constantes.TABLA_MULTIMEDIA:
//					if(((Act_Main.sPref.equals(Constantes.ANY)) && (Act_Main.wifiConnected 
//							|| Act_Main.mobileConnected)) || ((Act_Main.sPref.equals(Constantes.WIFI)) && 
//									(Act_Main.wifiConnected))){
						
						Mod_Multimedia evidencia = buss_obras.getEvidenciaSync(mod_Sync_Temp.getIdRegistro());
						if(enviaEvidencia(evidencia, context)){
							//	Actualizar registro
							int idMultimedia = bus_sync.updateSync(mod_Sync_Temp.getEntidad(), 
									mod_Sync_Temp.getIdRegistro(), mod_Sync_Temp.getAccion());
							Log.i("Update sync", "# reg update: " + idMultimedia);	
						}else{
							Log.e("Update sync", "Error multimedidia");
						}
//					}
					
					break;
				case Utl_Constantes.TABLA_REPO_MAQUINARIA_CAT_MAQUINARIA:
					RepMaquinariaCatMaquinaria maquinariaCatMaq = 
					bus_maquinaria.getReporteMaquinariaSync(mod_Sync_Temp.getIdRegistro());
					maquinariaCatMaq.setEmail(email);
					
					channel.insertRepMaquinariaCatMaquinaria(maquinariaCatMaq).execute();

					//					Actualizar registro
					int maquinaria = bus_sync.updateSync(mod_Sync_Temp.getEntidad(), 
							mod_Sync_Temp.getIdRegistro(), mod_Sync_Temp.getAccion());
					Log.i("Update sync", "# reg update: " + maquinaria);

					break;
				case Utl_Constantes.TABLA_REPO_PERSONAL_CAT_PERSONAL:
					RepoPersonalCatPersonal personalCatPersonal = 
					bus_personal.getReportesPersonalSync(mod_Sync_Temp.getIdRegistro());
					personalCatPersonal.setEmail(email);

					channel.insertRepoPersonalCatPersonal(personalCatPersonal).execute();

					//					Actualizar registro
					int idPersonal = bus_sync.updateSync(mod_Sync_Temp.getEntidad(), 
							mod_Sync_Temp.getIdRegistro(), mod_Sync_Temp.getAccion());
					Log.i("Update sync", "# reg update: " + idPersonal);

					break;

				case Utl_Constantes.TABLA_MINUTAS:
//					if(((Act_Main.sPref.equals(Constantes.ANY)) && (Act_Main.wifiConnected || 
//							Act_Main.mobileConnected)) || ((Act_Main.sPref.equals(Constantes.WIFI)) && 
//									(Act_Main.wifiConnected))){
						
						Mod_Minutas minuta = buss_obras.getMinuta(mod_Sync_Temp.getIdRegistro());
						if(enviaMinuta(minuta, context)){
//							Actualizar registro
							int idMinuta = bus_sync.updateSync(mod_Sync_Temp.getEntidad(), 
									mod_Sync_Temp.getIdRegistro(), mod_Sync_Temp.getAccion());
							Log.i("Update sync", "# reg update: " + idMinuta);		
						}else{
							Log.i("Update sync", "Error Minuta");
						}

							
//					}
					
					break;

				case Utl_Constantes.TABLA_DOCUMENTOS_OBRA:
//					if(((Act_Main.sPref.equals(Constantes.ANY)) && (Act_Main.wifiConnected || 
//							Act_Main.mobileConnected)) || ((Act_Main.sPref.equals(Constantes.WIFI)) && 
//									(Act_Main.wifiConnected))){
						
						Mod_Documentos documento = bus_documentos.getDocumento(mod_Sync_Temp.getIdRegistro());
						if(enviaDocumento(documento, context)){
							int idDocumento = bus_sync.updateSync(mod_Sync_Temp.getEntidad(),
									mod_Sync_Temp.getIdRegistro(), mod_Sync_Temp.getAccion());
							Log.i("Update sync", "# reg update: " + idDocumento);	
						}else{
							Log.i("Update sync", "Error Documento");
						}
//					}
					
					break;

				default:
					break;
				}
			}

			//			Actualizar Datos
			List<Mod_Sync_Temp> update = lista_registros.get(1);
			List<Long> idsObras = new ArrayList<Long>();
			for (Mod_Sync_Temp mod_Sync_Temp : update) {
				switch (mod_Sync_Temp.getEntidad()) {
				case Utl_Constantes.TABLA_CONCEPTO:
					Concepto concepto = bus_conceptos.getConcepto(mod_Sync_Temp.getIdRegistro());
					channel.updateConcepto(concepto).execute();

					//					Actualizar registro
					int idConcepto = bus_sync.updateSync(mod_Sync_Temp.getEntidad(), 
							mod_Sync_Temp.getIdRegistro(), mod_Sync_Temp.getAccion());

					idsObras.add(concepto.getIdObra());
					Log.i("Update sync", "# reg update: " + idConcepto);

					break;
				case Utl_Constantes.TABLA_NOTA:
					Notas nota = bus_notas.getNota(mod_Sync_Temp.getIdRegistro());
					nota.setEmail(email);
					channel.updateNotas(nota).execute();

					//					Actualizar registro
					int idMultimedia = bus_sync.updateSync(mod_Sync_Temp.getEntidad(), 
							mod_Sync_Temp.getIdRegistro(), mod_Sync_Temp.getAccion());
					Log.i("Update sync", "# reg update: " + idMultimedia);

					break;
				case Utl_Constantes.TABLA_MULTIMEDIA:
					//					Mod_Multimedia evidencia = buss_obras.getEvidencia(mod_Sync_Temp.getIdRegistro());
					//					enviaEvidencia(evidencia, context);
					//					
					//					Multimedia multimedia = new Multimedia();
					//					multimedia.se
					//					Multimediaendpoint multi = new Multimedia();
					//					multi.updateMultimedia(content)
					break;
				case Utl_Constantes.TABLA_REPO_MAQUINARIA_CAT_MAQUINARIA:

					break;
				case Utl_Constantes.TABLA_REPO_PERSONAL_CAT_PERSONAL:

					break;

				default:
					break;
				}
			}

			//			Pedir Calculo de Avace fisico
			//			Elimiar Duplicados
			HashSet<Long> hashSet = new HashSet<Long>(idsObras);
			idsObras.clear();
			idsObras.addAll(hashSet);
			for (Long long1 : idsObras) {
				Object objectAvance =  channel
						.calculaAvance(long1, Utl_Constantes.REF_OBRA, Utl_Constantes.CAL_AVANCE_FISICO).execute();

				//				AvanceFisico avance = new AvanceFisico();
				AvanceFisico avance = parsearObjetoAvance(objectAvance);
				List<AvanceFisico> avanceFisico = new ArrayList<AvanceFisico>();
				avanceFisico.add(avance);
				bus_insertFirstSync.insertBD_EndPoint(avanceFisico, Utl_Constantes.TABLA_AVANCE_FISICO_CALCULO);
				Log.i(TAG, "Calculo idObra: " + long1);
			}


			/**
			 * Datos nuevos
			 */

			// Obtener Empresas	
			CollectionResponseEmpresa ResponseEmpresas = channel.listEmpresa().execute();
			List<Empresa> itemsEmpresa = ResponseEmpresas.getItems();
			bus_insertFirstSync.insertBD_EndPoint(itemsEmpresa, Utl_Constantes.TABLA_EMPRESAS);
			Log.i(TAG, "GET Empresas OK");

			//			Obtener obras
//			CollectionResponseObra obras = channel.listObra(Long.parseLong("0"), Long.parseLong("0")).execute();
			CollectionResponseObra obras = channel.listObra(0L, 0L, email).execute();
			List<Obra> itemsObras = obras.getItems();
			bus_insertFirstSync.insertBD_EndPoint(itemsObras, Utl_Constantes.TABLA_OBRA);
			Log.i(TAG, "GET Obras OK");

			//			Obtener conceptos nuevos
			if(itemsObras != null)
				for (Obra obra : itemsObras) {

					//				Obtener ubicaciones
					Ubicaciones ubicaciones = channel.getUbicaciones(obra.getIdUbicacion()).execute();
					List<Ubicaciones> itemsUbicacion = new ArrayList<Ubicaciones>();
					itemsUbicacion.add(ubicaciones);
					bus_insertFirstSync.insertBD_EndPoint(itemsUbicacion, Utl_Constantes.TABLA_UBICACION_OBRA);
					Log.i(TAG, "GET Ubicacion OK");

					CollectionResponseConcepto conceptos = channel.listConcepto(obra.getIdObra(), 0L).execute();
					List<Concepto> items = conceptos.getItems();
					bus_insertFirstSync.insertBD_EndPoint(items, Utl_Constantes.TABLA_CONCEPTO);
					Log.i(TAG, "Get Conceptos OK");

					//				Obtener Avance Fisico
					CollectionResponseAvanceFisico  ResponseAvanceFisico = channel.listAvanceFisico(obra.getIdObra(), Utl_Constantes.REF_OBRA).execute();
					List<AvanceFisico> itemsAvanceFisico = ResponseAvanceFisico.getItems();
					bus_insertFirstSync.insertBD_EndPoint(itemsAvanceFisico, Utl_Constantes.TABLA_AVANCE_FISICO);
					Log.i(TAG, "Get Av. Fisico OK");

					//				Obtener Avance Financiero
					CollectionResponseAvanceFinanciero avaFinanciero = channel.listAvanceFinanciero(obra.getIdObra(), Utl_Constantes.REF_OBRA).execute();
					List<AvanceFinanciero> itemsFinanciero = avaFinanciero.getItems();
					bus_insertFirstSync.insertBD_EndPoint(itemsFinanciero, Utl_Constantes.TABLA_AVANCE_FINANCIERO);
					Log.i(TAG, "Get Av. Financiero OK");

					//				Obtener Directorio
					CollectionResponseDirectorio responseDirectorio = channel.listDirectorio(Long.parseLong(
							String.valueOf(Utl_Constantes.REF_OBRA)), obra.getIdObra()).execute();
					obtenerDirectorio(responseDirectorio);
					Log.i(TAG, "Get Directorio OK");
				}

			//			Obtener cat persoanal
			CollectionResponseCatPersonal ResponseCatPersonal = channel.listCatPersonal().execute();
			List<CatPersonal> catPersonal = ResponseCatPersonal.getItems();
			bus_insertFirstSync.insertBD_EndPoint(catPersonal, Utl_Constantes.TABLA_CAT_PERSONAL);
			Log.i(TAG, "Get Personal OK");	
			
			//				Obtener tipo maquinaria
			CollectionResponseCatTipoMaquinaria ResponseTipoMaquinaria = channel.listCatTipoMaquinaria().execute();
			List<CatTipoMaquinaria> catTipoMaq = ResponseTipoMaquinaria.getItems();
			bus_insertFirstSync.insertBD_EndPoint(catTipoMaq, Utl_Constantes.TABLA_TIPO_MAQUINARIA);
			Log.i(TAG, "Get Cat Tipo Maquinaria OK");	
			
//			//				Obtener maquinaria
//			CollectionResponseMaquinaria ResponseMaquinaria = channel.listMaquinaria().execute();
//			List<Maquinaria> listMaq = ResponseMaquinaria.getItems();
//			if(bus_insertFirstSync.insertBD_EndPoint(listMaq, Constantes.TABLA_CAT_MAQUINARIA, context.getContentResolver())){
//				Log.i(TAG, "Get Maquinaria OK");
//
//				//	Descargar Imagenes de la Maquinaria
//				List<Maquinaria> listaMaquinaria = bus_maquinaria.getCatMaquinaria();
//				Utl_Imagen.getFileBlobStore(listaMaquinaria, Constantes.REF_CAT_MAQUINARIA, context);
//
//				Log.i(TAG, "Get Imagenes Maquinaria OK");
//			}		
//if(!bus_maquinaria.isMaquinariaExist(maquina.getIdMaquinaria())){
				
//			}	
			
			//			Borrar Datos
			List<Mod_Sync_Temp> delete = lista_registros.get(2);
			for (Mod_Sync_Temp mod_Sync_Temp : delete) {
				switch (mod_Sync_Temp.getEntidad()) {
				case Utl_Constantes.TABLA_NOTA:

					break;
				case Utl_Constantes.TABLA_MULTIMEDIA:

					break;
				case Utl_Constantes.TABLA_REPO_MAQUINARIA_CAT_MAQUINARIA:

					break;
				case Utl_Constantes.TABLA_REPO_PERSONAL_CAT_PERSONAL:

					break;

				case Utl_Constantes.TABLA_MINUTAS:

					break;

				default:
					break;
				}
			}

			showToast(true, 0);

		} catch (Exception e) {
			// TODO: handle exception
			Log.i(TAG, "Error Sincronizacion: " + e.getMessage());
			showToast(false, 0);
		}

	}
	
	private boolean obtenerDirectorio(CollectionResponseDirectorio response){
		List<Mod_Directorio> directorio = new ArrayList<Mod_Directorio>();

		try {

			List<Directorio> lista = response.getItems();

			if(lista != null){
				for (Directorio directorio2 : lista) {

					for (String idPersona : directorio2.getArregloPersonasId()) {

						Persona responsePersona = channel.getPersona(Long.parseLong(idPersona)).execute();
						Mod_Directorio item = new Mod_Directorio();
//						item.setIdDirectorio(directorio2.getIdDirectorio());
						item.setIdObra(directorio2.getIdReferencia());
						
						Bus_Obras bus_obra = new Bus_Obras(context);
						Empresa empresa = bus_obra.getEmpresa(responsePersona.getIdEmpresa());
						
						item.setRfcEmpresa(empresa.getRfc());
						item.setTipoEmpresa(empresa.getIdTipoEmpresa());
						item.setNombreEmpresa(empresa.getNombre());
						item.setNombre(responsePersona.getNombre());
						item.setApPaterno(responsePersona.getApPaterno());
						item.setApMaterno(responsePersona.getApMaterno());
						item.setRfcPersonal(responsePersona.getRfc());
						item.setCargo(responsePersona.getCargo());
						item.setTituloProfesional(responsePersona.getTituloProfesional());
						item.setCedulaProfesional(responsePersona.getCedulaProfesional());
						item.setFotografia(responsePersona.getFotografia());
						item.setSkype(responsePersona.getUsuarioSkype());
						item.setEmail(responsePersona.getEmails());
						item.setTelefonos(responsePersona.getTelefonos());
						item.setRadios(responsePersona.getRadios());

						directorio.add(item);
					}
				}
				return bus_insertFirstSync.insertBD_EndPoint(directorio, Utl_Constantes.TABLA_DIRECTORIO);
			}else
				return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	private AvanceFisico parsearObjetoAvance(Object object){
		AvanceFisico avance = new AvanceFisico();

		@SuppressWarnings("unchecked")
		com.google.api.client.util.ArrayMap<String, Object> map = 
		(com.google.api.client.util.ArrayMap<String, Object>) object;
		
		avance.setIdAvanceFisico(Long.parseLong(String.valueOf(map.get("id_AvanceFisico"))));
		avance.setIdReferencia(Long.parseLong(String.valueOf(map.get("id_referencia"))));
		avance.setPorcentajeTendencia(Double.parseDouble(String.valueOf(map.get("porcentaje_tendencia"))));
		avance.setPavanceFisico(Double.parseDouble(String.valueOf(map.get("pavanceFisico"))));
		avance.setEstado(Integer.parseInt(String.valueOf(map.get("estado"))));
		avance.setFechaReporte(String.valueOf(map.get("fechaReporte")));
		avance.setTipoEntidad(Integer.parseInt(String.valueOf(map.get("tipo_Entidad"))));

		return avance;
	}
	
	private boolean enviaEvidencia(Mod_Multimedia imagen, Context context){
		Utl_HttpClient resposeGson = new Utl_HttpClient();
		Bus_Obras bus_obras = new Bus_Obras(context);

		String urlBlobStore = resposeGson.getUrlBlobStore(Utl_Constantes.URL_BLOB_STORE);
		if(urlBlobStore != null){
			try {
				int formato = Utl_Imagen.getFormatoInt(imagen.getFormato());
				Mod_BlobStore blobStore = resposeGson.setMultimedia(urlBlobStore, imagen.getPath(),
						String.valueOf(imagen.getIdObra()), String.valueOf(imagen.getTipo()), 
						String.valueOf(formato), String.valueOf(Utl_Constantes.REF_EVIDENCIA), 
						imagen.getDescripcion(), new String());

				if(blobStore != null){
					Log.i("BlobStore url", blobStore.getServingUrl());
					Log.i("BlobStore blobKey", blobStore.getBlobKey());
					ContentValues values = new ContentValues();

					values.put(ConstantesBD.ColMultimedia[6], blobStore.getServingUrl());
					values.put(ConstantesBD.ColMultimedia[7], blobStore.getBlobKey());
					values.put(ConstantesBD.ColMultimedia[8], Utl_Constantes.REG_SINC);

					int val = bus_obras.updateMultimedia(imagen.getIdMultimedia(), values);
					Log.i(Utl_Constantes.TAG_UPDATE_MULTIMEDIA, val + "");
					return true;
				}else{
					Log.i("BlobStore","null");
					return false;
				}
				// Actualizar BD
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				Log.i(TAG, "Error :" + e);
				Toast.makeText(context, "Error de Red", Toast.LENGTH_SHORT).show();
				return false;
			}
		}
		return false;
	}
	
	private boolean enviaDocumento(Mod_Documentos documento, Context context){
		Utl_HttpClient resposeGson = new Utl_HttpClient();
		String urlBlobStore = resposeGson.getUrlBlobStore(Utl_Constantes.URL_BLOB_STORE);
		
		if(urlBlobStore != null){
			try {
				int formato = Utl_Imagen.getFormatoInt(documento.getFormato());
				Mod_BlobStore blobStore = resposeGson.setMultimedia(urlBlobStore, 
						documento.getPathDocumento(), String.valueOf(documento.getIdObra()), 
						String.valueOf(documento.getTipoArchivo()), String.valueOf(formato), 
						String.valueOf(Utl_Constantes.REF_OBRA), documento.getNombreDocumento(),
						documento.getMimeType());

				if(blobStore!= null){
					Log.i("BlobStore url", blobStore.getServingUrl());
					Log.i("BlobStore blobKey", blobStore.getBlobKey());
					ContentValues values = new ContentValues();

					values.put(ConstantesBD.ColDocumentosObra[7], blobStore.getServingUrl());
					values.put(ConstantesBD.ColDocumentosObra[8], blobStore.getBlobKey());
					values.put(ConstantesBD.ColDocumentosObra[9], Utl_Constantes.REG_SINC);

					int val = bus_documentos.updateDocumento(documento.getIdDocumento(), values);
					Log.i(Utl_Constantes.TAG_UPDATE_DOCUMENTO, val+"");
					return true;
				}else{
					Log.i("BlobStore","null");
					return false;
				}
				// Actualizar BD
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				Log.i(TAG, "Error :" + e);
				Toast.makeText(context, "Error de Red", Toast.LENGTH_SHORT).show();
				return false;
			}
		}
		return false;
	}
	
	private boolean enviaMinuta(Mod_Minutas minuta, Context context){
		Utl_HttpClient resposeGson = new Utl_HttpClient();
		Bus_Obras bus_obras = new Bus_Obras(context);

		String urlBlobStore = resposeGson.getUrlBlobStore(Utl_Constantes.URL_BLOB_STORE);
		if(urlBlobStore != null){
			try {
				int formato = Utl_Imagen.getFormatoInt(minuta.getFormato());
				Mod_BlobStore blobStore = resposeGson.setMultimedia(urlBlobStore, minuta.getPathMinutas(),
						String.valueOf(minuta.getIdObra()), String.valueOf(minuta.getTipo()), 
						String.valueOf(formato), String.valueOf(Utl_Constantes.REF_MINUTA), new String(), 
						new String());

				if(blobStore!= null){
					Log.i("BlobStore url", blobStore.getServingUrl());
					Log.i("BlobStore blobKey", blobStore.getBlobKey());
					ContentValues values = new ContentValues();

					values.put(ConstantesBD.ColMinutas[6], blobStore.getServingUrl());
					values.put(ConstantesBD.ColMinutas[7], blobStore.getBlobKey());
					values.put(ConstantesBD.ColMinutas[8], Utl_Constantes.REG_SINC);

					int val = bus_obras.updateMinuta(minuta.getIdMinutas(), values);
					Log.i("Minuta", val+"");
					return true;
				}else{
					Log.i("BlobStore","null");
					return false;
				}

				// Actualizar BD
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				Log.i(TAG, "Error :" + e);
				Toast.makeText(context, "Error de Red", Toast.LENGTH_SHORT).show();
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Descarga las imagenes del blobstore
	 * @param lista lista de imagenes que se tienen que descargar
	 */
	private void getImageBlobStore(List<Mod_Multimedia> lista){
		try {
			int cont = 0;
			for (Mod_Multimedia mod_Multimedia : lista) {
				URL url = new URL(Utl_Constantes.URL_GET_BLOB_STORE + mod_Multimedia.getBlobKey());
				File file = new File("/storage/emulated/0/Pictures/Iuyet/Obras/delblob"+cont+".jpg");
				URLConnection urlconn = url.openConnection();
				InputStream is = urlconn.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				ByteArrayBuffer baf = new ByteArrayBuffer(50);
				int current = 0;
				while ((current = bis.read()) != -1) {
					baf.append((byte) current);
				}
				/* Convert the Bytes read to a String. */
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(baf.toByteArray());
				fos.close();

				Log.i("Imagen BlobStore", "Imagen "+cont+" descagada");
				cont++;
			}
		} catch (Exception e) {
			// TODO: handle exception

		}

	}
	
}
