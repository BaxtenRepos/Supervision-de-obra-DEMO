package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.model.Directivo_Proyecto;
import com.csgit.cao.model.Directivo_ProyectoEndpoint;
import com.csgit.cao.model.Empresa;
import com.csgit.cao.model.EmpresaEndpoint;
import com.csgit.cao.model.Persona;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.cao.model.Proyecto;
import com.csgit.cao.model.ProyectoEndpoint;
import com.csgit.cao.model.Ubicaciones;
import com.csgit.cao.model.UbicacionesEndpoint;
import com.csgit.cao.model.Usuario;
import com.csgit.cao.model.UsuarioEndpoint;
import com.csgit.entity.DependenciasEntity;
import com.csgit.entity.DirectivoProyectoEntity;
import com.csgit.entity.ProyectoEntityEdit;
import com.csgit.entity.PuntoEntity;
import com.csgit.entity.UsuarioEntity;
import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class EditProyecto extends HttpServlet {
	
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
		
		resp.setContentType("text/json");	
		System.out.println(req.getParameter("objectJson"));
		String parametro = req.getParameter("objectJson");
		
		if(parametro.contains("peticion")){   //validamos si es la peticion para el catalogo de proyectos 
			out.write(new Gson().toJson(obtenerProyectos()));
			return;
		}else if(parametro.contains("opcionproyecto:")){   //validamos si es la peticion para el catalogo de proyectos 
			String[] split= parametro.split(":");
			Long idProyecto = Long.parseLong(split[1].replace('"', ' ').trim());
			System.out.println("voy a devolver el proyecto con idProyecto = " + idProyecto);
			out.write(new Gson().toJson(obtenerProyecto(idProyecto)));
			return;
		}else if(parametro.contains("opcionselect")){   //validamos si es la peticion para el catalogo de empresas
			System.out.println("voy a devolver las empresas");
			out.write(new Gson().toJson(obtenerDependencias()));
			return;
		}else if(parametro.contains("opciondirectivos")){   //validamos si es la peticion para el catalogo de proyectos 
			System.out.println("voy a devolver a los directivos");
			out.write(new Gson().toJson(obtenerDirectivos()));
			return;
		}else if(parametro.contains("opcionubicacion:")){
			String[] split= parametro.split(":");
			Long idUbicacion = Long.parseLong(split[1].replace('"', ' ').trim());
			System.out.println("voy a devolver la ubicacion con idUbicacion = " + idUbicacion);
			out.write(new Gson().toJson(obtenerUbicacion(idUbicacion)));
			return;
		}else{
			ProyectoEntityEdit proyectoelemnt = (ProyectoEntityEdit) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new ProyectoEntityEdit());
			
			Proyecto proyecto = new Proyecto();
			ProyectoEndpoint proyectoEndpoint = new ProyectoEndpoint();
			
			proyecto.setId_Proyecto(proyectoelemnt.getId_Proyecto());
			proyecto.setId_secretaria(proyectoelemnt.getId_secretaria());
			proyecto.setId_dependencia(proyectoelemnt.getId_dependencia());
			proyecto.setId_ubicacion(proyectoelemnt.getId_ubicacion());
			proyecto.setNombre_largo(proyectoelemnt.getNombre_largo());
			proyecto.setNombre_corto(proyectoelemnt.getNombre_corto());
			proyecto.setDescripcion(proyectoelemnt.getDescripcion());
			try {
				proyectoEndpoint.updateProyecto(proyecto);
				try {
					InsertaUbicacion iu = new InsertaUbicacion();
					iu.updatePunto(proyectoelemnt.getDatosArrayUbicacion(), proyectoelemnt.getId_ubicacion());
					try {
						Collection<String> new_directivo = proyectoelemnt.getDirectivo();
						Collection<String> old_directivo = new ArrayList<String>();
						ArrayList<DirectivoProyectoEntity> directivos = proyectoelemnt.getDirectivo_Proyecto();
						for (DirectivoProyectoEntity aux : directivos) {
							old_directivo.add(String.valueOf(aux.getId_directivo()));
						}
						Collection<String> similar = new HashSet<String>(new_directivo);
						similar.retainAll(old_directivo);
						
				        Collection<String> directivo_remove = new HashSet<String>();
				        directivo_remove.addAll(old_directivo);
				        Collection<String> directivo_add = new HashSet<String>();
				        directivo_add.addAll(new_directivo);
				        directivo_remove.removeAll(similar);
				        directivo_add.removeAll(similar);
						System.out.println(directivo_remove);
						System.out.println(directivo_add);
						
						Directivo_ProyectoEndpoint directivoEndpoint = new Directivo_ProyectoEndpoint();
						ArrayList<Long> id_removeDirectivo_Proyecto = getIdDirectivosRemove(directivos,directivo_remove);
						for (Long id : id_removeDirectivo_Proyecto) {
							try {
								directivoEndpoint.removeDirectivo_Proyecto(id);
							} catch (Exception e) {
								// TODO: handle exception
//								out.write(new Gson().toJson("Proyecto insertado correctamente"));	
								System.out.println("Error al remover directivos");
							}
						}
						
						if(directivo_add.size() != 0){
							Long directivoId= GetIds.getIdDirectivo_Proyecto()+1;
							for (String idDirectivo : directivo_add) {
								Directivo_Proyecto dp = new Directivo_Proyecto();
								
								dp.setId(directivoId);
								dp.setId_directivo(Long.valueOf(idDirectivo));
								dp.setId_proyecto(proyectoelemnt.getId_Proyecto());
								try {
									directivoEndpoint.insertDirectivo_Proyecto(dp);
									directivoId ++;
									out.write(new Gson().toJson(proyectoelemnt.getId_Proyecto()));	
								} catch (Exception e) {
									error +="error al editar el Proyecto";
									out.write(new Gson().toJson(error));
									System.out.println("error al editar el Proyecto "+ e.getMessage() );
								}
							}
						}else {
							out.write(new Gson().toJson(proyectoelemnt.getId_Proyecto()));
							System.out.println("Proyecto editado correctamente");
						}
						
						
					} catch (Exception e) {
						error +="error al editar el Proyecto";
						out.write(new Gson().toJson(error));
						System.out.println("error al editar el Proyecto "+ e.getMessage() );
					}
				} catch (Exception e) {
					error +="error al editar el Proyecto";
					out.write(new Gson().toJson(error));
					System.out.println("error al editar el Proyecto "+ e.getMessage() );
				}
			} catch (Exception e) {
				error +="error al editar el Proyecto";
				out.write(new Gson().toJson(error));
				System.out.println("error al editar el Proyecto "+ e.getMessage() );
			}
		}
	}
	
	private ArrayList<PuntoEntity> obtenerUbicacion(Long idUbicacion) {

		ArrayList<PuntoEntity> puntos = new ArrayList<>();
		String puntos2 = "";
		Ubicaciones ubi = new UbicacionesEndpoint().getUbicaciones(idUbicacion);
		for (String puntoEntity : ubi.getUbicacion()) {
			puntos2+=puntoEntity+",";
		}
		String puntossplit[] = puntos2.split(",");
		for(int r=0;r<=puntossplit.length-2;r+=2){
			PuntoEntity pt = new PuntoEntity();
			pt.setK(puntossplit[r]);
			pt.setD(puntossplit[r+1]);
		    puntos.add(pt);	
		}
		return puntos;
	}

	private Collection<DependenciasEntity> obtenerProyectos() {
		CollectionResponse<Proyecto> lista = new ProyectoEndpoint().listProyecto(null, null);
		Collection<Proyecto>lista1 = lista.getItems();
		Collection<DependenciasEntity>respuesta = new ArrayList<>();
	    for (Proyecto elem : lista1) {
	    	DependenciasEntity aux_proyecto = new DependenciasEntity();
	    	aux_proyecto.setNombre(elem.getNombre_corto());
	    	aux_proyecto.setId(elem.getId_Proyecto());
	    	respuesta.add(aux_proyecto);
	    }
		return respuesta;
	}
	
	private ProyectoEntityEdit obtenerProyecto(Long idProyecto) {
		CollectionResponse<Proyecto> lista;
		Collection<Proyecto>lista1;
		ProyectoEntityEdit regresar = new ProyectoEntityEdit();
		lista =new ProyectoEndpoint().listProyecto(null, null);//traemos la lista de obras insertadas
		lista1=lista.getItems();
	    for (Proyecto elem : lista1) {
	    	if(elem.getId_Proyecto() == idProyecto){
	    		 regresar.setId_Proyecto(elem.getId_Proyecto());
	    		 regresar.setNombre_largo(elem.getNombre_largo());
	    		 regresar.setNombre_corto(elem.getNombre_corto());
	    		 regresar.setDescripcion(elem.getDescripcion());
	    		 regresar.setId_secretaria(elem.getId_secretaria());
	    		 regresar.setId_dependencia(elem.getId_dependencia());
	    		 regresar.setId_ubicacion(elem.getId_ubicacion());
	    		 break;
	    	}
	    }
		return obtenerIdDirectivo(regresar);
	}
	
	private ProyectoEntityEdit obtenerIdDirectivo(ProyectoEntityEdit regresar) {
		CollectionResponse<Directivo_Proyecto> lista =new Directivo_ProyectoEndpoint().listDirectivo_Proyecto(null, null, new Long(0));//traemos la lista de empresas insertadas
		Collection<Directivo_Proyecto> lista1=lista.getItems();
		ArrayList<DirectivoProyectoEntity> directivos = new ArrayList<DirectivoProyectoEntity>();
		for (Directivo_Proyecto elem : lista1) {
			if(elem.getId_proyecto() == regresar.getId_Proyecto()){
				DirectivoProyectoEntity aux = new DirectivoProyectoEntity();
				aux.setId(elem.getId());
				aux.setId_directivo(elem.getId_directivo());
				directivos.add(aux);
			}
		}
		regresar.setDirectivo_Proyecto(directivos);
		return regresar;
	}

	public Collection<DependenciasEntity> obtenerDependencias(){
		
		 Set regresar = new TreeSet();
		CollectionResponse<Empresa> lista =new EmpresaEndpoint().listEmpresa(null,null);//traemos la lista de empresas insertadas
		Collection<Empresa> lista1=lista.getItems();
		//Collection<DependenciasEntity>regresar = new ArrayList<>();
	    for (Empresa elem : lista1) {
        	if(elem.getId_tipo_empresa()==Constants.idDependencia && elem.isVisible()){
        		DependenciasEntity dp = new DependenciasEntity();
        		dp.setNombre(elem.getNombre());
        		dp.setId(elem.getId_Empresa());
        		dp.setTipoEmpresa(Constants.dependenciaTexto);
        		regresar.add(dp);
        	}else if(elem.getId_tipo_empresa()==Constants.idSecretaria && elem.isVisible()){
        		DependenciasEntity dp = new DependenciasEntity();
        		dp.setNombre(elem.getNombre());
        		dp.setId(elem.getId_Empresa());
        		dp.setTipoEmpresa(Constants.secretariaTexto);
        		regresar.add(dp);
        	}
	    }
		return regresar;
	}
	
	private Collection<UsuarioEntity> obtenerDirectivos() {
		// TODO Auto-generated method stub
		CollectionResponse<Usuario> lista = new UsuarioEndpoint().listUsuario(null, null);
		Collection<Usuario> lista1 = lista.getItems();
		Collection<UsuarioEntity> regresar = new ArrayList<>();
		
		CollectionResponse<Persona> listaPersonas = new PersonaEndpoint().listPersona(null, null);
		Collection<Persona> listaPersonas1 = listaPersonas.getItems();
		
		for (Usuario user : lista1) {
			if(user.getId_Tipo_Persona()==Constants.idDirectivo && user.isVisible()){
				UsuarioEntity ue = new UsuarioEntity();
				ue.setId(user.getId_Propietario());
				ue.setNombre(obtenerPersona(user.getId_Persona(),listaPersonas1));
				ue.setTipo(Constants.directivoTexto);
				regresar.add(ue);
			}
		}
		
		return regresar;
	}

	private String obtenerPersona(Long id_Persona,Collection<Persona> listaPersonas1) {
		String nombre = "";
		for (Persona elem : listaPersonas1){
			if(elem.getId_Persona() == id_Persona){
				nombre = elem.getNombre() + " " + elem.getAp_Paterno() + " " + elem.getAp_Materno();
				break;
			}
		}
		return nombre;
	}
	
	private ArrayList<Long> getIdDirectivosRemove(ArrayList<DirectivoProyectoEntity> directivos,
			Collection<String> directivo_remove){
		ArrayList<Long> id = new ArrayList<Long>();
		for (DirectivoProyectoEntity elem : directivos) {
			for (String elem2 : directivo_remove) {
				if(elem.getId_directivo() == Long.valueOf(elem2)){
					id.add(elem.getId());
				}
			}
		}
		return id;
	}


}
