package com.csgit.cao.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.csgit.cao.R;
import com.csgit.cao.model.Mod_ConceptoPadre;
import com.csgit.cao.model.communicationchannel.model.Concepto;

public class Adp_Expandable_ListaConceptos extends BaseExpandableListAdapter{

	private List<Mod_ConceptoPadre> elementos;
	private Context context;
	
	public Adp_Expandable_ListaConceptos(Context context){
		this.context = context;
		elementos = new ArrayList<Mod_ConceptoPadre>();
	}
	
	public void setData(List<Mod_ConceptoPadre> data){
		elementos.clear();
		if(data != null){
			for (Mod_ConceptoPadre mod_ConceptoPadre : data) {
				elementos.add(mod_ConceptoPadre);
			}
			notifyDataSetChanged();
		}
	}
	
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return elementos.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return elementos.get(groupPosition).getChildren().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return elementos.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return elementos.get(groupPosition).getChildren().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		GroupHolder groupHolder;
		
		if(vi == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.lyt_adp_padre_concepto, parent, false);
			groupHolder = new GroupHolder();
			
			groupHolder.nomConceptoPadre = (TextView) vi.findViewById(R.id.txv_nombreConc);
			groupHolder.claveConcepto = (TextView) vi.findViewById(R.id.txv_claveConc);
		
			vi.setTag(groupHolder);
		}else{
			groupHolder = (GroupHolder) vi.getTag();
		}
	    
		Mod_ConceptoPadre padre = (Mod_ConceptoPadre) getGroup(groupPosition);
		
		groupHolder.nomConceptoPadre.setText(padre.getNomConcepto());
		groupHolder.claveConcepto.setText(padre.getClave());
		
		return vi;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		ChildHolder childHolder;
		
		if(vi == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.lyt_adp_childs_conceptos, parent, false);
			childHolder = new ChildHolder();
			
			childHolder.nomConceptoHijo = (TextView) vi.findViewById(R.id.txv_NomCortoConcep);  
			childHolder.claveConcepto = (TextView) vi.findViewById(R.id.txv_ClaveC);
			childHolder.unidadMedida = (TextView) vi.findViewById(R.id.txv_UnidadMedida);
			childHolder.cantidad = (TextView) vi.findViewById(R.id.txv_Cantidad);
			
			vi.setTag(childHolder);
		}else{
			childHolder = (ChildHolder) vi.getTag();
		}
		
		Concepto hijo = (Concepto) getChild(groupPosition, childPosition);
		
		childHolder.nomConceptoHijo.setText(hijo.getDescripcion());
		childHolder.claveConcepto.setText(hijo.getClave());
		childHolder.unidadMedida.setText(hijo.getUnidadMedida());
		childHolder.cantidad.setText(String.valueOf(hijo.getCantidadTotal()));	
		
		return vi;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}
	
	private static class ChildHolder{
		TextView nomConceptoHijo;
		TextView claveConcepto;
		TextView unidadMedida;
		TextView cantidad;
	}
	
	private static class GroupHolder{
		TextView nomConceptoPadre;
		TextView claveConcepto;
	}
	
}


