package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.business.PersonalBusiness;
import com.csgit.cao.model.Multimedia;
import com.csgit.cao.model.MultimediaEndpoint;
import com.csgit.cao.model.Persona;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.util.UtilFechas;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gson.Gson;

public class AltaPersonal extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PersonaEndpoint personaEndpoint;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	private String path;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{	
		PrintWriter out = resp.getWriter();
		String error = "";
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json; charset=UTF-8");
	
		if(!PersonalBusiness.existenteCorreo(req.getParameter("form3Email"))) //Valida si ya existe el correo ingresado
		{
			List<BlobKey> blobs = blobstoreService.getUploads(req).get("myFile");
			if(blobs!=null)
			{
				BlobKey blobKey = blobs.get(0);
				
				Persona persona = new Persona(); 
				personaEndpoint = new PersonaEndpoint();			
				path = blobKey.getKeyString();
				
				resp.setStatus(HttpServletResponse.SC_OK);
				
				Long id_Persona = GetIds.getIdPersona() + 1;
				
				persona.setId_Persona(id_Persona);
				persona.setNombre(req.getParameter("AltaPersona_Nombre"));
				persona.setAp_Paterno(req.getParameter("AltaPersona_ApPaterno"));
				persona.setAp_Materno(req.getParameter("AltaPersona_ApMaterno"));
				persona.setIniciales(req.getParameter("AltaPersona_Iniciales"));
				persona.setFecha_Nacimiento(req.getParameter("AltaPersona_FechaNaci"));
				persona.setDireccion(req.getParameter("AltaPersona_Direccion"));
				persona.setTelefonos(req.getParameter("AltaPersona_Telefono"));
				persona.setRadios(req.getParameter("AltaPersona_Radio"));
				persona.setEmails(req.getParameter("form3Email"));
				persona.setRfc(req.getParameter("AltaPersona_rfc"));
				persona.setCargo(req.getParameter("AltaPersona_Cargo"));
				persona.setTituloProfesional(req.getParameter("AltaPersona_TituloProfesional"));
				persona.setCedulaProfesional(req.getParameter("AltaPersona_CedulaProfesional"));
				persona.setUsuarioSkype(req.getParameter("AltaPersona_UsuarioSkype"));
				persona.setIdEmpresa(new Long(req.getParameter("AltaPersona_empresa")));
				persona.setFotografia(path);
				persona.setVisible(true);
				
				try 
				{
					
					personaEndpoint.insertPersona(persona);
					System.out.println("se inserto la persona");
					//System.out.println("AltaPersona_Nombre "+req.getParameter("AltaPersona_Nombre"));
					//System.out.println("AltaPersona_Direccion "+req.getParameter("AltaPersona_Direccion"));
					//System.out.println("AltaPersona_UsuarioSkype "+req.getParameter("AltaPersona_UsuarioSkype"));
					
					Multimedia multimedia = new Multimedia();
			    	MultimediaEndpoint multimediaEndpoint = new MultimediaEndpoint();
			    
			    	multimedia.setIdMultimedia(GetIds.getIdMultimedia()+1);
			    	multimedia.setIdReferencia(id_Persona);
			    	multimedia.setPath(path);//blobkey
			    	multimedia.setTipoArchivo(Constants.imagen);//imagen o video o file
			    	multimedia.setTipoReferencia(Constants.CatalogoPersonas);
			    	multimedia.setFecha(UtilFechas.getFecha());
			    	multimedia.setDescripcion("Foto Persona");
			    	multimedia.setFormato(new Long(0));
			    	
			    	try 
			    	{
						multimediaEndpoint.insertMultimedia(multimedia);
						System.out.println("se inserto path persona en multimedia");
						out.write(new Gson().toJson("se inserto correctamente la foto de la persona"));
					} 
			    	catch (Exception e) 
			    	{
						try 
						{
							personaEndpoint.removePersona(id_Persona);
							System.out.println("se borro la persona insertada");
						} catch (Exception e2) 
						{
							System.out.println("No se pudo borrar la persona ya insertada  error: " + e2.getMessage());
						}
						error += e.getMessage();
						System.out.println("hubo un error al insertar path persona en multimedia");
						out.write(new Gson().toJson(error)); 		
					}
			    	resp.sendRedirect("/views/ListaPersonas.html");
				}
				catch (Exception e) 
				{
					error += e.getMessage();
					System.out.println("hubo un error al insertar la multimedia " + error);
				}
					
			}else
			{
				error += "No ha insertado imagen ";
				out.write(new Gson().toJson(error));
				System.out.println("No ha insertado imagen "+ error);
			}
		}else
		{
			error += "El correo ya existe en la base de datos";
			out.write(new Gson().toJson(error)); 		
			System.out.println("El correo ya existe en la base de datos "+ error);
		}
	}
}
