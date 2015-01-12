package com.csgit.cao;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.model.Xmpp;
import com.csgit.cao.model.XmppEndpoint;
import com.google.appengine.api.xmpp.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")

//STEP 1
public class XMPPAgentServlet extends HttpServlet {
public static final Logger _log = Logger.getLogger(XMPPAgentServlet.class.getName());
public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
{
	_log.warning("llegue al chat");
try {
String strStatus = "";
XMPPService xmpp = XMPPServiceFactory.getXMPPService();
//STEP 2
Message msg = xmpp.parseMessage(req);
JID fromJid = msg.getFromJid();
String body = msg.getBody();
_log.info("Receivedssage from " + fromJid + " and body = " + body);
Xmpp mensaje = new Xmpp();
mensaje.setIdXmpp(GetIds.getIdXmpp()+1);
mensaje.setMensaje("Mensaje recibido de " + fromJid + " Mensaje = " + body);
try {
	new XmppEndpoint().insertXmpp(mensaje);
	_log.info("xmpp insertado");
} catch (Exception e) {
	// TODO: handle exception
	_log.warning("error al insertar el mensaje");
}


//STEP 3
String msgBody = "You sent me : " + body;
Message replyMessage = new MessageBuilder()
.withRecipientJids(fromJid)
.withBody(msgBody)
.build();
//STEP 4
boolean messageSent = false;
if (xmpp.getPresence(fromJid).isAvailable()) {
SendResponse status = xmpp.sendMessage(replyMessage);
messageSent = (status.getStatusMap().get(fromJid) == SendResponse.Status.SUCCESS);
}
//STEP 5
if (messageSent) {
strStatus = "Message has been sent successfully";
}
else {
strStatus = "Message could not be sent";
}
_log.info(strStatus);
} catch (Exception e) {
_log.log(Level.SEVERE,e.getMessage());
}
}
}
