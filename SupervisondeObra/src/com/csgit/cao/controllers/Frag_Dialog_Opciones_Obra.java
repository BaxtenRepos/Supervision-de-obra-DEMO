package com.csgit.cao.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.ByteArrayBuffer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Opcion_Evidencia;
import com.csgit.cao.business.Bus_Documentos;
import com.csgit.cao.business.Bus_Obras;
import com.csgit.cao.model.Mod_Documentos;
import com.csgit.cao.model.Mod_Minutas;
import com.csgit.cao.model.Mod_Opcion_Evidencia;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Documentos;
import com.csgit.cao.utils.Utl_Fechas;
import com.csgit.cao.utils.Utl_Imagen;

public class Frag_Dialog_Opciones_Obra extends DialogFragment{
	
	private List<Mod_Opcion_Evidencia> list_opciones;
	private Context context;
	private Long idObra;
	private SharedPreferences preferences;
	private Adp_Opcion_Evidencia adapter;
	private Editor edit;
	
	private Bus_Documentos bus_documentos;
	private Bus_Obras bus_obra;
	
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int SUBIR_DOCUMENTO_ACTIVITY_REQUEST_CODE = 200;
	
	public Frag_Dialog_Opciones_Obra(Context context, Long idObra, SharedPreferences preferences) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.idObra = idObra;
		this.preferences = preferences;
		
