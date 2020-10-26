package gomel.iba.by.interfaces;

import gomel.iba.by.MovieStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieStaffRepository extends JpaRepository<MovieStaff, Integer> {
    List<MovieStaff> findByIdMovies(int id);

    Optional<MovieStaff> findByIdMoviesAndIdStaff(int idMovies, int idStaff);

    void deleteAllByIdMovies(int idMovies);
}
