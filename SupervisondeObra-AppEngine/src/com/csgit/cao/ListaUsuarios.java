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
import com.csgit.cao.model.Directivo_Proyecto;
import com.csgit.cao.model.Directivo_ProyectoEndpoint;
import com.csgit.cao.model.Obra;
import com.csgit.cao.model.ObraEndpoint;
import com.csgit.cao.model.Persona;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.cao.model.Usuario;
import com.csgit.cao.model.UsuarioEndpoint;
import com.csgit.entity.UsuarioDetalleEntity;
import com.csgit.entity.auxEntity;
import com.csgit.entity.borrarEntity;
import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class ListaUsuarios extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private String nombre="";
	private String telefono="";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/json");
		String parametro = req.getParameter("objectJson");
		if(parametro.contains("peticion"))
		{   //validamos si es la peticion para el catalogo de proyectos 
			out.write(new Gson().toJson(obtenerDetalleUsuarios()));
			return;
		}else{
			borrarEntity usuariosborrar = (borrarEntity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new borrarEntity());
			ArrayList<auxEntity> idUsuariosBorrar = usuariosborrar.getBorrar();
			CollectionResponse<Directivo_Proyecto> lista = new Directivo_ProyectoEndpoint().listDirectivo_Proyecto(null, null, 0L);
			Collection<Directivo_Proyecto>lista1 = lista.getItems();
			CollectionResponse<Obra> listaObra = new ObraEndpoint().listObra(null, null, new Long(0), new Long(0), "");
			Collection<Obra>listaObra1 = listaObra.getItems();
			
			UsuarioEndpoint usuarioEndpoint = new UsuarioEndpoint();
			ArrayList<auxEntity> borrados = new ArrayList<auxEntity>();
			
			for (auxEntity idUsuarios : idUsuariosBorrar) {
				if(!UsuarioBusiness.isDirectivoProyectos(idUsuarios.getId(), lista1)){
					if(!UsuarioBusiness.isSupervisorObra(idUsuarios.getId(), listaObra1)){
						Usuario aux_usuario = UsuarioBusiness.obtenerUsuario(idUsuarios.getId());
						Usuario usuario = new Usuario();
						
						usuario.setId_Propietario(aux_usuario.getId_Propietario());
						usuario.setId_Tipo_Persona(aux_usuario.getId_Tipo_Persona());
						usuario.setUsuario(aux_usuario.getUsuario());
						usuario.setContrasena(aux_usuario.getContrasena());
						usuario.setId_Persona(aux_usuario.getId_Persona());
						usuario.setVisible(false);
						
						try {
							usuarioEndpoint.updateUsuario(usuario);
							borrados.add(idUsuarios);
						} catch (Exception e) {
							out.write(new Gson().toJson("ha ocurrido un error con el usuario "+usuario.getUsuario()+
									": " + e.getMessage()));
						}
					}
					
				}
			}
			out.write(new Gson().toJson(borrados));
			return;
		}
	}
	
	private Collection<UsuarioDetalleEntity> obtenerDetalleUsuarios()
	{
		CollectionResponse<Usuario> listaRes = new UsuarioEndpoint().listUsuario(null, null);
		Collection<Usuario> lista = listaRes.getItems();
		Collection<UsuarioDetalleEntity> regresar = new ArrayList<>();
		//Set regresar = new TreeSet();
		for(Usuario usr : lista)
		{
			if(usr.isVisible()){
				UsuarioDetalleEntity usrDtEnt = new UsuarioDetalleEntity();
				usrDtEnt.setId(usr.getId_Propietario());
				usrDtEnt.setTipoUsuario(usr.getId_Tipo_Persona());
				usrDtEnt.setUsuario(usr.getUsuario());
				ObtenerDetallePersona(usr.getId_Persona()); //
				usrDtEnt.setNombre(this.nombre);
				usrDtEnt.setTelefonos(this.telefono);
				
				regresar.add(usrDtEnt);
			}
		}
		return regresar;
	}
	
	private void ObtenerDetallePersona(Long usr_id)
	{
		CollectionResponse<Persona> listaRes = new PersonaEndpoint().listPersona(null, null);
		Collection<Persona> lista = listaRes.getItems();
		for (Persona elem : lista) 
		{
			if(elem.getId_Persona() == usr_id)
			{
				this.nombre = elem.getNombre() + " " + elem.getAp_Paterno() + " " + elem.getAp_Materno();
				this.telefono = elem.getTelefonos();
			}
		}
	}
}
