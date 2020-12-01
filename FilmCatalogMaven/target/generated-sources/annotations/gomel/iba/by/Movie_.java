package gomel.iba.by;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Movie.class)
public abstract class Movie_ {

	public static volatile SingularAttribute<Movie, Integer> year;
	public static volatile SingularAttribute<Movie, Staff> director;
	public static volatile SetAttribute<Movie, Genre> genreSet;
	public static volatile ListAttribute<Movie, Staff> actorList;
	public static volatile SingularAttribute<Movie, Integer> id;
	public static volatile SingularAttribute<Movie, String> title;

	public static final String YEAR = "year";
	public static final String DIRECTOR = "director";
	public static final String GENRE_SET = "genreSet";
	public static final String ACTOR_LIST = "actorList";
	public static final String ID = "id";
	public static final String TITLE = "title";

}

