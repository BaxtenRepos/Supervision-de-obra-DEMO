package com.csgit.cao.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Estimacion {
	  @Id
		private Long id_Estimacion;
		private String fechaInicio;
		private String fechaFin;
		private Long Numero;
		private Long idObra;
		private Long idConcepto;
		private Long cantidadAutorizada;
		private String fechaEstimacion;
		private Long estimacion;
		private String Email;
		private Long id_dispo;
		
		
		public Long getId_dispo() {
			return id_dispo;
		}
		public void setId_dispo(Long id_dispo) {
			this.id_dispo = id_dispo;
		}
		public Long getId_Estimacion() {
			return id_Estimacion;
		}
		public void setId_Estimacion(Long id_Estimacion) {
			this.id_Estimacion = id_Estimacion;
		}
		public String getFechaInicio() {
			return fechaInicio;
		}
		public void setFechaInicio(String fechaInicio) {
			this.fechaInicio = fechaInicio;
		}
		public String getFechaFin() {
			return fechaFin;
		}
		public void setFechaFin(String fechaFin) {
			this.fechaFin = fechaFin;
		}
		public Long getNumero() {
			return Numero;
		}
		public void setNumero(Long numero) {
			Numero = numero;
		}
		public Long getIdObra() {
			return idObra;
		}
		public void setIdObra(Long idObra) {
			this.idObra = idObra;
		}
		public Long getIdConcepto() {
			return idConcepto;
		}
		public void setIdConcepto(Long idConcepto) {
			this.idConcepto = idConcepto;
		}
		public Long getCantidadAutorizada() {
			return cantidadAutorizada;
		}
		public void setCantidadAutorizada(Long cantidadAutorizada) {
			this.cantidadAutorizada = cantidadAutorizada;
		}
		public String getFechaEstimacion() {
			return fechaEstimacion;
		}
		public void setFechaEstimacion(String fechaEstimacion) {
			this.fechaEstimacion = fechaEstimacion;
		}
		public Long getEstimacion() {
			return estimacion;
		}
		public void setEstimacion(Long estimacion) {
			this.estimacion = estimacion;
		}
		public String getEmail() {
			return Email;
		}
		public void setEmail(String email) {
			Email = email;
		}
		


}
