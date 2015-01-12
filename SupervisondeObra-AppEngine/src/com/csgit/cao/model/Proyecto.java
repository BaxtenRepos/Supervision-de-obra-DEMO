package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.csgit.entity.PuntoEntity;
import com.google.appengine.api.datastore.Key;

@Entity
public class Proyecto {

@Id
	private Long id_Proyecto;
	private String Nombre_corto;
	private String Nombre_largo;
	private String Descripcion;
	private Long id_ubicacion;
	private Long id_secretaria;
	private Long id_dependencia;
	
	
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public Long getId_Proyecto() {
		return id_Proyecto;
	}
	public void setId_Proyecto(Long id_Proyecto) {
		this.id_Proyecto = id_Proyecto;
	}
	public Long getId_ubicacion() {
		return id_ubicacion;
	}
	public void setId_ubicacion(Long id_ubicacion) {
		this.id_ubicacion = id_ubicacion;
	}
	public Long getId_secretaria() {
		return id_secretaria;
	}
	public void setId_secretaria(Long id_secretaria) {
		this.id_secretaria = id_secretaria;
	}
	public String getNombre_corto() {
		return Nombre_corto;
	}
	public void setNombre_corto(String nombre_corto) {
		Nombre_corto = nombre_corto;
	}
	public String getNombre_largo() {
		return Nombre_largo;
	}
	public void setNombre_largo(String nombre_largo) {
		Nombre_largo = nombre_largo;
	}	
	public Long getId_dependencia() {
		return id_dependencia;
	}
	public void setId_dependencia(Long id_dependencia) {
		this.id_dependencia = id_dependencia;
	}
	
	
	
	
	
}
