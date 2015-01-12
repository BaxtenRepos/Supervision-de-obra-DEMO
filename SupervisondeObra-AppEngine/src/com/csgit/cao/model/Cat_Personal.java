package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Cat_Personal {
	
	@Id
	private Long id_Tipo_Personal;
	private String Tipo_Personal;
	private String Descipcion;
	private boolean visible;
	
	public Long getId_Tipo_Personal() {
		return id_Tipo_Personal;
	}
	public void setId_Tipo_Personal(Long id_Tipo_Personal) {
		this.id_Tipo_Personal = id_Tipo_Personal;
	}
	public String getTipo_Personal() {
		return Tipo_Personal;
	}
	public void setTipo_Personal(String tipo_Personal) {
		Tipo_Personal = tipo_Personal;
	}
	public String getDescipcion() {
		return Descipcion;
	}
	public void setDescipcion(String descipcion) {
		Descipcion = descipcion;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
