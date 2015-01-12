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
public class ReportsmodelEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listReportsmodel")
	public CollectionResponse<Reportsmodel> listReportsmodel(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit, @Named("idProyecto") Long idproyecto, @Named("idObra") Long idobra) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Reportsmodel> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from Reportsmodel as Reportsmodel where Reportsmodel.IdProyecto = "+idproyecto+" and Reportsmodel.IdObra = " +idobra);
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Reportsmodel>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Reportsmodel obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Reportsmodel> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getReportsmodel")
	public Reportsmodel getReportsmodel(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Reportsmodel reportsmodel = null;
		try {
			reportsmodel = mgr.find(Reportsmodel.class, id);
		} finally {
			mgr.close();
		}
		return reportsmodel;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param reportsmodel the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertReportsmodel")
	public Reportsmodel insertReportsmodel(Reportsmodel reportsmodel) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsReportsmodel(reportsmodel)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(reportsmodel);
		} finally {
			mgr.close();
		}
		return reportsmodel;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param reportsmodel the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateReportsmodel")
	public Reportsmodel updateReportsmodel(Reportsmodel reportsmodel) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsReportsmodel(reportsmodel)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(reportsmodel);
		} finally {
			mgr.close();
		}
		return reportsmodel;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeReportsmodel")
	public void removeReportsmodel(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Reportsmodel reportsmodel = mgr.find(Reportsmodel.class, id);
			mgr.remove(reportsmodel);
		} finally {
			mgr.close();
		}
	}

	private boolean containsReportsmodel(Reportsmodel reportsmodel) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Reportsmodel item = mgr.find(Reportsmodel.class,
					reportsmodel.getIdReporte());
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
