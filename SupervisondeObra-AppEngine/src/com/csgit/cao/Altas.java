package com.csgit.cao;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Altas extends HttpServlet{

	public Altas() {
		// TODO Auto-generated constructor stub
		
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String nombre = req.getParameter("usuario");
		System.out.println("welcome");
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("prueba 1");
		System.out.println(req.toString()+"mas "+resp.toString());
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

}
