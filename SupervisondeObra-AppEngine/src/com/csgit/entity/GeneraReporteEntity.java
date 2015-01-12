package com.csgit.entity;

public class GeneraReporteEntity {
//	'idProyecto' : document.getElementById("proyectoselect").value;,
//	'idObra' : document.getElementById("obraselect").value;,
//	'NumeroConvenio' : $('#NumeroConvenio').val().trim(),
//	'Fechaconvenio' : $('#Fechaconvenio').val().trim(),
//	'ImporteConvenio' : $('#ImporteConvenio').val().trim(),
//	'Dias' : $('#Dias').val().trim(),
//	'Fechainicio' : $('#Fechainicio').val().trim(),
//	'Fechafin' : $('#Fechafin').val().trim(),
	private String idProyecto;
	private String idObra;
	private String NumeroConvenio;
	private String Fechaconvenio;
	private String ImporteConvenio;
	private String Dias;
	private String Fechainicio;
	private String Fechafin;
	public String getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}
	public String getIdObra() {
		return idObra;
	}
	public void setIdObra(String idObra) {
		this.idObra = idObra;
	}
	public String getNumeroConvenio() {
		return NumeroConvenio;
	}
	public void setNumeroConvenio(String numeroConvenio) {
		NumeroConvenio = numeroConvenio;
	}
	public String getFechaconvenio() {
		return Fechaconvenio;
	}
	public void setFechaconvenio(String fechaconvenio) {
		Fechaconvenio = fechaconvenio;
	}
	public String getImporteConvenio() {
		return ImporteConvenio;
	}
	public void setImporteConvenio(String importeConvenio) {
		ImporteConvenio = importeConvenio;
	}
	public String getDias() {
		return Dias;
	}
	public void setDias(String dias) {
		Dias = dias;
	}
	public String getFechainicio() {
		return Fechainicio;
	}
	public void setFechainicio(String fechainicio) {
		Fechainicio = fechainicio;
	}
	public String getFechafin() {
		return Fechafin;
	}
	public void setFechafin(String fechafin) {
		Fechafin = fechafin;
	}

	
	
}
