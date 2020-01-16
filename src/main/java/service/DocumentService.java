package service;

import dto.DocumentDTO;
import entity.Admin;
import entity.Document;
import entity.User;
import producers.registrationNumber.DocumentRegistrationNumber;
import repository.AdminRepository;
import repository.DocumentRepository;
import repository.UserRepository;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Named
@SessionScoped
public class DocumentService implements Serializable {

    @EJB
    private DocumentRepository documentRepository;

    @EJB
    private UserRepository userRepository;

    @Inject
    @DocumentRegistrationNumber
    private Integer registrationNumber;

    public void uploadDocument(byte[] rawDocument, Integer registrationNumber) {
        try {
            Path path = Paths.get("/Users/mariusol/Documents/Faculty/AEA/AEA/documents/" + registrationNumber);
            Files.write(path, rawDocument);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getDocumentByRegistrationNumber(Integer registrationNumber) {
        try {
            Path path = Paths.get("/Users/mariusol/Documents/Faculty/AEA/AEA/documents/" + registrationNumber);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Document> getAllDocuments(){
        return documentRepository.findAll();
    }

    public Document getDocumentById(Long documentId){
        return documentRepository.findById(documentId);
    }

    public void addDocument(DocumentDTO documentDTO){
        User user = userRepository.findById(documentDTO.getUserId());

        Document document = new Document();
        document.setRegistrationNumber(registrationNumber);
        document.setName(documentDTO.getName());
        document.setUser(user);

        documentRepository.persist(document);
    }

    public void deleteDocument(Long documentId){
        Document document = getDocumentById(documentId);

        documentRepository.remove(document);
    }
}
