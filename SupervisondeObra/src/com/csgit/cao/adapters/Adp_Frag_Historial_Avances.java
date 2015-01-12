package com.csgit.cao.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.csgit.cao.R;
import com.csgit.cao.model.Mod_Historial_Avances;

public class Adp_Frag_Historial_Avances extends ArrayAdapter<Mod_Historial_Avances>{
	
	private Context context;
	
	public Adp_Frag_Historial_Avances(Context context) {
		super(context, android.R.layout.simple_list_item_2);
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	public void setData(List<Mod_Historial_Avances> data) {
        clear();
        if (data != null) {
            for (Mod_Historial_Avances appEntry : data) {
                add(appEntry);
            }
        }
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		Holder holder;
		
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			holder = new Holder();
			view = inflater.inflate(R.layout.lyt_adp_historial_avances, parent, false);
			holder.tv_cantidad = (TextView) view.findViewById(R.id.txv_historial_avances_cantidad_valor);
			holder.tv_unidadMedida = (TextView)view.findViewById(R.id.txv_historial_avances_unidadM_valor);
			holder.tv_fecha = (TextView)view.findViewById(R.id.txv_historial_avances_fecha);
			
			view.setTag(holder);
		}else{
			holder = (Holder) view.getTag();
		}
		Mod_Historial_Avances item = getItem(position);
		holder.tv_cantidad.setText(String.valueOf(item.getCantidadAvance()));
		holder.tv_unidadMedida.setText(item.getUnidadMedida());
		holder.tv_fecha.setText(item.getFecha());
		
		return view;
	}
	
	private class Holder{
		TextView tv_cantidad;
		TextView tv_unidadMedida;
		TextView tv_fecha;
	}
}
