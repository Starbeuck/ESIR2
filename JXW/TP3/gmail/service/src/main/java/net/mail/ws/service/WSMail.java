package net.mail.ws.service;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://david.bromberg.fr/", name="WSMail")
@SOAPBinding(style = SOAPBinding.Style.RPC)

public interface WSMail {
	 @WebMethod
	 public void sendMail(
	 	@WebParam(name="smtpHost") 
	 	java.lang.String smtpHost,
		@WebParam(name="smtpPort") 
		int smtpPort,
		@WebParam(name="from")
		java.lang.String from,
		@WebParam(name="to")
		java.lang.String to,
		@WebParam(name="username")
		java.lang.String username,
		@WebParam(name="password")
		java.lang.String password,
		@WebParam(name="isSSL")
		boolean isSSL
	);
}
