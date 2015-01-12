package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Programa {
    @Id
	private Long id_programa;
	private Long id_referencia;
	private Integer tipo_entidad;
	private Integer tipo_avance;
	private String fecha;
	private Double porcentaje_avance;
	public Long getId_programa() {
		return id_programa;
	}
	public void setId_programa(Long id_programa) {
		this.id_programa = id_programa;
	}
	public Long getId_referencia() {
		return id_referencia;
	}
	public void setId_referencia(Long id_referencia) {
		this.id_referencia = id_referencia;
	}
	public Integer getTipo_entidad() {
		return tipo_entidad;
	}
	public void setTipo_entidad(Integer tipo_entidad) {
		this.tipo_entidad = tipo_entidad;
	}
	public Integer getTipo_avance() {
		return tipo_avance;
	}
	public void setTipo_avance(Integer tipo_avance) {
		this.tipo_avance = tipo_avance;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Double getPorcentaje_avance() {
		return porcentaje_avance;
	}
	public void setPorcentaje_avance(Double porcentaje_avance) {
		this.porcentaje_avance = porcentaje_avance;
	}
}
