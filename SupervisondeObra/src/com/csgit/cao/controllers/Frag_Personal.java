package com.csgit.cao.controllers;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Frag_Personal;
import com.csgit.cao.business.Bus_Personal;
import com.csgit.cao.model.communicationchannel.model.ReporteDiario;

public class Frag_Personal extends ListFragment implements
LoaderManager.LoaderCallbacks<List<ReporteDiario>>{

	private Context context;
	
	private static long idObra;
	
	private Adp_Frag_Personal adapter;
	private static Bus_Personal bus_personal;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		context = getActivity();
		
		Bundle bundle = getArguments();
		idObra = bundle.getLong("idObra");
		
		adapter = new Adp_Frag_Personal(context);
		bus_personal = new Bus_Personal(context);
		
		setEmptyText(getResources().getString(R.string.str_frag_personal_empy));
		setListAdapter(adapter);
		setListShown(false);
		
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		ReporteDiario repDiario = (ReporteDiario) l.getAdapter().getItem(position);
		
		Intent intent = new Intent(getActivity(), Act_Personal.class);
		Bundle bundle = new Bundle();
		
		bundle.putLong("idObra", idObra);
		bundle.putLong("idReporteDiario", repDiario.getIdReporteDiario());
		bundle.putBoolean("nuevoReporte", false);
		intent.putExtra("datos", bundle);
		startActivity(intent);	
	}
	
	@Override
	public Loader<List<ReporteDiario>> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new DataListLoader(context, arg1);
	}

	@Override
	public void onLoadFinished(Loader<List<ReporteDiario>> arg0,
			List<ReporteDiario> arg1) {
		// TODO Auto-generated method stub
		adapter.setData(arg1);
		if(isResumed()){
			setListShown(true);
		}else{
			setListShownNoAnimation(true);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<ReporteDiario>> arg0) {
		// TODO Auto-generated method stub
		adapter.setData(null);
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getLoaderManager().restartLoader(0, null, this);
	}
	
	private static class DataListLoader extends AsyncTaskLoader<List<ReporteDiario>>{

		private List<ReporteDiario> mModels;
		
		public DataListLoader(Context context, Bundle bundle) {
			super(context);
			// TODO Auto-generated constructor stub 
		}

		@Override
		public List<ReporteDiario> loadInBackground() {
			// TODO Auto-generated method stub
			return bus_personal.getReporteDiarioPersonal(idObra);
		}
		
		@Override
		public void deliverResult(List<ReporteDiario> data) {
			// TODO Auto-generated method stub
			if(isReset()){
				if(data != null){
					onReleaseResources(data);
				}
			}
			List<ReporteDiario> oldApps = data;
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
		public void onCanceled(List<ReporteDiario> data) {
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
		
		protected void onReleaseResources(List<ReporteDiario> apps) {}
	}	
}
