package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Persona {
    
	@Id
	private Long id_Persona;
    private Long id_Tipo_Persona;
	private String Nombre;
	private String Ap_Paterno;
	private String Ap_Materno;
	private String Iniciales;
	private String Fecha_Nacimiento;
	private String Direccion;
	private String Telefonos;
	private String Radios;
	private String Emails;
	private String Rfc;
	private String Cargo;
	private String TituloProfesional;
	private String CedulaProfesional;
	private String Fotografia;
	private String UsuarioSkype;
	private Long idEmpresa;
	private boolean visible;
	
	public String getIniciales() {
		return Iniciales;
	}
	public void setIniciales(String iniciales) {
		Iniciales = iniciales;
	}
	
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Long getId_Persona() {
		return id_Persona;
	}
	public void setId_Persona(Long id_Persona) {
		this.id_Persona = id_Persona;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getAp_Paterno() {
		return Ap_Paterno;
	}
	public void setAp_Paterno(String ap_Paterno) {
		Ap_Paterno = ap_Paterno;
	}
	public String getAp_Materno() {
		return Ap_Materno;
	}
	public void setAp_Materno(String ap_Materno) {
		Ap_Materno = ap_Materno;
	}
	public String getFecha_Nacimiento() {
		return Fecha_Nacimiento;
	}
	public void setFecha_Nacimiento(String fecha_Nacimiento) {
		Fecha_Nacimiento = fecha_Nacimiento;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public Long getId_Tipo_Persona() {
		return id_Tipo_Persona;
	}
	public void setId_Tipo_Persona(Long id_Tipo_Persona) {
		this.id_Tipo_Persona = id_Tipo_Persona;
	}
	public String getTelefonos() {
		return Telefonos;
	}
	public void setTelefonos(String telefonos) {
		Telefonos = telefonos;
	}
	public String getRadios() {
		return Radios;
	}
	public void setRadios(String radios) {
		Radios = radios;
	}
	public String getEmails() {
		return Emails;
	}
	public void setEmails(String emails) {
		Emails = emails;
	}
	public String getRfc() {
		return Rfc;
	}
	public void setRfc(String rfc) {
		Rfc = rfc;
	}
	public String getCargo() {
		return Cargo;
	}
	public void setCargo(String cargo) {
		Cargo = cargo;
	}
	public String getTituloProfesional() {
		return TituloProfesional;
	}
	public void setTituloProfesional(String tituloProfesional) {
		TituloProfesional = tituloProfesional;
	}
	public String getCedulaProfesional() {
		return CedulaProfesional;
	}
	public void setCedulaProfesional(String cedulaProfesional) {
		CedulaProfesional = cedulaProfesional;
	}
	public String getFotografia() {
		return Fotografia;
	}
	public void setFotografia(String fotografia) {
		Fotografia = fotografia;
	}
	public String getUsuarioSkype() {
		return UsuarioSkype;
	}
	public void setUsuarioSkype(String usuarioSkype) {
		UsuarioSkype = usuarioSkype;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
