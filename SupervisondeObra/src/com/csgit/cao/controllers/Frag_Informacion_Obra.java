package com.csgit.cao.controllers;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.csgit.cao.R;
import com.csgit.cao.adapters.Adp_Frag_Informacion_Obra;
import com.csgit.cao.business.Bus_Obras;
import com.csgit.cao.model.Mod_Informacion_Obra;
import com.csgit.cao.model.communicationchannel.model.AvanceFinanciero;
import com.csgit.cao.model.communicationchannel.model.AvanceFisico;
import com.csgit.cao.model.communicationchannel.model.Obra;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Operaciones;

public class Frag_Informacion_Obra extends Fragment{

	private long idObra;
	
	private Bus_Obras bus_obra;
	private Obra obra;
	private Context context;
	
	private ListView lv_info_obra;
	private ImageButton imgBtn_verMas;
	private ImageButton imgBtn_ocultarInfo;
	
	private LinearLayout ll_ver_mas;
	
	private Adp_Frag_Informacion_Obra adapterInfo;
	
	private List<Mod_Informacion_Obra> infoVerMas;
	
	private Typeface fontLabel;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActivity().supportInvalidateOptionsMenu();
		setHasOptionsMenu(true);
		
		context = getActivity();
		bus_obra = new Bus_Obras(context);
		
		Bundle bundle = getArguments();
		idObra = bundle.getLong("idObra");
		obra = bus_obra.getInfomacionObras(idObra);
		
		List<Mod_Informacion_Obra> info = getInfo(obra);
		adapterInfo = new Adp_Frag_Informacion_Obra(context, R.layout.lyt_adp_informacion_obra, info);
		
		infoVerMas = getInfoVerMas(obra);
		
