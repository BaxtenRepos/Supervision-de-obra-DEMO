package com.csgit.entity;

public class DirectorioEntity {

	String nombre;
	Long id;
	boolean existe;
	Long id_directiorio;
	private String cargo;
	private String email;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isExiste() {
		return existe;
	}
	public void setExiste(boolean existe) {
		this.existe = existe;
	}
	public Long getId_directiorio() {
		return id_directiorio;
	}
	public void setId_directiorio(Long id_directiorio) {
		this.id_directiorio = id_directiorio;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
