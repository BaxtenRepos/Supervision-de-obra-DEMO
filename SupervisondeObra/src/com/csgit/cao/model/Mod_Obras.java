package com.csgit.cao.model;

public class Mod_Obras implements Comparable<Mod_Obras>{
	
	private Long idObra;
	private String nombre;
	private String porcentajeFisico;
	private int estadoFisico;
	private String estadoFisicoString;
	private String porcentajeFinanciero;
	private int estadoFinanciero;
	private String estadoFinancieroString;
	private String empresaContratista;
	private String empresaSupervisora;
	private String limiteDesvio;
	private String numeroContrato;
	private String entidadFederativa;
	
	
	
	public String getEntidadFederativa() {
		return entidadFederativa;
	}
	public void setEntidadFederativa(String entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}
	public String getEstadoFisicoString() {
		return estadoFisicoString;
	}
	public void setEstadoFisicoString(String estadoFisicoString) {
		this.estadoFisicoString = estadoFisicoString;
	}
	public String getEstadoFinancieroString() {
		return estadoFinancieroString;
	}
	public void setEstadoFinancieroString(String estadoFinancieroString) {
		this.estadoFinancieroString = estadoFinancieroString;
	}
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public int getEstadoFisico() {
		return estadoFisico;
	}
	public void setEstadoFisico(int estadoFisico) {
		this.estadoFisico = estadoFisico;
	}
	public int getEstadoFinanciero() {
		return estadoFinanciero;
	}
	public void setEstadoFinanciero(int estadoFinanciero) {
		this.estadoFinanciero = estadoFinanciero;
	}
	public Long getIdObra() {
		return idObra;
	}
	public void setIdObra(Long idObra) {
		this.idObra = idObra;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPorcentajeFisico() {
		return porcentajeFisico;
	}
	public void setPorcentajeFisico(String porcentajeFisico) {
		this.porcentajeFisico = porcentajeFisico;
	}
	public String getPorcentajeFinanciero() {
		return porcentajeFinanciero;
	}
	public void setPorcentajeFinanciero(String porcentajeFinanciero) {
		this.porcentajeFinanciero = porcentajeFinanciero;
	}
	public String getEmpresaContratista() {
		return empresaContratista;
	}
	public void setEmpresaContratista(String empresaContratista) {
		this.empresaContratista = empresaContratista;
	}
	public String getEmpresaSupervisora() {
		return empresaSupervisora;
	}
	public void setEmpresaSupervisora(String empresaSupervisora) {
		this.empresaSupervisora = empresaSupervisora;
	}
	public String getLimiteDesvio() {
		return limiteDesvio;
	}
	public void setLimiteDesvio(String limiteDesvio) {
		this.limiteDesvio = limiteDesvio;
	}
	
	@Override
	public int compareTo(Mod_Obras another) {
		// TODO Auto-generated method stub
		return this.nombre.compareTo(another.getNombre());
	}
}
