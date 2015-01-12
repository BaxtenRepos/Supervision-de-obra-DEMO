package com.csgit.cao.model;

public class Mod_Informacion_Obra {

	private String titulo;
	private String label;
	private String valor;
	
	public Mod_Informacion_Obra(String label, String valor){
		this.label = label;
		this.valor = valor;
	}
	
	public Mod_Informacion_Obra(String titulo){
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
