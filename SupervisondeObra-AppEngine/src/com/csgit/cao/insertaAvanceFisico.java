package com.csgit.cao;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.csgit.cao.model.Avance_Fisico;
import com.csgit.cao.model.Avance_FisicoEndpoint;

public class insertaAvanceFisico {
	public insertaAvanceFisico() {
		// TODO Auto-generated constructor stub
	}
	
	public void insertAvance(Long idReferencia,int tipo){
		Avance_Fisico avanceFisico = new Avance_Fisico();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Avance_FisicoEndpoint avanceFisicoEndpoint = new Avance_FisicoEndpoint();
		avanceFisico.setEstado(Constants.Estado_Conforme_Programa);
		avanceFisico.setFechaReporte(String.valueOf(sdf.format(new Date(System.currentTimeMillis()))));
		avanceFisico.setId_AvanceFisico(GetIds.getIdAvanceFisico()+1);
		avanceFisico.setId_referencia(idReferencia);
		avanceFisico.setPAvanceFisico(new Double(0));
		avanceFisico.setPorcentaje_tendencia(new Double(1));
		avanceFisico.setTipo_Entidad(tipo);
		try {
			avanceFisicoEndpoint.insertAvance_Fisico(avanceFisico);
			System.out.println("avance fisico insertado correctamente");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error al insertar el avance fisico "+e.getMessage());
		}

}

}
