package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cat_Tipo_Maquinaria {
    @Id
	private Long id_tipo_Maquinaria;
	private String Tipo_Maquinaria;
	private String Descripcion;
	public Long getId_tipo_Maquinaria() {
		return id_tipo_Maquinaria;
	}
	public void setId_tipo_Maquinaria(Long id_tipo_Maquinaria) {
		this.id_tipo_Maquinaria = id_tipo_Maquinaria;
	}
	public String getTipo_Maquinaria() {
		return Tipo_Maquinaria;
	}
	public void setTipo_Maquinaria(String tipo_Maquinaria) {
		Tipo_Maquinaria = tipo_Maquinaria;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

}
