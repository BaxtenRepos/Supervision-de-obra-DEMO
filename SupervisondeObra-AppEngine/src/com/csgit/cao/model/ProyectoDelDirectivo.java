package com.csgit.cao.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.csgit.util.ObraUtil;
@Entity
public class ProyectoDelDirectivo {
	@Id
	private Long id;
	private Proyecto proyecto;
	private ArrayList<Avance_Financiero> avanceFinanciero;
	private ArrayList<Avance_Fisico> avanceFisico;
	private ArrayList<ObraUtil> obras;
	
	
	
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ArrayList<Avance_Financiero> getAvanceFinanciero() {
		return avanceFinanciero;
	}
	public void setAvanceFinanciero(ArrayList<Avance_Financiero> avanceFinanciero) {
		this.avanceFinanciero = avanceFinanciero;
	}
	public ArrayList<Avance_Fisico> getAvanceFisico() {
		return avanceFisico;
	}
	public void setAvanceFisico(ArrayList<Avance_Fisico> avanceFisico) {
		this.avanceFisico = avanceFisico;
	}
	public ArrayList<ObraUtil> getObras() {
		return obras;
	}
	public void setObras(ArrayList<ObraUtil> obras) {
		this.obras = obras;
	}
	
	

}
