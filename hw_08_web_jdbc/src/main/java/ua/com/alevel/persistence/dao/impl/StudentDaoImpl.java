package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.StudentDao;
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
public class StudentDaoImpl implements StudentDao {

    private final JpaConfig jpaConfig;
    public static final String STUDENT_TABLE_NAME = "student";
    public static final String STUDENT_GROUP_TABLE_NAME = "student_group";
    public static final String ADJACENT_TABLE_NAME = "student_group_student";

    public StudentDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Student student) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.generateCreationQuery(STUDENT_TABLE_NAME, 6), Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, student.getUuid(), Types.OTHER);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(student.getCreated()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(student.getUpdated()));
            preparedStatement.setString(4, student.getFirstName());
            preparedStatement.setString(5, student.getLastName());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(student.getBirthDay().atStartOfDay()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            long lastInsertedId = resultSet.getLong(1);
            if (student.getStudentGroups() != null) {
                student.getStudentGroups().stream()
                        .map(StudentGroup::getId)
                        .forEach(id -> addToAdjectiveTableIds(lastInsertedId, id));
            }
        } catch (SQLException e) {
            System.out.println("Error for creation: = " + e.getMessage());
        }
    }

    @Override
    public void update(Student student) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.generateUpdateQueryByUuid(STUDENT_TABLE_NAME, Student.class, student.getUuid(), new String[]{"studentGroups"}))) {
            preparedStatement.setObject(1, student.getUuid(), Types.OTHER);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(student.getCreated()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(student.getUpdated()));
            preparedStatement.setString(4, student.getFirstName());
            preparedStatement.setString(5, student.getLastName());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(student.getBirthDay().atStartOfDay()));
            preparedStatement.executeUpdate();
            if (student.getStudentGroups() != null) {
                List<UUID> uuids = student.getStudentGroups().stream()
                        .map(StudentGroup::getUuid)
                        .toList();
                List<StudentGroup> groupList = findStudentGroupByUuids(uuids);
                Set<Long> resultGroupIds = groupList.stream()
                        .map(StudentGroup::getId)
                        .collect(Collectors.toSet());
                List<StudentGroup> groups = getStudentGroupsByStudentId(student.getId());
                if (!groups.isEmpty()) {
                    Set<Long> resultFetchGroupIds = groups.stream()
                            .map(StudentGroup::getId)
                            .collect(Collectors.toSet());
                    Set<Long> studentGroupIdForDelete = resultFetchGroupIds.stream()
                            .filter(val -> !resultGroupIds.contains(val))
                            .collect(Collectors.toSet());
                    Set<Long> studentGroupIdForAdd = groupList.stream()
                            .map(StudentGroup::getId)
                            .filter(val -> !resultFetchGroupIds.contains(val))
                            .collect(Collectors.toSet());
                    for (Long id : studentGroupIdForDelete) {
                        deleteFromAdjectiveTable(student.getId(), id);
                    }
                    for (Long id : studentGroupIdForAdd) {
                        addToAdjectiveTableIds(student.getId(), id);
                    }
                } else {
                    for (Long id : resultGroupIds) {
                        addToAdjectiveTableIds(student.getId(), id);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error for update: = " + e.getMessage());
        }
    }

    @Override
    public void delete(UUID uuid) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(JpaQueryUtil.generateDeletionQuery(STUDENT_TABLE_NAME, "uuid", String.valueOf(uuid)))) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error for delete: = " + e.getMessage());
        }
    }

    @Override
    public boolean existByUuid(UUID uuid) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.generateCountByUuidQuery(STUDENT_TABLE_NAME, uuid))) {
            if (resultSet.next()) {
                count = resultSet.getLong("count");
            }
        } catch (SQLException e) {
            System.out.println("Error for find: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Student findByUuid(UUID uuid) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.generateFindByUuidQuery(STUDENT_TABLE_NAME, uuid))) {
            if (resultSet.next()) {
                Student student = ResultSetUtil.initStudent(resultSet);
                List<StudentGroup> groups = getStudentGroupsByStudentId(student.getId());
                student.setStudentGroups(new HashSet<>(groups));
                return student;
            }
        } catch (SQLException e) {
            System.out.println("Error for find: = " + e.getMessage());
        }
        return new Student();
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.generateFindAllQuery(STUDENT_TABLE_NAME))) {
            while (resultSet.next()) {
                Student student = ResultSetUtil.initStudent(resultSet);
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("Error for search all: = " + e.getMessage());
        }
        return students;
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        List<Student> students = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.generateFindAllWithParamsQuery(STUDENT_TABLE_NAME, request))) {
            while (resultSet.next()) {
                Student student = ResultSetUtil.initStudent(resultSet);
                students.add(student);
            }
            if (!students.isEmpty()) {
                students.forEach(student -> {
                    List<StudentGroup> groups = getStudentGroupsByStudentId(student.getId());
                    student.setStudentGroups(new HashSet<>(groups));
                });
            }
        } catch (SQLException e) {
            System.out.println("Error for search all: = " + e.getMessage());
        }
        DataTableResponse<Student> response = new DataTableResponse<>();
        response.setEntities(students);
        return response;
    }

    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.generateCountQuery(STUDENT_TABLE_NAME))) {
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

    private List<StudentGroup> getStudentGroupsByStudentId(Long studentId) {
        List<StudentGroup> studentGroups = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(
                JpaQueryUtil.generateFindingQuerySecondEntityByFirstEntityIdOnAdjacentTable(
                        studentId,
                        StudentGroup.class,
                        STUDENT_TABLE_NAME,
                        STUDENT_GROUP_TABLE_NAME,
                        ADJACENT_TABLE_NAME
                )
        )) {
            while (resultSet.next()) {
                StudentGroup group = ResultSetUtil.initStudentGroup(resultSet);
                studentGroups.add(group);
            }
        } catch (SQLException e) {
            System.out.println("Error for update: = " + e.getMessage());
        }
        return studentGroups;
    }

    private List<StudentGroup> findStudentGroupByUuids(List<UUID> uuids) {
        List<StudentGroup> studentGroups = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(JpaQueryUtil.generateFindByUuidsQuery(uuids, STUDENT_GROUP_TABLE_NAME))) {
            while (resultSet.next()) {
                StudentGroup group = ResultSetUtil.initStudentGroup(resultSet);
                studentGroups.add(group);
            }
        } catch (SQLException e) {
            System.out.println("Error for update: = " + e.getMessage());
        }
        return studentGroups;
    }
}
