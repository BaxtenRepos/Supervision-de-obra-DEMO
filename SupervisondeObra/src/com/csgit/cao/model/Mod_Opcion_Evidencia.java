package com.csgit.cao.model;

public class Mod_Opcion_Evidencia {

	private int idResource;
	private String opcion;
	
	public Mod_Opcion_Evidencia(int idResource, String opcion){
		this.idResource =  idResource;
		this.opcion = opcion;
	}
	
	public int getIdResource() {
		return idResource;
	}
	public void setIdResource(int idResource) {
		this.idResource = idResource;
	}
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
}
