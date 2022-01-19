package ua.com.alevel.dto.table;


import ua.com.alevel.dto.table.helpdto.StudentForStudentGroupTableDto;
import ua.com.alevel.persistence.enumeration.GroupType;

import java.util.HashSet;
import java.util.Set;

public class StudentGroupTableDto extends AbstractTableDto {

    private String name;
    private String description;
    private GroupType groupType;
    private Set<StudentForStudentGroupTableDto> students = new HashSet<>();

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

    public Set<StudentForStudentGroupTableDto> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentForStudentGroupTableDto> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "StudentGroupTableDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", groupType=" + groupType +
                ", students=" + students +
                "} " + super.toString();
    }
}
