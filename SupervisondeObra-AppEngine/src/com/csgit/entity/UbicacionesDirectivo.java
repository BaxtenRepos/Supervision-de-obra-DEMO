package com.csgit.entity;

import java.util.ArrayList;

import com.csgit.cao.model.Ubicaciones;

public class UbicacionesDirectivo {
	
	private Long idProyecto;
	private String nombreCortoProyecto;
	private Ubicaciones ubicacionProyecto;
	private ArrayList<ObrasUbicacionesEntity> obra;
	public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long long1) {
		this.idProyecto = long1;
	}
	public String getNombreCortoProyecto() {
		return nombreCortoProyecto;
	}
	public void setNombreCortoProyecto(String nombreCortoProyecto) {
		this.nombreCortoProyecto = nombreCortoProyecto;
	}
	public Ubicaciones getUbicacionProyecto() {
		return ubicacionProyecto;
	}
	public void setUbicacionProyecto(Ubicaciones ubicacionProyecto) {
		this.ubicacionProyecto = ubicacionProyecto;
	}
	public ArrayList<ObrasUbicacionesEntity> getObra() {
		return obra;
	}
	public void setObra(ArrayList<ObrasUbicacionesEntity> obra) {
		this.obra = obra;
	}
	
	

}
