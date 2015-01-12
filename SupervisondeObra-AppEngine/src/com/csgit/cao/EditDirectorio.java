package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.model.Directorio;
import com.csgit.cao.model.DirectorioEndpoint;
import com.csgit.cao.model.Persona;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.entity.DirectorioEntity;
import com.csgit.entity.UpdateDirectorioEntity;
import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class EditDirectorio extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = resp.getWriter();
		String error = "";
		
		
		resp.setContentType("text/json");	
		System.out.println(req.getParameter("objectJson"));
		String parametro = req.getParameter("objectJson");
		
		if(parametro.contains("valores-")){   //validamos si es la peticion para el catalogo de proyectos 
			System.out.println(parametro);
			String elementos[] = parametro.split("-");
			Long idReferencia = new Long(elementos[1]);
			Long idTipoReferencia = new Long(elementos[2].replace('"', ' ').trim());
			 ArrayList<DirectorioEntity> directorio = getdirectorio(idReferencia,idTipoReferencia);
			
			
			
			out.write(new Gson().toJson(directorio));
			return;
		}else{
			
			UpdateDirectorioEntity dp ;
			dp = (UpdateDirectorioEntity)JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new UpdateDirectorioEntity());
			Directorio dir = new Directorio();
			if(dp.getIdDirectorio()==null){
				dir.setIdDirectorio(GetIds.getIdDirectorio()+1);
				dir.setArregloPersonasId(dp.getPersonas());
				dir.setIdReferencia(Long.parseLong(dp.getIdReferencia()));
				dir.setTipoReferencia(Long.parseLong(dp.getIdTipoRef()));
				
				try {
					new DirectorioEndpoint().insertDirectorio(dir);
					out.write(new Gson().toJson("directorio actualizado correctamente"));
					
				} catch (Exception e) {
					// TODO: handle exception
					out.write(new Gson().toJson("no se pudo actualizar el directorio"));
				}
				
			}
			else{
				dir.setIdDirectorio(Long.parseLong(dp.getIdDirectorio()));
				dir.setArregloPersonasId(dp.getPersonas());
				dir.setIdReferencia(Long.parseLong(dp.getIdReferencia()));
				dir.setTipoReferencia(Long.parseLong(dp.getIdTipoRef()));
				
				try {
					new DirectorioEndpoint().updateDirectorio(dir);
					out.write(new Gson().toJson("directorio actualizado correctamente"));
					
				} catch (Exception e) {
					// TODO: handle exception
					out.write(new Gson().toJson("no se pudo actualizar el directorio"));
				}
				
			}
	
			
			
			
		}
	
	}

	private ArrayList<DirectorioEntity> getdirectorio(Long idReferencia, Long idTipoReferencia) {
		// TODO Auto-generated method stub
		ArrayList<DirectorioEntity> directorioentity = new ArrayList<>();
		String personas[] = null;
		Long idDirectorio = null;
		CollectionResponse<Directorio>directorio = new DirectorioEndpoint().listDirectorio(null, null,new Long(0), new Long(0));
		for (Directorio element : directorio.getItems()) {
			if(element.getIdReferencia().longValue()==idReferencia && element.getTipoReferencia().longValue()==idTipoReferencia){
				personas=element.getArregloPersonasId();
				idDirectorio = element.getIdDirectorio();
				break;
			}
		}
		CollectionResponse<Persona> listapersonas = new PersonaEndpoint().listPersona(null, null);
	
			for (Persona element : listapersonas.getItems()) {
				if(element.isVisible()){
					DirectorioEntity de = new DirectorioEntity();
					de.setId(element.getId_Persona());
					de.setNombre(element.getNombre()+" " +element.getAp_Paterno()+" "+element.getAp_Materno());
					de.setExiste(false);
					de.setId_directiorio(idDirectorio);
					de.setCargo(element.getCargo());
					de.setEmail(element.getEmails());
					
					try {
						for (String string : personas) {
							if(Long.parseLong(string)==element.getId_Persona())
								de.setExiste(true);
							
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					directorioentity.add(de);
				}
			}
			
		

	
		return directorioentity;
	}
}
