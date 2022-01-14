package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.StudentGroupDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.StudentGroup;
import ua.com.alevel.util.JpaQueryUtil;
import ua.com.alevel.util.ResultSetUtil;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentGroupDaoImpl implements StudentGroupDao {

    private final JpaConfig jpaConfig;
    public static final String STUDENT_TABLE_NAME = "student";
    public static final String STUDENT_GROUP_TABLE_NAME = "student_group";
    public static final String ADJACENT_TABLE_NAME = "student_group_student";

    public StudentGroupDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(StudentGroup group) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.generateCreationQuery(STUDENT_GROUP_TABLE_NAME, 6), Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, group.getUuid(), Types.OTHER);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(group.getCreated()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(group.getUpdated()));
            preparedStatement.setString(4, group.getName());
            preparedStatement.setString(5, group.getDescription());
            preparedStatement.setString(6, group.getGroupType().name());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            long lastInsertedId = resultSet.getLong(1);
            if (group.getStudents() != null) {
                group.getStudents().stream()
                        .map(Student::getId)
                        .forEach(id -> addToAdjectiveTableIds(id, lastInsertedId));
            }
        } catch (SQLException e) {
            System.out.println("Error for creation: = " + e.getMessage());
        }
    }

    @Override
    public void update(StudentGroup group) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.generateUpdateQueryByUuid(STUDENT_GROUP_TABLE_NAME, StudentGroup.class, group.getUuid(), new String[]{"students"}))) {
            preparedStatement.setObject(1, group.getUuid(), Types.OTHER);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(group.getCreated()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(group.getUpdated()));
            preparedStatement.setString(4, group.getName());
            preparedStatement.setString(5, group.getDescription());
            preparedStatement.setString(6, group.getGroupType().name());
            preparedStatement.executeUpdate();
            if (group.getStudents() != null) {
                List<UUID> uuids = group.getStudents().stream()
                        .map(Student::getUuid)
                        .toList();
                List<Student> studentList = findStudentByUuids(uuids);
                Set<Long> resultStudentIds = studentList.stream()
                        .map(Student::getId)
                        .collect(Collectors.toSet());
                List<Student> students = getStudentsByGroupId(group.getId());
                if (!students.isEmpty()) {
                    Set<Long> resultFetchStudentIds = students.stream()
                            .map(Student::getId)
                            .collect(Collectors.toSet());
                    Set<Long> studentIdForDelete = resultFetchStudentIds.stream()
                            .filter(val -> !resultStudentIds.contains(val))
                            .collect(Collectors.toSet());
                    Set<Long> studentIdForAdd = studentList.stream()
                            .map(Student::getId)
                            .filter(val -> !resultFetchStudentIds.contains(val))
                            .collect(Collectors.toSet());
                    for (Long id : studentIdForDelete) {
                        deleteFromAdjectiveTable(id, group.getId());
                    }
                    for (Long id : studentIdForAdd) {
                        addToAdjectiveTableIds(id, group.getId());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error for update: = " + e.getMessage());
        }
    }

    @Override
    public void delete(UUID uuid) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.generateDeletionQuery(STUDENT_GROUP_TABLE_NAME, "uuid", String.valueOf(uuid)))) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error for delete: = " + e.getMessage());
        }
    }

    @Override
    public boolean existByUuid(UUID uuid) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.generateCountByUuidQuery(STUDENT_GROUP_TABLE_NAME, uuid))) {
            if (resultSet.next()) {
                count = resultSet.getLong("count");
            }
        } catch (SQLException e) {
            System.out.println("Error for find: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public StudentGroup findByUuid(UUID uuid) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.generateFindByUuidQuery(STUDENT_GROUP_TABLE_NAME, uuid))) {
            if (resultSet.next()) {
                StudentGroup group = ResultSetUtil.initStudentGroup(resultSet);
                List<Student> students = getStudentsByGroupId(group.getId());
                group.setStudents(new HashSet<>(students));
                return group;
            }
        } catch (SQLException e) {
            System.out.println("Error for find: = " + e.getMessage());
        }
        return new StudentGroup();
    }

    @Override
    public List<StudentGroup> findAll() {
        List<StudentGroup> groups = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.generateFindAllQuery(STUDENT_GROUP_TABLE_NAME))) {
            while (resultSet.next()) {
                StudentGroup group = ResultSetUtil.initStudentGroup(resultSet);
                groups.add(group);
            }
        } catch (SQLException e) {
            System.out.println("Error for search all: = " + e.getMessage());
        }
        return groups;
    }

    @Override
    public DataTableResponse<StudentGroup> findAll(DataTableRequest request) {
        List<StudentGroup> groups = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.generateFindAllWithParamsQuery(STUDENT_GROUP_TABLE_NAME, request))) {
            while (resultSet.next()) {
                StudentGroup group = ResultSetUtil.initStudentGroup(resultSet);
                groups.add(group);
            }
            if (!groups.isEmpty()) {
                groups.forEach(group -> {
                    List<Student> students = getStudentsByGroupId(group.getId());
                    group.setStudents(new HashSet<>(students));
                });
            }
        } catch (SQLException e) {
            System.out.println("Error for search all: = " + e.getMessage());
        }
        DataTableResponse<StudentGroup> response = new DataTableResponse<>();
        response.setEntities(groups);
        return response;
    }

    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.generateCountQuery(STUDENT_GROUP_TABLE_NAME))) {
            if (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void addToAdjectiveTableIds(Long studentId, Long studentGroupId) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.generateManyToManyCreationQuery(ADJACENT_TABLE_NAME))) {
            preparedStatement.setLong(1, studentId);
            preparedStatement.setLong(2, studentGroupId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error for creation: = " + e.getMessage());
        }
    }

    private List<Student> getStudentsByGroupId(Long groupId) {
        List<Student> students = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(
                JpaQueryUtil.generateFindingQuerySecondEntityByFirstEntityIdOnAdjacentTable(
                        groupId,
                        Student.class,
                        STUDENT_GROUP_TABLE_NAME,
                        STUDENT_TABLE_NAME,
                        ADJACENT_TABLE_NAME
                )
        )) {
            while (resultSet.next()) {
                Student student = ResultSetUtil.initStudent(resultSet);
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("Error for update: = " + e.getMessage());
        }
        return students;
    }

    private List<Student> findStudentByUuids(List<UUID> uuids) {
        List<Student> students = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.generateFindByUuidsQuery(uuids, STUDENT_TABLE_NAME))) {
            while (resultSet.next()) {
                Student student = ResultSetUtil.initStudent(resultSet);
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("Error for update: = " + e.getMessage());
        }
        return students;
    }

    private void deleteFromAdjectiveTable(Long studentId, Long studentGroupId) {
        Map<String, String> predicates = Map.of(
                "student_id", String.valueOf(studentId),
                "student_group_id", String.valueOf(studentGroupId)
        );
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.generateDeletionByPredicates(ADJACENT_TABLE_NAME, predicates))) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error for creation: = " + e.getMessage());
        }
    }
}
