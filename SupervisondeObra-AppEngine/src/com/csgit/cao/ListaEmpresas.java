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

import com.csgit.cao.business.EmpresaBusiness;
import com.csgit.cao.model.Empresa;
import com.csgit.cao.model.EmpresaEndpoint;
import com.csgit.cao.model.Multimedia;
import com.csgit.cao.model.MultimediaEndpoint;
import com.csgit.cao.model.Obra;
import com.csgit.cao.model.ObraEndpoint;
import com.csgit.cao.model.Persona;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.cao.model.Proyecto;
import com.csgit.cao.model.ProyectoEndpoint;
import com.csgit.entity.DependenciasEntity;
import com.csgit.entity.auxEntity;
import com.csgit.entity.borrarEntity;
import com.csgit.entity.empresaEntity;
import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class ListaEmpresas extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

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
//		String error;
		if(parametro.contains("peticion")){   //validamos si es la peticion para el catalogo de proyectos 
			out.write(new Gson().toJson(obtenerEmpresas()));
			return;
		}else{
			borrarEntity empresasborrar = (borrarEntity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new borrarEntity());
			ArrayList<auxEntity> idEmpresasBorrar = empresasborrar.getBorrar();
			CollectionResponse<Proyecto> lista = new ProyectoEndpoint().listProyecto(null, null);
			Collection<Proyecto>lista1 = lista.getItems();
			CollectionResponse<Obra> listaObra = new ObraEndpoint().listObra(null, null, new Long(0), new Long(0), "");
			Collection<Obra>listaObra1 = listaObra.getItems();
			CollectionResponse<Persona> listaPersonas = new PersonaEndpoint().listPersona(null, null);
			Collection<Persona>listaPersonas1 = listaPersonas.getItems();
			
			EmpresaEndpoint empresaEndpoint = new EmpresaEndpoint();
			ArrayList<auxEntity> borrados = new ArrayList<auxEntity>();
			
			for (auxEntity idEmpresas : idEmpresasBorrar) {
				if(!EmpresaBusiness.isEmpresaProyectos(idEmpresas.getId(), lista1)){
					if(!EmpresaBusiness.isEmpresaObras(idEmpresas.getId(), listaObra1)){
						if(!EmpresaBusiness.isEmpresaPersonas(idEmpresas.getId(), listaPersonas1)){
//							empresaEndpoint.removeEmpresa(Long.valueOf(idEmpresas));
							Empresa aux_empresa = obtenerEmpresa(idEmpresas.getId());
							Empresa empresa = new Empresa();
							empresa.setId_Empresa(aux_empresa.getId_Empresa());
							empresa.setRFC(aux_empresa.getRFC());
							empresa.setNombre(aux_empresa.getNombre());
							empresa.setSiglas(aux_empresa.getSiglas());
							empresa.setIMSS(aux_empresa.getIMSS());
							empresa.setCalle(aux_empresa.getCalle());
							empresa.setNum_Ext(aux_empresa.getNum_Ext());
							empresa.setNum_Int(aux_empresa.getNum_Int());
							empresa.setColonia(aux_empresa.getColonia());
							empresa.setDel_o_Mun(aux_empresa.getDel_o_Mun());
							empresa.setEntidad(aux_empresa.getEntidad());
							empresa.setCodi_Postal(aux_empresa.getCodi_Postal());
							empresa.setId_tipo_empresa(aux_empresa.getId_tipo_empresa());
							empresa.setVisible(false);
							try {
								empresaEndpoint.updateEmpresa(empresa);
								borrados.add(idEmpresas);
							} catch (Exception e) {
								// TODO: handle exception
								out.write(new Gson().toJson("ha ocurrido un error con la empresa "+empresa.getNombre()+
										": " + e.getMessage()));
							}
							
						}else{
//							out.write(new Gson().toJson("No se puede borrar la empresa, un usuario pertenece a esta empresa"));
						}
					}else{
//						out.write(new Gson().toJson("No se puede borrar la empresa, pertenece a una obra"));
					}
				}else{
//					out.write(new Gson().toJson("No se puede borrar la empresa, pertenece a un proyecto"));
				}
			}
			out.write(new Gson().toJson(borrados));
			return;
		}
 	}
	
