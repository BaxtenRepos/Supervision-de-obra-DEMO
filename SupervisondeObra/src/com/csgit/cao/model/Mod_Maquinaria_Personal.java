package com.csgit.cao.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

public class Mod_Maquinaria_Personal {

	private String descripcion;
	private String fecha;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@SuppressLint("SimpleDateFormat")
	public String getFecha() {
		 fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		return fecha;
	} 
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
