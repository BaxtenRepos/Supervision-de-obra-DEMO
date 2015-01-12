package com.csgit.cao.adapters;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.csgit.cao.R;
import com.csgit.cao.model.Mod_Repo_Personal_Cat_Personal;

public class Adp_Reporte_Personal extends BaseAdapter{

//	private List<Mod_ReporteDiario_Cantidades> lista;
	private List<Mod_Repo_Personal_Cat_Personal> lista;
	
	private Context context;
	private LayoutInflater inflater = null;
	
//	public Adp_Reporte_Personal(Context context, List<Mod_ReporteDiario_Cantidades> lista){
	public Adp_Reporte_Personal(Context context, List<Mod_Repo_Personal_Cat_Personal> lista){
		this.context = context;
		this.lista = lista;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		
		if(vi == null){
			inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.lyt_adp_scrollview, null);
		}
		
		TextView nomCategoria = (TextView) vi.findViewById(R.id.txv_NombreCategoria);
		nomCategoria.setText(lista.get(position).getNombre());
		
		TextView cantidadM = (TextView) vi.findViewById(R.id.txv_CantidadM);
		cantidadM.setText(context.getResources().getString(R.string.str_act_personal_cantidad));
		
		TextView cantidadMaqui = (TextView) vi.findViewById(R.id.txv_CantidadMaqui);
		cantidadMaqui.setText(String.valueOf(lista.get(position).getCantidad()));
		
		return vi;
	}

}
