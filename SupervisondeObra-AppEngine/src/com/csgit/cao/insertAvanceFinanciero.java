package com.csgit.cao;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.csgit.cao.model.Avance_Financiero;
import com.csgit.cao.model.Avance_FinancieroEndpoint;

public class insertAvanceFinanciero {

	public insertAvanceFinanciero() {
		// TODO Auto-generated constructor stub
	
	}
	public void insertAvance(Long idReferencia, int tipo){
		 
			
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Avance_Financiero avanceFinanciero = new Avance_Financiero();
				Avance_FinancieroEndpoint avanceFinancieroEndpoint = new Avance_FinancieroEndpoint();
				avanceFinanciero.setEstado(Constants.Estado_Conforme_Programa);
				avanceFinanciero.setFechaReporte(String.valueOf(sdf.format(new Date(System.currentTimeMillis()))));
				avanceFinanciero.setId_AvanceFinaciero(GetIds.getIdAvanceFinanciero()+1);

				avanceFinanciero.setId_referencia(idReferencia);
				avanceFinanciero.setPAvanceFinanciero((double) 0);

				avanceFinanciero.setPorcentaje_tendencia(new Double(1));
				avanceFinanciero.setTipo_Entidad(tipo);
				try {
					avanceFinancieroEndpoint.insertAvance_Financiero(avanceFinanciero);
					System.out.println("avance financiero insertado correctamente");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("error al insertar el avance financiero"+ e.getMessage());
					System.out.println();
				}
		
	}
	
}
