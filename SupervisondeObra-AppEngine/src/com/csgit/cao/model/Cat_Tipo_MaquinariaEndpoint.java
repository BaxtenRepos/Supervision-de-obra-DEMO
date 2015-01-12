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
public class Cat_Tipo_MaquinariaEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listCat_Tipo_Maquinaria")
	public CollectionResponse<Cat_Tipo_Maquinaria> listCat_Tipo_Maquinaria(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Cat_Tipo_Maquinaria> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from Cat_Tipo_Maquinaria as Cat_Tipo_Maquinaria");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Cat_Tipo_Maquinaria>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Cat_Tipo_Maquinaria obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Cat_Tipo_Maquinaria> builder()
				.setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getCat_Tipo_Maquinaria")
	public Cat_Tipo_Maquinaria getCat_Tipo_Maquinaria(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Cat_Tipo_Maquinaria cat_tipo_maquinaria = null;
		try {
			cat_tipo_maquinaria = mgr.find(Cat_Tipo_Maquinaria.class, id);
		} finally {
			mgr.close();
		}
		return cat_tipo_maquinaria;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param cat_tipo_maquinaria the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertCat_Tipo_Maquinaria")
	public Cat_Tipo_Maquinaria insertCat_Tipo_Maquinaria(
			Cat_Tipo_Maquinaria cat_tipo_maquinaria) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsCat_Tipo_Maquinaria(cat_tipo_maquinaria)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(cat_tipo_maquinaria);
		} finally {
			mgr.close();
		}
		return cat_tipo_maquinaria;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param cat_tipo_maquinaria the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateCat_Tipo_Maquinaria")
	public Cat_Tipo_Maquinaria updateCat_Tipo_Maquinaria(
			Cat_Tipo_Maquinaria cat_tipo_maquinaria) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsCat_Tipo_Maquinaria(cat_tipo_maquinaria)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(cat_tipo_maquinaria);
		} finally {
			mgr.close();
		}
		return cat_tipo_maquinaria;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeCat_Tipo_Maquinaria")
	public void removeCat_Tipo_Maquinaria(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Cat_Tipo_Maquinaria cat_tipo_maquinaria = mgr.find(
					Cat_Tipo_Maquinaria.class, id);
			mgr.remove(cat_tipo_maquinaria);
		} finally {
			mgr.close();
		}
	}

	private boolean containsCat_Tipo_Maquinaria(
			Cat_Tipo_Maquinaria cat_tipo_maquinaria) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Cat_Tipo_Maquinaria item = mgr.find(Cat_Tipo_Maquinaria.class,
					cat_tipo_maquinaria.getId_tipo_Maquinaria());
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
