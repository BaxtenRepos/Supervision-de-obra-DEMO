package com.csgit.cao.utils;

import android.view.Menu;
import android.view.MenuItem;

import com.csgit.cao.R;

public class Utl_Menus {
	
	
	public static MenuItem configItem(Menu menu, MenuItem item, int tipo){
		switch (tipo) {
		case Utl_Constantes.MENU_BUSCAR_OBRAS_ID:
			item = menu.add(Menu.NONE, Utl_Constantes.MENU_BUSCAR_OBRAS_ID, Menu.NONE, R.string.str_menu_buscar_obras);
			item.setIcon(android.R.drawable.ic_menu_search);
			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
			break;
		case Utl_Constantes.MENU_BUSCAR_CONCEPTOS_ID:
			item = menu.add(Menu.NONE, Utl_Constantes.MENU_BUSCAR_CONCEPTOS_ID, Menu.NONE, R.string.str_menu_buscar_conceptos);
			item.setIcon(android.R.drawable.ic_menu_search);
			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
			break;
		case Utl_Constantes.MENU_OPCIONES_ID:
			item = menu.add(Menu.NONE, Utl_Constantes.MENU_OPCIONES_ID, Menu.NONE, R.string.str_menu_opciones);
			item.setIcon(R.drawable.ic_action_new_event);
			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
			break;
		case Utl_Constantes.MENU_UBICACION_ID:
			item = menu.add(Menu.NONE, Utl_Constantes.MENU_UBICACION_ID, Menu.NONE, R.string.str_menu_ubicacion_obra);
			item.setIcon(R.drawable.ic_action_map);
			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
			break;
		case Utl_Constantes.MENU_DIRECTORIO_ID:
			item = menu.add(Menu.NONE, Utl_Constantes.MENU_DIRECTORIO_ID, Menu.NONE, R.string.str_menu_directorio);
			item.setIcon(R.drawable.ic_action_group);
			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
			break;
		case Utl_Constantes.MENU_VER_DOCUMENTOS_ID:
			item = menu.add(Menu.NONE, Utl_Constantes.MENU_VER_DOCUMENTOS_ID, Menu.NONE, R.string.str_menu_ver_documentos);
			item.setIcon(R.drawable.ic_action_view_as_list);
			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
			break;
		case Utl_Constantes.MENU_TOMAR_EVIDENCIA:
			item = menu.add(Menu.NONE, Utl_Constantes.MENU_TOMAR_EVIDENCIA, Menu.NONE, R.string.str_menu_tomar_evidencia);
			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
			break;
//		case Constantes.MENU_GALERIA_EVIDENCIA:
//			item = menu.add(Menu.NONE, Constantes.MENU_GALERIA_EVIDENCIA, Menu.NONE, R.string.str_menu_ver_galeria_evidencia);
//			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
//			break;
//		case Constantes.MENU_GALERIA_MINUTAS_ID:
//			item = menu.add(Menu.NONE, Constantes.MENU_GALERIA_MINUTAS_ID, Menu.NONE, R.string.str_menu_ver_galeria_minutas);
//			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
//			break;
		case Utl_Constantes.MENU_GALERIAS:
			item = menu.add(Menu.NONE, Utl_Constantes.MENU_GALERIAS, Menu.NONE, R.string.str_menu_galerias);
			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
			break;
		case Utl_Constantes.MENU_AGREGAR_REPORTE_ID:
			item = menu.add(Menu.NONE, Utl_Constantes.MENU_AGREGAR_REPORTE_ID, Menu.NONE, R.string.str_menu_agregar_reporte);
			item.setIcon(R.drawable.ic_action_new);
			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
			break;
		case Utl_Constantes.MENU_BUSCAR_DOCUMENTOS_ID:
			item = menu.add(Menu.NONE, Utl_Constantes.MENU_BUSCAR_DOCUMENTOS_ID, Menu.NONE, R.string.str_menu_buscar_documentos);
			item.setIcon(android.R.drawable.ic_menu_search);
			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
			break;
//		case Constantes.MENU_ESTIMACION_ID:
//			item = menu.add(Menu.NONE, Constantes.MENU_ESTIMACION_ID, Menu.NONE, R.string.str_menu_estimacion_obra);
//			item.setIcon(android.R.drawable.ic_menu_add);
//			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//			break;
//		case Constantes.MENU_MAQUINARIA_ID:
//			item = menu.add(Menu.NONE, Constantes.MENU_MAQUINARIA_ID, Menu.NONE, R.string.str_menu_maquinaria);
//			item.setIcon(android.R.drawable.ic_menu_add);
//			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//			break;
//		case Constantes.MENU_PERSONAL_ID:
//			item = menu.add(Menu.NONE, Constantes.MENU_PERSONAL_ID, Menu.NONE, R.string.str_menu_personal);
//			item.setIcon(android.R.drawable.ic_menu_add);
//			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//			break;
//		case Constantes.MENU_NOTAS_ID:
//			item = menu.add(Menu.NONE, Constantes.MENU_NOTAS_ID, Menu.NONE, R.string.str_menu_notas);
//			item.setIcon(android.R.drawable.ic_menu_add);
//			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//			break;
	
		default:
			break;
		}
		
		return item;
	}

