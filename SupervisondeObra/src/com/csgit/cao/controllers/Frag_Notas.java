package com.csgit.cao.controllers;

import java.util.List;

import android.annotation.SuppressLint;
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
import com.csgit.cao.adapters.Adp_Frag_Notas;
import com.csgit.cao.business.Bus_Notas;
import com.csgit.cao.model.communicationchannel.model.Notas;

@SuppressLint("InflateParams")
public class Frag_Notas extends ListFragment implements
LoaderManager.LoaderCallbacks<List<Notas>>{
	
	private Context context;
//	private EditText edt_nuevaNota;
	
	private static long idObra;
	
	private static Bus_Notas bus_notas; 
	private Adp_Frag_Notas adapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
		
		context = getActivity();
		
		Bundle bundle = getArguments();
		idObra = bundle.getLong("idObra");
		
		bus_notas = new Bus_Notas(context);
		adapter = new Adp_Frag_Notas(context);
		
		setEmptyText(context.getResources().getString(R.string.str_frag_notas_empy));
		setListAdapter(adapter);
		setListShown(false);
		
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Notas nota = (Notas) l.getAdapter().getItem(position);
		
		Intent intent = new Intent(context, Act_Notas.class);
		Bundle bundle = new Bundle();
		
		bundle.putLong("idObra", idObra);
		bundle.putBoolean("nuevoReporte", false);
		bundle.putLong("idRepoNotas", nota.getIdRepoNotas());
		bundle.putString("titulo", nota.getTitulo());
		bundle.putString("descripcion", nota.getDescripcion());
		intent.putExtra("datos", bundle);
		
		startActivity(intent);
	}
	
	@Override
	public Loader<List<Notas>> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new DataListLoader(context, arg1);
	}

	@Override
	public void onLoadFinished(Loader<List<Notas>> arg0, List<Notas> arg1) {
		// TODO Auto-generated method stub
		adapter.setData(arg1);
		if(isResumed()){
			setListShown(true);
		}else{
			setListShownNoAnimation(true);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<Notas>> arg0) {
		// TODO Auto-generated method stub
		adapter.setData(null);
	}
	
	private static class DataListLoader extends AsyncTaskLoader<List<Notas>>{

		private List<Notas> mModels;
		
		public DataListLoader(Context context, Bundle bundle) {
			super(context);
			// TODO Auto-generated constructor stub 
		}

		@Override
		public List<Notas> loadInBackground() {
			// TODO Auto-generated method stub
			return bus_notas.getNotas(idObra);
		}
		
		@Override
		public void deliverResult(List<Notas> data) {
			// TODO Auto-generated method stub
			if(isReset()){
				if(data != null){
					onReleaseResources(data);
				}
			}
			List<Notas> oldApps = data;
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
		public void onCanceled(List<Notas> data) {
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
		
		protected void onReleaseResources(List<Notas> apps) {}
	}

	 @Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getLoaderManager().restartLoader(0, null, this);
	}
	  
}


//public class Frag_Notas extends Fragment{
//
//	private Long idObra;
//	private ListView listaNotas;
//	private Adp_Base_ListaNotas adaptador;
//	private List<Notas> lista;
//	private List<Notas> aux;
//	private EditText edt_nuevaNota;
//	private Bus_Notas bus_notas;
//
//	
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		getActivity().supportInvalidateOptionsMenu();
//		setHasOptionsMenu(true);
//		
//		Bundle bundle = getArguments();
//		idObra = bundle.getLong("idObra");
//		
//		bus_notas = new Bus_Notas(getActivity().getContentResolver());
//		lista = bus_notas.getNotas(idObra);
//	}
//	
//	@Override
//	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//		// TODO Auto-generated method stub
//		super.onCreateOptionsMenu(menu, inflater);
//	}
//	
//	@Override
//	public void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		limpiarLista();
//	}
//	
//	
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onActivityCreated(savedInstanceState);
//		
//		adaptador = new Adp_Base_ListaNotas(getActivity().getApplicationContext(),lista);
//        listaNotas.setAdapter(adaptador);
//		
//        edt_nuevaNota.setOnEditorActionListener(new OnEditorActionListener() {
//			   
//			@Override
//			public boolean onEditorAction(TextView v, int actionId,
//					KeyEvent event) {
//				  boolean handled = false;
//			        if (actionId == EditorInfo.IME_ACTION_DONE) {
//			        	if(!edt_nuevaNota.getText().toString().equals("")){
//			        		
//			        		Notas nota = new Notas();
//							nota.setDescripcion(edt_nuevaNota.getText().toString());
//							nota.setFecha(Utl_Fechas.getFecha());
//							nota.setTitulo("");							
//							bus_notas.insertNota(idObra, nota);
//							
//							limpiarLista();
//							edt_nuevaNota.setText("");
//				            handled = true;
//			        	}
//			        }
//			        return handled;
//			}
//		});
//		     
//        listaNotas.setOnItemClickListener(new OnItemClickListener() {
//        	@Override
//        	public void onItemClick(AdapterView<?> parent, View view,
//        			int position, long id) {
//        		// TODO Auto-generated method stub    
//        		Intent intent = new Intent(getActivity(), Act_Notas.class);
//        		Bundle bundle = new Bundle();
//        		
//        		bundle.putLong("idObra", idObra);
//        		bundle.putBoolean("nuevoReporte", false);
//        		bundle.putLong("idRepoNotas", lista.get(position).getIdRepoNotas());
//        		bundle.putString("titulo", lista.get(position).getTitulo());
//        		bundle.putString("descripcion", lista.get(position).getDescripcion());
//        		
//        		intent.putExtra("datos", bundle);
//				startActivity(intent);
//        	}
//		});
//	}
//	
//	public void limpiarLista(){
//		lista.clear();
//		aux = bus_notas.getNotas(idObra); 
//		for (Notas reporteDiario : aux) {
//			lista.add(reporteDiario);
//		}
//		adaptador.notifyDataSetChanged();
//	}
//
//	@SuppressLint("InflateParams")
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.lyt_frag_lista_notas, null);
//		listaNotas = (ListView)root.findViewById(R.id.lyt_listados_notas);
//		edt_nuevaNota = (EditText) root.findViewById(R.id.edt_nueva_nota);
//		return root;
//	}
//}
