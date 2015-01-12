package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RepoPersonalCatPersonal {

	@Id
	private Long idPropietario;
	private Long idReporteDiario;
	private Long idCatPersonal;
	private Long cantidad;
	private String Email;
	private Long idDispo;
	
	
	public Long getIdDispo() {
		return idDispo;
	}
	public void setIdDispo(Long idDispo) {
		this.idDispo = idDispo;
	}
	public Long getIdPropietario() {
		return idPropietario;
	}
	public void setIdPropietario(Long idPropietario) {
		this.idPropietario = idPropietario;
	}
	public Long getIdReporteDiario() {
		return idReporteDiario;
	}
	public void setIdReporteDiario(Long idReporteDiario) {
		this.idReporteDiario = idReporteDiario;
	}
	public Long getIdCatPersonal() {
		return idCatPersonal;
	}
	public void setIdCatPersonal(Long idCatPersonal) {
		this.idCatPersonal = idCatPersonal;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	
	
}
