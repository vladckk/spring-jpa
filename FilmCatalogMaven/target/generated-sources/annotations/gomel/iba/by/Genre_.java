package gomel.iba.by;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Genre.class)
public abstract class Genre_ {

	public static volatile SetAttribute<Genre, Movie> movieSet;
	public static volatile SingularAttribute<Genre, String> name;
	public static volatile SingularAttribute<Genre, Integer> id;

	public static final String MOVIE_SET = "movieSet";
	public static final String NAME = "name";
	public static final String ID = "id";

}

