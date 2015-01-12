package com.csgit.cao;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.business.MultimediaBusiness;
import com.csgit.cao.business.UploadBusiness;
import com.csgit.cao.model.Multimedia;
import com.csgit.cao.model.MultimediaEndpoint;
import com.csgit.cao.model.Xmpp;
import com.csgit.cao.model.XmppEndpoint;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;

public class multimediaUpload extends HttpServlet {
	public static final Logger _log = Logger.getLogger(XMPPAgentServlet.class.getName());

    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    private Multimedia multimedia;
    private MultimediaEndpoint multimediaEndpoint;
    
    private String path;
    private String extension;
    private int referencia;
    private int tipo;
    XmppSincronizacion xmpp = new XmppSincronizacion();
    

    

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
    	List<BlobKey> blobs = blobstoreService.getUploads(req).get("archivo");
    	String tiporefval  = req.getParameter("tiporeferencia");
    	String idrefval  = req.getParameter("idreferencia");
    	
    	BlobKey blobKey = blobs.get(0);
    	MultimediaBusiness mb = new MultimediaBusiness();
    	PrintWriter out = res.getWriter();
		String error = "";
		multimedia = new Multimedia();
		multimediaEndpoint = new MultimediaEndpoint();
    	
    	
    	///
		
	
		/*
		 * excel:      application/vnd.ms-excel 
		 * 			   application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
		 * 
		 * pdf:        application/pdf
		 * 
		 * 
		 * 
		 * word:       application/vnd.openxmlformats-officedocument.wordprocessingml.document
		 * 			   application/msword
		 * 
		 * powerpoint: application/vnd.ms-powerpoint
		 * 			   application/vnd.openxmlformats-officedocument.presentationml.presentation
		 * 
		 *  3gp		   video/3gpp
		 *  
		 *  jpg/jpeg   image/jpeg
		 *  
		 *  png        image/png
		 *  
		 *  avi        video/x-msvideo
		 *  
		 *  mp4        video/mp4
		 *  		   application/mp4
		 *
		 */

		
		try {
			//  Map<String, List<BlobKey>> files_sent = BlobstoreServiceFactory.getBlobstoreService().getUploads(req);
			BlobKey file_uploaded_key = blobKey;
			//System.out.println("Document successfully POSTED, redirect to doGET");
			//JSONObject json = new JSONObject();
			//		       

			// TODO: handle exception

			BlobKey bk = new BlobKey(blobKey.getKeyString());
			BlobInfoFactory bif = new BlobInfoFactory();
			BlobInfo blobInfo = bif.loadBlobInfo(bk);
			  String contentType = blobInfo.getContentType();
			  multimedia.setTipoArchivo(Constants.file);
			  multimedia.setDescripcion(blobInfo.getFilename());
			  
			
			  
			  if(contentType.equalsIgnoreCase("application/vnd.ms-excel")||contentType.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){multimedia.setFormato(new Long(Constants.idExcel));}
			  else if(contentType.equalsIgnoreCase("application/pdf")){multimedia.setFormato(new Long(Constants.idPdf));}
			  else if(contentType.equalsIgnoreCase(" application/msword")||contentType.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.wordprocessingml.document")){multimedia.setFormato(new Long(Constants.idWord));}
			  else if(contentType.equalsIgnoreCase("capplication/vnd.ms-powerpoint")||contentType.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.presentationml.presentation")){multimedia.setFormato(new Long(Constants.idPowerPoint));}
			  else if(contentType.equalsIgnoreCase("video/3gpp")){multimedia.setFormato(new Long(Constants.id3Gp));}
			  else if(contentType.equalsIgnoreCase("image/jpeg")){multimedia.setFormato(new Long(Constants.idJpeg));multimedia.setTipoArchivo(Constants.imagen);}
			  else if(contentType.equalsIgnoreCase("image/png")){multimedia.setFormato(new Long(Constants.idPng));multimedia.setTipoArchivo(Constants.imagen);}
			  else if(contentType.equalsIgnoreCase("video/x-msvideo")){multimedia.setFormato(new Long(Constants.idAvi));}
			  else if(contentType.equalsIgnoreCase("video/mp4")||contentType.equalsIgnoreCase("application/mp4")){multimedia.setFormato(new Long(Constants.idMp4));}

			
			  xmpp.enviaMensaje("osalazar@adquem.com", "el formato es "+ multimedia.getFormato());
			//json.put("servingUrl", file_uploaded_key);
			//json.put("blobKey", blobKey.getKeyString());
			System.out.println(blobKey.getKeyString());

//			out.print(json.toString());
//			out.flush();
//			out.close();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    	Date date = new Date();
	    	String time = sdf.format(date);

			try {
				multimedia.setTipoReferencia(new Integer(req.getParameter("tiporeferencia")));
				multimedia.setIdMultimedia(GetIds.getIdMultimedia()+1);
				if(idrefval!=null) multimedia.setIdReferencia(new Long(idrefval));
				else multimedia.setIdReferencia(0L);
				multimedia.setPath(blobKey.getKeyString());
				multimedia.setFecha(time);
			
				
				
				multimediaEndpoint.insertMultimedia(multimedia);
				XmppSincronizacion xppp = new XmppSincronizacion();
				xppp.enviaMensaje("osalazar@adquem.com", "insercion realizada con exito blobstore es: "+multimedia.getPath());
				
				_log.info("insertado corectamente");
				if(new Integer(req.getParameter("tiporeferencia")) == 0)
					res.sendRedirect("/bibliotecaProyectos.jsp");
				else if(new Integer(req.getParameter("tiporeferencia")) == 1)
					res.sendRedirect("/bibliotecaObras.jsp");
				else 
					res.sendRedirect("/biblioteca.jsp");
			} catch (Exception e1) {
				// TODO: handle exception
				error += e1.getMessage();
				out.write(new Gson().toJson(error)); 
				_log.warning("error al insertar"+e1.getMessage());

			}


		}
		catch (Exception e) {
			System.out.println("Document failed to POST, redirecting back to upload.");
		}
			//
//		CollectionResponse<Multimedia> elementos  = multimediaEndpoint.listMultimedia(null, null, 0L, 0L, 0L);
//		for (Multimedia elem : elementos.getItems()) {
//			BlobInfoFactory bif = new BlobInfoFactory();
//			BlobKey bk = new BlobKey(elem.getPath());
//			BlobInfo blobInfo = bif.loadBlobInfo(bk);
//
//			if(blobInfo == null)
//			   _log.warning("error en el blob");
//			else{
//			    String contentType = blobInfo.getContentType();
//			    _log.info(contentType);
//			    XMPPService xmpp = XMPPServiceFactory.getXMPPService();
//			    JID fromJid = new JID("osalazar@adquem.com");
//			  
//			    //STEP 3
//			    String msgBody = "You sent me : " + contentType;
//			    Message replyMessage = new MessageBuilder()
//			    .withRecipientJids(fromJid)
//			    .withBody(msgBody)
//			    .build();
//			    //STEP 4
//			    boolean messageSent = false;
//			    if (xmpp.getPresence(fromJid).isAvailable()) {
//			    SendResponse status = xmpp.sendMessage(replyMessage);
//			    messageSent = (status.getStatusMap().get(fromJid) == SendResponse.Status.SUCCESS);
//			    }
//			}
//		}
		
		
		
		
    }
    
    
    public Long  returnId(){
		long id=0;
		CollectionResponse lista;
		Collection<Multimedia>lista1;
				
		lista =multimediaEndpoint.listMultimedia(null, null, new Long(0), new Long(0), new Long(0));
		lista1=lista.getItems();
	
	    for (Multimedia elem : lista1) {
	        if(elem.getIdMultimedia() > id)     		//verificamos si el id es mayor que el anterior para encontrar el id mas grande
	        	id = elem.getIdMultimedia();
	    }

		return id;	
	}
    
}
