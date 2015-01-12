package com.csgit.cao.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.csgit.cao.R;
import com.csgit.cao.model.Mod_Opcion_Evidencia;

public class Adp_Opcion_Evidencia extends ArrayAdapter<Mod_Opcion_Evidencia>{

	private Context context;
	private int idResource;
	private List<Mod_Opcion_Evidencia> opcion;
	
	public Adp_Opcion_Evidencia(Context context, int idResource, List<Mod_Opcion_Evidencia> opcion){
		super(context, idResource, opcion);
		this.context = context;
		this.idResource = idResource;
		this.opcion = opcion;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		EvidenciaHolder evidenciaHolder;
		View view = convertView;
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			evidenciaHolder = new EvidenciaHolder();
			
			view = inflater.inflate(idResource, parent, false);
			evidenciaHolder.icon = (ImageView) view.findViewById(R.id.img_frag_dialog_evidencia_imagenes);
			evidenciaHolder.itemOpcion = (TextView) view.findViewById(R.id.txv_frag_dialog_evidencia_imagenes);
			
			view.setTag(evidenciaHolder);
		}else{
			evidenciaHolder = (EvidenciaHolder) view.getTag();
		}
		
		Mod_Opcion_Evidencia item = (Mod_Opcion_Evidencia) this.opcion.get(position);
		
		evidenciaHolder.icon.setImageDrawable(view.getResources().getDrawable(item.getIdResource()));
		evidenciaHolder.itemOpcion.setText(item.getOpcion());
		
		return view;
	}
	
	private static class EvidenciaHolder{
		TextView itemOpcion;
		ImageView icon;
	}
}
