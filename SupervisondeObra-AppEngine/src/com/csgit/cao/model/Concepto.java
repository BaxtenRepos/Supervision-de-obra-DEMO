package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Text;

@Entity
public class Concepto {

	@Id
	private Long id_Concepto;
	private Long id_Obra;
	private String Clave;
	private Long padre;
	private Text Descripcion;
	private String UnidadMedida;
	private Double CantidadTotal;
	private Double PrecioUnitario;
	private Double Importe;
	private Double CantidadAvance;
	private String Fecha_Inicio;
	private boolean visible;

	public Long getPadre() {
		return padre;
	}

	public void setPadre(Long padre) {
		this.padre = padre;
	}

	public String getClave() {
		return Clave;
	}

	public void setClave(String clave) {
		Clave = clave;
	}

	public Long getId_Concepto() {
		return id_Concepto;
	}

	public void setId_Concepto(Long id_Concepto) {
		this.id_Concepto = id_Concepto;
	}

	private String Fecha_Fin;

	public Long getId_Obra() {
		return id_Obra;
	}

	public void setId_Obra(Long id_Obra) {
		this.id_Obra = id_Obra;
	}

	public String getFecha_Inicio() {
		return Fecha_Inicio;
	}

	public void setFecha_Inicio(String fecha_Inicio) {
		Fecha_Inicio = fecha_Inicio;
	}

	public String getFecha_Fin() {
		return Fecha_Fin;
	}

	public void setFecha_Fin(String fecha_Fin) {
		Fecha_Fin = fecha_Fin;
	}

	

	public Text getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(Text descripcion) {
		Descripcion = descripcion;
	}

	public String getUnidadMedida() {
		return UnidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		UnidadMedida = unidadMedida;
	}


	public Double getCantidadTotal() {
		return CantidadTotal;
	}

	public void setCantidadTotal(Double cantidadTotal) {
		CantidadTotal = cantidadTotal;
	}

	public Double getPrecioUnitario() {
		return PrecioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		PrecioUnitario = precioUnitario;
	}

	public Double getImporte() {
		return Importe;
	}

	public void setImporte(Double importe) {
		Importe = importe;
	}

	public Double getCantidadAvance() {
		return CantidadAvance;
	}

	public void setCantidadAvance(Double cantidadAvance) {
		CantidadAvance = cantidadAvance;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
