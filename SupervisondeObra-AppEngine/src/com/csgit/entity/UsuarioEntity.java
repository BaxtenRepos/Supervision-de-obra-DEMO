package com.csgit.entity;

public class UsuarioEntity implements Comparable<UsuarioEntity>{

	private String nombre;
	private String tipo;
	private Long id;
	private String path;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public int compareTo(UsuarioEntity o) {
		// TODO Auto-generated method stub
		return this.getNombre().toLowerCase().compareTo(o.getNombre().toLowerCase());
	}

}
