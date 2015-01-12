package com.csgit.cao.controllers;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.csgit.cao.R;
import com.csgit.cao.business.Bus_Avance_Concepto;
import com.csgit.cao.business.Bus_Conceptos;
import com.csgit.cao.model.communicationchannel.model.AditivasDeductivas;
import com.csgit.cao.model.communicationchannel.model.Concepto;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Fechas;
import com.csgit.cao.utils.Utl_Operaciones;

public class Act_Reporte_Concepto extends FragmentActivity{
	
	private static final int ID_DIALOG_AVANCE = 1000;
	private static final int ID_DIALOG_OPERACIONES = 1001;
	
	private Long idObra;
	private Long idConcepto;
	private int tipoOperacion;
	
	private Context context;
	private EditText edt_cantidadAvance;
	private double cantidadOperacion;
	private TextView txv_cantidadTotal;
	private TextView txv_precioUnitario;
	
	private Bus_Avance_Concepto bus_avanceConcepto;
	private Bus_Conceptos bus_concepto;
	private Concepto concepto;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.lyt_act_reporte_avance_concepto);
		getActionBar().setDisplayHomeAsUpEnabled(true); 
				
		context = getApplicationContext();
		Bundle bundle = getIntent().getExtras();
		idObra = bundle.getLong("idObra");
		idConcepto = bundle.getLong("idConcepto");
		
		bus_concepto = new Bus_Conceptos(context);
		bus_avanceConcepto = new Bus_Avance_Concepto(context);
		concepto = bus_avanceConcepto.getConcepto(idConcepto);
		
		edt_cantidadAvance = (EditText) findViewById(R.id.edt_cantidad_avance);
		TextView txv_avanceConcepto = (TextView) findViewById(R.id.tvx_avance_concepto);
		txv_cantidadTotal= (TextView) findViewById(R.id.tvx_cantidad_total);
		txv_precioUnitario = (TextView) findViewById(R.id.txv_precio_unitario);
		TextView txv_impote= (TextView) findViewById(R.id.txv_importe);
		TextView txv_fechaInicio = (TextView) findViewById(R.id.txv_fecha_inicio);
		TextView txv_fechaTermino= (TextView) findViewById(R.id.txv_fecha_termino);
		TextView txv_descripcion= (TextView) findViewById(R.id.txv_descripcion_concepto);
		TextView txv_unidadmedida= (TextView) findViewById(R.id.tvx_unidad_medida);
		TextView txv_cantidadProgramada  = (TextView) findViewById(R.id.txv_repCon_val_ava_programado);
		TextView txv_unidadProgramada = (TextView) findViewById(R.id.txv_repCon_val_unidad_progrmado);
		
		txv_descripcion.setText(concepto.getDescripcion());
		
		String precioFormateado = Utl_Operaciones.formatearCantidad(concepto.getPrecioUnitario());
		txv_precioUnitario.setText(precioFormateado);
		  
		String importeFormateado = Utl_Operaciones.formatearCantidad(concepto.getImporte());
		txv_impote.setText(importeFormateado);
		
		if(Utl_Fechas.isFechaValida(concepto.getFechaInicio())){
			String[] aux = concepto.getFechaInicio().split(" ");
			String inicioFormat = aux[0];
			txv_fechaInicio.setText(inicioFormat);
		}else{
			txv_fechaInicio.setText(concepto.getFechaInicio());	
		}
		if(Utl_Fechas.isFechaValida(concepto.getFechaFin())){
			String[] aux = concepto.getFechaFin().split(" ");
			String finFormat = aux[0];
			txv_fechaTermino.setText(finFormat);
		}else{
			txv_fechaTermino.setText(concepto.getFechaFin());	
		}
		
		txv_unidadmedida.setText(concepto.getUnidadMedida());
		
		String avanceFormateado = Utl_Operaciones.formatearCantidadSinSigno(concepto.getCantidadAvance());
		txv_avanceConcepto.setText(avanceFormateado);
		
		String totalFormateado = Utl_Operaciones.formatearCantidadSinSigno(concepto.getCantidadTotal());
		txv_cantidadTotal.setText(totalFormateado);
	
		// calcular catidad programada
		double cantidadProgramada = bus_avanceConcepto.getCantidadProgramada(concepto);
		String programadoFormateado = Utl_Operaciones.formatearCantidadSinSigno(cantidadProgramada);
		txv_cantidadProgramada.setText(String.valueOf(programadoFormateado));
		
		txv_unidadProgramada.setText(concepto.getUnidadMedida());
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		
		case android.R.id.home:
			if(bus_avanceConcepto.validaCantidadIngresada(edt_cantidadAvance.getText().toString())){
				Toast.makeText(context,
						getResources().getString(R.string.str_toast_rep_concepto_descardato),
						Toast.LENGTH_SHORT).show();
				finish();
			}else{
				showDialog(ID_DIALOG_AVANCE);
			}		
			return true;
			
		case R.id.menu_act_reporte_concepto_aditiva:
			tipoOperacion = Utl_Constantes.OPERACION_ADITIVA;
			showDialog(ID_DIALOG_OPERACIONES);
			break;
			
		case R.id.menu_act_reporte_concepto_deductiva:
			tipoOperacion = Utl_Constantes.OPERACION_DEDUCTIVA;
			showDialog(ID_DIALOG_OPERACIONES);
			break;
			
		case R.id.menu_act_reporte_concepto_historial:
			Intent intent = new Intent(Act_Reporte_Concepto.this, Act_Historial_Operacion.class);
			intent.putExtra("idConcepto", idConcepto);
			intent.putExtra("unidadMedida", concepto.getUnidadMedida());
			startActivity(intent);
			break;
			
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu_act_concepto_operaciones, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if(bus_avanceConcepto.validaCantidadIngresada(edt_cantidadAvance.getText().toString())){
				Toast.makeText(context,
						getResources().getString(R.string.str_toast_rep_concepto_descardato),
						Toast.LENGTH_SHORT).show();				
				finish();
			}else{
				showDialog(ID_DIALOG_AVANCE);
			}	
			break;

		default:
			break;
		}
		return true;
	}
	
	@SuppressLint("InflateParams")
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case ID_DIALOG_AVANCE:
			
			AlertDialog.Builder builderConfirmacion = new AlertDialog.Builder(this);
			builderConfirmacion.setIcon(R.drawable.ic_action_warning);
			builderConfirmacion.setCancelable(false);
			builderConfirmacion.setTitle(getResources().getString(R.string.str_dialog_reporte_concepto_titulo));
			builderConfirmacion.setMessage(getResources().getString(R.string.str_dialog_reporte_concepto_mensaje));
			builderConfirmacion.setPositiveButton(getResources().getString(R.string.str_dialog_reporte_concepto_si),
					new DialogInterface.OnClickListener() {
				
				@SuppressWarnings("deprecation")
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					double catAvance = Double.parseDouble(edt_cantidadAvance.getText().toString());
			
					if(bus_avanceConcepto.updateAvanceConcepto(idObra, idConcepto,
							catAvance, concepto.getPrecioUnitario(), concepto.getCantidadTotal(),
							concepto.getCantidadAvance(), concepto.getUnidadMedida())){
						dialog.dismiss();
						removeDialog(ID_DIALOG_AVANCE);
						Toast.makeText(context,
								getResources().getString(R.string.str_toast_rep_concepto_cantidad_guardada),
								Toast.LENGTH_SHORT).show();
						finish();
					}else{
						dialog.dismiss();
						removeDialog(ID_DIALOG_AVANCE);
						Toast.makeText(context,
								getResources().getString(R.string.str_toast_rep_concepto_cantidad_mayor),
								Toast.LENGTH_SHORT).show();
					}
				}
			});
			builderConfirmacion.setNegativeButton(getResources().getString(R.string.str_dialog_reporte_concepto_no),
					new DialogInterface.OnClickListener() {
				
				@SuppressWarnings("deprecation")
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
					removeDialog(ID_DIALOG_AVANCE);
					Toast.makeText(context,
							getResources().getString(R.string.str_toast_rep_concepto_descardato),
							Toast.LENGTH_SHORT).show();
					finish();
				}
			});
			
			return builderConfirmacion.create();
			
		case ID_DIALOG_OPERACIONES:
			AlertDialog.Builder builderOperaciones = new AlertDialog.Builder(this);
			builderOperaciones.setCancelable(false);
			
			if(tipoOperacion == Utl_Constantes.OPERACION_ADITIVA){
				builderOperaciones.setTitle(getResources().getString(R.string.str_dialog_operaciones_title_aditiva));
			}else{
				builderOperaciones.setTitle(getResources().getString(R.string.str_dialog_operaciones_title_deductiva));
			}
			LayoutInflater inflater = getLayoutInflater();
			View view = inflater.inflate(R.layout.lyt_operaciones_concepto, null);
			final EditText edt_cantidad = (EditText)view.findViewById(R.id.edt_cantidad_operacion);
			edt_cantidad.setFocusable(true);
			builderOperaciones.setView(view);
			builderOperaciones.setPositiveButton(getResources().getString
					(R.string.str_dialog_operaciones_boton_aceptar), 
					new DialogInterface.OnClickListener() {
						
						@SuppressWarnings("deprecation")
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							cantidadOperacion = 0D;
							cantidadOperacion = Double.parseDouble(String.valueOf(
									edt_cantidad.getText().toString()));
							if(bus_concepto.validarOperacion(tipoOperacion, concepto.getCantidadTotal(), cantidadOperacion)){
								saveOperacion();
							}else{
								Toast.makeText(context,
										getResources().getString(R.string.str_toast_not_save_operacion),
										Toast.LENGTH_SHORT).show();
							}
							dialog.dismiss();
							removeDialog(ID_DIALOG_OPERACIONES);
						}
					});
			builderOperaciones.setNegativeButton(getResources().getString
					(R.string.str_dialog_operaciones_boton_cancelar), 
					new DialogInterface.OnClickListener() {
						
						@SuppressWarnings("deprecation")
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
							removeDialog(ID_DIALOG_OPERACIONES);
						}
					});
			
			return builderOperaciones.create();

		default:
			break;
		}
		
		return null;
	}
	
	private void saveOperacion(){
		
		AditivasDeductivas operacion = new AditivasDeductivas();
		operacion.setIdConcepto(idConcepto);
		operacion.setTipoOperacion(tipoOperacion);
		operacion.setCantidad(cantidadOperacion);
		operacion.setFecha(Utl_Fechas.getFecha());
		
		bus_concepto.insertAditivaDeductiva(operacion);
		
		// Aplicar el cambio a la cantidad total del concepto
		int val = bus_concepto.updateCantidadConcepto(idConcepto, 
				cantidadOperacion, tipoOperacion, concepto.getCantidadTotal());
		
		if(val != 0){
			double result = bus_concepto.realizaOperacion(concepto.getCantidadTotal(), cantidadOperacion, tipoOperacion);
			concepto.setCantidadTotal(result);
			actualizaVista();
			Toast.makeText(context, 
					getResources().getString(R.string.str_toast_operacion_concepto_ok), 
					Toast.LENGTH_SHORT).show();
			
		}else{
			Toast.makeText(context, 
					getResources().getString(R.string.str_toast_operacion_concepto_error),
					Toast.LENGTH_SHORT).show();
		}
	}
	
	private void actualizaVista(){
		double nuevaCantidad = bus_concepto.getCantidadTotalActual(idConcepto);
		String nuevototal = Utl_Operaciones.formatearCantidadSinSigno(nuevaCantidad);
		txv_cantidadTotal.setText(nuevototal);
		
	}
}
