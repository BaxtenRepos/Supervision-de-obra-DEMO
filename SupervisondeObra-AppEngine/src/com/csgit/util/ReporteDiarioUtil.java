package com.csgit.util;

import java.util.ArrayList;

import com.csgit.cao.model.RepMaquinariaCatMaquinaria;
import com.csgit.cao.model.ReporteDiario;

public class ReporteDiarioUtil {
	
	private ReporteDiario reporteDiario;
	
	private ArrayList<RepMaquinariaCatMaquinaria> repMaquinariaCatMaquinaria;
	public ReporteDiario getReporteDiario() {
		return reporteDiario;
	}
	public void setReporteDiario(ReporteDiario reporteDiario) {
		this.reporteDiario = reporteDiario;
	}
	public ArrayList<RepMaquinariaCatMaquinaria> getRepMaquinariaCatMaquinaria() {
		return repMaquinariaCatMaquinaria;
	}
	public void setRepMaquinariaCatMaquinaria(
			ArrayList<RepMaquinariaCatMaquinaria> repMaquinariaCatMaquinaria) {
		this.repMaquinariaCatMaquinaria = repMaquinariaCatMaquinaria;
	}

	
	

}
