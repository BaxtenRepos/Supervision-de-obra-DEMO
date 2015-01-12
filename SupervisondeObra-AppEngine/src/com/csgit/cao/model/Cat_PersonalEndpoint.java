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
public class Cat_PersonalEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listCat_Personal")
	public CollectionResponse<Cat_Personal> listCat_Personal(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Cat_Personal> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from Cat_Personal as Cat_Personal");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Cat_Personal>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Cat_Personal obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Cat_Personal> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getCat_Personal")
	public Cat_Personal getCat_Personal(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Cat_Personal cat_personal = null;
		try {
			cat_personal = mgr.find(Cat_Personal.class, id);
		} finally {
			mgr.close();
		}
		return cat_personal;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param cat_personal the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertCat_Personal")
	public Cat_Personal insertCat_Personal(Cat_Personal cat_personal) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsCat_Personal(cat_personal)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(cat_personal);
		} finally {
			mgr.close();
		}
		return cat_personal;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param cat_personal the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateCat_Personal")
	public Cat_Personal updateCat_Personal(Cat_Personal cat_personal) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsCat_Personal(cat_personal)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(cat_personal);
		} finally {
			mgr.close();
		}
		return cat_personal;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeCat_Personal")
	public void removeCat_Personal(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Cat_Personal cat_personal = mgr.find(Cat_Personal.class, id);
			mgr.remove(cat_personal);
		} finally {
			mgr.close();
		}
	}

	private boolean containsCat_Personal(Cat_Personal cat_personal) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Cat_Personal item = mgr.find(Cat_Personal.class,
					cat_personal.getId_Tipo_Personal());
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
