package com.csgit.entity;

import com.csgit.cao.Constants;

public class MaquinariaEntity {
	
	private Long id_Maquinaria;
	private String tipo_Maquinaria;
	private String Path_Imagen;
	private String Descripcion;
	private String Nombre;
	private Long id_tipo_Maquinaria;
	private Long idMultimedia;

	public Long getId_Maquinaria() {
		return id_Maquinaria;
	}
	public void setId_Maquinaria(Long id_Maquinaria) {
		this.id_Maquinaria = id_Maquinaria;
	}
	public String getTipo_Maquinaria() {
		return tipo_Maquinaria;
	}
	public void setTipo_Maquinaria(String tipo_Maquinaria) {
		this.tipo_Maquinaria = tipo_Maquinaria;
	}
	public void setTipo_Maquinaria(Long id_tipo_Maquinaria){
		if(id_tipo_Maquinaria == 1)		{this.tipo_Maquinaria = Constants.pesadaTexto;}
		else if(id_tipo_Maquinaria == 2)	{this.tipo_Maquinaria = Constants.ligeraTexto;}
		else if(id_tipo_Maquinaria == 3)	{this.tipo_Maquinaria = Constants.equipoTexto;}
	}
	public String getPath_Imagen() {
		return Path_Imagen;
	}
	public void setPath_Imagen(String path_Imagen) {
		Path_Imagen = path_Imagen;
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
	public Long getId_tipo_Maquinaria() {
		return id_tipo_Maquinaria;
	}
	public void setId_tipo_Maquinaria(Long id_tipo_Maquinaria) {
		this.id_tipo_Maquinaria = id_tipo_Maquinaria;
	}
	public Long getIdMultimedia() {
		return idMultimedia;
	}
	public void setIdMultimedia(Long idMultimedia) {
		this.idMultimedia = idMultimedia;
	}
	
}
