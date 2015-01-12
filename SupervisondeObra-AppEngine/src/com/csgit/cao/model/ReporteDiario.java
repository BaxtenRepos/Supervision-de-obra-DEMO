package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReporteDiario {
	@Id
	private Long id_ReporteDiario;
	private Long id_Obra;
	private Integer Clave;
	private Long id_Notas;
	private Long id_RMaquinariaEquipo;
	private Long id_RPersonal;
	private Long id_Minuta;
	private String FechaReporteDiario;
	private String Email;
	private Long idDispo;
	
	
	public Long getIdDispo() {
		return idDispo;
	}
	public void setIdDispo(Long idDispo) {
		this.idDispo = idDispo;
	}
	public Long getId_ReporteDiario() {
		return id_ReporteDiario;
	}
	public Long getId_Obra() {
		return id_Obra;
	}
	public void setId_Obra(Long id_Obra) {
		this.id_Obra = id_Obra;
	}
	public void setId_ReporteDiario(Long id_ReporteDiario) {
		this.id_ReporteDiario = id_ReporteDiario;
	}

	public Integer getClave() {
		return Clave;
	}
	public void setClave(Integer clave) {
		Clave = clave;
	}
	public Long getId_Notas() {
		return id_Notas;
	}
	public void setId_Notas(Long id_Notas) {
		this.id_Notas = id_Notas;
	}
	public Long getId_RMaquinariaEquipo() {
		return id_RMaquinariaEquipo;
	}
	public void setId_RMaquinariaEquipo(Long id_RMaquinariaEquipo) {
		this.id_RMaquinariaEquipo = id_RMaquinariaEquipo;
	}
	public Long getId_RPersonal() {
		return id_RPersonal;
	}
	public void setId_RPersonal(Long id_RPersonal) {
		this.id_RPersonal = id_RPersonal;
	}
	public Long getId_Minuta() {
		return id_Minuta;
	}
	public void setId_Minuta(Long id_Minuta) {
		this.id_Minuta = id_Minuta;
	}
	public String getFechaReporteDiario() {
		return FechaReporteDiario;
	}
	public void setFechaReporteDiario(String fechaReporteDiario) {
		FechaReporteDiario = fechaReporteDiario;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	

}
