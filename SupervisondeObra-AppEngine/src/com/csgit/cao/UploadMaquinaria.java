package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.business.MaquinariaBusiness;
import com.csgit.cao.model.Cat_Tipo_Maquinaria;
import com.csgit.cao.model.Maquinaria;
import com.csgit.cao.model.MaquinariaEndpoint;
import com.csgit.cao.model.Multimedia;
import com.csgit.cao.model.MultimediaEndpoint;
import com.csgit.util.UtilFechas;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gson.Gson;

public class UploadMaquinaria extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
	private Maquinaria maquinaria;
	private MaquinariaEndpoint maquinariaEndpoint;
	private String path;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = res.getWriter();
		String error = "";
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/json; charset=UTF-8");

		if(req.getParameter("objectJson")!=null){
			System.out.println(req.getParameter("objectJson"));
			String parametro = req.getParameter("objectJson");
			// si el objeto es igual al string peticion entonces regresamos el
			// catalogo de maquinaria
			if (parametro.contains("peticion")) { // validamos si es la peticion
													// para el catalogo de maquinaria
//				res.setContentType("text/json");
				System.out.println("es una peticion");
				out.write(new Gson().toJson(obtenerCatTipoMaquinaria())); 
				return;
			} 
		}else{	
			if(!MaquinariaBusiness.existenteMaquinaria(req.getParameter("AltaMaquinaria_Nombre"))){
				List<BlobKey> blobs = blobstoreService.getUploads(req).get("myFile");
				if(blobs != null){
					BlobKey blobKey = blobs.get(0);
					
					maquinaria = new Maquinaria();
					maquinariaEndpoint = new MaquinariaEndpoint();
					path = blobKey.getKeyString();
					
					res.setStatus(HttpServletResponse.SC_OK);
//					res.setContentType("application/json");

					// insertaMaquinaria();
					Long id_Maquinaria = GetIds.getIdMaquinaria() + 1;
					maquinaria.setId_Maquinaria(id_Maquinaria);
					maquinaria.setId_tipo_Maquinaria(new Long(req.getParameter("TipoMaquinaria1")));
					maquinaria.setNombre(req.getParameter("AltaMaquinaria_Nombre"));
					maquinaria.setDescripcion(req.getParameter("AltaMaquinaria_Descripcion"));
					maquinaria.setVisible(true);
					
					try {
						maquinariaEndpoint.insertMaquinaria(maquinaria);
						System.out.println("se inserto correctamente la maquinaria");
						
						Multimedia multimedia = new Multimedia();
				    	MultimediaEndpoint multimediaEndpoint = new MultimediaEndpoint();
				    
				    	multimedia.setIdMultimedia(GetIds.getIdMultimedia()+1);
				    	multimedia.setIdReferencia(id_Maquinaria);//idMaquinaria
				    	multimedia.setPath(path);//blobkey
				    	multimedia.setTipoArchivo(Constants.imagen);//imagen o video o file
				    	multimedia.setTipoReferencia(Constants.CatalogoMaquinaria);//si pertenece a proyecto =0, obra=1, CatalogoMaquinaria=2 
				    	multimedia.setFecha(UtilFechas.getFecha());
				    	multimedia.setDescripcion("Imagen Maquinaria");
				    	multimedia.setFormato(new Long(0));
					
						try {
							multimediaEndpoint.insertMultimedia(multimedia);
							System.out.println("se inserto path maquinaria en multimedia");
							res.sendRedirect("/views/ListaMaquinaria.html");
						} catch (Exception e) {
							// TODO: handle exception
							try {
								maquinariaEndpoint.removeMaquinaria(id_Maquinaria);
								System.out.println("se borro la maquinaria insertada");
							} catch (Exception e2) {
								// TODO: handle exception
								System.out.println("No se pudo borrar la maquinaria ya insertada  error: " + e2.getMessage());
							}
							error += e.getMessage();
							System.out.println("hubo un error al insertar path maquinaria en multimedia");
							out.write(new Gson().toJson(error)); 
							
						}
					} catch (Exception e) {
						// TODO: handle exception
						error += e.getMessage();
						System.out.println("hubo un error al insertar la maquinaria " + error);
						out.write(new Gson().toJson(error));
					}
				}else{
					error += "No ha insertado imagen ";
					out.write(new Gson().toJson(error)); 
				}
				
			}else{
				error += "El nombre de la maquinaria ya existe ";
				out.write(new Gson().toJson(error)); 
			}
		}
	}
	
	public Collection<Cat_Tipo_Maquinaria> obtenerCatTipoMaquinaria(){
		Collection<Cat_Tipo_Maquinaria>respuesta = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Cat_Tipo_Maquinaria aux_maquinaria = new Cat_Tipo_Maquinaria();
			if(i==0){
				aux_maquinaria.setId_tipo_Maquinaria(new Long(Constants.idPesada));
				aux_maquinaria.setTipo_Maquinaria(Constants.pesadaTexto);
			}else if(i==1){
				aux_maquinaria.setId_tipo_Maquinaria(new Long(Constants.idLigera));
				aux_maquinaria.setTipo_Maquinaria(Constants.ligeraTexto);
			}else if(i==2){
				aux_maquinaria.setId_tipo_Maquinaria(new Long(Constants.idEquipo));
				aux_maquinaria.setTipo_Maquinaria(Constants.equipoTexto);
			}
			respuesta.add(aux_maquinaria);
		}
		return respuesta;
	}
	
}
