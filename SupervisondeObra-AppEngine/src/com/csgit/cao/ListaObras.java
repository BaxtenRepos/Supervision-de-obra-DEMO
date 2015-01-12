package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.business.EmpresaBusiness;
import com.csgit.cao.business.ObraBusiness;
import com.csgit.cao.model.Concepto;
import com.csgit.cao.model.ConceptoEndpoint;
import com.csgit.cao.model.Empresa;
import com.csgit.cao.model.EmpresaEndpoint;
import com.csgit.cao.model.Obra;
import com.csgit.cao.model.ObraEndpoint;
import com.csgit.cao.model.Persona;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.cao.model.Proyecto;
import com.csgit.cao.model.ProyectoEndpoint;
import com.csgit.entity.ListaObraEntity;
import com.csgit.entity.auxEntity;
import com.csgit.entity.borrarEntity;
import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class ListaObras extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
//		String error = "";
		
		
		resp.setContentType("text/json");	
		System.out.println(req.getParameter("objectJson"));
		String parametro = req.getParameter("objectJson");
		
		if(parametro.contains("peticion")){   //validamos si es la peticion para el catalogo de proyectos 
			out.write(new Gson().toJson(obtenerObras()));
			return;
		}else{
			borrarEntity obrasborrar = (borrarEntity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new borrarEntity());
			ArrayList<auxEntity> idObrasBorrar = obrasborrar.getBorrar();
			CollectionResponse<Concepto> listaConcepto = new ConceptoEndpoint().listConcepto(null, null, 0L, 0L);
			Collection<Concepto>lista1 = listaConcepto.getItems();
			
			ObraEndpoint obraEndpoint = new ObraEndpoint();
			ArrayList<Long> borrados = new ArrayList<Long>();
			
			for (auxEntity idObras : idObrasBorrar) {
				if(!ObraBusiness.isObrasConceptos(idObras.getId(), lista1)){
					Obra obra = obraEndpoint.getObra(idObras.getId());
					if (obra.getBorrador() != null) {
						if(Integer.parseInt(obra.getBorrador()) == 0){
							Obra obraelement = new Obra();
							
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
							obraelement.setId_EmpresaContratista(obra.getId_EmpresaContratista());
							obraelement.setId_Obra(obra.getId_Obra());
							obraelement.setId_Poyecto(obra.getId_Poyecto());
							obraelement.setId_ubicacion(obra.getId_ubicacion());
							obraelement.setIdDependencia(obra.getIdDependencia());
							obraelement.setIdGobierno(obra.getIdGobierno());
							obraelement.setIdSecretaria(obra.getIdSecretaria());
							obraelement.setImporteContratoSinIVA(obra.getImporteContratoSinIVA());
							obraelement.setImporteFiscal1SinIVA(obra.getImporteFiscal1SinIVA());
							obraelement.setLimite_Desvio(obra.getLimite_Desvio());
							obraelement.setMontoFianzaAnticipo(obra.getMontoFianzaAnticipo());
							obraelement.setMontoFianzaCumplimiento(obra.getMontoFianzaCumplimiento());
							obraelement.setNombreRevision2(obra.getNombreRevision2());
							obraelement.setIdUsuario(obra.getIdUsuario());
							obraelement.setNoContrato(obra.getNoContrato());
							obraelement.setNoFianzaAnticipo(obra.getNoFianzaAnticipo());
							obraelement.setNoFianzaCumplimiento(obra.getNoFianzaCumplimiento());
							obraelement.setNombre(obra.getNombre());
							obraelement.setNombreEjercicioFiscal1(obra.getNombreEjercicioFiscal1());
							obraelement.setNombreQuienAutoriza(obra.getNombreQuienAutoriza());
							obraelement.setNombreRevision1(obra.getNombreRevision1());
							obraelement.setNombreVoBo(obra.getNombreVoBo());
							obraelement.setPartidaPresupuestal(obra.getPartidaPresupuestal());
							obraelement.setPeriodoEjucionDias(obra.getPeriodoEjucionDias());
							obraelement.setSubdireccion(obra.getSubdireccion());
							obraelement.setSuperintendente(obra.getSuperintendente());
							obraelement.setTipoContrato(obra.getTipoContrato());
							obraelement.setVisible(false);
							try {
								obraEndpoint.updateObra(obraelement);
								borrados.add(obraelement.getId_Obra());
							} catch (Exception e) {
								System.out.println("ha ocurrido un error con la obra"+obraelement.getNombre()+
										": " + e.getMessage());
								out.write(new Gson().toJson("ha ocurrido un error con la obra"+obraelement.getNombre()+
										": " + e.getMessage()));
							}
						}
					}
				}
			}
			out.write(new Gson().toJson(borrados));
			return;
		}
	}

	private Collection<ListaObraEntity> obtenerObras() {
		CollectionResponse<Obra> lista = new ObraEndpoint().listObra(null, null,new Long(0),new Long(0),"");//traemos la lista de obras insertadas;
		Collection<Obra>lista1 = lista.getItems();
		
		CollectionResponse<Proyecto> listaProyecto = new ProyectoEndpoint().listProyecto(null, null);
		Collection<Proyecto>listaProyecto1 = listaProyecto.getItems();
		
		CollectionResponse<Empresa> listaEmpresa =new EmpresaEndpoint().listEmpresa(null,null);//traemos la lista de empresas insertadas
		Collection<Empresa> listaEmpresa1 = listaEmpresa.getItems();
	    
		
		Collection<ListaObraEntity> regresar = new ArrayList<>();
		
	    for (Obra elem : lista1) {
	    	if(elem.isVisible()){
	    		ListaObraEntity aux = new ListaObraEntity();
		    	aux.setId_Obra(elem.getId_Obra());
		    	aux.setNombre(elem.getNombre());
		    	aux.setPoyecto(obtenerProyecto(elem.getId_Poyecto(),listaProyecto1));
		    	aux.setDependencia(obtenerDependencia(elem.getIdDependencia(), listaEmpresa1));
		    	aux.setDescripcion(elem.getDescripcion());
		    	aux.setFechaContrato(elem.getFechaContrato());
		    	aux.setFechaInicioContrato(elem.getFechaInicioContrato());
		    	aux.setFechaTerminoContrato(elem.getFechaTerminoContrato());
		    	aux.setImporteContratoSinIVA(elem.getImporteContratoSinIVA());
		    	regresar.add(aux);
	    	}
	    }
		return regresar;
	}

	private String obtenerProyecto(Long id_Poyecto,Collection<Proyecto> listaProyecto1) {
		String proyecto="";
		for (Proyecto elem : listaProyecto1) {
	    	if(elem.getId_Proyecto() == id_Poyecto){
	    		proyecto = elem.getNombre_corto();
	    	}
	    }
		return proyecto;
	}
	
	public String obtenerDependencia(Long idDependencia, Collection<Empresa> listaEmpresa1){
		String dependencia = "";
		for (Empresa elem : listaEmpresa1) {
            if(elem.getId_Empresa()== idDependencia && elem.isVisible()){
        		dependencia = elem.getNombre();
        	} 
	    }
		return dependencia;
	}
}
