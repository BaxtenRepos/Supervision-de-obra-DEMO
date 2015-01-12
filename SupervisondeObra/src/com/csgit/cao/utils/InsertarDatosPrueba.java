package com.csgit.cao.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

import com.csgit.cao.dao.ConstantesBD;
import com.csgit.cao.model.communicationchannel.model.Concepto;

public class InsertarDatosPrueba {

	ContentResolver contentR;
	
	public InsertarDatosPrueba(ContentResolver resolver){
		this.contentR = resolver;
	}
	
	public void insertEmpresas(){
		ContentValues values = new ContentValues();
		
		values.put(ConstantesBD.colEmpresas[0], 1);
		values.put(ConstantesBD.colEmpresas[1], 1);
		values.put(ConstantesBD.colEmpresas[2], "CONAGUA");
		
		Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEmpresas), values);
		Log.i("Insert Empresas", uri.toString());
		
		ContentValues values1 = new ContentValues();
		values1.put(ConstantesBD.colEmpresas[0], 2);
		values1.put(ConstantesBD.colEmpresas[1], 1);
		values1.put(ConstantesBD.colEmpresas[2], "SEGOB");
		
		Uri uri1 = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEmpresas), values1);
		Log.i("Insert Empresas", uri1.toString());
		
	}
	
	public void insertObra(){
		
		ContentValues values = new ContentValues();
		for (int i = 1; i <= 1; i++) {
			
			values.put(ConstantesBD.ColObras[0], 2);//idobra
			values.put(ConstantesBD.ColObras[1], i);//id proyecto
			values.put(ConstantesBD.ColObras[2], 40958);//no contrato
			values.put(ConstantesBD.ColObras[3], "GAYH280686MCH567");// rfc
			values.put(ConstantesBD.ColObras[4], "PROTECCION DE TALUDES EN BORDOS EN CARRILLO PUERTO D.F.");//nombre obra
//			values.put(ConstantesBD.ColObras[4], "PROTECCION DE TALUDES");//nombre obra
			values.put(ConstantesBD.ColObras[5], 2);//idGobierno* Segob
			values.put(ConstantesBD.ColObras[6], 1);//idsecretaria*
			values.put(ConstantesBD.ColObras[7], 1);//iddependencia*
			values.put(ConstantesBD.ColObras[8], "SEGOB");//direccion
			values.put(ConstantesBD.ColObras[9], "Carolina Franco");//subdireccion
			values.put(ConstantesBD.ColObras[10], "Area");//area
			values.put(ConstantesBD.ColObras[11], "EXCAVACION POR MEDIOS MECANICOS (EXCAVADORA BRAZO LARGO Y/O DRAGA DE ARRASTRE) EN CUALQUIER TIPO DE MATERIAL EXCEPTO ROCA, INCLUYE: TRASPALEOS HASTA 5 ESTACIONES. ALMACENAMIENTO Y ACAMELLONAMIENTO DEL MATERIAL SOBRE LA BANQUETA DEL RIO PARA SU CARGA Y POSTERIOR TRASLADO HASTA EL SITIO DE DISPOSICION FINAL. INCLUYE: EQUIPO, MANO DE OBRA, HERRAMIENTA Y TODO LO NECESARIO PARA SU CORRECTA EJECUCION.");//descripcion
			values.put(ConstantesBD.ColObras[12], "08/07/2013");//fecha contrato
			values.put(ConstantesBD.ColObras[13], "Fijo");//tipocontrato
			values.put(ConstantesBD.ColObras[14], 8456472.78);//importecontrato sin iva
			values.put(ConstantesBD.ColObras[15], "Juan Hugalde");//nombreejercitofiscal 1
			values.put(ConstantesBD.ColObras[16], 7456472.78);//importe fiscal sin iva
			values.put(ConstantesBD.ColObras[17], 7456472.78);//importeconvenioampliacion
			values.put(ConstantesBD.ColObras[18], 7456472.78);//importeconvenioreduccion
			values.put(ConstantesBD.ColObras[19], 7456472.78);//importe ajustes costos
			values.put(ConstantesBD.ColObras[20], "10/09/2014");//fecha inicio contrato
			values.put(ConstantesBD.ColObras[21], "12/10/2016");//fecha termino contrato
			values.put(ConstantesBD.ColObras[22], "87463");//partida presupuestal
			values.put(ConstantesBD.ColObras[23], "2456472.78");//anticipo
			values.put(ConstantesBD.ColObras[24], 283);//no fianza anticipo
			values.put(ConstantesBD.ColObras[25], "09/07/2013");//fecha fianza anticipo
			values.put(ConstantesBD.ColObras[26], 7456472.78);//monto fianza anticipo
			values.put(ConstantesBD.ColObras[27], "7456472.78");//no fianza cumplimiento
			values.put(ConstantesBD.ColObras[28], "07/06/2016");//fecha fianza cumplimiento
			values.put(ConstantesBD.ColObras[29], 7456472.78);//monto fianza cumplimiento
			values.put(ConstantesBD.ColObras[30], "Carlos Barragan");//cargo revision 1
			values.put(ConstantesBD.ColObras[31], "Karen Oca");//nombre revision 1
			values.put(ConstantesBD.ColObras[32], "Mario Peralta");//cargo revision 2
			values.put(ConstantesBD.ColObras[33], "Juan Pérez");//nombre revision 2
			values.put(ConstantesBD.ColObras[34], "Pedro Palermo");//nombre quien autoriza
			values.put(ConstantesBD.ColObras[35], "Guillermo Parra");//cargo vobo
			values.put(ConstantesBD.ColObras[36], "Laura Flores");//nombre vobo
			values.put(ConstantesBD.ColObras[37], "Distrito Federal");//entidad federativa
			values.put(ConstantesBD.ColObras[38], 1);//idEmpresa contratista (Empresa: CECAMA)
			values.put(ConstantesBD.ColObras[39], "Jorge Aceituno");//superintendente
			values.put(ConstantesBD.ColObras[40], "Borrador");//borrador
			values.put(ConstantesBD.ColObras[41], "10");//limite desvio
			values.put(ConstantesBD.ColObras[42], 1);//ubicacion
			
			Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaObra), values);
			Log.i("Insert Obra", uri.toString());
		}
	}
	
	public void insertConceptos(){
		
		List<Concepto> conceptos = new ArrayList<Concepto>();
		Concepto concepto1 = new Concepto();
		concepto1.setIdConcepto(Long.parseLong("1"));
		concepto1.setIdObra(Long.parseLong("1"));
		concepto1.setClave("1");
		concepto1.setDescripcion("Instrumentación de la linea base para los levantamientos topográficos, georeferenciada a la Red Geodésica Nacional Activa del INEGI");
		concepto1.setUnidadMedida("Estación");
		concepto1.setCantidadTotal(2D);
		concepto1.setPrecioUnitario(Double.parseDouble("20000"));
		concepto1.setImporte(Double.parseDouble("40000"));
		concepto1.setCantidadAvance(0D);
		concepto1.setFechaInicio("01 Julio 2014");
		concepto1.setFechaFin("30 Julio 2014");
		
		Concepto concepto2 = new Concepto();
		concepto2.setIdConcepto(Long.parseLong("2"));
		concepto2.setIdObra(Long.parseLong("1"));
		concepto2.setClave("2");
		concepto2.setDescripcion("Postproceso de los datos obtenidos en campo y cálculo de la información geodésica");
		concepto2.setUnidadMedida("Estudio");
		concepto2.setCantidadTotal(1D);
		concepto2.setPrecioUnitario(Double.parseDouble("25000"));
		concepto2.setImporte(Double.parseDouble("25000"));
		concepto2.setCantidadAvance(0D);
		concepto2.setFechaInicio("01 Julio 2014");
		concepto2.setFechaFin("30 Julio 2014");
		
		Concepto concepto3 = new Concepto();
		concepto3.setIdConcepto(Long.parseLong("3"));
		concepto3.setIdObra(Long.parseLong("1"));
		concepto3.setClave("3");
		concepto3.setDescripcion("Instrumentación de bancos de nivel superficiales");
		concepto3.setUnidadMedida("Mojonera");
		concepto3.setCantidadTotal(100D);
		concepto3.setPrecioUnitario(Double.parseDouble("15"));
		concepto3.setImporte(Double.parseDouble("1500"));
		concepto3.setCantidadAvance(0D);
		concepto3.setFechaInicio("01 Julio 2014");
		concepto3.setFechaFin("30 Julio 2014");
		
		Concepto concepto4 = new Concepto();
		concepto4.setIdConcepto(Long.parseLong("4"));
		concepto4.setIdObra(Long.parseLong("1"));
		concepto4.setClave("4");
		concepto4.setDescripcion("Instrumentación de bancos de nivel flotantes");
		concepto4.setUnidadMedida("Banco");
		concepto4.setCantidadTotal(100D);
		concepto4.setPrecioUnitario(Double.parseDouble("15"));
		concepto4.setImporte(Double.parseDouble("1500"));
		concepto4.setCantidadAvance(0D);
		concepto4.setFechaInicio("01 Julio 2014");
		concepto4.setFechaFin("30 Julio 2014");
		
		Concepto concepto5 = new Concepto();
		concepto5.setIdConcepto(Long.parseLong("5"));
		concepto5.setIdObra(Long.parseLong("1"));
		concepto5.setClave("5");
		concepto5.setDescripcion("Instrumentación de bancos de nivel profundos");
		concepto5.setUnidadMedida("Banco");
		concepto5.setCantidadTotal(100D);
		concepto5.setPrecioUnitario(Double.parseDouble("15"));
		concepto5.setImporte(Double.parseDouble("1500"));
		concepto5.setCantidadAvance(0D);
		concepto5.setFechaInicio("01 Julio 2014");
		concepto5.setFechaFin("30 Julio 2014");
		
		Concepto concepto6 = new Concepto();
		concepto6.setIdConcepto(Long.parseLong("6"));
		concepto6.setIdObra(Long.parseLong("1"));
		concepto6.setClave("6");
		concepto6.setDescripcion("Suministro y colocación de cercado de malla ciclónica os imilar, para protección de la estación de medición y control altimétrico. Inlcuye: puerta de acceso y postes ahogados en concreto");
		concepto6.setUnidadMedida("Metros");
		concepto6.setCantidadTotal(100D);
		concepto6.setPrecioUnitario(Double.parseDouble("15"));
		concepto6.setImporte(Double.parseDouble("1500"));
		concepto6.setCantidadAvance(0D);
		concepto6.setFechaInicio("01 Julio 2014");
		concepto6.setFechaFin("30 Julio 2014");
		
		Concepto concepto7 = new Concepto();
		concepto7.setIdConcepto(Long.parseLong("7"));
		concepto7.setIdObra(Long.parseLong("1"));
		concepto7.setClave("7");
		concepto7.setDescripcion("Nivelación diferencial de precisión a bancos de nivel instrumentados: profundos, flotantes y superficiales, para determinación de hundimientos y referenciarla altimetría del estudio (dos recorridos de nivelación).");
		concepto7.setUnidadMedida("KM");
		concepto7.setCantidadTotal(100D);
		concepto7.setPrecioUnitario(Double.parseDouble("15"));
		concepto7.setImporte(Double.parseDouble("1500"));
		concepto7.setCantidadAvance(0D);
		concepto7.setFechaInicio("01 Julio 2014");
		concepto7.setFechaFin("30 Julio 2014");
		
		Concepto concepto8 = new Concepto();
		concepto8.setIdConcepto(Long.parseLong("8"));
		concepto8.setIdObra(Long.parseLong("1"));
		concepto8.setClave("8");
		concepto8.setDescripcion("Procesamiento de los datos obtenidos en campo y cálculo de la información topográfica y/o batimétrica.");
		concepto8.setUnidadMedida("Estudio");
		concepto8.setCantidadTotal(100D);
		concepto8.setPrecioUnitario(Double.parseDouble("15"));
		concepto8.setImporte(Double.parseDouble("1500"));
		concepto8.setCantidadAvance(0D);
		concepto8.setFechaInicio("01 Julio 2014");
		concepto8.setFechaFin("30 Julio 2014");
		
		Concepto concepto9 = new Concepto();
		concepto9.setIdConcepto(Long.parseLong("9"));
		concepto9.setIdObra(Long.parseLong("1"));
		concepto9.setClave("9");
		concepto9.setDescripcion("Elaboración de planos topográficos y/o batimétricos.");
		concepto9.setUnidadMedida("Plano");
		concepto9.setCantidadTotal(100D);
		concepto9.setPrecioUnitario(Double.parseDouble("15"));
		concepto9.setImporte(Double.parseDouble("1500"));
		concepto9.setCantidadAvance(0D);
		concepto9.setFechaInicio("01 Julio 2014");
		concepto9.setFechaFin("30 Julio 2014");
		
		Concepto concepto10 = new Concepto();
		concepto10.setIdConcepto(Long.parseLong("10"));
		concepto10.setIdObra(Long.parseLong("1"));
		concepto10.setClave("10");
		concepto10.setDescripcion("Proyección de hundimientos.");
		concepto10.setUnidadMedida("Estudio");
		concepto10.setCantidadTotal(100D);
		concepto10.setPrecioUnitario(Double.parseDouble("15"));
		concepto10.setImporte(Double.parseDouble("1500"));
		concepto10.setCantidadAvance(0D);
		concepto10.setFechaInicio("01 Julio 2014");
		concepto10.setFechaFin("30 Julio 2014");	
		
		conceptos.add(concepto1);
		conceptos.add(concepto2);
		conceptos.add(concepto3);
		conceptos.add(concepto4);
		conceptos.add(concepto5);
		conceptos.add(concepto6);
		conceptos.add(concepto7);
		conceptos.add(concepto8);
		conceptos.add(concepto9);
		conceptos.add(concepto10);
		
		ContentValues values = new ContentValues();
		for (int i = 0; i < conceptos.size(); i++) {
			values.put(ConstantesBD.ColConceptos[0], conceptos.get(i).getIdConcepto());
			values.put(ConstantesBD.ColConceptos[1], conceptos.get(i).getIdObra());
			values.put(ConstantesBD.ColConceptos[2], conceptos.get(i).getClave());
			values.put(ConstantesBD.ColConceptos[3], conceptos.get(i).getDescripcion());
			values.put(ConstantesBD.ColConceptos[4], conceptos.get(i).getUnidadMedida());
			values.put(ConstantesBD.ColConceptos[5], conceptos.get(i).getCantidadTotal());//cantidad total
			values.put(ConstantesBD.ColConceptos[6], conceptos.get(i).getPrecioUnitario());//precio unitario
			values.put(ConstantesBD.ColConceptos[7], conceptos.get(i).getImporte());//importe
			values.put(ConstantesBD.ColConceptos[8], conceptos.get(i).getCantidadAvance());//cantidad avance
			values.put(ConstantesBD.ColConceptos[9], conceptos.get(i).getFechaInicio());
			values.put(ConstantesBD.ColConceptos[10], conceptos.get(i).getFechaFin());
			
			Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaConcepto), values);
			Log.i("Insert Conceptos", uri.toString());
		}
		
	}
	
	public void insertMaquinaria(){
		
		int aux = 1;
		for (int i = 1; i <= 3; i++) {
			ContentValues values = new ContentValues();	
			
			values.put(ConstantesBD.ColCatTipoMaquinaria[0], i);
			if(i==1){
				values.put(ConstantesBD.ColCatTipoMaquinaria[1], "Pesada");
			}else if(i==2){
				values.put(ConstantesBD.ColCatTipoMaquinaria[1], "Ligera");
			}else if(i==3){
				values.put(ConstantesBD.ColCatTipoMaquinaria[1], "Equipo");
			}
			values.put(ConstantesBD.ColCatTipoMaquinaria[2], "Descripción...");
			
			Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaCatTipoMaquinaria), values);
			Log.i("Insert tipo maquinaria", uri.toString());
			
			for (int j = 1; j <= 15; j++) {
				ContentValues values1 = new ContentValues();
				values1.put(ConstantesBD.ColCatMaquinaria[0], aux);
				values1.put(ConstantesBD.ColCatMaquinaria[1], i);
				if(i==1){
					values1.put(ConstantesBD.ColCatMaquinaria[2], "/storage/emulated/0/Download/ligera.jpg");
				}else if(i==2){
					values1.put(ConstantesBD.ColCatMaquinaria[2], "/storage/emulated/0/Download/pesada.jpg");
				}else if(i==3){
					if(j==0){
						values1.put(ConstantesBD.ColCatMaquinaria[2], "/storage/emulated/0/bluetooth/20140720_114135.jpg");
					}else if(j==1){
						values1.put(ConstantesBD.ColCatMaquinaria[2], "/storage/emulated/0/Download/ligera");
					}else if(j==2){
						
						values1.put(ConstantesBD.ColCatMaquinaria[2], "/storage/emulated/0/Download/pesada.jpg");
					}else
						values1.put(ConstantesBD.ColCatMaquinaria[2], "/storage/emulated/0/Download/equipo.jpg");
//					values1.put(ConstantesBD.ColCatMaquinaria[2], "/storage/emulated/0/Download/equipo.jpg");
				}
				
				values1.put(ConstantesBD.ColCatMaquinaria[3], "Pala escabadora " + i + j);
				
				Uri uri2 = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaCatMaquinaria), values1);
				Log.i("Insert maquinaria", uri2.toString());	
				aux++;
			}
			
		}
		
	}
	
	public void insertPersonal(){
		ContentValues values = new ContentValues();
		for (int i = 1; i <= 100; i++) {
			values.put(ConstantesBD.ColCatPersonal[0], i);
			values.put(ConstantesBD.ColCatPersonal[1], "Personal ... " + i);
			values.put(ConstantesBD.ColCatPersonal[2], "Descripción... "+ i);
			
			Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaCatPersonal), values);
			Log.i("Insert CatPersonal", uri.toString());
		}
	}
	
	public void insertUbicacion(){
		ContentValues values = new ContentValues();
		for (int i = 1; i <= 1; i++) {
			values.put(ConstantesBD.ColUbicacionObra[0], i);
//			if(i==1){
//				values.put(ConstantesBD.ColUbicacionObra[1], "40.5891778,-4.14795");
//			}if(i==2){
//				values.put(ConstantesBD.ColUbicacionObra[1], "40.5891778,-4.14795:40.41852783096503,-3.714108467102051");
//			}if(i==3){
//				values.put(ConstantesBD.ColUbicacionObra[1], "40.5891778,-4.14795:40.41852783096503,-3.714108467102051:40.5891778,-4.14795");
//			}if(i==4){
				values.put(ConstantesBD.ColUbicacionObra[1], "40.5891778,-4.14795:40.8975,-4.00444:40.41852783096503,-3.714108467102051:40.03639,-3.60917:"
						+ "40.5891778,-4.14795");
//			}
			
			Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaUbicacionObra), values);
			Log.i("Insert UbicacionObra", uri.toString());
		}
	}
	
	
	public void insertAvanceFisico(){
		
		ContentValues values1 = new ContentValues();
		for (int i = 1;i <= 10;i++){
			values1.put(ConstantesBD.ColAvanceFisico[0], i);
			values1.put(ConstantesBD.ColAvanceFisico[1], 1);
			values1.put(ConstantesBD.ColAvanceFisico[2], (i*10));
			values1.put(ConstantesBD.ColAvanceFisico[3], (i*15));
			values1.put(ConstantesBD.ColAvanceFisico[4], "06 Julio 2014");
			values1.put(ConstantesBD.ColAvanceFisico[5], 1);
			
			Uri uri1 = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaAvanceFisico), values1);
			Log.i("Insert fisico", uri1.toString());	
		}
	}
	
	public void insertAvanceFinanciero(){
		
		ContentValues values = new ContentValues();
		for (int i = 1;i <= 10;i++){
			values.put(ConstantesBD.ColAvanceFinanciero[0], i);
			values.put(ConstantesBD.ColAvanceFinanciero[1], 1);
			values.put(ConstantesBD.ColAvanceFinanciero[2], (i*8));
			values.put(ConstantesBD.ColAvanceFinanciero[3], (i*12.5));
			values.put(ConstantesBD.ColAvanceFinanciero[4], "30 Julio 2014");
			values.put(ConstantesBD.ColAvanceFinanciero[5], 1);
			
			Uri uri = contentR.insert(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaAvanceFinanciero), values);
			Log.i("Insert financiero", uri.toString());	
		}
		
	}
	
}
