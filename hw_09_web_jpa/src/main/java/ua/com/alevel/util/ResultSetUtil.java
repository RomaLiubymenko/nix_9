package ua.com.alevel.util;

import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.StudentGroup;
import ua.com.alevel.persistence.enumeration.GroupType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ResultSetUtil {

    private ResultSetUtil() {
    }

    public static Student initStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setUuid(UUID.fromString(resultSet.getString("uuid")));
        student.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
        student.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        student.setBirthDay(resultSet.getTimestamp("birth_day").toLocalDateTime().toLocalDate());
        return student;
    }

    public static StudentGroup initStudentGroup(ResultSet resultSet) throws SQLException {
        StudentGroup group = new StudentGroup();
        group.setId(resultSet.getLong("id"));
        group.setUuid(UUID.fromString(resultSet.getString("uuid")));
        group.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
        group.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
        group.setName(resultSet.getString("name"));
        group.setDescription(resultSet.getString("description"));
        group.setGroupType(GroupType.valueOf(resultSet.getString("group_type")));
        return group;
    }
}
