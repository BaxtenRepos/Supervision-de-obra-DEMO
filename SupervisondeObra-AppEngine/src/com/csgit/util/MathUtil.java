package com.csgit.util;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.csgit.cao.Constants;
import com.csgit.cao.GetIds;
import com.csgit.cao.model.Avance_Fisico;
import com.csgit.cao.model.Avance_FisicoEndpoint;
import com.csgit.cao.model.Concepto;
import com.csgit.cao.model.ConceptoEndpoint;
import com.csgit.cao.model.Programa;
import com.google.api.server.spi.response.CollectionResponse;

public class MathUtil {
	Avance_FisicoEndpoint AFE = new Avance_FisicoEndpoint();
	ConceptoEndpoint CE = new ConceptoEndpoint();
	GetIds GetId = new GetIds();

	public List<Programa> ObtenerProgramado(Long id_referencia,
			Integer Tipo_Programa, Integer tipo_entidad) {
		List<Programa> lstprograma = new ArrayList<Programa>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal1 = new GregorianCalendar();
		Collection<Concepto> concepto = CE.listConcepto(null, null,
				id_referencia, 0L).getItems();
		List<Concepto> lstconceptos = new ArrayList<Concepto>();
		lstconceptos.addAll(concepto);
		List<Concepto> lstsinpadre = QuitaPadres(lstconceptos);
		String Fecha_Inical = GetFechaInical(lstsinpadre);
		String Feca_final = GetFechaFinal(lstsinpadre);
		Integer Duracion = GetNumeroDias(Fecha_Inical, Feca_final);
		ArrayList<Concepto> Ids = null;
		try {
			cal1.setTime(formatter.parse(Fecha_Inical));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; Duracion  > i; i++) {
			cal1.add(Calendar.DATE, 1);
			Ids=new ArrayList<Concepto>();
			for (int r=0;lstsinpadre.size()> r;r++	){
			if (EntreRango(Fecha_Inical, cal1.getTime().toString(), lstsinpadre.get(r)
					.getFecha_Inicio()))
				Ids.add(lstsinpadre.get(r));
			
			}
			lstprograma.add(ProgramaxDia(Ids, cal1, Fecha_Inical, lstsinpadre,
					Tipo_Programa, id_referencia, tipo_entidad));
		}
		return lstprograma;
	}
	
	private List<Concepto> QuitaPadres(List<Concepto> lstconceptos){
		List<Concepto>lstreturn=new ArrayList<Concepto>();
		
		for(int i=0;i<lstconceptos.size();i++){
			if(lstconceptos.get(i).getPadre()!=0L)
			lstreturn.add(lstconceptos.get(i));
		}
		return lstreturn;
	}

	private Programa ProgramaxDia(List<Concepto> lstconceptos, Calendar cal1,
			String fecha_Inical, List<Concepto> lsttotal,
			Integer tipo_Programa, Long id_referencia, Integer tipo_entidad) {
		// TODO Auto-generated method stub
		Double cantidad_avance_dia = 0.0;
		Double cantidad_total_de_avances = 0.0;
		Programa prog = new Programa();
		prog.setTipo_avance(tipo_Programa);
		prog.setId_referencia(id_referencia);
		prog.setTipo_entidad(tipo_entidad);
		switch (tipo_Programa) {
		case 0:
			for (int i = 0; lstconceptos.size() > i; i++) {
				Concepto concepto = new Concepto();
				concepto = lstconceptos.get(i);
				Integer duracion = GetNumeroDias(concepto.getFecha_Fin(),
						concepto.getFecha_Inicio());
				cantidad_avance_dia += (double) (concepto.getCantidadTotal() / duracion);
			}
			cantidad_total_de_avances = avancexObra(lsttotal,tipo_Programa);
			prog.setFecha(cal1.toString());
			prog.setId_programa(GetId.getIdPrograma());
			if (Math.rint(cantidad_avance_dia * 100) / 100 != 0.00) {
				prog.setPorcentaje_avance(Redondear((cantidad_avance_dia * 100)
						/ cantidad_total_de_avances, 2));
			} else {
				prog.setPorcentaje_avance((double) 0);
			}
			break;
		case 1:
			for (int i = 0; lstconceptos.size() > i; i++) {
				Concepto concepto = new Concepto();
				concepto = lstconceptos.get(i);
				Integer duracion = GetNumeroDias(concepto.getFecha_Inicio(),
						concepto.getFecha_Fin());
				cantidad_avance_dia += (double) (concepto.getCantidadTotal()*concepto.getPrecioUnitario() / duracion);
			}
			cantidad_total_de_avances = avancexObra(lsttotal, tipo_Programa);
			prog.setFecha(cal1.toString());
			prog.setId_programa(GetId.getIdPrograma());
			if (Math.rint(cantidad_avance_dia * 100) / 100 != 0.00) {
				prog.setPorcentaje_avance(Redondear((cantidad_avance_dia * 100)
						/ cantidad_total_de_avances, 2));
			} else {
				prog.setPorcentaje_avance((double) 0);
			}
			break;

		default:
			break;
		}

		return null;
	}

