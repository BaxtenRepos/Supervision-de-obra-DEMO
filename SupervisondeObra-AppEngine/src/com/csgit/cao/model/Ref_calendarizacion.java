package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Ref_calendarizacion {
	@Id
	private Long id_ref_Calendarizaion;
	private String path;
	public Long getId_ref_Calendarizaion() {
		return id_ref_Calendarizaion;
	}
	public void setId_ref_Calendarizaion(Long id_ref_Calendarizaion) {
		this.id_ref_Calendarizaion = id_ref_Calendarizaion;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

}
