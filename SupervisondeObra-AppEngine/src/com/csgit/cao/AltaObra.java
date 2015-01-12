package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.business.ObraBusiness;
import com.csgit.cao.model.Empresa;
import com.csgit.cao.model.EmpresaEndpoint;
import com.csgit.cao.model.Obra;
import com.csgit.cao.model.ObraEndpoint;
import com.csgit.cao.model.Proyecto;
import com.csgit.cao.model.ProyectoEndpoint;
import com.csgit.cao.model.Usuario;
import com.csgit.cao.model.UsuarioEndpoint;
import com.csgit.entity.DependenciasEntity;
import com.csgit.entity.ObraEntity;
import com.csgit.entity.UsuarioEntity;
import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class AltaObra extends HttpServlet{
	CollectionResponse lista;
	Collection<Empresa>lista1;
	Empresa empresa;
	EmpresaEndpoint empresaendpoint;
	Obra obraelement;
	ObraEndpoint obraendpoint;
	InsertaUbicacion insertaUbicacion;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		empresa = new Empresa();
		empresaendpoint = new EmpresaEndpoint();
		lista =empresaendpoint.listEmpresa(null,null);//traemos la lista de empresas insertadas
		lista1=lista.getItems();
		obraelement = new Obra();
		obraendpoint = new ObraEndpoint();
		insertaUbicacion = new InsertaUbicacion();
		
		PrintWriter out = resp.getWriter();
		ObraEntity obra;
		String error = "";
		resp.setContentType("text/json");
		String parametro = req.getParameter("objectJson");
		if(parametro.contains("empresas")){   //validamos si es la peticion para el catalogo de empresa 
			System.out.println("es una peticion de obras ");
			
			
			Collection<DependenciasEntity> dependencias = obtenerDependencias();
			dependencias = obtenerProyectos(dependencias);
			
			out.print(new Gson().toJson(dependencias));
			
			return;
		}else if((parametro.contains("getsupervisor"))){
			Collection<UsuarioEntity> directivos = obtenerSupervisores();
			
			out.print(new Gson().toJson(directivos));
			
			return;
			
			
		}else{
			System.out.println(req.getParameter("objectJson"));
			obra = (ObraEntity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new ObraEntity());
			if(ObraBusiness.ExistNoContrato(obra.getNoContrato(),getIdProyecto(obra.getProyecto())))
			{
				error +="el numero de contrato ya existe, por favor verifique la informacion";
				out.write(new Gson().toJson(error));
				return;
				
			}
			long idUbicacion = insertaUbicacion.insertaPuntos(obra.getDatosArrayUbicacion());
			if(idUbicacion==0){
				error += "Hubo un error al insertar la ubicacion la obra no se insertara";
				return;
			}else{
				if(!obra.getImporteAjusteCostos().equals("")){
					obraelement.setImporteAjusteCostos(Double.parseDouble(obra.getImporteAjusteCostos()));
				}if(!obra.getImporteConvenioAmpliacion().equals("")){
					obraelement.setImporteConvenioAmpliacion(Double.parseDouble(obra.getImporteConvenioAmpliacion()));
				}if(!obra.getImporteConvenioReduccion().equals("")){
					obraelement.setImporteConvenioReduccion(Double.parseDouble(obra.getImporteConvenioReduccion()));
				}if(!obra.getPorcentajeDesvio().equals("")){
					obraelement.setPorcentajeDesvio(Double.parseDouble(obra.getPorcentajeDesvio()));
				}
				
			Long id = GetIds.getIdObra()+1;	
			obraelement.setAnticipo(obra.getAnticipo());
			obraelement.setArea(obra.getArea());
			obraelement.setBorrador(obra.getBorrador());
			obraelement.setCargoRevision1(obra.getCargoRevision1());
			obraelement.setCargoRevision2(obra.getCargoRevision2());
			obraelement.setCargoVoBo(obra.getCargoVoBo());
			obraelement.setDescripcion(obra.getDescripcion());
			obraelement.setDireccion(obra.getDireccion());
			obraelement.setEntidadFederativa(obra.getEntidadFederativa());
			obraelement.setFechaContrato(obra.getFechaContrato());
			obraelement.setFechaFianzaAnticipo(obra.getFechaFianzaAnticipo());
			obraelement.setFechaFianzaCumplimiento(obra.getFechaFianzaCumplimiento());
			obraelement.setFechaInicioContrato(obra.getFechaInicioContrato());
			obraelement.setFechaTerminoContrato(obra.getFechaTerminoContrato());
			obraelement.setId_EmpresaContratista(getIdTipoEmpresa(obra.getContratista()));
			obraelement.setId_Obra(id);
			obraelement.setId_Poyecto(getIdProyecto(obra.getProyecto()));
			obraelement.setId_ubicacion(idUbicacion);
			obraelement.setIdDependencia(getIdTipoEmpresa(obra.getDependencia()));
			obraelement.setIdGobierno(getIdTipoEmpresa(obra.getGobierno()));
			obraelement.setIdSecretaria(getIdTipoEmpresa(obra.getSecretaria()));
			//obraelement.setImporteContratoSinIVA(Double.parseDouble(obra.getImporteContratoSinIva()));
			obraelement.setImporteFiscal1SinIVA(Double.parseDouble(obra.getImporteFiscal1SinIva()));
			obraelement.setLimite_Desvio(Integer.parseInt(obra.getLimiteDesvio()));
			obraelement.setMontoFianzaAnticipo(Double.parseDouble(obra.getMontoFianzaAnticipo()));
			obraelement.setMontoFianzaCumplimiento(Double.parseDouble(obra.getMontoFianzaCumplimiento()));
			obraelement.setNombreRevision2(obra.getNombreRevision2());
			obraelement.setIdUsuario(Long.parseLong(obra.getSupervisor()));
			obraelement.setNoContrato(obra.getNoContrato());
			obraelement.setNoFianzaAnticipo(obra.getNoFianzaAnticipo());
			obraelement.setNoFianzaCumplimiento(obra.getNoFianzaCumplimiento());
			obraelement.setNombre(obra.getNombre());
			obraelement.setNombreEjercicioFiscal1(obra.getNombreEjercicioFiscal1());
			obraelement.setNombreQuienAutoriza(obra.getNombreQuienAutoriza());
			obraelement.setNombreRevision1(obra.getNombreRevision1());
			obraelement.setNombreVoBo(obra.getNombreVoBo());
			obraelement.setPartidaPresupuestal(obra.getPartidaPresupuestal());
			obraelement.setPeriodoEjucionDias(Integer.parseInt(obra.getPerdiodoEjecucionDias()));
			obraelement.setSubdireccion(obra.getSubdireccion());
			obraelement.setSuperintendente(obra.getSuperintendente());
			obraelement.setTipoContrato(obra.getTipoContrato());
			obraelement.setVisible(true);
			
			try {
				obraendpoint.insertObra(obraelement);	
				//out.write(new Gson().toJson("La obra sera asignada como borrador hasta que tenga conceptos asiganados")); 
				out.write(new Gson().toJson(obraelement.getId_Obra()));
				//insertamos avance fisico y avance financiero
				insertAvanceFinanciero insert = new insertAvanceFinanciero();
				insert.insertAvance(id, Constants.Obra);
				insertaAvanceFisico insertAvanceFisico = new insertaAvanceFisico();
				insertAvanceFisico.insertAvance(id, Constants.Obra);
				
			} catch (Exception e) {
				// TODO: handle exception=
				System.out.println("error al insertar la obra "+ e.getMessage() );
			}
			
			
			
		}	
		}
		
		
		
	}
