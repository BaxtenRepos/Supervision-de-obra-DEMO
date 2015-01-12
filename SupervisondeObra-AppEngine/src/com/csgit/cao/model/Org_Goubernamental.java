package com.csgit.cao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

	
	@Entity
	public class Org_Goubernamental {
		  @Id
		    private Long id_Organizacion;
			private String Sitio_Web;
			private String Nombre_Oficial;
			private String Nombre_corto;
			private String Logotipo;
			
		
			public Long getId_Organizacion() {
				return id_Organizacion;
			}
			public void setId_Organizacion(Long id_Organizacion) {
				this.id_Organizacion = id_Organizacion;
			}
			public String getSitio_Web() {
				return Sitio_Web;
			}
			public void setSitio_Web(String sitio_Web) {
				Sitio_Web = sitio_Web;
			}
			public String getNombre_Oficial() {
				return Nombre_Oficial;
			}
			public void setNombre_Oficial(String nombre_Oficial) {
				Nombre_Oficial = nombre_Oficial;
			}
			public String getNombre_corto() {
				return Nombre_corto;
			}
			public void setNombre_corto(String nombre_corto) {
				Nombre_corto = nombre_corto;
			}
			public String getLogotipo() {
				return Logotipo;
			}
			public void setLogotipo(String logotipo) {
				Logotipo = logotipo;
			}
			
	

}
