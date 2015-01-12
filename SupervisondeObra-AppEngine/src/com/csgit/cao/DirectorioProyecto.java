package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.business.DirectorioBusiness;
import com.csgit.cao.model.Directorio;
import com.csgit.cao.model.DirectorioEndpoint;
import com.csgit.cao.model.Persona;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.entity.DirectorioproyectoEntity;
import com.csgit.entity.PersonaEntity;
import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class DirectorioProyecto extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		String error = "";
		resp.setContentType("text/json");
		String parametro = req.getParameter("objectJson");
		if(parametro.contains("usuarios")){
			
			out.write(new Gson().toJson(obtenerPersonas()));
			
		}else{
			
			DirectorioproyectoEntity dp ;
			dp = (DirectorioproyectoEntity)JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new DirectorioproyectoEntity());
			//proyecto = (ProyectoEntity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new ProyectoEntity());
			
			Directorio dir = new Directorio();
			dir.setArregloPersonasId(dp.getPersonas());
			dir.setIdDirectorio(GetIds.getIdDirectorio()+1);
			dir.setIdReferencia(Long.parseLong(dp.getIdProyecto()));
			dir.setTipoReferencia(new Long(Constants.Proyecto));
			if(!DirectorioBusiness.validaDirectorio(new Long(Constants.Proyecto),new Long(dir.getIdReferencia())))
			try {
				new DirectorioEndpoint().insertDirectorio(dir);
				out.write(new Gson().toJson("Directorio asociado correctamente"));
				return;
			} catch (Exception e) {
				// TODO: handle exception
				out.write(new Gson().toJson("Ocurrió un error al asociar el directorio"));
			}
			
			else
				out.write(new Gson().toJson("Esta obra ya contiene un directorio"));
			
			System.out.println("");
			
		}
	}

//	private ArrayList<DefaultEntity> getpersonas() {
//		// TODO Auto-generated method stub
//		ArrayList<DefaultEntity>regresa = new ArrayList<>();
//		
//		CollectionResponse<Persona> personas = new PersonaEndpoint().listPersona(null, null);
//		for (Persona element : personas.getItems()) {
//			DefaultEntity de = new DefaultEntity();
//			de.setId(element.getId_Persona().toString());
//			de.setNombre(element.getNombre()+" " +element.getAp_Paterno()+" "+element.getAp_Materno());
//			regresa.add(de);
//		}
//		return regresa;
//		
//	}
	
	private Collection<PersonaEntity> obtenerPersonas(){
		CollectionResponse<Persona> listaRes = new PersonaEndpoint().listPersona(null, null);
		Collection<Persona> lista = listaRes.getItems();
		Collection<PersonaEntity> regresar = new ArrayList<>();
		for (Persona elem : lista){
			if(elem.isVisible()){
				PersonaEntity ue = new PersonaEntity();
				ue.setId(elem.getId_Persona());
				String nombre = elem.getNombre() + " " + elem.getAp_Paterno() + " " + elem.getAp_Materno();
				ue.setNombre(nombre);
				ue.setCargo(elem.getCargo());
				ue.setEmail(elem.getEmails());
				regresar.add(ue);
			}
		}
		return regresar;
	}
}