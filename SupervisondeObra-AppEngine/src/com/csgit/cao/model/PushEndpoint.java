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
public class PushEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listPush")
	public CollectionResponse<Push> listPush(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Push> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Push as Push");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Push>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Push obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Push> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getPush")
	public Push getPush(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Push push = null;
		try {
			push = mgr.find(Push.class, id);
		} finally {
			mgr.close();
		}
		return push;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param push the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertPush")
	public Push insertPush(Push push) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsPush(push)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(push);
		} finally {
			mgr.close();
		}
		return push;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param push the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updatePush")
	public Push updatePush(Push push) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsPush(push)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(push);
		} finally {
			mgr.close();
		}
		return push;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removePush")
	public void removePush(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Push push = mgr.find(Push.class, id);
			mgr.remove(push);
		} finally {
			mgr.close();
		}
	}

	private boolean containsPush(Push push) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Push item = mgr.find(Push.class, push.getId_Propietario());
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
