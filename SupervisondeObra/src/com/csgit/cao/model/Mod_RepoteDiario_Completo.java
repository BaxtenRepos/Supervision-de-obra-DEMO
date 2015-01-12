package com.csgit.cao.model;

import java.util.List;

import com.csgit.cao.model.communicationchannel.model.Concepto;
import com.csgit.cao.model.communicationchannel.model.Notas;

public class Mod_RepoteDiario_Completo {

	private long idReporte;
	private long idObra;
	private String fechaReporteDiario;
	private List<Notas> notas;
	private List<Mod_Multimedia> evidencias;
//	private List<Mod_ReporteDiario_Cantidades> reporteMaquinaria;
//	private List<Mod_ReporteDiario_Cantidades> reportePersonal;
	private List<Mod_Repo_Maquinaria_Cat_Maquinaria> reporteMaquinaria;
	private List<Mod_Repo_Maquinaria_Cat_Maquinaria> reportePersonal;
	private List<Concepto> conceptos;
	
	
	public long getIdReporte() {
		return idReporte;
	}
	public void setIdReporte(long idReporte) {
		this.idReporte = idReporte;
	}
	public long getIdObra() {
		return idObra;
	}
	public void setIdObra(long idObra) {
		this.idObra = idObra;
	}
	public String getFechaReporteDiario() {
		return fechaReporteDiario;
	}
	public void setFechaReporteDiario(String fechaReporteDiario) {
		this.fechaReporteDiario = fechaReporteDiario;
	}
	public List<Notas> getNotas() {
		return notas;
	}
	public void setNotas(List<Notas> notas) {
		this.notas = notas;
	}
	public List<Mod_Multimedia> getEvidencias() {
		return evidencias;
	}
	public void setEvidencias(List<Mod_Multimedia> evidencias) {
		this.evidencias = evidencias;
	}
//	public List<Mod_ReporteDiario_Cantidades> getReporteMaquinaria() {
//		return reporteMaquinaria;
//	}
//	public void setReporteMaquinaria(
//			List<Mod_ReporteDiario_Cantidades> reporteMaquinaria) {
//		this.reporteMaquinaria = reporteMaquinaria;
//	}
//	public List<Mod_ReporteDiario_Cantidades> getReportePersonal() {
//		return reportePersonal;
//	}
//	public void setReportePersonal(
//			List<Mod_ReporteDiario_Cantidades> reportePersonal) {
//		this.reportePersonal = reportePersonal;
//	}
	
	
	public List<Concepto> getConceptos() {
		return conceptos;
	}
	public List<Mod_Repo_Maquinaria_Cat_Maquinaria> getReporteMaquinaria() {
		return reporteMaquinaria;
	}
	public void setReporteMaquinaria(
			List<Mod_Repo_Maquinaria_Cat_Maquinaria> reporteMaquinaria) {
		this.reporteMaquinaria = reporteMaquinaria;
	}
	public List<Mod_Repo_Maquinaria_Cat_Maquinaria> getReportePersonal() {
		return reportePersonal;
	}
	public void setReportePersonal(
			List<Mod_Repo_Maquinaria_Cat_Maquinaria> reportePersonal) {
		this.reportePersonal = reportePersonal;
	}
	public void setConceptos(List<Concepto> conceptos) {
		this.conceptos = conceptos;
	}
	
}