	public static void configurarMenus(){
		
	}
	
//	public static void configurarMenus(int fragmento, boolean drawerOpen){
//
//		switch (fragmento) {
//		case Constantes.FRAG_OBRAS:
//			Act_Main.menuBuscarObras.setVisible(true);
//			Act_Main.menuBuscarConceptos.setVisible(false);
//			Act_Main.menuMinutas.setVisible(false);
//			Act_Main.menuUbicacion.setVisible(false);
//			Act_Main.menuDirectorio.setVisible(false);
//			Act_Main.menuPersonal.setVisible(false);
//			Act_Main.menuMaquinaria.setVisible(false);
//			Act_Main.menuNotas.setVisible(false);
//			Act_Main.menuGaleriaMinutas.setVisible(false);
//			Act_Main.menuVerDocumentos.setVisible(false);
//			break;
//
//		case Constantes.FRAG_CONCEPTOS:
//			Act_Main.menuBuscarObras.setVisible(false);
//			Act_Main.menuBuscarConceptos.setVisible(true);
//			Act_Main.menuMinutas.setVisible(true);
//			Act_Main.menuUbicacion.setVisible(true);
//			Act_Main.menuDirectorio.setVisible(true);
//			Act_Main.menuMaquinaria.setVisible(false);
//			Act_Main.menuPersonal.setVisible(false);
//			Act_Main.menuNotas.setVisible(false);
//			Act_Main.menuEstimacion.setVisible(false);
//			Act_Main.menuGaleriaMinutas.setVisible(true);
//			Act_Main.menuVerDocumentos.setVisible(true);
//			break;
//			
//		case Constantes.FRAG_GRAFICAS_AVANCE:
//			Act_Main.menuBuscarObras.setVisible(false);
//			Act_Main.menuBuscarConceptos.setVisible(false);
//			Act_Main.menuMinutas.setVisible(true);
//			Act_Main.menuUbicacion.setVisible(true);
//			Act_Main.menuDirectorio.setVisible(true);
//			Act_Main.menuMaquinaria.setVisible(false);
//			Act_Main.menuPersonal.setVisible(false);
//			Act_Main.menuNotas.setVisible(false);
//			Act_Main.menuEstimacion.setVisible(false);
//			Act_Main.menuGaleriaMinutas.setVisible(true);
//			Act_Main.menuVerDocumentos.setVisible(true);
//			break;
//			
//		case Constantes.FRAG_DRAWER:
//			Act_Main.menuMaquinaria.setVisible(!drawerOpen);
//			Act_Main.menuPersonal.setVisible(!drawerOpen);
//			Act_Main.menuDirectorio.setVisible(!drawerOpen);
//			Act_Main.menuMinutas.setVisible(!drawerOpen);
//			Act_Main.menuUbicacion.setVisible(!drawerOpen);
//			Act_Main.menuNotas.setVisible(!drawerOpen);
//			Act_Main.menuEstimacion.setVisible(!drawerOpen);
//			Act_Main.menuGaleriaMinutas.setVisible(!drawerOpen);
//			Act_Main.menuVerDocumentos.setVisible(!drawerOpen);
//			break;
//
//		default:
//			break;
//		}
//	}
}
