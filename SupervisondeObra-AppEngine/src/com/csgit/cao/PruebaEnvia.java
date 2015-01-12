package com.csgit.cao;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

public class PruebaEnvia extends HttpServlet {
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		  JID jid = new JID("roberto.salazar.manilla@gmail.com");
	        String msgBody = "Someone has sent you a gift on Example.com. To view: http://example.com/gifts/";
	        Message msg = new MessageBuilder()
	            .withRecipientJids(jid)
	            .withBody(msgBody)
	            .build();

	        boolean messageSent = false;
	        XMPPService xmpp = XMPPServiceFactory.getXMPPService();
	        SendResponse status = xmpp.sendMessage(msg);
	        messageSent = (status.getStatusMap().get(jid) == SendResponse.Status.SUCCESS);

	        if (!messageSent) {
	            // Send an email message instead...
	        	System.out.println("error al enviar el mensaje");
	        }		
	}
	

	      

}

