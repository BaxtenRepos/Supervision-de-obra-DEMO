package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cat_Contactos {
@Id
private Long id_cat_contactos;
private Long id_Obra;
private Long id_Poryecto;
private Long id_Persona;
public Long getId_cat_contactos() {
	return id_cat_contactos;
}
public void setId_cat_contactos(Long id_cat_contactos) {
	this.id_cat_contactos = id_cat_contactos;
}
public Long getId_Obra() {
	return id_Obra;
}
public void setId_Obra(Long id_Obra) {
	this.id_Obra = id_Obra;
}
public Long getId_Poryecto() {
	return id_Poryecto;
}
public void setId_Poryecto(Long id_Poryecto) {
	this.id_Poryecto = id_Poryecto;
}
public Long getId_Persona() {
	return id_Persona;
}
public void setId_Persona(Long id_Persona) {
	this.id_Persona = id_Persona;
}

}
