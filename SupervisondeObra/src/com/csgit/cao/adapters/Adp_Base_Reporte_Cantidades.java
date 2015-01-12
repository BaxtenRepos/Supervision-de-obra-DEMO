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
import com.csgit.cao.model.Mod_Repo_Maquinaria_Cat_Maquinaria;

@SuppressLint({ "InflateParams", "ViewHolder" })
public class Adp_Base_Reporte_Cantidades extends BaseAdapter{

	List<Mod_Repo_Maquinaria_Cat_Maquinaria> Lista;
	Context Contexto;
	LayoutInflater inflater = null;


	public Adp_Base_Reporte_Cantidades(Context context, List<Mod_Repo_Maquinaria_Cat_Maquinaria> lista) {
		this.Contexto = context;
		this.Lista = lista;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Lista.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return Lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
	
		if (vi == null)
			inflater = (LayoutInflater) Contexto
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

		vi = inflater.inflate(R.layout.lyt_adp_lv_maquinaria, null);

		TextView descrpcion = (TextView) vi.findViewById(R.id.txv_NombreCategoria_cantidades_maqui); //
		TextView cantidad = (TextView) vi.findViewById(R.id.txv_Cantidad_maquinaria);

		descrpcion.setText(Lista.get(position).getNombre());
		cantidad.setText(Lista.get(position).getCantidad() + "");

		return vi;
	}
	
	public String desCorto(int position){
		String cadena = "";
		if (Lista.get(position).getNombre().length() >= 100) {
			cadena = Lista.get(position).getNombre().substring(0, 99) + "...";
		}else{
			cadena = Lista.get(position).getNombre();
		}
		return cadena;
	}


}