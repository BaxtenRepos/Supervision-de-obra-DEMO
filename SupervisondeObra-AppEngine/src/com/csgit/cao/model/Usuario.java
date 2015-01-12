package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Usuario {
	
	@Id
	private Long Id_Propietario;
	private Long Id_Persona;
	private String Usuario;
	private String contrasena;
	private Long id_Tipo_Persona;
	private boolean visible;
	
	
	public Long getId_Persona() {
		return Id_Persona;
	}
	public void setId_Persona(Long id_Persona) {
		Id_Persona = id_Persona;
	}	
	public Long getId_Propietario() {
		return Id_Propietario;
	}
	public void setId_Propietario(Long id_Propietario) {
		Id_Propietario = id_Propietario;
	}

	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public Long getId_Tipo_Persona() {
		return id_Tipo_Persona;
	}
	public void setId_Tipo_Persona(Long id_Tipo_Persona) {
		this.id_Tipo_Persona = id_Tipo_Persona;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	

}
