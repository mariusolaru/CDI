package util;

import dto.DocumentDTO;
import entity.Admin;
import entity.Document;
import entity.Guest;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class EntityConverter {

    public static Guest toGuest(User user){
        Guest guest = new Guest();

        guest.setUsername(user.getUsername());
        guest.setPassword(user.getPassword());

        return guest;
    }

    public static Admin toAdmin(User user){
        Admin admin = new Admin();

        admin.setUsername(user.getUsername());
        admin.setPassword(user.getPassword());

        return admin;
    }

    public static List<DocumentDTO> toDocumentDTOList(List<Document> documents){
        List<DocumentDTO> documentDTOS = new ArrayList<>();

        for(Document document : documents){
            DocumentDTO documentDTO = new DocumentDTO();

            documentDTO.setId(document.getId());
            documentDTO.setRegistrationNumber(document.getRegistrationNumber());
            documentDTO.setName(document.getName());
            documentDTO.setUserId(document.getUser().getId());

            documentDTOS.add(documentDTO);
        }

        return documentDTOS;
    }
}
