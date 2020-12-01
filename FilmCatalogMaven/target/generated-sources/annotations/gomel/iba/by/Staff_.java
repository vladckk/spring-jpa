package gomel.iba.by;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Staff.class)
public abstract class Staff_ {

	public static volatile SingularAttribute<Staff, String> fullName;
	public static volatile SingularAttribute<Staff, Integer> id;
	public static volatile SingularAttribute<Staff, String> position;
	public static volatile ListAttribute<Staff, Movie> movieList;

	public static final String FULL_NAME = "fullName";
	public static final String ID = "id";
	public static final String POSITION = "position";
	public static final String MOVIE_LIST = "movieList";

}

