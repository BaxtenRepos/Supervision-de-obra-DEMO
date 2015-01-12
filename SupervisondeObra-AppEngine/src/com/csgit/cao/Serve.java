package com.csgit.cao;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.csgit.cao.model.MultimediaEndpoint;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class Serve extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

    		String val = req.getParameter("eliminar");
    		if(val!=null && val.contains(":")){
    			String valores[] = val.split(":");
    			if(valores[1].contains("obra")){
    				new MultimediaEndpoint().removeMultimedia(Long.parseLong(valores[0].trim()));
    				res.sendRedirect("/bibliotecaObras.jsp");
    			}else if(valores[1].contains("proyecto")){
    				new MultimediaEndpoint().removeMultimedia(Long.parseLong(valores[0].trim()));
    				res.sendRedirect("/bibliotecaProyectos.jsp");
    			}
    		}
    		else if(val==null)
    			{
    			BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
    			blobstoreService.serve(blobKey, res);
    			}else
    			{
    				new MultimediaEndpoint().removeMultimedia(Long.parseLong(val.trim()));
    				res.sendRedirect("/biblioteca.jsp");
    				
    			}
    		
            
         
            
        }
}