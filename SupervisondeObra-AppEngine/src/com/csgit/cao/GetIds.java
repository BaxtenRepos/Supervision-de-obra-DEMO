package com.csgit.cao;

import java.util.Collection;

import com.csgit.cao.model.Avance_Financiero;
import com.csgit.cao.model.Avance_FinancieroEndpoint;
import com.csgit.cao.model.Avance_Fisico;
import com.csgit.cao.model.Avance_FisicoEndpoint;
import com.csgit.cao.model.Cat_Personal;
import com.csgit.cao.model.Cat_PersonalEndpoint;
import com.csgit.cao.model.Cat_Tipo_Maquinaria;
import com.csgit.cao.model.Cat_Tipo_MaquinariaEndpoint;
import com.csgit.cao.model.Concepto;
import com.csgit.cao.model.ConceptoEndpoint;
import com.csgit.cao.model.Directivo_Proyecto;
import com.csgit.cao.model.Directivo_ProyectoEndpoint;
import com.csgit.cao.model.Directorio;
import com.csgit.cao.model.DirectorioEndpoint;
import com.csgit.cao.model.Empresa;
import com.csgit.cao.model.EmpresaEndpoint;
import com.csgit.cao.model.Estimacion;
import com.csgit.cao.model.EstimacionEndpoint;
import com.csgit.cao.model.Maquinaria;
import com.csgit.cao.model.MaquinariaEndpoint;
import com.csgit.cao.model.Multimedia;
import com.csgit.cao.model.MultimediaEndpoint;
import com.csgit.cao.model.Notas;
import com.csgit.cao.model.NotasEndpoint;
import com.csgit.cao.model.Obra;
import com.csgit.cao.model.ObraEndpoint;
import com.csgit.cao.model.Persona;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.cao.model.Programa;
import com.csgit.cao.model.ProgramaEndpoint;
import com.csgit.cao.model.Proyecto;
import com.csgit.cao.model.ProyectoEndpoint;
import com.csgit.cao.model.RepMaquinariaCatMaquinaria;
import com.csgit.cao.model.RepMaquinariaCatMaquinariaEndpoint;
import com.csgit.cao.model.RepoPersonalCatPersonal;
import com.csgit.cao.model.RepoPersonalCatPersonalEndpoint;
import com.csgit.cao.model.ReporteDiario;
import com.csgit.cao.model.ReporteDiarioEndpoint;
import com.csgit.cao.model.Reportsmodel;
import com.csgit.cao.model.ReportsmodelEndpoint;
import com.csgit.cao.model.Ubicaciones;
import com.csgit.cao.model.UbicacionesEndpoint;
import com.csgit.cao.model.Usuario;
import com.csgit.cao.model.UsuarioEndpoint;
import com.csgit.cao.model.Xmpp;
import com.csgit.cao.model.XmppEndpoint;
import com.google.api.server.spi.response.CollectionResponse;

public class GetIds {
	
