package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csgit.cao.business.UbicacionesBusiness;
import com.csgit.cao.model.Directivo_Proyecto;
import com.csgit.cao.model.Directivo_ProyectoEndpoint;
import com.csgit.cao.model.Empresa;
import com.csgit.cao.model.EmpresaEndpoint;
import com.csgit.cao.model.Persona;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.cao.model.Proyecto;
import com.csgit.cao.model.ProyectoEndpoint;
import com.csgit.cao.model.Ubicaciones;
import com.csgit.cao.model.Usuario;
import com.csgit.cao.model.UsuarioEndpoint;
import com.csgit.cao.model.Xmpp;
import com.csgit.cao.model.XmppEndpoint;
import com.csgit.entity.DependenciasEntity;
import com.csgit.entity.ProyectoEntity;
import com.csgit.entity.PuntoEntity;
import com.csgit.entity.UsuarioEntity;
import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.google.gson.Gson;

public class AltaProyecto extends HttpServlet implements java.io.Serializable{
	
	private static final Logger log = Logger.getLogger(AltaProyecto.class.getName());

	private ProyectoEndpoint proyect;
	private Proyecto proyectElement; 
	InsertaUbicacion insertaUbicacion;
	DependenciasEntity dependenciasentity;
	Empresa empresa;
	EmpresaEndpoint empresaendpoint;
	CollectionResponse lista;
	Collection<Empresa>lista1;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
		
		
		
		// TODO Auto-generated method stub
		//Los forms y los a de html hacen peticiones request, los forms puden hacer post o get y los a hacen solo get, va, por eso me puse aqui
		HttpSession sesion = req.getSession();
		System.out.println();
		//Ya we aqui está tu sesion, aquí nace, puedes ingresar objectos al conexto de la sesion, y para destruirla cuando cierres tu aplicativo
		//con 
		//HttpSession sesion = req.getSession();
		//sesion.invalidate(); //aqui la destruyes
		
		List<String> personas = new ArrayList<String>();
		personas.add("Ricardo");
		personas.add("Osvaldo");
		personas.add("Sombradelsilencion");
		
