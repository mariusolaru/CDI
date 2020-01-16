package webservices.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "ViewDocumentService")
public class ViewDocumentService implements ViewDocumentServiceInterface {

    @WebMethod(operationName = "test")
    public String testMethod() {
        return "Hello from WebService";
    }
}
