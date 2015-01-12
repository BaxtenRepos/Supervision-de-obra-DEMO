package com.csgit.cao.controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_View_Pager;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Menus;

@SuppressLint("InflateParams")
public class Frag_ViewPager extends Fragment{

	public static ViewPager viewPager;
	private Context context;
	private Long idObra;
	private String nombreObra;
//	private String area;
	private Frag_Dialog_Evidencia frag_dialog;
	private Frag_Dialog_Opciones_Obra frag_dialog_opciones;
	private Frag_Dialog_AgregarReporte frag_dialog_agregar_reporte;
	private SharedPreferences preferences;
//	private FragmentManager fm;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getActivity().supportInvalidateOptionsMenu();
		setHasOptionsMenu(true);

		viewPager = (ViewPager) getView().findViewById(R.id.view_pager_cao);
		viewPager.setOffscreenPageLimit(Utl_Constantes.NUMERO_TABS_PRINCIPAL);
		context = getActivity();
//		fm = getFragmentManager();

		Bundle bundle = getArguments();
		idObra = bundle.getLong("idObra");
		nombreObra = bundle.getString("nombreObra");
//		area = bundle.getString("area");
		
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
		frag_dialog = new Frag_Dialog_Evidencia(context, idObra, preferences);
		frag_dialog_opciones = new Frag_Dialog_Opciones_Obra(context, idObra, preferences);
		frag_dialog_agregar_reporte = new Frag_Dialog_AgregarReporte(context, idObra);
		
		final FragmentManager fm = getFragmentManager();

		Adp_View_Pager pagerAdapter = new Adp_View_Pager(fm, context, idObra);
		viewPager.setAdapter(pagerAdapter);

		getActivity().setTitle(nombreObra);

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
//		inflater.inflate(R.menu.menu_act_reporte_concepto, menu);
		
		menu.clear();
		Act_Main.menuBuscarConceptos = Utl_Menus.configItem(menu, Act_Main.menuBuscarConceptos, 
				Utl_Constantes.MENU_BUSCAR_CONCEPTOS_ID);		
		Act_Main.menuOpciones = Utl_Menus.configItem(menu, Act_Main.menuOpciones,
				Utl_Constantes.MENU_OPCIONES_ID);
		Act_Main.menuUbicacion = Utl_Menus.configItem(menu, Act_Main.menuUbicacion, 
				Utl_Constantes.MENU_UBICACION_ID);
		Act_Main.menuDirectorio = Utl_Menus.configItem(menu, Act_Main.menuDirectorio,
				Utl_Constantes.MENU_DIRECTORIO_ID);
		Act_Main.menuVerDocumentos = Utl_Menus.configItem(menu, Act_Main.menuVerDocumentos,
				Utl_Constantes.MENU_VER_DOCUMENTOS_ID);
		Act_Main.menuTomarEvidencia = Utl_Menus.configItem(menu, Act_Main.menuTomarEvidencia,
				Utl_Constantes.MENU_TOMAR_EVIDENCIA);
//		Act_Main.menuGaleriaEvidencia = Utl_Menus.configItem(menu, Act_Main.menuGaleriaEvidencia,
//				Constantes.MENU_GALERIA_EVIDENCIA);
//		Act_Main.menuGaleriaMinutas = Utl_Menus.configItem(menu, Act_Main.menuGaleriaMinutas,
//				Constantes.MENU_GALERIA_MINUTAS_ID);
		Act_Main.menuGalerias = Utl_Menus.configItem(menu, Act_Main.menuGalerias,
				Utl_Constantes.MENU_GALERIAS);
		Act_Main.menuAgregarReporte = Utl_Menus.configItem(menu, Act_Main.menuAgregarReporte,
				Utl_Constantes.MENU_AGREGAR_REPORTE_ID);
//		Act_Main.menuEstimacion = Utl_Menus.configItem(menu, Act_Main.menuEstimacion,
//				Constantes.MENU_ESTIMACION_ID);
//		Act_Main.menuMaquinaria = Utl_Menus.configItem(menu, Act_Main.menuMaquinaria,
//				Constantes.MENU_MAQUINARIA_ID);
//		Act_Main.menuPersonal = Utl_Menus.configItem(menu, Act_Main.menuPersonal,
//				Constantes.MENU_PERSONAL_ID);
//		Act_Main.menuNotas = Utl_Menus.configItem(menu, Act_Main.menuNotas,
//				Constantes.MENU_NOTAS_ID);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		
//		case R.id.menu_ubicacion:
		case Utl_Constantes.MENU_UBICACION_ID:
			Intent intent = new Intent(getActivity(), Act_Ubicacion.class);
			intent.putExtra("idObra", idObra);
			intent.putExtra("nombreObra", nombreObra);
//			intent.putExtra("area", area);
			startActivity(intent);
			
