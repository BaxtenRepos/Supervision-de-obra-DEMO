package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Directorio {
	@Id
	private Long idDirectorio;
	private Long idReferencia;
	private Long TipoReferencia;
	private String[] arregloPersonasId;
	public Long getIdDirectorio() {
		return idDirectorio;
	}
	public void setIdDirectorio(Long idDirectorio) {
		this.idDirectorio = idDirectorio;
	}
	public Long getIdReferencia() {
		return idReferencia;
	}
	public void setIdReferencia(Long idReferencia) {
		this.idReferencia = idReferencia;
	}
	public Long getTipoReferencia() {
		return TipoReferencia;
	}
	public void setTipoReferencia(Long tipoReferencia) {
		TipoReferencia = tipoReferencia;
	}
	public String[] getArregloPersonasId() {
		return arregloPersonasId;
	}
	public void setArregloPersonasId(String[] arregloPersonasId) {
		this.arregloPersonasId = arregloPersonasId;
	}
	
	
	
	

}
