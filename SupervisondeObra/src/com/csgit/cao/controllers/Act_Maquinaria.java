package com.csgit.cao.controllers;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Array_Spinner_Tipo_Maquinaria;
import com.csgit.cao.adapters.Adp_Base_Maquinaria_Gallery;
import com.csgit.cao.adapters.Adp_Base_Reporte_Cantidades;
import com.csgit.cao.business.Bus_Maquinaria;
import com.csgit.cao.business.Bus_ReporteDiario;
import com.csgit.cao.model.Mod_Repo_Maquinaria_Cat_Maquinaria;
import com.csgit.cao.model.communicationchannel.model.CatTipoMaquinaria;
import com.csgit.cao.model.communicationchannel.model.Maquinaria;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_DialogFragment;
import com.csgit.cao.utils.Utl_DialogFragment.DialogFragmentListener;
import com.csgit.cao.utils.Utl_Fechas;

@SuppressWarnings("deprecation")
public class Act_Maquinaria extends FragmentActivity implements DialogFragmentListener{

	private static final int ID_DIALOG_DELETE = 101;
		
	private ArrayList<CatTipoMaquinaria> familia;
	private ArrayList<Maquinaria> categoria;
	private ArrayList<Maquinaria> auxiliar = new ArrayList<Maquinaria>();
	
	private ArrayList<Mod_Repo_Maquinaria_Cat_Maquinaria> datosLista;
	private ArrayList<Mod_Repo_Maquinaria_Cat_Maquinaria> datosDelete;
	
	private Bus_Maquinaria bus_maquinaria;
	private Bus_ReporteDiario bus_reporteDiario;
	
	private boolean nuevoReporte;
	private int tipoAcction;
	
	private SlidingDrawer drawer;
	private GridView gridView;
	private ListView lv_cantidades;
	private TextView fecha_handle;
	private ImageView img_collapse;
	private ImageView img_expand;
	private Spinner spinner1;
	
	private Adp_Base_Reporte_Cantidades adaptador;
	private Adp_Base_Maquinaria_Gallery adp_gallery;
	private Adp_Array_Spinner_Tipo_Maquinaria dataAdapter;
	
	private int positionAdd;
	private int positionEdit;
	private int positionDelete;
	
