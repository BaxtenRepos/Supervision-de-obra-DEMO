package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Maquinaria {

@Id
private Long id_Maquinaria;
private Long id_tipo_Maquinaria;
private Long Familia;
private String Path_Imagen;
private boolean visible;

public Long getFamilia() {
	return Familia;
}
public void setFamilia(Long familia) {
	Familia = familia;
}
public String getPath_Imagen() {
	return Path_Imagen;
}
public void setPath_Imagen(String path_Imagen) {
	Path_Imagen = path_Imagen;
}
public Long getId_Familia() {
	return Familia;
}
public void setId_Familia(Long Familia) {
	this.Familia = Familia;
}
private String Descripcion;
private String Nombre;
public Long getId_Maquinaria() {
	return id_Maquinaria;
}
public void setId_Maquinaria(Long id_Maquinaria) {
	this.id_Maquinaria = id_Maquinaria;
}
public Long getId_tipo_Maquinaria() {
	return id_tipo_Maquinaria;
}
public void setId_tipo_Maquinaria(Long id_tipo_Maquinaria) {
	this.id_tipo_Maquinaria = id_tipo_Maquinaria;
}
public String getDescripcion() {
	return Descripcion;
}
public void setDescripcion(String descripcion) {
	Descripcion = descripcion;
}
public String getNombre() {
	return Nombre;
}
public void setNombre(String nombre) {
	Nombre = nombre;
}
public boolean isVisible() {
	return visible;
}
public void setVisible(boolean visible) {
	this.visible = visible;
}
}
