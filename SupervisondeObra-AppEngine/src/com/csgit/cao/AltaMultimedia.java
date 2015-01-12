package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.business.Proyecto;
import com.csgit.cao.model.Multimedia;
import com.csgit.cao.model.MultimediaEndpoint;
import com.csgit.cao.model.Obra;
import com.csgit.cao.model.ObraEndpoint;
import com.csgit.cao.model.ProyectoEndpoint;
import com.csgit.entity.ObraEntity;
import com.csgit.util.NombreTipoUtility;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class AltaMultimedia extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	
//		CollectionResponse<Multimedia> mul = new MultimediaEndpoint().listMultimedia(null, null, 0L, 0L, 0L);
//		for (Multimedia elem : mul.getItems()) {
//			new MultimediaEndpoint().removeMultimedia(elem.getIdMultimedia());
//			
//		}
		
		
		
		PrintWriter out = resp.getWriter();
		ObraEntity obra;
		String error = "";
		resp.setContentType("text/json");
		String parametro = req.getParameter("objectJson");
		if(parametro.contains("obras")){
			out.write(new Gson().toJson(obtenerObras())); 
			
			
			
		}else if(parametro.contains("proyectos")){
			out.write(new Gson().toJson(obtenerProyectos())); 
		}
		
		
	}
public Collection<NombreTipoUtility> obtenerProyectos(){
		
		CollectionResponse<com.csgit.cao.model.Proyecto> lista;
		Collection<com.csgit.cao.model.Proyecto>lista1;
		Collection<NombreTipoUtility>regresar = new ArrayList<>();
		lista =new ProyectoEndpoint().listProyecto(null, null);//traemos la lista de empresas insertadas
		lista1=lista.getItems();
	    for ( com.csgit.cao.model.Proyecto elem : lista1) {
	     NombreTipoUtility nu = new NombreTipoUtility();
	     nu.setNombre(elem.getNombre_corto());
	     nu.setTipo(elem.getId_Proyecto().toString());
	     regresar.add(nu);
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
	
}
