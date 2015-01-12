package com.csgit.entity;

import java.util.Arrays;

public class ProyectoEntity {


	private String idDescripcion;
	private String idDependencia;
	private String idSecretaria;
	private String nombreCorto;
	private String nombreLargo;
	private PuntoEntity[] datosArrayUbicacion;
	private String[] directivo;


	public ProyectoEntity() {
		
	}

	public String getIdDescripcion() {
		return idDescripcion;
	}

	public void setIdDescripcion(String idDescripcion) {
		this.idDescripcion = idDescripcion;
	}

	public String getIdDependencia() {
		return idDependencia;
	}

	public void setIdDependencia(String idDependencia) {
		this.idDependencia = idDependencia;
	}





	public String getIdSecretaria() {
		return idSecretaria;
	}

	public void setIdSecretaria(String idSecretaria) {
		this.idSecretaria = idSecretaria;
	}

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	@Override
	public String toString() {
		return "ProyectoEntity [ idDescripcion="
				+ idDescripcion + ", idDependencia=" + idDependencia
				+ ", idUbicacion=" + ", idSecretaria="
				+ idSecretaria + ", nombreCorto=" + nombreCorto
				+ ", nombreLargo=" + nombreLargo + ", datosArray="
				+ Arrays.toString(datosArrayUbicacion) + "]";
	}

	
	public String getNombreLargo() {
		return nombreLargo;
	}

	public PuntoEntity[] getDatosArrayUbicacion() {
		return datosArrayUbicacion;
	}

	public void setDatosArrayUbicacion(PuntoEntity[] datosArrayUbicacion) {
		this.datosArrayUbicacion = datosArrayUbicacion;
	}

	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}
	
	public String[] getDirectivo() {
		return directivo;
	}

	public void setDirectivo(String[] directivo) {
		this.directivo = directivo;
	}
	
}