//public class Adp_Expandable_ListaConceptos extends BaseExpandableListAdapter implements Filterable{
//	
//	private List<Mod_ConceptoPadre> Lista;
//	private Context context;
////    private List<Mod_ConceptoPadre> listPadre;
//    LayoutInflater inflater = null;
//    private List<Mod_ConceptoPadre> orig;
//	private SharedPreferences preferences;
//	private Editor edit;
////	private List<String> hijos;
//	
//    public Adp_Expandable_ListaConceptos(Context context, List<Mod_ConceptoPadre> listPadre) {
//    	super();
//    	this.Lista = listPadre;
//        this.context = context;
//        this.orig = listPadre;
//        preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
////        hijos = new ArrayList<>();
//    }
//
//	@Override
//	public int getGroupCount() {
//		// TODO Auto-generated method stub
//		return Lista.size();
//	}
//
//	@Override
//	public int getChildrenCount(int groupPosition) {
//		// TODO Auto-generated method stub
//		return this.Lista.get(groupPosition).getChildren().size();
//	}
//
//	@Override
//	public Object getGroup(int groupPosition) {
//		// TODO Auto-generated method stub
//		return Lista.get(groupPosition);
//	}
//
//	@Override
//	public Object getChild(int groupPosition, int childPosition) {
//		// TODO Auto-generated method stub
//		return Lista.get(groupPosition).getChildren().get(childPosition);
//	}
//
//	@Override
//	public long getGroupId(int groupPosition) {
//		// TODO Auto-generated method stub
//		return groupPosition;
//	}
//
//	@Override
//	public long getChildId(int groupPosition, int childPosition) {
//		// TODO Auto-generated method stub
//		return childPosition;
//	}
//
//	@Override
//	public boolean hasStableIds() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@SuppressLint("InflateParams")
//	@Override
//	public View getGroupView(int groupPosition, boolean isExpanded,
//			View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		View vi = convertView;
//		GroupHolder groupHolder;
//		
//		if(vi == null){
//			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			vi = inflater.inflate(R.layout.lyt_adp_padre_concepto, parent, false);
//			groupHolder = new GroupHolder();
//			
//			groupHolder.nomConceptoPadre = (TextView) vi.findViewById(R.id.txv_nombreConc);
//			groupHolder.claveConcepto = (TextView) vi.findViewById(R.id.txv_claveConc);
//		
//			vi.setTag(groupHolder);
//		}else{
//			groupHolder = (GroupHolder) vi.getTag();
//		}
//	    
//		Mod_ConceptoPadre padre = (Mod_ConceptoPadre) getGroup(groupPosition);
//		
//		groupHolder.nomConceptoPadre.setText(padre.getNomConcepto());
//		groupHolder.claveConcepto.setText(padre.getClave());
//		
//		return vi;
//	}
//
//	@SuppressLint("InflateParams")
//	@Override
//	public View getChildView(int groupPosition, int childPosition,
//			boolean isLastChild, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		View vi = convertView;
//		ChildHolder childHolder;
//		
//		if(vi == null){
//			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			vi = inflater.inflate(R.layout.lyt_adp_childs_conceptos, parent, false);
//			childHolder = new ChildHolder();
//			
//			childHolder.nomConceptoHijo = (TextView) vi.findViewById(R.id.txv_NomCortoConcep);  
//			childHolder.claveConcepto = (TextView) vi.findViewById(R.id.txv_ClaveC);
//			childHolder.unidadMedida = (TextView) vi.findViewById(R.id.txv_UnidadMedida);
//			childHolder.cantidad = (TextView) vi.findViewById(R.id.txv_Cantidad);
//			
//			vi.setTag(childHolder);
//		}else{
//			childHolder = (ChildHolder) vi.getTag();
//		}
//		
//		Concepto hijo = (Concepto) getChild(groupPosition, childPosition);
//		
//		childHolder.nomConceptoHijo.setText(hijo.getDescripcion());
//		childHolder.claveConcepto.setText(hijo.getClave());
//		childHolder.unidadMedida.setText(hijo.getUnidadMedida());
//		childHolder.cantidad.setText(String.valueOf(hijo.getCantidadTotal()));	
//		
//		return vi;
//	}
//	
//	private static class ChildHolder{
//		TextView nomConceptoHijo;
//		TextView claveConcepto;
//		TextView unidadMedida;
//		TextView cantidad;
//	}
//
//	private static class GroupHolder{
//		TextView nomConceptoPadre;
//		TextView claveConcepto;
//	}
//
//	@Override
//	public boolean isChildSelectable(int groupPosition, int childPosition) {
//		// TODO Auto-generated method stub
//		return true;
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
//					edit.putString(Constantes.PRE_QUERY_CONCEPTO, constraint.toString());
//					edit.commit();
//				}else{
//					Lista = (List<Mod_ConceptoPadre>) results.values;
//				}
//				notifyDataSetChanged();
//			}
//			
//			@Override
//			protected FilterResults performFiltering(CharSequence constraint) {
//				// TODO Auto-generated method stub
//				FilterResults results = new FilterResults();
//				List<Mod_ConceptoPadre> FilteredArrayNames = new ArrayList<Mod_ConceptoPadre>();
//				
//				constraint = constraint.toString().toLowerCase();
//
//				int pre = preferences.getString(Constantes.PRE_QUERY_CONCEPTO, "").length();
//				int con = constraint.length();
//				if(Lista.size() == 0 ||  (pre > con)){
//					Lista = orig;
//				}
//					
//				
//				for (Mod_ConceptoPadre concepto : Lista) {
//					if(concepto.getClave() != null && concepto.getClave().toLowerCase().contains(constraint.toString()))
//						FilteredArrayNames.add(concepto);
//				}
////				for (int i = 0;i<Lista.size();i++) {
////					if(Lista.get(i).getClave() != null && (Lista.get(i).getClave().toLowerCase().contains(constraint.toString()) || 
////							hijos.get(i).toLowerCase().contains(constraint.toString())))
////						FilteredArrayNames.add(Lista.get(i));
////				}
//				
//				results.count = FilteredArrayNames.size();
//				results.values = FilteredArrayNames;
////				Log.i("Values", results.values.toString());
//				
//				edit = preferences.edit();
//				edit.putString(Constantes.PRE_QUERY_CONCEPTO, constraint.toString());
//				edit.commit();
//				
//				return results;
//			}
//		};
//		return filter;
//	}
//}
