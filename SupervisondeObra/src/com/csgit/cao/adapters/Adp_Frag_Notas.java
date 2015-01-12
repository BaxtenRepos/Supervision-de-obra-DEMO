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
import com.csgit.cao.model.communicationchannel.model.Notas;

public class Adp_Frag_Notas extends ArrayAdapter<Notas>{

	private Context context;
	
	public Adp_Frag_Notas(Context context) {
		super(context, android.R.layout.simple_list_item_2);
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	public void setData(List<Notas> data){
		clear();
		if(data != null){
			for (Notas nota : data) {
				add(nota);				
			}
		}
	}
	
	@SuppressWarnings("static-access")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		NotaHolder notaHolder;
		
		if (vi == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.lyt_adp_maquinaria, parent, false);
			notaHolder = new NotaHolder();
			
			notaHolder.descrpcion = (TextView) vi.findViewById(R.id.txv_DescMaqui); 
			notaHolder.fecha = (TextView) vi.findViewById(R.id.txv_fechaMaqui);
			
			vi.setTag(notaHolder);
		}else{
			notaHolder = (NotaHolder) vi.getTag();
		}
		
		Notas nota = getItem(position);
		
		if(nota.getTitulo().equals("")){
			notaHolder.descrpcion.setText(nota.getDescripcion());
		}else{
			notaHolder.descrpcion.setText(nota.getTitulo());
		}
		
		notaHolder.descrpcion.setTextColor(new Color().BLACK);
		notaHolder.fecha.setTextColor(new Color().BLUE);
		notaHolder.fecha.setText(nota.getFecha());		
		
		return vi;
	}
	
	private static class NotaHolder{
		TextView descrpcion; 
		TextView fecha;
	}
	
}

//@SuppressLint({ "ViewHolder", "InflateParams" })
//public class Adp_Frag_Notas extends BaseAdapter{
//	List<Notas> Lista;
//	Context Contexto;
//	LayoutInflater inflater = null;
//	
//	
//	public Adp_Frag_Notas(Context contexto,List<Notas> lista) {
//		// TODO Auto-generated constructor stub
//		super();
//		this.Lista = lista;
//		this.Contexto = contexto;
//	}
//	
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return Lista.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		// TODO Auto-generated method stub
//		return Lista.get(position);
//	}
//
//	@Override
//	public long getItemId(int position) {
//		// TODO Auto-generated method stub
//		return position;
//	}
//
//	@SuppressWarnings("static-access")
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		View vi = convertView;
//		
//		if (vi == null)
//			inflater = (LayoutInflater) Contexto
//					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//		
//		vi = inflater.inflate(R.layout.lyt_adp_maquinaria, null);
//
//		TextView descrpcion = (TextView) vi.findViewById(R.id.txv_DescMaqui); // 
//		TextView fecha = (TextView) vi.findViewById(R.id.txv_fechaMaqui); 
//		if(Lista.get(position).getTitulo().equals("")){
//			descrpcion.setText(Lista.get(position).getDescripcion());
//		}else{
//			descrpcion.setText(Lista.get(position).getTitulo());
//		}
//		
//		descrpcion.setTextColor(new Color().BLACK);
//		fecha.setTextColor(new Color().BLUE);
//		fecha.setText(Lista.get(position).getFecha());		
//		
//		return vi;
//	}
//	
//}
