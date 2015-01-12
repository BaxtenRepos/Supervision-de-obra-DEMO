package com.csgit.entity;

public class DependenciasEntity implements Comparable<DependenciasEntity> {
	
	String nombre;
	String tipoEmpresa;
	Long id;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipoEmpresa() {
		return tipoEmpresa;
	}
	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public int compareTo(DependenciasEntity o) {
		// TODO Auto-generated method stub
		return this.nombre.toLowerCase().compareTo(o.getNombre().toLowerCase());
	}
	
	


   

}