			break;

//		case R.id.menu_minutas:
		case Utl_Constantes.MENU_OPCIONES_ID:
			showDialogOpcionesObra();
			break;

//		case R.id.menu_act_tomar_evidencia:
		case Utl_Constantes.MENU_TOMAR_EVIDENCIA:
			showEditDialog();
			break;

//		case R.id.menu_act_galeria:
//		case Constantes.MENU_GALERIA_EVIDENCIA:
//			Intent intent1 = new Intent(getActivity(), Act_Galeria_Evidencia.class);
//			intent1.putExtra("idReporte", idObra);
//			startActivity(intent1);
//			break;
			
//		case R.id.menu_agregar_estimacion:
//		case Constantes.MENU_ESTIMACION_ID:
//			Intent intentEstimacion = new Intent(getActivity(), Act_Estimacion.class);
//			Bundle bundleEstimacion = new Bundle();
//			bundleEstimacion.putLong("idObra", idObra);
//			bundleEstimacion.putBoolean("nuevoReporte", true);
//			intentEstimacion.putExtra("datos", bundleEstimacion);
//			startActivity(intentEstimacion);
//			break;
//			
////		case R.id.menu_agregar_reporte_maquinaria:
//		case Constantes.MENU_MAQUINARIA_ID:
//			
//			Intent intentMaquinaria = new Intent(getActivity(), Act_Maquinaria.class);
//			intentMaquinaria.putExtra("idObra", idObra);
//			intentMaquinaria.putExtra("nuevoReporte", true);
//			startActivity(intentMaquinaria);
//
//			break;
//			
////		case R.id.menu_agregar_reporte_personal:
//		case Constantes.MENU_PERSONAL_ID:
//			
//			Intent intentPersonal = new Intent(getActivity(), Act_Personal.class);
//			intentPersonal.putExtra("idObra", idObra);
//			intentPersonal.putExtra("nuevoReporte", true);
//			startActivity(intentPersonal);
//
//			break;
//			
////		case R.id.menu_agregar_notas:
//		case Constantes.MENU_NOTAS_ID:
//			Intent intentNotas = new Intent(getActivity(), Act_Notas.class);
//			intentNotas.putExtra("idObra", idObra);
//			intentNotas.putExtra("nuevoReporte", true);
//			startActivity(intentNotas);
//
//			break;
			
		case Utl_Constantes.MENU_AGREGAR_REPORTE_ID:
			showDialogAgregarReporte();
			break;
			
//		case R.id.menu_act_galeria_minuta:
//		case Constantes.MENU_GALERIA_MINUTAS_ID:
//			Intent intentMinutas = new Intent(getActivity(), Act_Galeria_Minutas.class);
//			intentMinutas.putExtra("idObra", idObra);
//			startActivity(intentMinutas);
//		
//			break;
			
		case Utl_Constantes.MENU_GALERIAS:
			Intent intentGalerias = new Intent(getActivity(), Act_Galerias.class);
			intentGalerias.putExtra("idObra", idObra);
			startActivity(intentGalerias);
			break;
			
//		case R.id.menu_ver_documentos:
		case Utl_Constantes.MENU_VER_DOCUMENTOS_ID:
			Intent intentDoc = new Intent(getActivity(), Act_Documentos.class);
			intentDoc.putExtra("idObra", idObra);
			startActivity(intentDoc);
			
			break;
			
//		case R.id.menu_directorio:
		case Utl_Constantes.MENU_DIRECTORIO_ID:
			Intent intentDirectorio = new Intent(getActivity(), Act_Contactos.class);
			intentDirectorio.putExtra("idObra", idObra);
			startActivity(intentDirectorio);
			
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showDialogAgregarReporte(){
		FragmentManager fm = getFragmentManager();
		frag_dialog_agregar_reporte.show(fm, "frag_dialog_agregar_reporte");
	}
	
	private void showEditDialog() {
		FragmentManager fm = getFragmentManager();
		frag_dialog.show(fm, "fragment_edit_name");
	}
	
	private void showDialogOpcionesObra(){
		FragmentManager fm = getFragmentManager();
		frag_dialog_opciones.show(fm, "frag_dialog_opciones");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		getActivity().setTitle(
				getResources().getString(R.string.titulo_principal));
		idObra = null;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return (View) inflater.inflate(R.layout.lyt_view_pager, null);
	}
}
