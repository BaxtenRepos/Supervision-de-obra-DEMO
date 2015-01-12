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
public class EstimacionEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listEstimacion")
	public CollectionResponse<Estimacion> listEstimacion(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit, @Named("idObra")  Long id_obra,@Named("email")String email) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Estimacion> execute = null;

		try {
			mgr = getEntityManager();
			Query query = null;
			if(id_obra!=null){
				query= mgr.createQuery("select from Estimacion as Estimacion where Estimacion.idObra ="+id_obra);
					
			}
			else if(email.contains("%20"))
			{
				email.replaceAll("%20","");
				String prueba3 = email.replace("%20", "");
				email=prueba3;
				if(!email.trim().equalsIgnoreCase("")){
					if(email.contains("%40")){
						String []prueba = email.split("%40");
						email=prueba[0]+"@"+prueba[1];
					}

				    query = mgr.createQuery("select from Estimacion as Estimacion where Estimacion.Email="+email);
	  
				}
			}
			else{
				query = mgr
					.createQuery("select from Estimacion as Estimacion");
			}
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Estimacion>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Estimacion obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Estimacion> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getEstimacion")
	public Estimacion getEstimacion(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Estimacion estimacion = null;
		try {
			estimacion = mgr.find(Estimacion.class, id);
		} finally {
			mgr.close();
		}
		return estimacion;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param estimacion the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertEstimacion")
	public Estimacion insertEstimacion(Estimacion estimacion) {
		EntityManager mgr = getEntityManager();
		estimacion.setId_Estimacion(GetIds.getIdEstimacion()+1);
		try {
			if (containsEstimacion(estimacion)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(estimacion);
		} finally {
			mgr.close();
		}
		return estimacion;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param estimacion the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateEstimacion")
	public Estimacion updateEstimacion(Estimacion estimacion) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsEstimacion(estimacion)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(estimacion);
		} finally {
			mgr.close();
		}
		return estimacion;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeEstimacion")
	public void removeEstimacion(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Estimacion estimacion = mgr.find(Estimacion.class, id);
			mgr.remove(estimacion);
		} finally {
			mgr.close();
		}
	}

	private boolean containsEstimacion(Estimacion estimacion) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Estimacion item = mgr.find(Estimacion.class,
					estimacion.getId_Estimacion());
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
