package webservices;

import webservices.rs.DocumentsController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("/services")
public class ApplicationConfig extends Application {

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(DocumentsController.class);
    }

    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

}
