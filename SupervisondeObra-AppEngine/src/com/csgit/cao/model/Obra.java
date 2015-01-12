package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Obra {
	
	@Id
	private Long id_Obra;
	private Long id_Poyecto;
	private String NoContrato;
	private String RFC; 
	private String Nombre;
	private Long idGobierno;
	private Long idSecretaria;
	private Long idDependencia;
	private String Direccion;
	private String Subdireccion;
	private String Area;
	private Long id_EmpresaContratista;
	private String Superintendente;
	private String EntidadFederativa;
	private String Descripcion;
	private String FechaContrato;
	private String TipoContrato;
	private Double ImporteContratoSinIVA ;
	private String NombreEjercicioFiscal1;
	private Double ImporteFiscal1SinIVA;
	private Double ImporteConvenioAmpliacion;
	private Double ImporteConvenioReduccion;
	private Double ImporteAjusteCostos;
	private String FechaInicioContrato;
	private String FechaTerminoContrato;
	private Integer PeriodoEjucionDias;
	private String PartidaPresupuestal;
	private String Anticipo;
	private String NoFianzaAnticipo;
	private String FechaFianzaAnticipo ;
	private Double MontoFianzaAnticipo;
	private String NoFianzaCumplimiento;
	private String FechaFianzaCumplimiento;
	private Double MontoFianzaCumplimiento;
	private String CargoRevision1;
	private String NombreRevision1;
	private String CargoRevision2;
	private String NombreRevision2;
	private String NombreQuienAutoriza;
	private String CargoVoBo;
	private String NombreVoBo;
	private Long id_ubicacion;
	private Long idUsuario;
	private Double porcentajeDesvio;
	private boolean visible;
	
	public Long getId_ubicacion() {
		return id_ubicacion;
	}
	public void setId_ubicacion(Long id_ubicacion) {
		this.id_ubicacion = id_ubicacion;
	}
	public Integer getLimite_Desvio() {
		return Limite_Desvio;
	}
	public void setLimite_Desvio(Integer limite_Desvio) {
		Limite_Desvio = limite_Desvio;
	}
	private String Borrador;
	private Integer Limite_Desvio;
	
	public Long getId_Obra() {
		return id_Obra;
	}
	public void setId_Obra(Long id_Obra) {
		this.id_Obra = id_Obra;
	}
	public Long getId_Poyecto() {
		return id_Poyecto;
	}
	public void setId_Poyecto(Long id_Poyecto) {
		this.id_Poyecto = id_Poyecto;
	}
	public String getNoContrato() {
		return NoContrato;
	}
	public void setNoContrato(String noContrato) {
		NoContrato = noContrato;
	}
	public String getRFC() {
		return RFC;
	}
	public void setRFC(String rFC) {
		RFC = rFC;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	public Long getIdGobierno() {
		return idGobierno;
	}
	public void setIdGobierno(Long idGobierno) {
		this.idGobierno = idGobierno;
	}
	public Long getIdSecretaria() {
		return idSecretaria;
	}
	public void setIdSecretaria(Long idSecretaria) {
		this.idSecretaria = idSecretaria;
	}
	public Long getIdDependencia() {
		return idDependencia;
	}
	public void setIdDependencia(Long idDependencia) {
		this.idDependencia = idDependencia;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getSubdireccion() {
		return Subdireccion;
	}
	public void setSubdireccion(String subdireccion) {
		Subdireccion = subdireccion;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public Long getId_EmpresaContratista() {
		return id_EmpresaContratista;
	}
	public void setId_EmpresaContratista(Long id_EmpresaContratista) {
		this.id_EmpresaContratista = id_EmpresaContratista;
	}
	public String getSuperintendente() {
		return Superintendente;
	}
	public void setSuperintendente(String superintendente) {
		Superintendente = superintendente;
	}
	public String getEntidadFederativa() {
		return EntidadFederativa;
	}
	public void setEntidadFederativa(String entidadFederativa) {
		EntidadFederativa = entidadFederativa;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getFechaContrato() {
		return FechaContrato;
	}
	public void setFechaContrato(String fechaContrato) {
		FechaContrato = fechaContrato;
	}
	public String getTipoContrato() {
		return TipoContrato;
	}
	public void setTipoContrato(String tipoContrato) {
		TipoContrato = tipoContrato;
	}
	public Double getImporteContratoSinIVA() {
		return ImporteContratoSinIVA;
	}
	public void setImporteContratoSinIVA(Double importeContratoSinIVA) {
		ImporteContratoSinIVA = importeContratoSinIVA;
	}
	public String getNombreEjercicioFiscal1() {
		return NombreEjercicioFiscal1;
	}
	public void setNombreEjercicioFiscal1(String nombreEjercicioFiscal1) {
		NombreEjercicioFiscal1 = nombreEjercicioFiscal1;
	}
	public Double getImporteFiscal1SinIVA() {
		return ImporteFiscal1SinIVA;
	}
	public void setImporteFiscal1SinIVA(Double importeFiscal1SinIVA) {
		ImporteFiscal1SinIVA = importeFiscal1SinIVA;
	}
	public Double getImporteConvenioAmpliacion() {
		return ImporteConvenioAmpliacion;
	}
	public void setImporteConvenioAmpliacion(Double importeConvenioAmpliacion) {
		ImporteConvenioAmpliacion = importeConvenioAmpliacion;
	}
	public Double getImporteConvenioReduccion() {
		return ImporteConvenioReduccion;
	}
	public void setImporteConvenioReduccion(Double importeConvenioReduccion) {
		ImporteConvenioReduccion = importeConvenioReduccion;
	}
	public Double getImporteAjusteCostos() {
		return ImporteAjusteCostos;
	}
	public void setImporteAjusteCostos(Double importeAjusteCostos) {
		ImporteAjusteCostos = importeAjusteCostos;
	}
	public String getFechaInicioContrato() {
		return FechaInicioContrato;
	}
	public void setFechaInicioContrato(String fechaInicioContrato) {
		FechaInicioContrato = fechaInicioContrato;
	}
	public String getFechaTerminoContrato() {
		return FechaTerminoContrato;
	}
	public void setFechaTerminoContrato(String fechaTerminoContrato) {
		FechaTerminoContrato = fechaTerminoContrato;
	}
	public Integer getPeriodoEjucionDias() {
		return PeriodoEjucionDias;
	}
	public void setPeriodoEjucionDias(Integer periodoEjucionDias) {
		PeriodoEjucionDias = periodoEjucionDias;
	}
	public String getPartidaPresupuestal() {
		return PartidaPresupuestal;
	}
	public void setPartidaPresupuestal(String partidaPresupuestal) {
		PartidaPresupuestal = partidaPresupuestal;
	}
	public String getAnticipo() {
		return Anticipo;
	}
	public void setAnticipo(String anticipo) {
		Anticipo = anticipo;
	}
	public String getNoFianzaAnticipo() {
		return NoFianzaAnticipo;
	}
	public void setNoFianzaAnticipo(String noFianzaAnticipo) {
		NoFianzaAnticipo = noFianzaAnticipo;
	}
	public String getFechaFianzaAnticipo() {
		return FechaFianzaAnticipo;
	}
	public void setFechaFianzaAnticipo(String fechaFianzaAnticipo) {
		FechaFianzaAnticipo = fechaFianzaAnticipo;
	}
	public Double getMontoFianzaAnticipo() {
		return MontoFianzaAnticipo;
	}
	public void setMontoFianzaAnticipo(Double montoFianzaAnticipo) {
		MontoFianzaAnticipo = montoFianzaAnticipo;
	}
	public String getNoFianzaCumplimiento() {
		return NoFianzaCumplimiento;
	}
	public void setNoFianzaCumplimiento(String noFianzaCumplimiento) {
		NoFianzaCumplimiento = noFianzaCumplimiento;
	}
	public String getFechaFianzaCumplimiento() {
		return FechaFianzaCumplimiento;
	}
	public void setFechaFianzaCumplimiento(String fechaFianzaCumplimiento) {
		FechaFianzaCumplimiento = fechaFianzaCumplimiento;
	}
	public Double getMontoFianzaCumplimiento() {
		return MontoFianzaCumplimiento;
	}
	public void setMontoFianzaCumplimiento(Double montoFianzaCumplimiento) {
		MontoFianzaCumplimiento = montoFianzaCumplimiento;
	}
	public String getCargoRevision1() {
		return CargoRevision1;
	}
	public void setCargoRevision1(String cargoRevision1) {
		CargoRevision1 = cargoRevision1;
	}
	public String getNombreRevision1() {
		return NombreRevision1;
	}
	public void setNombreRevision1(String nombreRevision1) {
		NombreRevision1 = nombreRevision1;
	}
	public String getCargoRevision2() {
		return CargoRevision2;
	}
	public void setCargoRevision2(String cargoRevision2) {
		CargoRevision2 = cargoRevision2;
	}
	public String getNombreRevision2() {
		return NombreRevision2;
	}
	public void setNombreRevision2(String nombreRevision2) {
		NombreRevision2 = nombreRevision2;
	}
	public String getNombreQuienAutoriza() {
		return NombreQuienAutoriza;
	}
	public void setNombreQuienAutoriza(String nombreQuienAutoriza) {
		NombreQuienAutoriza = nombreQuienAutoriza;
	}
	public String getCargoVoBo() {
		return CargoVoBo;
	}
	public void setCargoVoBo(String cargoVoBo) {
		CargoVoBo = cargoVoBo;
	}
	public String getNombreVoBo() {
		return NombreVoBo;
	}
	public void setNombreVoBo(String nombreVoBo) {
		NombreVoBo = nombreVoBo;
	}
	public String getBorrador() {
		return Borrador;
	}
	public void setBorrador(String borrador) {
		Borrador = borrador;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Double getPorcentajeDesvio() {
		return porcentajeDesvio;
	}
	public void setPorcentajeDesvio(Double porcentajeDesvio) {
		this.porcentajeDesvio = porcentajeDesvio;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}


}
