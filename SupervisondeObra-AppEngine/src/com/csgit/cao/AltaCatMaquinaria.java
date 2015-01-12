package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.business.MaquinariaBusiness;
import com.csgit.cao.model.Cat_Tipo_Maquinaria;
import com.csgit.cao.model.Cat_Tipo_MaquinariaEndpoint;
import com.csgit.util.JsonParserUtil;
import com.google.gson.Gson;

public class AltaCatMaquinaria extends HttpServlet {
	
	Cat_Tipo_Maquinaria cat_tipo_maquinaria;
	Cat_Tipo_MaquinariaEndpoint cat_tipo_maquinariaEndpoint;
	
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
		cat_tipo_maquinariaEndpoint = new Cat_Tipo_MaquinariaEndpoint();
		cat_tipo_maquinaria = new Cat_Tipo_Maquinaria();
		PrintWriter out = resp.getWriter();
		String error = "";

		// empresaBusiness = new EmpresaBusiness();

		resp.setContentType("text/json");

		// si el objeto es igual al string peticion entonces regresamos el
		// catalogo de empresas

		cat_tipo_maquinaria = (Cat_Tipo_Maquinaria) JsonParserUtil.getInstance()
				.getJsonParserObject(req.getParameter("objectJson"),
						new Cat_Tipo_Maquinaria());
		cat_tipo_maquinaria.setId_tipo_Maquinaria(GetIds.getIdCatTipoMaquinaria() + 1);
		System.out.println(req.getParameter("objectJson"));

		if (!MaquinariaBusiness.existenteCatTipoMaquinaria(cat_tipo_maquinaria.getTipo_Maquinaria())) {
			System.out.println();
			// insertamor los parametros
			try {
				cat_tipo_maquinariaEndpoint.insertCat_Tipo_Maquinaria(cat_tipo_maquinaria);
				System.out.println("se inserto correctamente el CatMaquinaria");
				out.write(new Gson()
						.toJson("se inserto correctamente en el Catalogo de Maquinaria"));
			} catch (Exception e) {
				// TODO: handle exception
				error += e.getMessage();
				System.out.println("hubo un error al insertar en el Catalogo de Maquinaria");
				out.write(new Gson().toJson(error));
			}
		} else {
			error += "El Tipo de Maquinaria ya existe";
			out.write(new Gson().toJson(error));
		}

	}

}
