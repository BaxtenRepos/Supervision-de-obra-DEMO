package com.csgit.cao.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;
import android.widget.Toast;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Act_Estimacion;
import com.csgit.cao.adapters.Adp_Expandable_ListaConceptos;
import com.csgit.cao.business.Bus_Conceptos;
import com.csgit.cao.business.Bus_Estimacion;
import com.csgit.cao.model.Mod_ConceptoPadre;
import com.csgit.cao.model.Mod_Estimacion_Concepto;
import com.csgit.cao.model.communicationchannel.model.Concepto;
import com.csgit.cao.model.communicationchannel.model.Estimacion;
import com.csgit.cao.utils.DatePickerFragment;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_DialogFragment;
import com.csgit.cao.utils.Utl_Fechas;

@SuppressWarnings("deprecation")
@SuppressLint({ "InflateParams", "DefaultLocale" })
public class Frag_Act_Estimacion extends Fragment implements
LoaderManager.LoaderCallbacks<List<Mod_ConceptoPadre>>, OnQueryTextListener, OnCloseListener{
	
	private static long idObra;
	private static int positionPadre;
	private static int positionHijo;
	private int DATE_DIALOG_ID;
	private static boolean nuevo;
	private static long idEstimacion;
	private String fechaInicio;
	private String fechaTermino;
	private long numeroEstimacion;
	private boolean puedeEliminar = false;
	
	private static ArrayList<Mod_Estimacion_Concepto> datosLista;
	private static ArrayList<Mod_ConceptoPadre> lista;
	private static ArrayList<Estimacion> datosGuardar;
	
	private View empyView;
	private static Context context;
	private ExpandableListView expListView;
	private ListView lv_cantidades;
	private SlidingDrawer drawer;
	private static EditText edt_fecha_inicio;
	private static EditText edt_fecha_termino;
	private static EditText etd_num_estimacion;
	
	private static Bus_Conceptos bus_conceptos;
	private static Bus_Estimacion bus_estimacion;
	private Adp_Expandable_ListaConceptos adapterConceptos;
	private static Adp_Act_Estimacion adaptador;
	private Utl_DialogFragment utl_dialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActivity().supportInvalidateOptionsMenu();
		setHasOptionsMenu(true);
		
		context = getActivity();
		utl_dialog = new Utl_DialogFragment(2);
		bus_conceptos = new Bus_Conceptos(context);
		bus_estimacion = new Bus_Estimacion(context);
		lista = new ArrayList<Mod_ConceptoPadre>();
		
		Bundle bundle = getArguments();
		idObra = bundle.getLong("idObra");
		nuevo = bundle.getBoolean("nuevoReporte");
		
		if(!nuevo){
			idEstimacion = bundle.getLong("idEstimacion");
			numeroEstimacion = bundle.getLong("numero");
			fechaInicio = bundle.getString("fInicio");
			fechaTermino = bundle.getString("fTermino");
			datosLista = bus_estimacion.getEstimacion_Conceptos(idEstimacion);
			
			puedeEliminar = bus_estimacion.puedeEliminar(idEstimacion);
		}else{
			puedeEliminar = true;
			datosLista = new ArrayList<Mod_Estimacion_Concepto>();
		}
		
		datosGuardar = new ArrayList<Estimacion>();
		adapterConceptos = new Adp_Expandable_ListaConceptos(context);
		
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.lyt_frag_act_estimacion, null);
		expListView = (ExpandableListView) root.findViewById(R.id.exp_lv_conceptos_estimacion);
		drawer = (SlidingDrawer) root.findViewById(R.id.slidingDrawer_Estimacion);
		lv_cantidades = (ListView) root.findViewById(R.id.list_conceptos_ingresadas_estimacion);
		final ImageView img_collapse = (ImageView) root.findViewById(R.id.img_collapse);
		final ImageView img_expand = (ImageView) root.findViewById(R.id.img_expand);
		etd_num_estimacion = (EditText) root.findViewById(R.id.edt_numEstimacion);
		edt_fecha_inicio = (EditText) root.findViewById(R.id.edt_fechaInicioEstimacion);
		edt_fecha_termino = (EditText) root.findViewById(R.id.edt_fechaTerminoEstimacion);
		TextView fecha_handle = (TextView) root.findViewById(R.id.txv_fecha_handle_estimacion);
		fecha_handle.setText(Utl_Fechas.getFecha());
		
		if(!nuevo){
			etd_num_estimacion.setText(String.valueOf(numeroEstimacion));
			etd_num_estimacion.setEnabled(false);
			edt_fecha_inicio.setText(fechaInicio);
			edt_fecha_inicio.setEnabled(false);
			edt_fecha_termino.setText(fechaTermino);
			edt_fecha_termino.setEnabled(false);
		}

		expListView.setTextFilterEnabled(true);
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				positionPadre = groupPosition;
				positionHijo = childPosition;
				if(!checharConceptoReportado())
					showEditDialog();
				else
					Toast.makeText(context,
							context.getResources().getString(R.string.str_toast_concepto_ya_reportado), 
							Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		empyView = inflater.inflate(R.layout.lyt_empy_view, null);
		expListView.setEmptyView(empyView);
		expListView.setAdapter(adapterConceptos);
		
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
		
		edt_fecha_inicio.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus){
					DATE_DIALOG_ID = 0;
					showDatePicker();
					edt_fecha_inicio.clearFocus();
				}	
			}
		});

		edt_fecha_termino.setOnFocusChangeListener(new OnFocusChangeListener() {	
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus){
					DATE_DIALOG_ID = 1;
					showDatePicker();
					edt_fecha_termino.clearFocus();
				}
			}
		});
		
		adaptador = new Adp_Act_Estimacion(context, datosLista);
		lv_cantidades.setAdapter(adaptador);	
		lv_cantidades.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "elemnto corto", Toast.LENGTH_SHORT).show();
			}
		});
		lv_cantidades.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "elemnto largo", Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		
		return root;
	}
	
	private void eliminarConcepto(){
		
	}
	
	private boolean checharConceptoReportado(){
		Mod_ConceptoPadre padre = (Mod_ConceptoPadre) expListView.getAdapter().getItem(positionPadre);
		for (Mod_Estimacion_Concepto item : datosLista) {
			if(item.getIdConcepto() == padre.getChildren().get(positionHijo).getIdConcepto()){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean onClose() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Loader<List<Mod_ConceptoPadre>> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new DataListLoader(context, arg1);
	}

	@Override
	public void onLoadFinished(Loader<List<Mod_ConceptoPadre>> arg0,
			List<Mod_ConceptoPadre> arg1) {
		// TODO Auto-generated method stub
		adapterConceptos.setData(arg1);
		expListView.setAdapter(adapterConceptos);
		lista.clear();
		lista = (ArrayList<Mod_ConceptoPadre>) arg1;
	}

	@Override
	public void onLoaderReset(Loader<List<Mod_ConceptoPadre>> arg0) {
		// TODO Auto-generated method stub
		adapterConceptos.setData(null);
	}
	
	private static class DataListLoader extends AsyncTaskLoader<List<Mod_ConceptoPadre>>{

		private List<Mod_ConceptoPadre> mModels;
		private String filter = "";
		
		public DataListLoader(Context context, Bundle bundle) {
			super(context);
			// TODO Auto-generated constructor stub
			if(bundle != null)
				filter = bundle.getString("filter"); 
		}

		@Override
		public List<Mod_ConceptoPadre> loadInBackground() {
			// TODO Auto-generated method stub
			List<Mod_ConceptoPadre> aux = bus_conceptos.getLevelConceptos(idObra);
			if(filter == null || filter.isEmpty() || filter.endsWith("null"))
				return aux;
			
			List<Mod_ConceptoPadre> lista = new ArrayList<Mod_ConceptoPadre>();
			for (Mod_ConceptoPadre padre : aux) {
				for (Concepto hijo : padre.getChildren()) {
					if(hijo.getDescripcion().toLowerCase().contains(filter) ||
							hijo.getClave().toLowerCase().contains(filter) || 
							hijo.getDescripcion().toLowerCase().contains(filter)){
						lista.add(padre);
					}
				}
			}
			return lista;
		}
		
		@Override
		public void deliverResult(List<Mod_ConceptoPadre> data) {
			// TODO Auto-generated method stub
			if(isReset()){
				if(data != null){
					onReleaseResources(data);
				}
			}
			List<Mod_ConceptoPadre> oldApps = data;
			mModels = data;
			
			if(isStarted()){
				super.deliverResult(data);
			}
			
			if(oldApps != null){
				onReleaseResources(oldApps);
			}
		}
		
		@Override
		protected void onStartLoading() {
			// TODO Auto-generated method stub
			if(mModels != null){
				deliverResult(mModels);
			}
			
			if(takeContentChanged() || mModels == null){
				forceLoad();
			}
		}
		
		@Override
		protected void onStopLoading() {
			// TODO Auto-generated method stub
			cancelLoad();
		}
		
		@Override
		public void onCanceled(List<Mod_ConceptoPadre> data) {
			// TODO Auto-generated method stub
			super.onCanceled(data);
			onReleaseResources(data);
		}
		
		@Override
		protected void onReset() {
			// TODO Auto-generated method stub
			super.onReset();
			onStopLoading();
			
			if(mModels != null){
				onReleaseResources(mModels);
				mModels = null;
			}
		}
		
		protected void onReleaseResources(List<Mod_ConceptoPadre> apps) {}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case R.id.menu_act_estimacion_borrar:
			borrarEstimacion();
			break;
			
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.menu_act_estimacion, menu);
	}
	
	public static boolean guardarDatos(){
		if(datosGuardar.size() > 0){
			if(validarEstimacion()){
				if(nuevo){
					bus_estimacion.insertEstimacion(getDatosEstimacion());
				}else{
					bus_estimacion.insertEstimacion(getDatosEstimacion(),idEstimacion);
				}
				Toast.makeText(context, 
						context.getResources().getString(R.string.str_act_estimacion_guardado_ok), 
						Toast.LENGTH_SHORT).show();
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
	
	public static List<Estimacion> getDatosEstimacion(){
		List<Estimacion> estimaciones = new ArrayList<Estimacion>();
		for (int i = 0; i < datosGuardar.size(); i++) {
			Estimacion estimacion = new Estimacion();
			estimacion.setEstimacion(idEstimacion);
			estimacion.setIdObra(idObra);
			estimacion.setIdConcepto(datosGuardar.get(i).getIdConcepto());
			estimacion.setNumero(Long.parseLong(etd_num_estimacion.getText().toString()));
			estimacion.setFechaInicio(edt_fecha_inicio.getText().toString());
			estimacion.setFechaFin(edt_fecha_termino.getText().toString());
			estimacion.setCantidadAutorizada(datosGuardar.get(i).getCantidadAutorizada());
			estimacion.setFechaEstimacion(Utl_Fechas.getFecha());
			estimaciones.add(estimacion);
		}
		return estimaciones;
	}
	
	private static boolean validarEstimacion() {
		// TODO Auto-generated method stub
		if(etd_num_estimacion.getText().toString().length() != 0){
			if(edt_fecha_inicio.getText().toString().length() != 0 && edt_fecha_termino.getText().toString().length()!=0){
				if(Utl_Fechas.compararFechas(edt_fecha_inicio.getText().toString(), edt_fecha_termino.getText().toString())){
					return true;	
				}else{
					Toast.makeText(context, 
							context.getResources().getString(R.string.str_act_estimacion_fecha_termino_menor), 
							Toast.LENGTH_SHORT).show();
				}
			}else{
				Toast.makeText(context, 
						context.getResources().getString(R.string.str_act_estimacion_faltan_fechas), 
						Toast.LENGTH_SHORT).show();
			}
		}else{
			Toast.makeText(context, 
					context.getResources().getString(R.string.str_act_estimacion_falta_numero_estimacion),
					Toast.LENGTH_SHORT).show();
		}

		return false;
	}
	
	private void borrarEstimacion(){
		if(nuevo){
			Toast.makeText(context, 
					getResources().getString(R.string.str_toast_estimacion_eliminada),
					Toast.LENGTH_SHORT).show();
			getActivity().finish();
		}else if(puedeEliminar){
			datosGuardar.clear();
			for (Mod_Estimacion_Concepto item : datosLista) {
				bus_estimacion.deleteEstimacion(item.getIdEstimacion());
			}
			Toast.makeText(context, 
					getResources().getString(R.string.str_toast_estimacion_eliminada),
					Toast.LENGTH_SHORT).show();
			getActivity().finish();
		}else{
			Toast.makeText(context, 
					getResources().getString(R.string.str_toast_not_delete_estimacion),
					Toast.LENGTH_SHORT).show();
		}
	}
	
	private void showEditDialog() {
		FragmentManager fm = getActivity().getSupportFragmentManager();
		utl_dialog.setTitulo("Cantidad Autorizada");
		utl_dialog.show(fm, "fragment_edit_name");
	}

	private void showDatePicker() {
		DatePickerFragment date = new DatePickerFragment();
		/**
		 * Set Up Current Date Into dialog
		 */
		Calendar calender = Calendar.getInstance();
		Bundle args = new Bundle();
		args.putInt("year", calender.get(Calendar.YEAR));//
		args.putInt("month",calender.get(Calendar.MONTH));//
		args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));//
		date.setArguments(args);
		/**
		 * Set Call back to capture selected date
		 */
		date.setCallBack(ondate);
		date.show(getActivity().getSupportFragmentManager(), "Date Picker");
	}
	
	DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			SimpleDateFormat formateador =  new SimpleDateFormat(Utl_Constantes.FOR_FECHA, 
					getResources().getConfiguration().locale);
			Date fechaDate = new Date(view.getCalendarView().getDate());
			String fecha = formateador.format(fechaDate);

			switch (DATE_DIALOG_ID) {
			case 0:
				edt_fecha_inicio.setText(fecha);
				break;
			case 1:
				edt_fecha_termino.setText(fecha);
				break;

			default:
				break;
			}
		}
	};
	
	public static void setElemento(String inputText, boolean action){
		if(action){// nuevo 
			if(!inputText.isEmpty()){
				Mod_Estimacion_Concepto cantidades = new Mod_Estimacion_Concepto();
				cantidades.setIdConcepto(lista.get(positionPadre).getChildren().get(positionHijo).getIdConcepto());
				cantidades.setDescripcion(lista.get(positionPadre).getChildren().get(positionHijo).getDescripcion());
				cantidades.setCantidadAutorizada(Double.parseDouble(inputText));
				datosLista.add(cantidades);
				
				Estimacion estimacion_concepto = new Estimacion();
				estimacion_concepto.setIdConcepto(lista.get(positionPadre).getChildren().get(positionHijo).getIdConcepto());
				estimacion_concepto.setCantidadAutorizada(Long.parseLong(inputText));
				datosGuardar.add(estimacion_concepto);
				adaptador.notifyDataSetChanged();	
			}
			
		}else{// editar
			
		}
	}
}
