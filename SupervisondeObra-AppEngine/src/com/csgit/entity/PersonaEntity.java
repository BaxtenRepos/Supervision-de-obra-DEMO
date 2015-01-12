package com.csgit.entity;

public class PersonaEntity {
	
	private Long id;
	private String nombre;
	private String cargo;
	private String email;
	private String Path_Imagen;
	private String telefono;
	private String empresa;
	private String rfc;
	
	public String getPath_Imagen() {
		return Path_Imagen;
	}
	public void setPath_Imagen(String path_Imagen) {
		Path_Imagen = path_Imagen;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
}
