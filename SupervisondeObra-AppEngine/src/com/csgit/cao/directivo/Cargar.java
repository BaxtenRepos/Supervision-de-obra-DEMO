package com.csgit.cao.directivo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.model.ProyectoDelDirectivo;
import com.csgit.cao.model.ProyectoDelDirectivoEndpoint;
import com.csgit.entity.ProyectoEntity;
import com.google.api.server.spi.response.CollectionResponse;

public class Cargar extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		ProyectoEntity proyecto;
		String error = "";
		resp.setContentType("text/json");

		//elementos = new String[]{Constants.contratistaTexto,Constants.dependenciaTexto,Constants.particularTexto,Constants.secretariaTexto,Constants.supervisoraTexto};

		String parametro = req.getParameter("objectJson");
		if(parametro.contains("proyectos")){
			String parametros [] = parametro.split(":");
			long idDirectivo = Long.parseLong(parametros[1]);
			System.out.println();
			CollectionResponse<ProyectoDelDirectivo> proyectos = new ProyectoDelDirectivoEndpoint().listProyectoDelDirectivo(null, null, idDirectivo);
System.out.println(proyectos.getItems().size());
			
			
			
			
		}
	}

}
