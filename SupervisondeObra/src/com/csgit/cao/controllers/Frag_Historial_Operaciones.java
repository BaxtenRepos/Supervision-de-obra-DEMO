package com.csgit.cao.controllers;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.widget.TextView;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Frag_Historial_Operaciones;
import com.csgit.cao.business.Bus_Conceptos;
import com.csgit.cao.model.communicationchannel.model.AditivasDeductivas;


public class Frag_Historial_Operaciones extends ListFragment implements 
LoaderManager.LoaderCallbacks<List<AditivasDeductivas>>{

	private static Context context;
	private static long idConcepto;
	private Bundle bundle = null;

	private static Bus_Conceptos bus_conceptos;
	private Adp_Frag_Historial_Operaciones adp_operaciones;
	private String unidadMedida;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		context = getActivity();
		
		bundle = getArguments();
		idConcepto = bundle.getLong("idConcepto");
		unidadMedida = bundle.getString("unidadMedida");
		
		bus_conceptos = new Bus_Conceptos(context);

		setEmptyText(context.getResources().getString(R.string.str_frag_historial_operaciones_vacio));
		
		TextView texto = new TextView(context);
		texto.setText(getResources().getString(R.string.str_adp_historial_cantidad_original) + 
				bus_conceptos.getCantidadTotalOriginal(idConcepto) + " " + unidadMedida);
		getListView().addHeaderView(texto);
		
		adp_operaciones = new Adp_Frag_Historial_Operaciones(context);
		setListAdapter(adp_operaciones);
		setListShown(false);
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<List<AditivasDeductivas>> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new DataListLoader(context);
	}

	@Override
	public void onLoaderReset(Loader<List<AditivasDeductivas>> arg0) {
		// TODO Auto-generated method stub
		adp_operaciones.setData(null);
	}

	@Override
	public void onLoadFinished(Loader<List<AditivasDeductivas>> arg0,
			List<AditivasDeductivas> arg1) {
		// TODO Auto-generated method stub
		adp_operaciones.setData(arg1);
		// The list should now be shown.
		if (isResumed()) {
			setListShown(true);
		} else {
			setListShownNoAnimation(true);
		}
	}

	public static class DataListLoader extends AsyncTaskLoader<List<AditivasDeductivas>>{

		List<AditivasDeductivas> mModels;

		public DataListLoader(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public List<AditivasDeductivas> loadInBackground() {
			// TODO Auto-generated method stub
			List<AditivasDeductivas> elementos = new ArrayList<AditivasDeductivas>();
			elementos = bus_conceptos.getOperacionesFromConcepto(idConcepto);

			return elementos;
		}

		@Override
		public void deliverResult(List<AditivasDeductivas> data) {
			// TODO Auto-generated method stub
			if (isReset()) {
				// An async query came in while the loader is stopped.  We
				// don't need the result.
				if (data != null) {
					onReleaseResources(data);
				}
			}
			List<AditivasDeductivas> oldApps = data;
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

		public void onCanceled(List<AditivasDeductivas> data) {
			super.onCanceled(data);

			// At this point we can release the resources associated with 'apps'
			// if needed.
			onReleaseResources(data);
		};

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

		protected void onReleaseResources(List<AditivasDeductivas> apps) {}


	}
}
