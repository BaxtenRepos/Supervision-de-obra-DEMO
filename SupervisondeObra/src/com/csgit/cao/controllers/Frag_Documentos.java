package com.csgit.cao.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Documentos;
import com.csgit.cao.business.Bus_Documentos;
import com.csgit.cao.model.Mod_Documentos;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Menus;
import com.csgit.cao.utils.Utl_SearchView;

@SuppressLint("DefaultLocale")
public class Frag_Documentos extends ListFragment implements OnQueryTextListener, OnCloseListener, 
LoaderManager.LoaderCallbacks<List<Mod_Documentos>>{
	
	private static Context context;
	private SearchView mSearchView;
	
	private static Bus_Documentos bus_documentos;
	private static Adp_Documentos adapter;
	
	private static int positionDelete;
	private static long idObra;
	private String mCurFilter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getActivity().supportInvalidateOptionsMenu();
		setHasOptionsMenu(true);
		
		context = getActivity();
		adapter = new Adp_Documentos(context);
		bus_documentos = new Bus_Documentos(context);
		
		idObra = getArguments().getLong("idObra");
		
		setEmptyText(context.getResources().getString(R.string.str_act_documentos_empy));
		setListAdapter(adapter);
		setListShown(false);
		
		OnItemLongClickListener listener = new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				positionDelete = position;
				showDialog();
				return true;
			}
		};
		
		getListView().setOnItemLongClickListener(listener);
		getLoaderManager().initLoader(0, null, this);
	}
		
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		
		Act_Main.menuBuscarDocumentos = Utl_Menus.configItem(menu, Act_Main.menuBuscarDocumentos, 
				Utl_Constantes.MENU_BUSCAR_DOCUMENTOS_ID);
		mSearchView = new Utl_SearchView(context);
		mSearchView.setOnQueryTextListener(this);
		mSearchView.setOnCloseListener(this);
		mSearchView.setIconifiedByDefault(true);
		Act_Main.menuBuscarDocumentos.setActionView(mSearchView);
	}
	
	private void showDialog(){
		DialogFragment dialog = Frag_Dialog_Msg_Buttons.newInstance(R.string.str_act_documentos_title,
				R.string.str_act_documentos_dialog_delete_mensaje);
		dialog.show(getFragmentManager(), "dialog");
	}
	
	public static void deleteDocumento(){
		Mod_Documentos documento = adapter.getItem(positionDelete);
		if(documento.getIsSync() == Utl_Constantes.REG_NO_SINC){
			int val = bus_documentos.deleteDocumento(documento.getIdDocumento(), 
					documento.getPathDocumento());
			if(val != 0){
				for(int i=0; i < adapter.getCount();i++){
					if(adapter.getItem(positionDelete).getIdDocumento() == 
							documento.getIdDocumento()){
						adapter.remove(documento);
						adapter.notifyDataSetChanged();
						Toast.makeText(context,
								context.getResources().getString(R.string.str_toast_documento_eliminado),
								Toast.LENGTH_SHORT).show();
//						getActivity().finish();	
						break;
					}
				}
			}
		}
		
//		if(docSeleccionado.getIsSync() == Constantes.REG_NO_SINC){
//			int val = bus_documentos.deleteDocumento(docSeleccionado.getIdDocumento(), 
//					docSeleccionado.getPathDocumento());
//			if(val != 0){
//				
//				for(int i=0; i < adapter.getCount();i++){
//					if(listaDocumentos.get(i).getIdDocumento() == docSeleccionado.getIdDocumento()){
//						listaDocumentos.remove(i);
//						adapter.notifyDataSetChanged();
//						Toast.makeText(getApplicationContext(),
//								getResources().getString(R.string.str_toast_documento_eliminado),
//								Toast.LENGTH_SHORT).show();
//						finish();	
//						break;
//					}
//				}
//			}else{
//				Toast.makeText(getApplicationContext(),
//						getResources().getString(R.string.str_toast_error_eliminar),
//						Toast.LENGTH_SHORT).show();
//			}
//		}else{
//			Toast.makeText(getApplicationContext(),
//					getResources().getString(R.string.str_toast_not_delete_documento),
//					Toast.LENGTH_SHORT).show();
//		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Mod_Documentos docuemnto = (Mod_Documentos) l.getAdapter().getItem(position);
		abrirDocumento(docuemnto.getPathDocumento(), docuemnto.getMimeType());
	}
	
	private void abrirDocumento(String pathDocumento, String mimeType){
		File file = new File(pathDocumento);
		Uri uri = Uri.fromFile(file);
		
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri, mimeType);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	@Override
	public Loader<List<Mod_Documentos>> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new DataListLoader(context, arg1);
	}

	@Override
	public void onLoadFinished(Loader<List<Mod_Documentos>> arg0,
			List<Mod_Documentos> arg1) {
		// TODO Auto-generated method stub
		adapter.setData(arg1);
		if(isResumed()){
			setListShown(true);
		}else{
			setListShownNoAnimation(true);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<Mod_Documentos>> arg0) {
		// TODO Auto-generated method stub
		adapter.setData(null);
	}
	
	private static class DataListLoader extends AsyncTaskLoader<List<Mod_Documentos>>{

		private List<Mod_Documentos> mModels;
		private String filter = "";
		
		public DataListLoader(Context context, Bundle bundle) {
			super(context);
			// TODO Auto-generated constructor stub
			if(bundle != null)
				filter = bundle.getString("filter"); 
		}

		@Override
		public List<Mod_Documentos> loadInBackground() {
			// TODO Auto-generated method stub
			List<Mod_Documentos> aux =  bus_documentos.getDocumentosFromObra(idObra);
			if(filter == null || filter.isEmpty() || filter.equals("null"))
				return aux;
			
			List<Mod_Documentos> elementos = new ArrayList<Mod_Documentos>();
			for (Mod_Documentos mod_Documentos : aux) {
				if(mod_Documentos.getNombreDocumento().toLowerCase().contains(filter))
					elementos.add(mod_Documentos);
			}
			return elementos;
		}
		
		@Override
		public void deliverResult(List<Mod_Documentos> data) {
			// TODO Auto-generated method stub
			if(isReset()){
				if(data != null){
					onReleaseResources(data);
				}
			}
			List<Mod_Documentos> oldApps = data;
			mModels = data;
			
			if(isStarted()){
				super.deliverResult(data);
			}
			
			if(oldApps != null){
				onReleaseResources(oldApps);
			}
		}
		
		@Override
		protected void onStartLoading() {
			// TODO Auto-generated method stub
			if(mModels != null){
				deliverResult(mModels);
			}
			
			if(takeContentChanged() || mModels == null){
				forceLoad();
			}
		}
		
		@Override
		protected void onStopLoading() {
			// TODO Auto-generated method stub
			cancelLoad();
		}
		
		@Override
		public void onCanceled(List<Mod_Documentos> data) {
			// TODO Auto-generated method stub
			super.onCanceled(data);
			onReleaseResources(data);
		}
		
		@Override
		protected void onReset() {
			// TODO Auto-generated method stub
			super.onReset();
			onStopLoading();
			
			if(mModels != null){
				onReleaseResources(mModels);
				mModels = null;
			}
		}
		
		protected void onReleaseResources(List<Mod_Documentos> apps) {}
	}

	@Override
	public boolean onClose() {
		// TODO Auto-generated method stub
		if(!TextUtils.isEmpty(mSearchView.getQuery())){
			mSearchView.setQuery(null, true);
		}
		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		String newFilter = !TextUtils.isEmpty(newText) ? newText : null;
		if(mCurFilter == null && newFilter == null){
			return true;
		}
		if(mCurFilter != null && mCurFilter.equals(newFilter)){
			return true;
		}
		mCurFilter = newFilter;
		Bundle bundle =  new Bundle();
		bundle.putString("filter", mCurFilter);
		getLoaderManager().restartLoader(0, bundle, this);
		return true;
	}
	
}
