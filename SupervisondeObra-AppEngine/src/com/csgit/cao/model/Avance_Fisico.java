package com.csgit.cao.model;


import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Avance_Fisico {
    @Id
	private Long id_AvanceFisico;
	private Long id_referencia;
	private Double PAvanceFisico;
	private String FechaReporte;
	private Integer Estado;
	private Integer Tipo_Entidad;
	private Double porcentaje_tendencia;
	

	public Double getPorcentaje_tendencia() {
		return porcentaje_tendencia;
	}
	public void setPorcentaje_tendencia(Double porcentaje_tendencia) {
		this.porcentaje_tendencia = porcentaje_tendencia;
	}
	public Long getId_AvanceFisico() {
		return id_AvanceFisico;
	}
	public void setId_AvanceFisico(Long id_AvanceFisico) {
		this.id_AvanceFisico = id_AvanceFisico;
	}

	public Long getId_referencia() {
		return id_referencia;
	}
	public void setId_referencia(Long id_referencia) {
		this.id_referencia = id_referencia;
	}


	public Double getPAvanceFisico() {
		return PAvanceFisico;
	}
	public void setPAvanceFisico(Double pAvanceFisico) {
		PAvanceFisico = pAvanceFisico;
	}
	public Integer getEstado() {
		return Estado;
	}
	public void setEstado(Integer estado) {
		Estado = estado;
	}
	public String getFechaReporte() {
		return FechaReporte;
	}
	public void setFechaReporte(String fechaReporte) {
		FechaReporte = fechaReporte;
	}
	public Integer getTipo_Entidad() {
		return Tipo_Entidad;
	}
	public void setTipo_Entidad(Integer tipo_Entidad) {
		Tipo_Entidad = tipo_Entidad;
	}
	

}