	private Double avancexObra(List<Concepto> lsttotal, Integer Tipo_programa) {
		// TODO Auto-generated method stub
		Double cantidad_total = 0.0;
		for (int i = 0; lsttotal.size() > i; i++) {
			if (Tipo_programa == 0) {
				cantidad_total += lsttotal.get(i).getCantidadTotal();
			} else {
				cantidad_total += lsttotal.get(i).getCantidadTotal()*lsttotal.get(i).getPrecioUnitario();
			}
		}
		return cantidad_total;
	}

	public Integer ObtenerEstado() {

		return 1;
	}

	public Double calculaAvanceFisico(Long id, Integer tipo) {
		// TODO Auto-generated method stub
		CollectionResponse<Avance_Fisico> avances = AFE.listAvance_Fisico(null,
				null, id, tipo);
		CollectionResponse<Concepto> concepto = CE.listConcepto(null, null, id,
				0L);
		int int_totalconceptos = concepto.getItems().size();
		Double total_total = 0.0;
		Double total_actual = 0.0;
		List<Double> Avance_actual_X = new ArrayList<Double>();
		List<Double> Avance_total = new ArrayList<Double>();
		Collection<Concepto> conceptos = concepto.getItems();
		Collection<Avance_Fisico> avancesF = avances.getItems();
		List<Concepto> lstconceptos = new ArrayList<Concepto>();
		lstconceptos.addAll(conceptos);
		List<Avance_Fisico> lstavancesf = new ArrayList<Avance_Fisico>();
		lstavancesf.addAll(avancesF);
		for (int r = 0; r < int_totalconceptos; r++) {
			if (lstconceptos.get(r).getPadre() != 0) {
				total_total += Double.valueOf(lstconceptos.get(r)
						.getCantidadTotal().toString());
				total_actual += Double.valueOf(lstconceptos.get(r)
						.getCantidadAvance().toString());
				Avance_total.add(Double.valueOf(lstconceptos.get(r)
						.getCantidadTotal().toString()));
				Avance_actual_X.add(Double.valueOf(lstconceptos.get(r)
						.getCantidadAvance().toString()));
			}
		}

		if (Math.rint(total_actual * 100) / 100 != 0.00) {
			return Redondear((total_actual * 100) / total_total, 2);
		} else {
			return (double) 0;
		}
	}

	public List<Double> calculaTendencia(Long id, Integer tipo) {
		// TODO Auto-generated method stub
		CollectionResponse<Avance_Fisico> avances = AFE.listAvance_Fisico(null,
				null, id, tipo);
		CollectionResponse<Concepto> concepto = CE.listConcepto(null, null, id,
				0L);
		Collection<Concepto> conceptos = concepto.getItems();
		Collection<Avance_Fisico> avancesF = avances.getItems();
		List<Concepto> lstconceptos = new ArrayList<Concepto>();
		lstconceptos.addAll(conceptos);
		List<Avance_Fisico> lstavancesf = new ArrayList<Avance_Fisico>();
		lstavancesf.addAll(avancesF);
		int int_totalconceptos = concepto.getItems().size();
		List<Double> Avance_actual_Y = new ArrayList<Double>();
		List<Double> Avance_total = new ArrayList<Double>();
		List<Double> X2 = new ArrayList<Double>();
		List<Double> XY = new ArrayList<Double>();
		List<Double> X = new ArrayList<Double>();
		Double cantidad_total = null;
		for (int r = 0; r < int_totalconceptos; r++) {
			if (lstconceptos.get(r).getPadre() != 0) {
				//cantidad_total+=Double.valueOf(lstconceptos.get(r)
					//	.getCantidadTotal().toString());
				Avance_total.add(Double.valueOf(lstconceptos.get(r)
						.getCantidadTotal().toString()));
				Avance_actual_Y.add(Double.valueOf(lstconceptos.get(r)
						.getCantidadAvance().toString()));
				X.add(Double.valueOf(r + 1));
				X2.add(Double.valueOf(lstconceptos.get(r).getCantidadAvance())
						* (lstconceptos.get(r).getCantidadAvance()));
				XY.add(Double.valueOf(r
						* lstconceptos.get(r).getCantidadAvance()));
			}
		}  
		
		return minimos_cuadrados(Avance_actual_Y, X, XY, X2, X.size(),
				X.get(X.size() - 1) + 1,cantidad_total);
	}

