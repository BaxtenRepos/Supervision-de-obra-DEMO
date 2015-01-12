package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.model.Multimedia;
import com.csgit.cao.model.MultimediaEndpoint;
import com.csgit.entity.LoginEntity;
import com.csgit.util.BibliotecaItemUtitlity;
import com.csgit.util.DefaultEntity;
import com.csgit.util.NombreTipoUtility;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

public class biblioteca extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
	PrintWriter out = resp.getWriter();
	resp.setContentType("text/json");
	LoginEntity proyecto;
	String error = "";

	ArrayList<BibliotecaItemUtitlity>lista = new ArrayList<>();
	
	
	String parametro = req.getParameter("objectJson");
	
	if(parametro.contains("biblioteca"))
	{	
		
		CollectionResponse<Multimedia>mult =  new MultimediaEndpoint().listMultimedia(null, null,new Long(Constants.Biblioteca), 0L, 0L);
		for (Multimedia elem : mult.getItems()) {
			BibliotecaItemUtitlity regresar = new BibliotecaItemUtitlity();
			regresar.setId(elem.getFormato());//aqui mandaremos el tipo de archivo video, imagen , xls, pdf etc.....
			regresar.setNombre(elem.getDescripcion());
			regresar.setTipo(Constants.URL_GET_BLOB_STORE+"*-*"+elem.getPath());
			regresar.setIdMultimedia(elem.getIdMultimedia());
			
			lista.add(regresar);
		}
		out.write(new Gson().toJson(lista));
	}
	
	else if(parametro.contains("elimina")){
		
		
	}else{
 		String element =  parametro.replace((char) 34, (char) 0);
		String[] elementos = element.split(",");

Long tiporeferencia =Long.parseLong(elementos[0].replace('"', ' ').trim());
Long idReferencia =  Long.parseLong(elementos[1].replace('"', ' ').trim());

		CollectionResponse<Multimedia> mult = new MultimediaEndpoint().listMultimedia(null, null,tiporeferencia,idReferencia, 0L);
		for (Multimedia elem : mult.getItems()) {
			BibliotecaItemUtitlity regresar = new BibliotecaItemUtitlity();
			regresar.setId(elem.getFormato());//aqui mandaremos el tipo de archivo video, imagen , xls, pdf etc.....
			regresar.setNombre(elem.getDescripcion());
			regresar.setTipo(Constants.URL_GET_BLOB_STORE+"*-*"+elem.getPath());
			regresar.setIdMultimedia(elem.getIdMultimedia());
			
			lista.add(regresar);
		}
		out.write(new Gson().toJson(lista));
		
	}




}

}
