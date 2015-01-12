package com.csgit.cao.model;

import com.csgit.cao.Constants;
import com.csgit.cao.EMF;
import com.csgit.cao.business.UsuarioBusiness;
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
public class UsuarioEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listUsuario")
	public CollectionResponse<Usuario> listUsuario(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {
		

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Usuario> execute = null;
		Query query;

		try {
			mgr = getEntityManager();
			
			 query = mgr.createQuery("select from Usuario as Usuario");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Usuario>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Usuario obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Usuario> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getUsuario")
	public Usuario getUsuario(@Named("id") Long id,@Named("user") String user, @Named ("contrasena")String contrasena) {
		EntityManager mgr = getEntityManager();
		Usuario usuario = null;
		if(id==0){
			Long id2=UsuarioBusiness.validaUsuario(user, contrasena);
			try {
				usuario = mgr.find(Usuario.class,id2);
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				mgr.close();
				
			}
		}else{
		try {
			usuario = mgr.find(Usuario.class, id);
		} finally {
			mgr.close();
		}
		
		}
		return usuario;
	}
	
	

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param usuario the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertUsuario")
	public Usuario insertUsuario(Usuario usuario) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsUsuario(usuario)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(usuario);
		} finally {
			mgr.close();
		}
		return usuario;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param usuario the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateUsuario")
	public Usuario updateUsuario(Usuario usuario) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsUsuario(usuario)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(usuario);
		} finally {
			mgr.close();
		}
		return usuario;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeUsuario")
	public void removeUsuario(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Usuario usuario = mgr.find(Usuario.class, id);
			mgr.remove(usuario);
		} finally {
			mgr.close();
		}
	}

	private boolean containsUsuario(Usuario usuario) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Usuario item = mgr.find(Usuario.class, usuario.getId_Propietario());
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