//private void inertaAvanceFinanciero(Long id_referencia) {
//		// TODO Auto-generated method stub
//    Date dNow = new Date( );
//    SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//
//    System.out.println("Current Date: " + ft.format(dNow));
//		Avance_Financiero avanceFinanciero = new Avance_Financiero();
//		Avance_FinancieroEndpoint avanceFinancieroEndpoint = new Avance_FinancieroEndpoint();
//		
//		avanceFinanciero.setEstado(Constants.Estado_Conforme_Programa);
//		avanceFinanciero.setFechaReporte(dNow.toString());
//		avanceFinanciero.setId_AvanceFinaciero(GetIds.getIdAvanceFinanciero()+1);
//		avanceFinanciero.setId_referencia((int)id_referencia.longValue());
//		avanceFinanciero.setPAvanceFinanciero(0);
//		avanceFinanciero.setPorcentaje_tendencia(new Float(0));
//		avanceFinanciero.setTipo_Entidad(Constants.Obra);
//		try {
//			avanceFinancieroEndpoint.insertAvance_Financiero(avanceFinanciero);
//			System.out.println("avance financiero insertado correctamente");
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("error al insertar el avance financiero");
//		}
//
//	}

//private Long getIdAvanceFinanciero() {
//	// TODO Auto-generated method stub
//	long id=0;
//	CollectionResponse lista;
//	Collection<Avance_Financiero>lista1;
//	
//			
//	lista = new Avance_FinancieroEndpoint().listAvance_Financiero(null, null, null);
//	lista1=lista.getItems();
//
//    for (Avance_Financiero elem : lista1) {
//        if(elem.getId_AvanceFinaciero() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
//        	id = elem.getId_AvanceFinaciero();
//    }
//
//	return id;	
//}

