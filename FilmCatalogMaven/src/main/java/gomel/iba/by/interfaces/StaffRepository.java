package gomel.iba.by.interfaces;

import gomel.iba.by.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>, JpaSpecificationExecutor<Staff> {

    Optional<Staff> findFirstByFullName(String fullName);

    Optional<Staff> findFirstByFullNameAndPosition(String fullName, String position);

    boolean existsByFullNameAndPosition(String fullName, String position);

}
