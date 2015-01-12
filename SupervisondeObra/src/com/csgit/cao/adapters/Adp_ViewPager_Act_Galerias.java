package com.csgit.cao.adapters;

import com.csgit.cao.R;
import com.csgit.cao.controllers.Frag_Evidencias;
import com.csgit.cao.controllers.Frag_Minutas;
import com.csgit.cao.utils.Utl_Constantes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Adp_ViewPager_Act_Galerias extends FragmentStatePagerAdapter{

	private Context context;
	private String[] titlePager;
	private long idObra;
	
	public Adp_ViewPager_Act_Galerias(FragmentManager fm, Context context, long idObra) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.titlePager = this.context.getResources().getStringArray(R.array.str_array_pager_galerias_titles);
		this.idObra = idObra;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		Fragment fragment = null;
		Bundle bundle = new Bundle();
		bundle.putLong("idObra", idObra);
		
		switch (arg0) {
		case Utl_Constantes.TAB_EVIDENCIAS:
			fragment = new Frag_Evidencias();
			break;
		case Utl_Constantes.TAB_MINUTAS:
			fragment = new Frag_Minutas();
			break;

		default:
			break;
		}
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Utl_Constantes.NUMERO_TABS_GALERIAS;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		CharSequence title = null;
		
		switch (position) {
		case Utl_Constantes.TAB_EVIDENCIAS:
			title = titlePager[Utl_Constantes.TAB_EVIDENCIAS];
			break;
		case Utl_Constantes.TAB_MINUTAS:
			title = titlePager[Utl_Constantes.TAB_MINUTAS];
			break;

		default:
			break;
		}
		
		return title;
	}

}
