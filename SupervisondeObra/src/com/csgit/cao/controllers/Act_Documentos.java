package com.csgit.cao.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Documentos;
import com.csgit.cao.business.Bus_Documentos;
import com.csgit.cao.model.Mod_Documentos;
import com.csgit.cao.utils.Utl_Constantes;

public class Act_Documentos extends FragmentActivity{

//	private static final int ID_DIALOG_DELETE = 200;
//	private ListView listView;
//	private Adp_Documentos adapter;
//	private List<Mod_Documentos> listaDocumentos;
//	private Context context;
//	private Bus_Documentos bus_documentos;
	private long idObra;
//	private RelativeLayout relative;
//	private Mod_Documentos docSeleccionado;
	
	
	public void doPositiveClick(){
		Frag_Documentos.deleteDocumento();
	}
	
	public void doNegativeClick(){
		
	}
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.lyt_act_documentos);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		idObra = getIntent().getLongExtra("idObra", idObra);
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Fragment fragment = new Frag_Documentos();
		Bundle bundle = new Bundle();
		bundle.putLong("idObra", idObra);
		fragment.setArguments(bundle);
		ft.replace(R.id.fl_act_documentos, fragment);
		ft.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
		
//		context = getApplicationContext(); 
//		listView = (ListView) findViewById(R.id.lv_act_documentos);
//		relative = (RelativeLayout) findViewById(R.id.lyt_default_documentos);
//		
//		idObra = getIntent().getExtras().getLong("idObra");
//		
//		listView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				Mod_Documentos docuemnto = (Mod_Documentos) parent.getAdapter().getItem(position);
//				abrirDocumento(docuemnto.getPathDocumento(), docuemnto.getMimeType());
//			}
//		});
//		
//		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
//
//			@SuppressWarnings("deprecation")
//			@Override
//			public boolean onItemLongClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				docSeleccionado = (Mod_Documentos) parent.getAdapter().getItem(position);
//				showDialog(ID_DIALOG_DELETE);
//				return true;
//			}
//		});
//		
//		ObtenerDocumentos obtenerDocumentos = new ObtenerDocumentos();
//		obtenerDocumentos.execute();
//	}	
//	
//	@Override
////	protected Dialog onCreateDialog(int id) {
////		// TODO Auto-generated method stub
////		switch (id) {
////		case ID_DIALOG_DELETE:
////			AlertDialog.Builder dialogBulder = new AlertDialog.Builder(this);
////			dialogBulder.setCancelable(false);
////			dialogBulder.setMessage(getResources().getString(R.string.str_act_documentos_dialog_delete_mensaje));
////			dialogBulder.setPositiveButton(getResources().getString(R.string.str_act_documentos_dialog_delete_si)
////					, new DialogInterface.OnClickListener() {
////						
////						@Override
////						public void onClick(DialogInterface dialog, int which) {
////							// TODO Auto-generated method stub
////							deleteDocumento();
////							dialog.dismiss();
////						}
////					});
////			dialogBulder.setNegativeButton(getResources().getString(R.string.str_act_documentos_dialog_delete_no)
////					, new DialogInterface.OnClickListener() {
////						
////						@Override
////						public void onClick(DialogInterface dialog, int which) {
////							// TODO Auto-generated method stub
////							dialog.dismiss();
////						}
////					});
////			return dialogBulder.create();
////
////		default:
////			break;
////		}
////		
////		return null;
////	}
////	
////	public void deleteDocumento(){
////		if(docSeleccionado.getIsSync() == Constantes.REG_NO_SINC){
////			int val = bus_documentos.deleteDocumento(docSeleccionado.getIdDocumento(), 
////					docSeleccionado.getPathDocumento());
////			if(val != 0){
////				for(int i=0; i < listaDocumentos.size();i++){
////					if(listaDocumentos.get(i).getIdDocumento() == docSeleccionado.getIdDocumento()){
////						listaDocumentos.remove(i);
////						adapter.notifyDataSetChanged();
////						Toast.makeText(getApplicationContext(),
////								getResources().getString(R.string.str_toast_documento_eliminado),
////								Toast.LENGTH_SHORT).show();
////						finish();	
////						break;
////					}
////				}
////			}else{
////				Toast.makeText(getApplicationContext(),
////						getResources().getString(R.string.str_toast_error_eliminar),
////						Toast.LENGTH_SHORT).show();
////			}
////		}else{
////			Toast.makeText(getApplicationContext(),
////					getResources().getString(R.string.str_toast_not_delete_documento),
////					Toast.LENGTH_SHORT).show();
////		}
////	}
////	
////	@Override
////	public boolean onOptionsItemSelected(MenuItem item) {
////		// TODO Auto-generated method stub
////		switch (item.getItemId()) {
////		case android.R.id.home:
////			finish();
////			
////			return true;
////
////		default:
////			break;
////		}
////		
////		return super.onOptionsItemSelected(item);
////	}
////	
////	private void abrirDocumento(String pathDocumento, String mimeType){
////		File file = new File(pathDocumento);
////		Uri uri = Uri.fromFile(file);
////		
////		Intent intent = new Intent(Intent.ACTION_VIEW);
////		intent.setDataAndType(uri, mimeType);
////		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////		context.startActivity(intent);
////	}
////	
////	class ObtenerDocumentos extends AsyncTask<Void, Void, Void>{
//
//		@Override
//		protected void onPreExecute() {
//			// TODO Auto-generated method stub
//			super.onPreExecute();
//			
//			listaDocumentos =  new ArrayList<Mod_Documentos>();
//			bus_documentos = new Bus_Documentos(context);
//		}
//		
//		@Override
//		protected Void doInBackground(Void... params) {
//			// TODO Auto-generated method stub
//			
//			listaDocumentos = bus_documentos.getDocumentosFromObra(idObra);
//			if(listaDocumentos.size() == 0){
//				relative.setVisibility(View.VISIBLE);
//				listView.setVisibility(View.GONE);
//			}
//						
//			return null;
//		}
//		
//		@Override
//		protected void onPostExecute(Void result) {
//			// TODO Auto-generated method stub
//			super.onPostExecute(result);
//			
////			adapter =  new Adp_Documentos(context, R.layout.lyt_adp_documentos, listaDocumentos);
//			listView.setAdapter(adapter);
//		}
//		
//	}
	
}
