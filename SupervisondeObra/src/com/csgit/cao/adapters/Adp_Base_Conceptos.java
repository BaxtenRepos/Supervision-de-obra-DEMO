package com.csgit.cao.adapters;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.csgit.cao.R;
import com.csgit.cao.model.communicationchannel.model.Concepto;
import com.csgit.cao.utils.Utl_Constantes;

@SuppressLint("DefaultLocale")
public class Adp_Base_Conceptos extends BaseAdapter implements Filterable{
	
	private List<Concepto> Lista;
	private Context Contexto;
	private LayoutInflater inflater = null;
	private List<Concepto> orig;
	private SharedPreferences preferences;
	private Editor edit;

	public Adp_Base_Conceptos( Context contexto, List<Concepto> lista) {
		super();
		
		this.Lista = lista;
		this.Contexto = contexto;
		this.orig = lista;
		preferences = PreferenceManager.getDefaultSharedPreferences(this.Contexto);
//		preferences = contexto.getSharedPreferences(Constantes.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
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
	
	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		
		if (vi == null)
			inflater = (LayoutInflater) Contexto.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		vi = inflater.inflate(R.layout.lyt_adp_childs_conceptos, null);

		TextView nomConcepto = (TextView) vi.findViewById(R.id.txv_NomCortoConcep); // 
		TextView claveConcepto = (TextView) vi.findViewById(R.id.txv_ClaveC);
		TextView unidadMedida = (TextView) vi.findViewById(R.id.txv_UnidadMedida); // 
		TextView cantidad = (TextView) vi.findViewById(R.id.txv_Cantidad);
//		TextView realizado = (TextView) vi.findViewById(R.id.txv_realizado); //		
	    
		nomConcepto.setText(Lista.get(position).getDescripcion());
		claveConcepto.setText(String.valueOf(Lista.get(position).getClave()));
		unidadMedida.setText(Lista.get(position).getUnidadMedida());
		cantidad.setText(String.valueOf(Lista.get(position).getCantidadTotal()));
//		realizado.setText("x");		
		
//		int listItemBackgroundPosition = position % listItemBackground.length;
//		vi.setBackgroundResource(listItemBackground[listItemBackgroundPosition]);
//		Animation animation = AnimationUtils.loadAnimation(Contexto, R.animator.push_list_view);
//		vi.setAnimation(animation);
		
		return vi;
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub

		Filter filter = new Filter() {

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {
				// TODO Auto-generated method stub
				if(constraint.equals("")){
					Lista = orig;
					edit = preferences.edit();
					edit.putString(Utl_Constantes.PRE_QUERY_CONCEPTO, constraint.toString());
					edit.commit();
				}else{
					Lista = (List<Concepto>) results.values;
				}
				notifyDataSetChanged();
			}

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				// TODO Auto-generated method stub
				FilterResults results = new FilterResults();
				List<Concepto> FilteredArrayNames = new ArrayList<Concepto>();
				
				constraint = constraint.toString().toLowerCase();

				int pre = preferences.getString(Utl_Constantes.PRE_QUERY_CONCEPTO, "").length();
				int con = constraint.length();
				if(Lista.size() == 0 ||  (pre > con)){
					Lista = orig;
				}
					
				
				for (Concepto concepto : Lista) {
					if(concepto.getClave().toLowerCase().contains(constraint.toString()))
						FilteredArrayNames.add(concepto);
				}
				results.count = FilteredArrayNames.size();
				results.values = FilteredArrayNames;
//				Log.i("Values", results.values.toString());
				
				edit = preferences.edit();
				edit.putString(Utl_Constantes.PRE_QUERY_CONCEPTO, constraint.toString());
				edit.commit();
				
				return results;
			}
		};
		return filter;
	}
}
