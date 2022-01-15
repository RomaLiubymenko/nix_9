package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.StudentGroupDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.StudentGroup;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;


@Repository
@Transactional
public class StudentGroupDaoImpl implements StudentGroupDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public StudentGroupDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(StudentGroup studentGroup) {
        entityManager.persist(studentGroup);
    }

    @Override
    public void update(StudentGroup studentGroup) {
        entityManager.merge(studentGroup);
    }

    @Override
    public void delete(UUID uuid) {
        int isSuccessful = entityManager.createQuery("delete from StudentGroup studentGroup where studentGroup.uuid = :uuid")
                .setParameter("uuid", uuid)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("Error for deleting");
        }
    }

    @Override
    public boolean existByUuid(UUID uuid) {
        Query query = entityManager.createQuery("select count(studentGroup.id) from StudentGroup studentGroup where studentGroup.uuid = :uuid")
                .setParameter("uuid", uuid);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public StudentGroup findByUuid(UUID uuid) {
        return (StudentGroup) entityManager.createQuery("select studentGroup from StudentGroup studentGroup where studentGroup.uuid = :uuid")
                .setParameter("uuid", uuid)
                .getSingleResult();
    }

    @Override
    public List<StudentGroup> findAll() {
        return entityManager.createQuery("select studentGroup from StudentGroup studentGroup").getResultList();
    }

    @Override
    public DataTableResponse<StudentGroup> findAll(DataTableRequest request) {
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StudentGroup> criteriaQuery = criteriaBuilder.createQuery(StudentGroup.class);
        Root<StudentGroup> from = criteriaQuery.from(StudentGroup.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        List<StudentGroup> studentGroups = entityManager.createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(request.getPageSize())
                .getResultList();
        DataTableResponse<StudentGroup> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setEntities(studentGroups);
        return dataTableResponse;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(studentGroup.id) from StudentGroup studentGroup");
        return (Long) query.getSingleResult();
    }
}
