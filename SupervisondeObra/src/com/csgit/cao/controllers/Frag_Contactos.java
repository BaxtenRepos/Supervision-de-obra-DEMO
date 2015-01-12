package com.csgit.cao.controllers;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Base_ListaDirectorio;
import com.csgit.cao.business.Bus_Obras;
import com.csgit.cao.model.Mod_Directorio;

public class Frag_Contactos extends ListFragment implements
LoaderManager.LoaderCallbacks<List<Mod_Directorio>>{
	
	private Context context;
	private static long idObra;
	private static Bus_Obras bus_obras;
	private Adp_Base_ListaDirectorio adapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		context = getActivity();
		bus_obras = new Bus_Obras(context);
		idObra = getArguments().getLong("idObra");
		
		setEmptyText(context.getResources().getString(R.string.str_act_directorio_lista_vacia));
		adapter = new Adp_Base_ListaDirectorio(context);
		setListAdapter(adapter);
		setListShown(false);
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public Loader<List<Mod_Directorio>> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new DataListLoader(context);
	}

	@Override
	public void onLoadFinished(Loader<List<Mod_Directorio>> arg0,
			List<Mod_Directorio> arg1) {
		// TODO Auto-generated method stub
		adapter.setData(arg1);
		if(isResumed()){
			setListShown(true);
		}else{
			setListShownNoAnimation(true);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<Mod_Directorio>> arg0) {
		// TODO Auto-generated method stub
		adapter.setData(null);
	}
	
	private static class DataListLoader extends AsyncTaskLoader<List<Mod_Directorio>>{

		List<Mod_Directorio> mModels;
		
		public DataListLoader(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public List<Mod_Directorio> loadInBackground() {
			// TODO Auto-generated method stub
			List<Mod_Directorio> elementos = bus_obras.getDirectorioFromObra(idObra);
			return elementos;
		}
		
		@Override
		public void deliverResult(List<Mod_Directorio> data) {
			// TODO Auto-generated method stub
			if(isReset()){
				if(data != null){
					onReleaseResources(data);
				}
			}
			List<Mod_Directorio> oldApps = data;
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
		public void onCanceled(List<Mod_Directorio> data) {
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
		
		protected void onReleaseResources(List<Mod_Directorio> apps) {}
	}
	

}
