package com.csgit.cao.model;

public class Mod_Minutas {
	
	private Long idMinutas;
	private Long idObra;
	private String formato;
	private String pathMinutas;
	private String fechaMinutas;
	private int tipoArchivo;
	private String urlServer;
	private String blobKey;
	private int isSync;
	
	
	public int getIsSync() {
		return isSync;
	}
	public void setIsSync(int isSync) {
		this.isSync = isSync;
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
	public int getTipo() {
		return tipoArchivo;
	}
	public void setTipo(int tipo) {
		this.tipoArchivo = tipo;
	}
	public Long getIdMinutas() {
		return idMinutas;
	}
	public void setIdMinutas(Long idMinutas) {
		this.idMinutas = idMinutas;
	}
	public Long getIdObra() {
		return idObra;
	}
	public void setIdObra(Long idObra) {
		this.idObra = idObra;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getPathMinutas() {
		return pathMinutas;
	}
	public void setPathMinutas(String pathMinutas) {
		this.pathMinutas = pathMinutas;
	}
	public String getFechaMinutas() {
		return fechaMinutas;
	}
	public void setFechaMinutas(String fechaMinutas) {
		this.fechaMinutas = fechaMinutas;
	}
	
}
