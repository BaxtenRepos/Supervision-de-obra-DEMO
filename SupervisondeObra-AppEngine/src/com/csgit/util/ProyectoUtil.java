package com.csgit.util;

import java.util.ArrayList;

import com.csgit.cao.model.Avance_Financiero;
import com.csgit.cao.model.Avance_Fisico;
import com.csgit.cao.model.Proyecto;

public class ProyectoUtil {
	private Proyecto proyecto;
	private Avance_Financiero avanceFinanciero;
	private Avance_Fisico avanceFisico;
	private ArrayList<ObraUtil> obras;
	
	
	
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	public Avance_Financiero getAvanceFinanciero() {
		return avanceFinanciero;
	}
	public void setAvanceFinanciero(Avance_Financiero avanceFinanciero) {
		this.avanceFinanciero = avanceFinanciero;
	}
	public Avance_Fisico getAvanceFisico() {
		return avanceFisico;
	}
	public void setAvanceFisico(Avance_Fisico avanceFisico) {
		this.avanceFisico = avanceFisico;
	}
	public ArrayList<ObraUtil> getObras() {
		return obras;
	}
	public void setObras(ArrayList<ObraUtil> obras) {
		this.obras = obras;
	}
	
	

}
