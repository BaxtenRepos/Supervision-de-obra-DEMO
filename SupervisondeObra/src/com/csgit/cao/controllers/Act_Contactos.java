package com.csgit.cao.controllers;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.csgit.cao.R;

public class Act_Contactos extends FragmentActivity {
	
	private long idObra;
	private FragmentManager fm;
	private FragmentTransaction ft;
	private Frag_Contactos fragmentContactos;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.lyt_act_lista_directorio);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		idObra = getIntent().getExtras().getLong("idObra");
		Bundle bundle =  new Bundle();
		bundle.putLong("idObra", idObra);
		
		fragmentContactos = new Frag_Contactos();
		fragmentContactos.setArguments(bundle);
		
		fm = getSupportFragmentManager();
		ft = fm.beginTransaction();
		ft.replace(R.id.frm_lyt_directorio, fragmentContactos);
		ft.commit();	
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
}
