package com.csgit.cao;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

public class XmppSincronizacion {

	XMPPService xmpp;
	public static final Logger log = Logger.getLogger(XmppSincronizacion.class.getName());
	boolean regresa = false;
	
	public XmppSincronizacion() {
		// TODO Auto-generated constructor stub
	
	 xmpp = XMPPServiceFactory.getXMPPService();
	}
	
	public boolean enviaMensaje(String to, String msg){


		try {
			log.info("Envio de mensajes xmpp");
			String strStatus = "";

			String msgBody = msg;
			JID fromJid=new JID(to);
			Message replyMessage = new MessageBuilder()
			.withRecipientJids(fromJid)
			.withBody(msgBody)
			.build();
			boolean messageSent = false;
			log.info("verificamos si esta disponible el usuario");
			if (xmpp.getPresence(fromJid).isAvailable()) {
				log.info("si esta disponible");
				SendResponse status = xmpp.sendMessage(replyMessage);
				messageSent = (status.getStatusMap().get(fromJid) == SendResponse.Status.SUCCESS);
			}
			if (messageSent) {
				strStatus = "Message has been sent successfully";
				regresa=true;
			}
			else {
				strStatus = "Message could not be sent";
				regresa=false;
			}
			log.info(strStatus);
		} catch (Exception e) {
			log.log(Level.SEVERE,e.getMessage());
		}


		
		return regresa;
		
	}
}
