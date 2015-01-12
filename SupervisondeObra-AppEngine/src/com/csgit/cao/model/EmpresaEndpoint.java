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
public class EmpresaEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listEmpresa")
	public CollectionResponse<Empresa> listEmpresa(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Empresa> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Empresa as Empresa");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Empresa>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Empresa obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Empresa> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getEmpresa")
	public Empresa getEmpresa(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Empresa empresa = null;
		try {
			empresa = mgr.find(Empresa.class, id);
		} finally {
			mgr.close();
		}
		return empresa;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param empresa the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertEmpresa")
	public Empresa insertEmpresa(Empresa empresa) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsEmpresa(empresa)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(empresa);
		} finally {
			mgr.close();
		}
		return empresa;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param empresa the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateEmpresa")
	public Empresa updateEmpresa(Empresa empresa) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsEmpresa(empresa)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(empresa);
		} finally {
			mgr.close();
		}
		return empresa;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeEmpresa")
	public void removeEmpresa(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Empresa empresa = mgr.find(Empresa.class, id);
			mgr.remove(empresa);
		} finally {
			mgr.close();
		}
	}

	private boolean containsEmpresa(Empresa empresa) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Empresa item = mgr.find(Empresa.class, empresa.getId_Empresa());
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
