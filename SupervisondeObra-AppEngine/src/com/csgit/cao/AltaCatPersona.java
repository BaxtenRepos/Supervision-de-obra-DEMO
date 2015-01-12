package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.business.PersonalBusiness;
import com.csgit.cao.model.Cat_Personal;
import com.csgit.cao.model.Cat_PersonalEndpoint;
import com.csgit.util.JsonParserUtil;
import com.google.gson.Gson;

public class AltaCatPersona extends HttpServlet {

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
		Cat_PersonalEndpoint Cat_PersonalEndpoint = new Cat_PersonalEndpoint();
		Cat_Personal Cat_Personal = new Cat_Personal();
		Cat_Personal Cat_Personal2 = new Cat_Personal();
		PrintWriter out = resp.getWriter();
		String error = "";

		// empresaBusiness = new EmpresaBusiness();

		resp.setContentType("text/json");

		// si el objeto es igual al string peticion entonces regresamos el
		// catalogo de empresas

		Cat_Personal = (Cat_Personal) JsonParserUtil.getInstance()
				.getJsonParserObject(req.getParameter("objectJson"),
						new Cat_Personal());
		Cat_Personal2.setId_Tipo_Personal(GetIds.getIdCatPersona() + 1);
		Cat_Personal2.setTipo_Personal(Cat_Personal.getTipo_Personal());
		Cat_Personal2.setDescipcion(Cat_Personal.getDescipcion());
		Cat_Personal2.setVisible(true);
		System.out.println(req.getParameter("objectJson"));

		if (!PersonalBusiness.existenteCatPersonal(Cat_Personal2.getTipo_Personal())) {
			System.out.println();
			// insertamor los parametros
			try {
				Cat_PersonalEndpoint.insertCat_Personal(Cat_Personal2);
				System.out.println("se inserto correctamente el CatPersonal");
				out.write(new Gson()
						.toJson("se inserto correctamente el CatPersonal"));
			} catch (Exception e) {
				// TODO: handle exception
				error += e.getMessage();
				System.out.println("hubo un error al insertar el CatPersonal");
				out.write(new Gson().toJson(error));
			}
		} else {
			error += "El Tipo de Persona ya existe ";
			out.write(new Gson().toJson(error));
		}

	}

}
