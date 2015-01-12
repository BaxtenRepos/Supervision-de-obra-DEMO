package com.csgit.cao.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Frag_Obras;
import com.csgit.cao.business.Bus_Obras;
import com.csgit.cao.model.Mod_Obras;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Menus;
import com.csgit.cao.utils.Utl_SearchView;

@SuppressLint("DefaultLocale")
public class Frag_Obras extends ListFragment implements OnQueryTextListener, OnCloseListener,
LoaderManager.LoaderCallbacks<List<Mod_Obras>>{

	private Context context;
	private FragmentTransaction ft;
	private SearchView mSearchView;
	private String mCurFilter;
	
	private static Bus_Obras bus_obras;
	private Adp_Frag_Obras adapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
		context = getActivity();
		bus_obras =  new Bus_Obras(context);
		ft = getFragmentManager().beginTransaction();
		
		setEmptyText(context.getResources().getString(R.string.str_frag_obras_empy));
		adapter = new Adp_Frag_Obras(context);
		setListAdapter(adapter);
		setListShown(false);
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Mod_Obras obraSeleccionada = (Mod_Obras) l.getAdapter().getItem(position);
		
		Long idObra = obraSeleccionada.getIdObra();
		String nombreObra = obraSeleccionada.getNombre();
		
		Frag_ViewPager viewPager = new Frag_ViewPager();
		Bundle bundle = new Bundle();
		bundle.putLong("idObra", idObra);
		bundle.putString("nombreObra", nombreObra);
		viewPager.setArguments(bundle);
		
		ft.replace(R.id.content_frame, viewPager);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		Act_Main.menuBuscarObras = Utl_Menus.configItem(menu, Act_Main.menuBuscarObras, 
				Utl_Constantes.MENU_BUSCAR_OBRAS_ID);
		mSearchView = new Utl_SearchView(context);
		mSearchView.setOnQueryTextListener(this);
		mSearchView.setOnCloseListener(this);
		mSearchView.setIconifiedByDefault(true);
		Act_Main.menuBuscarObras.setActionView(mSearchView);
		
	}
	
	@Override
	public Loader<List<Mod_Obras>> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new DataListLoader(context, arg1);
	}

	@Override
	public void onLoadFinished(Loader<List<Mod_Obras>> arg0,
			List<Mod_Obras> arg1) {
		// TODO Auto-generated method stub
		Collections.sort(arg1);
		adapter.setData(arg1);
		if(isResumed()){
			setListShown(true);
		}else{
			setListShownNoAnimation(true);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<Mod_Obras>> arg0) {
		// TODO Auto-generated method stub
		adapter.setData(null);
	}
	
	private static class DataListLoader extends AsyncTaskLoader<List<Mod_Obras>>{

		private List<Mod_Obras> mModels;
		private String filter = "";
		
		public DataListLoader(Context context, Bundle bundle) {
			super(context);
			// TODO Auto-generated constructor stub
			if(bundle != null)
				filter = bundle.getString("filter"); 
		}

		@Override
		public List<Mod_Obras> loadInBackground() {
			// TODO Auto-generated method stub
			List<Mod_Obras> aux = bus_obras.getObras();
			if(filter == null || filter.isEmpty() || filter.equals("null"))
				return aux;
			
			List<Mod_Obras> elementos = new ArrayList<Mod_Obras>();
			for (Mod_Obras mod_Obras : aux) {
				if(mod_Obras.getNombre().toLowerCase().contains(filter) || 
						mod_Obras.getNumeroContrato().toLowerCase().contains(filter) ||
						mod_Obras.getEstadoFisicoString().toLowerCase().contains(filter) ||
						mod_Obras.getEstadoFinancieroString().toLowerCase().contains(filter) || 
						mod_Obras.getEntidadFederativa().toLowerCase().contains(filter)){
					elementos.add(mod_Obras);
				}
			}
			return elementos;
		}
		
		@Override
		public void deliverResult(List<Mod_Obras> data) {
			// TODO Auto-generated method stub
			if(isReset()){
				if(data != null){
					onReleaseResources(data);
				}
			}
			List<Mod_Obras> oldApps = data;
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
		public void onCanceled(List<Mod_Obras> data) {
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
		
		protected void onReleaseResources(List<Mod_Obras> apps) {}
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
		Bundle bun = new Bundle();
		bun.putString("filter", mCurFilter);
		getLoaderManager().restartLoader(0, bun, this);
		return true;
	}
	
	
}