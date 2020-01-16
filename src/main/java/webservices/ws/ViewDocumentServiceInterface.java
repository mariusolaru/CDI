package webservices.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ViewDocumentServiceInterface {

    @WebMethod
    public String testMethod();
}