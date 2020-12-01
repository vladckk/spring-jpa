package gomel.iba.by.specifications;

import gomel.iba.by.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;


public class MovieSpecifications {

    public static Specification<Movie> likeTitle(String title) {
        if (title == null || title.equals("")) return (root, query, cb) -> cb.conjunction();
        return (root, query, cb) -> cb.like(root.get(Movie_.TITLE), "%" + title + "%");
    }

    public static Specification<Movie> equalYear(Integer year) {
        if (year == null) return (root, query, cb) -> cb.conjunction();
        return (root, query, cb) -> cb.equal(root.get(Movie_.YEAR), year);
    }

    public static Specification<Movie> likeDirector(String director) {
        if (director == null || director.equals("")) return (root, query, cb) -> cb.conjunction();
        return (root, query, cb) -> cb.like(root.get(Movie_.DIRECTOR).get(Staff_.FULL_NAME), "%" + director + "%");
    }

    public static Specification<Movie> likeActor(String actor) {
        if (actor == null || actor.equals("")) return (root, query, cb) -> cb.conjunction();
        return (root, query, cb) -> {
            Join<Movie, Staff> staff = root.join(Movie_.ACTOR_LIST);
            return cb.like(staff.get(Staff_.FULL_NAME), "%" + actor + "%");
        };
    }

    public static Specification<Movie> likeGenre(String genre) {
        if (genre == null || genre.equals("")) return (root, query, cb) -> cb.conjunction();
        return (root, query, cb) -> {
            Join<Movie, Genre> genres = root.join(Movie_.GENRE_SET);
            return cb.like(genres.get(Genre_.NAME), "%" + genre + "%");
        };
    }
}
