package com.csgit.cao.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.csgit.cao.R;
import com.csgit.cao.business.Bus_Obras;
import com.csgit.cao.model.Mod_Obras;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Operaciones;


public class Adp_Frag_Obras extends ArrayAdapter<Mod_Obras>{

	private Context context;
	private Bus_Obras bus_obra;

	public Adp_Frag_Obras(Context context) {
		super(context, android.R.layout.simple_list_item_2);
		// TODO Auto-generated constructor stub
		this.context = context;
		bus_obra = new Bus_Obras(this.context);
	}

	public void setData(List<Mod_Obras> data){
		clear();
		if(data != null){
			for (Mod_Obras mod_Obras : data) {
				add(mod_Obras);				
			}
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		ObraHolder obraHolder;
		if(vi == null){
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.lyt_adp_obras, parent, false);
			obraHolder = new ObraHolder();

			obraHolder.nomObra = (TextView) vi.findViewById(R.id.txv_NombreObra);
			obraHolder.empContratista = (TextView) vi.findViewById(R.id.txv_EmpresaContra); 
			obraHolder.nomSuper = (TextView) vi.findViewById(R.id.txv_SuperEncargado);
			obraHolder.portcFisico = (TextView) vi.findViewById(R.id.txv_porctAvanFisico);
			obraHolder.portcFinanciero = (TextView) vi.findViewById(R.id.txv_porctAvanFinanciero);
			obraHolder.portcDesvio = (TextView) vi.findViewById(R.id.txv_porctDesvio);

			vi.setTag(obraHolder);
		}else{
			obraHolder = (ObraHolder) vi.getTag();
		}

		Mod_Obras obra = getItem(position);
		String contratista = bus_obra.getEmpresaContratistaFromObra(
				Long.parseLong(obra.getEmpresaContratista()));

		obraHolder.empContratista.setText(contratista);
		obraHolder.nomObra.setText(obra.getNombre());
		obraHolder.nomSuper.setText(obra.getEmpresaSupervisora());

		switch (obra.getEstadoFisico()) {
		case Utl_Constantes.ESTADO_ADELANTADO:
			obraHolder.portcFisico.setTextColor(Utl_Constantes.COLOR_VERDE);
			break;
		case Utl_Constantes.ESTADO_ATRASADO:
			obraHolder.portcFisico.setTextColor(Utl_Constantes.COLOR_ROJO);
			break;
		case Utl_Constantes.ESTADO_CONFORME_PROGRAMA:
			obraHolder.portcFisico.setTextColor(Utl_Constantes.COLOR_NARANJA);
			break;

		default:
			break;
		}

		switch (obra.getEstadoFinanciero()) {
		case Utl_Constantes.ESTADO_ADELANTADO:
			obraHolder.portcFinanciero.setTextColor(Utl_Constantes.COLOR_VERDE);
			break;
		case Utl_Constantes.ESTADO_ATRASADO:
			obraHolder.portcFinanciero.setTextColor(Utl_Constantes.COLOR_ROJO);
			break;
		case Utl_Constantes.ESTADO_CONFORME_PROGRAMA:
			obraHolder.portcFinanciero.setTextColor(Utl_Constantes.COLOR_NARANJA);
			break;

		default:
			break;
		}

		obraHolder.portcFisico.setText(Utl_Operaciones.redondearValor(
				Double.parseDouble(obra.getPorcentajeFisico()), 2) +
				Utl_Constantes.CARAC_PORCENTAJE);
		obraHolder.portcFinanciero.setText(Utl_Operaciones.redondearValor(
				Double.parseDouble(obra.getPorcentajeFinanciero()), 2) + 
				Utl_Constantes.CARAC_PORCENTAJE);

		obraHolder.portcDesvio.setText(obra.getLimiteDesvio() + Utl_Constantes.CARAC_PORCENTAJE);

		return vi;
	}

	private static class ObraHolder{
		TextView nomObra;
		TextView empContratista; 
		TextView nomSuper;
		TextView portcFisico;
		TextView portcFinanciero;
		TextView portcDesvio;
	}

//	@Override
//	public Filter getFilter() {
//		// TODO Auto-generated method stub
//		Filter filter = new Filter() {
//
//			@SuppressWarnings("unchecked")
//			@Override
//			protected void publishResults(CharSequence constraint, FilterResults results) {
//				// TODO Auto-generated method stub
//				if(constraint.equals("")){
//					objects = orig;
//					edit = preferences.edit();
//					edit.putString(Constantes.PRE_QUERY_OBRA, constraint.toString());
//					edit.commit();
//				}else{
//					objects = (List<Mod_Obras>) results.values;	
//				}
//
//				notifyDataSetChanged();
//			}
//
//			@Override
//			protected FilterResults performFiltering(CharSequence constraint) {
//				// TODO Auto-generated method stub
//				FilterResults results = new FilterResults();
//				List<Mod_Obras> FilteredArrayNames = new ArrayList<Mod_Obras>();
//
//				constraint = constraint.toString().toLowerCase();
//
//				int pre = preferences.getString(Constantes.PRE_QUERY_OBRA, "").length();
//				int con = constraint.length();
//
//				if(objects.size() == 0 ||  (pre > con))
//					objects = orig;
//
//				for (Mod_Obras mod_Obras : objects) {
//					if(mod_Obras.getNombre().toLowerCase().contains(constraint.toString()))
//						FilteredArrayNames.add(mod_Obras);
//				}
//				results.count = FilteredArrayNames.size();
//				results.values = FilteredArrayNames;
//				//			Log.e("VALUES", results.values.toString());
//
//				edit = preferences.edit();
//				edit.putString(Constantes.PRE_QUERY_OBRA, constraint.toString());
//				edit.commit();
//
//				return results;
//			}
//		};
//		return filter;
//	}
}

