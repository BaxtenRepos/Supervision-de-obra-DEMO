package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.model.Concepto;
import com.csgit.cao.model.ConceptoEndpoint;
import com.csgit.cao.model.Obra;
import com.csgit.cao.model.ObraEndpoint;
import com.csgit.entity.Conceptoentity;
import com.csgit.util.JsonParserUtil;
import com.csgit.util.NombreTipoUtility;
import com.csgit.util.UtilFechas;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Text;
import com.google.gson.Gson;

public class Altaconcepto extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		String error = "";
		resp.setContentType("text/json");
		String parametro = req.getParameter("objectJson");
		if(parametro.contains("obras")){   //validamos si es la peticion para el catalogo de empresa 
		out.write(new Gson().toJson(obtenerObras())); 
		}else if(parametro.contains("conceptos:")){
			
			String[] split= parametro.split("-");
			Long idObra = getIdObrabyNoCotrato(split[split.length-1].replace((char)34, (char)0));
			
		out.write(new Gson().toJson(obtenerConceptos(idObra)));
		}
		else if(parametro.contains("cerrarobra-")){
			String obraId[] = parametro.split("-") ;
			//for(int y=0;y<obraId.length;y++)
			//	System.out.println(obraId[y]);
			Obra obra = new Obra();
			ObraEndpoint obraendpoint = new ObraEndpoint();
			Obra updateObra = new Obra();
			updateObra.setBorrador("1");
			
			obra = obraendpoint.getObra(new Long(getIdObrabyNoCotrato(obraId[2])));
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
			updateObra.setImporteContratoSinIVA(obra.getImporteContratoSinIVA());
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
			
			obra.setBorrador("1");
			//obra.setId_Obra(new Long(obraId[2]));
			try {
				obraendpoint.updateObra(updateObra);	
				out.write(new Gson().toJson("la obra ahora esta activa"));
			} catch (Exception e) {
				// TODO: handle exception
				out.write(new Gson().toJson("error al activar la obra"));
			}
			
			
		}
		else{
			
			Conceptoentity concepto = (Conceptoentity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new Conceptoentity());
			ConceptoEndpoint conceptoendpoint = new ConceptoEndpoint();
			Concepto concept = new Concepto();
			concept.setCantidadAvance(0D);
			//concept.setCantidadTotal(Integer.parseInt(concepto.getCantidadtotal()));
			concept.setCantidadTotal(Double.parseDouble(concepto.getCantidadtotal()));
			concept.setClave(concepto.getClave());
			concept.setDescripcion(new Text(concepto.getDescripcion()));
			concept.setFecha_Fin(concepto.getFechafin());
			concept.setFecha_Inicio(concepto.getFechainicio());
			concept.setId_Concepto(GetIds.getIdConcepto()+1);
			
			String []obra = concepto.getObra().split("-");
			concept.setId_Obra(getIdObrabyNoCotrato(obra[1]));
			concept.setImporte(Double.valueOf(concepto.getImporte()));
			String[]conc = concepto.getPadre().split("-");
			if(concepto.getPadre().equals("0")){
				concept.setPadre(new Long(0));
			}else{
			concept.setPadre(getIdObrabyClave(conc[1]));
			}
//			if(Long.getLong(concepto.getPadre()) != null)
//			concept.setPadre(Long.getLong(concepto.getPadre()));
//			else concept.setPadre(new Long(0));
			concept.setPrecioUnitario(Double.valueOf(concepto.getPreciounitario()));
			concept.setUnidadMedida(concepto.getMedida());
			System.out.println();
			if(!existConceptInObra(concept.getClave(),concept.getId_Obra()))
			try {
				
				conceptoendpoint.insertConcepto(concept);
				System.out.println("concepto insertado correctamente");
				out.write(new Gson().toJson("Concepto insertado correctamente"));
			} catch (Exception e) {
				System.out.println("error en la inserción"+ e);
				// TODO: handle exception
			}else
			{
				out.write(new Gson().toJson("Clave de concepto existente en la obra"));
				return;
				
			}
			
		}
		
	}
	private boolean existConceptInObra(String clave, Long id_Obra) {
		// TODO Auto-generated method stub
		boolean regresar=false;
		CollectionResponse lista;
		Collection<Concepto>lista1;		
		lista =new ConceptoEndpoint().listConcepto(null, null, new Long(0),0L);//traemos la lista de empresas insertadas
		lista1=lista.getItems();
	    for (Concepto elem : lista1) {
//	    	System.out.println(elem.getId_Poyecto().toString());
//	    	System.out.println(idProyecto+"es id proyecto");
//	    	System.out.println(noContrato+"es numero contrato");
//	    	System.out.println(elem.getId_Poyecto().longValue()==idProyecto);
//	    	System.out.println(elem.getNoContrato().longValue()==noContrato);
	    	
	        if((elem.getId_Obra().longValue()==id_Obra)&&(elem.getClave().equals(clave)))     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	regresar=true;
	    }
		return regresar;
	}

	public Collection<NombreTipoUtility> obtenerObras(){
		
		CollectionResponse<Obra> lista;
		Collection<Obra>lista1;
		Collection<NombreTipoUtility>regresar = new ArrayList<>();
		lista =new ObraEndpoint().listObra(null, null,new Long(0),new Long(0),"");//traemos la lista de empresas insertadas
		lista1=lista.getItems();
	    for ( Obra elem : lista1) {
	     NombreTipoUtility nu = new NombreTipoUtility();
	     nu.setNombre(elem.getNombre());
	     nu.setTipo(elem.getId_Obra().toString());
	     regresar.add(nu);
	    }
		
			return regresar;
	}
	public Collection<NombreTipoUtility> obtenerConceptos(Long obra){
		
		CollectionResponse<Concepto> lista;
		Collection<Concepto>lista1;
		Collection<NombreTipoUtility>regresar = new ArrayList<>();
		lista =new ConceptoEndpoint().listConcepto(null, null, new Long(0),0L);//traemos la lista de empresas insertadas
		lista1=lista.getItems();
	    for ( Concepto elem : lista1) {
	    		if(elem.getId_Obra()==obra)
	    		{
	    			NombreTipoUtility nu = new NombreTipoUtility();
	    			nu.setNombre(elem.getDescripcion().getValue());
	    			nu.setTipo(elem.getClave());
	    			regresar.add(nu);
	    		}
	    	
	    }
		
			return regresar;
	}
	public Long getIdObrabyNoCotrato(String noContrato){
		
		
		CollectionResponse<Obra> lista;
		Collection<Obra>lista1;
		Long regresar = null;
		lista =new ObraEndpoint().listObra(null, null,new Long(0),new Long(0),"");//traemos la lista de empresas insertadas
		lista1=lista.getItems();

		for (Obra obra2 : lista1) {
			if(obra2.getNoContrato()==noContrato)
				//System.out.println("el no de contrato");
				regresar = obra2.getId_Obra();
			
		}
		System.out.println("el id del noContrato"+noContrato+" es:"+regresar);
	return regresar;
		
		
		
	}

	public Long getIdObrabyClave(String clave){
		
		
		CollectionResponse<Concepto> lista;
		Collection<Concepto>lista1;
		Long regresar = null;
		lista =new ConceptoEndpoint().listConcepto(null,null, new Long(0),0L);//traemos la lista de empresas insertadas
		lista1=lista.getItems();

		for (Concepto obra2 : lista1) {
			//Long a=Long.valueOf(clave.trim());
			if(obra2.getClave().equalsIgnoreCase(clave));
				//System.out.println("el no de contrato");
				regresar = obra2.getId_Concepto();
			
		}

	return regresar;
		
		
		
	}
}
