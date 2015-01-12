package com.csgit.cao.controllers;

import java.util.ArrayList;
import java.util.List;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Dialog_Agregar_Reporte;
import com.csgit.cao.business.Bus_ReporteDiario;
import com.csgit.cao.model.Mod_Opciones_Nuevo_Reporte;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Frag_Dialog_AgregarReporte extends DialogFragment{

	private Context context;
	private long idObra;
	
	private List<Mod_Opciones_Nuevo_Reporte> lista;
	private Adp_Dialog_Agregar_Reporte adapter;
	
	public Frag_Dialog_AgregarReporte(Context context, long idObra){
		this.context = context;
		this.idObra = idObra;
	}
	
	private void llenarOpciones(){
		String[] aux = getResources().getStringArray(R.array.opciones_agragar_reporte);
		lista.add(new Mod_Opciones_Nuevo_Reporte(aux[0]));
		lista.add(new Mod_Opciones_Nuevo_Reporte(aux[1]));
		lista.add(new Mod_Opciones_Nuevo_Reporte(aux[2]));
		lista.add(new Mod_Opciones_Nuevo_Reporte(aux[3]));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.lyt_frag_dialog_agregar_reporte, container);
		getDialog().setTitle(R.string.str_frag_dialog_agregar_reporte_title);
		

		lista = new ArrayList<Mod_Opciones_Nuevo_Reporte>();
		llenarOpciones();
		
		ListView listView = (ListView) view.findViewById(R.id.lv_frag_dialog_agregar_reporte);
		adapter = new Adp_Dialog_Agregar_Reporte(this.context, R.layout.lyt_adp_dialog_agregar_reporte, lista);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = null;
				Bundle bundle = new Bundle();
				switch (position) {
				case 0:// Maquinaria y Personal
					intent = new Intent(context, Act_Maquinaria.class);
					break;
				case 1:// Personal
					intent = new Intent(context, Act_Personal.class);
					break;
				case 2:// Notas
					intent = new Intent(context, Act_Notas.class);
					break;
				case 3:// Estimacion
					intent = new Intent(context, Act_Estimacion.class);
					break;

				default:
					break;
				}
				
//				if(intent != null){
					bundle.putLong("idObra", idObra);
					bundle.putBoolean("nuevoReporte", true);
					intent.putExtra("datos", bundle);
					startActivity(intent);
//				}else{
//					Toast.makeText(context,
//							context.getResources().getString(R.string.str_toast_nuevo_reporte_diario),
//							Toast.LENGTH_SHORT).show();
//				}
				
				
				getDialog().dismiss();
			}
		});
		
		return view;
	}
}
