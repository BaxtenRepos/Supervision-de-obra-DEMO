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
public class Ref_calendarizacionEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listRef_calendarizacion")
	public CollectionResponse<Ref_calendarizacion> listRef_calendarizacion(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Ref_calendarizacion> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from Ref_calendarizacion as Ref_calendarizacion");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Ref_calendarizacion>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Ref_calendarizacion obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Ref_calendarizacion> builder()
				.setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getRef_calendarizacion")
	public Ref_calendarizacion getRef_calendarizacion(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Ref_calendarizacion ref_calendarizacion = null;
		try {
			ref_calendarizacion = mgr.find(Ref_calendarizacion.class, id);
		} finally {
			mgr.close();
		}
		return ref_calendarizacion;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param ref_calendarizacion the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertRef_calendarizacion")
	public Ref_calendarizacion insertRef_calendarizacion(
			Ref_calendarizacion ref_calendarizacion) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsRef_calendarizacion(ref_calendarizacion)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(ref_calendarizacion);
		} finally {
			mgr.close();
		}
		return ref_calendarizacion;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param ref_calendarizacion the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateRef_calendarizacion")
	public Ref_calendarizacion updateRef_calendarizacion(
			Ref_calendarizacion ref_calendarizacion) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsRef_calendarizacion(ref_calendarizacion)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(ref_calendarizacion);
		} finally {
			mgr.close();
		}
		return ref_calendarizacion;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeRef_calendarizacion")
	public void removeRef_calendarizacion(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Ref_calendarizacion ref_calendarizacion = mgr.find(
					Ref_calendarizacion.class, id);
			mgr.remove(ref_calendarizacion);
		} finally {
			mgr.close();
		}
	}

	private boolean containsRef_calendarizacion(
			Ref_calendarizacion ref_calendarizacion) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Ref_calendarizacion item = mgr.find(Ref_calendarizacion.class,
					ref_calendarizacion.getId_ref_Calendarizaion());
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
