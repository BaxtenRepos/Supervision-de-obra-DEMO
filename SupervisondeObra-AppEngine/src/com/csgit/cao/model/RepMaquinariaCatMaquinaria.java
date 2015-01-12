package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RepMaquinariaCatMaquinaria {
	
	@Id
	private Long idRepMaquinaria;
	private Long idReporteDiario;
	private Long idCatMaquinaria;
	private Long Cantidad;
	private String Email;
	private Long idDispo;
	
	public Long getIdDispo() {
		return idDispo;
	}
	public void setIdDispo(Long idDispo) {
		this.idDispo = idDispo;
	}
	public Long getIdRepMaquinaria() {
		return idRepMaquinaria;
	}
	public void setIdRepMaquinaria(Long idRepMaquinaria) {
		this.idRepMaquinaria = idRepMaquinaria;
	}
	public Long getIdReporteDiario() {
		return idReporteDiario;
	}
	public void setIdReporteDiario(Long idReporteDiario) {
		this.idReporteDiario = idReporteDiario;
	}
	public Long getIdCatMaquinaria() {
		return idCatMaquinaria;
	}
	public void setIdCatMaquinaria(Long idCatMaquinaria) {
		this.idCatMaquinaria = idCatMaquinaria;
	}
	public Long getCantidad() {
		return Cantidad;
	}
	public void setCantidad(Long cantidad) {
		Cantidad = cantidad;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	

	

	
}
