package gomel.iba.by.interfaces;

import gomel.iba.by.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    @Query("SELECT st.fullName FROM Staff st, MovieStaff mst WHERE mst.idMovies = :idFilm AND " +
            "mst.idStaff = st.id AND st.position <> 'director'")
    List<String> findByMoviesIdActors(@Param("idFilm") Integer idFilm);

    @Query("SELECT st.fullName FROM Staff st, MovieStaff mst WHERE mst.idMovies = :idFilm AND " +
            "mst.idStaff = st.id AND st.position = 'director'")
    Optional<String> findFirstByMoviesIdDirector(@Param("idFilm") Integer idFilm);

    Optional<Staff> findFirstByFullName(String fullName);

    Optional<Staff> findFirstByFullNameAndPosition(String fullName, String position);

    boolean existsByFullNameAndPosition(String fullName, String position);

}
