package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.misc.JavaAWTAccess;

import com.csgit.cao.model.Concepto;
import com.csgit.cao.model.ConceptoEndpoint;
import com.csgit.cao.model.Usuario;
import com.csgit.cao.model.UsuarioEndpoint;
import com.csgit.entity.InformacionUsuario;
import com.csgit.entity.LoginEntity;
import com.csgit.entity.ObraEntityEdit;
import com.csgit.entity.ProyectoEntity;
import com.csgit.util.JsonParserUtil;
import com.csgit.util.MathUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class Login extends HttpServlet implements java.io.Serializable{
	
	private static final Logger log = Logger.getLogger(Login.class.getName());
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		MathUtil util = new MathUtil();
		
		ConceptoEndpoint ce = new ConceptoEndpoint();
		//
//		CollectionResponse<Concepto> conceptos = ce.listConcepto(null, null, 0L, 0L);
//		for (Concepto elem : conceptos.getItems()) {
//			
//			ce.removeConcepto(elem.getId_Concepto());
//		}
		
		////
		

 		PrintWriter out = resp.getWriter();
		resp.setContentType("text/json");
		LoginEntity proyecto;
		String error = "";
		String conversio;
	
		System.out.println("Tipo de Peticion: "+req.getParameter("objectJson"));
		//log.warning("Tipo de Peticion objectJson: "+req.getParameter("objectJson"));
		
		String parametro = req.getParameter("objectJson");
		
		if(parametro.contains("loginPlus"))
		{		
			//log.warning("Datos: "+req.getParameter("objectDatos"));
			System.out.println("Entra a peticion_plus");
			InformacionUsuario usr = new InformacionUsuario();
			usr = (InformacionUsuario) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectDatos"), new InformacionUsuario());			
			HttpSession sessionPlus = req.getSession(true);	
			sessionPlus.setAttribute(Constants.InfoUsrApellido, usr.getApellidos());
			sessionPlus.setAttribute(Constants.InfoUsrEmail, usr.getEmail());
			sessionPlus.setAttribute(Constants.InfoUsrFoto, usr.getFotografia());
			sessionPlus.setAttribute(Constants.InfoUsrNombre, usr.getNombre());
			
			//log.warning("OK");

			System.out.println("ID Session Plus: "+sessionPlus.getId());
			out.write(new Gson().toJson("ok"));
			return;
		}
	
			
		
		proyecto = (LoginEntity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new LoginEntity());
		try {
			 
			 conversio = util.md5(proyecto.getPassword());
			System.out.println("la conversion es: "+conversio);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			error+="error en el usuario";
			out.write(new Gson().toJson(error));
			return;
		}
		CollectionResponse<Usuario> usuarios = new UsuarioEndpoint().listUsuario(null, null);
		for (Usuario   element : usuarios.getItems()) {
			System.out.println(element.getUsuario()+" pass: "+element.getContrasena());
			
			
		}
		Usuario usuario = new UsuarioEndpoint().getUsuario(new Long(0), proyecto.getUser(),conversio);
		if(usuario!=null){
			HttpSession session = req.getSession(true);
			
		out.write(new Gson().toJson(usuario.getId_Persona()));
		}
		else out.write(new Gson().toJson("verifique su informacion usuario y/o password invalidos"));
	}



}
