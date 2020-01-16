package repository;

import entity.Document;

import javax.ejb.Stateless;

@Stateless
public class DocumentRepository extends DataRepository<Document, Long> {

    public DocumentRepository(){
        super(Document.class);
    }

}
