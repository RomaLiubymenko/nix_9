package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Student;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends AbstractRepository<Student> {

    Student getByUuid(UUID uuid);

    @EntityGraph(attributePaths = {"studentGroups"})
    Optional<Student> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    @Override
    @Query(
            value = "SELECT student FROM Student student LEFT JOIN FETCH student.studentGroups",
            countQuery = "SELECT COUNT(student) FROM Student student"
    )
    Page<Student> findAll(Pageable pageable);
}
