package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.csgit.util.UtilFechas;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gson.Gson;


public class AltaEmpresa extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EmpresaEndpoint empresaEndpoint;
	EmpresaBusiness empresaBusiness;

public static final Logger _log = Logger.getLogger(XMPPAgentServlet.class.getName());
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
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String []elementos;	
		elementos = new String[]{Constants.contratistaTexto,Constants.dependenciaTexto,Constants.particularTexto,Constants.secretariaTexto,Constants.supervisoraTexto,Constants.GobiernoTexto};
		System.out.println(req.getParameter("objectJson"));
		String parametro = req.getParameter("objectJson");
		if(parametro!=null)
		{
			if(parametro.contains("peticion"))
			{   //validamos si es la peticion para el catalogo de empresa 
				System.out.println("es una peticion");
				out.write(new Gson().toJson(elementos)); 
				return;
			}	
		}		
		else
		{
//			req.setCharacterEncoding("UTF-8");
			empresaBusiness = new EmpresaBusiness();
			System.out.println("Guardar campos...Empresa "+req.getParameter("nombre"));
			if(!empresaBusiness.existente(req.getParameter("nombre")))
			{
				List<BlobKey> blobs = blobstoreService.getUploads(req).get("myFile_altaempresa");
				if(blobs!=null)
				{
					BlobKey blobKey = blobs.get(0);
					resp.setStatus(HttpServletResponse.SC_OK);
					Empresa empresas = new Empresa();
					empresaEndpoint = new EmpresaEndpoint();
					path = blobKey.getKeyString();
					
					Long idEmpresa = GetIds.getIdEmpresa()+1;
					
					empresas.setId_Empresa(idEmpresa);
					empresas.setRFC(req.getParameter("rfc"));
					empresas.setNombre(req.getParameter("nombre"));
					empresas.setSiglas(req.getParameter("siglas"));
					empresas.setIMSS(req.getParameter("imss"));
					empresas.setCalle(req.getParameter("calle"));
					empresas.setNum_Ext(req.getParameter("num_ext"));
					empresas.setNum_Int(req.getParameter("num_int"));
					empresas.setColonia(req.getParameter("colonia"));
					empresas.setDel_o_Mun(req.getParameter("del_mun"));
					empresas.setEntidad(req.getParameter("entidad"));
					empresas.setCodi_Postal(Integer.parseInt(req.getParameter("codigo_postal")));
					empresas.setId_tipo_empresa(new Long(empresaBusiness.returnId(req.getParameter("empresa"))));
					empresas.setVisible(true);
					
					try 
					{
						_log.warning("el id que sigue es: +"+empresas.getId_Empresa());
						empresaEndpoint.insertEmpresa(empresas);
						System.out.println("se inserto correctamente la empresa");
						
						Multimedia multimedia = new Multimedia();
				    	MultimediaEndpoint multimediaEndpoint = new MultimediaEndpoint();
				    
				    	multimedia.setIdMultimedia(GetIds.getIdMultimedia()+1);
				    	multimedia.setIdReferencia(idEmpresa);
				    	multimedia.setPath(path);//blobkey
				    	multimedia.setTipoArchivo(Constants.imagen);//imagen o video o file
				    	multimedia.setTipoReferencia(Constants.CatalogoEmpresas);
				    	multimedia.setFecha(UtilFechas.getFecha());
				    	multimedia.setDescripcion("Logo Empresa");
				    	multimedia.setFormato(new Long(0));
				    	
				    	try 
				    	{
							multimediaEndpoint.insertMultimedia(multimedia);
							System.out.println("se inserto correctamente el logo de la persona");
						} 
				    	catch (Exception e) 
				    	{
				    		_log.warning("error : "+e.getMessage());
							try 
							{
								empresaEndpoint.removeEmpresa(idEmpresa);
								System.out.println("se borro la empresa insertada");
							} catch (Exception e2) 
							{
								_log.warning("error : "+e.getMessage());
								System.out.println("No se pudo borrar la empresa ya insertada  error: " + e2.getMessage());
							}
							System.out.println("hubo un error al insertar path empresa en multimedia");	
						}
					}
					catch (Exception e) 
					{
						_log.warning("error : "+e.getMessage());
						System.out.println("hubo un error al insertar la multimedia ");
					}
					
					//empresas.setFotografia(path);
					
					resp.sendRedirect("/views/ListaEmpresas.html");
				}
				else
				{
					System.out.println("No inserto imagen");
					resp.sendRedirect("/views/Empresaalta.jsp");
				}
				
			}
			else //
			{
				System.out.println("El nombre de la empresa ya existe");
				resp.sendRedirect("/views/Empresaalta.jsp");
			}
			
			
			/*
			empresa = (empresaEntity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new empresaEntity());
			System.out.println(req.getParameter("objectJson"));
			System.out.println(empresa);
			if(!empresaBusiness.existente(empresa.getNombre())){
			empresas.setId_Empresa(GetIds.getIdEmpresa()+1);
			empresas.setId_tipo_empresa(new Long( empresaBusiness.returnId(empresa.getTipoEmpresa())));
			empresas.setRFC(empresa.getRfc());
			empresas.setNombre(empresa.getNombre());
			empresas.setSiglas(empresa.getSiglas());
			empresas.setIMSS(empresa.getImss());
			empresas.setCalle(empresa.getCalle());
			empresas.setNum_Int(empresa.getNumInt());
			empresas.setNum_Ext(empresa.getNumExt());
			empresas.setCodi_Postal(Integer.parseInt(empresa.getCodPostal()));
			empresas.setColonia(empresa.getColonia());
			empresas.setDel_o_Mun(empresa.getDelMun());
			empresas.setEntidad(empresa.getEntidad());
			System.out.println();
			
			//insertamor los parametros
			
			try {
				empresaEndpoint.insertEmpresa(empresas);
				System.out.println("se inserto correctamente la empresa");
				out.write(new Gson().toJson("se inserto correctamente la empresa")); 
			} catch (Exception e) {
				// TODO: handle exception
				error += e.getMessage();
				System.out.println("hubo un error al insertar la empresa");
				out.write(new Gson().toJson(error)); 
				
			}
			}
			else 
			{
				error += "esta empresa ya existe ";
				out.write(new Gson().toJson(error)); 
			}*/
		
		}
	}

}
