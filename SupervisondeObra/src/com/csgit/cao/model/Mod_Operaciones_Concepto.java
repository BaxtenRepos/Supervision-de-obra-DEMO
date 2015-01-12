package com.csgit.cao.model;

public class Mod_Operaciones_Concepto {
	
	private long idOpreracion;
	private long idConcepto;
	private int tipoOperacion;
	private double cantidad;
	private String fecha;
	
	
	public long getIdOpreracion() {
		return idOpreracion;
	}
	public void setIdOpreracion(long idOpreracion) {
		this.idOpreracion = idOpreracion;
	}
	public long getIdConcepto() {
		return idConcepto;
	}
	public void setIdConcepto(long idConcepto) {
		this.idConcepto = idConcepto;
	}
	public int getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(int tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
