package com.csgit.cao.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import com.csgit.cao.model.communicationchannel.model.Concepto;
import com.jjoe64.graphview.GraphView.GraphViewData;

public class Utl_Operaciones {

	public static double redondearValor(double valor, int digitos){
		int cifras = (int)Math.pow(10, digitos);
		return Math.rint(valor*100)/cifras;
	}
	
	public static String formatearCantidad(double cantidad){
		String result = "0.0";
		Locale.setDefault(Locale.US);
		NumberFormat formater = new DecimalFormat(Utl_Constantes.FOR_CANTIDADES);
		result = formater.format(cantidad);
		return result;
	}
	
	public static String formatearCantidadSinSigno(double cantidad){
		String result = "0.0";
		Locale.setDefault(Locale.US);
		NumberFormat formater = new DecimalFormat(Utl_Constantes.FOR_CANTIDADES_SIN_SINGO);
		result = formater.format(cantidad);
		return result;
	}
	
	public static List<Long> getIdsPadres(List<Concepto> lista){
		List<Long> ids = new ArrayList<Long>();
		for (Concepto concepto : lista) {
			ids.add(concepto.getPadre());
		}
		HashSet<Long> hashSet = new HashSet<Long>(ids);
		ids.clear();
		ids.addAll(hashSet);
		return ids;
	}
	
	public static double getSumatoria(double X, double N){
		if(N > 0){
			X = getSumatoria(X, N-1);
			X = X + N;
			return X;
		}else{
			X = 0;
			return X;
		}
	}
	
	public static GraphViewData[] getPuntosY(List<Double> objeto){
//	public static List<Double> getPuntosY(){
//		List<Double> objeto = new ArrayList<>();
//		objeto.add(147D);
//		objeto.add(125D);
//		objeto.add(160D);
//		objeto.add(218D);
//		objeto.add(249D);
//		objeto.add(228D);
//		objeto.add(350D);
//		objeto.add(345D);
//		objeto.add(315D);
//		objeto.add(440D);
//		objeto.add(452D);
//		objeto.add(455D);
		
		double sumaX = 0;
		double sumaY = 0;
		double sumaXY = 0;
		double sumaX2 = 0;
		double promedioX = 0;
		double promedioY = 0;
		double a = 0;
		double b = 0;
		double y = 0;
//		List<Double> puntosY = new ArrayList<>();
		GraphViewData[] puntosY = new GraphViewData[objeto.size()];
		
		if(objeto != null && objeto.size() != 0){
			sumaX = getSumatoria(0, objeto.size());
			for (int i = 0; i < objeto.size(); i++) {
				sumaY += objeto.get(i);
				sumaXY += ((i + 1) * objeto.get(i));
				sumaX2 += Math.pow((i + 1), 2);
			}
			promedioX = sumaX / objeto.size();
			promedioY = sumaY / objeto.size();
			
			b = (objeto.size()*sumaXY-sumaX*sumaY)/(objeto.size()*sumaX2-(Math.pow(sumaX, 2)));
			b = redondearValor(b, 2);
			a = promedioY - b * promedioX;
			a = redondearValor(a, 2);
			
			for(int k = 0; k < objeto.size(); k++){
				y = a + b * k;
				y = redondearValor(y, 2); 
//				puntosY.add(y);
				puntosY[k] = new GraphViewData(k, y);
			}		
		}
		return puntosY;
	}
	
}
