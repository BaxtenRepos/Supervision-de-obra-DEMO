package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Empresa {
	
	@Id
private Long id_Empresa;
private Long id_tipo_empresa;
private String RFC;
private String Nombre;
private String Siglas;
private String IMSS;
private String Calle;
private String Num_Ext;
private String Num_Int;
private String Colonia;
private String Del_o_Mun;
private String Entidad;
private Integer Codi_Postal;
private String Logo;
private boolean visible;

public String getLogo() {
	return Logo;
}
public void setLogo(String logo) {
	Logo = logo;
}
public String getIMSS() {
	return IMSS;
}
public void setIMSS(String iMSS) {
	IMSS = iMSS;
}
public Long getId_Empresa() {
	return id_Empresa;
}
public void setId_Empresa(Long id_Empresa) {
	this.id_Empresa = id_Empresa;
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
public String getSiglas() {
	return Siglas;
}
public void setSiglas(String siglas) {
	Siglas = siglas;
}
public String getCalle() {
	return Calle;
}
public void setCalle(String calle) {
	Calle = calle;
}
public String getNum_Ext() {
	return Num_Ext;
}
public void setNum_Ext(String num_Ext) {
	Num_Ext = num_Ext;
}
public String getNum_Int() {
	return Num_Int;
}
public void setNum_Int(String num_Int) {
	Num_Int = num_Int;
}
public String getColonia() {
	return Colonia;
}
public void setColonia(String colonia) {
	Colonia = colonia;
}
public String getDel_o_Mun() {
	return Del_o_Mun;
}
public void setDel_o_Mun(String del_o_Mun) {
	Del_o_Mun = del_o_Mun;
}
public String getEntidad() {
	return Entidad;
}
public void setEntidad(String entidad) {
	Entidad = entidad;
}
public Integer getCodi_Postal() {
	return Codi_Postal;
}
public void setCodi_Postal(Integer codi_Postal) {
	Codi_Postal = codi_Postal;
}
public Long getId_tipo_empresa() {
	return id_tipo_empresa;
}
public void setId_tipo_empresa(Long id_tipo_empresa) {
	this.id_tipo_empresa = id_tipo_empresa;
}
public boolean isVisible() {
	return visible;
}
public void setVisible(boolean visible) {
	this.visible = visible;
}


}
