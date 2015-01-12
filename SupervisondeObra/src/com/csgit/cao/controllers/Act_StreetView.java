package com.csgit.cao.controllers;

import com.csgit.cao.R;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

public class Act_StreetView extends FragmentActivity{

	private static final LatLng SAN_FRAN = new LatLng(37.765927, -122.449972);
	private StreetViewPanorama streeView;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.lyt_act_street_view);
		
		
		if(streeView == null){
			streeView = ((SupportStreetViewPanoramaFragment) getSupportFragmentManager()
					.findFragmentById(R.id.streetviewpanorama)).getStreetViewPanorama();
			if(streeView != null){
				if(arg0 == null){
					streeView.setPosition(SAN_FRAN);
				}
			}
		}
	}
}
