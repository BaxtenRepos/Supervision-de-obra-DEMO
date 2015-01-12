package com.csgit.entity;

	import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.csgit.cao.model.Avance_Fisico;
	
	public class Avance_FisicoEntity {
	    
		private Avance_Fisico AvanceFisico;
		private List<Double>Tendencia;
		public Avance_Fisico getAvanceFisico() {
			return AvanceFisico;
		}
		public void setAvanceFisico(Avance_Fisico avanceFisico) {
			AvanceFisico = avanceFisico;
		}
		public List<Double> getTendencia() {
			return Tendencia;
		}
		public void setTendencia(List<Double> tendencia) {
			Tendencia = tendencia;
		}
		

	}


