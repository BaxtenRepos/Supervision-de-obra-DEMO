package com.csgit.cao.controllers;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_ViewPager_Historial_Operaciones;
import com.csgit.cao.utils.Utl_Constantes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

public class Act_Historial_Operacion extends FragmentActivity{
	
	private ViewPager pager;	
	private Context context;
	private FragmentManager fm;
	private Adp_ViewPager_Historial_Operaciones adp_viewPager; 
	private long idConcepto;
	private String unidadMedida;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.lyt_act_historial_operaciones);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		context = getApplicationContext();
		fm = getSupportFragmentManager();
		
		idConcepto = getIntent().getExtras().getLong("idConcepto");
		unidadMedida = getIntent().getExtras().getString("unidadMedida");
		
		pager = (ViewPager) findViewById(R.id.view_pager_historial_operaciones);
		pager.setOffscreenPageLimit(Utl_Constantes.NUMERO_TABS_HISTORIAL);
		
		adp_viewPager = new Adp_ViewPager_Historial_Operaciones(fm, context, idConcepto, unidadMedida);
		pager.setAdapter(adp_viewPager);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			 NavUtils.navigateUpFromSameTask(this);
		     return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
