package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.enumeration.GroupType;

import java.util.Objects;
import java.util.Set;

public class StudentGroup extends AbstractEntity {

    private String name;
    private String description;
    private GroupType groupType;
    private Set<Student> students;

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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroup group = (StudentGroup) o;
        return getUuid().equals(group.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", groupType=" + groupType +
                ", students=" + students +
                '}';
    }
}
