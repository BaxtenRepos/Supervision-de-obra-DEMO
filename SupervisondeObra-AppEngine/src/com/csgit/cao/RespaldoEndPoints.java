package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.model.Avance_Financiero;
import com.csgit.cao.model.Avance_FinancieroEndpoint;
import com.csgit.cao.model.Avance_FisicoEndpoint;
import com.csgit.cao.model.Cat_Contactos;
import com.csgit.cao.model.Cat_ContactosEndpoint;
import com.csgit.cao.model.Cat_PersonalEndpoint;
import com.csgit.cao.model.Cat_Tipo_Empresa;
import com.csgit.cao.model.Cat_Tipo_EmpresaEndpoint;
import com.csgit.cao.model.Cat_Tipo_MaquinariaEndpoint;
import com.csgit.cao.model.Cat_Tipo_PresonaEndpoint;
import com.csgit.cao.model.ConceptoEndpoint;
import com.csgit.cao.model.DeviceInfoEndpoint;
import com.csgit.cao.model.Directivo_ProyectoEndpoint;
import com.csgit.cao.model.DirectorioEndpoint;
import com.csgit.cao.model.EmpresaEndpoint;
import com.csgit.cao.model.EstimacionEndpoint;
import com.csgit.cao.model.MaquinariaEndpoint;
import com.csgit.cao.model.MessageEndpoint;
import com.csgit.cao.model.MultimediaEndpoint;
import com.csgit.cao.model.NotasEndpoint;
import com.csgit.cao.model.ObraEndpoint;
import com.csgit.cao.model.Org_GoubernamentalEndpoint;
import com.csgit.cao.model.PersonaEndpoint;
import com.csgit.cao.model.ProgramaEndpoint;
import com.csgit.cao.model.ProyectoEndpoint;
import com.csgit.cao.model.PushEndpoint;
import com.csgit.cao.model.Ref_calendarizacionEndpoint;
import com.csgit.cao.model.RepMaquinariaCatMaquinariaEndpoint;
import com.csgit.cao.model.RepoPersonalCatPersonalEndpoint;
import com.csgit.cao.model.ReporteDiario;
import com.csgit.cao.model.ReporteDiarioEndpoint;
import com.csgit.cao.model.Sincronizacion;
import com.csgit.cao.model.SincronizacionEndpoint;
import com.csgit.cao.model.UbicacionesEndpoint;
import com.csgit.cao.model.UsuarioEndpoint;
import com.csgit.entity.ProyectoEntity;
import com.csgit.util.JsonParserUtil;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.gson.Gson;

public class RespaldoEndPoints extends HttpServlet{
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stu
		PrintWriter out = resp.getWriter();
		String error = "";
		resp.setContentType("text/json");
		String parametro = req.getParameter("objectJson");
		if(parametro.contains("avanceFinanciero")){
			CollectionResponse<Avance_Financiero>lista;
			ArrayList<Avance_Financiero>listaa;
		//	proyecto = (ProyectoEntity) JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new ProyectoEntity());
			
			//lista=(CollectionResponse<Avance_Financiero>)JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"),new CollectionResponse<Avance_Financiero>());
			//listaa = (ArrayList<Avance_Financiero>)JsonParserUtil.getInstance().getJsonParserObject(req.getParameter("objectJson"), new ArrayList<Avance_Financiero>());
			out.write(new Gson().toJson(new Avance_FinancieroEndpoint().listAvance_Financiero(null, null, new Long(0), 0)));
			return;
			
		}else if(parametro.contains("avanceFisico")){
			
			out.write(new Gson().toJson(new Avance_FisicoEndpoint().listAvance_Fisico(null, null, new Long(0), 0)));
			return;
		}
		else if(parametro.contains("catContactos")){
			out.write(new Gson().toJson(new Cat_ContactosEndpoint().listCat_Contactos(null, null)));
			return;
		}
		else if(parametro.contains("catPersonal")){
			out.write(new Gson().toJson(new Cat_PersonalEndpoint().listCat_Personal(null, null)));
			return;
		}
		else if(parametro.contains("catTipoEmpresa")){
			CollectionResponse<Cat_Tipo_Empresa> lista;
			lista=new Cat_Tipo_EmpresaEndpoint().listCat_Tipo_Empresa(null, null);
			out.write(new Gson().toJson(new Cat_Tipo_EmpresaEndpoint().listCat_Tipo_Empresa(null, null)));
			return;
		}
		else if(parametro.contains("catTipoMaquinaria")){
			out.write(new Gson().toJson(new Cat_Tipo_MaquinariaEndpoint().listCat_Tipo_Maquinaria(null, null)));
			return;
		}
		else if(parametro.contains("catTipoPersona")){
			out.write(new Gson().toJson(new Cat_Tipo_PresonaEndpoint().listCat_Tipo_Presona(null, null)));
			return;
		}
		else if(parametro.contains("concepto")){
			out.write(new Gson().toJson(new ConceptoEndpoint().listConcepto(null, null, new Long(0),0L)));
			return;
		}
		else if(parametro.contains("deviceInfo")){
			out.write(new Gson().toJson(new DeviceInfoEndpoint().listDeviceInfo(null, null)));
			return;
		}
		else if(parametro.contains("directivoProyecto")){
			out.write(new Gson().toJson(new Directivo_ProyectoEndpoint().listDirectivo_Proyecto(null, null, new Long(0))));
			return;
		}
		else if(parametro.contains("directorio")){
			out.write(new Gson().toJson(new DirectorioEndpoint().listDirectorio(null, null, new Long(0), new Long(0))));
			return;
		}
		else if(parametro.contains("empresa")){
			out.write(new Gson().toJson(new EmpresaEndpoint().listEmpresa(null, null)));
			return;
		}
	
		else if(parametro.contains("maquinaria")){
			out.write(new Gson().toJson(new MaquinariaEndpoint().listMaquinaria(null, null)));
			return;
		}
		else if(parametro.contains("message")){
			out.write(new Gson().toJson(new MessageEndpoint().listMessages(null, null)));
			return;
		}
		else if(parametro.contains("multimedia")){
			out.write(new Gson().toJson(new MultimediaEndpoint().listMultimedia(null, null, new Long(0), new Long(0), new Long(0))));
			return;
		}
	
		else if(parametro.contains("obra")){
			out.write(new Gson().toJson(new ObraEndpoint().listObra(null, null, new Long(0), new Long(0),"")));
			return;
		}
		else if(parametro.contains("orgGubernamental")){
			out.write(new Gson().toJson(new Org_GoubernamentalEndpoint().listOrg_Goubernamental(null, null)));
			return;
		}
		else if(parametro.contains("persona")){
			out.write(new Gson().toJson(new PersonaEndpoint().listPersona(null, null)));
			return;
		}
		else if(parametro.contains("programa")){
			out.write(new Gson().toJson(new ProgramaEndpoint().listPrograma(null, null)));
			return;
		}
		else if(parametro.contains("proyecto")){
			out.write(new Gson().toJson(new ProyectoEndpoint().listProyecto(null, null)));
			return;
		}
		else if(parametro.contains("push")){
			out.write(new Gson().toJson(new PushEndpoint().listPush(null, null)));
			return;
		}
		
	}

}
