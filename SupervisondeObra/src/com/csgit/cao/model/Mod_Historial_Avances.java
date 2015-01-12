package com.csgit.cao.model;

public class Mod_Historial_Avances {
	
	private long idAvance;
	private long idReporteDiario;
	private long idConcepto;
	private double cantidadAvance;
	private String fecha;
	private String unidadMedida;
	
	
	
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public long getIdAvance() {
		return idAvance;
	}
	public void setIdAvance(long idAvance) {
		this.idAvance = idAvance;
	}
	public long getIdReporteDiario() {
		return idReporteDiario;
	}
	public void setIdReporteDiario(long idReporteDiario) {
		this.idReporteDiario = idReporteDiario;
	}
	public long getIdConcepto() {
		return idConcepto;
	}
	public void setIdConcepto(long idConcepto) {
		this.idConcepto = idConcepto;
	}
	public double getCantidadAvance() {
		return cantidadAvance;
	}
	public void setCantidadAvance(double cantidadAvance) {
		this.cantidadAvance = cantidadAvance;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
