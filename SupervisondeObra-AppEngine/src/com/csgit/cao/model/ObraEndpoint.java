package com.csgit.cao.model;

import com.csgit.cao.Constants;
import com.csgit.cao.EMF;
import com.csgit.cao.XMPPAgentServlet;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.log.LogQuery;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "communicationchannel", namespace = @ApiNamespace(ownerDomain = "csgit.com", ownerName = "csgit.com", packagePath = "cao.model"), clientIds = {
	Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID,
	Constants.IOS_CLIENT_ID }, audiences = { Constants.ANDROID_AUDIENCE }, version = "v1")
public class ObraEndpoint {

	
	public static final Logger _log = Logger.getLogger(ObraEndpoint.class.getName());
	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listObra")
	public CollectionResponse<Obra> listObra(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit
			, @Named("idProyecto")Long idProyecto
			, @Named("idObra")Long idObra
			, @Named("email")String email
		    ) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Obra> execute = null;
		LogQuery query2 = LogQuery.Builder.withDefaults();
		query2.includeAppLogs(true);
		
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
			_log.info("el e mail es"+email);
			query2.offset("el e mail es"+email);
				Long idpersona = null;
				Long idUsuario = null;
				Collection<Persona>lista1;
				Collection<Usuario>lista2;
				lista =new PersonaEndpoint().listPersona(null,null);//traemos la lista de personas insertadas
				lista1=lista.getItems();
			
			    for (Persona elem : lista1) {
			        if(elem.getEmails().equals(email))     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
			        	idpersona = elem.getId_Persona();
			        _log.info("el idpersona= "+idpersona );
			    	query2.offset("el idpersona= "+idpersona);
			    }
			    lista= new UsuarioEndpoint().listUsuario(null, null);
			    lista2=lista.getItems();
			    for (Usuario elem : lista2) {
			        if(elem.getId_Persona()==idpersona)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
			        	idUsuario = elem.getId_Propietario();
			        _log.info("el idUsuario= "+idUsuario);
query2.offset("el idUsuario= "+idUsuario);
			    }
			    
			    
			    query = mgr.createQuery("select from Obra as Obra where Obra.idUsuario="+idUsuario);
				
			    
			    
			}
			else if(idProyecto!=0||idObra!=0){
				
				if(idProyecto!=0)
				query = mgr.createQuery("select from Obra as Obra where Obra.id_Poyecto="+idProyecto);
				else
					query = mgr.createQuery("select from Obra as Obra where Obra.id_Obra="+idObra);
			}
		else{
			
			
			 query = mgr.createQuery("select from Obra as Obra where Obra.id_Obra");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}
			}

			execute = (List<Obra>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Obra obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Obra> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getObra")
	public Obra getObra(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Obra obra = null;
		try {
			obra = mgr.find(Obra.class, id);
		} finally {
			mgr.close();
		}
		return obra;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param obra the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertObra")
	public Obra insertObra(Obra obra) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsObra(obra)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(obra);
		} finally {
			mgr.close();
		}
		return obra;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param obra the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateObra")
	public Obra updateObra(Obra obra) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsObra(obra)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(obra);
		} finally {
			mgr.close();
		}
		return obra;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeObra")
	public void removeObra(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Obra obra = mgr.find(Obra.class, id);
			mgr.remove(obra);
		} finally {
			mgr.close();
		}
	}

	private boolean containsObra(Obra obra) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Obra item = mgr.find(Obra.class, obra.getId_Obra());
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
