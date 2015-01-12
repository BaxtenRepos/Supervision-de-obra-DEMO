package com.csgit.cao.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.csgit.cao.R;
import com.csgit.cao.controllers.Frag_Conceptos;
import com.csgit.cao.controllers.Frag_Estimacion;
import com.csgit.cao.controllers.Frag_Graficas_Avance;
import com.csgit.cao.controllers.Frag_Informacion_Obra;
import com.csgit.cao.controllers.Frag_Maquinaria;
import com.csgit.cao.controllers.Frag_Notas;
import com.csgit.cao.controllers.Frag_Personal;
import com.csgit.cao.utils.Utl_Constantes;

public class Adp_View_Pager extends FragmentStatePagerAdapter{

	private Context contexto;
	private String[] obra_opciones;
	private Long idObra = null;
	
	public Adp_View_Pager(FragmentManager fm, Context context, Long idObra) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.contexto = context;
		obra_opciones = contexto.getResources().getStringArray(R.array.obra_opciones);
		this.idObra = idObra;
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		
		Fragment frag = null;
		Bundle bundle = new Bundle();
		bundle.putLong("idObra", idObra);
		
		switch (position) {
		
		case Utl_Constantes.TAB_OBRA_CONCEPTOS:
			frag = new Frag_Conceptos();
			break;
		case Utl_Constantes.TAB_OBRA_AVANCE:
			frag = new Frag_Graficas_Avance();
			break;
		case Utl_Constantes.TAB_OBRA_INFORMACION:
			frag = new Frag_Informacion_Obra();
			break;
		case Utl_Constantes.TAB_OBRA_ESTIMACION:
			frag = new Frag_Estimacion();
			break;
		case Utl_Constantes.TAB_OBRA_MAQUINARIA:
			frag = new Frag_Maquinaria();
			break;
		case Utl_Constantes.TAB_OBRA_PERSONAL:
			frag = new Frag_Personal();
			break;
		case Utl_Constantes.TAB_OBRA_NOTAS:
			frag = new Frag_Notas();
			break;

		default:
			break;
		}
		
		frag.setArguments(bundle);
		
		return frag;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Utl_Constantes.NUMERO_TABS_PRINCIPAL;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		CharSequence Titulo = null;

		switch (position) {
		case Utl_Constantes.TAB_OBRA_CONCEPTOS:
			Titulo = obra_opciones[Utl_Constantes.TAB_OBRA_CONCEPTOS];
			break;
		case Utl_Constantes.TAB_OBRA_AVANCE:
			Titulo = obra_opciones[Utl_Constantes.TAB_OBRA_AVANCE];
			break;
		case Utl_Constantes.TAB_OBRA_INFORMACION:
			Titulo = obra_opciones[Utl_Constantes.TAB_OBRA_INFORMACION];
			break;
		case Utl_Constantes.TAB_OBRA_ESTIMACION:
			Titulo = obra_opciones[Utl_Constantes.TAB_OBRA_ESTIMACION];
			break;
		case Utl_Constantes.TAB_OBRA_MAQUINARIA:
			Titulo = obra_opciones[Utl_Constantes.TAB_OBRA_MAQUINARIA];
			break;
		case Utl_Constantes.TAB_OBRA_PERSONAL:
			Titulo = obra_opciones[Utl_Constantes.TAB_OBRA_PERSONAL];
			break;
		case Utl_Constantes.TAB_OBRA_NOTAS:
			Titulo = obra_opciones[Utl_Constantes.TAB_OBRA_NOTAS];
			break;

		default:
			break;
		}		
		return Titulo;
	}

}