public Collection<DependenciasEntity> obtenerDependencias(){
	
	 Set regresar = new TreeSet();
	    for (Empresa elem : lista1) {
	            		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	if(elem.getId_tipo_empresa()==Constants.idDependencia && elem.isVisible())
	        	{
	        		DependenciasEntity dp = new DependenciasEntity();
	        		dp.setNombre(elem.getNombre());
	        		dp.setTipoEmpresa(Constants.dependenciaTexto);
	        		regresar.add(dp);
	        	}
	        	else if(elem.getId_tipo_empresa()==Constants.idSecretaria && elem.isVisible())
	        	{
	        		DependenciasEntity dp = new DependenciasEntity();
	        		dp.setNombre(elem.getNombre());
	        		dp.setTipoEmpresa(Constants.secretariaTexto);
	        		regresar.add(dp);
	        	}
	        	else if(elem.getId_tipo_empresa()==Constants.idGobierno && elem.isVisible())
	        	{
	        		DependenciasEntity dp = new DependenciasEntity();
	        		dp.setNombre(elem.getNombre());
	        		dp.setTipoEmpresa(Constants.GobiernoTexto);
	        		regresar.add(dp);
	        	}
	        	else if(elem.getId_tipo_empresa()==Constants.idContratista && elem.isVisible())
	        	{
	        		DependenciasEntity dp = new DependenciasEntity();
	        		dp.setNombre(elem.getNombre());
	        		dp.setTipoEmpresa(Constants.contratistaTexto);
	        		regresar.add(dp);
	        	}
	        
	    }
		
			return regresar;
	}
public Collection<DependenciasEntity> obtenerProyectos(Collection<DependenciasEntity > dep){
	ProyectoEndpoint proyectoendpoint = new ProyectoEndpoint();
	Proyecto proyecto = new Proyecto();
	CollectionResponse lista;
	Collection<Proyecto> lista1;
	ArrayList<String> regresar = new ArrayList<>();
	
	
	lista = proyectoendpoint.listProyecto(null, null);
	lista1 = lista.getItems();
	for (Proyecto proyectoitem : lista1) {
		DependenciasEntity dp = new DependenciasEntity();
		dp.setNombre(proyectoitem.getNombre_corto());
		dp.setTipoEmpresa("proyecto");
		dep.add(dp);
		
	}
	return dep;
}
//hacemos la relacion entre el nombre de la dependendia seleccionada con el id que esta insertado
private long getIdTipoEmpresa(String dato){
	long id=0;
    for (Empresa elem : lista1) {
        if(elem.getNombre().equalsIgnoreCase(dato)){     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
        	id = elem.getId_Empresa();
        	System.out.println("La secretaria :"+elem.getNombre() + "tiene el id: "+elem.getId_Empresa());
        }
    }
    return id;
}
private long getIdSecretaria(String dato){
	long id=0;
    for (Empresa elem : lista1) {
        if(elem.getNombre().equalsIgnoreCase(dato)){     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
        	id = elem.getId_Empresa();
        	System.out.println("La secretaria :"+elem.getNombre() + "tiene el id: "+elem.getId_Empresa());
        }
    }
    return id;
}
private long getIdGobierno(String dato){
	long id=0;
    for (Empresa elem : lista1) {
        if(elem.getNombre().equalsIgnoreCase(dato)){     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
        	id = elem.getId_Empresa();
        	System.out.println("El gobierno  :"+elem.getNombre() + "tiene el id: "+elem.getId_Empresa());
        }
    }
    return id;
}
private long getIdContratista(String dato){
	long id=0;
    for (Empresa elem : lista1) {
        if(elem.getNombre().equalsIgnoreCase(dato)){     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
        	id = elem.getId_Empresa();
        	System.out.println("El gobierno  :"+elem.getNombre() + "tiene el id: "+elem.getId_Empresa());
        }
    }
    return id;
}

//public Long  returnId(){
//	long id=0;
//	CollectionResponse lista;
//	Collection<Obra>lista1;
//	
//			
//	lista =obraendpoint.listObra(null,null);//traemos la lista de empresas insertadas
//	lista1=lista.getItems();
//
//    for (Obra elem : lista1) {
//        if(elem.getId_Obra() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
//        	id = elem.getId_Obra();
//    }
//
//	return id;	
//}
private long getIdProyecto(String dato){
	long id=0;
	Proyecto proyecto = new Proyecto();
	ProyectoEndpoint proyectoendpoint = new ProyectoEndpoint();
	CollectionResponse lista;
	Collection<Proyecto>lista1;
	lista = proyectoendpoint.listProyecto(null, null);
	lista1 = lista.getItems();
	
    for (Proyecto elem : lista1) {
        if(elem.getNombre_corto()!=null && elem.getNombre_corto().equalsIgnoreCase(dato)){     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
        	id = elem.getId_Proyecto();
        	System.out.println("El gobierno  :"+elem.getNombre_corto() + "tiene el id: "+elem.getId_Proyecto());
        }
    }
    return id;
}

private Collection<UsuarioEntity> obtenerSupervisores() {
	// TODO Auto-generated method stub
	CollectionResponse lista;
	Collection<Usuario> lista1;
	Collection<UsuarioEntity> regresar = new ArrayList<>();
	
	lista =  new UsuarioEndpoint().listUsuario(null, null);
	lista1 = lista.getItems();
	for (Usuario user : lista1) {
		
		if(user.getId_Tipo_Persona()==Constants.idSupervisor && user.isVisible()){
			UsuarioEntity ue = new UsuarioEntity();
			ue.setId(user.getId_Propietario());
			ue.setNombre(user.getUsuario());
			ue.setTipo(Constants.supervisorTexto);
			regresar.add(ue);
			
		}
	}
	
	return regresar;
}
}
