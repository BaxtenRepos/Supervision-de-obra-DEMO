package com.csgit.cao.model;

import com.csgit.cao.Constants;
import com.csgit.cao.EMF;
import com.csgit.cao.GetIds;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.Collection;
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
public class NotasEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listNotas")
	public CollectionResponse<Notas> listNotas(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit
			,@Named("email")String email
			,@Named("def")Long def) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Notas> execute = null;
		Query query;

		try {
			mgr = getEntityManager();
			
			if(email.contains("%20"));
			{
				email.replaceAll("%20","");
				String prueba3 = email.replace("%20", "");
				email=prueba3;
			}
			if(!email.trim().equalsIgnoreCase("")){
				if(email.contains("%40")){
					String []prueba = email.split("%40");
					email=prueba[0]+"@"+prueba[1];
				}
				CollectionResponse lista;

				
			    
			    
			    query = mgr.createQuery("select from Notas as Notas where Notas.Email="+email);
				
			    
			    
			}
			
			
			
			else
			 query = mgr.createQuery("select from Notas as Notas");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Notas>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Notas obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Notas> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getNotas")
	public Notas getNotas(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Notas notas = null;
		try {
			notas = mgr.find(Notas.class, id);
		} finally {
			mgr.close();
		}
		return notas;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param notas the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertNotas")
	public Notas insertNotas(Notas notas) {
		EntityManager mgr = getEntityManager();
		notas.setIdRepoNotas(GetIds.getIdNotas()+1);
		try {
			if (containsNotas(notas)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(notas);
		} finally {
			mgr.close();
		}
		return notas;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param notas the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateNotas")
	public Notas updateNotas(Notas notas) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsNotas(notas)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(notas);
		} finally {
			mgr.close();
		}
		return notas;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeNotas")
	public void removeNotas(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Notas notas = mgr.find(Notas.class, id);
			mgr.remove(notas);
		} finally {
			mgr.close();
		}
	}

	private boolean containsNotas(Notas notas) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Notas item = mgr.find(Notas.class, notas.getIdRepoNotas());
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
