package com.csgit.cao.model;

import com.csgit.cao.Constants;
import com.csgit.cao.EMF;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
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
public class Directivo_ProyectoEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listDirectivo_Proyecto")
	public CollectionResponse<Directivo_Proyecto> listDirectivo_Proyecto(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit, @Nullable @Named("directivo")Long directivo) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Directivo_Proyecto> execute = null;
		mgr = getEntityManager();
		Query query;
		if(directivo!=0){
//			
			 query = mgr
					.createQuery("select from Directivo_Proyecto as Directivo_Proyecto where Directivo_Proyecto.id_directivo="+directivo);
		}else{
			 query = mgr
					.createQuery("select from Directivo_Proyecto as Directivo_Proyecto");
//			
		}

		try {
			//mgr = getEntityManager();
			
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Directivo_Proyecto>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Directivo_Proyecto obj : execute)
				;
		} finally {
			mgr.close();
		}
		

		return CollectionResponse.<Directivo_Proyecto> builder()
				.setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getDirectivo_Proyecto")
	public Directivo_Proyecto getDirectivo_Proyecto(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Directivo_Proyecto directivo_proyecto = null;
		try {
			directivo_proyecto = mgr.find(Directivo_Proyecto.class, id);
		} finally {
			mgr.close();
		}
		return directivo_proyecto;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param directivo_proyecto the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertDirectivo_Proyecto")
	public Directivo_Proyecto insertDirectivo_Proyecto(
			Directivo_Proyecto directivo_proyecto) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsDirectivo_Proyecto(directivo_proyecto)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(directivo_proyecto);
		} finally {
			mgr.close();
		}
		return directivo_proyecto;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param directivo_proyecto the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateDirectivo_Proyecto")
	public Directivo_Proyecto updateDirectivo_Proyecto(
			Directivo_Proyecto directivo_proyecto) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsDirectivo_Proyecto(directivo_proyecto)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(directivo_proyecto);
		} finally {
			mgr.close();
		}
		return directivo_proyecto;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeDirectivo_Proyecto")
	public void removeDirectivo_Proyecto(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Directivo_Proyecto directivo_proyecto = mgr.find(
					Directivo_Proyecto.class, id);
			mgr.remove(directivo_proyecto);
		} finally {
			mgr.close();
		}
	}

	private boolean containsDirectivo_Proyecto(
			Directivo_Proyecto directivo_proyecto) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Directivo_Proyecto item = mgr.find(Directivo_Proyecto.class,
					directivo_proyecto.getId());
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
