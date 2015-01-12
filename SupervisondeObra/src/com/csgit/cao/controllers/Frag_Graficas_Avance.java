package com.csgit.cao.controllers;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.csgit.cao.R;
import com.csgit.cao.business.Bus_Avance;
import com.csgit.cao.model.communicationchannel.model.AvanceFinanciero;
import com.csgit.cao.model.communicationchannel.model.AvanceFisico;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Operaciones;
import com.jjoe64.graphview.CustomLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;

public class Frag_Graficas_Avance extends Fragment{

	private GraphView graphViewFisico;
	private GraphView graphViewFinanciero;

	private Long idObra;
	
	private RadioGroup radioGroup;
	private RadioButton radioFisico;
	private RadioButton radioFinanciero;
	
	private List<AvanceFisico> list_avance_fisico;
	private List<AvanceFinanciero> list_avance_financiero;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActivity().supportInvalidateOptionsMenu();
		setHasOptionsMenu(true);
		
		Bundle bundle = getArguments();
		idObra = bundle.getLong("idObra");
		
		Bus_Avance bus_avance = new Bus_Avance(getActivity().getContentResolver());
		list_avance_fisico = bus_avance.getAvanceFisico(idObra);
		list_avance_financiero = bus_avance.getAvanceFinanciero(idObra);
		
		createGraphFisico();
		createGraphFinanciero();
		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@SuppressLint("SimpleDateFormat")
	@SuppressWarnings("deprecation")
	private void createGraphFisico(){
		
		List<Double> auxTendencia = new ArrayList<>();
		GraphViewData[] dataFisico;
		GraphViewData[] dataTendencia;
		GraphViewSeries serieFisico;
		GraphViewSeries serieTendencia;
		String labelXFisico[];
		String labelYFisico[];
		
		graphViewFisico = new LineGraphView(getActivity(), "");
		
		dataFisico = new GraphViewData[list_avance_fisico.size()];
		dataTendencia = new GraphViewData[list_avance_fisico.size()];
		
		labelXFisico = new String[list_avance_fisico.size()];
		labelYFisico = new String[list_avance_fisico.size()]; 
		
		for (int i = 0; i < list_avance_fisico.size(); i++) {
			dataFisico[i] = new GraphViewData(i,
					list_avance_fisico.get(i).getPavanceFisico());
			labelXFisico[i] = list_avance_fisico.get(i).getFechaReporte();
			labelYFisico[i] = list_avance_fisico.get(i).getPavanceFisico() + 
					Utl_Constantes.CARAC_PORCENTAJE;
			auxTendencia.add(list_avance_fisico.get(i).getPorcentajeTendencia());
		}
		
		serieFisico = new GraphViewSeries(getResources().getString
				(R.string.str_frag_graficas_label_fisico_real), 
				new GraphViewSeriesStyle(Color.BLUE, 3), dataFisico);
		
		((LineGraphView) graphViewFisico).setDrawDataPoints(true);
		((LineGraphView) graphViewFisico).setDrawBackground(true);
		
		// Ingresar grafica de tendencia
		dataTendencia = Utl_Operaciones.getPuntosY(auxTendencia);
		serieTendencia = new GraphViewSeries(getResources().getString(
				R.string.str_frag_graficas_label_fisico_tendencia), 
				new GraphViewSeriesStyle(Color.RED, 3), dataTendencia);

		if(labelXFisico.length <= 1){
			labelXFisico = new String[2];
			labelXFisico[0] = Utl_Constantes.VALOR_MINIMO;
			labelXFisico[1] = Utl_Constantes.VALOR_MAXIMO;
		}
		if(labelYFisico.length <= 1){
			labelYFisico = new String[2];
			labelYFisico[1] = Utl_Constantes.PORCENTAJE_MINIMO;
			labelYFisico[0] = Utl_Constantes.PORCENTAJE_MAXIMO;
		}
//		graphViewFisico.setHorizontalLabels(labelXFisico);
//		graphViewFisico.setVerticalLabels(labelYFisico);
		
//		graphViewFisico.setHorizontalLabels(new String[] {"2 days ago","fdsg"});
//		graphViewFisico.setVerticalLabels(new String[] {"high", "sfs"});
		
//		final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d");
		graphViewFisico.setCustomLabelFormatter(new CustomLabelFormatter() {
			
			@Override
			public String formatLabel(double value, boolean isValueX) {
				// TODO Auto-generated method stub
				if(!isValueX)
				  return String.valueOf(Utl_Operaciones.redondearValor(value, 2)) + "%";
				else
					return null;
			}
		});
		
		graphViewFisico.addSeries(serieFisico);
		graphViewFisico.addSeries(serieTendencia);
		
		graphViewFisico.setScalable(true);
		graphViewFisico.setShowLegend(true);
		graphViewFisico.setLegendWidth(300);
	}
	
