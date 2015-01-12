package com.csgit.cao.model;

import com.csgit.cao.Constants;
import com.csgit.cao.EMF;
import com.csgit.cao.XmppSincronizacion;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.log.LogQuery;
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
public class MultimediaEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listMultimedia")
	public CollectionResponse<Multimedia> listMultimedia(

			@Named("cursor") @Nullable  String cursorString,
			 @Named("limit")@Nullable Integer limit, @Named("tipoReferencia") Long tipoReferencia, @Named("idReferencia") Long idReferencia, @Named("tipoArchivo") Long tipoArchivo) {

		
		
		EntityManager mgr = null;
		Cursor cursor = null;
		List<Multimedia> execute = null;
		Query query = null;
		LogQuery log = LogQuery.Builder.withDefaults();
	    log.includeAppLogs(true);
		try {
			mgr = getEntityManager();
			if(tipoReferencia == 0 && idReferencia == 0 && tipoArchivo == 0)
				query = mgr.createQuery("select from Multimedia as Multimedia");
			else if(tipoReferencia.longValue() != 0 && idReferencia.longValue()!=0)
				query =  mgr.createQuery("select from Multimedia as Multimedia where Multimedia.tipoReferencia = "+tipoReferencia+"and Multimedia.idReferencia = "+idReferencia);
			else if(tipoReferencia.longValue() != 0 )
				query =  mgr.createQuery("select from Multimedia as Multimedia where Multimedia.tipoReferencia = "+tipoReferencia); 
			else if(tipoReferencia.longValue() == 0 && idReferencia.longValue() != 0 )
				query =  mgr.createQuery("select from Multimedia as Multimedia where Multimedia.idReferencia = "+idReferencia+"and Multimedia.tipoReferencia = "+tipoReferencia);
				
				else if(tipoReferencia.longValue() == 0 && idReferencia.longValue() != 0 )
				query =  mgr.createQuery("select from Multimedia as Multimedia where Multimedia.idReferencia = "+idReferencia);
				
			

	//		query = mgr.createQuery("select from Multimedia as Multimedia where Multimedia.tipoReferencia="+tipoReferencia+" and Multimedia.idReferencia="+idReferencia+" and Multimedia.tipoArchivo="+tipoArchivo);

//			if(tipoReferencia!=0 && idReferencia==0&tipoArchivo==0){
//				query = mgr.createQuery("select from Multimedia as Multimedia where Multimedia.tipoReferencia="+tipoReferencia);
//				  log.offset("se solicito la lista solo con el tipo referencia = "+tipoReferencia);
//				  System.out.println("se solicito la lista solo con el tipo referencia = "+tipoReferencia);
//			}
//			else if(tipoReferencia!=0 && idReferencia!=0&tipoArchivo==0){
//				query = mgr.createQuery("select from Multimedia as Multimedia where Multimedia.tipoReferencia="+tipoReferencia+"and Multimedia.idReferencia="+idReferencia);
//				  log.offset("se solicito la lista solo con el tipo referencia = "+tipoReferencia+" idReferencia="+idReferencia);
//				  System.out.println("se solicito la lista solo con el tipo referencia = "+tipoReferencia+" idReferencia="+idReferencia);
//			}
//			else if(tipoReferencia!=0 && idReferencia != 0 && tipoArchivo != 0){
//				query = mgr.createQuery("select from Multimedia as Multimedia where Multimedia.idReferencia="+idReferencia+"and Multimedia.tipoReferencia="+tipoReferencia+"and Multimedia.tipoArchivo="+tipoArchivo);
//				  log.offset("se solicito la lista solo con el tipo referencia = "+tipoReferencia+" idReferencia="+idReferencia+" tipoArchivo="+tipoArchivo);
//				  System.out.println("se solicito la lista solo con el tipo referencia = "+tipoReferencia+" idReferencia="+idReferencia+" tipoArchivo="+tipoArchivo);
//			}
//			else if(tipoReferencia==0 && idReferencia==0&tipoArchivo!=0)
//				{
//					query = mgr.createQuery("select from Multimedia as Multimedia where Multimedia.tipoArchivo="+tipoArchivo);
//					  log.offset("se solicito la lista solo con el tipo referencia = "+tipoReferencia+" idReferencia="+idReferencia+" tipoArchivo="+tipoArchivo);
//					  System.out.println("se solicito la lista solo con el tipo referencia = "+tipoReferencia+" idReferencia="+idReferencia+" tipoArchivo="+tipoArchivo);
//				}
//			else
//			{
//				query = mgr.createQuery("select from Multimedia as Multimedia");	
//				 log.offset("regresare toda la lista");
//				 System.out.println("regresare toda la lista");
//			}
			     
			
		
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Multimedia>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Multimedia obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Multimedia> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getMultimedia")
	public Multimedia getMultimedia(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Multimedia multimedia = null;
		try {
			multimedia = mgr.find(Multimedia.class, id);
		} finally {
			mgr.close();
		}
		return multimedia;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param multimedia the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertMultimedia")
	public Multimedia insertMultimedia(Multimedia multimedia) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsMultimedia(multimedia)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(multimedia);
		} finally {
			mgr.close();
		}
		return multimedia;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param multimedia the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateMultimedia")
	public Multimedia updateMultimedia(Multimedia multimedia) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsMultimedia(multimedia)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(multimedia);
		} finally {
			mgr.close();
		}
		return multimedia;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeMultimedia")
	public void removeMultimedia(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Multimedia multimedia = mgr.find(Multimedia.class, id);
			mgr.remove(multimedia);
		} finally {
			mgr.close();
		}
	}

	private boolean containsMultimedia(Multimedia multimedia) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Multimedia item = mgr.find(Multimedia.class,
					multimedia.getIdMultimedia());
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
