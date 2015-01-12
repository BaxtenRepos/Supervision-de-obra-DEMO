package com.csgit.cao.adapters;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.csgit.cao.R;
import com.csgit.cao.model.communicationchannel.model.ReporteDiario;

@SuppressLint("ViewHolder")
public class Adp_Base_ListaMaquinaria extends BaseAdapter{
	List<ReporteDiario> Lista;
	Context Contexto;
	LayoutInflater inflater = null;

	public Adp_Base_ListaMaquinaria(Context contexto,List<ReporteDiario> lista) {
		// TODO Auto-generated constructor stub
		super();
		this.Lista = lista;
		this.Contexto = contexto;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;

		if (vi == null)
			inflater = (LayoutInflater) Contexto.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

		vi = inflater.inflate(R.layout.lyt_adp_maquinaria, null);

		TextView descrpcion = (TextView) vi.findViewById(R.id.txv_DescMaqui); // 
		TextView sincr = (TextView) vi.findViewById(R.id.txv_fechaMaqui); 
		String sincronizado = "Sincronizado";
		sincr.setTextColor(new Color().GREEN);
		if(Lista.get(position).getClave()==0){
			sincronizado = "No Sincronizado";
			sincr.setTextColor(new Color().RED);
		}		
		descrpcion.setText("Reporte con Fecha: " + Lista.get(position).getFechaReporteDiario());
		sincr.setText(sincronizado);



		return vi;
	}

}
