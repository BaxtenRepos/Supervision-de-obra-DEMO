package com.csgit.cao.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_ViewPager_Act_Galerias;
import com.csgit.cao.utils.Utl_Constantes;

public class Act_Galerias extends FragmentActivity{
	
	private ViewPager pager;
	private Context context;
	private FragmentManager fm;
	private Adp_ViewPager_Act_Galerias adapter;
	private long idObra;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.lyt_act_galerias);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		context = getApplicationContext();
		fm = getSupportFragmentManager();
		
		pager = (ViewPager) findViewById(R.id.vp_act_gelerias);
		pager.setOffscreenPageLimit(Utl_Constantes.NUMERO_TABS_GALERIAS);
		
		idObra = getIntent().getExtras().getLong("idObra");
		
		adapter = new Adp_ViewPager_Act_Galerias(fm, context, idObra);
		pager.setAdapter(adapter);
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
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		adapter.notifyDataSetChanged();
		pager.setAdapter(adapter);
	}

}
