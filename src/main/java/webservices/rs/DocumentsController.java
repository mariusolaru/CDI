package webservices.rs;

import dto.DocumentDTO;
import entity.Document;
import service.DocumentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

import static util.EntityConverter.toDocumentDTOList;

@Path(value = "/documents")
public class DocumentsController {

    @Inject
    private DocumentService documentService;

    @GET
    public List<DocumentDTO> getAllDocuments(){
        return toDocumentDTOList(documentService.getAllDocuments());
    }

    @GET
    @Path("{id}")
    public Document getDocumentById(@PathParam("id") Long id) {
        return documentService.getDocumentById(id);
    }

    @POST
    public void addDocument(DocumentDTO documentDTO) {
        documentService.addDocument(documentDTO);
    }

    @DELETE
    @Path("{id}")
    public void deleteDocument(@PathParam("id") Long id){
        documentService.deleteDocument(id);
    }
}
