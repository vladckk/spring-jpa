package gomel.iba.by.interfaces;

import gomel.iba.by.MovieGenres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieGenresRepository extends JpaRepository<MovieGenres, Integer> {
    List<MovieGenres> findByIdMovies(int id);

    boolean existsByIdMoviesAndIdGenres(int idMovies, int idGenres);

    void deleteAllByIdMovies(int idMovies);
}
