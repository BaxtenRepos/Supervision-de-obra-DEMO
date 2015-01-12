package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Cat_Tipo_Presona {
	@Id
	private Long id_Tipo_Persona;
	private String Tipo_Persona;
	private String Descipcion;
	public Long getId_Tipo_Persona() {
		return id_Tipo_Persona;
	}
	public void setId_Tipo_Persona(Long id_Tipo_Persona) {
		this.id_Tipo_Persona = id_Tipo_Persona;
	}
	public String getTipo_Persona() {
		return Tipo_Persona;
	}
	public void setTipo_Persona(String tipo_Persona) {
		Tipo_Persona = tipo_Persona;
	}
	public String getDescipcion() {
		return Descipcion;
	}
	public void setDescipcion(String descipcion) {
		Descipcion = descipcion;
	}
	
}
