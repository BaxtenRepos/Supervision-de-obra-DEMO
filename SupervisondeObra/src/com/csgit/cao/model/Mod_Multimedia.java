package com.csgit.cao.model;

public class Mod_Multimedia {
	
	private long idMultimedia;
	private long idReporteDiario;
	private long idObra;
	private String formato;
	private String path;
	private String descripcion;
	private int tipo;
	private String urlServer;
	private String blobKey;
	private int sync;
	private String fecha;
	
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public long getIdMultimedia() {
		return idMultimedia;
	}
	public void setIdMultimedia(long idMultimedia) {
		this.idMultimedia = idMultimedia;
	}
	public long getIdReporteDiario() {
		return idReporteDiario;
	}
	public void setIdReporteDiario(long idReporteDiario) {
		this.idReporteDiario = idReporteDiario;
	}
	public long getIdObra() {
		return idObra;
	}
	public void setIdObra(long idObra) {
		this.idObra = idObra;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getUrlServer() {
		return urlServer;
	}
	public void setUrlServer(String urlServer) {
		this.urlServer = urlServer;
	}
	public String getBlobKey() {
		return blobKey;
	}
	public void setBlobKey(String blobKey) {
		this.blobKey = blobKey;
	}
	public int getSync() {
		return sync;
	}
	public void setSync(int sync) {
		this.sync = sync;
	}
}