	public List<Double> minimos_cuadrados(List<Double> Sumy, List<Double> Sumx,
			List<Double> Sumxy, List<Double> Sumx2, int n, Double x, Double cantidad_total) {
		Double m;
		Double b;
		Double tend;
		Double Ptendencia;
		Double sumatoriaX = 0.0;
		Double sumatoriaY = 0.0;
		Double sumatoriaXY = 0.0;
		Double sumatoriaX2 = 0.0;

		List<Double> tendencia = new ArrayList<Double>();
		if (Sumy.size() == Sumx.size() && Sumx.size() == Sumxy.size()
				&& Sumxy.size() == Sumx2.size()) {
			for (int i = 0; i < Sumy.size(); i++) {
				sumatoriaX += Sumx.get(i);
				sumatoriaY += Sumy.get(i);
				sumatoriaX2 += Sumx2.get(i);
				sumatoriaXY += Sumxy.get(i);

			}
			m = (n * sumatoriaXY - sumatoriaX * sumatoriaY)
					/ (n * sumatoriaX2 * (sumatoriaX * sumatoriaX));
			b = (sumatoriaY - m * sumatoriaX) / n;
			tend = (m * x) + b;
		//	Ptendencia= tend*100/cantidad_total;
			tendencia.add(Redondear(tend, 2));
		}
		return tendencia;
	}

	public static double Redondear(double numero, int digitos) {
		int cifras = (int) Math.pow(10, digitos);
		return Math.rint(numero * cifras) / cifras;
	}

	public Double calculaAvanceFinanciero(Long id, Integer tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Double> Union(Double a[], Double b[]) {
		Double[] x = new Double[a.length + b.length];
		List<Double> resultado = new ArrayList<Double>();
		for (int i = 0; i < b.length; i++) {
			resultado.add(b[i]);
		}
		for (int h = 0; h < a.length; h++) {
			for (int i = 0; i < b.length; i++) {
				if (a[h] != b[i]) {
					resultado.add(resultado.size() + 1, a[h]);
				}
			}
		}
		return resultado;

	}// union

	public List<Double> Interseccion(Double a[], Double b[]) {
		List<Double> resultado = new ArrayList<Double>();
		for (int h = 0; h < a.length; h++) {
			for (int i = 0; i < 5; i++) {
				if (a[h] == b[i]) {
					resultado.add(b[i]);
				}
			}
		}
		return resultado;
	}

	public Boolean EntreRango(String Inicial, String Final, String Verificar) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Calendar cal1 = new GregorianCalendar();
		try {
			cal1.setTime(formatter.parse(Inicial));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal2 = new GregorianCalendar();
		try {
			cal2.setTime(formatter.parse(Final));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date3 = null;
		try {
			date3 = (Date) formatter.parse(Verificar);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal3 = new GregorianCalendar();
		cal3.setTime(date3);
		if (cal3.after(cal1) && cal3.before(cal2)) {
			return true;
		} else {
			return false;
		}

	}

	public Integer GetNumeroDias(String Inicial, String Final) {
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; // Milisegundos al
															// dîa
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date inicio = null;
		try {
			inicio = (Date) formatter.parse(Inicial);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Fecha de hoy
		Date fin = null;
		try {
			fin = (Date) formatter.parse(Final);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Fecha de hoy
        System.out.print( fin.getTime() );
        System.out.print( inicio.getTime() );
        System.out.print( MILLSECS_PER_DAY );
        long diferenciaEn_ms = fin.getTime() - inicio.getTime();
		long dias = diferenciaEn_ms / MILLSECS_PER_DAY;
		System.out.print( diferenciaEn_ms );
		return (int) dias;
	}

	public String GetFechaInical(List<Concepto> lstConceptos) {
		Collections.sort(lstConceptos, new ConceptoComparatorInicio());
		return lstConceptos.get(0).getFecha_Inicio();
	}

	public String GetFechaFinal(List<Concepto> lstConceptos) {
		Collections.sort(lstConceptos, new ConceptoComparatorFin());
		return lstConceptos.get(lstConceptos.size() - 1).getFecha_Inicio();
	}

	public String md5(String pass) throws Exception {
		String clear = Constants.SecretClient + pass;

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(clear.getBytes());

		int size = b.length;
		StringBuffer h = new StringBuffer(size);
		for (int i = 0; i < size; i++) {
			int u = b[i] & 255;
			if (u < 16) {
				h.append("0" + Integer.toHexString(u));
			} else {
				h.append(Integer.toHexString(u));
			}
		}
		return h.toString();
	}

}