		fontLabel = Typeface.createFromAsset(this.context.getAssets(), "FRAMDCN.TTF");
	}
	
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.lyt_frag_informacion_obra, null);

		lv_info_obra = (ListView) root.findViewById(R.id.lv_frag_info_obra);
		lv_info_obra.setAdapter(adapterInfo);
		ll_ver_mas = (LinearLayout) root.findViewById(R.id.ll_frag_info_obra_ver_mas_content);
		imgBtn_ocultarInfo = (ImageButton) root.findViewById(R.id.imgbtn_frag_info_obras_ocultar_info);
		imgBtn_verMas = (ImageButton) root.findViewById(R.id.imgbtn_frag_info_obras_ver_mas);
		imgBtn_verMas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				animar(true);
				mostrarInfoCompleta();
				imgBtn_verMas.setVisibility(View.GONE);
				imgBtn_ocultarInfo.setVisibility(View.VISIBLE);
				ll_ver_mas.setVisibility(View.VISIBLE);
			}
		});
		
		imgBtn_ocultarInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imgBtn_verMas.setVisibility(View.VISIBLE);
				imgBtn_ocultarInfo.setVisibility(View.GONE);
				ll_ver_mas.setVisibility(View.GONE);
				ll_ver_mas.removeAllViews();
			}
		});
		
		
		TextView detallePorctFisico = (TextView)root.findViewById(R.id.txv_detalle_porctAvanFisico);
		TextView detallePorctFinanciero = (TextView)root.findViewById(R.id.txv_detalle_porctAvanFinanciero);
		
		AvanceFisico fisico = bus_obra.getInfomacionAvanceFisico(idObra);
		if(fisico.getEstado() != null){
			switch (fisico.getEstado()) {
			case Utl_Constantes.ESTADO_ADELANTADO:
				detallePorctFisico.setTextColor(Utl_Constantes.COLOR_VERDE);
				break;
			case Utl_Constantes.ESTADO_CONFORME_PROGRAMA:
				detallePorctFisico.setTextColor(Utl_Constantes.COLOR_NARANJA);
				break;
			case Utl_Constantes.ESTADO_ATRASADO:
				detallePorctFisico.setTextColor(Utl_Constantes.COLOR_ROJO);
				break;

			default:
				break;
			}
			detallePorctFisico.setText(Utl_Operaciones.redondearValor(fisico.getPavanceFisico(), 2) + Utl_Constantes.CARAC_PORCENTAJE);
		}else{
			detallePorctFisico.setTextColor(Utl_Constantes.COLOR_NARANJA);
			detallePorctFisico.setText(Utl_Operaciones.redondearValor(0, 2) + Utl_Constantes.CARAC_PORCENTAJE);
		}
		
		AvanceFinanciero financiero = bus_obra.getInfomacionAvanceFinanciero(idObra);
		if(financiero.getEstado() != null){
			switch (financiero.getEstado()) {
			case Utl_Constantes.ESTADO_ADELANTADO:
				detallePorctFinanciero.setTextColor(Utl_Constantes.COLOR_VERDE);
				break;
			case Utl_Constantes.ESTADO_CONFORME_PROGRAMA:
				detallePorctFinanciero.setTextColor(Utl_Constantes.COLOR_NARANJA);
				break;
			case Utl_Constantes.ESTADO_ATRASADO:
				detallePorctFinanciero.setTextColor(Utl_Constantes.COLOR_ROJO);
				break;

			default:
				break;
			}
			detallePorctFinanciero.setText(Utl_Operaciones.redondearValor(financiero.getPavanceFinanciero(), 2) + Utl_Constantes.CARAC_PORCENTAJE);
		}else{
			detallePorctFinanciero.setTextColor(Utl_Constantes.COLOR_NARANJA);
			detallePorctFinanciero.setText(Utl_Operaciones.redondearValor(0, 2) + Utl_Constantes.CARAC_PORCENTAJE);
		}
		
		return root;
	}
	
	@SuppressLint("InflateParams")
	private void mostrarInfoCompleta(){
		
		for(int i = 0; i < infoVerMas.size(); i++){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LinearLayout ll_mas = (LinearLayout) inflater.inflate(R.layout.lyt_adp_informacion_obra	, null, false);
			TextView titulo = (TextView) ll_mas.findViewById(R.id.txv_frag_info_obra_title);
			titulo.setVisibility(View.GONE);
			TextView label =(TextView) ll_mas.findViewById(R.id.txv_frag_info_obra_label);
			label.setText(infoVerMas.get(i).getLabel());
			label.setTypeface(fontLabel);
			TextView valor = (TextView) ll_mas.findViewById(R.id.txv_frag_info_obra_valor);
			valor.setText(infoVerMas.get(i).getValor());
			
			View view = (View) inflater.inflate(R.layout.lyt_separator, null, false);
		
			ll_ver_mas.addView(ll_mas);
			ll_ver_mas.addView(view);
		}
	}
	
	private List<Mod_Informacion_Obra> getInfo(Obra datos){
		List<Mod_Informacion_Obra> listInfo = new ArrayList<Mod_Informacion_Obra>();
		
		listInfo.add(new Mod_Informacion_Obra(datos.getNombre()));
		
		listInfo.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_num_contrato), datos.getNoContrato()));
		listInfo.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_periodo_ejecucion), String.valueOf(datos.getPeriodoEjucionDias())));
		listInfo.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_fecha_inicio), datos.getFechaInicioContrato()));
		listInfo.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_fecha_termino), datos.getFechaTerminoContrato()));
		listInfo.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_superintendente), datos.getSuperintendente()));
		
		List<String> empresas =  bus_obra.getEmpresasObra(datos.getIdGobierno(),
				datos.getIdSecretaria(), datos.getIdDependencia());
		
		listInfo.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_gobierno), empresas.get(0)));
		
		return listInfo;
	}
	
	private List<Mod_Informacion_Obra> getInfoVerMas(Obra datos){
		List<Mod_Informacion_Obra> listInfoVerMas = new ArrayList<Mod_Informacion_Obra>();
		
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_rfc_empresa), datos.getRfc()));

		List<String> empresas =  bus_obra.getEmpresasObra(datos.getIdGobierno(),
				datos.getIdSecretaria(), datos.getIdDependencia());
		
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_secretaria), empresas.get(1)));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_dependencia), empresas.get(2)));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_direccion), datos.getDireccion()));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_subdireccion), datos.getSubdireccion()));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_area), datos.getArea()));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_descripcion), datos.getDescripcion()));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_fecha_contrato), datos.getFechaContrato()));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_tipo_contrato), datos.getTipoContrato()));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_importe_contrato_sinIVA), Utl_Operaciones.formatearCantidad(datos.getImporteContratoSinIVA())));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_ejercicio_fiscal1), datos.getNombreEjercicioFiscal1()));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_importe_fiscal_sinIVA), Utl_Operaciones.formatearCantidad(datos.getImporteFiscal1SinIVA())));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_importe_convenio_ampliacion), Utl_Operaciones.formatearCantidad(datos.getImporteConvenioAmpliacion())));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_importe_convenio_reduccion), Utl_Operaciones.formatearCantidad(datos.getImporteConvenioReduccion())));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_importe_ajustes_costos), Utl_Operaciones.formatearCantidad(datos.getImporteAjusteCostos())));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_partida_presupuestal), datos.getPartidaPresupuestal()));
//		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_anticipo), Utl_Operaciones.formatearCantidad(Double.parseDouble(datos.getAnticipo()))));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_anticipo_noFianza), datos.getNoFianzaAnticipo()));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_anticipo_fecha), datos.getFechaFianzaAnticipo()));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_anticipo_monto), Utl_Operaciones.formatearCantidad(datos.getMontoFianzaAnticipo())));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_cumplimiento_noFianza), String.valueOf(datos.getNoFianzaCumplimiento())));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_cumplimiento_fecha), datos.getFechaFianzaCumplimiento()));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_cumplimiento_monto), Utl_Operaciones.formatearCantidad(datos.getMontoFianzaCumplimiento())));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_cargo_revision1), datos.getCargoRevision1()));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_nombre_revision1), datos.getNombreRevision1()));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_cargo_revision2), datos.getCargoRevision2()));
		listInfoVerMas.add(new Mod_Informacion_Obra(context.getResources().getString(R.string.str_informacion_obra_nombre_revision2), datos.getNombreRevision2()));	
		
		return listInfoVerMas;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	private void animar(boolean mostrar){
		AnimationSet set = new AnimationSet(true);
		Animation animation = null;
		if (mostrar){
			//desde la esquina inferior derecha a la superior izquierda
			animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		}else{    //desde la esquina superior izquierda a la esquina inferior derecha
			 animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
		}
		//duración en milisegundos
		animation.setDuration(500);
		set.addAnimation(animation);
		LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);

		ll_ver_mas.setLayoutAnimation(controller);
		ll_ver_mas.startAnimation(animation);
	}
	
}
