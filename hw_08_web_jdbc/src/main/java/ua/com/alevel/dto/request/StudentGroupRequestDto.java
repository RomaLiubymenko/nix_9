package ua.com.alevel.dto.request;

import ua.com.alevel.persistence.enumeration.GroupType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentGroupRequestDto extends RequestDto {

    private String name;
    private String description;
    private GroupType groupType;
    private List<UUID> studentUuids = new ArrayList<>();

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
        return "StudentGroupRequestDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", groupType=" + groupType +
                ", studentUuids=" + studentUuids +
                '}';
    }
}
