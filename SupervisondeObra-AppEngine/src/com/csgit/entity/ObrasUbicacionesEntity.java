package com.csgit.entity;

import com.csgit.cao.model.Obra;
import com.csgit.cao.model.Ubicaciones;

public class ObrasUbicacionesEntity {

		private Long idObra;
		private String nombreObra;
		private Ubicaciones ubicacionObra;
		public Long getIdObra() {
			return idObra;
		}
		public void setIdObra(Long obra) {
			this.idObra = obra;
		}
		public String getNombreObra() {
			return nombreObra;
		}
		public void setNombreObra(String nombreObra) {
			this.nombreObra = nombreObra;
		}
		public Ubicaciones getUbicacionObra() {
			return ubicacionObra;
		}
		public void setUbicacionObra(Ubicaciones ubicacionObra) {
			this.ubicacionObra = ubicacionObra;
		}
		
		
}
