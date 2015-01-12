package com.csgit.cao.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import jxl.Cell;


import com.csgit.cao.GetIds;
import com.csgit.cao.model.Concepto;
import com.csgit.cao.model.ConceptoEndpoint;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Text;
import com.google.gson.Gson;

public class ConceptoBusiness {
	public Concepto insertConcepto(Concepto concepto) {

		return null;
	}

	public List<Concepto> Normailiza(List<Concepto> conceptos) {

		ConceptoEndpoint conceptoendpoint = new ConceptoEndpoint();

		Concepto conceptoAux = null;
		for (int r = 0; conceptos.size() < r; r++) {
			if (conceptos.get(r).getPadre() == 0L) {
				conceptoAux = new Concepto();
				conceptoAux = conceptos.get(r);
				if (!existConceptInObra(conceptoAux.getClave(),
						conceptoAux.getId_Obra())) {
					conceptoAux.setId_Concepto(GetIds.getIdConcepto() + 1);
					try {

						conceptoendpoint.insertConcepto(conceptoAux);
						System.out.println("concepto insertado correctamente");
					} catch (Exception e) {
						System.out.println("error en la inserción" + e);
						// TODO: handle exception
					}
				} else {
					System.out
							.println("Clave de concepto existente en la obra");
					// out.write(new
					// Gson().toJson("Clave de concepto existente en la obra"));

				}

			}
			if (conceptoAux != null) {
				// conceptos.get(r).setPadre(conceptoAux.getClave());
			}

		}

		return conceptos;

	}

	public Concepto ValidaDatos(Cell[] cells) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Concepto concepto = new Concepto();
		System.out.println(cells.length);
	
			if (cells[2].getContents().toString().equalsIgnoreCase("")
					&& cells[3].getContents().toString().equalsIgnoreCase("")
					&& cells[4].getContents().toString().equalsIgnoreCase("")
					) {
				concepto.setClave(cells[0].getContents().toString());
				concepto.setDescripcion( new Text(cells[1].getContents().toString()));
				concepto.setPadre(0L);
			} else {

				for (int c = 0; c < cells.length; c++) {

					switch (c) {

					case 0:
						concepto.setClave(cells[c].getContents().toString());
						break;
					case 1:
						concepto.setDescripcion(new Text(cells[c].getContents().toString()));
						break;
					case 2:
						concepto.setUnidadMedida(cells[c].getContents()
								.toString());
						break;
					case 3:
						concepto.setCantidadTotal(Double.parseDouble(cells[c]
								.getContents().toString().replace(",", "")));
						concepto.setCantidadAvance(0D);

						break;
					case 4:
						System.out.println(cells[c].getContents().toString());
						String conversion = cells[c].getContents().toString()
								.replace((char) 34, (char) 0)
								.replace((char) 36, (char) 0);
						String remplazado = conversion.replace(",", "");
						System.out.println(remplazado);
						// concepto.setPrecioUnitario(Double.parseDouble(cells[c].getContents().toString().replace("$",
						// "").replace(",", "")));
						concepto.setPrecioUnitario(Double
								.parseDouble(remplazado));
						System.out.println(concepto.getPrecioUnitario());
						break;
					case 5:
						concepto.setImporte(concepto.getCantidadTotal()
								* concepto.getPrecioUnitario());
						try {
							System.out.print(formatter.format(formatter.parse(
									cells[c].getContents().toString())));
							concepto.setFecha_Inicio(formatter.format(formatter.parse(
									cells[c].getContents().toString()))
									.toString());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 6:
						try {
							System.out.print(formatter.format(formatter.parse(
									cells[c].getContents().toString())));
							concepto.setFecha_Fin(formatter.format(formatter.parse(
									cells[c].getContents().toString()))
									.toString());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;

					default:
						break;
					}
					// return concepto;
				}
			}
		
		return concepto;
	}


   

	private boolean existConceptInObra(String clave, Long id_Obra) {
		// TODO Auto-generated method stub
		boolean regresar=false;
		CollectionResponse lista;
		Collection<Concepto>lista1;		
		lista =new ConceptoEndpoint().listConcepto(null, null, null,0L);//traemos la lista de empresas insertadas
		lista1=lista.getItems();
	    for (Concepto elem : lista1) {
	    	
	        if((elem.getId_Obra().longValue()==id_Obra)&&(elem.getClave().equals(clave)))     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	regresar=true;
	    }
		return regresar;
	}


}
