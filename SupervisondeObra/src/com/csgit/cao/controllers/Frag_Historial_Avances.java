package com.csgit.cao.controllers;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Frag_Historial_Avances;
import com.csgit.cao.business.Bus_Conceptos;
import com.csgit.cao.model.Mod_Historial_Avances;


public class Frag_Historial_Avances extends ListFragment implements 
LoaderManager.LoaderCallbacks<List<Mod_Historial_Avances>>{
	
	private static Context context;
	private static long idConcepto;
	private Bundle bundle = null;
	
	private static Bus_Conceptos bus_conceptos;
	private Adp_Frag_Historial_Avances adp_avances;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		context = getActivity();
		bundle = getArguments();
		idConcepto = bundle.getLong("idConcepto");
		bus_conceptos = new Bus_Conceptos(context);
		
		setEmptyText(context.getResources().getString(R.string.str_frag_historial_operaciones_vacio));

		adp_avances = new Adp_Frag_Historial_Avances(context);
		setListAdapter(adp_avances);
		setListShown(false);
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public Loader<List<Mod_Historial_Avances>> onCreateLoader(int arg0,
			Bundle arg1) {
		// TODO Auto-generated method stub
		return new DataListLoader(context);
	}

	@Override
	public void onLoadFinished(Loader<List<Mod_Historial_Avances>> arg0,
			List<Mod_Historial_Avances> arg1) {
		// TODO Auto-generated method stub
		adp_avances.setData(arg1);
		// The list should now be shown.
		if (isResumed()) {
			setListShown(true);
		} else {
			setListShownNoAnimation(true);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<Mod_Historial_Avances>> arg0) {
		// TODO Auto-generated method stub
		adp_avances.setData(null);
	}
	
	public static class DataListLoader extends AsyncTaskLoader<List<Mod_Historial_Avances>>{

		List<Mod_Historial_Avances> mModels;
		
		public DataListLoader(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public List<Mod_Historial_Avances> loadInBackground() {
			// TODO Auto-generated method stub
			List<Mod_Historial_Avances> elementos = new ArrayList<Mod_Historial_Avances>();
			elementos = bus_conceptos.getHistorialAvancesFromConcepto(idConcepto);

			return elementos;
		}
		
		@Override
		public void deliverResult(List<Mod_Historial_Avances> data) {
			// TODO Auto-generated method stub
			if (isReset()) {
				// An async query came in while the loader is stopped.  We
				// don't need the result.
				if (data != null) {
					onReleaseResources(data);
				}
			}
			List<Mod_Historial_Avances> oldApps = data;
			mModels = data;

			if (isStarted()) {
				// If the Loader is currently started, we can immediately
				// deliver its results.
				super.deliverResult(data);
			}

			// At this point we can release the resources associated with
			// 'oldApps' if needed; now that the new result is delivered we
			// know that it is no longer in use.
			if (oldApps != null) {
				onReleaseResources(oldApps);
			}
		}
		
		@Override
		protected void onStartLoading() {
			// TODO Auto-generated method stub
			if (mModels != null) {
				// If we currently have a result available, deliver it
				// immediately.
				deliverResult(mModels);
			}


			if (takeContentChanged() || mModels == null) {
				// If the data has changed since the last time it was loaded
				// or is not currently available, start a load.
				forceLoad();
			}
		}
		
		@Override
		protected void onStopLoading() {
			// TODO Auto-generated method stub
			cancelLoad();
		}
		
		@Override
		public void onCanceled(List<Mod_Historial_Avances> data) {
			// TODO Auto-generated method stub
			super.onCanceled(data);

			// At this point we can release the resources associated with 'apps'
			// if needed.
			onReleaseResources(data);
		}
		
		@Override
		protected void onReset() {
			// TODO Auto-generated method stub
			super.onReset();

			// Ensure the loader is stopped
			onStopLoading();

			// At this point we can release the resources associated with 'apps'
			// if needed.
			if (mModels != null) {
				onReleaseResources(mModels);
				mModels = null;
			}
		}
		
		protected void onReleaseResources(List<Mod_Historial_Avances> apps) {}
		
	}

}
