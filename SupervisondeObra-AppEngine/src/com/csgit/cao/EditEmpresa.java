package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.business.EmpresaBusiness;
import com.csgit.cao.model.Empresa;
import com.csgit.cao.model.EmpresaEndpoint;
import com.csgit.cao.model.Multimedia;
import com.csgit.cao.model.MultimediaEndpoint;
import com.csgit.entity.DependenciasEntity;
import com.csgit.util.UtilFechas;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gson.Gson;

public class EditEmpresa extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Logger _log = Logger.getLogger(EditEmpresa.class.getName());
	
	private EmpresaEndpoint empresaEndpoint = new EmpresaEndpoint();
	EmpresaBusiness empresaBusiness;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	

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
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		if(req.getParameter("objectJson")!=null)
		{
			System.out.println(req.getParameter("objectJson"));
			String parametro = req.getParameter("objectJson");
			
			if(parametro.contains("peticion")){   //validamos si es la peticion para el catalogo de proyectos 
				out.write(new Gson().toJson(obtenerEmpresas()));
				return;
			}else if(parametro.contains("opcionempresa:")){  
				String[] split= parametro.split(":");
				Long idEmpresa = Long.parseLong(split[1].replace('"', ' ').trim());
				System.out.println("voy a devolver la empresa con id = " + idEmpresa);
				out.write(new Gson().toJson(obtenerEmpresa(idEmpresa)));
				return;
			}else if(parametro.contains("opciondependencias")){  
				out.write(new Gson().toJson(obtenerTipoEmpresa()));
				return;
			}			
		}	
		else
		{
			empresaBusiness = new EmpresaBusiness();
			Empresa empresa = new Empresa();
			empresa.setId_Empresa(new Long(req.getParameter("editEmpresa_Id")));
			empresa.setRFC(req.getParameter("editEmpresa_rfc"));
			empresa.setNombre(req.getParameter("editEmpresa_nombre"));
			empresa.setSiglas(req.getParameter("editEmpresa_siglas"));
			empresa.setIMSS(req.getParameter("editEmpresa_imss"));
			empresa.setCalle(req.getParameter("editEmpresa_calle"));
			empresa.setNum_Ext(req.getParameter("editEmpresa_num_ext"));
			empresa.setNum_Int(req.getParameter("editEmpresa_num_int"));
			empresa.setColonia(req.getParameter("editEmpresa_colonia"));
			empresa.setDel_o_Mun(req.getParameter("editEmpresa_del_mun"));
			empresa.setEntidad(req.getParameter("editEmpresa_entidad"));
			empresa.setCodi_Postal(Integer.parseInt(req.getParameter("editEmpresa_codigo_postal")));
			empresa.setId_tipo_empresa(new Long(req.getParameter("editEmpresa_selectempresa")));
			empresa.setVisible(true);
			
			if(!EmpresaBusiness.existente(empresa.getNombre(), empresa.getId_Empresa()))
			{
				try 
				{
					empresaEndpoint.updateEmpresa(empresa);
					System.out.println("\n \n Se editaron correctamente datos de la Empresa "+empresa.getNombre() +"\n \n ");
					int isNew = Integer.parseInt(req.getParameter("isNew"));
					
					List<BlobKey> blobs = blobstoreService.getUploads(req).get("myFile_editempresa");
					if (blobs != null && isNew == 1) 
					{
						BlobKey blobKey = blobs.get(0);
						resp.setStatus(HttpServletResponse.SC_OK);
						String path = blobKey.getKeyString();
						_log.info("entro para editar el blob key: " + path);
						actualizaPathLogo(empresa.getId_Empresa(), path);
						resp.sendRedirect("/views/ListaEmpresas.html");
					} 
					else // No se inserto imagen
					{
						_log.info("No va a editar el blob key: ");
						System.out.println(" No se actualiza imagen");
						resp.sendRedirect("/views/ListaEmpresas.html");
					}		
				} 
				catch (Exception e) 
				{
					System.out.println("Hubo un error al editar la empresa "+e);
					resp.sendRedirect("/views/ListaEmpresas.html");
				}
			}
			else 
			{
				System.out.println("Empresa existente: "+empresa.getNombre());
				resp.sendRedirect("/views/ListaEmpresas.html");
			}
		}
	}
	
	private Collection<DependenciasEntity> obtenerTipoEmpresa() {
		// TODO Auto-generated method stub
		Collection<DependenciasEntity>respuesta = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			DependenciasEntity aux_dependencia = new DependenciasEntity();
			if(i==0){
				aux_dependencia.setId(new Long(Constants.idContratista));
				aux_dependencia.setTipoEmpresa(Constants.contratistaTexto);
			}else if(i==1){
				aux_dependencia.setId(new Long(Constants.idSupervisora));
				aux_dependencia.setTipoEmpresa(Constants.supervisoraTexto);
			}else if(i==2){
				aux_dependencia.setId(new Long(Constants.idDependencia));
				aux_dependencia.setTipoEmpresa(Constants.dependenciaTexto);
			}else if(i==3){
				aux_dependencia.setId(new Long(Constants.idParticular));
				aux_dependencia.setTipoEmpresa(Constants.particularTexto);
			}else if(i==4){
				aux_dependencia.setId(new Long(Constants.idSecretaria));
				aux_dependencia.setTipoEmpresa(Constants.secretariaTexto);
			}else if(i==5){
				aux_dependencia.setId(new Long(Constants.idGobierno));
				aux_dependencia.setTipoEmpresa(Constants.GobiernoTexto);
			}
			respuesta.add(aux_dependencia);
		}
		return respuesta;
	}
	private Empresa obtenerEmpresa(Long idEmpresa) {
		CollectionResponse<Empresa> lista =new EmpresaEndpoint().listEmpresa(null,null);//traemos la lista de empresas insertadas
		Collection<Empresa> lista1=lista.getItems();
		CollectionResponse<Multimedia> listaRes1 = new MultimediaEndpoint().listMultimedia(null, null, new Long(0), new Long(0), new Long(0));
		Collection<Multimedia> listaMultimedia = listaRes1.getItems();
		
		
		
		Empresa regresar = new Empresa();
	    for (Empresa elem : lista1) {
	    	if(elem.getId_Empresa() == idEmpresa){
	    		 regresar = elem;
	    		 regresar = obtenerPathImagen(idEmpresa,listaMultimedia, regresar);
	    		 break;
	    	}
	    }
		return regresar;
	}
	
	
	private Empresa obtenerPathImagen(Long idEmpresa,Collection<Multimedia> listaMultimedia,Empresa aux ) 
	{
		for (Multimedia multimedia : listaMultimedia) {
			if(idEmpresa.longValue() == multimedia.getIdReferencia().longValue() && multimedia.getTipoReferencia() == Constants.CatalogoEmpresas)
			{
				aux.setLogo(Constants.URL_GET_BLOB_STORE + multimedia.getPath());
				break;
			}
		}
		return aux;
	}
	

	public Collection<DependenciasEntity> obtenerEmpresas(){
		CollectionResponse<Empresa> lista =new EmpresaEndpoint().listEmpresa(null,null);//traemos la lista de empresas insertadas
		Collection<Empresa> lista1=lista.getItems();
		Collection<DependenciasEntity>regresar = new ArrayList<>();
	    for (Empresa elem : lista1) {
	    	if(elem.isVisible()){
	    		DependenciasEntity dp = new DependenciasEntity();
	    		dp.setNombre(elem.getNombre());
	    		dp.setId(elem.getId_Empresa());
	    		regresar.add(dp);
	    	}	    
    	}
		return regresar;
	}
	
	private void actualizaPathLogo(Long id_Empresa, String pathN) 
	{		
		CollectionResponse<Multimedia> listaResp = new MultimediaEndpoint().listMultimedia(null, null, new Long(0), new Long(0), new Long(0));
		Collection<Multimedia> listaMultimedia = listaResp.getItems();		
		MultimediaEndpoint multimediaEndpoint = new MultimediaEndpoint();
		Multimedia multimedia2 = new Multimedia();
		//System.out.println("id_Empresa " +id_Empresa+" pathN= "+pathN);	

		for (Multimedia multimedia : listaMultimedia) 
		{
			if(id_Empresa.longValue() == multimedia.getIdReferencia().longValue() && multimedia.getTipoReferencia() == Constants.CatalogoEmpresas)
			{
				multimedia2.setPath(pathN);
				multimedia2.setFecha(UtilFechas.getFecha());
				multimedia2.setIdMultimedia(multimedia.getIdMultimedia());
		    	multimedia2.setIdReferencia(id_Empresa);
		    	multimedia2.setTipoArchivo(Constants.imagen);//imagen o video o file
		    	multimedia2.setTipoReferencia(Constants.CatalogoEmpresas);
		    	multimedia2.setDescripcion("Logo Empresa");
		    	multimedia2.setFormato(new Long(0));
				try
				{
					multimediaEndpoint.updateMultimedia(multimedia2);
					System.out.println("Actualiza Logo");
				}catch (Exception e) 
				{
					System.out.println("Hubo un error al editar la multimedia "+e);
				}
				break;
			}//Todas las Empresas tienen Logo, validacion hecha en alta de Empresa
		}
	}
	
	
}
