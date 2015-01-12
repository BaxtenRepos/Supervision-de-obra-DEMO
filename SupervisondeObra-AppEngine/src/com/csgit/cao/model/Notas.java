package com.csgit.cao.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Notas {
	@Id
	private Long IdRepoNotas;
	private Long IdReporteDiario;
	private String Descripcion;
	private String Fecha;
	private String titulo;
	private String Email;
	private Long idDispo;
	
	

	public Long getIdDispo() {
		return idDispo;
	}
	public void setIdDispo(Long idDispo) {
		this.idDispo = idDispo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Long getIdRepoNotas() {
		return IdRepoNotas;
	}
	public void setIdRepoNotas(Long idRepoNotas) {
		IdRepoNotas = idRepoNotas;
	}
	public Long getIdReporteDiario() {
		return IdReporteDiario;
	}
	public void setIdReporteDiario(Long idReporteDiario) {
		IdReporteDiario = idReporteDiario;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}



}
