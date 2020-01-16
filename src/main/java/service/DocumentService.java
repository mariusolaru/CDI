package service;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Named
@SessionScoped
public class DocumentService implements Serializable {

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
}
