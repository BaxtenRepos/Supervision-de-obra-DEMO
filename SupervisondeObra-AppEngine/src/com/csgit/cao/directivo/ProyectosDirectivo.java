package com.csgit.cao.directivo;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csgit.cao.model.Obra;
import com.csgit.cao.model.Proyecto;
import com.csgit.cao.model.ProyectoDelDirectivo;
import com.csgit.cao.model.ProyectoDelDirectivoEndpoint;
import com.csgit.cao.model.Ubicaciones;
import com.csgit.cao.model.UbicacionesEndpoint;
import com.csgit.entity.LoginEntity;
import com.csgit.entity.ObrasUbicacionesEntity;
import com.csgit.entity.UbicacionesDirectivo;
import com.csgit.util.ObraUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class ProyectosDirectivo extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/json");
		LoginEntity proyecto;
		String error = "";
		String conversio;

		System.out.println("Tipo de Peticion: "+req.getParameter("objectJson"));
		//log.warning("Tipo de Peticion objectJson: "+req.getParameter("objectJson"));
		
		String parametro = req.getParameter("objectJson");
		if(parametro.contains("directivo")){
			String directivo[] = parametro.split("-");
			
			long idDirectivo = Long.parseLong(directivo[1]);
			CollectionResponse<ProyectoDelDirectivo> pd = new ProyectoDelDirectivoEndpoint().listProyectoDelDirectivo(null, null, idDirectivo);
			HttpSession session = req.getSession(true);
	//		session.setAttribute("proyectosdirectivo", pd);
			ArrayList<UbicacionesDirectivo> ubicacionesDirectivo = new ArrayList<>();
			UbicacionesDirectivo ud = new UbicacionesDirectivo();
			for (ProyectoDelDirectivo elemento : pd.getItems()) {
			
				System.out.println(elemento.getProyecto());
				Proyecto proyect = elemento.getProyecto();
				ud.setIdProyecto(proyect.getId_Proyecto());
				ud.setNombreCortoProyecto(proyect.getNombre_corto());
				ud.setUbicacionProyecto(new UbicacionesEndpoint().getUbicaciones(proyect.getId_ubicacion()));
				//ArrayList<ObraUtil>obraUtil = elemento.getObras();
				ArrayList< ObrasUbicacionesEntity>obrasUbicacionesEntity = new ArrayList<>();
				if(elemento.getObras()!=null){
					System.out.println(elemento.getObras().size());
				
				for (ObraUtil obras : elemento.getObras()) {
					
					ObrasUbicacionesEntity oue = new ObrasUbicacionesEntity();
					Obra obra = obras.getObra();
					oue.setUbicacionObra(new UbicacionesEndpoint().getUbicaciones(obra.getId_ubicacion()));
					oue.setNombreObra(obra.getNombre());
					oue.setIdObra(obra.getId_Obra());
					obrasUbicacionesEntity.add(oue);
					
					
				}
				ud.setObra(obrasUbicacionesEntity);
			}
				ubicacionesDirectivo.add(ud);
			}
			out.write(new Gson().toJson(ubicacionesDirectivo));
			System.out.println();
			
			
			
			
			
		}else if(parametro.contains("verificaProyectos")){
			
			
		}
		
	}
	
	

}