	public Long getIdPrograma() {
		// TODO Auto-generated method stub
		long id=0;
		CollectionResponse lista;
		Collection<Programa>lista1;
		
				

		lista = new ProgramaEndpoint().listPrograma(null, null);

		lista1=lista.getItems();

	    for (Programa elem : lista1) {
	        if(elem.getId_programa() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId_programa();
	    }
		return id;	
	}
	public static Long getIdAvanceFinanciero() {
		// TODO Auto-generated method stub
		long id=0;
		CollectionResponse lista;
		Collection<Avance_Financiero>lista1;
		
				

		lista = new Avance_FinancieroEndpoint().listAvance_Financiero(null, null, new Long(0), 0);

		lista1=lista.getItems();

	    for (Avance_Financiero elem : lista1) {
	        if(elem.getId_AvanceFinaciero() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId_AvanceFinaciero();
	    }
		return id;	
	}
	//obtencion del id que continua en las obras
	public static Long getIdObra(){
		
		long id=0;
		CollectionResponse lista;
		Collection<Obra>lista1;		
		lista =new ObraEndpoint().listObra(null,null,new Long(0),new Long(0),"");//traemos la lista de empresas insertadas
		lista1=lista.getItems();
	    for (Obra elem : lista1) {
	        if(elem.getId_Obra() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId_Obra();
	    }
		return id;
	}
	
	
	public static Long getIdEmpresa(){
		long id=0;
		CollectionResponse lista;
		Collection<Empresa>lista1;
		lista =new EmpresaEndpoint().listEmpresa(null,null);//traemos la lista de empresas insertadas
		lista1=lista.getItems();
	
	    for (Empresa elem : lista1) {
	        if(elem.getId_Empresa() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId_Empresa();
	    }
		return id;	
	}
	
	public static Long getIdProyecto(){
		long id=0;
		CollectionResponse lista;
		Collection<Proyecto>lista1;		
		lista =new ProyectoEndpoint().listProyecto(null,null);//traemos la lista de empresas insertadas
		lista1=lista.getItems();
	    for (Proyecto elem : lista1) {
	        if(elem.getId_Proyecto() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId_Proyecto();
	    }
		return id;
		
	}
	
	public static Long getIdAvanceFisico(){
		long id=0;
		CollectionResponse lista;
		Collection<Avance_Fisico>lista1;
		Avance_Fisico af = new Avance_Fisico();		
	//	lista = new Avance_Fisico().listAvance_Financiero(null, null, null);
		lista = new Avance_FisicoEndpoint().listAvance_Fisico(null, null, new Long(0), 0);
		lista1=lista.getItems();

	    for (Avance_Fisico elem : lista1) {
	        if(elem.getId_AvanceFisico() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId_AvanceFisico();
	    }
		return id;
		
	}
	public static Long getIdUbicacion(){
		long id=0;
		CollectionResponse lista;
		Collection<Ubicaciones>lista1;
				
		lista =new UbicacionesEndpoint().listUbicaciones(null,null);//traemos la lista de empresas insertadas
		lista1=lista.getItems();
	
	    for (Ubicaciones elem : lista1) {
	        if(elem.getId_Ubicacion() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId_Ubicacion();
	    }

		return id;
		
		
	}
	/**
	 * 
	 * @return idPersona
	 */
	public static Long getIdPersona(){
		long id=0;
		CollectionResponse lista;
		Collection<Persona>lista1;
		lista =new PersonaEndpoint().listPersona(null,null);//traemos la lista de personas insertadas
		lista1=lista.getItems();
	
	    for (Persona elem : lista1) {
	        if(elem.getId_Persona() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId_Persona();
	    }
		return id;	
	}
	/**
	 * 
	 * @return idCatPersona
	 */
	public static Long getIdCatPersona(){
		long id=0;
		CollectionResponse lista;
		Collection<Cat_Personal>lista1;
		lista =new Cat_PersonalEndpoint().listCat_Personal(null, null);//traemos la lista del catalogo tipo personas insertadas
		lista1=lista.getItems();
	
	    for (Cat_Personal elem : lista1) {
	        if(elem.getId_Tipo_Personal() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId_Tipo_Personal();
	    }
		return id;	
	}
	
	public static Long getIdMaquinaria(){
		long id=0;
		CollectionResponse lista;
		Collection<Maquinaria>lista1;
		lista =new MaquinariaEndpoint().listMaquinaria(null, null);//traemos la lista de maquinarias insertadas
		lista1=lista.getItems();
	
	    for (Maquinaria elem : lista1) {
	        if(elem.getId_Maquinaria() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId_Maquinaria();
	    }
		return id;	
	}
	
	/**
	 * 
	 * @return idUsuario
	 */
	public static Long getIdUsuario(){
		long id=0;
		CollectionResponse lista;
		Collection<Usuario>lista1;
		lista =new UsuarioEndpoint().listUsuario(null, null);//traemos la lista de usuarios insertadas
		lista1=lista.getItems();
	
	    for (Usuario elem : lista1) {
	        if(elem.getId_Propietario() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId_Propietario();
	    }
		return id;	
	}
	

	public static Long getIdDirectivo_Proyecto(){
		long id=0;
		CollectionResponse lista;
		Collection<Directivo_Proyecto>lista1;
				
		lista =new Directivo_ProyectoEndpoint().listDirectivo_Proyecto(null, null, new Long(0));
		lista1=lista.getItems();
	
	    for (Directivo_Proyecto elem : lista1) {
	        if(elem.getId() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId();
	    }

		return id;
		
		
	}
	
	public static Long getIdConcepto(){
		long id=0;
		CollectionResponse lista;
		Collection<Concepto>lista1;
				
		lista =new ConceptoEndpoint().listConcepto(null, null, new Long(0),0L);//traemos la lista de empresas insertadas
		lista1=lista.getItems();
	
	    for (Concepto elem : lista1) {
	        if(elem.getId_Concepto() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId_Concepto();
	    }

		return id;
	}
	
	/**
	 * 
	 * @return idCatTipoMaquinaria
	 */
	public static Long getIdCatTipoMaquinaria(){
		long id=0;
		CollectionResponse lista;
		Collection<Cat_Tipo_Maquinaria>lista1;
		lista =new Cat_Tipo_MaquinariaEndpoint().listCat_Tipo_Maquinaria(null, null);//traemos la lista del catalogo tipo personas insertadas
		lista1=lista.getItems();
	
	    for (Cat_Tipo_Maquinaria elem : lista1) {
	        if(elem.getId_tipo_Maquinaria() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId_tipo_Maquinaria();
	    }
		return id;	
	}
	/**
	 * 
	 * @return idMutimedia
	 */
	 public static Long getIdMultimedia(){
			long id=0;
			CollectionResponse lista;
			Collection<Multimedia>lista1;	

			lista =new MultimediaEndpoint().listMultimedia(null, null, new Long(0), new Long(0), new Long(0));//traemos la lista de elementos insertadas

			lista1=lista.getItems();
		
		    for (Multimedia elem : lista1) {
		        if(elem.getIdMultimedia() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
		        	id = elem.getIdMultimedia();
		    }

			return id;	
		}
	 public static Long getIdDirectorio(){
			long id=0;
			CollectionResponse<Directorio> lista;
			
			lista =new DirectorioEndpoint().listDirectorio(null, null,new Long(0), new Long(0));//traemos la lista de elementos insertadas

		
		    for (Directorio elem : lista.getItems()) {
		        if(elem.getIdDirectorio() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
		        	id = elem.getIdDirectorio();
		    }

			return id;	
		}
	 public static Long getIdXmpp(){
			long id=0;
			CollectionResponse<Xmpp> lista;
			
			lista =new XmppEndpoint().listXmpp(null, null);

		
		    for (Xmpp elem : lista.getItems()) {
		        if(elem.getIdXmpp() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
		        	id = elem.getIdXmpp();
		    }

			return id;	
		}
	 public static Long getIdNotas(){
			long id=0;
			CollectionResponse<Notas> lista;
			
			lista =new NotasEndpoint().listNotas(null, null, " ", 0L);

		
		    for (Notas elem : lista.getItems()) {
		        if(elem.getIdRepoNotas() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
		        	id = elem.getIdRepoNotas();
		    }

			return id;	
		}
	 
	 public static Long getIdReporteDiario(){
			long id=0;
			CollectionResponse<ReporteDiario> lista;
			
			lista =new ReporteDiarioEndpoint().listReporteDiario(null, null, 0L, " ");

		
		    for (ReporteDiario elem : lista.getItems()) {
		        if(elem.getId_ReporteDiario() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
		        	id = elem.getId_ReporteDiario();
		    }

			return id;	
		}
	 public static Long getIdRepoPersonalCatPersonal(){
			long id=0;
			CollectionResponse<RepoPersonalCatPersonal> lista;
			
			lista =new RepoPersonalCatPersonalEndpoint().listRepoPersonalCatPersonal(null, null, " ", 0L);
			

		
		    for (RepoPersonalCatPersonal elem : lista.getItems()) {
		        if(elem.getIdCatPersonal() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
		        	id = elem.getIdCatPersonal();
		    }

			return id;	
		} 
	 public static Long getIdRepoMaqCatMaq(){
			long id=0;
			CollectionResponse<RepMaquinariaCatMaquinaria> lista;
			
			lista =new RepMaquinariaCatMaquinariaEndpoint().listRepMaquinariaCatMaquinaria(null, null, 0L, " ");
			

		
		    for (RepMaquinariaCatMaquinaria elem : lista.getItems()) {
		        if(elem.getIdRepMaquinaria() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
		        	id = elem.getIdRepMaquinaria();
		    }

			return id;	
		}
	 public static Long getIdEstimacion(){
			long id=0;
			CollectionResponse<Estimacion> lista;
			
			lista =new EstimacionEndpoint().listEstimacion(null, null, 0L, " ");
			

		
		    for (Estimacion elem : lista.getItems()) {
		        if(elem.getId_Estimacion() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
		        	id = elem.getId_Estimacion();
		    }

			return id;	
		}
	 public static Long getIdReports(){
			long id=0;
			CollectionResponse<Reportsmodel> lista;
			
			lista =new ReportsmodelEndpoint().listReportsmodel(null, null, 0L, 0L);
			

		
		    for (Reportsmodel elem : lista.getItems()) {
		        if(elem.getIdReporte() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
		        	id = elem.getIdReporte();
		    }

			return id;	
		}
}
