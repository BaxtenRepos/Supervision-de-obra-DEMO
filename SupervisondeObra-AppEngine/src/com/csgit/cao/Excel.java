package com.csgit.cao;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableWorkbook;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.csgit.cao.business.ConceptoBusiness;
import com.csgit.cao.model.Concepto;
import com.csgit.cao.model.ConceptoEndpoint;
import com.csgit.cao.model.Obra;
import com.csgit.cao.model.ObraEndpoint;
import com.csgit.cao.model.Programa;
import com.csgit.util.MathUtil;
import com.google.appengine.api.datastore.Text;
import com.google.gson.Gson;


public class Excel extends HttpServlet {
	public static final Logger _log = Logger.getLogger(Excel.class.getName());
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ConceptoBusiness bus_concepto;
		Long idObra;
		Double importe = new Double(0);

		HttpSession sesion = req.getSession();
		PrintWriter out = res.getWriter();
		String obraId = (String) sesion.getAttribute("idObra");
		if(obraId!=null)
		try {
			
			idObra = Long.parseLong(obraId.trim());
		
			ServletFileUpload upload = new ServletFileUpload();
			upload.setSizeMax(100000000);
			res.setContentType("text/plain");
			
            bus_concepto = new ConceptoBusiness();
			try {
				FileItemIterator iterator = upload.getItemIterator(req);
				while (iterator.hasNext()) {
					
					
					
					WorkbookSettings opciones= new WorkbookSettings();
					opciones.setEncoding("iso-8859-1");
					
					
					
					
					FileItemStream item = iterator.next();
					InputStream in = item.openStream();
					  // Workbook workBook =  Workbook.getWorkbook(in,opciones) ;
					   Workbook workBook = Workbook.getWorkbook(in, opciones);
					if (item.isFormField()) {
						out.println("Got a form field: " + item.getFieldName());
					} else {
						String fieldName = item.getFieldName();
						String fileName = item.getName();
						String contentType = item.getContentType();

						out.println("--------------");
						out.println("fileName = " + fileName);
						out.println("field name = " + fieldName);
						out.println("contentType = " + contentType);

						String fileContents = null;

					Sheet sheet = workBook.getSheet(0);
					Gson gson = new Gson();
					List Conceptos = new ArrayList<Concepto>();
					for (int r=0; r<sheet.getRows();r++){
						 Cell[] row = sheet.getRow(r);
						 if(r!=0)
						 Conceptos.add(bus_concepto.ValidaDatos(row));
							//out.println("Numero de Celdas por este renglon:"+     	row.getPhysicalNumberOfCells());

//						 for(int c=0;c<row.getLastCellNum();c++){
//							 out.println( row.getCell(c));	 
//						 }
					}	 
					Long id = 0L;
					ConceptoEndpoint conceptoendpoint = new ConceptoEndpoint();
					ArrayList<Concepto> elementos =  new ArrayList<Concepto>();
				
					id = GetIds.getIdConcepto()+1;
					Long idPadre;
					for(int i = 0; i< Conceptos.size(); i++){
						
						Concepto c = (Concepto) Conceptos.get(i);
						if(c.getPadre()!=null){
							
							try {
					
								c.setId_Concepto(id+=1);
								idPadre=id;

								c.setId_Obra(idObra);
								c.setVisible(true);
								if(!c.getDescripcion().getValue().equalsIgnoreCase("")){
									conceptoendpoint.insertConcepto(c);
								//importe
									if(c.getImporte()!=null)
								importe += c.getImporte();
									
								}

								_log.info("concepto insertado correctamente");
								
							
								i++;
								Concepto conc =  (Concepto) Conceptos.get(i);
								if(conc.getPadre()!=null)i--;
								
								while(conc.getPadre()==null){
									Concepto c2 = (Concepto) Conceptos.get(i);
									
									c2.setId_Concepto(id+=1);
									c2.setPadre(idPadre);
									c2.setId_Obra(idObra);
									c2.setVisible(true);
									if(!c2.getDescripcion().getValue().equalsIgnoreCase("")){
									conceptoendpoint.insertConcepto(c2);
									//importe
									if(c2.getImporte()!=null)
									importe += c2.getImporte();
									}
								_log.info("conceptos hiijos insertados");
								i++;
								 conc =  (Concepto) Conceptos.get(i);
								 if(conc.getPadre()!=null)i--;
							
								}
								
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println("error"+e.getMessage());
								_log.warning("error al insertat los conceptos "+ e.getMessage());
							}
						}
						else {
   							elementos.add(c);
						}
					}
					
				
				
				if(elementos.size()!=0)
					try {
						Concepto c = new Concepto();
						c.setDescripcion(new Text("Concepto"));
						c.setId_Concepto(id+=1);
						c.setPadre(0L);
						c.setId_Obra(idObra);
						c.setVisible(true);
						conceptoendpoint.insertConcepto(c);
						//importe
						if(c.getImporte()!=null)
						importe += c.getImporte();
						
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println();
						_log.warning("error: "+e.getMessage());
					}
					
					for(int j = 0; j<elementos.size(); j++){
						try {
							Concepto c2 = (Concepto) Conceptos.get(j);
							
							c2.setId_Concepto(GetIds.getIdConcepto()+1);
							c2.setPadre(id+=1);
							c2.setId_Obra(idObra);
							c2.setVisible(true);
							conceptoendpoint.insertConcepto(c2);
							//importe
							if(c2.getImporte()!=null)
							importe += c2.getImporte();
							
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("error "+e.getMessage());
							_log.warning("error: "+e.getMessage());
						}
					
					}
					Obra obra = new Obra();
					ObraEndpoint obraendpoint = new ObraEndpoint();
					Obra updateObra = new Obra();
					updateObra.setBorrador("1");
					
					obra = obraendpoint.getObra(idObra);
					updateObra.setId_Obra(obra.getId_Obra());
					updateObra.setAnticipo(obra.getAnticipo());
					updateObra.setArea(obra.getArea());
					updateObra.setCargoRevision1(obra.getCargoRevision1());
					updateObra.setCargoRevision2(obra.getCargoRevision2());
					updateObra.setCargoVoBo(obra.getCargoVoBo());
					updateObra.setDescripcion(obra.getDescripcion());
					updateObra.setDireccion(obra.getDireccion());
					updateObra.setEntidadFederativa(obra.getEntidadFederativa());
					updateObra.setFechaContrato(obra.getFechaContrato());
					updateObra.setFechaFianzaAnticipo(obra.getFechaFianzaAnticipo());
					updateObra.setFechaFianzaCumplimiento(obra.getFechaFianzaCumplimiento());
					updateObra.setFechaInicioContrato(obra.getFechaInicioContrato());
					updateObra.setFechaTerminoContrato(obra.getFechaTerminoContrato());
					updateObra.setId_EmpresaContratista(obra.getId_EmpresaContratista());
					updateObra.setId_Poyecto(obra.getId_Poyecto());
					updateObra.setId_ubicacion(obra.getId_ubicacion());
					updateObra.setIdDependencia(obra.getIdDependencia());
					updateObra.setIdGobierno(obra.getIdGobierno());
					updateObra.setIdSecretaria(obra.getIdSecretaria());
					updateObra.setIdUsuario(obra.getIdUsuario());
					updateObra.setImporteAjusteCostos(obra.getImporteAjusteCostos());
					if(obra.getImporteContratoSinIVA()!=null)
					updateObra.setImporteContratoSinIVA(obra.getImporteContratoSinIVA()+importe);
					else
						updateObra.setImporteContratoSinIVA(importe);
					
					updateObra.setImporteConvenioAmpliacion(obra.getImporteConvenioAmpliacion());
					updateObra.setImporteConvenioReduccion(obra.getImporteConvenioReduccion());
					updateObra.setImporteFiscal1SinIVA(obra.getImporteFiscal1SinIVA());
					updateObra.setLimite_Desvio(obra.getLimite_Desvio());
					updateObra.setMontoFianzaAnticipo(obra.getMontoFianzaAnticipo());
					updateObra.setMontoFianzaCumplimiento(obra.getMontoFianzaCumplimiento());
					updateObra.setNoContrato(obra.getNoContrato());
					updateObra.setNoFianzaAnticipo(obra.getNoFianzaAnticipo());
					updateObra.setNoFianzaCumplimiento(obra.getNoFianzaCumplimiento());
					updateObra.setNombre(obra.getNombre());
					updateObra.setNombreEjercicioFiscal1(obra.getNombreEjercicioFiscal1());
					updateObra.setNombreQuienAutoriza(obra.getNombreQuienAutoriza());
					updateObra.setNombreRevision1(obra.getNombreRevision1());
					updateObra.setNombreRevision2(obra.getNombreRevision2());
					updateObra.setNombreVoBo(obra.getNombreVoBo());
					updateObra.setPartidaPresupuestal(obra.getPartidaPresupuestal());
					updateObra.setPeriodoEjucionDias(obra.getPeriodoEjucionDias());
					updateObra.setRFC(obra.getRFC());
					updateObra.setSubdireccion(obra.getSubdireccion());
					updateObra.setSuperintendente(obra.getSuperintendente());
					updateObra.setTipoContrato(obra.getTipoContrato());
					updateObra.setPorcentajeDesvio(obra.getPorcentajeDesvio());
					updateObra.setVisible(true);
					
					obra.setBorrador("1");
					try {
					//	new ObraEndpoint().updateObra(obra);
					
						new ObraEndpoint().updateObra(updateObra);
						System.out.println("el importe de la obra es: "+updateObra.getImporteFiscal1SinIVA());
						System.out.println("e inserto correctamente la obra y El importe de la obra es: "+ importe);
						_log.info("se inserto correctamente la obra y El importe de la obra es: "+ importe);
					} catch (Exception e) {
						System.out.println("error al update de la obra : "+e.getMessage());
						_log.warning("error al actualizar la obra: "+e.getMessage());
						// TODO: handle exception
					}
					
					
					MathUtil mu = new MathUtil();
					 List<Programa> a = mu.ObtenerProgramado(idObra, 0, 1);
					//sesion.removeAttribute("idObra");
//					out.println(gson.toJson( a));
					res.sendRedirect("/views/ListaConceptos.html");
					
					//Get iterator to all the rows in current sheet
					//Iterator<Row> rowIterator = sheet.iterator();

					//Get iterator to all cells of current row
//					Iterator<Cell> cellIterator = row.cellIterator();
                    out.println("Sheet column = " +  sheet.getRows());
                    return;
					}
				}
			} catch (SizeLimitExceededException e) {
				out.println("You exceeded the maximu size ("
						+ e.getPermittedSize() + ") of the file ("
						+ e.getActualSize() + ")");
			}
			
		//	InputStream is = new ByteArrayInputStream(ba);
	     
		} catch (Exception ex) {

			throw new ServletException(ex);
		}
		else out.println("no se selecciono una obra intentelo de nuevo");
	}

}