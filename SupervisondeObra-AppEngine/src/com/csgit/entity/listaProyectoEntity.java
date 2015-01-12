package com.csgit.entity;

import java.util.Collection;

public class listaProyectoEntity {
	
	
	private Long id_Proyecto;
	private String Nombre_corto;
	private String Nombre_largo;
	private String Descripcion;
	private String secretaria;
	private String dependencia;
	private Collection<String> directivo;
	private Long id_secretaria;
	private Long id_dependencia;
	
	public Long getId_Proyecto() {
		return id_Proyecto;
	}
	public void setId_Proyecto(Long id_Proyecto) {
		this.id_Proyecto = id_Proyecto;
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
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getSecretaria() {
		return secretaria;
	}
	public void setSecretaria(String secretaria) {
		this.secretaria = secretaria;
	}
	public String getDependencia() {
		return dependencia;
	}
	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}
	public Collection<String> getDirectivo() {
		return directivo;
	}
	public void setDirectivo(Collection<String> directivo) {
		this.directivo = directivo;
	}
	public Long getId_secretaria() {
		return id_secretaria;
	}
	public void setId_secretaria(Long id_secretaria) {
		this.id_secretaria = id_secretaria;
	}
	public Long getId_dependencia() {
		return id_dependencia;
	}
	public void setId_dependencia(Long id_dependencia) {
		this.id_dependencia = id_dependencia;
	}

}
