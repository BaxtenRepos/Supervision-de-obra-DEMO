package com.csgit.cao.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Opcion_Evidencia;
import com.csgit.cao.business.Bus_Evidencia;
import com.csgit.cao.model.Mod_Opcion_Evidencia;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Imagen;

public class Frag_Dialog_Evidencia extends DialogFragment{
	
	private Long idObra;
	private SharedPreferences preferences;
	private Editor edit;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	private Context context;
	private Adp_Opcion_Evidencia adapter;
	private List<Mod_Opcion_Evidencia> listopciones;
	private Bus_Evidencia bus_evidencia;
	
	public Frag_Dialog_Evidencia(Context context,Long idObra,SharedPreferences preferences){
		this.context = context;
		this.idObra = idObra;
		this.preferences = preferences;
		bus_evidencia = new Bus_Evidencia(this.context);
	}
	
	private void llenarLista(){
		String[] aux = getResources().getStringArray(R.array.evidencia_opciones);
		listopciones.add(new Mod_Opcion_Evidencia(R.drawable.ic_action_camera, aux[0]));
		listopciones.add(new Mod_Opcion_Evidencia(R.drawable.ic_action_video, aux[1]));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.lyt_frag_dialog_evidencia, container);
        getDialog().setTitle(getResources().getString(R.string.str_act_evi_title_dialog));
       
        listopciones = new ArrayList<Mod_Opcion_Evidencia>();
        llenarLista();
        
        ListView listView = (ListView)view.findViewById(R.id.lv_opcion_evidencia);
        adapter = new Adp_Opcion_Evidencia(context, R.layout.lyt_adp_frag_opcion_evidencia, listopciones);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					if(bus_evidencia.masEvidencia(idObra, true))
						abrirCamara(true);
					else
						Toast.makeText(context,
								context.getResources().getString(R.string.str_toast_mas_evidencia_foto_error),
								Toast.LENGTH_SHORT).show();
					break;
				case 1:
					if(bus_evidencia.masEvidencia(id, false))
						abrirCamara(false);
					else
						Toast.makeText(context,
								context.getResources().getString(R.string.str_toast_mas_evidencia_video_error), 
								Toast.LENGTH_SHORT).show();
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
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
			if(resultCode == Activity.RESULT_OK){
				Intent intento = new Intent(context, Act_Descripcion_Evidencia.class);
				intento.putExtra("idObra", idObra);
				intento.putExtra("tipo", true);
				intento.putExtra("tipoMultimedia", true);
				intento.putExtra("pathImagen", preferences.getString(Utl_Constantes.PRE_PATH_EVIDENCIA, Utl_Constantes.PRE_DEFAULT));
				intento.putExtra("descripcion", "");
				startActivity(intento);
			}
			
			break;

		case CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE:
			if(resultCode == Activity.RESULT_OK){
				Intent intento = new Intent(context, Act_Descripcion_Evidencia.class);
				intento.putExtra("idObra", idObra);
				intento.putExtra("tipo", true);
				intento.putExtra("tipoMultimedia", false);
				intento.putExtra("pathImagen", preferences.getString(Utl_Constantes.PRE_PATH_EVIDENCIA, Utl_Constantes.PRE_DEFAULT));
				intento.putExtra("descripcion", "");
				startActivity(intento);
			}
			break;
		default:
			break;
		}
		
		this.dismiss();
	}
	
	public void abrirCamara(boolean tipoEvidencia){
		Intent intento = null;
		File file = null;
		
		if(tipoEvidencia){
			file = Utl_Imagen.crearAchivoEvidencia(tipoEvidencia, idObra, context);
			intento = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intento.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
			startActivityForResult(intento, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		}else{
			file = Utl_Imagen.crearAchivoEvidencia(tipoEvidencia, idObra, context);
			intento = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
			intento.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
			intento.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, Utl_Constantes.VID_CALIDAD_MAXIMA);
			intento.putExtra(MediaStore.EXTRA_DURATION_LIMIT, Utl_Constantes.VID_DURACION_VIDEO_MAX);
			intento.putExtra(MediaStore.EXTRA_SIZE_LIMIT, Utl_Constantes.VID_LIMITE_TAMANO);
			intento.putExtra(MediaStore.EXTRA_SHOW_ACTION_ICONS, true);
			startActivityForResult(intento, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
		}
		
		edit = preferences.edit();
		edit.putString(Utl_Constantes.PRE_PATH_EVIDENCIA, file.getPath());
		edit.commit();
	}
	
}
