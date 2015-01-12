package com.csgit.cao.controllers;

import com.csgit.cao.R;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;


public class Act_Acerca_De extends FragmentActivity{

	private TextView txv_version;
	private Context context;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.lyt_act_acerca_de);
		
		context = getBaseContext();
		
		txv_version = (TextView) findViewById(R.id.txv_act_acerca_version);
		try {
			txv_version.setText(getResources().getString(R.string.str_act_acerca_version) + " " + 
		context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			txv_version.setText("");
			Log.i("Error Acerca de", "Error al Obtener la Versión");
		}
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
