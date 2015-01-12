package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Directivo_Proyecto {

 @Id
 private Long id;
 private Long id_proyecto;
 private Long id_directivo;

 
 
 public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getId_proyecto() {
	return id_proyecto;
}
public void setId_proyecto(Long id_proyecto) {
	this.id_proyecto = id_proyecto;
}
public Long getId_directivo() {
	return id_directivo;
}
public void setId_directivo(Long id_directivo) {
	this.id_directivo = id_directivo;
}

}
