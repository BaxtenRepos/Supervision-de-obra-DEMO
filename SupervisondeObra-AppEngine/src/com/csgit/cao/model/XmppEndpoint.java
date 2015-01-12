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
public class XmppEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listXmpp")
	public CollectionResponse<Xmpp> listXmpp(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Xmpp> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Xmpp as Xmpp");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Xmpp>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Xmpp obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Xmpp> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getXmpp")
	public Xmpp getXmpp(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Xmpp xmpp = null;
		try {
			xmpp = mgr.find(Xmpp.class, id);
		} finally {
			mgr.close();
		}
		return xmpp;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param xmpp the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertXmpp")
	public Xmpp insertXmpp(Xmpp xmpp) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsXmpp(xmpp)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(xmpp);
		} finally {
			mgr.close();
		}
		return xmpp;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param xmpp the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateXmpp")
	public Xmpp updateXmpp(Xmpp xmpp) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsXmpp(xmpp)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(xmpp);
		} finally {
			mgr.close();
		}
		return xmpp;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeXmpp")
	public void removeXmpp(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Xmpp xmpp = mgr.find(Xmpp.class, id);
			mgr.remove(xmpp);
		} finally {
			mgr.close();
		}
	}

	private boolean containsXmpp(Xmpp xmpp) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Xmpp item = mgr.find(Xmpp.class, xmpp.getIdXmpp());
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
