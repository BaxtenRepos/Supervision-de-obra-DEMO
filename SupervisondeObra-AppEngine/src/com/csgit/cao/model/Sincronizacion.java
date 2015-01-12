package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Sincronizacion {

	 @Id
	  private Long idSincronizacion;
	  private String Entidad;
	  private Long idRegistro;
	  private Long accion;
	  private Long status;
	  
	  
	  
	public Long getIdSincronizacion() {
		return idSincronizacion;
	}
	public void setIdSincronizacion(Long idSincronizacion) {
		this.idSincronizacion = idSincronizacion;
	}
	public String getEntidad() {
		return Entidad;
	}
	public void setEntidad(String entidad) {
		Entidad = entidad;
	}
	public Long getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}
	public Long getAccion() {
		return accion;
	}
	public void setAccion(Long accion) {
		this.accion = accion;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	

}
