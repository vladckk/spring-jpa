package gomel.iba.by.interfaces;

import java.util.List;
import java.util.Optional;

public interface AbstractRepository<T> {

    Optional<T> findById(Long id);
    void save(T movie);
    void delete(T movie);
    List<T> findAll();
}
