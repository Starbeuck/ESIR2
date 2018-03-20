package net.mail.ws.service;

import java.lang.reflect.Array;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.FromStringTerm;
import javax.mail.search.SearchTerm;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface ="net.mail.ws.service.WSMail", serviceName="MyOwnMailAgent")

public class WSMailImpl {
	public void sendMail(String smtpHost, int smtpPort, String from, String to, String username, String password, boolean isSSL)
	{
		Properties props = new Properties();
		if(isSSL){
			props.put("mail.transport.protocol", "smtps");
			props.put("mail.smtps.auth", "true");
		} else {
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtps.auth", "false");
		}	
	
		props.put("mail.smtp.host",smtpHost);
		
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);
		
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Hello");
			message.setText("Hello World");
			
			Transport tr;
			tr = session.getTransport();
			tr.connect(smtpHost, smtpPort, username, password);
			message.saveChanges();
			
			tr.sendMessage(message, message.getAllRecipients());
			tr.close();
		}
		catch (Exception e) {
			System.out.println("MessagingException" + e);
		}
	}
	
}
