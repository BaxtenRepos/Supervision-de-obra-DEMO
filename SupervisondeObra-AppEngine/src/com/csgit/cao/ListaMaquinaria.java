package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.model.Maquinaria;
import com.csgit.cao.model.MaquinariaEndpoint;
import com.csgit.cao.model.Multimedia;
import com.csgit.cao.model.MultimediaEndpoint;
import com.csgit.entity.MaquinariaEntity;
import com.csgit.entity.auxEntity;
import com.csgit.entity.borrarEntity;
import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class ListaMaquinaria extends HttpServlet{
	
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
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/json");
		String parametro = req.getParameter("objectJson");
		if(parametro.contains("peticion")){ 
			out.write(new Gson().toJson(obtenerMaquinarias()));
			return;
		}else{
			borrarEntity maquinariaborrar = (borrarEntity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new borrarEntity());
			ArrayList<auxEntity> idMaquinariaBorrar = maquinariaborrar.getBorrar();
			
			MaquinariaEndpoint maquinariaEndpoint = new MaquinariaEndpoint();
			ArrayList<auxEntity> borrados = new ArrayList<auxEntity>();
			
			for (auxEntity idMaquinaria : idMaquinariaBorrar) {
				Maquinaria aux_maquinaria = obtenerMaquinarias(idMaquinaria.getId());
				Maquinaria maquinaria = new Maquinaria();
				maquinaria.setId_Maquinaria(aux_maquinaria.getId_Maquinaria());
				maquinaria.setNombre(aux_maquinaria.getNombre());
				maquinaria.setDescripcion(aux_maquinaria.getDescripcion());
				maquinaria.setId_tipo_Maquinaria(aux_maquinaria.getId_tipo_Maquinaria());
				maquinaria.setVisible(false);
				try {
					maquinariaEndpoint.updateMaquinaria(maquinaria);
					borrados.add(idMaquinaria);
				} catch (Exception e) {
					// TODO: handle exception
					out.write(new Gson().toJson("ha ocurrido un error con la maquinaria "+maquinaria.getNombre()+
							": " + e.getMessage()));
				}
			}
			out.write(new Gson().toJson(borrados));
			return;
		}
	}

	private Collection<MaquinariaEntity>  obtenerMaquinarias() {
		CollectionResponse<Maquinaria> listaRes = new MaquinariaEndpoint().listMaquinaria(null, null);
		Collection<Maquinaria> lista = listaRes.getItems();
		CollectionResponse<Multimedia> listaRes1 = new MultimediaEndpoint().listMultimedia(null, null, new Long(0), new Long(0), new Long(0));
		Collection<Multimedia> listaMultimedia = listaRes1.getItems();
		
		Collection<MaquinariaEntity> regresar = new ArrayList<>();
		
		for (Maquinaria elem : lista) {
			if(elem.isVisible()){
				MaquinariaEntity aux = new MaquinariaEntity();
				aux.setId_Maquinaria(elem.getId_Maquinaria());
				aux.setNombre(elem.getNombre());
				aux.setDescripcion(elem.getDescripcion());
				aux.setTipo_Maquinaria(elem.getId_tipo_Maquinaria());
				aux.setPath_Imagen(obtenerPathImagen(elem.getId_Maquinaria(),listaMultimedia));
				regresar.add(aux);
			}
		}
		return regresar;
	}

	private String obtenerPathImagen(Long id_Maquinaria,Collection<Multimedia> listaMultimedia) {
		// TODO Auto-generated method stub
		String path = "";
		for (Multimedia multimedia : listaMultimedia) {
			if(id_Maquinaria == multimedia.getIdReferencia() && multimedia.getTipoReferencia() == Constants.CatalogoMaquinaria){
				path = Constants.URL_GET_BLOB_STORE + multimedia.getPath();
				break;
			}
		}
		return path;
	}
	
	private Maquinaria  obtenerMaquinarias(Long idMaquinaria) {
		CollectionResponse<Maquinaria> listaRes = new MaquinariaEndpoint().listMaquinaria(null, null);
		Collection<Maquinaria> lista = listaRes.getItems();
		
		Maquinaria regresar = new Maquinaria();
		for (Maquinaria elem : lista) {
			if(elem.getId_Maquinaria() == idMaquinaria){
				regresar = elem;
				break;
			}
		}
		return regresar;
	}
}
