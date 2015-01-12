package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.model.Cat_Personal;
import com.csgit.cao.model.Cat_PersonalEndpoint;
import com.csgit.entity.auxEntity;
import com.csgit.entity.borrarEntity;
import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class ListaCatPersonas extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

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
		resp.setContentType("text/json");	
		System.out.println(req.getParameter("objectJson"));
		String parametro = req.getParameter("objectJson");
		if(parametro.contains("peticion"))
		{   //validamos si es la peticion para el catalogo de proyectos 
			out.write(new Gson().toJson(obtenerCatPersonas()));
			return;
		}else{
			borrarEntity catpersonalborrar = (borrarEntity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new borrarEntity());
			ArrayList<auxEntity> idcatpersonalBorrar = catpersonalborrar.getBorrar();
			
			Cat_PersonalEndpoint cat_PersonalEndpoint = new Cat_PersonalEndpoint();
			ArrayList<auxEntity> borrados = new ArrayList<auxEntity>();
			
			for (auxEntity idCat_Personal : idcatpersonalBorrar) {
				Cat_Personal aux_cat_Personal = obtenerCatPersonas(idCat_Personal.getId());
				Cat_Personal cat_Personal = new Cat_Personal();
				
				cat_Personal.setId_Tipo_Personal(aux_cat_Personal.getId_Tipo_Personal());
				cat_Personal.setTipo_Personal(aux_cat_Personal.getTipo_Personal());
				cat_Personal.setDescipcion(aux_cat_Personal.getDescipcion());
				cat_Personal.setVisible(false);
				try {
					cat_PersonalEndpoint.updateCat_Personal(cat_Personal);
					borrados.add(idCat_Personal);
				} catch (Exception e) {
					// TODO: handle exception
//					out.write(new Gson().toJson("ha ocurrido un error con la empresa "+cat_Personal.getTipo_Personal()+
//							": " + e.getMessage()));
					System.out.println("ha ocurrido un error con la empresa "+cat_Personal.getTipo_Personal()+
							": " + e.getMessage());
				}
			}
			out.write(new Gson().toJson(borrados));
			return;
		}
	}
	
	private Collection<Cat_Personal> obtenerCatPersonas() 
	{
		CollectionResponse<Cat_Personal> listaRes = new Cat_PersonalEndpoint().listCat_Personal(null, null);
		Collection<Cat_Personal> lista = listaRes.getItems();
		Collection<Cat_Personal> regresar = new ArrayList<>();
		for (Cat_Personal elem : lista) {
			if(elem.isVisible()){
				regresar.add(elem);
			}
		}
		return regresar;
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
