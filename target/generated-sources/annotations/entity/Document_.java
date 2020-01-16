package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Document.class)
public abstract class Document_ {

	public static volatile SingularAttribute<Document, Integer> registrationNumber;
	public static volatile SingularAttribute<Document, String> name;
	public static volatile SingularAttribute<Document, Long> id;

	public static final String REGISTRATION_NUMBER = "registrationNumber";
	public static final String NAME = "name";
	public static final String ID = "id";

}

