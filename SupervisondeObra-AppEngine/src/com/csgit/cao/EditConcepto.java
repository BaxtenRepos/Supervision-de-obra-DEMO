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
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Text;
import com.google.gson.Gson;

public class EditConcepto extends HttpServlet {
	
	
	public static final Logger _log = Logger.getLogger(XMPPAgentServlet.class.getName());

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
		String error = "";
		
		resp.setContentType("text/json");	
		System.out.println(req.getParameter("objectJson"));
		String parametro = req.getParameter("objectJson");
		//si el objeto es igual al string peticion entonces regresamos el catalogo de conceptos
		if(parametro.contains("peticion")){   //validamos si es la peticion para el catalogo de conceptos 
			System.out.println("es una peticion");
			Collection<Concepto> aux_concepto = obtenerConcepto();
			out.write(new Gson().toJson(aux_concepto));
			return;
		}else if(parametro.contains("concepto:")){
			String[] split= parametro.split(":");
			Long idConcepto = Long.parseLong(split[1].replace('"', ' ').trim());
			System.out.println("voy a devolver los conceptos con idConcepto = " + idConcepto);
			Concepto aux_concepto = obtenerConcepto(idConcepto);
			out.write(new Gson().toJson(aux_concepto));
		}else if(parametro.contains("opcionobra:")){
			String[] split= parametro.split(":");
			Long idObra = Long.parseLong(split[1].replace('"', ' ').trim());
			System.out.println("voy a devolver la obra con idObra = " + idObra);
			out.write(new Gson().toJson(obtenerObras(idObra)));
		}else if(parametro.contains("opcionpadre")){
			Collection<Concepto> aux_concepto = obtenerPadre();
			out.write(new Gson().toJson(aux_concepto));
		}else{
			ConceptoEndpoint conceptoEndpoint = new ConceptoEndpoint();
			Conceptoentity concepto= (Conceptoentity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new Conceptoentity());
			System.out.println("Entro para la edicion");
			_log.info("entro para la edicion");
			if(!existConceptInObra(concepto.getClave(),Long.valueOf(concepto.getObra()), concepto.getId_Concepto())){
				System.out.println("Entro para al if de edicion");
				_log.info("Entro para el if de la edicion");
				Concepto aux_concepto = new Concepto();
				aux_concepto.setId_Concepto(concepto.getId_Concepto());
				aux_concepto.setId_Obra(Long.valueOf(concepto.getObra()));
				aux_concepto.setPadre(Long.valueOf(concepto.getPadre()));
				aux_concepto.setClave(concepto.getClave());
				aux_concepto.setDescripcion(new Text(concepto.getDescripcion()));
				aux_concepto.setUnidadMedida(concepto.getMedida());
				aux_concepto.setCantidadTotal(Double.valueOf(concepto.getCantidadtotal()));
				aux_concepto.setPrecioUnitario(Double.valueOf(concepto.getPreciounitario()));
				aux_concepto.setImporte(Double.valueOf(concepto.getImporte()));
				aux_concepto.setCantidadAvance(Double.valueOf(concepto.getCantidadavance()));
				aux_concepto.setFecha_Inicio(concepto.getFechainicio());
				aux_concepto.setFecha_Fin(concepto.getFechafin());
				aux_concepto.setVisible(true);
				try {
					conceptoEndpoint.updateConcepto(aux_concepto);
					System.out.println("concepto editado correctamente");
					_log.info("Concepto editado correctamente");
					out.write(new Gson().toJson("Concepto editado correctamente"));
				} catch (Exception e) {
					System.out.println("error en la edicion"+ e);
					_log.info("error en la edicion "+e);
					out.write(new Gson().toJson("error en la edicion"+ e));
					// TODO: handle exception
				}
			}else{
				System.out.println("Clave de concepto existente en la obra");
				_log.info("Clave del concepto existente en la obra");
				out.write(new Gson().toJson("Clave de concepto existente en la obra"));
				return;					
			}
		}
	}

	private Concepto obtenerConcepto(Long idConcepto) {
		// TODO Auto-generated method stub
		CollectionResponse lista = new ConceptoEndpoint().listConcepto(null, null, 0L,0L);
		Collection<Concepto>lista1 = lista.getItems();
		Concepto respuesta = new Concepto();
	    for (Concepto elem : lista1) {
	    	if(elem.getId_Concepto().longValue() == idConcepto){
	    		respuesta = elem;
	    		break;
	    	}
	    }
		return respuesta;
	}

	private Collection<Concepto> obtenerConcepto() {
		// TODO Auto-generated method stub
		CollectionResponse<Concepto> lista = new ConceptoEndpoint().listConcepto(null, null, 0L,0L);
		Collection<Concepto>lista1 = lista.getItems();
		Collection<Concepto>respuesta = new ArrayList<>();
	    for (Concepto elem : lista1) {
	    	Concepto aux_conceptos = new Concepto();
	    	aux_conceptos.setId_Concepto(elem.getId_Concepto());
	    	aux_conceptos.setClave(elem.getClave());
	    	aux_conceptos.setDescripcion(elem.getDescripcion());
			respuesta.add(aux_conceptos);
	    }
		return respuesta;

	}
	
	private Collection<Concepto> obtenerPadre() {
		// TODO Auto-generated method stub
		CollectionResponse<Concepto> lista = new ConceptoEndpoint().listConcepto(null, null, 0L,0L);
		Collection<Concepto>lista1 = lista.getItems();
		Collection<Concepto>respuesta = new ArrayList<>();
	    for (Concepto elem : lista1) {
	    	if(elem.getPadre() == 0 && elem.isVisible()){
	    		Concepto aux_conceptos = new Concepto();
		    	aux_conceptos.setId_Concepto(elem.getId_Concepto());
		    	aux_conceptos.setClave(elem.getClave());
		    	aux_conceptos.setDescripcion(elem.getDescripcion());
				respuesta.add(aux_conceptos);
	    	}
	    }
		return respuesta;

	}
	
	private boolean existConceptInObra(String clave, Long id_Obra, Long idConcepto) {
		// TODO Auto-generated method stub
		boolean regresar=false;
		CollectionResponse<Concepto> lista;
		Collection<Concepto>lista1;		
		lista =new ConceptoEndpoint().listConcepto(null, null, 0L,0L);//traemos la lista de conceptos insertadas
		lista1=lista.getItems();
	    for (Concepto elem : lista1) {
	    	if(elem.getClave()!=null){
	    		if((elem.getId_Obra().longValue()==id_Obra)&&(elem.getClave().equals(clave)) &&(elem.getId_Concepto().longValue() != idConcepto)) {
		    		regresar=true;
		    		break;
		    	}
	    	}	
	    }
		return regresar;
	}
	public Collection<NombreTipoUtility> obtenerObras(){
		
		CollectionResponse<Obra> lista;
		Collection<Obra>lista1;
		Collection<NombreTipoUtility>regresar = new ArrayList<>();
		lista =new ObraEndpoint().listObra(null, null,new Long(0),new Long(0),"");//traemos la lista de obras insertadas
		lista1=lista.getItems();
	    for ( Obra elem : lista1) {
	    	if(elem.isVisible()){
	    		 NombreTipoUtility nu = new NombreTipoUtility();
		   	     nu.setNombre(elem.getNombre());
		   	     nu.setTipo(elem.getNoContrato().toString());
		   	     nu.setId(elem.getId_Obra());
		   	     regresar.add(nu);
	    	}
	    }
		
			return regresar;
	}
	public NombreTipoUtility obtenerObras(Long idObra){
		
		CollectionResponse<Obra> lista;
		Collection<Obra>lista1;
		NombreTipoUtility regresar = new NombreTipoUtility();
		lista =new ObraEndpoint().listObra(null, null,new Long(0),new Long(0),"");//traemos la lista de obras insertadas
		lista1=lista.getItems();
	    for ( Obra elem : lista1) {
	    	if(elem.getId_Obra() == idObra && elem.isVisible()){
	    		 regresar.setNombre(elem.getNombre());
    		     regresar.setTipo(elem.getNoContrato().toString());
    		     regresar.setId(elem.getId_Obra());
	    	}
	    }
		
		return regresar;
	}
}
