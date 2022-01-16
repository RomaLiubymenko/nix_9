package ua.com.alevel.persistence.dao.impl;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Student student) {
        entityManager.persist(student);
    }

    @Override
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    public void delete(UUID uuid) {
        int isSuccessful = entityManager.createQuery("delete from Student student where student.uuid = :uuid")
                .setParameter("uuid", uuid)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("Error for deleting");
        }
    }

    @Override
    public boolean existByUuid(UUID uuid) {
        Query query = entityManager.createQuery("select count(student.id) from Student student where student.uuid = :uuid")
                .setParameter("uuid", uuid);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Student findByUuid(UUID uuid) {
        return (Student) entityManager.createQuery("select student from Student student left join fetch student.studentGroups where student.uuid = :uuid")
                .setParameter("uuid", uuid)
                .getSingleResult();
    }

    @Override
    public Set<Student> findAll() {
        return Set.copyOf(entityManager.createQuery("select student from Student student left join fetch student.studentGroups", Student.class).getResultList());
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> from = criteriaQuery.from(Student.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        List<Student> students = entityManager.createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(request.getPageSize())
                .getResultList();
        for (Student student : students) {
            Hibernate.initialize(student.getStudentGroups());
        }
        DataTableResponse<Student> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setEntities(students);
        return dataTableResponse;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(student.id) from Student student");
        return (Long) query.getSingleResult();
    }
}
