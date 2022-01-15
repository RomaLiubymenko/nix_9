package ua.com.alevel.dto.response;


import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.StudentGroup;
import ua.com.alevel.persistence.enumeration.GroupType;

import java.util.List;
import java.util.UUID;

public class StudentGroupResponseDto extends ResponseDto {

    private String name;
    private String description;
    private GroupType groupType;
    private List<UUID> studentUuids;

    public StudentGroupResponseDto(StudentGroup group) {
        setUuid(group.getUuid());
        setCreated(group.getCreated());
        setUpdated(group.getUpdated());
        name = group.getName();
        description = group.getDescription();
        groupType = group.getGroupType();
        if (group.getStudents() != null) {
            studentUuids = group.getStudents().stream()
                    .map(Student::getUuid)
                    .toList();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public List<UUID> getStudentUuids() {
        return studentUuids;
    }

    public void setStudentUuids(List<UUID> studentUuids) {
        this.studentUuids = studentUuids;
    }

    @Override
    public String toString() {
        return "StudentGroupResponseDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", groupType=" + groupType +
                ", studentUuids=" + studentUuids +
                '}';
    }
}
