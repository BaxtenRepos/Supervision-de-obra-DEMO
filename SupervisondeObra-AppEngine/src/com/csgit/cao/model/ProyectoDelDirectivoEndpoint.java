package com.csgit.cao.model;

import com.csgit.cao.Constants;
import com.csgit.cao.EMF;
import com.csgit.cao.XmppSincronizacion;
import com.csgit.util.ObraUtil;
import com.csgit.util.ReporteDiarioUtil;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.log.LogQuery;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "communicationchannel", namespace = @ApiNamespace(ownerDomain = "csgit.com", ownerName = "csgit.com", packagePath = "cao.model"), clientIds = {
	Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID,
	Constants.IOS_CLIENT_ID }, audiences = { Constants.ANDROID_AUDIENCE }, version = "v1")
public class ProyectoDelDirectivoEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
//	@ApiMethod(name = "listProyectoDelDirectivo")
//	public CollectionResponse<ProyectoDelDirectivo> listProyectoDelDirectivo(
//			@Nullable @Named("cursor") String cursorString,
//			@Nullable @Named("limit") Integer limit) {
//
//		EntityManager mgr = null;
//		Cursor cursor = null;
//		List<ProyectoDelDirectivo> execute = null;
//
//		try {
//			mgr = getEntityManager();
//			Query query = mgr
//					.createQuery("select from ProyectoDelDirectivo as ProyectoDelDirectivo");
//			if (cursorString != null && cursorString != "") {
//				cursor = Cursor.fromWebSafeString(cursorString);
//				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
//			}
//
//			if (limit != null) {
//				query.setFirstResult(0);
//				query.setMaxResults(limit);
//			}
//
//			execute = (List<ProyectoDelDirectivo>) query.getResultList();
//			cursor = JPACursorHelper.getCursor(execute);
//			if (cursor != null)
//				cursorString = cursor.toWebSafeString();
//
//			// Tight loop for fetching all entities from datastore and accomodate
//			// for lazy fetch.
//			for (ProyectoDelDirectivo obj : execute)
//				;
//		} finally {
//			mgr.close();
//		}
//
//		return CollectionResponse.<ProyectoDelDirectivo> builder()
//				.setItems(execute).setNextPageToken(cursorString).build();
//	}
	
	@ApiMethod(name = "listProyectoDelDirectivo")
	public CollectionResponse<ProyectoDelDirectivo> listProyectoDelDirectivo(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit, @Named("directivo")Long directivo) {


		List<ProyectoDelDirectivo> proyectosDirectivo = new ArrayList<>();
		CollectionResponse<ProyectoDelDirectivo> proy = null;
		
		LogQuery query = LogQuery.Builder.withDefaults();
		query.includeAppLogs(true);
		
		CollectionResponse<Directivo_Proyecto> proyectos = new Directivo_ProyectoEndpoint().listDirectivo_Proyecto(null, null, directivo);
	//	ArrayList<Directivo_Proyecto>proye = (ArrayList<Directivo_Proyecto>) proyectos.getItems();
		for (Directivo_Proyecto elementos : proyectos.getItems()) {
			
			
			ProyectoDelDirectivo pd =  new ProyectoDelDirectivo();
			pd.setProyecto(new ProyectoEndpoint().getProyecto(elementos.getId_proyecto()));
			
			CollectionResponse<Avance_Financiero> afinanciero =  new Avance_FinancieroEndpoint().listAvance_Financiero(null, null, pd.getProyecto().getId_Proyecto(), Constants.Proyecto);
			ArrayList<Avance_Financiero> avancefinanciero = new ArrayList<>();
			for (Avance_Financiero elem : afinanciero.getItems()) {
			
				avancefinanciero.add(elem);
				
			}
			pd.setAvanceFinanciero(avancefinanciero);
			//ArrayList<Avance_Financiero>afinanciero2 = (ArrayList<Avance_Financiero>) afinanciero.getItems();
		
			CollectionResponse<Avance_Fisico>afisico =  new Avance_FisicoEndpoint().listAvance_Fisico(null, null, pd.getProyecto().getId_Proyecto(),Constants.Proyecto);
			//ArrayList<Avance_Fisico>afisico2 = (ArrayList<Avance_Fisico>) afisico.getItems();
			ArrayList<Avance_Fisico> avancefisico= new ArrayList<>();
			for (Avance_Fisico elem : afisico.getItems()) {
				avancefisico.add(elem);
				
			}
			pd.setAvanceFisico(avancefisico);
			CollectionResponse<Obra> obras  =new ObraEndpoint().listObra(null, null, pd.getProyecto().getId_Proyecto(), 0L, " ");
			//ArrayList<Obra>obraselement =  (ArrayList<Obra>) obras.getItems();
			ArrayList<ObraUtil>obrautil = new ArrayList<>();
			
			for (Obra element : obras.getItems()) {
				ObraUtil ou = new ObraUtil();
				ou.setObra(element);
				afinanciero =  new Avance_FinancieroEndpoint().listAvance_Financiero(null, null, ou.getObra().getId_Obra(), Constants.Obra);
				//afinanciero2 = (ArrayList<Avance_Financiero>) afinanciero.getItems();
				//ou.setAvanceFinanciero(afinanciero2.get(0));
				ArrayList<Avance_Financiero>avancefinanciero2 = new ArrayList<>();
				
				for (Avance_Financiero elem : afinanciero.getItems()) {
					avancefinanciero2.add(elem);
					
				}
				ou.setAvanceFinanciero(avancefinanciero2);

				afisico =  new Avance_FisicoEndpoint().listAvance_Fisico(null, null, ou.getObra().getId_Obra(),Constants.Obra);
				ArrayList<Avance_Fisico>avancefisico2 =  new ArrayList<>();
				for (Avance_Fisico elem : afisico.getItems()) {
					avancefisico2.add(elem);
					
				}
				ou.setAvanceFisico(avancefisico2);
				
				ArrayList<ReporteDiarioUtil> reportesdeobra = new ArrayList<>();
				CollectionResponse<ReporteDiario> repDiario =  new ReporteDiarioEndpoint().listReporteDiario(null, null, ou.getObra().getId_Obra(), " ");
				query.offset(repDiario.getItems().size()+"");
				System.out.println(repDiario.getItems().size()+"");
				
				//ArrayList<ReporteDiario> rd =  (ArrayList<ReporteDiario>) repDiario.getItems();
				for (ReporteDiario reporteDiario : repDiario.getItems()) {
					System.out.println(repDiario.getItems().size());
					ReporteDiarioUtil rdu = new ReporteDiarioUtil();
					rdu.setReporteDiario(reporteDiario);
					CollectionResponse<RepMaquinariaCatMaquinaria> repmaq =  new RepMaquinariaCatMaquinariaEndpoint().listRepMaquinariaCatMaquinaria(null, null, reporteDiario.getId_ReporteDiario(), " ");
					//ArrayList<RepMaquinariaCatMaquinaria>repmaqcatmaq =  (ArrayList<RepMaquinariaCatMaquinaria>) repmaq.getItems();
					//rdu.setRepMaquinariaCatMaquinaria(repmaqcatmaq.get(0));
					ArrayList<RepMaquinariaCatMaquinaria> maqcatmaq = new ArrayList<>();
					for (RepMaquinariaCatMaquinaria elem : repmaq.getItems()) {
						query.offset(repmaq.getItems().size()+"");
						
						maqcatmaq.add(elem);
						
					}
					rdu.setRepMaquinariaCatMaquinaria(maqcatmaq);
					reportesdeobra.add(rdu);
					ou.setReportesDiarios(reportesdeobra);
				
					
				}
				
				obrautil.add(ou);
				pd.setObras(obrautil);
			}
			
			 
			
			proyectosDirectivo.add(pd);
			
			
			
			
			
			//pd.setObras(obraselement);
		}
		
		

		return CollectionResponse.<ProyectoDelDirectivo> builder()
				.setItems(proyectosDirectivo).setNextPageToken(cursorString).build();

	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
//	@ApiMethod(name = "getProyectoDelDirectivo")
//	public ProyectoDelDirectivo getProyectoDelDirectivo(@Named("id") Long id) {
//		EntityManager mgr = getEntityManager();
//		ProyectoDelDirectivo proyectodeldirectivo = null;
//		try {
//			proyectodeldirectivo = mgr.find(ProyectoDelDirectivo.class, id);
//		} finally {
//			mgr.close();
//		}
//		return proyectodeldirectivo;
//	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param proyectodeldirectivo the entity to be inserted.
	 * @return The inserted entity.
	 */
//	@ApiMethod(name = "insertProyectoDelDirectivo")
//	public ProyectoDelDirectivo insertProyectoDelDirectivo(
//			ProyectoDelDirectivo proyectodeldirectivo) {
//		EntityManager mgr = getEntityManager();
//		try {
//			if (containsProyectoDelDirectivo(proyectodeldirectivo)) {
//				throw new EntityExistsException("Object already exists");
//			}
//			mgr.persist(proyectodeldirectivo);
//		} finally {
//			mgr.close();
//		}
//		return proyectodeldirectivo;
//	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param proyectodeldirectivo the entity to be updated.
	 * @return The updated entity.
	 */
//	@ApiMethod(name = "updateProyectoDelDirectivo")
//	public ProyectoDelDirectivo updateProyectoDelDirectivo(
//			ProyectoDelDirectivo proyectodeldirectivo) {
//		EntityManager mgr = getEntityManager();
//		try {
//			if (!containsProyectoDelDirectivo(proyectodeldirectivo)) {
//				throw new EntityNotFoundException("Object does not exist");
//			}
//			mgr.persist(proyectodeldirectivo);
//		} finally {
//			mgr.close();
//		}
//		return proyectodeldirectivo;
//	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
//	@ApiMethod(name = "removeProyectoDelDirectivo")
//	public void removeProyectoDelDirectivo(@Named("id") Long id) {
//		EntityManager mgr = getEntityManager();
//		try {
//			ProyectoDelDirectivo proyectodeldirectivo = mgr.find(
//					ProyectoDelDirectivo.class, id);
//			mgr.remove(proyectodeldirectivo);
//		} finally {
//			mgr.close();
//		}
//	}

//	private boolean containsProyectoDelDirectivo(
//			ProyectoDelDirectivo proyectodeldirectivo) {
//		EntityManager mgr = getEntityManager();
//		boolean contains = true;
//		try {
//			ProyectoDelDirectivo item = mgr.find(ProyectoDelDirectivo.class,
//					proyectodeldirectivo.getId());
//			if (item == null) {
//				contains = false;
//			}
//		} finally {
//			mgr.close();
//		}
//		return contains;
//	}

//	private static EntityManager getEntityManager() {
//		return EMF.get().createEntityManager();
//	}

}
