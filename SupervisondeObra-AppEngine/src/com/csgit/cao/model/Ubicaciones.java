package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ubicaciones {
@Id
private Long id_Ubicacion;
private String[] Ubicacion;



public Long getId_Ubicacion() {
	return id_Ubicacion;
}
public void setId_Ubicacion(Long id_Ubicacion) {
	this.id_Ubicacion = id_Ubicacion;
}

public String[] getUbicacion() {
	return Ubicacion;
}
public void setUbicacion(String[] ubicacion) {
	Ubicacion = ubicacion;
}


}
