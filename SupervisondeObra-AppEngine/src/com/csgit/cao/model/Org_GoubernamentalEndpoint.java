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
public class Org_GoubernamentalEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listOrg_Goubernamental")
	public CollectionResponse<Org_Goubernamental> listOrg_Goubernamental(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Org_Goubernamental> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from Org_Goubernamental as Org_Goubernamental");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Org_Goubernamental>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Org_Goubernamental obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Org_Goubernamental> builder()
				.setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getOrg_Goubernamental")
	public Org_Goubernamental getOrg_Goubernamental(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Org_Goubernamental org_goubernamental = null;
		try {
			org_goubernamental = mgr.find(Org_Goubernamental.class, id);
		} finally {
			mgr.close();
		}
		return org_goubernamental;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param org_goubernamental the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertOrg_Goubernamental")
	public Org_Goubernamental insertOrg_Goubernamental(
			Org_Goubernamental org_goubernamental) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsOrg_Goubernamental(org_goubernamental)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(org_goubernamental);
		} finally {
			mgr.close();
		}
		return org_goubernamental;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param org_goubernamental the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateOrg_Goubernamental")
	public Org_Goubernamental updateOrg_Goubernamental(
			Org_Goubernamental org_goubernamental) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsOrg_Goubernamental(org_goubernamental)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(org_goubernamental);
		} finally {
			mgr.close();
		}
		return org_goubernamental;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeOrg_Goubernamental")
	public void removeOrg_Goubernamental(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Org_Goubernamental org_goubernamental = mgr.find(
					Org_Goubernamental.class, id);
			mgr.remove(org_goubernamental);
		} finally {
			mgr.close();
		}
	}

	private boolean containsOrg_Goubernamental(
			Org_Goubernamental org_goubernamental) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Org_Goubernamental item = mgr.find(Org_Goubernamental.class,
					org_goubernamental.getId_Organizacion());
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
