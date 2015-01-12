package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csgit.entity.InformacionUsuario;
import com.google.gson.Gson;

public class ValidarSesion extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ValidarSesion.class
			.getName());

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
		resp.setContentType("text/json");
		System.out.println(req.getParameter("objectJson"));
		String parametro = req.getParameter("objectJson");

		if (parametro.contains("validasesion")) {
			HttpSession sesion = req.getSession();
			if (sesion.isNew()) {
				sesion.invalidate();
				out.write(new Gson().toJson(false));
			} 
			
			else 
			{
				InformacionUsuario usr = new InformacionUsuario();
				usr.setApellidos((String) sesion.getAttribute(Constants.InfoUsrApellido));
				usr.setEmail((String) sesion.getAttribute(Constants.InfoUsrEmail));
				usr.setFotografia((String) sesion.getAttribute(Constants.InfoUsrFoto));
				usr.setNombre((String) sesion.getAttribute(Constants.InfoUsrNombre));
				//log.warning("Objeto[Email] : " + usr.getEmail());
				out.write(new Gson().toJson(usr));
				//out.write(new Gson().toJson(true));
			}
		}
		else if(parametro.contains("idObra")){
			HttpSession sesion = req.getSession();
			String[] valor = parametro.split(":");
			valor[1]=valor[1].replace((char)34, (char)0);
			sesion.setAttribute("idObra", valor[1]);
			
		}
		else if(parametro.contains("cerrarsesion")){
			HttpSession sesion = req.getSession();
			sesion.invalidate();
			out.write(new Gson().toJson(true));
		}
	}

}
