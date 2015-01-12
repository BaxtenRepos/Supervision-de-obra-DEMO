package com.csgit.cao.controllers;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Expandable_ListaConceptos;
import com.csgit.cao.business.Bus_Conceptos;
import com.csgit.cao.model.Mod_ConceptoPadre;
import com.csgit.cao.model.communicationchannel.model.Concepto;
import com.csgit.cao.utils.Utl_SearchView;

@SuppressLint({ "InflateParams", "DefaultLocale" })
public class Frag_Conceptos extends Fragment implements OnQueryTextListener, OnCloseListener,
LoaderManager.LoaderCallbacks<List<Mod_ConceptoPadre>>{

	private static Context context;
	private static long idObra;
	private ExpandableListView expListView;
	private View empyView;
	private SearchView mSearchView;
	private String mCurFilter;
	
	private static Bus_Conceptos bus_conceptos;
	private Adp_Expandable_ListaConceptos adapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getActivity().supportInvalidateOptionsMenu();
		setHasOptionsMenu(true);
		context = getActivity();
		bus_conceptos = new Bus_Conceptos(context);
		
		Bundle bundle = getArguments();
		idObra = bundle.getLong("idObra");
		
		adapter = new Adp_Expandable_ListaConceptos(context);
		
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.lyt_frag_lista_general_conceptos, null);
		expListView = (ExpandableListView)root.findViewById(R.id.exp_lv_conceptos);
		expListView.setTextFilterEnabled(true);
		expListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				Concepto concepto_seleccionado = (Concepto) adapter.getChild(
						groupPosition, childPosition);

        		Intent intento = new Intent(getActivity(), Act_Reporte_Concepto.class);
				intento.putExtra("idObra", idObra);
			    intento.putExtra("idConcepto", concepto_seleccionado.getIdConcepto());
			    startActivity(intento);
				return true;
			}
		});
		empyView = inflater.inflate(R.layout.lyt_empy_view, null);
		expListView.setEmptyView(empyView);
		expListView.setAdapter(adapter);
		
		return root;
	}
		
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);

		mSearchView = new Utl_SearchView(context);
		mSearchView.setOnQueryTextListener(this);
		mSearchView.setOnCloseListener(this);
		mSearchView.setIconifiedByDefault(true);
		Act_Main.menuBuscarConceptos.setActionView(mSearchView);
	}
	
	@Override
	public Loader<List<Mod_ConceptoPadre>> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new DataListLoader(context, arg1);
	}

	@Override
	public void onLoadFinished(Loader<List<Mod_ConceptoPadre>> arg0,
			List<Mod_ConceptoPadre> arg1) {
		// TODO Auto-generated method stub
		adapter.setData(arg1);
		expListView.setAdapter(adapter);
	}

	@Override
	public void onLoaderReset(Loader<List<Mod_ConceptoPadre>> arg0) {
		// TODO Auto-generated method stub
		adapter.setData(null);
	}
	
	private static class DataListLoader extends AsyncTaskLoader<List<Mod_ConceptoPadre>>{

		private List<Mod_ConceptoPadre> mModels;
		private String filter = "";
		
		public DataListLoader(Context context, Bundle bundle) {
			super(context);
			// TODO Auto-generated constructor stub
			if(bundle != null)
				filter = bundle.getString("filter"); 
		}

		@Override
		public List<Mod_ConceptoPadre> loadInBackground() {
			// TODO Auto-generated method stub
			List<Mod_ConceptoPadre> aux = bus_conceptos.getLevelConceptos(idObra);
			if(filter == null || filter.isEmpty() || filter.endsWith("null"))
				return aux;
			
			List<Mod_ConceptoPadre> lista = new ArrayList<Mod_ConceptoPadre>();
			for (Mod_ConceptoPadre padre : aux) {
				for (Concepto hijo : padre.getChildren()) {
					if(hijo.getDescripcion().toLowerCase().contains(filter) ||
							hijo.getClave().toLowerCase().contains(filter) || 
							hijo.getDescripcion().toLowerCase().contains(filter)){
						lista.add(padre);
					}
				}
			}
			return lista;
		}
		
		@Override
		public void deliverResult(List<Mod_ConceptoPadre> data) {
			// TODO Auto-generated method stub
			if(isReset()){
				if(data != null){
					onReleaseResources(data);
				}
			}
			List<Mod_ConceptoPadre> oldApps = data;
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
		public void onCanceled(List<Mod_ConceptoPadre> data) {
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
		
		protected void onReleaseResources(List<Mod_ConceptoPadre> apps) {}
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

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getLoaderManager().restartLoader(0, null, this);
	}
}
