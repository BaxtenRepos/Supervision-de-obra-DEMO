package com.csgit.cao.adapters;

import com.csgit.cao.R;
import com.csgit.cao.controllers.Frag_Historial_Avances;
import com.csgit.cao.controllers.Frag_Historial_Operaciones;
import com.csgit.cao.utils.Utl_Constantes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Adp_ViewPager_Historial_Operaciones extends FragmentStatePagerAdapter{

	private Context context;
	private String[] titlesPager;
	private long idConcepto = 0;
	private String unidadMedida;
	
	public Adp_ViewPager_Historial_Operaciones(FragmentManager fm, Context context, long idConcepto, String unidadMedida) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.idConcepto = idConcepto;
		this.unidadMedida = unidadMedida;
		this.titlesPager = this.context.getResources().getStringArray(R.array.str_array_pager_historial_titles);
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		Fragment frag = null;
		Bundle bundle = new Bundle();
		bundle.putLong("idConcepto", idConcepto);
		bundle.putString("unidadMedida", unidadMedida);
		
		switch (arg0) {
		case Utl_Constantes.TAB_AVANCES:
			frag = new Frag_Historial_Avances();
			break;
		case Utl_Constantes.TAB_OPERACIONES:
			frag = new Frag_Historial_Operaciones();
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
		return Utl_Constantes.NUMERO_TABS_HISTORIAL;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		
		CharSequence title = null;
		
		switch (position) {
		case Utl_Constantes.TAB_AVANCES:
			title = titlesPager[Utl_Constantes.TAB_AVANCES];
			break;
		case Utl_Constantes.TAB_OPERACIONES:
			title = titlesPager[Utl_Constantes.TAB_OPERACIONES];
			break;
			
		default:
			break;
		}
		return title;
	}
	
}
