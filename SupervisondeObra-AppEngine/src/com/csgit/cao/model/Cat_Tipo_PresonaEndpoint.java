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
public class Cat_Tipo_PresonaEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listCat_Tipo_Presona")
	public CollectionResponse<Cat_Tipo_Presona> listCat_Tipo_Presona(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Cat_Tipo_Presona> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from Cat_Tipo_Presona as Cat_Tipo_Presona");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Cat_Tipo_Presona>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Cat_Tipo_Presona obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Cat_Tipo_Presona> builder()
				.setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getCat_Tipo_Presona")
	public Cat_Tipo_Presona getCat_Tipo_Presona(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Cat_Tipo_Presona cat_tipo_presona = null;
		try {
			cat_tipo_presona = mgr.find(Cat_Tipo_Presona.class, id);
		} finally {
			mgr.close();
		}
		return cat_tipo_presona;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param cat_tipo_presona the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertCat_Tipo_Presona")
	public Cat_Tipo_Presona insertCat_Tipo_Presona(
			Cat_Tipo_Presona cat_tipo_presona) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsCat_Tipo_Presona(cat_tipo_presona)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(cat_tipo_presona);
		} finally {
			mgr.close();
		}
		return cat_tipo_presona;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param cat_tipo_presona the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateCat_Tipo_Presona")
	public Cat_Tipo_Presona updateCat_Tipo_Presona(
			Cat_Tipo_Presona cat_tipo_presona) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsCat_Tipo_Presona(cat_tipo_presona)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(cat_tipo_presona);
		} finally {
			mgr.close();
		}
		return cat_tipo_presona;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeCat_Tipo_Presona")
	public void removeCat_Tipo_Presona(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Cat_Tipo_Presona cat_tipo_presona = mgr.find(
					Cat_Tipo_Presona.class, id);
			mgr.remove(cat_tipo_presona);
		} finally {
			mgr.close();
		}
	}

	private boolean containsCat_Tipo_Presona(Cat_Tipo_Presona cat_tipo_presona) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Cat_Tipo_Presona item = mgr.find(Cat_Tipo_Presona.class,
					cat_tipo_presona.getId_Tipo_Persona());
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
