package com.csgit.cao.model;

import java.util.List;

import com.csgit.cao.model.communicationchannel.model.Concepto;

public class Mod_ConceptoPadre {
	
	private long idPadre;
	private String clave;
	private String nomConcepto;
	private List<Concepto> children;
	
	
	public long getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(long idPadre) {
		this.idPadre = idPadre;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNomConcepto() {
		return nomConcepto;
	}
	public void setNomConcepto(String nomConcepto) {
		this.nomConcepto = nomConcepto;
	}
	public List<Concepto> getChildren() {
		return children;
	}
	public void setChildren(List<Concepto> children) {
		this.children = children;
	}	

}
