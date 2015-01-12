package com.csgit.cao.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.csgit.cao.R;
import com.csgit.cao.utils.Utl_DialogFragment.DialogFragmentListener;

public class Act_Estimacion extends FragmentActivity implements DialogFragmentListener{
	
	private Bundle bundle;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.lyt_act_estimacion);
		
		bundle = getIntent().getBundleExtra("datos");
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Fragment fragmento = new Frag_Act_Estimacion();
		fragmento.setArguments(bundle);
		ft.replace(R.id.ll_act_estimacion_content, fragmento);
		ft.commit();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			if(Frag_Act_Estimacion.guardarDatos())
				finish();
			return true;		
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(Frag_Act_Estimacion.guardarDatos())
			finish();
		return true;
	}

	@Override
	public void onFinishEditDialog(String inputText) {
		// TODO Auto-generated method stub
		Frag_Act_Estimacion.setElemento(inputText, true);
	}
}