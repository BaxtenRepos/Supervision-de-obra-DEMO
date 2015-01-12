package com.csgit.cao.controllers;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Evidencia_Gallery;
import com.csgit.cao.business.Bus_Obras;
import com.csgit.cao.model.Mod_Multimedia;
import com.csgit.cao.utils.Utl_Constantes;

@SuppressLint("InflateParams")
public class Frag_Evidencias extends Fragment{

	public static Adp_Evidencia_Gallery adp_gallery;
	private GridView gridView;
	private Long idObra;
	private obtenerImagenes getImagenes;
	public static List<Mod_Multimedia> lista_imagenes;
	private RelativeLayout relativeLayout;
	private Context context;
//	private List<Integer> itemsDelete = new ArrayList<Integer>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActivity().supportInvalidateOptionsMenu();
		setHasOptionsMenu(true);
		context = getActivity();
		lista_imagenes = new ArrayList<Mod_Multimedia>();
		Bundle bundle = getArguments();
		idObra = bundle.getLong("idObra");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.lyt_act_galeria_evidencia, null);
		gridView = ((GridView) root.findViewById(R.id.grid_view_evidencia));
		relativeLayout = ((RelativeLayout) root.findViewById(R.id.lyt_default_galeria));
		//el número de columnas se calculará en función del tamaño de pantalla y la posición
		boolean bigScreen = (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
		
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			if (bigScreen){
				gridView.setNumColumns(4);
			}else{
				gridView.setNumColumns(3);
			}
		}else{if (bigScreen){
				gridView.setNumColumns(3);
			}else{
				gridView.setNumColumns(2);
			}
		}
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), Act_Descripcion_Evidencia.class);
				intent.putExtra("idMultimedia", lista_imagenes.get(position).getIdMultimedia());
				intent.putExtra("idObra", idObra);
				intent.putExtra("tipo", false);
				boolean tipoMultimadia = false;
				if (lista_imagenes.get(position).getTipo() == Utl_Constantes.TIPO_IMAGEN) {
					tipoMultimadia = true;
				}
				intent.putExtra("tipoMultimedia", tipoMultimadia);
				intent.putExtra("pathImagen", lista_imagenes.get(position).getPath());
				intent.putExtra("descripcion", lista_imagenes.get(position).getDescripcion());
				startActivity(intent);
//				finish();
			}
		});
		
//		gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
//		gridView.setMultiChoiceModeListener(new MultiChoiceModeListener() {
//			
//			@Override
//			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//			
//			@Override
//			public void onDestroyActionMode(ActionMode mode) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//				// TODO Auto-generated method stub
//				MenuInflater inflater = mode.getMenuInflater();
//		        inflater.inflate(R.menu.menu_act_desc_evidencia, menu);
//		        return true;
//			}
//			
//			@Override
//			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//				// TODO Auto-generated method stub
//				switch (item.getItemId()) {
//	            case R.id.menu_act_evidencia_borrar_evidencia:
////	            	lista_imagenes.remove(itemsDelete);
//	            	
//	            	itemsDelete.clear();
//	                mode.finish(); // Action picked, so close the CAB
//	                return true;
//	            default:
//	                return false;
//				}
//			}
//			
//			@Override
//			public void onItemCheckedStateChanged(ActionMode mode, int position,
//					long id, boolean checked) {
//				// TODO Auto-generated method stub
//				if(checked){
//					itemsDelete.add(position);
//				}else{
//					for (int i = 0; i < itemsDelete.size(); i++) {
//						if(itemsDelete.get(i) == position){
//							itemsDelete.remove(i);
//						}
//					}
//				}
//			}
//		});
		
		return root;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getImagenes = new obtenerImagenes();
		getImagenes.execute();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			getActivity().finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		// TODO Auto-generated method stub
//		switch (keyCode) {
//		case KeyEvent.KEYCODE_BACK:
//			finish();
//			break;
//
//		default:
//			break;
//		}
//		return true;
//	}
	
	private class obtenerImagenes extends AsyncTask<Long, Void, Void>{
		
		Bus_Obras buss_obras;
		private ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			buss_obras = new Bus_Obras(context);
			dialog = new ProgressDialog(getActivity());
			dialog.setMessage(getResources().getString(R.string.str_progressdialog_cargar_galeria));
			dialog.setCancelable(false);
			dialog.show();
		}
		
		@Override
		protected Void doInBackground(Long... params) {
			// TODO Auto-generated method stub
			try {
				lista_imagenes = buss_obras.getMultimediaFromObra(idObra);
				if(lista_imagenes.size()==0){
					relativeLayout.setVisibility(View.VISIBLE);
					gridView.setVisibility(View.GONE);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.e("Error Galeria", e.getMessage());
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			adp_gallery = new Adp_Evidencia_Gallery(context, lista_imagenes);
			gridView.setAdapter(adp_gallery);
			
			if(dialog.isShowing())
				dialog.dismiss();
		}
	}
}
