package com.csgit.cao;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.csgit.cao.business.Proyecto;
import com.csgit.cao.model.Sincronizacion;
import com.csgit.cao.model.SincronizacionEndpoint;
import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DeleteContext;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PrePut;
import com.google.appengine.api.datastore.PostPut;
import com.google.appengine.api.datastore.PostDelete;
import com.google.appengine.api.datastore.PutContext;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class ManyCallbacks {

//    @PrePut(kinds = {"kind1", "kind2"})
//    void foo(PutContext context) {
//      MemcacheService ms = MemcacheServiceFactory.getMemcacheService();
//      // ...
//    }
//
//    @PrePut
//    void bar(PutContext context) {
//      DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
//      // ...
//    }
//
//    @PostPut(kinds = {"kind1", "kind2"})
//    void baz(PutContext context) {
//        Queue q = QueueFactory.getDefaultQueue();
//        // ...
//    }
//
//    @PostDelete(kinds = {"kind2"})
//    void yam(DeleteContext context) {
//        URLFetchService us = URLFetchServiceFactory.getURLFetchService();
//        // ...
//    }
    
    @PostPut // Applies to all kinds
    void collectSample(PutContext context) {
        System.out.println();
        AsyncDatastoreService datastore = DatastoreServiceFactory.getAsyncDatastoreService();
        Future<Transaction> txn = datastore.beginTransaction();

        // Async call to lookup the Employee entity
        Key key = KeyFactory.createKey("Proyecto", "prueba datastore");
        Future<Entity> entityFuture = datastore.get(key);
       
      //  Future<Entity> employeeEntityFuture = datastore.get(employeeKey);
        try {
			Entity entity = entityFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Entity adjustmentEntity = new Entity("Proyecto", key);
       adjustmentEntity.setProperty("Nombre_largo","23432424334");
       datastore.put(adjustmentEntity);
       try {
		txn.get().commit();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} // could also call txn.get().commitAsync() here
        System.out.println("inserte correctamente");
        
        
    }

}