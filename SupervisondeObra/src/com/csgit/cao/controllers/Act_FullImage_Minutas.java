package com.csgit.cao.controllers;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.csgit.cao.R;
import com.csgit.cao.business.Bus_Obras;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Imagen;

public class Act_FullImage_Minutas extends FragmentActivity{
	
	private long idMinutas;
//	private long idObra;
	private String path;
//	private String fecha;
	private int puedeEliminar;
	private Bus_Obras bus_obra;
	
	private Context context;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_act_fullimage_minutas);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		context = getApplicationContext();
		
		bus_obra = new Bus_Obras(context);
		
		idMinutas = getIntent().getExtras().getLong("idMinuta");
//		idObra = getIntent().getExtras().getLong("idObra");
		path = getIntent().getExtras().getString("pathImagen");
//		fecha = getIntent().getExtras().getString("fechaMinuta");
		puedeEliminar = getIntent().getExtras().getInt("sync");
		
		ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
		Bitmap bitmap = null;
   	 	bitmap = Utl_Imagen.escalarImagen(path, Utl_Constantes.IMG_Width, Utl_Constantes.IMG_Height);
   	 	imageView.setImageBitmap(bitmap);
		
   	 	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu_act_minutas, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
			
		case R.id.menu_act_minuta_borrar:
			if(puedeEliminar == Utl_Constantes.REG_NO_SINC){
				int val = bus_obra.deleteMinuta(idMinutas, path);
				if(val != 0){
// checar
//					for(int i =0;i < Act_Galeria_Minutas.lista_imagenes.size();i++){
//						if(Act_Galeria_Minutas.lista_imagenes.get(i).getIdMinutas() == idMinutas){
//							Act_Galeria_Minutas.lista_imagenes.remove(i);
//							Act_Galeria_Minutas.adp_gallery.notifyDataSetChanged();
//							
//							Toast.makeText(getApplicationContext(),
//									getResources().getString(R.string.str_toast_minuta_eliminada),
//									Toast.LENGTH_SHORT).show();
//							finish();
//							break;
//						}
//					}
					
					for(int i =0;i < Frag_Minutas.lista_imagenes.size();i++){
						if(Frag_Minutas.lista_imagenes.get(i).getIdMinutas() == idMinutas){
							Frag_Minutas.lista_imagenes.remove(i);
							Frag_Minutas.adp_gallery.notifyDataSetChanged();

							Toast.makeText(getApplicationContext(),
									getResources().getString(R.string.str_toast_minuta_eliminada),
									Toast.LENGTH_SHORT).show();
							finish();
							break;
						}
				}
					
				}else{
					Toast.makeText(getApplicationContext(),
							getResources().getString(R.string.str_toast_error_eliminar),
							Toast.LENGTH_SHORT).show();
				}
			}else{
				Toast.makeText(getApplicationContext(), 
						getResources().getString(R.string.str_toast_not_delete_minuta),
						Toast.LENGTH_SHORT).show();
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
			finish();
			break;

		default:
			break;
		}
		return true;
	}
	
}