//@SuppressLint({ "ViewHolder", "InflateParams", "DefaultLocale" })
//public class Adp_Base_ListaObra extends BaseAdapter implements Filterable{
//	
//	public List<Mod_Obras> Lista;
//	private Context Contexto;
//	private LayoutInflater inflater = null;
//	public List<Mod_Obras> orig;
//	
//	private Bus_Obras bus_obra;
//	private SharedPreferences preferences;
//	private Editor edit;
//
//	public Adp_Base_ListaObra(List<Mod_Obras> lista, Context contexto) {
//		super();
//		
//		this.Lista = lista;
//		this.Contexto = contexto;
//		this.orig = lista;
//		bus_obra = new Bus_Obras(this.Contexto.getContentResolver());
//		this.preferences = PreferenceManager.getDefaultSharedPreferences(this.Contexto);
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
//	@SuppressLint("ResourceAsColor")
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		View vi = convertView;
//		
//		if (vi == null)
//			inflater = (LayoutInflater) Contexto.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//		
//		vi = inflater.inflate(R.layout.lyt_adp_obras, null);
//
//		TextView nomObra = (TextView) vi.findViewById(R.id.txv_NombreObra);
//		TextView empContratista = (TextView) vi.findViewById(R.id.txv_EmpresaContra); 
//		TextView nomSuper = (TextView) vi.findViewById(R.id.txv_SuperEncargado);
//		TextView portcFisico = (TextView) vi.findViewById(R.id.txv_porctAvanFisico);
//		TextView portcFinanciero = (TextView) vi.findViewById(R.id.txv_porctAvanFinanciero);
//		TextView portcDesvio = (TextView) vi.findViewById(R.id.txv_porctDesvio);
//		
//		String contratista = bus_obra.getEmpresaContratistaFromObra(
//				Long.parseLong(Lista.get(position).getEmpresaContratista()));
//		
//		empContratista.setText(contratista);
//		nomObra.setText(Lista.get(position).getNombre());
//		nomSuper.setText(Lista.get(position).getEmpresaSupervisora());
//		
//		switch (Lista.get(position).getEstadoFisico()) {
//		case Constantes.ESTADO_ADELANTADO:
//			portcFisico.setTextColor(Constantes.COLOR_VERDE);
//			break;
//		case Constantes.ESTADO_ATRASADO:
//			portcFisico.setTextColor(Constantes.COLOR_ROJO);
//			break;
//		case Constantes.ESTADO_CONFORME_PROGRAMA:
//			portcFisico.setTextColor(Constantes.COLOR_NARANJA);
//			break;
//
//		default:
//			break;
//		}
//		
//		switch (Lista.get(position).getEstadoFinanciero()) {
//		case Constantes.ESTADO_ADELANTADO:
//			portcFinanciero.setTextColor(Constantes.COLOR_VERDE);
//			break;
//		case Constantes.ESTADO_ATRASADO:
//			portcFinanciero.setTextColor(Constantes.COLOR_ROJO);
//			break;
//		case Constantes.ESTADO_CONFORME_PROGRAMA:
//			portcFinanciero.setTextColor(Constantes.COLOR_NARANJA);
//			break;
//
//		default:
//			break;
//		}
//		
//		portcFisico.setText(Utl_Operaciones.redondearValor(
//				Double.parseDouble(Lista.get(position).getPorcentajeFisico()), 2) +
//				Constantes.CARAC_PORCENTAJE);
//		portcFinanciero.setText(Utl_Operaciones.redondearValor(
//				Double.parseDouble(Lista.get(position).getPorcentajeFinanciero()), 2) + 
//				Constantes.CARAC_PORCENTAJE);
//		
//		
//		portcDesvio.setText(Lista.get(position).getLimiteDesvio() + Constantes.CARAC_PORCENTAJE);
//
//		return vi;
//	}
//
//	@Override
//	public Filter getFilter() {
//		// TODO Auto-generated method stub
//		Filter filter = new Filter() {
//			
//			@SuppressWarnings("unchecked")
//			@Override
//			protected void publishResults(CharSequence constraint, FilterResults results) {
//				// TODO Auto-generated method stub
//				if(constraint.equals("")){
//					Lista = orig;
//					edit = preferences.edit();
//					edit.putString(Constantes.PRE_QUERY_OBRA, constraint.toString());
//					edit.commit();
//				}else{
//					Lista = (List<Mod_Obras>) results.values;	
//				}
//				
//				notifyDataSetChanged();
//			}
//			
//			@Override
//			protected FilterResults performFiltering(CharSequence constraint) {
//				// TODO Auto-generated method stub
//				FilterResults results = new FilterResults();
//				List<Mod_Obras> FilteredArrayNames = new ArrayList<Mod_Obras>();
//				
//				constraint = constraint.toString().toLowerCase();
//				
//				int pre = preferences.getString(Constantes.PRE_QUERY_OBRA, "").length();
//				int con = constraint.length();
//				
//				if(Lista.size() == 0 ||  (pre > con))
//					Lista = orig;
//				
//				for (Mod_Obras mod_Obras : Lista) {
//					if(mod_Obras.getNombre().toLowerCase().contains(constraint.toString()))
//						FilteredArrayNames.add(mod_Obras);
//				}
//				results.count = FilteredArrayNames.size();
//				results.values = FilteredArrayNames;
////				Log.e("VALUES", results.values.toString());
//				
//				edit = preferences.edit();
//				edit.putString(Constantes.PRE_QUERY_OBRA, constraint.toString());
//				edit.commit();
//				 
//				return results;
//			}
//		};
//		return filter;
//	}
//
//}
