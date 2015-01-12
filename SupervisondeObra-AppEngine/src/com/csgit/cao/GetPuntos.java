package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.model.Ubicaciones;
import com.csgit.cao.model.UbicacionesEndpoint;
import com.csgit.entity.ObraEntity;
import com.csgit.entity.PuntoEntity;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class GetPuntos extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		ObraEntity obra;
		String error = "";
		resp.setContentType("text/json");
		String parametro = req.getParameter("objectJson");
		if(parametro.contains("puntos")){
			ArrayList<PuntoEntity> puntos = new ArrayList<>();
			CollectionResponse<Ubicaciones> ubicaciones;
			ubicaciones = new UbicacionesEndpoint().listUbicaciones(null, null);
			String puntos2 = "";
			Ubicaciones ubi = new UbicacionesEndpoint().getUbicaciones(new Long(ubicaciones.getItems().size()));
			for (String puntoEntity : ubi.getUbicacion()) {
				puntos2+=puntoEntity+",";
			}
		String puntossplit[] = puntos2.split(",");
		for(int r=0;r<=puntossplit.length-2;r+=2){
			PuntoEntity pt = new PuntoEntity();
			pt.setK(puntossplit[r]);
			pt.setD(puntossplit[r+1]);
		   puntos.add(pt);	
		}
			out.write(new Gson().toJson(puntos));
			System.out.println(puntos2);
			
		}
	}
	
}
