package com.csgit.cao.model;

import com.csgit.cao.Constants;
import com.csgit.cao.EMF;
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
public class DirectorioEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listDirectorio")
	public CollectionResponse<Directorio> listDirectorio(
		   @Nullable  @Named("cursor") String cursorString,
			@Nullable  @Named("limit") Integer limit, @Named("tipoReferencia") Long tipoReferencia, @Named("idReferencia") Long idReferencia) {

//		EntityManager mgr = null;
//		Cursor cursor = null;
//		List<Directorio> execute = null;
//		mgr = getEntityManager();
//		Query query;
//	
//			query = mgr
//				.createQuery("select from Directorio as Directorio");
//
//		
//		try {
		
		EntityManager mgr = null;
		Cursor cursor = null;
		List<Directorio> execute = null;
		Query query = null;
		LogQuery log = LogQuery.Builder.withDefaults();
	    log.includeAppLogs(true);
		try {
			mgr = getEntityManager();
			if(idReferencia!=0 ){
				query = mgr.createQuery("select from Directorio as Directorio where Directorio.idReferencia="+idReferencia + "and Directorio.TipoReferencia="+tipoReferencia);
				  log.offset("se solicito la lista solo con el tipo referencia = "+tipoReferencia);
				  System.out.println("se solicito la lista solo con el tipo referencia = "+tipoReferencia);
			}
			else 
				query = mgr
				.createQuery("select from Directorio as Directorio");
			
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Directorio>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Directorio obj : execute)
				;
		} finally {
			mgr.close();
		}
		
		return CollectionResponse.<Directorio> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getDirectorio")
	public Directorio getDirectorio(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Directorio directorio = null;
		
		
		
		try {
			directorio = mgr.find(Directorio.class, id);
		} finally {
			mgr.close();
		}
		
		return directorio;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param directorio the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertDirectorio")
	public Directorio insertDirectorio(Directorio directorio) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsDirectorio(directorio)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(directorio);
		} finally {
			mgr.close();
		}
		return directorio;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param directorio the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateDirectorio")
	public Directorio updateDirectorio(Directorio directorio) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsDirectorio(directorio)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(directorio);
		} finally {
			mgr.close();
		}
		return directorio;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeDirectorio")
	public void removeDirectorio(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Directorio directorio = mgr.find(Directorio.class, id);
			mgr.remove(directorio);
		} finally {
			mgr.close();
		}
	}

	private boolean containsDirectorio(Directorio directorio) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Directorio item = mgr.find(Directorio.class,
					directorio.getIdDirectorio());
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
