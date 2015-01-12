package com.csgit.cao.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Reportsmodel {
    @Id
	private Long IdReporte;
    private Long IdConsecutivo;
	private Long IdProyecto;
	private Long IdObra;
	private String Nombre_Contrato;
	private String Numero_informe;
	private String Numero_Contrato;
	private String Fecha_Contrato;
	private Long IdEmpresa_Comtratista;
	private String Supervisor_Obra;
	private String Direccion_Adscipcion;
	private String Residente_Obra;
	private Long Idubicacion;
	private Double Importe_Contrato;
	private Double Anticipo_Contrato;
	private String Fecha_Inicio;
	private String Fecha_Termino;
	private Integer Periodo_Ejecucion;
	private Double Avance_Fisico_Reportado;
	private Double Avance_Financiero_Reportado;
	private Double Importe_Acunulado;
	private Double Saldo_Contrato;
	private String Conveio_Numero;
	private String Fecha_Convenio;
	private Double Importe_Convenio;
	private List<Long> IdConceptos;
	private List<Long> IdMultimedia;
	
	
	
	public Long getIdConsecutivo() {
		return IdConsecutivo;
	}
	public void setIdConsecutivo(Long idConsecutivo) {
		IdConsecutivo = idConsecutivo;
	}
	public Long getIdProyecto() {
		return IdProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		IdProyecto = idProyecto;
	}
	public Long getIdObra() {
		return IdObra;
	}
	public void setIdObra(Long idObra) {
		IdObra = idObra;
	}
	public Long getIdReporte() {
		return IdReporte;
	}
	public void setIdReporte(Long idReporte) {
		IdReporte = idReporte;
	}
	public String getNombre_Contrato() {
		return Nombre_Contrato;
	}
	public void setNombre_Contrato(String nombre_Contrato) {
		Nombre_Contrato = nombre_Contrato;
	}
	public String getNumero_informe() {
		return Numero_informe;
	}
	public void setNumero_informe(String numero_informe) {
		Numero_informe = numero_informe;
	}
	public String getNumero_Contrato() {
		return Numero_Contrato;
	}
	public void setNumero_Contrato(String numero_Contrato) {
		Numero_Contrato = numero_Contrato;
	}
	public String getFecha_Contrato() {
		return Fecha_Contrato;
	}
	public void setFecha_Contrato(String fecha_Contrato) {
		Fecha_Contrato = fecha_Contrato;
	}

	public String getSupervisor_Obra() {
		return Supervisor_Obra;
	}
	public void setSupervisor_Obra(String supervisor_Obra) {
		Supervisor_Obra = supervisor_Obra;
	}
	public String getDireccion_Adscipcion() {
		return Direccion_Adscipcion;
	}
	public void setDireccion_Adscipcion(String direccion_Adscipcion) {
		Direccion_Adscipcion = direccion_Adscipcion;
	}
	public String getResidente_Obra() {
		return Residente_Obra;
	}
	public void setResidente_Obra(String residente_Obra) {
		Residente_Obra = residente_Obra;
	}

	public Long getIdubicacion() {
		return Idubicacion;
	}
	public void setIdubicacion(Long idubicacion) {
		Idubicacion = idubicacion;
	}
	public Double getImporte_Contrato() {
		return Importe_Contrato;
	}
	public void setImporte_Contrato(Double importe_Contrato) {
		Importe_Contrato = importe_Contrato;
	}
	public Double getAnticipo_Contrato() {
		return Anticipo_Contrato;
	}
	public void setAnticipo_Contrato(Double anticipo_Contrato) {
		Anticipo_Contrato = anticipo_Contrato;
	}
	public String getFecha_Inicio() {
		return Fecha_Inicio;
	}
	public void setFecha_Inicio(String fecha_Inicio) {
		Fecha_Inicio = fecha_Inicio;
	}
	public String getFecha_Termino() {
		return Fecha_Termino;
	}
	public void setFecha_Termino(String fecha_Termino) {
		Fecha_Termino = fecha_Termino;
	}
	public Integer getPeriodo_Ejecucion() {
		return Periodo_Ejecucion;
	}
	public void setPeriodo_Ejecucion(Integer periodo_Ejecucion) {
		Periodo_Ejecucion = periodo_Ejecucion;
	}
	public Double getAvance_Fisico_Reportado() {
		return Avance_Fisico_Reportado;
	}
	public void setAvance_Fisico_Reportado(Double avance_Fisico_Reportado) {
		Avance_Fisico_Reportado = avance_Fisico_Reportado;
	}
	public Double getAvance_Financiero_Reportado() {
		return Avance_Financiero_Reportado;
	}
	public void setAvance_Financiero_Reportado(Double avance_Financiero_Reportado) {
		Avance_Financiero_Reportado = avance_Financiero_Reportado;
	}
	public Double getImporte_Acunulado() {
		return Importe_Acunulado;
	}
	public void setImporte_Acunulado(Double importe_Acunulado) {
		Importe_Acunulado = importe_Acunulado;
	}
	public Double getSaldo_Contrato() {
		return Saldo_Contrato;
	}
	public void setSaldo_Contrato(Double saldo_Contrato) {
		Saldo_Contrato = saldo_Contrato;
	}
	public String getConveio_Numero() {
		return Conveio_Numero;
	}
	public void setConveio_Numero(String conveio_Numero) {
		Conveio_Numero = conveio_Numero;
	}
	public String getFecha_Convenio() {
		return Fecha_Convenio;
	}
	public void setFecha_Convenio(String fecha_Convenio) {
		Fecha_Convenio = fecha_Convenio;
	}
	public Double getImporte_Convenio() {
		return Importe_Convenio;
	}
	public void setImporte_Convenio(Double importe_Convenio) {
		Importe_Convenio = importe_Convenio;
	}
	public Long getIdEmpresa_Comtratista() {
		return IdEmpresa_Comtratista;
	}
	public void setIdEmpresa_Comtratista(Long idEmpresa_Comtratista) {
		IdEmpresa_Comtratista = idEmpresa_Comtratista;
	}
	public List<Long> getIdConceptos() {
		return IdConceptos;
	}
	public void setIdConceptos(List<Long> idConceptos) {
		IdConceptos = idConceptos;
	}
	public List<Long> getIdMultimedia() {
		return IdMultimedia;
	}
	public void setIdMultimedia(List<Long> idMultimedia) {
		IdMultimedia = idMultimedia;
	}

	
	

	
}
