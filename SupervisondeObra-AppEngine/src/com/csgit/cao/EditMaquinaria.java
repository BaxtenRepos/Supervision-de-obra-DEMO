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
import com.csgit.entity.MaquinariaEntity;
import com.csgit.util.UtilFechas;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gson.Gson;

public class EditMaquinaria extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String error = "";
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json; charset=UTF-8");
		
		if(req.getParameter("objectJson")!=null){
			System.out.println(req.getParameter("objectJson"));
			String parametro = req.getParameter("objectJson");
			// si el objeto es igual al string peticion entonces regresamos el tipo de maquinaria
			if (parametro.contains("opcionmaquinaria:")) {
				String[] split= parametro.split(":");
				Long id_Maquinaria = Long.parseLong(split[1].replace('"', ' ').trim());
				System.out.println("voy a devolver la maquinaria con idMaquinaria = " + id_Maquinaria);
				out.write(new Gson().toJson(obtenerMaquinarias(id_Maquinaria)));
			}else if(parametro.contains("opciontipoMaquinaria")){
				out.write(new Gson().toJson(obtenerCatTipoMaquinaria())); 
				return;
			}
		}else {
			resp.setStatus(HttpServletResponse.SC_OK);
			Maquinaria maquinaria = new Maquinaria();
			MaquinariaEndpoint maquinariaEndpoint = new MaquinariaEndpoint();
			maquinaria.setId_Maquinaria(new Long(req.getParameter("EditMaquinaria_Id")));
			maquinaria.setId_tipo_Maquinaria(new Long(req.getParameter("TipoMaquinaria1")));
			maquinaria.setNombre(req.getParameter("EditMaquinaria_Nombre"));
			maquinaria.setDescripcion(req.getParameter("EditMaquinaria_Descripcion"));
			maquinaria.setVisible(true);
			int isNew = Integer.parseInt(req.getParameter("isNew"));
			if(!MaquinariaBusiness.existenteMaquinaria(maquinaria.getNombre(), maquinaria.getId_Maquinaria())){
				try {
					maquinariaEndpoint.updateMaquinaria(maquinaria);
					
					System.out.println("\n \n Se editaron correctamente datos de la Maquinaria: "+maquinaria.getNombre() +"\n \n ");
					
					List<BlobKey> blobs = blobstoreService.getUploads(req).get("myFile");
					if (blobs != null && isNew == 1){
						BlobKey blobKey = blobs.get(0);
						path = blobKey.getKeyString();
						actualizaPathLogo(maquinaria.getId_Maquinaria(), path);
						resp.sendRedirect("/views/ListaMaquinaria.html");
					} else {// No se inserto imagen
						System.out.println(" No se actualiza imagen");
						resp.sendRedirect("/views/ListaMaquinaria.html");
					}
				} catch (Exception e) {
					System.out.println("Hubo un error al editar la maquinaria "+e);
					resp.sendRedirect("/views/EditMaquinaria.jsp");
				}
			}else{
				System.out.println("Maquinaria existente: "+maquinaria.getNombre());
				error += "Nombre de la maquinaría existente";
				out.write(new Gson().toJson(error)); 
			}
		}
	}
	
	private MaquinariaEntity  obtenerMaquinarias(Long idMaquinaria) {
		CollectionResponse<Maquinaria> listaRes = new MaquinariaEndpoint().listMaquinaria(null, null);
		Collection<Maquinaria> lista = listaRes.getItems();
		CollectionResponse<Multimedia> listaRes1 = new MultimediaEndpoint().listMultimedia(null, null, new Long(0), new Long(0), new Long(0));
		Collection<Multimedia> listaMultimedia = listaRes1.getItems();
		
		MaquinariaEntity aux = new MaquinariaEntity();
		
		for (Maquinaria elem : lista) {
			if(elem.getId_Maquinaria() == idMaquinaria){
				aux.setId_Maquinaria(elem.getId_Maquinaria());
				aux.setNombre(elem.getNombre());
				aux.setDescripcion(elem.getDescripcion());
				aux.setId_tipo_Maquinaria(elem.getId_tipo_Maquinaria());
				aux = obtenerPathImagen(elem.getId_Maquinaria(),listaMultimedia, aux);
				break;
			}
		}
		return aux;
	}

	private MaquinariaEntity obtenerPathImagen(Long id_Maquinaria,Collection<Multimedia> listaMultimedia,MaquinariaEntity aux ) {
		// TODO Auto-generated method stub
		for (Multimedia multimedia : listaMultimedia) {
			if(id_Maquinaria == multimedia.getIdReferencia() && multimedia.getTipoReferencia() == Constants.CatalogoMaquinaria){
				aux.setPath_Imagen(Constants.URL_GET_BLOB_STORE + multimedia.getPath());
				aux.setIdMultimedia(multimedia.getIdMultimedia());
				break;
			}
		}
		return aux;
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
	
	private void actualizaPathLogo(Long id_Maquinaria, String pathN){		
		CollectionResponse<Multimedia> listaResp = new MultimediaEndpoint().listMultimedia(null, null, new Long(0), new Long(0), new Long(0));
		Collection<Multimedia> listaMultimedia = listaResp.getItems();		
		MultimediaEndpoint multimediaEndpoint = new MultimediaEndpoint();
		Multimedia multimedia2 = new Multimedia();
		//System.out.println("id_Empresa " +id_Empresa+" pathN= "+pathN);	

		for (Multimedia multimedia : listaMultimedia){
			if(id_Maquinaria.longValue() == multimedia.getIdReferencia().longValue() && multimedia.getTipoReferencia() == Constants.CatalogoMaquinaria){
				multimedia2.setPath(pathN);
				multimedia2.setFecha(UtilFechas.getFecha());
				multimedia2.setIdMultimedia(multimedia.getIdMultimedia());
		    	multimedia2.setIdReferencia(id_Maquinaria);
		    	multimedia2.setTipoArchivo(Constants.imagen);//imagen o video o file
		    	multimedia2.setTipoReferencia(Constants.CatalogoMaquinaria);
		    	multimedia2.setDescripcion("Imagen Maquinaria");
		    	multimedia2.setFormato(new Long(0));
				try{
					multimediaEndpoint.updateMultimedia(multimedia2);
					System.out.println("Actualiza Logo");
				}catch (Exception e){
					System.out.println("Hubo un error al editar la multimedia "+e);
				}
				break;
			}//Todas las Empresas tienen Logo, validacion hecha en alta de Empresa
		}
	}
}
