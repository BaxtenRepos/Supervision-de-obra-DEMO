package com.csgit.entity;

import com.csgit.cao.Constants;

public class UsuarioDetalleEntity  implements Comparable<UsuarioDetalleEntity>
{
	private Long id;
	private String tipoUsuario;
	private String usuario;
	private String nombre;
	private String telefonos;
	
	public void setTipoUsuario(Long idTipoUsuario) //Sobrecarga de contructor que recibe Long IDTipoUsuario
	{
		if(idTipoUsuario == 1)		{this.tipoUsuario = Constants.administradorTexto;}
		else if(idTipoUsuario==2)	{this.tipoUsuario = Constants.directivoTexto;}
		else if(idTipoUsuario==3)	{this.tipoUsuario = Constants.supervisorTexto;}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

	@Override
	public int compareTo(UsuarioDetalleEntity o) {
		// TODO Auto-generated method stub
		return this.usuario.toLowerCase().compareTo(o.getUsuario().toLowerCase());
	}


	

}
