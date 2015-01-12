package com.csgit.cao.model;

import com.csgit.cao.Constants;
import com.csgit.cao.EMF;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.users.User;
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
public class Avance_FinancieroEndpoint {

	/**
	 * This method lists all the entities inserted in datastore. It uses HTTP
	 * GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 *         persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listAvance_Financiero")
	public CollectionResponse<Avance_Financiero> listAvance_Financiero(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit,
			@Named("id_referencia") Long id,
		    @Named("Tipo_Entidad") Integer tipo) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Avance_Financiero> execute = null;

		try {
			mgr = getEntityManager();
			Query query;
			if (id != 0) {
				query = mgr
						.createQuery("select from Avance_Financiero as Avance_Financiero where Avance_Financiero.id_referencia ="
								+ id
								+ " and Avance_Financiero.Tipo_Entidad ="
								+ tipo);

			} else {

				query = mgr
						.createQuery("select from Avance_Financiero as Avance_Financiero");
			}
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Avance_Financiero>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and
			// accomodate
			// for lazy fetch.
			for (Avance_Financiero obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Avance_Financiero> builder()
				.setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET
	 * method.
	 *
	 * @param id
	 *            the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getAvance_Financiero")
	public Avance_Financiero getAvance_Financiero(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Avance_Financiero avance_financiero = null;
		try {
			avance_financiero = mgr.find(Avance_Financiero.class, id);
		} finally {
			mgr.close();
		}
		return avance_financiero;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity
	 * already exists in the datastore, an exception is thrown. It uses HTTP
	 * POST method.
	 *
	 * @param avance_financiero
	 *            the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertAvance_Financiero")
	public Avance_Financiero insertAvance_Financiero(
			Avance_Financiero avance_financiero) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsAvance_Financiero(avance_financiero)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(avance_financiero);
		} finally {
			mgr.close();
		}
		return avance_financiero;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does
	 * not exist in the datastore, an exception is thrown. It uses HTTP PUT
	 * method.
	 *
	 * @param avance_financiero
	 *            the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateAvance_Financiero")
	public Avance_Financiero updateAvance_Financiero(
			Avance_Financiero avance_financiero) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsAvance_Financiero(avance_financiero)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(avance_financiero);
		} finally {
			mgr.close();
		}
		return avance_financiero;
	}

	/**
	 * This method removes the entity with primary key id. It uses HTTP DELETE
	 * method.
	 *
	 * @param id
	 *            the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeAvance_Financiero")
	public void removeAvance_Financiero(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Avance_Financiero avance_financiero = mgr.find(
					Avance_Financiero.class, id);
			mgr.remove(avance_financiero);
		} finally {
			mgr.close();
		}
	}

	private boolean containsAvance_Financiero(
			Avance_Financiero avance_financiero) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Avance_Financiero item = mgr.find(Avance_Financiero.class,
					avance_financiero.getId_AvanceFinaciero());
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
