package gomel.iba.by.interfaces;

import gomel.iba.by.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Query(value = "SELECT g.name FROM Genre g, MovieGenres mg  WHERE :idFilm = mg.idMovies AND mg.idGenres = g.id")
    List<String> getAllGenresByFilm(@Param("idFilm") Integer idFilm);

    Optional<Genre> findFirstByName(String name);

    boolean existsByName(String name);
}
