package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Period.class)
public abstract class Period_ {

	public static volatile SingularAttribute<Period, Date> endDate;
	public static volatile SingularAttribute<Period, Long> id;
	public static volatile SingularAttribute<Period, Admin> setBy;
	public static volatile SingularAttribute<Period, Date> startDate;

	public static final String END_DATE = "endDate";
	public static final String ID = "id";
	public static final String SET_BY = "setBy";
	public static final String START_DATE = "startDate";

}

