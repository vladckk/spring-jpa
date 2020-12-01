package gomel.iba.by.interfaces;

import gomel.iba.by.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer>, JpaSpecificationExecutor<Genre> {

    Optional<Genre> findFirstByName(String name);

    boolean existsByName(String name);
}
