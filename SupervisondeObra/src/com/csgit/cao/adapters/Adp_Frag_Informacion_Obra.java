package com.csgit.cao.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.csgit.cao.R;
import com.csgit.cao.model.Mod_Informacion_Obra;

public class Adp_Frag_Informacion_Obra extends ArrayAdapter<Mod_Informacion_Obra>{

	private Context context;
	private List<Mod_Informacion_Obra> listItems;
	private int resource;
	private Typeface fontTittle;
	private Typeface fontLabel;
	
	public Adp_Frag_Informacion_Obra(Context context, int resource,
			List<Mod_Informacion_Obra> listItems) {
		super(context, resource, listItems);
		// TODO Auto-generated constructor stub
		
		this.context = context;
		this.listItems = listItems;
		this.resource = resource;
		fontTittle = Typeface.createFromAsset(this.context.getAssets(), "FRADMCN.TTF");
		fontLabel = Typeface.createFromAsset(this.context.getAssets(), "FRAMDCN.TTF");
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		InforHolder infoHolder;
		View  view = convertView;
		if(view == null){
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			infoHolder = new InforHolder();
			
			view = inflater.inflate(resource, parent, false);
			infoHolder.title = (TextView) view.findViewById(R.id.txv_frag_info_obra_title);
			infoHolder.label = (TextView) view.findViewById(R.id.txv_frag_info_obra_label);
			infoHolder.valor = (TextView) view.findViewById(R.id.txv_frag_info_obra_valor);
			
			view.setTag(infoHolder);
		}else{
			infoHolder = (InforHolder) view.getTag();
		}
		
		Mod_Informacion_Obra infoObra = (Mod_Informacion_Obra) this.listItems.get(position);
		
		if(infoObra.getLabel() == null){
			infoHolder.title.setVisibility(View.VISIBLE);
			infoHolder.label.setVisibility(View.GONE);
			infoHolder.valor.setVisibility(View.GONE);
			
			infoHolder.title.setText(listItems.get(position).getTitulo());
			infoHolder.title.setTypeface(fontTittle);
			
		}else{
			infoHolder.title.setVisibility(View.GONE);
			infoHolder.label.setVisibility(View.VISIBLE);
			infoHolder.valor.setVisibility(View.VISIBLE);
			
			infoHolder.label.setText(listItems.get(position).getLabel());
			infoHolder.label.setTypeface(fontLabel);
			
			infoHolder.valor.setText(listItems.get(position).getValor());
		}
		
		return view;
	}
	
	private static class InforHolder{
		TextView label;
		TextView valor;
		TextView title;
	}
}