	private boolean puedeEliminar = false;
	private Long idObra;
	private long idReporteDiario;
	private Context context;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.lyt_act_maquinaria);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		context = getApplicationContext();
		
		Bundle bundle = getIntent().getBundleExtra("datos");
		idObra = bundle.getLong("idObra");
		idReporteDiario = bundle.getLong("idReporteDiario");
		nuevoReporte = bundle.getBoolean("nuevoReporte");
		
		spinner1 = (Spinner) findViewById(R.id.spn_familiaMaqui);
		gridView = (GridView)findViewById(R.id.grid_view_maquinaria);
		lv_cantidades = (ListView)findViewById(R.id.list_cantidades_ingresadas);
		img_collapse = (ImageView)findViewById(R.id.img_collapse);
		img_expand = (ImageView)findViewById(R.id.img_expand);
		fecha_handle = (TextView)findViewById(R.id.txv_fecha_handle);
		
		bus_maquinaria = new Bus_Maquinaria(context);
		bus_reporteDiario = new Bus_ReporteDiario(context);
		
		fecha_handle.setText(Utl_Fechas.getFecha());
		
		familia = bus_maquinaria.getTipoMaquinaria();
		categoria = bus_maquinaria.getCatMaquinaria();
		
		
		datosDelete = new ArrayList<Mod_Repo_Maquinaria_Cat_Maquinaria>();
		
		if(arg0 != null){
			datosLista = arg0.getParcelableArrayList("datosLista");
		}else{
			if(!nuevoReporte){
				datosLista = bus_maquinaria.getReportesMaquinaria(idObra, idReporteDiario);
				puedeEliminar = !bus_reporteDiario.reporteDiarioIsSync(datosLista.get(0).getIdReporteDiario());
			}else{
				datosLista = new ArrayList<Mod_Repo_Maquinaria_Cat_Maquinaria>();
				puedeEliminar = true;
			}

		}
		
		drawer = (SlidingDrawer)findViewById(R.id.slidingDrawer);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				positionAdd = position;
				tipoAcction = Utl_Constantes.TIPO_ACCION_INSERT;
				if(validarAddMaquinaria())
					showEditDialog(tipoAcction);
				else
					Toast.makeText(context, getResources().
							getString(R.string.str_toast_cantidad_reporte_existente_maquinaria), 
							Toast.LENGTH_SHORT).show();
				
			}
		});
		
		drawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
			@Override
			public void onDrawerOpened() {
				img_collapse.setVisibility(View.GONE);
				img_expand.setVisibility(View.VISIBLE);
				
			}
		});
		
		drawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				img_expand.setVisibility(View.GONE);
				img_collapse.setVisibility(View.VISIBLE);
			}
		});
		
		adaptador = new Adp_Base_Reporte_Cantidades(getBaseContext(), datosLista);
		lv_cantidades.setAdapter(adaptador);
		lv_cantidades.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view,
        			int position, long id) {
        		// TODO Auto-generated method stub
        		positionEdit = position;
        		tipoAcction = Utl_Constantes.TIPO_ACCION_UPDATE;
    			showEditDialog(tipoAcction);
        	}
        	
		});
		lv_cantidades.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				positionDelete = position;
				showDialog(ID_DIALOG_DELETE);
				return true;
			}
		});
	
		dataAdapter = new Adp_Array_Spinner_Tipo_Maquinaria(this, android.R.layout.simple_spinner_item, familia);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(dataAdapter);
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id){
				CatTipoMaquinaria tipoMaq = dataAdapter.getItem(position);
				auxiliar.clear();
				auxiliar = filtrarMaquinaria(tipoMaq.getIdTipoMaquinaria(), categoria);
				adp_gallery = new Adp_Base_Maquinaria_Gallery(getBaseContext(), auxiliar);
				gridView.setAdapter(adp_gallery);
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> adapterView){
				//nothing				
			}
		});	
		
		
		//el número de columnas se calculará en función del tamaño de pantalla y la posición
		boolean bigScreen = (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
		
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			if (bigScreen){
				gridView.setNumColumns(5);
			}else{
				gridView.setNumColumns(4);
			}
		}else{if (bigScreen){
				gridView.setNumColumns(4);
			}else{
				gridView.setNumColumns(3);
			}
		}
		
		if(!puedeEliminar){
			gridView.setEnabled(false);
			lv_cantidades.setEnabled(false);
		}
	
	}
	
	private boolean validarAddMaquinaria(){
		if(datosLista.size() > 0){
			long idMaqui = auxiliar.get(positionAdd).getIdMaquinaria();
			for (Mod_Repo_Maquinaria_Cat_Maquinaria maqui : datosLista) {
				if(maqui.getIdCatMaquinaria() == idMaqui)
					return false;
			}	
		}
		return true;
	}
	
	private void showEditDialog(int tipo) {
		
		FragmentManager fm = getSupportFragmentManager();
        Utl_DialogFragment editNameDialog = new Utl_DialogFragment(1);
        
		switch (tipo) {
		case Utl_Constantes.TIPO_ACCION_INSERT:
			editNameDialog.setTitulo(context.getResources()
					.getString(R.string.str_act_maquinaria_dialog_title_cantidad_insert));	
			break;
		case Utl_Constantes.TIPO_ACCION_UPDATE:
			editNameDialog.setTitulo(context.getResources()
					.getString(R.string.str_act_maquinaria_dialog_title_cantidad_update));
			break;

		default:
			break;
		}
		editNameDialog.show(fm, "fragment_edit_name");
        
    }
	
    @Override
    public void onFinishEditDialog(String inputText) {
    	
    	if(!inputText.isEmpty()){
    		switch (tipoAcction) {
			case Utl_Constantes.TIPO_ACCION_INSERT:
				Mod_Repo_Maquinaria_Cat_Maquinaria cantidades = new Mod_Repo_Maquinaria_Cat_Maquinaria();
	    		cantidades.setIdCatMaquinaria(auxiliar.get(positionAdd).getIdMaquinaria());
	    		cantidades.setNombre(auxiliar.get(positionAdd).getDescripcion());
	    		cantidades.setCantidad(Long.parseLong(inputText));
	    		cantidades.setAuxAccion(Utl_Constantes.TIPO_ACCION_INSERT);
	    		datosLista.add(cantidades);
	    		adaptador.notifyDataSetChanged();
				break;
				
			case Utl_Constantes.TIPO_ACCION_UPDATE:
//				Mod_Repo_Maquinaria_Cat_Maquinaria mod = datosLista.get(positionEdit);
//				for(int i = 0;i<datosLista.size();i++){
//					if(datosLista.get(i).getIdCatMaquinaria() == mod.getIdCatMaquinaria()){
//						datosLista.get(i).setCantidad(Long.parseLong(inputText));
//						
//						if(datosLista.get(i).getAuxAccion() == Constantes.TIPO_ACCION_INSERT)
//							datosLista.get(i).setAuxAccion(Constantes.TIPO_ACCION_INSERT);
//						else
//							datosLista.get(i).setAuxAccion(Constantes.TIPO_ACCION_UPDATE);
//						
//						adaptador.notifyDataSetChanged();
//						break;
//					}
//				}
				
				// temporal
//				Mod_Repo_Maquinaria_Cat_Maquinaria mod = datosLista.get(positionEdit);
//				for(int i = 0;i<datosLista.size();i++){
//					if(datosLista.get(i).getIdCatMaquinaria() == mod.getIdCatMaquinaria()){
						datosLista.get(positionEdit).setCantidad(Long.parseLong(inputText));
						
						if(datosLista.get(positionEdit).getAuxAccion() == Utl_Constantes.TIPO_ACCION_INSERT)
							datosLista.get(positionEdit).setAuxAccion(Utl_Constantes.TIPO_ACCION_INSERT);
						else
							datosLista.get(positionEdit).setAuxAccion(Utl_Constantes.TIPO_ACCION_UPDATE);
						
						adaptador.notifyDataSetChanged();
//						break;
//					}
//				}
				
				
				
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
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	// TODO Auto-generated method stub
    	outState.putParcelableArrayList("datosLista", datosLista);
    	super.onSaveInstanceState(outState);
    }
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			guardarReporte();			
			finish();
			break;

		default:
			break;
		}
		return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu_act_maquinaria, menu);
		if(!puedeEliminar){
			menu.findItem(R.id.menu_act_maquinaria_borrar).setEnabled(false);
		}
		return true;
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case ID_DIALOG_DELETE:
			AlertDialog.Builder personalBulder = new AlertDialog.Builder(this);
			personalBulder.setMessage(getResources().getString(R.string.str_act_maquinaria_dialog_delete_mensaje));
			personalBulder.setCancelable(false);
			personalBulder.setPositiveButton(getResources()
					.getString(R.string.str_act_maquinaria_dialog_delete_si),
					new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							deleteMaquinaria();
							dialog.dismiss();
						}
					});
			personalBulder.setNegativeButton(getResources()
					.getString(R.string.str_act_maquinaria_dialog_delete_no), 
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
	
	
//	public void deleteMaquinaria(){
//		Mod_Repo_Maquinaria_Cat_Maquinaria maquinaSelect = datosLista.get(positionDelete); 
//		
//		for(int k=0; k < datosLista.size(); k++){
//			if(datosLista.get(k).getIdCatMaquinaria() == maquinaSelect.getIdCatMaquinaria()){
//				datosDelete.add(datosLista.get(k));
//				datosLista.remove(k);
//				adaptador.notifyDataSetChanged();
//				break;
//			}
//		}
//		
//	}
	
	public void deleteMaquinaria(){
//		Mod_Repo_Maquinaria_Cat_Maquinaria maquinaSelect = datosLista.get(positionDelete); 
		
//		for(int k=0; k < datosLista.size(); k++){
//			if(datosLista.get(k).getIdCatMaquinaria() == maquinaSelect.getIdCatMaquinaria()){
				datosDelete.add(datosLista.get(positionDelete));
				datosLista.remove(positionDelete);
				adaptador.notifyDataSetChanged();
//				break;
//			}
//		}
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			guardarReporte();			
			finish();
			return true;
			
		case R.id.menu_act_maquinaria_borrar:
			if(nuevoReporte){
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.str_toast_reporte_descardado), 
						Toast.LENGTH_SHORT).show();
				finish();
			}else{
				for (Mod_Repo_Maquinaria_Cat_Maquinaria mac : datosLista) {
					bus_maquinaria.deleteReporteMaquinaria(
							mac.getIdRepoMaquinariaCatMaquinaria(), mac.getIdReporteDiario());
					
				}
				for (Mod_Repo_Maquinaria_Cat_Maquinaria macDelete : datosDelete) {
					bus_maquinaria.deleteReporteMaquinaria(
							macDelete.getIdRepoMaquinariaCatMaquinaria(), macDelete.getIdReporteDiario());
					
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
	
	public void guardarReporte(){
		List<Mod_Repo_Maquinaria_Cat_Maquinaria> insert = new ArrayList<Mod_Repo_Maquinaria_Cat_Maquinaria>();
		List<Mod_Repo_Maquinaria_Cat_Maquinaria> update = new ArrayList<Mod_Repo_Maquinaria_Cat_Maquinaria>();
		for (Mod_Repo_Maquinaria_Cat_Maquinaria mod : datosLista) {
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
		bus_maquinaria.insertReporteMaquinaria(idObra, insert);
		bus_maquinaria.updateReporteMaquinaria(update);
		
		if(insert.size() != 0 || update.size() != 0)
			Toast.makeText(getBaseContext(), getResources()
					.getString(R.string.str_toast_rep_maquinaria_guaradar),
					Toast.LENGTH_SHORT).show();
		
		for (Mod_Repo_Maquinaria_Cat_Maquinaria itemDelete : datosDelete) {
			bus_maquinaria.deleteReporteMaquinaria(itemDelete.getIdRepoMaquinariaCatMaquinaria(),
					itemDelete.getIdReporteDiario());
		}
		
	}

	public ArrayList<Maquinaria> filtrarMaquinaria(Long idTipoMaquinaria, ArrayList<Maquinaria> categoria) {
		// TODO Auto-generated method stub
		ArrayList<Maquinaria> maqui = new ArrayList<Maquinaria>();
		for (int i = 0; i < categoria.size(); i++) {
			if(categoria.get(i).getIdTipoMaquinaria() == idTipoMaquinaria){
				Maquinaria temp = categoria.get(i);
				maqui.add(temp);
			}
		}
		return maqui;
	}

}
