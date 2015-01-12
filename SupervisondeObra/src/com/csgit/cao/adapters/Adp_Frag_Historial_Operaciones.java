package com.csgit.cao.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.csgit.cao.R;
import com.csgit.cao.model.communicationchannel.model.AditivasDeductivas;
import com.csgit.cao.utils.Utl_Constantes;

public class Adp_Frag_Historial_Operaciones extends ArrayAdapter<AditivasDeductivas>{

	private LayoutInflater inflater;
	
	public Adp_Frag_Historial_Operaciones(Context context) {
		super(context, android.R.layout.simple_list_item_2);
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setData(List<AditivasDeductivas> data) {
        clear();
        if (data != null) {
            for (AditivasDeductivas appEntry : data) {
                add(appEntry);
            }
        }
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		if(convertView == null){
			view = inflater.inflate(R.layout.lyt_adp_frag_historial_operaciones, parent, false);
		}else{
			view = convertView;
		}
		AditivasDeductivas item = getItem(position);
		if(item.getTipoOperacion() == Utl_Constantes.OPERACION_ADITIVA){
			((ImageView) view.findViewById(R.id.img_adp_historial_operaciones)).
			setImageResource(R.drawable.ic_action_aditiva);
		}else{
			((ImageView) view.findViewById(R.id.img_adp_historial_operaciones)).
			setImageResource(R.drawable.ic_action_deductiva);
		}
		((TextView) view.findViewById(R.id.txv_historial_cantidad)).setText(String.valueOf(item.getCantidad()));
		((TextView) view.findViewById(R.id.txv_historia_fecha)).setText(item.getFecha());
		
		return view;
	}
}
