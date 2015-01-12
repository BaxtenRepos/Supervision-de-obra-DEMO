package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.business.UsuarioBusiness;
import com.csgit.cao.model.Persona;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.cao.model.Usuario;
import com.csgit.cao.model.UsuarioEndpoint;
import com.csgit.entity.UsuarioEntity;
import com.csgit.util.JsonParserUtil;
import com.csgit.util.MathUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class AltaUsuario extends HttpServlet{
	
	/**
	 * 
	 */
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
		String error = "";
		MathUtil util = new MathUtil();
		
		resp.setContentType("text/json");	
		System.out.println(req.getParameter("objectJson"));
		String parametro = req.getParameter("objectJson");
		if(parametro.contains("peticion")){   //
			System.out.println("es una peticion");
			out.write(new Gson().toJson(obtenerTipoUsuario()));
			return;
		}else if(parametro.contains("opciopersona")){
			System.out.println("voy a devolver las personas ");
			out.write(new Gson().toJson(obtenerPersonas()));
		}else{
			Usuario usuario = new Usuario();
			UsuarioEndpoint usuarioEndpoint = new UsuarioEndpoint();
			usuario = (Usuario) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new Usuario());
			if(!UsuarioBusiness.existenteUsuario(usuario.getUsuario())){
				usuario.setId_Propietario(GetIds.getIdUsuario()+1);
				usuario.setVisible(true);
				try {
					System.out.println(usuario.getContrasena());
					usuario.setContrasena(util.md5(usuario.getContrasena()));
					System.out.println(usuario.getContrasena());
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					usuarioEndpoint.insertUsuario(usuario);
					System.out.println("se inserto correctamente el usuario");
					out.write(new Gson().toJson("Se inserto correctamente el usuario"));
				} catch (Exception e) {
					error += e.getMessage();
					System.out.println("hubo un error al insertar al usuario");
					out.write(new Gson().toJson(error)); 
				}
			}
			else {
				error += "El usuario ya existe ";
				out.write(new Gson().toJson(error)); 
			}
		}

	}
	
	private Collection<UsuarioEntity> obtenerTipoUsuario() {
		// TODO Auto-generated method stub
		Collection<UsuarioEntity>respuesta = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			UsuarioEntity aux_usuario = new UsuarioEntity();
			if(i==0){
				aux_usuario.setId(new Long(Constants.idAdministrador));
				aux_usuario.setTipo(Constants.administradorTexto);
			}else if(i==1){
				aux_usuario.setId(new Long(Constants.idDirectivo));
				aux_usuario.setTipo(Constants.directivoTexto);
			}else if(i==2){
				aux_usuario.setId(new Long(Constants.idSupervisor));
				aux_usuario.setTipo(Constants.supervisorTexto);
			}
			respuesta.add(aux_usuario);
		}
		return respuesta;
	}
	
	private Collection<UsuarioEntity> obtenerPersonas() {
		// TODO Auto-generated method stub
		CollectionResponse<Persona> lista = new PersonaEndpoint().listPersona(null, null);
		Collection<Persona> lista1 = lista.getItems();
		//Collection<UsuarioEntity> regresar = new ArrayList<>();
		Set regresar = new TreeSet();
		for (Persona elem : lista1) {
			if(!UsuarioBusiness.PersonaAsignada(elem.getId_Persona()) && elem.isVisible()){
				UsuarioEntity ue = new UsuarioEntity();
				ue.setId(elem.getId_Persona());
				String nombre = elem.getNombre() + " " + elem.getAp_Paterno() + " " + elem.getAp_Materno();
				ue.setNombre(nombre);
				regresar.add(ue);
			}
		}
		return regresar;
	}


}
