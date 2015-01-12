package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cat_Tipo_Empresa {
@Id
private Long id_Tipo_Empresa;
private String Tipo_Empresa;
private String Descipcion;
public Long getId_Tipo_Empresa() {
	return id_Tipo_Empresa;
}
public void setId_Tipo_Empresa(Long id_Tipo_Empresa) {
	this.id_Tipo_Empresa = id_Tipo_Empresa;
}
public String getTipo_Empresa() {
	return Tipo_Empresa;
}
public void setTipo_Empresa(String tipo_Empresa) {
	Tipo_Empresa = tipo_Empresa;
}
public String getDescipcion() {
	return Descipcion;
}
public void setDescipcion(String descipcion) {
	Descipcion = descipcion;
}

}