public Collection<DependenciasEntity> obtenerDependencias(){
		
		CollectionResponse lista;
		Collection<Empresa>lista1;
		//Collection<DependenciasEntity>regresar = new ArrayList<>();
		 Set regresar = new TreeSet();
		//Empresa empresa = new Empresa();
		//EmpresaEndpoint empresaendpoint = new EmpresaEndpoint();
				
		lista =new EmpresaEndpoint().listEmpresa(null, null);// traemos las empresas insertadas
		lista1=lista.getItems();
	
	    for (Empresa elem : lista1) {
	            		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	if(elem.isVisible())
	        	{
	        		DependenciasEntity dp = new DependenciasEntity();
	        		dp.setNombre(elem.getNombre());
	        		dp.setTipoEmpresa(elem.getId_Empresa().toString());
	        		regresar.add(dp);
	        	}
	        	
	        
	    }
		
			return regresar;
	}
	
	private Collection<empresaEntity> obtenerEmpresas()
	{
		// TODO Auto-generated method stub
		CollectionResponse<Empresa> listaRes = new EmpresaEndpoint().listEmpresa(null, null);
		Collection<Empresa> lista = listaRes.getItems();
	//	Collection<empresaEntity> regresar = new ArrayList<>();
		Set regresar = new TreeSet();
		CollectionResponse<Multimedia> listaRes1 = new MultimediaEndpoint().listMultimedia(null, null, new Long(0), new Long(0), new Long(0));
		Collection<Multimedia> listaMultimedia = listaRes1.getItems();
		
		for(Empresa emp : lista)
		{
			if(emp.isVisible()){
				empresaEntity empEnt = new empresaEntity();
				empEnt.setIdEmpresa(emp.getId_Empresa());
				empEnt.setRfc(emp.getRFC());
				empEnt.setNombre(emp.getNombre());
				empEnt.setSiglas(emp.getSiglas());
				empEnt.setCalle(emp.getCalle());
				empEnt.setNumExt(emp.getNum_Ext());
				empEnt.setNumInt(emp.getNum_Int());
				empEnt.setColonia(emp.getColonia());
				empEnt.setDelMun(emp.getDel_o_Mun());
				empEnt.setEntidad(emp.getEntidad());
				empEnt.setCodPostal(emp.getCodi_Postal().toString());
				empEnt.setDireccion();
				empEnt.setTipoEmpresa(emp.getId_tipo_empresa());
				empEnt.setPath_Imagen(obtenerPathImagen(emp.getId_Empresa(),listaMultimedia));
				regresar.add(empEnt);
			}
		}
		return regresar;
	}
	
	
	private String obtenerPathImagen(Long id_Empresa,Collection<Multimedia> listaMultimedia) 
	{
		String path = "";
		for (Multimedia multimedia : listaMultimedia) {
			if(id_Empresa == multimedia.getIdReferencia() && multimedia.getTipoReferencia() == Constants.CatalogoEmpresas)
			{
				path = Constants.URL_GET_BLOB_STORE + multimedia.getPath();
				break;
			}
		}
		return path;
	}
	
	private Empresa obtenerEmpresa(Long idEmpresa) {
		CollectionResponse<Empresa> lista =new EmpresaEndpoint().listEmpresa(null,null);//traemos la lista de empresas insertadas
		Collection<Empresa> lista1=lista.getItems();		
		
		Empresa regresar = new Empresa();
	    for (Empresa elem : lista1) {
	    	if(elem.getId_Empresa() == idEmpresa){
	    		 regresar = elem;
	    		 break;
	    	}
	    }
		return regresar;
	}
}
