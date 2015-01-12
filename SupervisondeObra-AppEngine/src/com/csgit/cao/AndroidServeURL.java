package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class AndroidServeURL extends HttpServlet{
	  private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws IOException {

	//"uploaded" is another servlet which will send UploadUrl and blobkey to android client
	String blobUploadUrl = blobstoreService.createUploadUrl("/blob/upload"); 

	        resp.setStatus(HttpServletResponse.SC_OK);
	        resp.setContentType("text/plain");

	        PrintWriter out = resp.getWriter();
	        out.print(blobUploadUrl);
	    }
}
