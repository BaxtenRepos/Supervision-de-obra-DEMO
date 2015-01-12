package com.csgit.entity;

public class ListaObraEntity {

	private Long id_Obra;
	private String Poyecto;
	private String Nombre;
	private String Descripcion;
	private String FechaContrato;
	private String FechaInicioContrato;
	private String FechaTerminoContrato;
	private Double ImporteContratoSinIVA ;
	private String Dependencia;
	
	public Long getId_Obra() {
		return id_Obra;
	}
	public void setId_Obra(Long id_Obra) {
		this.id_Obra = id_Obra;
	}
	public String getPoyecto() {
		return Poyecto;
	}
	public void setPoyecto(String poyecto) {
		Poyecto = poyecto;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getFechaContrato() {
		return FechaContrato;
	}
	public void setFechaContrato(String fechaContrato) {
		FechaContrato = fechaContrato;
	}
	public String getFechaInicioContrato() {
		return FechaInicioContrato;
	}
	public void setFechaInicioContrato(String fechaInicioContrato) {
		FechaInicioContrato = fechaInicioContrato;
	}
	public String getFechaTerminoContrato() {
		return FechaTerminoContrato;
	}
	public void setFechaTerminoContrato(String fechaTerminoContrato) {
		FechaTerminoContrato = fechaTerminoContrato;
	}
	public Double getImporteContratoSinIVA() {
		return ImporteContratoSinIVA;
	}
	public void setImporteContratoSinIVA(Double importeContratoSinIVA) {
		ImporteContratoSinIVA = importeContratoSinIVA;
	}	
	public String getDependencia() {
		return Dependencia;
	}
	public void setDependencia(String dependencia) {
		Dependencia = dependencia;
	}
	
}
