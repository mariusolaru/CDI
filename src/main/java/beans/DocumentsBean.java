package beans;

import entity.Document;
import interceptors.ValidPeriod;
import org.primefaces.model.UploadedFile;
import producers.registrationNumber.DocumentRegistrationNumber;
import repository.DocumentRepository;
import service.AuthenticationService;
import service.DocumentService;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
@DeclareRoles({"admin", "guest"})
public class DocumentsBean {

    private UploadedFile file;

    @Inject
    @DocumentRegistrationNumber
    private Integer registrationNumber;

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private DocumentService documentService;

    @EJB
    private DocumentRepository documentRepository;

    @Any
    @Inject
    private Event<Document> event;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    @ValidPeriod
    @RolesAllowed("guest")
    public void upload() {
        if(file != null) {
            documentService.uploadDocument(file.getContents(), registrationNumber);

            Document newDocument = new Document();
            newDocument.setRegistrationNumber(registrationNumber);
            newDocument.setName(file.getFileName());
            newDocument.setUser(authenticationService.getCurrentUser());

            documentRepository.persist(newDocument);

            event.fire(newDocument);

            //documentEvent.fire(newDocument);
            FacesMessage message = new FacesMessage("Success", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
