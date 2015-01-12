package com.csgit.cao.model;


import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Avance_Financiero {
	  @Id
		private Long id_AvanceFinaciero;
		private Long id_referencia;
		private Double PAvanceFinanciero;
		private String FechaReporte;
		private Integer Estado;
		private Double porcentaje_tendencia;
		private Integer Tipo_Entidad;
		public Integer getEstado() {
			return Estado;
		}

		public void setEstado(Integer estado) {
			Estado = estado;
		}
	
		public Double getPorcentaje_tendencia() {
			return porcentaje_tendencia;
		}

		public void setPorcentaje_tendencia(Double porcentaje_tendencia) {
			this.porcentaje_tendencia = porcentaje_tendencia;
		}

		public Long getId_AvanceFinaciero() {
			return id_AvanceFinaciero;
		}
		public void setId_AvanceFinaciero(Long id_AvanceFinaciero) {
			this.id_AvanceFinaciero = id_AvanceFinaciero;
		}
	
		public Double getPAvanceFinanciero() {
			return PAvanceFinanciero;
		}
		public void setPAvanceFinanciero(Double pAvanceFinanciero) {
			PAvanceFinanciero = pAvanceFinanciero;
		}
		public Long getId_referencia() {
			return id_referencia;
		}
		public void setId_referencia(Long id_referencia) {
			this.id_referencia = id_referencia;
		}
	
		public String getFechaReporte() {
			return FechaReporte;
		}
		public void setFechaReporte(String fechaReporte) {
			FechaReporte = fechaReporte;
		}
		public Integer getTipo_Entidad() {
			return Tipo_Entidad;
		}
		public void setTipo_Entidad(Integer tipo_Entidad) {
			Tipo_Entidad = tipo_Entidad;
		}	
}
