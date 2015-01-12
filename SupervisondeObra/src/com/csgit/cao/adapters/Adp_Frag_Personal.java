package com.csgit.cao.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.csgit.cao.R;
import com.csgit.cao.model.communicationchannel.model.ReporteDiario;

public class Adp_Frag_Personal extends ArrayAdapter<ReporteDiario>{

	private Context context;
	
	public Adp_Frag_Personal(Context context) {
		super(context, android.R.layout.simple_list_item_2);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public void setData(List<ReporteDiario> data){
		clear();
		if(data != null){
			for (ReporteDiario repDiario : data) {
				add(repDiario);				
			}
		}
	}
	
	@SuppressWarnings("static-access")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		PersonalHolder personalHolder;

		if (vi == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.lyt_adp_maquinaria, parent , false);
			personalHolder = new PersonalHolder();
			
			personalHolder.descrpcion = (TextView) vi.findViewById(R.id.txv_DescMaqui); 
			personalHolder.sinc = (TextView) vi.findViewById(R.id.txv_fechaMaqui);
			
			vi.setTag(personalHolder);
		}else{
			personalHolder = (PersonalHolder) vi.getTag();
		}

		ReporteDiario reporte = getItem(position);
		
		if(reporte.getClave() == 0){
			personalHolder.sinc.setTextColor(new Color().RED);
			personalHolder.sinc.setText(context.getResources().getString(R.string.str_frag_personal_no_sincronizado));
		}else{
			personalHolder.sinc.setTextColor(new Color().GREEN);
			personalHolder.sinc.setText(context.getResources().getString(R.string.str_frag_personal_sincronizado));
		}
		personalHolder.descrpcion.setText("Reporte con Fecha: " + reporte.getFechaReporteDiario());
		
		return vi;
	}
	
	private static class PersonalHolder{
		TextView descrpcion; 
		TextView sinc;
	}
	
}
