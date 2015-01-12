package com.csgit.cao.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.csgit.cao.R;
import com.csgit.cao.model.Mod_Directorio;
import com.csgit.cao.utils.Utl_Constantes;

public class Adp_Base_ListaDirectorio extends ArrayAdapter<Mod_Directorio>{

	private Context context;

	public Adp_Base_ListaDirectorio(Context context) {
		super(context, android.R.layout.simple_list_item_2);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public void setData(List<Mod_Directorio> data){
		clear();
		if(data != null){
			for (Mod_Directorio mod_Directorio : data) {
				add(mod_Directorio);
			}
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		DirectorioHolder dirHolder;

		if (vi == null){
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.lyt_adp_directorio, parent, false);
			dirHolder = new DirectorioHolder();

			dirHolder.nombre = (TextView) vi.findViewById(R.id.txv_NombreDirectorio); // 
			dirHolder.tipEmpresa = (TextView) vi.findViewById(R.id.txv_TipoEmpresaDirectorio);
			dirHolder.correo = (TextView) vi.findViewById(R.id.txv_CorreoElectDirectorio); 
			dirHolder.tel = (TextView) vi.findViewById(R.id.txv_TelefonoDirectorio); 

			vi.setTag(dirHolder);
		}else{
			dirHolder = (DirectorioHolder) vi.getTag();
		}

		Mod_Directorio directorio = getItem(position);

		dirHolder.nombre.setText(directorio.getNombre()+ " " + directorio.getApPaterno() 
				+ " " + directorio.getApMaterno());

		switch (Integer.parseInt(String.valueOf(directorio.getTipoEmpresa()))) {
		case Utl_Constantes.idContratista:
			dirHolder.tipEmpresa.setText(Utl_Constantes.contratistaTexto + ", " + directorio.getNombreEmpresa());
			break;
		case Utl_Constantes.idSupervisora:
			dirHolder.tipEmpresa.setText(Utl_Constantes.supervisoraTexto + ", " + directorio.getNombreEmpresa());
			break;
		case Utl_Constantes.idDependencia:
			dirHolder.tipEmpresa.setText(Utl_Constantes.dependenciaTexto + ", " + directorio.getNombreEmpresa());
			break;
		case Utl_Constantes.idParticular:
			dirHolder.tipEmpresa.setText(Utl_Constantes.particularTexto + ", " + directorio.getNombreEmpresa());
			break;
		case Utl_Constantes.idSecretariaa:
			dirHolder.tipEmpresa.setText(Utl_Constantes.secretariaTexto + ", " + directorio.getNombreEmpresa());
			break;
		case Utl_Constantes.idGobierno:
			dirHolder.tipEmpresa.setText(Utl_Constantes.GobiernoTexto + ", " + directorio.getNombreEmpresa());
			break;

		default:
			break;
		}

		dirHolder.correo.setText(directorio.getEmail());
		dirHolder.tel.setText(directorio.getTelefonos());

		return vi;
	}
	
	private static class DirectorioHolder{
		TextView nombre; 
		TextView tipEmpresa;
		TextView correo; 
		TextView tel;
	}
	
}
//public class Adp_Base_ListaDirectorio extends BaseAdapter{
//	List<Mod_Directorio> Lista;
//	Context Contexto;
//	LayoutInflater inflater = null;
//	
//	public Adp_Base_ListaDirectorio(Context contexto,List<Mod_Directorio> lista) {
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
//	@SuppressLint({ "ViewHolder", "InflateParams" })
//	@Override
//	public View getView(final int position, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		View vi = convertView;
//		
//		if (vi == null)
//			inflater = (LayoutInflater) Contexto
//					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//		
//		vi = inflater.inflate(R.layout.lyt_adp_directorio, null);
//
//		TextView nombre = (TextView) vi.findViewById(R.id.txv_NombreDirectorio); // 
//		TextView tipEmpresa = (TextView) vi.findViewById(R.id.txv_TipoEmpresaDirectorio);
//		TextView correo = (TextView) vi.findViewById(R.id.txv_CorreoElectDirectorio); 
//		TextView tel = (TextView) vi.findViewById(R.id.txv_TelefonoDirectorio); 
//		
//		nombre.setText(Lista.get(position).getNombre()+ " " + Lista.get(position).getApPaterno() + " "
//				+ Lista.get(position).getApMaterno());
//		
//		
//		switch (Integer.parseInt(String.valueOf(Lista.get(position).getTipoEmpresa()))) {
//		case Constantes.idContratista:
//			tipEmpresa.setText(Constantes.contratistaTexto + ", " + Lista.get(position).getNombreEmpresa());
//			break;
//		case Constantes.idSupervisora:
//			tipEmpresa.setText(Constantes.supervisoraTexto + ", " + Lista.get(position).getNombreEmpresa());
//			break;
//		case Constantes.idDependencia:
//			tipEmpresa.setText(Constantes.dependenciaTexto + ", " + Lista.get(position).getNombreEmpresa());
//			break;
//		case Constantes.idParticular:
//			tipEmpresa.setText(Constantes.particularTexto + ", " + Lista.get(position).getNombreEmpresa());
//			break;
//		case Constantes.idSecretariaa:
//			tipEmpresa.setText(Constantes.secretariaTexto + ", " + Lista.get(position).getNombreEmpresa());
//			break;
//		case Constantes.idGobierno:
//			tipEmpresa.setText(Constantes.GobiernoTexto + ", " + Lista.get(position).getNombreEmpresa());
//			break;
//
//		default:
//			break;
//		}
//		
//		correo.setText(Lista.get(position).getEmail());
//		tel.setText(Lista.get(position).getTelefonos());
//		
//		return vi;
//	}
//
//}