		req.setAttribute("personas", personas); //aqui metemos al contexto de request los atributos y una vista jsp puede tomarlos
		//en vez de regresar un flujo, regresamos hacia una vista jsp
		req.getRequestDispatcher("/views/Altaproyecto.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub


		log.warning("prueba del log");
		
//	XmppSincronizacion sincronizacion = new XmppSincronizacion();
//	sincronizacion.enviaMensaje("osalazar@adquem.com", "mensaje enviado desde el alta de proyectos");
		HttpSession sesion = req.getSession (); 
		if (sesion.isNew ()) { 
			//Cual es la vista de error de sesion?
			//la mandare al index pero aqui lo que no se es el pedo de las / (diagonales)
			//req.getRequestDispatcher("vistaerrorSession").forward(req, resp);
			sesion.invalidate();
			resp.sendRedirect("http://www.google.com.mx");
			//req.getRequestDispatcher("/views/Altaempresa.jsp").forward(req, resp);
			return;
			//esto ya lo habia intentado hace un momento pero no funciono
		} else {
			//continuas la ejecución
			////////////////////////////////
			
			//inicializamos
			Collection<DependenciasEntity> elementos;
			proyect = new ProyectoEndpoint();
			proyectElement = new Proyecto();
			insertaUbicacion = new InsertaUbicacion();
			dependenciasentity = new DependenciasEntity();
			empresa = new Empresa();
			empresaendpoint = new EmpresaEndpoint();
			lista =empresaendpoint.listEmpresa(null,null);//traemos la lista de empresas insertadas
			lista1=lista.getItems();
			
			
			
			PrintWriter out = resp.getWriter();
			ProyectoEntity proyecto;
			String error = "";
			resp.setContentType("text/json");

			//elementos = new String[]{Constants.contratistaTexto,Constants.dependenciaTexto,Constants.particularTexto,Constants.secretariaTexto,Constants.supervisoraTexto};

			String parametro = req.getParameter("objectJson");
			//si el objeto es igual al string peticion entonces regresamos el catalogo de empresas
			if(parametro.contains("peticion")){   //validamos si es la peticion para el catalogo de empresa 
				System.out.println("es una peticion de proyectos ");
				
				Collection<DependenciasEntity> dependencias = obtenerDependencias();
				
				out.print(new Gson().toJson(dependencias));
				
				return;




			}
			else if(parametro.contains("validasesion"))
			{
				
				HttpSession sesion2 = req.getSession (); 
				if (sesion2.isNew ()) { 
				sesion.invalidate();
				out.write(new Gson().toJson(false));
				}else{
					out.write(new Gson().toJson(true));
				}
				
				
			}
			else if(parametro.contains("directivos")){
				Collection<UsuarioEntity> directivos = obtenerDirectivos();
				
				out.print(new Gson().toJson(directivos));
				
				return;
				
				
			}
			else{
			//parseo del objeto que recibimos del servlet 
			System.out.println(req.getParameter("objectJson"));
			proyecto = (ProyectoEntity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new ProyectoEntity());
			
			//validacion de existencia de puntos
			
			
			
			
			String dependencia = proyecto.getIdDependencia();
			String secretaria = proyecto.getIdSecretaria();
			long dependencialong;
			long secretarialong;
			long idProyecto; 
			if(dependencia.equalsIgnoreCase(Constants.dependenciaTexto))dependencialong =  Constants.idDependencia;	
			long idUbicacion = insertaUbicacion.insertaPuntos(proyecto.getDatosArrayUbicacion());
			if(idUbicacion==0){
				error += "Hubo un error al insertar la ubicacion el proyecto no se insertara";
				return;
			}else{
			    idProyecto = GetIds.getIdProyecto()+1;
				proyectElement.setId_ubicacion(idUbicacion);
				proyectElement.setDescripcion(proyecto.getIdDescripcion());
				proyectElement.setId_dependencia(getIdDependencia(proyecto.getIdDependencia()));
				proyectElement.setId_secretaria(getIdSecretaria(proyecto.getIdSecretaria()));
				proyectElement.setId_Proyecto(idProyecto);	
				proyectElement.setNombre_corto(proyecto.getNombreCorto());
				proyectElement.setNombre_largo(proyecto.getNombreLargo());
				
			}
			
			Long directivoId= GetIds.getIdDirectivo_Proyecto()+1;
			for (String idDirectivo : proyecto.getDirectivo()) {
				Directivo_Proyecto dp = new Directivo_Proyecto();
				Directivo_ProyectoEndpoint directivoEndpoint = new Directivo_ProyectoEndpoint();
				
				dp.setId(directivoId);
				dp.setId_directivo(Long.valueOf(idDirectivo));
				dp.setId_proyecto(idProyecto);
				try {
					directivoEndpoint.insertDirectivo_Proyecto(dp);
					directivoId ++;
				} catch (Exception e) {
					// TODO: handle exception
//					out.write(new Gson().toJson("Proyecto insertado correctamente"));	
					System.out.println("Error al insertar directivos");
				}
			}
			
			
			try {
				proyect.insertProyecto(proyectElement);
				System.out.println("el proyecto se inserto sin problemas con el id: "+ idProyecto);
				out.write(new Gson().toJson(proyectElement.getId_Proyecto())); 
				//insertamos avance fisico y financiero
				
				insertAvanceFinanciero insert = new insertAvanceFinanciero();
				insert.insertAvance(idProyecto, Constants.Proyecto);
				insertaAvanceFisico insertAvanceFisico = new insertaAvanceFisico();
				insertAvanceFisico.insertAvance(idProyecto, Constants.Proyecto);
				
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("entre al catch");
				System.out.println("ocurrio un error al i nsertar el proyecto :"+e.getMessage());
				error += e.getMessage();
//				directivoEndpoint.removeDirectivo_Proyecto(directivoId);
				
				out.write(new Gson().toJson(error)); 
			}
			
			
			
			}
			
			///////////////////////////////
			
			
			
			
			
			
			
			
			
			
			
		}
		
			
		//}
		
		
		
		
/*		PrintWriter out = resp.getWriter();
		ProyectoEntity proyecto;
		String error = "";
		
	
		resp.setContentType("text/json");
		try {
			//Parseamos la info
			System.out.println(req.getParameter("objectJson"));
			proyecto = (ProyectoEntity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new ProyectoEntity());
			
			//Reglas de negocio
			if(proyecto.getDatosArrayUbicacion().length == 0) { //digamos que tendrias que envar todo el objecto proyecto a una clase que valide especificamente no y entonces si es invalido
				error += "Sin datos de ubicación"; //se ejecuta el error necesario, aqui es muy sencillo y no generalizado we
				//out.print(new Gson().toJson(error)); //se crea un Gson para parsear de java a json y le metes el error, este puede ser una clase, una lista, etc, en este caso es un string sencillo va
				resp.setStatus(403);
				resp.getWriter().print(error);
				return; //y retornas void, vale? si, lo que te señale es para especificarle al response el tipo de contenido o flujo que retornas, como te dije puede ser cualquier flujo por
			}			//una imagen setContentType("img/jpg"), o un archivo setContenType("file/pdf"), etc estamos?si, ahora si ocurre una excepcion en tu proceso
			
			//Si hizo todo correctamente tendrias que hacer tu clase de validaciones we ok pero no entien
			out.write(new Gson().toJson("Todo bien oswi")); //ya we ok ok  vamos a ver
		} catch(NullPointerException e) { //Puedes meter las que necesite tu proceso no, aqui meti NullPointer que valida datos nulls por eso quite lo que puse de != null va
			error += e.getMessage(); //Si hay algo nulo vendra y tomara el mensage de excepcion
			resp.setStatus(401); //setea al response un estatos 401 que se considera error del 303 al 600 creo se consideran errores algo asi checalos mas tarde 
			resp.getWriter().print(error); //y aqui regreas el error
		} catch(Exception e) {
			error += e.getMessage();
			resp.setStatus(402);
			resp.getWriter().print(error);
		} finally {
			out.flush(); //cierras los flujos
			out.close();
		}
		return; //y regresas, vale?si, ahra vete al js
		*/
		
		
		
/*
 * if(!UbicacionesBusiness.arregloNoVacio(proyecto.getDatosArrayUbicacion())){
			error += "Sin datos de ubicación"; 
			resp.setStatus(400);
			resp.getWriter().print(error);
			return;
		}else
		{
			System.out.println("entre al else");
			puntos=proyecto.getDatosArrayUbicacion();
			proyectElement.setId_dependencia(new Long(1));
			proyectElement.setDescripcion(proyecto.getIdDescripcion());			
			proyectElement.setId_hubicacion(insertaUbicacion.insertaPuntos(puntos,Constants.Proyecto));
			proyectElement.setId_secretaria(new Long(6));
			proyectElement.setId_ubicacion(new Long(18));
			proyectElement.setNombre_corto("nombre corto");
			proyectElement.setNombre_largo("nomnbre largo");
			proyectElement.setId_Proyecto(new Long(10));

			proyect.insertProyecto(proyectElement);
			
		}		
 */
		
		
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

	public Collection<DependenciasEntity> obtenerDependencias(){
		
		//CollectionResponse lista;
		//Collection<Empresa>lista1;
		//Collection<DependenciasEntity>regresar = new ArrayList<>();
		 Set regresar = new TreeSet();
		//Empresa empresa = new Empresa();
		//EmpresaEndpoint empresaendpoint = new EmpresaEndpoint();
				
		//lista =empresaendpoint.listEmpresa(null,null);//traemos la lista de empresas insertadas
		//lista1=lista.getItems();
	
	    for (Empresa elem : lista1) {
	            		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	if(elem.getId_tipo_empresa()==Constants.idDependencia && elem.isVisible())
	        	{
	        		DependenciasEntity dp = new DependenciasEntity();
	        		dp.setNombre(elem.getNombre());
	        		dp.setTipoEmpresa(Constants.dependenciaTexto);
	        		regresar.add(dp);
	        	}
	        	else if(elem.getId_tipo_empresa()==Constants.idSecretaria && elem.isVisible())
	        	{
	        		DependenciasEntity dp = new DependenciasEntity();
	        		dp.setNombre(elem.getNombre());
	        		dp.setTipoEmpresa(Constants.secretariaTexto);
	        		regresar.add(dp);
	        	}
	        
	    }
		
			return regresar;
	}
	
	
	//hacemos la relacion entre el nombre de la dependendia seleccionada con el id que esta insertado
	private long getIdDependencia(String dato){
		long id=0;
	    for (Empresa elem : lista1) {
	        if(elem.getNombre().equalsIgnoreCase(dato)){     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId_Empresa();
	        	System.out.println("La secretaria :"+elem.getNombre() + "tiene el id: "+elem.getId_Empresa());
	        }
	    }
	    return id;
	}
	private long getIdSecretaria(String dato){
		long id=0;
	    for (Empresa elem : lista1) {
	        if(elem.getNombre().equalsIgnoreCase(dato)){     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getId_Empresa();
	        	System.out.println("La secretaria :"+elem.getNombre() + "tiene el id: "+elem.getId_Empresa());
	        }
	    }
	    return id;
	}
//	public Long  returnId(){
//		long id=0;
//		CollectionResponse lista;
//		Collection<Proyecto>lista1;
//				
//		lista =proyect.listProyecto(null,null);//traemos la lista de empresas insertadas
//		lista1=lista.getItems();
//	
//	    for (Proyecto elem : lista1) {
//	        if(elem.getId_Proyecto() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
//	        	id = elem.getId_Proyecto();
//	    }
//
//		return id;	
//	}
	
	
	
	
	
	public static String md5(String pass) throws Exception {
		String clear = "W2UGrILfpVXtY9QHBlbQeg3r7ZdReNWkkbTGTXz+efosc6hYoQhf192WAATmerS0XM6Ceva5WzpoRfE/YvIoIA==8eOjzPkt6eky6Id3rlLf5Q=="+pass;

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(clear.getBytes());

		int size = b.length;
		StringBuffer h = new StringBuffer(size);
		for (int i = 0; i < size; i++) {
		int u = b[i] & 255;
		if (u < 16) {
		h.append("0" + Integer.toHexString(u));
		} else {
		h.append(Integer.toHexString(u));
		}
		}
		System.out.println("es lo que regresa: "+h.toString());
		return h.toString();
		}
}

