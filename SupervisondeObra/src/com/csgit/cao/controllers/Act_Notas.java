package com.csgit.cao.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.csgit.cao.R;
import com.csgit.cao.business.Bus_Notas;
import com.csgit.cao.business.Bus_ReporteDiario;
import com.csgit.cao.model.communicationchannel.model.Notas;
import com.csgit.cao.utils.Utl_Fechas;

public class Act_Notas extends FragmentActivity{
	
	private Long idObra;
	private Long idRepoNotas;
	private boolean nuevoReporte;
	
	private EditText notaTitulo;
	private EditText notaDescr;
	
	private Notas nuevosDatos;
	
	private Bus_Notas bus_notas;
	private Bus_ReporteDiario bus_reporteDiario;	
	
	private boolean puedeEliminar = false;
	
	private Context context;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.lyt_act_notas);
		
		context =  getApplicationContext();
		
		notaTitulo = (EditText) findViewById(R.id.edt_nota_titulo);
		notaDescr = (EditText) findViewById(R.id.edt_nota_descripcion);
		
		bus_notas = new Bus_Notas(context);
		bus_reporteDiario = new Bus_ReporteDiario(context);
		nuevosDatos = new Notas();
		 
		Bundle bundle = getIntent().getBundleExtra("datos");
		idObra = bundle.getLong("idObra");
	    nuevoReporte = bundle.getBoolean("nuevoReporte");
		String titulo = bundle.getString("titulo");
		String descripcion = bundle.getString("descripcion");
		idRepoNotas = bundle.getLong("idRepoNotas");
		
	    if(!nuevoReporte){
	    	notaTitulo.setText(titulo);
	    	notaDescr.setText(descripcion);
	    	
	    	long idReporteDiario = bus_notas.getIdReporteDiarioFromNota(idRepoNotas);
	    	puedeEliminar = !bus_reporteDiario.reporteDiarioIsSync(idReporteDiario);
	    }else{
	    	puedeEliminar = true;
	    }
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu_act_notas, menu);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
			
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			guardar();
			finish();
			return true;

		case R.id.menu_act_notas_borrar_nota:

			if(nuevoReporte){
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.str_toast_notas_descartada),
						Toast.LENGTH_SHORT).show();			
				finish();
			}else if(puedeEliminar){
				int i = bus_notas.deleteNota(idRepoNotas);
				if(i != 0){
					Toast.makeText(getApplicationContext(),
							getResources().getString(R.string.str_toast_notas_eliminada),
							Toast.LENGTH_SHORT).show();
					finish();
				}
			}else{
				Toast.makeText(getApplicationContext(), 
						getResources().getString(R.string.str_act_notas_not_delete),
						Toast.LENGTH_SHORT).show();
			}

			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		guardar();
		finish();
	}
	public void guardar(){
		if(nuevoReporte){
			if(!notaDescr.getText().toString().equals("")){
				nuevosDatos.setDescripcion(notaDescr.getText().toString());
				nuevosDatos.setFecha(Utl_Fechas.getFecha());
				nuevosDatos.setTitulo(notaTitulo.getText().toString());
				bus_notas.insertNota(idObra, nuevosDatos);
				
				Toast.makeText(getBaseContext(), getResources()
						.getString(R.string.str_toast_notas_guardar),
						Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.str_toast_notas_descartada),
						Toast.LENGTH_SHORT).show();				
			}
			
		}else{
			if(!notaDescr.getText().toString().equals("")){
				nuevosDatos.setDescripcion(notaDescr.getText().toString());
				nuevosDatos.setIdRepoNotas(idRepoNotas);
				nuevosDatos.setTitulo(notaTitulo.getText().toString());
				bus_notas.updateNotas(nuevosDatos);
				
				Toast.makeText(getBaseContext(), getResources()
						.getString(R.string.str_toast_notas_guardar),
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
