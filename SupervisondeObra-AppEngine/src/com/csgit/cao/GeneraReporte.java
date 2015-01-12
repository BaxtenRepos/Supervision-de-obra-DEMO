package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.model.Obra;
import com.csgit.cao.model.ObraEndpoint;
import com.csgit.cao.model.Proyecto;
import com.csgit.cao.model.ProyectoEndpoint;
import com.csgit.entity.GeneraReporteEntity;
import com.csgit.entity.LoginEntity;
import com.csgit.entity.ProyectoEntity;
import com.csgit.entity.ProyectoEntityHelp;
import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class GeneraReporte extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/json");
		LoginEntity proyecto;
		String error = "";
		String conversio;
	
		System.out.println("Tipo de Peticion: "+req.getParameter("objectJson"));
		//log.warning("Tipo de Peticion objectJson: "+req.getParameter("objectJson"));
		
		String parametro = req.getParameter("objectJson");
		
		if(parametro.contains("proyectos")){
			out.write(new Gson().toJson(getProyects()));
			
			
		}else if(parametro.contains("obras")){
			
			String [] elementos  = parametro.split(":");
			out.write(new Gson().toJson(getObras(elementos[1])));
		}
		else{

			GeneraReporteEntity reporte = (GeneraReporteEntity)JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new GeneraReporteEntity());
			out.write("");
			System.out.println();
		}
		
		
	}

	
	
	



	private ArrayList<ProyectoEntityHelp> getProyects(){
		ArrayList<ProyectoEntityHelp> proyectos = new ArrayList<ProyectoEntityHelp>();
		CollectionResponse<Proyecto>listaProyectos =null;
		
		listaProyectos =  new ProyectoEndpoint().listProyecto(null, null);
		
		for (Proyecto element : listaProyectos.getItems()) {
			ProyectoEntityHelp proyectEntity = new ProyectoEntityHelp();
			proyectEntity.setId(element.getId_Proyecto());
			proyectEntity.setNombre(element.getNombre_corto());
			proyectos.add(proyectEntity);
			
		}
		
		return proyectos;
		
		
		
		
	}
	private ArrayList<ProyectoEntityHelp> getObras(String id) {
		ArrayList<ProyectoEntityHelp> obras = new ArrayList<ProyectoEntityHelp>();
		CollectionResponse<Obra>listaObras =null;
		
		listaObras =  new ObraEndpoint().listObra(null, null, new Long(id), 0L, "");
		
		for (Obra element : listaObras.getItems()) {
			ProyectoEntityHelp proyectEntity = new ProyectoEntityHelp();
			proyectEntity.setId(element.getId_Obra());
			proyectEntity.setNombre(element.getNombre());
			obras.add(proyectEntity);
			
		}
		
		return obras;
	}
	
}
