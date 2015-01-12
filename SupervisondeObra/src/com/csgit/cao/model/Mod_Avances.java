package com.csgit.cao.model;

import com.google.gson.annotations.SerializedName;

public class Mod_Avances {

	@SerializedName("id_AvanceFisico")
	private Long idAvance;
	@SerializedName("id_referencia")
	private Long idReferencia;
	@SerializedName("pavanceFisico")
	private Double pAvance;
	@SerializedName("fechaReporte")
	private String fechaReporte;
	@SerializedName("estado")
	private Integer estado;
	@SerializedName("tipo_Entidad")
	private Integer tipoEntidad;
	@SerializedName("porcentaje_tendencia")
	private Double pTendencia;
	
	
	public long getIdAvance() {
		return idAvance;
	}
	public void setIdAvance(long idAvance) {
		this.idAvance = idAvance;
	}
	public long getIdReferencia() {
		return idReferencia;
	}
	public void setIdReferencia(long idReferencia) {
		this.idReferencia = idReferencia;
	}
	public double getpAvance() {
		return pAvance;
	}
	public void setpAvance(double pAvance) {
		this.pAvance = pAvance;
	}
	public String getFechaReporte() {
		return fechaReporte;
	}
	public void setFechaReporte(String fechaReporte) {
		this.fechaReporte = fechaReporte;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getTipoEntidad() {
		return tipoEntidad;
	}
	public void setTipoEntidad(int tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}
	public double getpTendencia() {
		return pTendencia;
	}
	public void setpTendencia(double pTendencia) {
		this.pTendencia = pTendencia;
	}
	
}
