package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.model.Multimedia;
import com.csgit.cao.model.MultimediaEndpoint;
import com.csgit.cao.model.Proyecto;
import com.csgit.cao.model.ProyectoEndpoint;
import com.csgit.entity.DependenciasEntity;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.gson.Gson;

public class AltaLogotipo extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
	private Multimedia multimedia;
	private MultimediaEndpoint multimediaEndpoint;
	private String path;
	private Long idProyecto;
	private Long idMultimedia;
	

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
		String error = "";
		if(req.getParameter("objectJson")!=null){
			resp.setContentType("text/json");
			System.out.println(req.getParameter("objectJson"));
			String parametro = req.getParameter("objectJson");
			if(parametro.contains("peticion")){   //validamos si es la peticion para el catalogo de proyectos 
				out.write(new Gson().toJson(obtenerProyectos()));
				return;
			}
		}else{
			idProyecto = Long.parseLong(req.getParameter("altaLogotipo_proyectoselect"));
			System.out.print(idProyecto);
			if(idProyecto!=0){
				List<BlobKey> blobs_dependencia = blobstoreService.getUploads(req).get("myFile_dependencia");
				if(blobs_dependencia != null){
					idMultimedia = GetIds.getIdMultimedia();
					List<BlobKey> blobs_secretaria = blobstoreService.getUploads(req).get("myFile_secretaria");
					List<BlobKey> blobs_iuyet = blobstoreService.getUploads(req).get("myFile_iuyet");
					List<BlobKey> blobs_adicional = blobstoreService.getUploads(req).get("myFile_adicional");
					guardad(blobs_dependencia, resp, "Dependencia");
					guardad(blobs_secretaria, resp, "Secretaria");
					guardad(blobs_iuyet, resp, "Iuyet");
					guardad(blobs_adicional, resp, "Adicional");
				}else{
					error += "No ha insertado imagen ";
					out.write(new Gson().toJson(error)); 
				}
				
			}else{
				out.write(new Gson().toJson(null));
			}
		}
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
	
	public static String getFecha(){
		String fecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
		return fecha;
	}
	
	public boolean guardad(List<BlobKey> blobs, HttpServletResponse resp, String descripcion){
		if(blobs != null){
			BlobKey blobKey = blobs.get(0);
			
			multimedia = new Multimedia();
			multimediaEndpoint = new MultimediaEndpoint();
			
			path = blobKey.getKeyString();

			ImagesService imagesService = ImagesServiceFactory
					.getImagesService();
			ServingUrlOptions servingOptions = ServingUrlOptions.Builder
					.withBlobKey(blobKey);

//			String servingUrl = imagesService.getServingUrl(servingOptions);

			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("application/json");
			
			multimedia.setIdMultimedia(idMultimedia+1);
	    	multimedia.setIdReferencia(idProyecto);//idProyecto
	    	multimedia.setPath(path);//blobkey
	    	multimedia.setTipoArchivo(Constants.imagen);//imagen o video o file
	    	multimedia.setTipoReferencia(Constants.Logotipos);//logotipo=3 
	    	multimedia.setFecha(getFecha());
	    	multimedia.setDescripcion(descripcion);
	    	multimedia.setFormato(new Long(0));
		
			try {
				multimediaEndpoint.insertMultimedia(multimedia);
				idMultimedia ++;
				System.out.println("se inserto la multimedia: "+descripcion+" correctamente con idMultimedia: " + idMultimedia);
				return true;
//				out.write(new Gson().toJson("se inserto la multimedia correctamente"));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("hubo un error al insertar la multimedia: "+descripcion+" correctamente" + e);
				return false; 
			}
		}else{
			return false;
		}
	}
}
