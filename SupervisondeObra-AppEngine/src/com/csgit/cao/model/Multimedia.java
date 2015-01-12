package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Multimedia {
	
	@Id
	private Long idMultimedia;
	private Long idReferencia;
	private int tipoArchivo;
	private int tipoReferencia;
	private String path;
	private String descripcion;
	private String fecha;
	private Long formato;
	
	public Long getIdMultimedia() {
		return idMultimedia;
	}
	public void setIdMultimedia(Long idMultimedia) {
		this.idMultimedia = idMultimedia;
	}


	public Long getIdReferencia() {
		return idReferencia;
	}
	public void setIdReferencia(Long idReferencia) {
		this.idReferencia = idReferencia;
	}
	public int getTipoReferencia() {
		return tipoReferencia;
	}
	public void setTipoReferencia(int tipoReferencia) {
		this.tipoReferencia = tipoReferencia;
	}
	

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}


	public int getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(int tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Long getFormato() {
		return formato;
	}
	public void setFormato(Long formato) {
		this.formato = formato;
	}
	
		
}
