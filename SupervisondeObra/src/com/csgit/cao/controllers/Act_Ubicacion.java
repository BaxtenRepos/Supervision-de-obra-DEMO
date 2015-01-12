package com.csgit.cao.controllers;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.csgit.cao.R;
import com.csgit.cao.business.Bus_Ubicacion;
import com.csgit.cao.model.communicationchannel.model.Ubicaciones;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class Act_Ubicacion extends FragmentActivity {

	private GoogleMap mMap;
	private Bus_Ubicacion busUbicacion;
	private Ubicaciones ubicaciones;
	private Long idObra;
	private String nombreObra;
//	private String area;
//	private Adp_InfoWindow_Maker infoWindow;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.lyt_act_ubicacion);

		overridePendingTransition(R.animator.activity_push_up_in,
                R.animator.push_up_out);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		busUbicacion = new Bus_Ubicacion(getContentResolver());

		Bundle bundle = getIntent().getExtras();
		idObra = bundle.getLong("idObra");
		nombreObra = bundle.getString("nombreObra");
//		area = bundle.getString("area");
		
//		infoWindow = new Adp_InfoWindow_Maker(getLayoutInflater(), nombreObra, area,"Proyecto");
		
		setUpMapIfNeeded();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
		if (mMap == null) {
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.frag_ubicacion)).getMap();
		}
		mMap.setMyLocationEnabled(true);
		
		
			ubicaciones = busUbicacion.getUbicacionObra(idObra);
			if (ubicaciones.size() != 0) {
				if (ubicaciones.getUbicacion().size() > 1) {
					if (ubicaciones
							.getUbicacion()
							.get(0)
							.equals(ubicaciones.getUbicacion().get(
									ubicaciones.getUbicacion().size() - 1))) {
						poligono(ubicaciones.getUbicacion());
					} else {
						lineas(ubicaciones.getUbicacion());
					}
				} else {
					punto(ubicaciones.getUbicacion().get(0));	
				}
			}else
				Toast.makeText(getBaseContext(),
						getResources().getString(R.string.str_act_ubicacion_nohay_puntos),
						Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case android.R.id.home:
			// NavUtils.navigateUpFromSameTask(this);
			finish();
//			Intent intent = new Intent(Act_Ubicacion.this, Act_StreetView.class);
//			startActivity(intent);
			
//			Intent streetView = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("google.streetview:cbll="+ 37.765927+","+-122.449972+"&cbp=1,99.56,,1,-5.27&mz=21"));
//			startActivity(streetView);
			return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void punto(String ubicaciones) {
		List<Double> latlon = ubicaciones(ubicaciones);
		mostrarMarcador(latlon.get(0), latlon.get(1));
	}

	public void mostrarMarcador(Double latitud, Double longitud) {
		CameraUpdate camUpd2 = CameraUpdateFactory.newLatLngZoom(new LatLng(
				latitud, longitud), 9F);
		mMap.animateCamera(camUpd2);
//		Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(latitud, longitud))
//				.title(nombreObra)
//				.snippet(getResources().getString(R.string.str_act_ubicacion_makert_descripcion) + 
//						area+", "+"Proyecto: ")
//				.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_obra)));
		
		Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(latitud, longitud))
				.title(nombreObra));
		
//		mMap.setInfoWindowAdapter(infoWindow);
//		MarkerOptions markerOptions = new MarkerOptions();
//		markerOptions.position(new LatLng(latitud, longitud));
//		markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_obra));
//		Marker marker = mMap.addMarker(markerOptions);
		marker.showInfoWindow();
	}
	
	

	/**
	 * El método poligono realiza la funcionalidad del evento lanzado por el
	 * botón 'Poligono'.
	 * 
	 * @param view
	 *            - View
	 */
	public void poligono(List<String> ubicaciones) {

		PolygonOptions opcionesPoligono = new PolygonOptions();
		for (int i = 0; i < ubicaciones.size(); i++) {
			List<Double> latlon = ubicaciones(ubicaciones.get(i));
			opcionesPoligono.add(new LatLng(latlon.get(0), latlon.get(1)));
		}
		mMap.clear();
		Polygon poligono = mMap.addPolygon(opcionesPoligono);
		poligono.setStrokeColor(Color.BLUE); // Bordes del polígono
		poligono.setStrokeWidth(2);
		poligono.setFillColor(Color.argb(20, Color.red(Color.BLUE),
				Color.green(Color.BLUE), Color.blue(Color.BLUE)));// Relleno del polígono
		punto(ubicaciones.get(0));
	}

	public void lineas(List<String> ubicaciones) {

		PolylineOptions opcionesPoliLinea = new PolylineOptions();
		for (int i = 0; i < ubicaciones.size(); i++) {
			List<Double> latlon = ubicaciones(ubicaciones.get(i));
			opcionesPoliLinea.add(new LatLng(latlon.get(0), latlon.get(1)));
		}
		opcionesPoliLinea.color(Color.BLUE);
		mMap.clear();
		Polyline polyline = mMap.addPolyline(opcionesPoliLinea);
		polyline.setColor(Color.BLUE);
		polyline.setWidth(2);
		punto(ubicaciones.get(0));
	}

	public List<Double> ubicaciones(String texto) {
		List<Double> m = new ArrayList<Double>();

		String splitUbicacion[] = texto.split(",");
		for (String LogLat : splitUbicacion) {
			m.add(Double.parseDouble(LogLat));
		}

		return m;
	}
}
