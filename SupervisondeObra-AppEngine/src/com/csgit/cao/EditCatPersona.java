package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.business.PersonalBusiness;
import com.csgit.cao.model.Cat_Personal;
import com.csgit.cao.model.Cat_PersonalEndpoint;
import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class EditCatPersona extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cat_Personal cat_Personal = new Cat_Personal();
	private Cat_PersonalEndpoint cat_PersonalEndpoint = new Cat_PersonalEndpoint();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String error = "";
		resp.setContentType("text/json");	
		System.out.println(req.getParameter("objectJson"));
		String parametro = req.getParameter("objectJson");
		if(parametro.contains("opcioncatpersona:")){   //validamos si es la peticion para el catalogo de proyectos 
			String[] split= parametro.split(":");
			Long idTipoPersona = Long.parseLong(split[1].replace('"', ' ').trim());
			System.out.println("voy a devolver Cat Tipo Persona con id = " + idTipoPersona);
			out.write(new Gson().toJson(obtenerCatPersonas(idTipoPersona)));
			return;
		}else{
			Cat_Personal cat_Personal = new Cat_Personal();
			Cat_PersonalEndpoint cat_PersonalEndpoint = new Cat_PersonalEndpoint();
			cat_Personal = (Cat_Personal) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new Cat_Personal());
			if(!PersonalBusiness.existenteCatPersonal(cat_Personal.getTipo_Personal(), cat_Personal.getId_Tipo_Personal())){
				try {
					cat_Personal.setVisible(true);
					cat_PersonalEndpoint.updateCat_Personal(cat_Personal);
					System.out.println("Categoria de persona  editada correctamente");
					out.write(new Gson().toJson("Categoria de persona editada correctamente")); 
				} catch (Exception e) {
					error += "Error al editar la Categoria de persona";
					System.out.println(error + e.getMessage());
					out.write(new Gson().toJson(error)); 
				}
			}else {
				error += "Categoria de persona existente";
				out.write(new Gson().toJson(error)); 
			}
		}
	}
	
	private Cat_Personal obtenerCatPersonas(Long idTipoPersona){
		CollectionResponse<Cat_Personal> listaRes = new Cat_PersonalEndpoint().listCat_Personal(null, null);
		Collection<Cat_Personal> lista = listaRes.getItems();
		Cat_Personal regresar = new Cat_Personal();
		for (Cat_Personal aux : lista) {
			if (aux.getId_Tipo_Personal() == idTipoPersona){
				regresar = aux;
				break;
			}
		}
		return regresar;
	}

}
