package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

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

public class EditUsuario extends HttpServlet{

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
		MathUtil util = new MathUtil();
		PrintWriter out = resp.getWriter();
		String error = "";
		
		resp.setContentType("text/json");	
		System.out.println(req.getParameter("objectJson"));
		String parametro = req.getParameter("objectJson");
		if(parametro.contains("peticion")){   //validamos si es la peticion para el catalogo de empresa 
			System.out.println("es una peticion");
			out.write(new Gson().toJson(obtenerUsuarios()));
			return;
		}else if(parametro.contains("opcionusuario:")){
			String[] split= parametro.split(":");
			Long id_Usuario = Long.parseLong(split[1].replace('"', ' ').trim());
			System.out.println("voy a devolver los usuarios y detalle  con idUsuario = " + id_Usuario);
			out.write(new Gson().toJson(UsuarioBusiness.obtenerUsuario(id_Usuario)));
		}else if(parametro.contains("opciontipoUsuario")){
			System.out.println("voy a devolver los tipos de usuarios");
			out.write(new Gson().toJson(obtenerTipoUsuarios()));
			return;
		}else if(parametro.contains("opcionpersona:")){
			String[] split= parametro.split(":");
			Long id_Usuario = Long.parseLong(split[1].replace('"', ' ').trim());
			System.out.println("voy a devolver las personas ");
			out.write(new Gson().toJson(obtenerPersonas(id_Usuario)));
			return;
		}else{
			UsuarioEndpoint usuarioEndpoint = new UsuarioEndpoint();
			Usuario usuario = (Usuario) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new Usuario());
			
			if(!UsuarioBusiness.existenteUsuario(usuario.getUsuario(), usuario.getId_Propietario())){
				try {
					usuario.setVisible(true);
					usuario.setContrasena(util.md5(usuario.getContrasena()));
					usuarioEndpoint.updateUsuario(usuario);
					
					System.out.println("se edito correctamente el usuario");
					out.write(new Gson().toJson("Se edito correctamente el usuario"));
				} catch (Exception e) {
					// TODO: handle exception
					error += e.getMessage();
					System.out.println("hubo un error al editar el usuario");
					out.write(new Gson().toJson(error)); 
				}		
			}
			else {
				error += "El usuario ya existe ";
				out.write(new Gson().toJson(error)); 
			}
		}

	}
	
	
	private Collection<UsuarioEntity> obtenerPersonas(Long id_Usuario) {
		// TODO Auto-generated method stub
		CollectionResponse<Persona> lista = new PersonaEndpoint().listPersona(null, null);
		Collection<Persona> lista1 = lista.getItems();
		Collection<UsuarioEntity> regresar = new ArrayList<>();
		for (Persona elem : lista1) {
			if(!UsuarioBusiness.PersonaAsignada(elem.getId_Persona(), id_Usuario) && elem.isVisible()){
				UsuarioEntity ue = new UsuarioEntity();
				ue.setId(elem.getId_Persona());
				String nombre = elem.getNombre() + " " + elem.getAp_Paterno() + " " + elem.getAp_Materno();
				ue.setNombre(nombre);
				regresar.add(ue);
			}
		}
		return regresar;
	}

	private Collection<UsuarioEntity> obtenerTipoUsuarios() {
		// TODO Auto-generated method stub
		Collection<UsuarioEntity>respuesta = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			UsuarioEntity aux_usuario = new UsuarioEntity();
			if(i==0){
				aux_usuario.setId(new Long(Constants.idAdministrador));
				aux_usuario.setNombre(Constants.administradorTexto);	
			}else if(i==1){
				aux_usuario.setId(new Long(Constants.idDirectivo));
				aux_usuario.setNombre(Constants.directivoTexto);
			}else if(i==2){
				aux_usuario.setId(new Long(Constants.idSupervisor));
				aux_usuario.setNombre(Constants.supervisorTexto);
			}
			respuesta.add(aux_usuario);
		}
		return respuesta;
	}

	private Collection<UsuarioEntity> obtenerUsuarios() {
		// TODO Auto-generated method stub
		CollectionResponse<Usuario> lista = new UsuarioEndpoint().listUsuario(null, null);
		Collection<Usuario>lista1 = lista.getItems();
		Collection<UsuarioEntity>respuesta = new ArrayList<>();
	    for ( Usuario elem : lista1) {
	    	UsuarioEntity aux_usuario = new UsuarioEntity();
			aux_usuario.setId(elem.getId_Propietario());
			aux_usuario.setNombre(elem.getUsuario());
			respuesta.add(aux_usuario);	
	    }
		return respuesta;
	}
}
