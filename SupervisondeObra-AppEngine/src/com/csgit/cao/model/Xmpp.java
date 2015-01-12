package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Xmpp {
	@Id
	private Long idXmpp;
	private String mensaje;
	public Long getIdXmpp() {
		return idXmpp;
	}
	public void setIdXmpp(Long idXmpp) {
		this.idXmpp = idXmpp;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
