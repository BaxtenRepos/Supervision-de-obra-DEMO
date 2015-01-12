package com.csgit.cao.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.csgit.cao.R;
import com.csgit.cao.business.Bus_Evidencia;
import com.csgit.cao.business.Bus_ReporteDiario;
import com.csgit.cao.dao.ConstantesBD;
import com.csgit.cao.model.Mod_Multimedia;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Fechas;
import com.csgit.cao.utils.Utl_Imagen;

public class Act_Descripcion_Evidencia extends FragmentActivity{

	private Context context;

	private Bus_Evidencia bus_evidencia;
	private Bus_ReporteDiario bus_reporteDiario;
	
	private EditText edt_descripcion;
	private ImageView img_imagen;
	private VideoView vid_video;
	
	private long idMultimedia;
	private long idObra;
	private boolean tipo; // true --> nuevo , false --> existente
	private boolean tipoMultimedia;
	private String path;
	private String descripcion;
	
    private int mDstWidth;
    private int mDstHeight;
    private boolean puedeEliminar = false;
	
    long idReporteDiario;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.lyt_act_descripcion_evidencia);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		context = getBaseContext();
		bus_evidencia = new Bus_Evidencia(context);
		bus_reporteDiario = new Bus_ReporteDiario(context);
		
		mDstWidth = getResources().getDimensionPixelSize(R.dimen.destination_width);
        mDstHeight = getResources().getDimensionPixelSize(R.dimen.destination_height);
		
		edt_descripcion = (EditText)findViewById(R.id.edt_act_desc_evidencia_descripcion);
		img_imagen = (ImageView) findViewById(R.id.img_act_desc_evidencia_imagen);
		vid_video = (VideoView) findViewById(R.id.vdo_videoView_video);
		
		idMultimedia = getIntent().getExtras().getLong("idMultimedia");
		idObra = getIntent().getExtras().getLong("idObra");
		tipo = getIntent().getExtras().getBoolean("tipo");
		path = getIntent().getExtras().getString("pathImagen");
		descripcion = getIntent().getExtras().getString("descripcion");
		tipoMultimedia = getIntent().getExtras().getBoolean("tipoMultimedia");
		
		if(!tipo){
			idReporteDiario = bus_evidencia.getIdReporteDiarioFromEvidencia(idMultimedia);
			puedeEliminar = !bus_reporteDiario.reporteDiarioIsSync(idReporteDiario);
		}else{
			puedeEliminar = true;
		}
		
		mostrarInfo(tipoMultimedia);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu_act_desc_evidencia, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			if(tipo){
				guardarInformacion(true);
				finish();
			}else{
				guardarInformacion(false);
				finish();
			}	
			break;
			
		case R.id.menu_act_evidencia_borrar_evidencia:
			
			if(!tipo){// nuevo
				if(puedeEliminar){
					int i = bus_evidencia.deleteEvidencia(idMultimedia, idReporteDiario);
					if(i != 0){
//						checar
//						for(int j = 0;j < Act_Galeria_Evidencia.lista_imagenes.size();j++){
//							if(Act_Galeria_Evidencia.lista_imagenes.get(j).getIdMultimedia() == idMultimedia){
//								Act_Galeria_Evidencia.lista_imagenes.remove(j);
//								Act_Galeria_Evidencia.adp_gallery.notifyDataSetChanged();
//								Toast.makeText(getApplicationContext(),
//										getResources().getString(R.string.str_toast_eviencia_eliminada),
//										Toast.LENGTH_SHORT).show();
//								finish();				
//								break;
//							}
//						}
						
						for(int j = 0;j < Frag_Evidencias.lista_imagenes.size(); j++){
							if(Frag_Evidencias.lista_imagenes.get(j).getIdMultimedia() == idMultimedia){
								Frag_Evidencias.lista_imagenes.remove(j);
								Frag_Evidencias.adp_gallery.notifyDataSetChanged();
								Toast.makeText(getApplicationContext(),
										getResources().getString(R.string.str_toast_eviencia_eliminada),
										Toast.LENGTH_SHORT).show();
								finish();				
								break;
							}
						}
						
						
					}
				}else{
					Toast.makeText(getApplicationContext(),
							getResources().getString(R.string.str_toast_not_delete_eviencia),
							Toast.LENGTH_SHORT).show();
				}
				
			}else{
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.str_toast_eviencia_eliminada),
						Toast.LENGTH_SHORT).show();
				finish();
			}
			
			
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if(tipo){
				guardarInformacion(true);
				finish();	
			}else{
				guardarInformacion(false);
				finish();
			}	
			break;

		default:
			break;
		}
		return true;
	}
	
	private void mostrarInfo(boolean tipoMultimedia){
		DisplayMetrics dm;
		if(tipoMultimedia){
			Bitmap bit = Utl_Imagen.badImagen(path, mDstWidth, mDstHeight);
			edt_descripcion.setText(descripcion);
			img_imagen.setVisibility(View.VISIBLE);
			vid_video.setVisibility(View.GONE);
			img_imagen.setScaleType(ScaleType.CENTER_CROP);
			img_imagen.setImageBitmap(bit);
		}else{
			MediaController mc = new MediaController(this);	
			dm = new DisplayMetrics(); 
			this.getWindowManager().getDefaultDisplay().getMetrics(dm); 
			int height = dm.heightPixels; 
			int width = dm.widthPixels; 
			vid_video.setMinimumWidth(width); 
			vid_video.setMinimumHeight(height);
			vid_video.setMediaController(mc);
			vid_video.setVideoPath(path);
			vid_video.start();
			img_imagen.setVisibility(View.GONE);
			vid_video.setVisibility(View.VISIBLE);
			edt_descripcion.setText(descripcion);
		}
	}
	
	private void guardarInformacion(boolean bandera){
		if(bandera){// nuevo
			String nombre = Utl_Imagen.getNombreImagen(path);
			String formato = Utl_Imagen.getExtencionEvidencia(nombre);
			String descripcion = edt_descripcion.getText().toString();
			
			Mod_Multimedia multimedia = new Mod_Multimedia();
			if(tipoMultimedia){
				Utl_Imagen.procesarImagen(path);
				multimedia.setTipo(Utl_Constantes.TIPO_IMAGEN);
			}else{
//				procesarVideo(path);
				multimedia.setTipo(Utl_Constantes.TIPO_FILE);	
			}
			multimedia.setFormato(formato);
			multimedia.setPath(path);
			multimedia.setDescripcion(descripcion);
			multimedia.setSync(Utl_Constantes.REG_NO_SINC);
			multimedia.setFecha(Utl_Fechas.getFecha());
				
			if(bus_evidencia.insertarEvidencia(context, multimedia, idObra))
				Toast.makeText(context, getResources().getString(R.string.str_toast_act_desc_guardar_ok), Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(context, getResources().getString(R.string.str_toast_act_desc_guardar_error), Toast.LENGTH_SHORT).show();
			
		}else if(!(descripcion.trim().equals(edt_descripcion.getText().toString().trim())) ){// existente
			
			ContentValues values1 = new ContentValues();
			values1.put(ConstantesBD.ColMultimedia[4], edt_descripcion.getText().toString());
			
			if(bus_evidencia.actualizarEvidencia(context.getContentResolver(), values1, idMultimedia))
				Toast.makeText(context, getResources().getString(R.string.str_toast_act_desc_guardar_ok), Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(context, getResources().getString(R.string.str_toast_act_desc_guardar_error), Toast.LENGTH_SHORT).show();
		}
	}

	
//	private void procesarVideo(String rutaVideo){
//		
//		setCommand(Constantes.COMMAND1 + rutaVideo + Constantes.COMMAND2 + rutaVideo);
////		setCommand(Constantes.COMMAND1 + rutaVideo + Constantes.COMMAND2 + "/storage/emulated/0/Pictures/Iuyet/Obras/1/Evidencia/resultPos.mp4");
//		setProgressBarVisibility(false);
//		runTranscoing();
//		
//	}
//	
//	@Override
//	public void transcodingFinished() {
//		// TODO Auto-generated method stub
//		
//	}
	
}
