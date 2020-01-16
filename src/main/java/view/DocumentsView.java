package view;

import com.sun.faces.component.visit.FullVisitContext;
import entity.Document;
import org.primefaces.PrimeFaces;
import org.primefaces.component.poll.Poll;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import repository.DocumentRepository;
import service.DocumentService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

@Named
@RequestScoped
public class DocumentsView {

    @EJB
    private DocumentRepository documentRepository;

    @Inject
    private DocumentService documentService;

    private List<Document> documents;

    private StreamedContent file;

    @PostConstruct
    public void init() {
        this.documents = documentRepository.findAll();
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public StreamedContent getFile(Integer registrationNumber, String name) {
        InputStream stream = new ByteArrayInputStream(documentService.getDocumentByRegistrationNumber(registrationNumber));
        file = new DefaultStreamedContent(stream, FacesContext.getCurrentInstance().getExternalContext().getMimeType(name), name);
        return file;
    }

    public void onDocumentsUpload(@Observes Document document) {
        documents.add(document);

        PrimeFaces.current().ajax().update("output-panel:docsTable");
    }
}