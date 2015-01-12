package com.csgit.entity;

import com.csgit.cao.Constants;

public class empresaEntity implements Comparable<empresaEntity>{

	private Long idEmpresa;
	private String rfc;
	private String nombre;
	private String siglas;
	private String imss;
	private String calle;
	private String numExt;
	private String numInt;
	private String colonia;
	private String delMun;
	private String entidad;
	private String codPostal;
	private String tipoEmpresa;
	private String direccion;
	private String Path_Imagen;
	
	public String getPath_Imagen() {
		return Path_Imagen;
	}
	public void setPath_Imagen(String path_Imagen) {
		Path_Imagen = path_Imagen;
	}
	public String getImss() {
		return imss;
	}
	public void setImss(String imss) {
		this.imss = imss;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion() {
		this.direccion = calle+" "+numExt+" "+numInt+" "+colonia+" "+delMun+" "+entidad;
	}
	
	public void setTipoEmpresa(Long idTipoEmpresa) 
	{
		if(idTipoEmpresa == 1)		{this.tipoEmpresa = Constants.contratistaTexto;}
		else if(idTipoEmpresa==2)	{this.tipoEmpresa = Constants.supervisoraTexto;}
		else if(idTipoEmpresa==3)	{this.tipoEmpresa = Constants.dependenciaTexto;}
		else if(idTipoEmpresa==4)	{this.tipoEmpresa = Constants.particularTexto;}
		else if(idTipoEmpresa==5)	{this.tipoEmpresa = Constants.secretariaTexto;}
		else if(idTipoEmpresa==6)	{this.tipoEmpresa = Constants.GobiernoTexto;}	
	}
	
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return rfc+nombre+siglas+calle+numExt+numInt+colonia+delMun+entidad+codPostal+tipoEmpresa;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSiglas() {
		return siglas;
	}
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumExt() {
		return numExt;
	}
	public void setNumExt(String numExt) {
		this.numExt = numExt;
	}
	public String getNumInt() {
		return numInt;
	}
	public void setNumInt(String numInt) {
		this.numInt = numInt;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getDelMun() {
		return delMun;
	}
	public void setDelMun(String delMun) {
		this.delMun = delMun;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public String getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	public String getTipoEmpresa() {
		return tipoEmpresa;
	}
	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}
	@Override
	public int compareTo(empresaEntity o) {
		// TODO Auto-generated method stub
		return this.nombre.toLowerCase().compareTo(o.getNombre().toLowerCase());
	}

	

}
