
package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.model.Concepto;
import com.csgit.cao.model.ConceptoEndpoint;
import com.csgit.cao.model.Obra;
import com.csgit.cao.model.ObraEndpoint;
import com.csgit.entity.Conceptoentity;
import com.csgit.entity.auxEntity;
import com.csgit.entity.borrarEntity;
import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class ListaConceptos extends HttpServlet {
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
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/json");
		String parametro = req.getParameter("objectJson");
		if (parametro.contains("peticion")) { // validamos si es la peticion
												// para el catalogo de proyectos
			out.write(new Gson().toJson(obtenerConceptos()));
			return;
		}else{
			borrarEntity conceptosborrar = (borrarEntity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new borrarEntity());
			ArrayList<auxEntity> idConceptosBorrar = conceptosborrar.getBorrar();
			CollectionResponse<Concepto> listaConcepto = new ConceptoEndpoint().listConcepto(null, null, 0L, 0L);
			Collection<Concepto>listaConcepto1 = listaConcepto.getItems();
			
			ConceptoEndpoint conceptoEndpoint = new ConceptoEndpoint();
			ArrayList<Long> borrados = new ArrayList<Long>();
			
			
			for (auxEntity idConceptos : idConceptosBorrar) {
				if(idConceptos.getIndex() == 0 ){
					Concepto aux_concepto = obtenerConcepto(idConceptos.getId());	
					try {
						conceptoEndpoint.updateConcepto(updateConcepto(aux_concepto));
						borrados.add(idConceptos.getId());
					} catch (Exception e) {
						out.write(new Gson().toJson("ha ocurrido un error con el concepto "+aux_concepto.getDescripcion()+
								": " + e.getMessage()));
					}
					for (Concepto elem : listaConcepto1) {
						if(aux_concepto.getId_Concepto().longValue() == elem.getPadre().longValue() 
								&& elem.getCantidadAvance() == 0){
							Concepto aux_concepto2 = obtenerConcepto(elem.getId_Concepto());	
							try {
								conceptoEndpoint.updateConcepto(updateConcepto(aux_concepto2));
//								auxEntity aux = new auxEntity();
//								aux.setId(elem.getId_Concepto());
//								aux.setIndex(elem.getPadre().intValue());
								borrados.add(aux_concepto2.getId_Concepto());
							} catch (Exception e) {
								out.write(new Gson().toJson("ha ocurrido un error con el concepto "+aux_concepto2.getDescripcion()+
										": " + e.getMessage()));
							}
						}
					}
				}else{
					Concepto aux_concepto = conceptoEndpoint.getConcepto(idConceptos.getId());
					if(aux_concepto.isVisible()){
						try {
							conceptoEndpoint.updateConcepto(updateConcepto(aux_concepto));
							borrados.add(idConceptos.getId());
						} catch (Exception e) {
							out.write(new Gson().toJson("ha ocurrido un error con el concepto "+aux_concepto.getDescripcion()+
									": " + e.getMessage()));
						}
					}
				}
			}
			out.write(new Gson().toJson(borrados));
			return;
		}
	}

	private Collection<Conceptoentity> obtenerConceptos() {
		CollectionResponse<Concepto> listaRes = new ConceptoEndpoint().listConcepto(null, null, 0L,0L);
		Collection<Concepto> lista = listaRes.getItems();
		Collection<Conceptoentity> regresar = new ArrayList<>();
		CollectionResponse<Obra> collResObra = new ObraEndpoint().listObra(null, null,new Long(0),new Long(0)," ");
		Collection<Obra> listaObra = collResObra.getItems();

		for (Concepto cnp : lista) {
			if(cnp.isVisible()){
				Conceptoentity conceptoEnt = new Conceptoentity();
				if(cnp.getId_Concepto()!=null)conceptoEnt.setId_Concepto(cnp.getId_Concepto());
				if(cnp.getId_Obra()!=null)conceptoEnt.setObra(ObtenerObra(cnp.getId_Obra(), listaObra));
				if(cnp.getClave()!=null)conceptoEnt.setClave(cnp.getClave());
				
				if(cnp.getDescripcion()!=null)conceptoEnt.setDescripcion(cnp.getDescripcion().getValue());
				
				if(cnp.getUnidadMedida()!=null)conceptoEnt.setMedida(cnp.getUnidadMedida());
				
				if(cnp.getCantidadTotal()!=null)conceptoEnt.setCantidadtotal(cnp.getCantidadTotal().toString());
				
				if(cnp.getPrecioUnitario()!=null)conceptoEnt.setPreciounitario(cnp.getPrecioUnitario().toString());
				
				if(cnp.getImporte()!=null)
					conceptoEnt.setImporte(cnp.getImporte().toString());

				if(cnp.getFecha_Inicio()!=null)conceptoEnt.setFechainicio(cnp.getFecha_Inicio());

				if(cnp.getFecha_Fin()!=null)conceptoEnt.setFechafin(cnp.getFecha_Fin());

				if(cnp.getPadre()!=null)conceptoEnt.setPadre(String.valueOf(cnp.getPadre()));

				regresar.add(conceptoEnt);
			}
		}
		return regresar;
	}

	private String ObtenerObra(Long id_Obra, Collection<Obra> listaObra) {
		String respuesta = "";
		for (Obra obra : listaObra) {
			if (obra.getId_Obra() == id_Obra) {
				return respuesta = obra.getNombre();
			}
		}
		return respuesta;
	}
	
	private Concepto obtenerConcepto(Long idConcepto) {
		// TODO Auto-generated method stub
		CollectionResponse<Concepto> lista = new ConceptoEndpoint().listConcepto(null, null, 0L,0L);
		Collection<Concepto>lista1 = lista.getItems();
		Concepto respuesta = new Concepto();
	    for (Concepto elem : lista1) {
	    	if(elem.getId_Concepto() == idConcepto){
	    		respuesta = elem;
	    		break;
	    	}
	    }
		return respuesta;
	}
	
	private Concepto updateConcepto(Concepto aux_concepto){
		Concepto concepto = new Concepto();
		
		concepto.setId_Concepto(aux_concepto.getId_Concepto());
		concepto.setId_Obra(aux_concepto.getId_Obra());
		concepto.setPadre(aux_concepto.getPadre());
		concepto.setClave(aux_concepto.getClave());
		concepto.setDescripcion(aux_concepto.getDescripcion());
		concepto.setUnidadMedida(aux_concepto.getUnidadMedida());
		concepto.setCantidadTotal(aux_concepto.getCantidadTotal());
		concepto.setCantidadAvance(aux_concepto.getCantidadAvance());
		concepto.setPrecioUnitario(aux_concepto.getPrecioUnitario());
		concepto.setImporte(aux_concepto.getImporte());
		concepto.setFecha_Inicio(aux_concepto.getFecha_Inicio());
		concepto.setFecha_Fin(aux_concepto.getFecha_Fin());
		concepto.setVisible(false);
		updateObra(concepto.getId_Obra(),concepto.getImporte());
		
		
		return concepto;
	}

	
	public void  updateObra (Long idObra,Double importe){
		Obra obra = new Obra();
		ObraEndpoint obraendpoint = new ObraEndpoint();
		Obra updateObra = new Obra();
		updateObra.setBorrador("1");
		
		obra = obraendpoint.getObra(idObra);
		updateObra.setId_Obra(obra.getId_Obra());
		updateObra.setAnticipo(obra.getAnticipo());
		updateObra.setArea(obra.getArea());
		updateObra.setCargoRevision1(obra.getCargoRevision1());
		updateObra.setCargoRevision2(obra.getCargoRevision2());
		updateObra.setCargoVoBo(obra.getCargoVoBo());
		updateObra.setDescripcion(obra.getDescripcion());
		updateObra.setDireccion(obra.getDireccion());
		updateObra.setEntidadFederativa(obra.getEntidadFederativa());
		updateObra.setFechaContrato(obra.getFechaContrato());
		updateObra.setFechaFianzaAnticipo(obra.getFechaFianzaAnticipo());
		updateObra.setFechaFianzaCumplimiento(obra.getFechaFianzaCumplimiento());
		updateObra.setFechaInicioContrato(obra.getFechaInicioContrato());
		updateObra.setFechaTerminoContrato(obra.getFechaTerminoContrato());
		updateObra.setId_EmpresaContratista(obra.getId_EmpresaContratista());
		updateObra.setId_Poyecto(obra.getId_Poyecto());
		updateObra.setId_ubicacion(obra.getId_ubicacion());
		updateObra.setIdDependencia(obra.getIdDependencia());
		updateObra.setIdGobierno(obra.getIdGobierno());
		updateObra.setIdSecretaria(obra.getIdSecretaria());
		updateObra.setIdUsuario(obra.getIdUsuario());
		updateObra.setImporteAjusteCostos(obra.getImporteAjusteCostos());
		updateObra.setImporteContratoSinIVA(obra.getImporteContratoSinIVA()-importe);
		updateObra.setImporteConvenioAmpliacion(obra.getImporteConvenioAmpliacion());
		updateObra.setImporteConvenioReduccion(obra.getImporteConvenioReduccion());
		updateObra.setImporteFiscal1SinIVA(obra.getImporteFiscal1SinIVA());
		updateObra.setLimite_Desvio(obra.getLimite_Desvio());
		updateObra.setMontoFianzaAnticipo(obra.getMontoFianzaAnticipo());
		updateObra.setMontoFianzaCumplimiento(obra.getMontoFianzaCumplimiento());
		updateObra.setNoContrato(obra.getNoContrato());
		updateObra.setNoFianzaAnticipo(obra.getNoFianzaAnticipo());
		updateObra.setNoFianzaCumplimiento(obra.getNoFianzaCumplimiento());
		updateObra.setNombre(obra.getNombre());
		updateObra.setNombreEjercicioFiscal1(obra.getNombreEjercicioFiscal1());
		updateObra.setNombreQuienAutoriza(obra.getNombreQuienAutoriza());
		updateObra.setNombreRevision1(obra.getNombreRevision1());
		updateObra.setNombreRevision2(obra.getNombreRevision2());
		updateObra.setNombreVoBo(obra.getNombreVoBo());
		updateObra.setPartidaPresupuestal(obra.getPartidaPresupuestal());
		updateObra.setPeriodoEjucionDias(obra.getPeriodoEjucionDias());
		updateObra.setRFC(obra.getRFC());
		updateObra.setSubdireccion(obra.getSubdireccion());
		updateObra.setSuperintendente(obra.getSuperintendente());
		updateObra.setTipoContrato(obra.getTipoContrato());
		updateObra.setPorcentajeDesvio(obra.getPorcentajeDesvio());
		updateObra.setVisible(true);
		
		obra.setBorrador("1");
		try {
			new ObraEndpoint().updateObra(updateObra);
			System.out.println("se le resto a la obra el valor del concepto");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
