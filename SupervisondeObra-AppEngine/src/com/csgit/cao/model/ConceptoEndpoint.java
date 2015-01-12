package com.csgit.cao.model;

import com.csgit.cao.Constants;
import com.csgit.cao.EMF;
import com.csgit.cao.GetIds;
import com.csgit.util.MathUtil;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;
import com.google.appengine.api.log.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
public class ConceptoEndpoint {

	/**
	 * This method lists all the entities inserted in datastore. It uses HTTP
	 * GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 *         persisted and a cursor to the next page.
	 */

	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listConcepto")
	public CollectionResponse<Concepto> listConcepto(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit, @Named("id_obra") Long id
			, @Named("def")Long def) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Concepto> execute = null;

		try {
			mgr = getEntityManager();
			Query query;
			if (id != 0) {
				query = mgr
						.createQuery("select from Concepto as Concepto where Concepto.id_Obra="
								+ id);

			} else {
				query = mgr.createQuery("select from Concepto as Concepto");

			}
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Concepto>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and
			// accomodate
			// for lazy fetch.
			for (Concepto obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Concepto> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET
	 * method.
	 *
	 * @param id
	 *            the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	 @ApiMethod(name = "getConcepto")
	 public Concepto getConcepto(@Named("id") Long id) {
	 EntityManager mgr = getEntityManager();
	 Concepto concepto = null;
	 try {
	 concepto = mgr.find(Concepto.class, id);
	 } finally {
	 mgr.close();
	 }
	 return concepto;
	 }

	/**
	 * This inserts a new entity into App Engine datastore. If the entity
	 * already exists in the datastore, an exception is thrown. It uses HTTP
	 * POST method.
	 *
	 * @param concepto
	 *            the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertConcepto")
	public Concepto insertConcepto(Concepto concepto) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsConcepto(concepto)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(concepto);
		} finally {
			mgr.close();
		}
		return concepto;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does
	 * not exist in the datastore, an exception is thrown. It uses HTTP PUT
	 * method.
	 *
	 * @param concepto
	 *            the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateConcepto")
	public Concepto updateConcepto(Concepto concepto) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsConcepto(concepto)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(concepto);
		} finally {

			mgr.close();
		}
		return concepto;
	}

	/**
	 * This method removes the entity with primary key id. It uses HTTP DELETE
	 * method.
	 *
	 * @param id
	 *            the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeConcepto")
	public void removeConcepto(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Concepto concepto = mgr.find(Concepto.class, id);
			mgr.remove(concepto);
		} finally {
			mgr.close();
		}
	}

	private boolean containsConcepto(Concepto concepto) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;

		try {
			Concepto item = mgr.find(Concepto.class, concepto.getId_Concepto());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private boolean containsAvance_Fisico(Avance_Fisico avence) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;

		try {
			Avance_Fisico item = mgr.find(Avance_Fisico.class,
					avence.getId_AvanceFisico());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private boolean containsAvance_Financiero(Avance_Financiero avence) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;

		try {
			Avance_Financiero item = mgr.find(Avance_Financiero.class,
					avence.getId_AvanceFinaciero());
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

	/**
	 * This inserts a new entity into App Engine datastore. If the entity
	 * already exists in the datastore, an exception is thrown. It uses HTTP
	 * POST method.
	 *
	 * @param boolean .
	 * @return Avancefisiso Entity .
	 */
	@ApiMethod(name = "calculaAvance")
	public Object calculaAvance(@Named("id_referencia") Long id,
			@Named("tipo_referencia") Integer tipo,
			@Named("tipo_calculo") Integer calculo) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		GetIds getid = new GetIds();
		LogQuery query = LogQuery.Builder.withDefaults();
		query.includeAppLogs(true);
		Object respuesta = new Object();
		query.offset("Se llamo correctamente el metodo calculaAvance +++++++++++++++++++++++++++++++++++++++++++++++++++");
		MathUtil math = new MathUtil();
		Avance_Fisico AvRow = new Avance_Fisico();
		Avance_Financiero AvfRow = new Avance_Financiero();
		ArrayList<Long> arrayList = new ArrayList<Long>();
		ArrayList<Long> aList = new ArrayList<Long>();
		List<Double> Tendencia = null;
		EntityManager mgr = getEntityManager();
		Avance_FinancieroEndpoint AFE = new Avance_FinancieroEndpoint();
		Avance_FisicoEndpoint AfE = new Avance_FisicoEndpoint();
		switch (calculo) {
		case Constants.Avance_Fisico:
			switch (tipo) {
			case Constants.Proyecto:
				query.offset("Se solicita calcular el avance del Proyecto con id="
						+ id);
				Collection<Avance_Fisico> avnces = AfE.listAvance_Fisico(null,
						null, id, tipo).getItems();

				List<Avance_Fisico> lstavFisico = new ArrayList<>(avnces);
				for (int i = 0; i > avnces.size(); i++) {
					if (lstavFisico.get(i).getId_referencia() == id
							&& lstavFisico.get(i).getTipo_Entidad() == Constants.Proyecto) {
						arrayList.add(lstavFisico.get(i).getId_AvanceFisico());
					}
				}
				try {
					Tendencia = math.calculaTendencia(id, tipo);
				} catch (Exception e) {
					// TODO: handle exception

				}
				// if(aList!=null){
				AvRow.setId_AvanceFisico(getid.getIdAvanceFisico() + 1);
				//
				// }else{
				// AvRow.setId_AvanceFisico((long) 1);
				// }

				AvRow.setId_referencia(id);
				AvRow.setTipo_Entidad(tipo);
				AvRow.setFechaReporte(String.valueOf(sdf.format(new Date(System.currentTimeMillis()))));
				AvRow.setEstado(math.ObtenerEstado());
				AvRow.setPAvanceFisico(math.calculaAvanceFisico(id, tipo));
				AvRow.setPorcentaje_tendencia(Tendencia.get(Tendencia.size() - 1));
				try {
					if (containsAvance_Fisico(AvRow)) {
						throw new EntityExistsException("Object already exists");
					}
					mgr.persist(AvRow);
					respuesta = (Object)AvRow;
				} finally {
					mgr.close();
				}
				break;

			case Constants.Obra:
				query.offset("Se solicita calcular el avance de la Obra con id="
						+ id);
				Collection<Concepto> conceptos = listConcepto(null, null, id,0L)
						.getItems();
				ArrayList<Concepto> lstconceptos = new ArrayList<>(conceptos);
				Collection<Avance_Fisico> avnces1 = AfE.listAvance_Fisico(null,
						null, id, tipo).getItems();
				List<Avance_Fisico> lstavFisico1 = new ArrayList<>(avnces1);
				try {
					Tendencia = math.calculaTendencia(id, tipo);
				} catch (Exception e) {
					// TODO: handle exception

				}
				AvRow.setId_referencia(id);
				AvRow.setTipo_Entidad(tipo);
				AvRow.setFechaReporte(String.valueOf(sdf.format(new Date(System.currentTimeMillis()))));
				AvRow.setEstado(math.ObtenerEstado());
				AvRow.setPAvanceFisico(math.calculaAvanceFisico(id, tipo));
				AvRow.setPorcentaje_tendencia(Tendencia.get(Tendencia.size() - 1));
				for (int i = 0; i > lstavFisico1.size(); i++) {
					// if(((List<Concepto>) conceptos).get(i).getId_Obra()==id){
					aList.add(lstavFisico1.get(i).getId_AvanceFisico());
					// }
				}
				// if(aList.size()>0){
				AvRow.setId_AvanceFisico(getid.getIdAvanceFisico() + 1);
				//
				// }else{
				// AvRow.setId_AvanceFisico((long) 1);
				// }

				try {
					if (containsAvance_Fisico(AvRow)) {
						throw new EntityExistsException("Object already exists");
					}
					mgr.persist(AvRow);
					respuesta = (Object)AvRow;
				} finally {
					mgr.close();
				}
				break;

			}

			break;

		case Constants.Avance_Financiaro:

			switch (tipo) {
			case Constants.Proyecto:
				query.offset("Se solicita calcular el avance del Proyecto con id="
						+ id);
				Collection<Avance_Financiero> avnces = AFE
						.listAvance_Financiero(null, null, id, tipo).getItems();

				List<Avance_Financiero> lstavFisico = new ArrayList<>(avnces);
				for (int i = 0; i > avnces.size(); i++) {
					if (lstavFisico.get(i).getId_referencia() == id
							&& lstavFisico.get(i).getTipo_Entidad() == Constants.Proyecto) {
						arrayList.add(lstavFisico.get(i)
								.getId_AvanceFinaciero());
					}
				}
				try {
					Tendencia = math.calculaTendencia(id, tipo);
				} catch (Exception e) {
					// TODO: handle exception

				}
				// if(aList!=null){
				AvRow.setId_AvanceFisico(getid.getIdAvanceFinanciero() + 1);
				//
				// }else{
				// AvRow.setId_AvanceFisico((long) 1);
				// }

				AvfRow.setId_referencia(id);
				AvfRow.setTipo_Entidad(tipo);
				AvfRow.setFechaReporte(String.valueOf(sdf.format(new Date(System.currentTimeMillis()))));
				AvfRow.setEstado(math.ObtenerEstado());
				AvfRow.setPAvanceFinanciero(math.calculaAvanceFinanciero(id, tipo));
				AvfRow.setPorcentaje_tendencia(Tendencia.get(Tendencia.size() - 1));
				try {
					if (containsAvance_Financiero(AvfRow)) {
						throw new EntityExistsException("Object already exists");
					}
					mgr.persist(AvfRow);
					respuesta = (Object)AvfRow;
				} finally {
					mgr.close();
				}
				break;

			case Constants.Obra:
				query.offset("Se solicita calcular el avance de la Obra con id="
						+ id);
				Collection<Concepto> conceptos = listConcepto(null, null, id,0L)
						.getItems();
				ArrayList<Concepto> lstconceptos = new ArrayList<>(conceptos);
				Collection<Avance_Financiero> avnces1 = AFE
						.listAvance_Financiero(null, null, id, tipo).getItems();
				List<Avance_Financiero> lstavFisico1 = new ArrayList<>(avnces1);
				try {
					Tendencia = math.calculaTendencia(id, tipo);
				} catch (Exception e) {
					// TODO: handle exception

				}
				AvfRow.setId_referencia(id);
				AvfRow.setTipo_Entidad(tipo);
				AvfRow.setFechaReporte(String.valueOf(sdf.format(new Date(System.currentTimeMillis()))));
				AvfRow.setEstado(math.ObtenerEstado());
				AvfRow.setPAvanceFinanciero(math.calculaAvanceFinanciero(id, tipo));
				AvfRow.setPorcentaje_tendencia(Tendencia.get(Tendencia.size() - 1));
				for (int i = 0; i > lstavFisico1.size(); i++) {
					// if(((List<Concepto>) conceptos).get(i).getId_Obra()==id){
					aList.add(lstavFisico1.get(i).getId_AvanceFinaciero());
					// }
				}
				// if(aList.size()>0){
				AvfRow.setId_AvanceFinaciero(getid.getIdAvanceFinanciero() + 1);
				//
				// }else{
				// AvRow.setId_AvanceFisico((long) 1);
				// }

				try {
					if (containsAvance_Financiero(AvfRow)) {
						throw new EntityExistsException("Object already exists");
					}
					mgr.persist(AvfRow);
					respuesta = (Object)AvfRow;
				} finally {
					mgr.close();

				}
				break;

			}

			break;
		}
		return respuesta;
	}
}
