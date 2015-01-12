package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Push {
	@Id
	private Long Id_Propietario;
	private String Payload;
	private String to;
	public Long getId_Propietario() {
		return Id_Propietario;
	}
	public void setId_Propietario(Long id_Propietario) {
		Id_Propietario = id_Propietario;
	}
	public String getPayload() {
		return Payload;
	}
	public void setPayload(String payload) {
		Payload = payload;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
		
		// TODO Auto-generated constructor stub
	

}
