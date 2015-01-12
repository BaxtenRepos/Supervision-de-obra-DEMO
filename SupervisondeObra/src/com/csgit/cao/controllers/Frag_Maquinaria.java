package com.csgit.cao.controllers;

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
import com.csgit.cao.adapters.Adp_Base_ListaMaquinaria;
import com.csgit.cao.business.Bus_ReporteDiario;
import com.csgit.cao.model.communicationchannel.model.ReporteDiario;

@SuppressLint("InflateParams")
public class Frag_Maquinaria extends Fragment{
	
	private ListView listaM;
	private Long idObra;
	private List<ReporteDiario> lista;
	private Adp_Base_ListaMaquinaria adaptador;
	
	private Bus_ReporteDiario busReporte;
	
	private List<ReporteDiario> aux;
	
	private Context context;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActivity().supportInvalidateOptionsMenu();
		setHasOptionsMenu(true);
		
		context = getActivity();
		
		Bundle bundle = getArguments();
		idObra = bundle.getLong("idObra");
		    
		busReporte = new Bus_ReporteDiario(context);
		lista = busReporte.getReporteDiarioMaquinaria(getActivity().getContentResolver(), idObra);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		lista.clear();
		aux = busReporte.getReporteDiarioMaquinaria(getActivity().getContentResolver(), idObra);
		for (ReporteDiario reporteDiario : aux) {
			lista.add(reporteDiario);
		} 
		adaptador.notifyDataSetChanged();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		listaM = (ListView)getView().findViewById(R.id.lyt_listados_maquinaria);
		
		adaptador = new Adp_Base_ListaMaquinaria(getActivity().getBaseContext(),lista);
        listaM.setAdapter(adaptador);
        listaM.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view,
        			int position, long id) {
        		// TODO Auto-generated method stub        
        		ReporteDiario reporteDiario = (ReporteDiario) parent.getAdapter().getItem(position);
        		
        		Intent intent = new Intent(getActivity(), Act_Maquinaria.class);
        		Bundle bundle = new Bundle();
        		bundle.putLong("idObra", idObra);
        		bundle.putLong("idReporteDiario", reporteDiario.getIdReporteDiario());
        		bundle.putBoolean("nuevoReporte", false);
        		intent.putExtra("datos", bundle);
				startActivity(intent);
        	}
		});
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return (View) inflater.inflate(R.layout.lyt_frag_lista_maquinaria, null);
	}
}