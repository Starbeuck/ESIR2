package net.tuto2.ws.service.concat;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://david.bromberg.fr/service/concat",
	name = "WSConcat")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface WSConcat {
	@WebMethod
	public String concat(
		@WebParam(name = "str1")
		String str1,
		@WebParam(name = "str2")
		String str2
);
}