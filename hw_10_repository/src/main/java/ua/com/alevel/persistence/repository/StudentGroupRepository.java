package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.StudentGroup;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentGroupRepository extends AbstractRepository<StudentGroup> {

    StudentGroup getByUuid(UUID uuid);

    @EntityGraph(attributePaths = {"students"})
    Optional<StudentGroup> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    @Query(
            value = "SELECT studentGroup FROM StudentGroup studentGroup LEFT JOIN FETCH studentGroup.students",
            countQuery = "SELECT COUNT(studentGroup) FROM StudentGroup studentGroup"
    )
    Page<StudentGroup> findAll(Pageable pageable);
}
