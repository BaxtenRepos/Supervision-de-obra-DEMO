package com.csgit.cao.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Base_ListaEstimaciones;
import com.csgit.cao.business.Bus_Estimacion;
import com.csgit.cao.model.communicationchannel.model.Estimacion;

@SuppressLint("InflateParams")
public class Frag_Estimacion extends Fragment{

	private Context context;
	private ListView listaEstimaciones;
	private Long idObra;
	private List<Estimacion> lista;
	private Adp_Base_ListaEstimaciones adaptador;
	private Bus_Estimacion busEstimacion;
	private List<Estimacion> aux;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActivity().supportInvalidateOptionsMenu();
		setHasOptionsMenu(true);	
		
		context =  getActivity();
		
		Bundle bundle = getArguments();
		idObra = bundle.getLong("idObra");
		
		busEstimacion = new Bus_Estimacion(context);
		lista = busEstimacion.getEstimaciones(idObra);
		
		List<Estimacion> val = filtrarEstimaciones(lista);
		lista.clear();
		lista = val;
	
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	private List<Estimacion> filtrarEstimaciones(List<Estimacion> estima){
		List<Estimacion> estimaciones = estima;
		List<Estimacion> listaAux = new ArrayList<Estimacion>();
		List<Long> idEstima = new ArrayList<Long>();
		
		for (Estimacion estimacion : estimaciones) {
			idEstima.add(estimacion.getEstimacion());
		}
		HashSet<Long> hashSet = new HashSet<Long>(idEstima);
		idEstima.clear();
		idEstima.addAll(hashSet);
		
		for (int i = 0; i < idEstima.size(); i++) {
			for (int j = 0; j < estimaciones.size(); j++) {
				if(idEstima.get(i) == estimaciones.get(j).getEstimacion()){
					listaAux.add(estimaciones.get(j));
					break;
				}
			}
		}
		
		return listaAux;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		lista.clear();
		aux = busEstimacion.getEstimaciones(idObra);
		List<Estimacion> es = filtrarEstimaciones(aux);
		for (Estimacion estimacion : es) {
			lista.add(estimacion);
		}
//		for (Estimacion estimacion : aux) {
//			lista.add(estimacion);
//		} 
		adaptador.notifyDataSetChanged();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.lyt_frag_estimacion, null);
		listaEstimaciones = (ListView)root.findViewById(R.id.lyt_listados_estimaciones);
		
		adaptador = new Adp_Base_ListaEstimaciones(getActivity().getBaseContext(),lista);
		listaEstimaciones.setAdapter(adaptador);
		listaEstimaciones.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view,
        			int position, long id) {
        		// TODO Auto-generated method stub        		
        		Intent intent = new Intent(getActivity(), Act_Estimacion.class);
        		Bundle bundle1 = new Bundle();
        		Estimacion estimacion = (Estimacion) parent.getAdapter().getItem(position);
        		
        		bundle1.putLong("idObra", idObra);
        		bundle1.putLong("idEstimacion", estimacion.getEstimacion());
        		bundle1.putLong("numero", estimacion.getNumero());
        		bundle1.putString("fInicio", estimacion.getFechaInicio());
        		bundle1.putString("fTermino", estimacion.getFechaFin());
        		bundle1.putBoolean("nuevoReporte", false);
        		
        		intent.putExtra("datos", bundle1);

				startActivity(intent);
        	}
		});
		
		return root;
	}
	
	
}