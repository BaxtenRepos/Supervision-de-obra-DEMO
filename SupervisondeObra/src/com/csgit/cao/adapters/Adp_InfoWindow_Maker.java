package com.csgit.cao.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.csgit.cao.R;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

public class Adp_InfoWindow_Maker implements InfoWindowAdapter{

	private TextView txv_obra;
	private TextView txv_area;
	private TextView txv_proyecto;
	private View view;
	private LayoutInflater inflater;
	private String obra;
	private String area;
	private String proyecto;
	
	public Adp_InfoWindow_Maker(LayoutInflater inflater, String obra, String area, String proyecto){
		this.inflater = inflater;
		this.obra = obra;
		this.area = area;
		this.proyecto = proyecto;
	}
	
	@Override
	public View getInfoContents(Marker marker) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getInfoWindow(Marker marker) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.info_window_layout, null);
		
		txv_obra = (TextView) view.findViewById(R.id.txv_marker_obra);
		txv_area = (TextView) view.findViewById(R.id.txv_marker_area);
		txv_proyecto = (TextView) view.findViewById(R.id.txv_marker_proyecto);
		
		txv_obra.setText(obra);
		txv_area.setText(area);
		txv_proyecto.setText(proyecto);
		
		return view;
	}

}
