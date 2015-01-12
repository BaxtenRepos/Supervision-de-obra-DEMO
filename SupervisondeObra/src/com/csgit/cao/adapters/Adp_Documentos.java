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
import com.csgit.cao.model.Mod_Documentos;

public class Adp_Documentos extends ArrayAdapter<Mod_Documentos>{

	private Context context;

	public Adp_Documentos(Context context) {
		super(context, android.R.layout.simple_list_item_2);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public void setData(List<Mod_Documentos> data){
		clear();
		if(data != null){
			for (Mod_Documentos mod_Documentos : data) {
				add(mod_Documentos);
			}
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view  = convertView;
		Holder holder;
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			holder = new Holder();
			view = inflater.inflate(R.layout.lyt_adp_documentos, parent, false);
			
			holder.icon = (ImageView) view.findViewById(R.id.img_documento);
			holder.name = (TextView) view.findViewById(R.id.txv_nombre_documento);

			view.setTag(holder);
		}else{
			holder = (Holder) view.getTag();
		}

		Mod_Documentos item = (Mod_Documentos) getItem(position);
		if(item.getFormato().contains("jpg") || item.getFormato().contains("png"))
			item.setIdResource(R.drawable.ic_png);
		else if(item.getFormato().contains("xl") ||item.getFormato().contains("ods"))
			item.setIdResource(R.drawable.ic_excel);
		else if(item.getFormato().contains("doc") || item.getFormato().contains("do") || 
				item.getFormato().contains("odt"))
			item.setIdResource(R.drawable.ic_word);
		else if(item.getFormato().contains("pp") || item.getFormato().contains("odp") ||
				item.getFormato().contains("otp") || item.getFormato().contains("potm"))
			item.setIdResource(R.drawable.ic_powerpoint);
		else if(item.getFormato().contains("pdf"))
			item.setIdResource(R.drawable.ic_pdf);
		else
			item.setIdResource(R.drawable.ic_archivo_default);

		holder.icon.setImageDrawable(view.getResources().getDrawable(item.getIdResource()));
		holder.name.setText(item.getNombreDocumento());

		return view;
	}

	private static class Holder{
		ImageView icon;
		TextView name;
	}

}