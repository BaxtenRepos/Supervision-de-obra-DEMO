package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Aditivas_Deductivas {
	@Id
	private Long id_AditivasDeductivas;
	private Long id_Concepto;
	private int tipoOperacion;
	private Double cantidad;
	private String fecha;
	
	public Long getId_AditivasDeductivas() {
		return id_AditivasDeductivas;
	}
	public void setId_AditivasDeductivas(Long id_AditivasDeductivas) {
		this.id_AditivasDeductivas = id_AditivasDeductivas;
	}
	public Long getId_Concepto() {
		return id_Concepto;
	}
	public void setId_Concepto(Long id_Concepto) {
		this.id_Concepto = id_Concepto;
	}
	public int getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(int tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}	
	
}
