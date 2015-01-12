package com.csgit.cao.adapters;

import java.util.List;

import com.csgit.cao.R;
import com.csgit.cao.model.Mod_Opciones_Nuevo_Reporte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Adp_Dialog_Agregar_Reporte extends ArrayAdapter<Mod_Opciones_Nuevo_Reporte>{

	private Context context;
	private List<Mod_Opciones_Nuevo_Reporte> objects;
	
	public Adp_Dialog_Agregar_Reporte(Context context, int resource, List<Mod_Opciones_Nuevo_Reporte> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.objects = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		OpcionesHolder opcionHolder;
		View view = convertView;
		if(view == null){
			LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			opcionHolder = new OpcionesHolder();
			
			view = inflate.inflate(R.layout.lyt_adp_dialog_agregar_reporte, parent, false);
			opcionHolder.opcion = (TextView) view.findViewById(R.id.txv_dialog_agregar_opciones);
			
			view.setTag(opcionHolder);
		}else{
			opcionHolder = (OpcionesHolder) view.getTag();
		}
		
		Mod_Opciones_Nuevo_Reporte item = objects.get(position);
		
		opcionHolder.opcion.setText(item.getOpcion());
		
		return view;
	}
	
	private static class OpcionesHolder{
		TextView opcion;
	}
	
}
