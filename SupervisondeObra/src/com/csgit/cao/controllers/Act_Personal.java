package com.csgit.cao.controllers;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Array_Spinner_Personal;
import com.csgit.cao.adapters.Adp_Reporte_Personal;
import com.csgit.cao.business.Bus_Personal;
import com.csgit.cao.business.Bus_ReporteDiario;
import com.csgit.cao.model.Mod_Repo_Personal_Cat_Personal;
import com.csgit.cao.model.communicationchannel.model.CatPersonal;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_DialogFragment;
import com.csgit.cao.utils.Utl_DialogFragment.DialogFragmentListener;

public class Act_Personal extends FragmentActivity implements DialogFragmentListener{
	
	private static final int ID_DIALOG_DELETE = 100;
	
	private ArrayList<CatPersonal> categoria;
	private List<Mod_Repo_Personal_Cat_Personal> datosLista;
	private List<Mod_Repo_Personal_Cat_Personal> datosDelete;
	
	private Long idObra;
	private long idReporteDiario;
	
	private Bus_Personal bus_personal;
	private Bus_ReporteDiario bus_reporteDiario;
	
	private boolean nuevoReporte;
	private int tipoAcction;
	
	private ListView listView;
	private Adp_Reporte_Personal adapterPersonal;
	
	private int positionEdit;
	private int positionDelete;
	
	private boolean puedeEliminar = false;
	
