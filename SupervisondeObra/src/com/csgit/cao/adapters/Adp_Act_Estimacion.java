package com.csgit.cao.adapters;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.csgit.cao.R;
import com.csgit.cao.model.Mod_Estimacion_Concepto;

@SuppressLint({ "InflateParams", "ViewHolder" })
public class Adp_Act_Estimacion extends BaseAdapter {

	List<Mod_Estimacion_Concepto> Lista;
	Context context;

	public Adp_Act_Estimacion(Context context,
			List<Mod_Estimacion_Concepto> lista) {
		this.context = context;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		final EstimacionHolder estimacionHolder;
		
		if(vi == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.lyt_adp_adp_act_estimacion, parent, false);
			estimacionHolder = new EstimacionHolder();
			
			estimacionHolder.descrpcion = (TextView) vi.findViewById(R.id.txv_NombreCategoria_cantidades);
			estimacionHolder.cantidad = (TextView) vi.findViewById(R.id.txv_Cantidad);
			estimacionHolder.vermas = (TextView) vi.findViewById(R.id.txv_vermas_adp_lista);
			
			vi.setTag(estimacionHolder);
		}else{
			estimacionHolder = (EstimacionHolder) vi.getTag();
		}
		final Mod_Estimacion_Concepto item = (Mod_Estimacion_Concepto) getItem(position);
			
		if (item.getDescripcion().length() >= 100) {
			estimacionHolder.descrpcion.setText(desCorto(item.getDescripcion()));
			estimacionHolder.vermas.setVisibility(View.VISIBLE);
		} else {
			estimacionHolder.descrpcion.setText(item.getDescripcion());
		}

		estimacionHolder.cantidad.setText(Lista.get(position).getCantidadAutorizada() + "");
		estimacionHolder.vermas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (estimacionHolder.vermas.getText().toString().equals("Ver mas...")) {
					estimacionHolder.descrpcion.setText(item.getDescripcion());
					estimacionHolder.vermas.setText("Ocultar..");
				} else if (estimacionHolder.vermas.getText().toString().equals("Ocultar..")) {
					estimacionHolder.descrpcion.setText(desCorto(item.getDescripcion()));
					estimacionHolder.vermas.setText("Ver mas...");
				}
			}
		});
		
		return vi;
	}
	
	public String desCorto(String descrip){
		String cadena = "";
		if (descrip.length() >= 100) {
			cadena = descrip.substring(0, 99) + "...";
		}else{
			cadena = descrip;
		}
		return cadena;
	}

	private static class EstimacionHolder{
		TextView descrpcion;
		TextView cantidad;
		TextView vermas;
	}
}