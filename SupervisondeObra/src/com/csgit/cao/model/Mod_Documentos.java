package com.csgit.cao.model;

public class Mod_Documentos {

	private long idDocumento;
	private long idObra;
	private String pathDocumento;
	private String formato;
	private String fecha;
	private int idResource;
	private String nombreDocumento;	
	private String extDocumento;	
	private String mimeType;
	private String urlServer;
	private String blobKey;
	private int isSync;
	private int tipoArchivo;
	
	
	public int getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(int tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}
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
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public long getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(long idDocumento) {
		this.idDocumento = idDocumento;
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getIdResource() {
		return idResource;
	}
	public void setIdResource(int idResource) {
		this.idResource = idResource;
	}
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	public String getPathDocumento() {
		return pathDocumento;
	}
	public void setPathDocumento(String pathDocumento) {
		this.pathDocumento = pathDocumento;
	}
	public String getExtDocumento() {
		return extDocumento;
	}
	public void setExtDocumento(String extDocumento) {
		this.extDocumento = extDocumento;
	}
	
	
}
