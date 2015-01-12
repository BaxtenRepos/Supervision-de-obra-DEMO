package com.csgit.cao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.util.CollectionUtils;

import com.csgit.cao.business.UsuarioBusiness;
import com.csgit.cao.model.Avance_Financiero;
import com.csgit.cao.model.Avance_FinancieroEndpoint;
import com.csgit.cao.model.Avance_Fisico;
import com.csgit.cao.model.Avance_FisicoEndpoint;
import com.csgit.cao.model.Concepto;
import com.csgit.cao.model.ConceptoEndpoint;
import com.csgit.cao.model.Empresa;
import com.csgit.cao.model.EmpresaEndpoint;
import com.csgit.cao.model.Multimedia;
import com.csgit.cao.model.MultimediaEndpoint;
import com.csgit.cao.model.Obra;
import com.csgit.cao.model.ObraEndpoint;
import com.csgit.cao.model.ProyectoEndpoint;
import com.csgit.cao.model.Reportsmodel;
import com.csgit.cao.model.ReportsmodelEndpoint;
import com.csgit.cao.model.Usuario;
import com.csgit.cao.model.UsuarioEndpoint;
import com.csgit.util.ReportsComparatorConsecutivo;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.labs.repackaged.com.google.common.collect.Iterables;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Reports extends HttpServlet {

	private static final double CELL_DEFAULT_HEIGHT = 17;
	private static final double CELL_DEFAULT_WIDTH = 64;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// blobstoreService.serve(blobKey, resp);
		ConceptoEndpoint conceptoEndpoint = new ConceptoEndpoint();
		ReportsmodelEndpoint endpoints= new ReportsmodelEndpoint();
		ObraEndpoint obraendpoint = new ObraEndpoint();
		MultimediaEndpoint multiendpoint= new MultimediaEndpoint();
		ProyectoEndpoint proyectoendpoint = new ProyectoEndpoint();
		EmpresaEndpoint empresaEndpoint = new EmpresaEndpoint();
		Avance_FinancieroEndpoint aFinanEndpoint = new Avance_FinancieroEndpoint();
		Avance_FisicoEndpoint aFisicoEndpoint = new Avance_FisicoEndpoint();
		UsuarioEndpoint usiarioEndpoint = new UsuarioEndpoint();
		resp.getOutputStream();
		
		Long idProyecto=Long.parseLong(req.getParameter("idproyecto").toString());
		Long idObra = Long.parseLong(req.getParameter("idobra").toString());
		Collection<Multimedia> collectMultime = multiendpoint.listMultimedia(null, null, Long.parseLong(String.valueOf(Constants.Evidencia)), idObra, 0L).getItems();
		Collection<Obra> cllectObra=obraendpoint.listObra(null, null, idProyecto, idObra, " " ).getItems();
		Collection<Reportsmodel> collect=endpoints.listReportsmodel(null, null, idProyecto, idObra).getItems();
		Collection<Avance_Financiero> collectaFinan= aFinanEndpoint.listAvance_Financiero(null, null, idObra, Constants.Obra).getItems();
		Collection<Avance_Fisico> collectFisico = aFisicoEndpoint.listAvance_Fisico(null, null, idObra, Constants.Obra).getItems();
		Collection<Concepto> collecConceptos = conceptoEndpoint.listConcepto(null, null, idObra, 0L).getItems();
		
		List<Concepto> listConcepto = new ArrayList<Concepto>();
		List<Reportsmodel> listReports=new ArrayList<Reportsmodel>();
		List<Avance_Financiero> listAvanceFinanc= new ArrayList<Avance_Financiero>();
		List<Avance_Fisico> listAvanceFisico = new ArrayList<Avance_Fisico>();
		List<Multimedia> listMultimedia= new ArrayList<Multimedia>();
		
		Multimedia secretaria ;
		Multimedia dependencia ;
		Multimedia gobierno ;
		Usuario usuario = new Usuario(); 
		Obra obra = new Obra();
		listConcepto.addAll(collecConceptos);
		listAvanceFinanc.addAll(collectaFinan);
		listAvanceFisico.addAll(collectFisico);
		listMultimedia.addAll(collectMultime);
		listReports.addAll(collect);
		List<Obra> obras= new ArrayList<Obra>();
		obras.addAll(cllectObra);
		obra=obras.get(0);
	    usuario = usiarioEndpoint.getUsuario(obra.getIdUsuario(), "0", "0");
		gobierno = Iterables.get(multiendpoint.listMultimedia(null, null, Long.parseLong(String.valueOf(Constants.CatalogoEmpresas)),obra.getIdGobierno(),0L).getItems(), 0);
		dependencia=Iterables.get(multiendpoint.listMultimedia(null, null, Long.parseLong(String.valueOf(Constants.CatalogoEmpresas)),obra.getIdDependencia(),0L).getItems(), 0);
		secretaria = Iterables.get(multiendpoint.listMultimedia(null, null, Long.parseLong(String.valueOf(Constants.CatalogoEmpresas)),obra.getIdSecretaria(),0L).getItems(), 0);
		List<Long> Conceptos = new ArrayList<Long>();
		List<Long> Multimedia = new ArrayList<Long>();
		
		//Obteniendo Ids 
		
		for(int i=0; listConcepto.size()>i;i++) {
			Conceptos.add(listConcepto.get(i).getId_Concepto());
		}
		for(int i=0; listMultimedia.size()>i;i++) {
			Conceptos.add(listMultimedia.get(i).getIdMultimedia());
		}
		
		// ByteArrayInputStream bis = new ByteArrayInputStream(source);
		// read bytes from bis ...).getResourceAsStream(file);
	
		Reportsmodel lastReport = new Reportsmodel();
		Reportsmodel newReport = new Reportsmodel();
		if(listReports.size()>0){
			lastReport=GetConsecutivol(listReports);
		    newReport.setIdReporte(GetIds.getIdReports()+1);
		    newReport.setIdConsecutivo(lastReport.getIdConsecutivo()+1);

		
		}else{
			  newReport.setIdReporte(GetIds.getIdReports()+1);
			    newReport.setIdConsecutivo(1L);
				
		}
	    newReport.setIdObra(idObra);
	    newReport.setIdProyecto(idProyecto);
	    newReport.setAnticipo_Contrato(Double.valueOf(obra.getAnticipo()));
	    newReport.setAvance_Financiero_Reportado(listAvanceFinanc.get(listAvanceFinanc.size()-1).getPAvanceFinanciero());
	    newReport.setAvance_Fisico_Reportado(listAvanceFisico.get(listAvanceFisico.size()-1).getPAvanceFisico());
	    newReport.setConveio_Numero("");
	    newReport.setDireccion_Adscipcion("");
	    newReport.setFecha_Contrato(obra.getFechaContrato());
	    newReport.setFecha_Convenio("");
	    newReport.setFecha_Inicio(obra.getFechaInicioContrato());
	    newReport.setFecha_Termino(obra.getFechaTerminoContrato());
	    newReport.setIdConceptos(Conceptos);
	    newReport.setIdEmpresa_Comtratista(obra.getId_EmpresaContratista());
	    newReport.setIdMultimedia(Multimedia);
	    newReport.setIdubicacion(obra.getId_ubicacion());
	    newReport.setImporte_Acunulado(0.0);
	    newReport.setImporte_Contrato(obra.getImporteContratoSinIVA());
	    newReport.setImporte_Convenio(0.0);
	    newReport.setNombre_Contrato(obra.getNombre());
	    newReport.setNumero_Contrato(obra.getNoContrato());
	    newReport.setNumero_informe("");
	    newReport.setPeriodo_Ejecucion(0);
	    newReport.setResidente_Obra("");
	    newReport.setSaldo_Contrato(0.0);
	    newReport.setSupervisor_Obra(usuario.getUsuario());
		// write bytes to bos ...

		try {
//            reportsmodelEndpoint.
			resp.setHeader("Cache-Control", "");
			resp.setHeader("Pragma", "");
			resp.setHeader("Content-Disposition",
					"attachment; filename=foo.xls");
			resp.setContentType("application/vnd.ms-excel");
			// Se crea el libro Excel
			WritableWorkbook workbook = Workbook.createWorkbook(resp
					.getOutputStream());

			// Se crea una nueva hoja dentro del libro
			WritableSheet sheet = workbook.createSheet("Reporte Prueba 1", 0);
			sheet.addImage(ImagefromMulti(gobierno.getPath(), 0,0));
			
			sheet.addImage(ImagefromMulti(dependencia.getPath(), 6,0));
			
			sheet.addImage(ImagefromMulti(secretaria.getPath(), 11,0));
			//Formato de font 
			WritableFont times8font = new WritableFont(WritableFont.ARIAL, 25, WritableFont.BOLD, true);
            times8font.setColour(Colour.BLACK);
            WritableFont title2 = new WritableFont(WritableFont.ARIAL, 15, WritableFont.NO_BOLD, true);
            title2.setColour(Colour.BLACK);
            WritableFont notafont = new WritableFont(WritableFont.ARIAL, 7, WritableFont.BOLD, true);
            notafont.setColour(Colour.BLACK);
          

            //formato de celdas 
            WritableCellFormat formatofondogris = new WritableCellFormat(times8font);
            formatofondogris.setWrap(true);
            formatofondogris.setBackground(Colour.GRAY_25);
            formatofondogris.setAlignment(Alignment.CENTRE);
            formatofondogris.setVerticalAlignment(VerticalAlignment.CENTRE);
            formatofondogris.setBorder(Border.ALL, BorderLineStyle.THIN);
            
            WritableCellFormat formatotitulo = new WritableCellFormat(title2);
            WritableCellFormat formatonota = new WritableCellFormat(notafont);

            //vista de cledas 
            sheet.setRowView(6, 20*26);
            //conbinacion de celdas 
            sheet.mergeCells(0, 6, 12,6);
			//titulos 

            sheet.addCell(new jxl.write.Label(0,6, "Reporte diario",formatofondogris));
			sheet.addCell(new jxl.write.Label(0,7 , "Número de informe",formatotitulo));
			sheet.addCell(new jxl.write.Label(0,8 , "Nombre del contrato",formatotitulo));
			sheet.addCell(new jxl.write.Label(10,7 , "Hoja",formatotitulo));
			sheet.addCell(new jxl.write.Label(10,8 , "Fecha",formatotitulo));
			sheet.addCell(new jxl.write.Label(12,7 , "de",formatotitulo));
			sheet.addCell(new jxl.write.Label(0,10 , "Número de contrato",formatotitulo));
			sheet.addCell(new jxl.write.Label(5,10 , "Ubicación",formatotitulo));
			sheet.addCell(new jxl.write.Label(10,10 , "*Convenio número",formatotitulo));
			sheet.addCell(new jxl.write.Label(0,11 , "Fecha contrato",formatotitulo));
			sheet.addCell(new jxl.write.Label(5,11 , "Importe del contrato",formatotitulo));
			sheet.addCell(new jxl.write.Label(10,11 , "*Fecha de convenio",formatotitulo));
			sheet.addCell(new jxl.write.Label(0,12 , "Empresa contratista",formatotitulo));
			sheet.addCell(new jxl.write.Label(5,12 , "Anticipo del contrato",formatotitulo));
			sheet.addCell(new jxl.write.Label(10,12 , "*Importe de convenio",formatotitulo));
			sheet.addCell(new jxl.write.Label(0,13 , "Superintendente de obra",formatotitulo));
			sheet.addCell(new jxl.write.Label(5,13 , "Fecha de inicio",formatotitulo));
			sheet.addCell(new jxl.write.Label(0,14 , "Supervisora externa",formatotitulo));
			sheet.addCell(new jxl.write.Label(5,14 , "Fecha de termino",formatotitulo));
			sheet.addCell(new jxl.write.Label(10,14 , "*Plazo de ejecucion",formatotitulo));
			sheet.addCell(new jxl.write.Label(0,15 , "Supervisor de obra",formatotitulo));
			sheet.addCell(new jxl.write.Label(5,15 , "Periodo de ejecución",formatotitulo));
			sheet.addCell(new jxl.write.Label(0,16 , "Dirección de Adscripción",formatotitulo));			
			sheet.addCell(new jxl.write.Label(5,16 , "Avance fisico reportado",formatotitulo));
			sheet.addCell(new jxl.write.Label(10,16 , "*Fecha de Inicio",formatotitulo));
			sheet.addCell(new jxl.write.Label(5,17 , "Avance financiero reportado",formatotitulo));
			sheet.addCell(new jxl.write.Label(10,17 , "*Fecha de termino",formatotitulo));
			sheet.addCell(new jxl.write.Label(0,8 , "Nombre del Contrato",formatotitulo));
			sheet.addCell(new jxl.write.Label(0,18 , "Recidente de obra",formatotitulo));
			sheet.addCell(new jxl.write.Label(5,18 , "Importe acumulado",formatotitulo));
			sheet.addCell(new jxl.write.Label(10,18 , "*Campos para llenar en caso de existir convenio",formatonota));
			//
			//
			// Creamos celdas de varios tipos
//			sheet.addCell(new jxl.write.Number(0, 0, 1));
//			sheet.addCell(new jxl.write.Number(1, 0, 1.2));
//			
//			sheet.addCell(new jxl.write.Boolean(3, 0, true));

			// Creamos una celda de tipo fecha y la mostramos
			// indicando un patón de formato
//			DateFormat customDateFormat = new DateFormat("d/m/yy h:mm");
//
//			WritableCellFormat dateFormat = new WritableCellFormat(
//					customDateFormat);
//
//			sheet.addCell(new jxl.write.DateTime(4, 0, new Date(), dateFormat));

			// Escribimos los resultados al fichero Excel
			workbook.write();
			workbook.close();

			System.out.println("Ejemplo finalizado.");
		} catch (IOException ex) {
			System.out.println("Error al crear el fichero.");
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.getOutputStream().close();

	}
	public Reportsmodel GetConsecutivol(List<Reportsmodel> lstreports) {
		Collections.sort(lstreports, new ReportsComparatorConsecutivo());
		return lstreports.get(lstreports.size()-1);
	}

    public WritableImage ImagefromMulti(String Path,Integer posx, Integer posy){
		BlobKey blobKey = new BlobKey(Path);
		ImagesService imagesService = ImagesServiceFactory.getImagesService();
		Image oldImage = ImagesServiceFactory.makeImageFromBlob(blobKey);
		com.google.appengine.api.images.Transform transform = ImagesServiceFactory
				.makeResize(150, 75);
		Image newImage = imagesService.applyTransform(transform, oldImage);
    	
	       WritableImage image =    new WritableImage(posx, posy, newImage.getWidth()
				/ CELL_DEFAULT_WIDTH, newImage.getHeight()
				/ CELL_DEFAULT_HEIGHT, newImage.getImageData());
    	return image;
    }
}
