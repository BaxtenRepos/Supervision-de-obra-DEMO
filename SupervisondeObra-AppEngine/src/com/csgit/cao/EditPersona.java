package com.csgit.cao;

import java.io.IOException;
import java.util.Collection;
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
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class EditPersona extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	private String path;
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException 
	{
		
		resp.setStatus(HttpServletResponse.SC_OK);
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json; charset=UTF-8");
		Persona persona = new Persona();
		PersonaEndpoint personaEndpoint = new PersonaEndpoint();
		
		persona.setId_Persona(new Long(req.getParameter("EditPersona_Id")));
		persona.setNombre(req.getParameter("EditPersona_Nombre"));
		persona.setAp_Paterno(req.getParameter("EditPersona_ApPaterno"));
		persona.setAp_Materno(req.getParameter("EditPersona_ApMaterno"));
		persona.setIniciales(req.getParameter("EditPersona_Iniciales"));
		persona.setFecha_Nacimiento(req.getParameter("EditPersona_FechaNaci"));
		persona.setDireccion(req.getParameter("EditPersona_Direccion"));
		persona.setTelefonos(req.getParameter("EditPersona_Telefono"));
		persona.setRadios(req.getParameter("EditPersona_Radio"));
		persona.setEmails(req.getParameter("form3Email"));
		persona.setRfc(req.getParameter("EditPersona_rfc"));
		persona.setCargo(req.getParameter("EditPersona_Cargo"));
		persona.setTituloProfesional(req.getParameter("EditPersona_TituloProfesional"));
		persona.setCedulaProfesional(req.getParameter("EditPersona_CedulaProfesional"));
		persona.setUsuarioSkype(req.getParameter("EditPersona_UsuarioSkype"));
		persona.setIdEmpresa(new Long(req.getParameter("EditPersona_empresa")));
		persona.setVisible(true);
		int isNew = Integer.parseInt(req.getParameter("isNew"));
		
		if(!PersonalBusiness.existenteCorreo(persona.getEmails(),persona.getId_Persona())) //Validar que otra Persona no tenga el correo ingresado
		{
			try 
			{
				System.out.println("Guardado "+persona.getNombre()+" -Empresa:  "+persona.getIdEmpresa());
				personaEndpoint.updatePersona(persona);
				System.out.println("\n \n Se edito correctamente datos de persona\n \n ");
				
				List<BlobKey> blobs = blobstoreService.getUploads(req).get("myFile");
				if (blobs != null && isNew == 1) 
				{
					BlobKey blobKey = blobs.get(0);
					path = blobKey.getKeyString();
					actualizaPathImagen(persona.getId_Persona(), path);
					resp.sendRedirect("/views/ListaPersonas.html");
				} 
				else // No se inserto imagen
				{
					System.out.println(" No se actualiza imagen");
					resp.sendRedirect("/views/ListaPersonas.html");
				}
				
			} catch (Exception e) {
				System.out.println("    Hubo un error al editar la persona");
				resp.sendRedirect("/views/ListaPersonas.html");
			}
		}
		else
		{
			System.out.println(" Correo Existente");
			resp.sendRedirect("/views/ListaPersonas.html");		
		}
	}

	
	private void actualizaPathImagen(Long id_Persona, String pathN) 
	{
		//System.out.println("id_Persona " +id_Persona+" pathN= "+pathN);
		CollectionResponse<Multimedia> listaResp = new MultimediaEndpoint().listMultimedia(null, null, new Long(0), new Long(0), new Long(0));
		Collection<Multimedia> listaMultimedia = listaResp.getItems();		
		MultimediaEndpoint multimediaEndpoint = new MultimediaEndpoint();
		Multimedia multimedia2 = new Multimedia();
		for (Multimedia multimedia : listaMultimedia) 
		{
			if(id_Persona.longValue() == multimedia.getIdReferencia().longValue() && multimedia.getTipoReferencia() == Constants.CatalogoPersonas)
			{
				multimedia2.setPath(pathN);
				multimedia2.setFecha(UtilFechas.getFecha());
				multimedia2.setIdMultimedia(multimedia.getIdMultimedia());
		    	multimedia2.setIdReferencia(id_Persona);
		    	multimedia2.setTipoArchivo(Constants.imagen);//imagen o video o file
		    	multimedia2.setTipoReferencia(Constants.CatalogoPersonas);
		    	multimedia2.setDescripcion("Foto Persona");
		    	multimedia2.setFormato(new Long(0));
				try
				{
					multimediaEndpoint.updateMultimedia(multimedia2);					
				}catch (Exception e) 
				{
					System.out.println("    Hubo un error al editar la multimedia "+e);
				}
				break;
			}//Todas las personas tienen foto, validacion hecha en alta de persona
		}
	}
}