		bus_documentos = new Bus_Documentos(this.context);
		bus_obra = new Bus_Obras(this.context);
	}
	
	private void llenarLista(){
		String[] aux = getResources().getStringArray(R.array.opciones_obra);
		list_opciones.add(new Mod_Opcion_Evidencia(R.drawable.ic_action_minuta, aux[0]));
		list_opciones.add(new Mod_Opcion_Evidencia(R.drawable.ic_action_upload, aux[1]));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.lyt_frag_dialog_opciones_obra, container);
		getDialog().setTitle(getResources().getString(R.string.str_act_evi_title_dialog));
		
		list_opciones = new ArrayList<Mod_Opcion_Evidencia>();
		llenarLista();
		
		ListView listView = (ListView) view.findViewById(R.id.lv_opciones_obra);
		adapter = new Adp_Opcion_Evidencia(context, R.layout.lyt_adp_frag_opcion_evidencia, list_opciones);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					if(bus_obra.masMinutas(idObra))
						tomarMinuta();
					else
						Toast.makeText(context, getResources().getString(R.string.str_toast_mas_minutas_error),
								Toast.LENGTH_LONG).show();
					break;
				case 1:
					subirArchivo();
					break;

				default:
					break;
				}
			}
		});
		
		return view;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		switch (requestCode) {
		case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
			if(resultCode == Activity.RESULT_OK){
				guardarMinuta();
			}
			break;
			
		case SUBIR_DOCUMENTO_ACTIVITY_REQUEST_CODE:
			if(resultCode == Activity.RESULT_OK){
				cargarArchivo cargar = new cargarArchivo();
				cargar.execute(data);
			}
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void guardarMinuta() {

		Mod_Minutas minuta = new Mod_Minutas();
		String pathMinuta = preferences.getString(Utl_Constantes.PRE_PATH_MINUTAS,
				Utl_Constantes.PRE_DEFAULT);
		String nombre = Utl_Imagen.getNombreImagen(pathMinuta);
		String formato = Utl_Imagen.getExtencionEvidencia(nombre);
		procesarImagen(pathMinuta);
		minuta.setIdObra(idObra);
		minuta.setFormato(formato);
		minuta.setPathMinutas(pathMinuta);
		minuta.setFechaMinutas(Utl_Fechas.getFecha());
		minuta.setTipo(Utl_Constantes.TIPO_IMAGEN);
		minuta.setIsSync(Utl_Constantes.REG_NO_SINC);

		if (Bus_Obras.insertarMinutas(context.getContentResolver(), minuta))
			Toast.makeText(
					context,
					getResources().getString(
							R.string.str_toast_minuta_guardar_ok),
					Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(
					context,
					getResources().getString(
							R.string.str_toast_minuta_guardar_error),
					Toast.LENGTH_SHORT).show();
		
		getDialog().dismiss();
	}

	private void procesarImagen(String pathImagen) {

		Bitmap bit = Utl_Imagen.optimizaImagen(pathImagen);

		try {
			File fileImage = new File(pathImagen);
			FileOutputStream outputStream = new FileOutputStream(fileImage);
			bit.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("Minuta", "Error: " + e);
		}

	}
	
	public void tomarMinuta(){

		Intent intento = null;
		File file = null;
		file = Utl_Imagen.crearAchivoMinuta(idObra, context);
		intento = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intento.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
		startActivityForResult(intento, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

		edit = preferences.edit();
		edit.putString(Utl_Constantes.PRE_PATH_MINUTAS, file.getPath());
		edit.commit();

	}
	
	@SuppressLint("InlinedApi")
	private void subirArchivo(){
		
		Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType("*/*");
		if(intent.resolveActivity(context.getPackageManager()) != null){
			startActivityForResult(intent, SUBIR_DOCUMENTO_ACTIVITY_REQUEST_CODE);
		}
	}
	
	class cargarArchivo extends AsyncTask<Intent, Void, Boolean>{

		ProgressDialog progress;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			progress = new ProgressDialog(getActivity());
			progress.setMessage(getResources().getString(R.string.str_dialog_opciones_archivo_procesando));
			progress.setCancelable(false);
			progress.show();
		}
		
		@Override
		protected Boolean doInBackground(Intent... params) {
			// TODO Auto-generated method stub
			String TAG = "Documentos";
			
			Intent data = params[0];
			
			if(Utl_Documentos.isMimeTypeValido(context.getContentResolver().getType(data.getData()))){
								
				Uri uri = data.getData();
				
//				Obtener nombre del Documento
				Cursor returnCursor = context.getContentResolver().query(uri, null, null, null, null);
				int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
//				int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
				returnCursor.moveToFirst();
				String nombreDocumento = returnCursor.getString(nameIndex);
				returnCursor.close();
				
				if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
					String pathGeneral  = 
							Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
							.getAbsolutePath() + Utl_Constantes.RUTA_PRINCIPAL;
					String pathDoc = pathGeneral + Utl_Constantes.RUTA_OBRAS + Utl_Constantes.CARAC_SEPARA_RUTAS + 
							idObra + Utl_Constantes.RUTA_DOCUMENTOS_OBRA;
					
					File album = new File(pathDoc);
					album.mkdirs();
					File file = new File(album + Utl_Constantes.CARAC_SEPARA_RUTAS + nombreDocumento);
					try {
						file.createNewFile();
						InputStream is = context.getContentResolver().openInputStream(uri);
						BufferedInputStream bis = new BufferedInputStream(is);
						ByteArrayBuffer baf = new ByteArrayBuffer(50);
						int current = 0;
						while((current = bis.read()) != -1){
							baf.append((byte) current);
						}
//						Convertir los Bytes leídos en un String
						FileOutputStream fos = new FileOutputStream(file);
						fos.write(baf.toByteArray());
						fos.close();
						
//						Insertar nuevo documento a la BD
						Mod_Documentos documento = new Mod_Documentos();
						documento.setIdObra(idObra);
						documento.setPathDocumento(file.getAbsolutePath());
						if(Utl_Documentos.getExtensionDoc(nombreDocumento).equals(nombreDocumento)){
							String var = context.getContentResolver().getType(data.getData());
							String[] aux = var.split("/");
							documento.setFormato(aux[(aux.length - 1)]);
						}else{
							documento.setFormato(Utl_Documentos.getExtensionDoc(nombreDocumento));	
						}
						
						documento.setFecha(Utl_Fechas.getFecha());
						documento.setNombreDocumento(nombreDocumento);
						documento.setMimeType(context.getContentResolver().getType(data.getData()));
						documento.setIsSync(Utl_Constantes.REG_NO_SINC);
						documento.setTipoArchivo(Utl_Constantes.TIPO_FILE);
						
						Uri uriInsert = bus_documentos.insertDocumento(documento);
						Log.i("Insert Documento", uriInsert.toString());
												
						return true;
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Log.i(TAG, "Error al obtener archivo, error: " +e.getMessage());
						
						return false;
					}
					
				}
				return false;
				
			}	
//			}else{
//				Toast.makeText(context,
//						context.getResources().getString(R.string.str_dialog_opcines_archivo_no_soportado),
//						Toast.LENGTH_SHORT).show();
//				return false;
//			}
			return false;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if(result){
				Toast.makeText(context,
						context.getResources().getString(R.string.str_dialog_opciones_archivo_cargado),
						Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(context,
						context.getResources().getString(R.string.str_dialog_opcines_archivo_error),
						Toast.LENGTH_SHORT).show();
			}
			
			if(progress.isShowing())
				progress.dismiss();
			
			getDialog().dismiss();
		}
		
	}
	
}
