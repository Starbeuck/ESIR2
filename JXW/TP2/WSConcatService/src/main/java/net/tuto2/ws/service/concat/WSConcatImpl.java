package net.tuto2.ws.service.concat;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "net.tuto2.ws.service.concat.WSConcat",
	serviceName = "MyConcatAgent")
public class WSConcatImpl {

  public String concat (String str1,String str2){
	return str1+str2;
  }
}