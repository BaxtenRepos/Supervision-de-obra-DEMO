package com.csgit.util;

import java.util.ArrayList;

import com.csgit.cao.model.Avance_Financiero;
import com.csgit.cao.model.Avance_Fisico;
import com.csgit.cao.model.Obra;

public class ObraUtil {

		private ArrayList<Avance_Financiero> avanceFinanciero;
		private ArrayList<Avance_Fisico> avanceFisico;
		private Obra obra;
		private ArrayList<ReporteDiarioUtil> reportesDiarios;
	
		
		public ArrayList<Avance_Financiero> getAvanceFinanciero() {
			return avanceFinanciero;
		}
		public void setAvanceFinanciero(ArrayList<Avance_Financiero> avanceFinanciero) {
			this.avanceFinanciero = avanceFinanciero;
		}
		public ArrayList<Avance_Fisico> getAvanceFisico() {
			return avanceFisico;
		}
		public void setAvanceFisico(ArrayList<Avance_Fisico> avanceFisico) {
			this.avanceFisico = avanceFisico;
		}
		public Obra getObra() {
			return obra;
		}
		public void setObra(Obra obra) {
			this.obra = obra;
		}
		public ArrayList<ReporteDiarioUtil> getReportesDiarios() {
			return reportesDiarios;
		}
		public void setReportesDiarios(ArrayList<ReporteDiarioUtil> reportesDiarios) {
			this.reportesDiarios = reportesDiarios;
		}
	
		
		
		
}