	@SuppressWarnings("deprecation")
	private void createGraphFinanciero(){
		List<Double> auxTendencia = new ArrayList<>();
		GraphViewData[] dataFinanciero;
		GraphViewData[] dataTendencia;
		GraphViewSeries serieFinanciero;
		GraphViewSeries serieTendencia;
		String labelXFinanciero[];
		String labelYFinanciero[];
		
		graphViewFinanciero = new LineGraphView(getActivity(), "");
		
		dataFinanciero = new GraphViewData[list_avance_financiero.size()];
		
		labelXFinanciero = new String[list_avance_financiero.size()];
		labelYFinanciero = new String[list_avance_financiero.size()];
		
		for(int j = 0; j < list_avance_financiero.size(); j++){
			dataFinanciero[j] =  new GraphViewData(j, 
					list_avance_financiero.get(j).getPavanceFinanciero());
			labelXFinanciero[j] = list_avance_financiero.get(j).getFechaReporte();
			labelYFinanciero[j] = list_avance_financiero.get(j).getPavanceFinanciero() + 
					Utl_Constantes.CARAC_PORCENTAJE;
			auxTendencia.add(list_avance_financiero.get(j).getPorcentajeTendencia());
		}
		
		serieFinanciero = new GraphViewSeries(getResources().getString
				(R.string.str_frag_graficas_label_financiero_real),
				new GraphViewSeriesStyle(Color.BLUE, 3), dataFinanciero);
		
		((LineGraphView) graphViewFinanciero).setDrawDataPoints(true);
		((LineGraphView) graphViewFinanciero).setDrawBackground(true);
		
		// Ingresar grafica de tendencia
		dataTendencia = Utl_Operaciones.getPuntosY(auxTendencia);
		serieTendencia = new GraphViewSeries(getResources().getString
				(R.string.str_frag_graficas_label_financiero_tendencia),
				new GraphViewSeriesStyle(Color.RED, 3), dataTendencia);

		if(labelXFinanciero.length <= 1){
			labelXFinanciero = new String[2];
			labelXFinanciero[0] = Utl_Constantes.VALOR_MINIMO;
			labelXFinanciero[1] = Utl_Constantes.VALOR_MAXIMO;
		}
		if(labelYFinanciero.length <= 1){
			labelYFinanciero = new String[2];
			labelYFinanciero[1] = Utl_Constantes.PORCENTAJE_MINIMO;
			labelYFinanciero[0] = Utl_Constantes.PORCENTAJE_MAXIMO;
		}
			
		graphViewFinanciero.setHorizontalLabels(labelXFinanciero);
		graphViewFinanciero.setVerticalLabels(labelYFinanciero);

		graphViewFinanciero.addSeries(serieFinanciero);
		graphViewFinanciero.addSeries(serieTendencia);
		
		graphViewFinanciero.setScalable(true);
		graphViewFinanciero.setShowLegend(true);
		graphViewFinanciero.setLegendWidth(300);
	}
	
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		LinearLayout root = (LinearLayout) inflater.inflate(R.layout.lyt_frag_graficas_avance, null);
		final LinearLayout viewGraph = (LinearLayout) root.findViewById(R.id.ll_graficas);
		radioGroup = (RadioGroup)root.findViewById(R.id.rdg_graficas_avance);
		radioFisico = (RadioButton) radioGroup.findViewById(R.id.radio_fisico);
		radioFinanciero = (RadioButton) radioGroup.findViewById(R.id.radio_financiero);
		
		viewGraph.removeAllViews();
		viewGraph.addView(graphViewFisico);
		
		radioFisico.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewGraph.removeAllViews();
				viewGraph.addView(graphViewFisico);
			}
		});
		radioFinanciero.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewGraph.removeAllViews();
				viewGraph.addView(graphViewFinanciero);
			}
		});
		
		return root;
	}
}
