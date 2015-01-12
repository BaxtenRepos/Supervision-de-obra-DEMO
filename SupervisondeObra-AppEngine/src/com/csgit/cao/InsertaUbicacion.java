package com.csgit.cao;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

import com.csgit.cao.model.Empresa;
import com.csgit.cao.model.Ubicaciones;
import com.csgit.cao.model.UbicacionesEndpoint;
import com.csgit.entity.PuntoEntity;
import com.google.api.server.spi.response.CollectionResponse;

public class InsertaUbicacion {

	private  UbicacionesEndpoint ubication;
	private  Ubicaciones ubications;
	private  String[] CadenaUbicaciones;
	
	
	InsertaUbicacion(){
		ubication = new UbicacionesEndpoint();
		ubications = new Ubicaciones();
		

		
		
	}
	
	
	public InsertaUbicacion(PuntoEntity[] ubicaciones, Integer Tipo_Entidad) {
		// TODO Auto-generated constructor stub
		
		

	//	ubications.setId_Ubicacion(id_Ubicacion);
	}
	
	public   long insertaPuntos(PuntoEntity[] puntos){
	//	CadenaUbicaciones = new String[(puntos.length)*2];
		CadenaUbicaciones = new String[(puntos.length)];
		int i=0;
		long id=0;
		
				
		for (PuntoEntity puntoEntity : puntos) {
			CadenaUbicaciones[i]=puntoEntity.getK()+","+puntoEntity.getD();
			i++;
			//CadenaUbicaciones[i]=puntoEntity.getB();
			//i++;
		}
		
		id=GetIds.getIdUbicacion()+1;
		ubications.setId_Ubicacion(id);
		ubications.setUbicacion(CadenaUbicaciones);
		
	
		
		
		System.out.println(CadenaUbicaciones);
		
		
		try {
		ubication.insertUbicaciones(ubications);
		System.out.println("ubicacion insertada sin problemas con el id = "+id);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ocurrio un error al insertar la ubicacion el error es : "+e.getMessage());
			//retornamos un valor 0 para identificar el error
			id=0;
		}
		
		return id;
	}
	
	public  void updatePunto(PuntoEntity[] puntos , long id){
		//	CadenaUbicaciones = new String[(puntos.length)*2];
			CadenaUbicaciones = new String[(puntos.length)];
			int i=0;
		
			
					
			for (PuntoEntity puntoEntity : puntos) {
				CadenaUbicaciones[i]=puntoEntity.getK()+","+puntoEntity.getD();
				i++;
				//CadenaUbicaciones[i]=puntoEntity.getB();
				//i++;
			}
			
	
	
			ubications.setId_Ubicacion(id);
			ubications.setUbicacion(CadenaUbicaciones);
			ubication.updateUbicaciones(ubications);
			
		
			
			
			System.out.println(CadenaUbicaciones);
			
			
			
		}
	
	
//	public Long  returnId(){
//		long id=0;
//		CollectionResponse lista;
//		Collection<Ubicaciones>lista1;
//				
//		lista =ubication.listUbicaciones(null,null);//traemos la lista de empresas insertadas
//		lista1=lista.getItems();
//	
//	    for (Ubicaciones elem : lista1) {
//	        if(elem.getId_Ubicacion() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
//	        	id = elem.getId_Ubicacion();
//	    }
//
//		return id;	
//	}



}
