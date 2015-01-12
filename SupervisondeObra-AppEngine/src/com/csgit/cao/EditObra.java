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

import com.csgit.cao.business.ObraBusiness;
import com.csgit.cao.model.Empresa;
import com.csgit.cao.model.EmpresaEndpoint;
import com.csgit.cao.model.Obra;
import com.csgit.cao.model.ObraEndpoint;
import com.csgit.cao.model.Proyecto;
import com.csgit.cao.model.ProyectoEndpoint;
import com.csgit.cao.model.Ubicaciones;
import com.csgit.cao.model.UbicacionesEndpoint;
import com.csgit.cao.model.Usuario;
import com.csgit.cao.model.UsuarioEndpoint;
import com.csgit.entity.DependenciasEntity;
import com.csgit.entity.ObraEntityEdit;
import com.csgit.entity.PuntoEntity;
import com.csgit.entity.UsuarioEntity;
import com.csgit.util.JsonParserUtil;
import com.csgit.util.NombreTipoUtility;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class EditObra extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ObraEntityEdit obra = new ObraEntityEdit();
	ObraEndpoint obraEndpoint = new ObraEndpoint();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		String error = "";
		
		
		resp.setContentType("text/json");	
		System.out.println(req.getParameter("objectJson"));
		String parametro = req.getParameter("objectJson");
		
		if(parametro.contains("peticion")){   //validamos si es la peticion para el catalogo de proyectos 
			out.write(new Gson().toJson(obtenerProyectos()));
			return;
		}else if(parametro.contains("opcionproyecto:")){   //validamos si es la peticion para el catalogo de proyectos 
			String[] split= parametro.split(":");
			Long idProyecto = Long.parseLong(split[1].replace('"', ' ').trim());
			System.out.println("voy a devolver las obras con idProyecto = " + idProyecto);
			out.write(new Gson().toJson(obtenerObras(idProyecto)));
			return;
		}else if(parametro.contains("opcionselect")){   //validamos si es la peticion para el catalogo de proyectos 
			System.out.println("voy a devolver las empresas");
			out.write(new Gson().toJson(obtenerDependencias()));
			return;
		}else if(parametro.contains("opcionsupervisor")){   //validamos si es la peticion para el catalogo de proyectos 
			System.out.println("voy a devolver a los supervisores");
			out.write(new Gson().toJson(obtenerSupervisores()));
			return;
		}else if(parametro.contains("opcionobra:")){   //validamos si es la peticion para el catalogo de proyectos 
			String[] split= parametro.split(":");
			Long idObra = Long.parseLong(split[1].replace('"', ' ').trim());
			System.out.println("voy a devolver la obra con id = " + idObra);
			out.write(new Gson().toJson(obtenerObra(idObra)));
			return;
		}else if(parametro.contains("ubicacion:")){
			String idUbicacion[] = parametro.split("-");
			ArrayList<PuntoEntity> puntos = new ArrayList<>();
			System.out.println(idUbicacion[1].replace('"', ' ').trim());
			String puntos2 = "";
			Ubicaciones ubi = new UbicacionesEndpoint().getUbicaciones(new Long(idUbicacion[1].replace('"', ' ').trim()));
			for (String puntoEntity : ubi.getUbicacion()) {
				puntos2+=puntoEntity+",";
			}
			String puntossplit[] = puntos2.split(",");
			for(int r=0;r<=puntossplit.length-2;r+=2){
				PuntoEntity pt = new PuntoEntity();
				pt.setK(puntossplit[r]);
				pt.setD(puntossplit[r+1]);
			    puntos.add(pt);	
			}
			out.write(new Gson().toJson(puntos));
			System.out.println(puntos2);
			
		}else{
			System.out.println(req.getParameter("objectJson"));
			obra = (ObraEntityEdit) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new ObraEntityEdit());
			
			if(!ObraBusiness.ExistNoContrato(obra.getNoContrato(),obra.getId_Poyecto(), obra.getId_Obra())){

					Obra obraelement = new Obra();
					if(!obra.getImporteAjusteCostos().equals("")){
						obraelement.setImporteAjusteCostos(Double.parseDouble(obra.getImporteAjusteCostos()));
					}if(!obra.getImporteConvenioAmpliacion().equals("")){
						obraelement.setImporteConvenioAmpliacion(Double.parseDouble(obra.getImporteConvenioAmpliacion()));
					}if(!obra.getImporteConvenioReduccion().equals("")){
						obraelement.setImporteConvenioReduccion(Double.parseDouble(obra.getImporteConvenioReduccion()));
					}if(!obra.getPorcentajeDesvio().equals("")){
						obraelement.setPorcentajeDesvio(Double.parseDouble(obra.getPorcentajeDesvio()));
					}
					
					obraelement.setAnticipo(obra.getAnticipo());
					obraelement.setArea(obra.getArea());
					obraelement.setBorrador(obra.getBorrador());
					obraelement.setCargoRevision1(obra.getCargoRevision1());
					obraelement.setCargoRevision2(obra.getCargoRevision2());
					obraelement.setCargoVoBo(obra.getCargoVoBo());
					obraelement.setDescripcion(obra.getDescripcion());
					obraelement.setDireccion(obra.getDireccion());
					obraelement.setEntidadFederativa(obra.getEntidadFederativa());
					obraelement.setFechaContrato(obra.getFechaContrato());
					obraelement.setFechaFianzaAnticipo(obra.getFechaFianzaAnticipo());
					obraelement.setFechaFianzaCumplimiento(obra.getFechaFianzaCumplimiento());
					obraelement.setFechaInicioContrato(obra.getFechaInicioContrato());
					obraelement.setFechaTerminoContrato(obra.getFechaTerminoContrato());
					obraelement.setId_EmpresaContratista(obra.getId_EmpresaContratista());
					obraelement.setId_Obra(obra.getId_Obra());
					obraelement.setId_Poyecto(obra.getId_Poyecto());
					obraelement.setId_ubicacion(obra.getId_ubicacion());
					obraelement.setIdDependencia(obra.getIdDependencia());
					obraelement.setIdGobierno(obra.getIdGobierno());
					obraelement.setIdSecretaria(obra.getIdSecretaria());
					
					obraelement.setImporteContratoSinIVA(obra.getImporteContratoSinIVA());
					obraelement.setImporteFiscal1SinIVA(obra.getImporteFiscal1SinIVA());
					obraelement.setLimite_Desvio(obra.getLimite_Desvio());
					obraelement.setMontoFianzaAnticipo(obra.getMontoFianzaAnticipo());
					obraelement.setMontoFianzaCumplimiento(obra.getMontoFianzaCumplimiento());
					obraelement.setNombreRevision2(obra.getNombreRevision2());
					obraelement.setIdUsuario(obra.getIdUsuario());
					obraelement.setNoContrato(obra.getNoContrato());
					obraelement.setNoFianzaAnticipo(obra.getNoFianzaAnticipo());
					obraelement.setNoFianzaCumplimiento(obra.getNoFianzaCumplimiento());
					obraelement.setNombre(obra.getNombre());
					obraelement.setNombreEjercicioFiscal1(obra.getNombreEjercicioFiscal1());
					obraelement.setNombreQuienAutoriza(obra.getNombreQuienAutoriza());
					obraelement.setNombreRevision1(obra.getNombreRevision1());
					obraelement.setNombreVoBo(obra.getNombreVoBo());
					obraelement.setPartidaPresupuestal(obra.getPartidaPresupuestal());
					obraelement.setPeriodoEjucionDias(obra.getPeriodoEjucionDias());
					obraelement.setSubdireccion(obra.getSubdireccion());
					obraelement.setSuperintendente(obra.getSuperintendente());
					obraelement.setTipoContrato(obra.getTipoContrato());
					obraelement.setVisible(true);
					
					try {
						obraEndpoint.updateObra(obraelement);
						try {
							InsertaUbicacion iu = new InsertaUbicacion();
							iu.updatePunto(obra.getDatosArrayUbicacion(), obra.getId_ubicacion());
							out.write(new Gson().toJson("Obra editada correctamente"));
						} catch (Exception e) {
							// TODO: handle exception=
							error +="error al editar la obra";
							out.write(new Gson().toJson(error));
							System.out.println("error al editar la obra "+ e.getMessage() );
						}
						
					} catch (Exception e) {
						// TODO: handle exception=
						error +="error al editar la obra";
						out.write(new Gson().toJson(error));
						System.out.println("error al editar la obra "+ e.getMessage() );
					}
			}else{
				error +="el numero de contrato ya existe, por favor verifique la informacion";
				out.write(new Gson().toJson(error));
				return;
			}
		}
	}
	
	private Collection<UsuarioEntity> obtenerSupervisores() {
		// TODO Auto-generated method stub
		CollectionResponse<Usuario> lista = new UsuarioEndpoint().listUsuario(null, null);
		Collection<Usuario> lista1 = lista.getItems();
		Collection<UsuarioEntity> regresar = new ArrayList<>();
		for (Usuario user : lista1) {
			if(user.getId_Tipo_Persona()==Constants.idSupervisor && user.isVisible()){
				UsuarioEntity ue = new UsuarioEntity();
				ue.setId(user.getId_Propietario());
				ue.setNombre(user.getUsuario());
				ue.setTipo(Constants.supervisorTexto);
				regresar.add(ue);
			}
		}
		
		return regresar;
	}
	
	public Collection<DependenciasEntity> obtenerDependencias(){
		CollectionResponse<Empresa> lista =new EmpresaEndpoint().listEmpresa(null,null);//traemos la lista de empresas insertadas
		Collection<Empresa> lista1=lista.getItems();
		 Set regresar = new TreeSet();
	    for (Empresa elem : lista1) {
	            		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	if(elem.getId_tipo_empresa()==Constants.idDependencia && elem.isVisible()){
	        		DependenciasEntity dp = new DependenciasEntity();
	        		dp.setNombre(elem.getNombre());
	        		dp.setId(elem.getId_Empresa());
	        		dp.setTipoEmpresa(Constants.dependenciaTexto);
	        		regresar.add(dp);
	        	}else if(elem.getId_tipo_empresa()==Constants.idSecretaria && elem.isVisible()){
	        		DependenciasEntity dp = new DependenciasEntity();
	        		dp.setNombre(elem.getNombre());
	        		dp.setId(elem.getId_Empresa());
	        		dp.setTipoEmpresa(Constants.secretariaTexto);
	        		regresar.add(dp);
	        	}else if(elem.getId_tipo_empresa()==Constants.idGobierno && elem.isVisible()){
	        		DependenciasEntity dp = new DependenciasEntity();
	        		dp.setNombre(elem.getNombre());
	        		dp.setTipoEmpresa(Constants.GobiernoTexto);
	        		dp.setId(elem.getId_Empresa());
	        		regresar.add(dp);
	        	}else if(elem.getId_tipo_empresa()==Constants.idContratista && elem.isVisible()){
	        		DependenciasEntity dp = new DependenciasEntity();
	        		dp.setNombre(elem.getNombre());
	        		dp.setId(elem.getId_Empresa());
	        		dp.setTipoEmpresa(Constants.contratistaTexto);
	        		regresar.add(dp);
	        	} 
	    }
		return regresar;
	}

	private Collection<NombreTipoUtility> obtenerObras(Long idProyecto) {
		CollectionResponse<Obra> lista;
		Collection<Obra>lista1;
		Collection<NombreTipoUtility> regresar = new ArrayList<>();
		lista =new ObraEndpoint().listObra(null, null,new Long(0),new Long(0),"");//traemos la lista de obras insertadas
		lista1=lista.getItems();
	    for (Obra elem : lista1) {
	    	if(elem.getId_Poyecto() == idProyecto && elem.isVisible()){
	    		 NombreTipoUtility nu = new NombreTipoUtility();
		   	     nu.setNombre(elem.getNombre());
		   	     nu.setTipo(elem.getNoContrato().toString());
		   	     nu.setId(elem.getId_Obra());
		   	     regresar.add(nu);
	    	}
	    }
		return regresar;
	}
	
	private Obra obtenerObra(Long idObra) {
		CollectionResponse<Obra> lista;
		Collection<Obra>lista1;
		Obra regresar = new Obra();
		lista =new ObraEndpoint().listObra(null, null,new Long(0),new Long(0),"");//traemos la lista de obras insertadas
		lista1=lista.getItems();
	    for (Obra elem : lista1) {
	    	if(elem.getId_Obra() == idObra){
	    		 regresar = elem;
	    		 break;
	    	}
	    }
		return regresar;
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
}