	private Context context;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.lyt_act_personal);
		getActionBar().setDisplayHomeAsUpEnabled(true); 
		
		context = getApplicationContext();
		
		listView = (ListView) findViewById(R.id.lv_act_personal_cantidades);
		datosLista = new ArrayList<Mod_Repo_Personal_Cat_Personal>();
		datosDelete = new ArrayList<Mod_Repo_Personal_Cat_Personal>();
		
		adapterPersonal = new Adp_Reporte_Personal(getApplicationContext(), datosLista);
		listView.setAdapter(adapterPersonal);
		
		final Spinner spinner1 = (Spinner) findViewById(R.id.spn_categoriaPersonal);
		final EditText edt_cantidadP = (EditText) findViewById(R.id.edt_cantidadPersonal);
		Button btn_agregarPersonal = (Button) findViewById(R.id.btn_AgregarPersonal);
        
		Bundle bundle = getIntent().getBundleExtra("datos");
		idObra = bundle.getLong("idObra");
		idReporteDiario = bundle.getLong("idReporteDiario");
		nuevoReporte = bundle.getBoolean("nuevoReporte");
		
		bus_personal = new Bus_Personal(context);
		bus_reporteDiario = new Bus_ReporteDiario(context);
		  
		if(!nuevoReporte){
		    datosLista = bus_personal.getReportesPersonal(idObra, idReporteDiario);
		    adapterPersonal = new Adp_Reporte_Personal(getApplicationContext(), datosLista);
		    listView.setAdapter(adapterPersonal);
		    adapterPersonal.notifyDataSetChanged();
		    
		    puedeEliminar = !bus_reporteDiario.reporteDiarioIsSync(datosLista.get(0).getIdReporteDiario());
		}else{
			puedeEliminar = true;
		}
		
		categoria = bus_personal.getCatPersonal();
		final Adp_Array_Spinner_Personal dataAdapter = new Adp_Array_Spinner_Personal(this,
				android.R.layout.simple_spinner_item, categoria);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(dataAdapter);
		
		btn_agregarPersonal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(edt_cantidadP.getText().toString().trim().isEmpty()){
					Toast.makeText(getApplicationContext(),
							getResources().getString(R.string.str_toast_cantidad_reporte_cantidad_faltante),
							Toast.LENGTH_SHORT).show();
				}else{
					boolean bandera = false;
					for (int i = 0 ;i < datosLista.size(); i++) {
						if(datosLista.get(i).getIdCatPersonal() == 
								dataAdapter.getItem(spinner1.getSelectedItemPosition()).getIdTipoPersonal()){
							bandera = true;
							Toast.makeText(getApplicationContext(), 
									getResources().getString(R.string.str_toast_cantidad_reporte_existente),
									Toast.LENGTH_SHORT).show();
							break;
						}
					}
					if(!bandera){
						Mod_Repo_Personal_Cat_Personal cantidades = new Mod_Repo_Personal_Cat_Personal();
						cantidades.setNombre(dataAdapter.getItem(spinner1.getSelectedItemPosition()).getTipoPersonal());
						cantidades.setIdCatPersonal(dataAdapter.getItem(spinner1.getSelectedItemPosition()).getIdTipoPersonal());
						cantidades.setCantidad(Long.parseLong(edt_cantidadP.getText().toString()));
						cantidades.setAuxAccion(Utl_Constantes.TIPO_ACCION_INSERT);
						
						datosLista.add(cantidades);
						adapterPersonal.notifyDataSetChanged();
					}
					
				}
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				positionEdit = position;
				tipoAcction = Utl_Constantes.TIPO_ACCION_UPDATE;
				showEditDialog(tipoAcction);
			}
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				positionDelete = position;
				showDialog(ID_DIALOG_DELETE);
				return true;
			}
		});
		
		if(!puedeEliminar){
			btn_agregarPersonal.setEnabled(false);
		}
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			guardarReporte();
			finish();
			return true;

		case R.id.menu_act_personal_borrar:
			if(nuevoReporte){
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.str_toast_reporte_descardado),
						Toast.LENGTH_SHORT).show();
				finish();
			}else{
				for (Mod_Repo_Personal_Cat_Personal val : datosLista) {
					bus_personal.deleteReportePersonal(val.getIdRepoPersonalCatPersonal(),
							val.getIdReporteDiario());

				}
				for (Mod_Repo_Personal_Cat_Personal valDelete : datosDelete) {
					bus_personal.deleteReportePersonal(valDelete.getIdRepoPersonalCatPersonal(),
							valDelete.getIdReporteDiario());

				}

				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.str_toast_reporte_eliminado),
						Toast.LENGTH_SHORT).show();
			}

			finish();

			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case ID_DIALOG_DELETE:
			AlertDialog.Builder personalBulder = new AlertDialog.Builder(this);
			personalBulder.setMessage(getResources().getString(R.string.str_act_personal_dialog_delete_mensaje));
			personalBulder.setCancelable(false);
			personalBulder.setPositiveButton(getResources()
					.getString(R.string.str_act_personal_dialog_delete_si),
					new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
							deletePersonal();
							dialog.dismiss();
						}
					});
			personalBulder.setNegativeButton(getResources()
					.getString(R.string.str_act_personal_dialog_delete_no), 
					new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();					
						}
					});
			
			return personalBulder.create();

		default:
			break;
		}
		return null;
	}
	

	private void deletePersonal(){

		datosDelete.add(datosLista.get(positionDelete));
		datosLista.remove(positionDelete);
		adapterPersonal.notifyDataSetChanged();

	}
	
	private void guardarReporte(){
		List<Mod_Repo_Personal_Cat_Personal> insert = new ArrayList<Mod_Repo_Personal_Cat_Personal>();
		List<Mod_Repo_Personal_Cat_Personal> update = new ArrayList<Mod_Repo_Personal_Cat_Personal>();
		
		for (Mod_Repo_Personal_Cat_Personal mod : datosLista) {
			switch (mod.getAuxAccion()) {
			case Utl_Constantes.TIPO_ACCION_INSERT:
				insert.add(mod);
				break;
			case Utl_Constantes.TIPO_ACCION_UPDATE:
				update.add(mod);
				break;

			default:
				break;
			}
		}
	
		bus_personal.insertRepoPersonal(idObra, insert);
		bus_personal.updateReporteMaquinaria(update);
		
		if(insert.size() != 0 || update.size() != 0)
			Toast.makeText(getBaseContext(), getResources()
					.getString(R.string.str_toast_rep_maquinaria_guaradar),
					Toast.LENGTH_SHORT).show();
		
		for (Mod_Repo_Personal_Cat_Personal itemDelete : datosDelete) {
			bus_personal.deleteReportePersonal(itemDelete.getIdRepoPersonalCatPersonal(),
					itemDelete.getIdReporteDiario());
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			guardarReporte();
			finish();
			return true;

		default:
			break;
		}
		return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu_act_personal, menu);
		if(!puedeEliminar)
			menu.findItem(R.id.menu_act_personal_borrar).setEnabled(false);
		return true;
	}
	
	private void showEditDialog(int tipo) {
		
        FragmentManager fm = getSupportFragmentManager();
        Utl_DialogFragment editNameDialog = new Utl_DialogFragment(1);
        editNameDialog.setTitulo("Nueva Cantidad");
        editNameDialog.show(fm, "fragment_edit_cantidad");
    }
	
	@Override
	public void onFinishEditDialog(String inputText) {
		// TODO Auto-generated method stub
		if(!inputText.isEmpty()){
			switch (tipoAcction) {
			case Utl_Constantes.TIPO_ACCION_UPDATE:

				datosLista.get(positionEdit).setCantidad(Long.parseLong(inputText));
				if(datosLista.get(positionEdit).getAuxAccion() == Utl_Constantes.TIPO_ACCION_INSERT)
					datosLista.get(positionEdit).setAuxAccion(Utl_Constantes.TIPO_ACCION_INSERT);
				else
					datosLista.get(positionEdit).setAuxAccion(Utl_Constantes.TIPO_ACCION_UPDATE);

				adapterPersonal.notifyDataSetChanged();

				break;

			default:
				break;
			}
		}else{
			Toast.makeText(getApplicationContext(),
					getResources().getString(R.string.str_toast_cantidad_reporte_vacia),
					Toast.LENGTH_SHORT).show();
		}
	}
	
	
}
