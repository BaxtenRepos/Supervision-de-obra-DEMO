package com.csgit.cao.model;

import com.csgit.cao.Constants;
import com.csgit.cao.EMF;
import com.csgit.cao.GetIds;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.log.LogQuery;
import com.google.appengine.datanucleus.query.JPACursorHelper;

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
public class ReporteDiarioEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listReporteDiario")
	public CollectionResponse<ReporteDiario> listReporteDiario(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit, @Named("idObra") Long idObra,@Named("email")String email) {

//		EntityManager mgr = null;
//		Cursor cursor = null;
//		List<ReporteDiario> execute = null;
//
//		try {
//			mgr = getEntityManager();
//			Query query = mgr
//					.createQuery("select from ReporteDiario as ReporteDiario");
		
		
		EntityManager mgr = null;
		Cursor cursor = null;
		List<ReporteDiario> execute = null;
		Query query = null;
		LogQuery log = LogQuery.Builder.withDefaults();
	    log.includeAppLogs(true);
		try {
			mgr = getEntityManager();
			if(idObra!=0 ){
				query = mgr.createQuery("select from ReporteDiario as ReporteDiario where ReporteDiario.id_Obra="+idObra );
				  log.offset("se solicito la lista solo con el tipo referencia = "+idObra);
				  System.out.println("se solicito la lista solo con el tipo referencia = "+idObra);
			}
			else if(email.contains("%20"))
			{
				email.replaceAll("%20","");
				String prueba3 = email.replace("%20", "");
				email=prueba3;
				if(!email.trim().equalsIgnoreCase("")){
					if(email.contains("%40")){
						String []prueba = email.split("%40");
						email=prueba[0]+"@"+prueba[1];
					}
	
				    query = mgr.createQuery("select from ReporteDiario as ReporteDiario where ReporteDiario.Email="+email);
	  
				}
			}

			else 
				query = mgr
				.createQuery("select from ReporteDiario as ReporteDiario");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<ReporteDiario>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (ReporteDiario obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<ReporteDiario> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
//	@ApiMethod(name = "getReporteDiario")
//	public ReporteDiario getReporteDiario(@Named("id") Long id) {
//		EntityManager mgr = getEntityManager();
//		ReporteDiario reportediario = null;
//		try {
//			reportediario = mgr.find(ReporteDiario.class, id);
//		} finally {
//			mgr.close();
//		}
//		return reportediario;
//	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param reportediario the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertReporteDiario")
	public ReporteDiario insertReporteDiario(ReporteDiario reportediario) {
		EntityManager mgr = getEntityManager();
		reportediario.setId_ReporteDiario(GetIds.getIdReporteDiario()+1);
		try {
			if (containsReporteDiario(reportediario)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(reportediario);
		} finally {
			mgr.close();
		}
		return reportediario;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param reportediario the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateReporteDiario")
	public ReporteDiario updateReporteDiario(ReporteDiario reportediario) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsReporteDiario(reportediario)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(reportediario);
		} finally {
			mgr.close();
		}
		return reportediario;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeReporteDiario")
	public void removeReporteDiario(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			ReporteDiario reportediario = mgr.find(ReporteDiario.class, id);
			mgr.remove(reportediario);
		} finally {
			mgr.close();
		}
	}

	private boolean containsReporteDiario(ReporteDiario reportediario) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			ReporteDiario item = mgr.find(ReporteDiario.class,
					reportediario.getId_ReporteDiario());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
