package ua.com.alevel.dto.profile;

import ua.com.alevel.dto.table.StudentTableDto;
import ua.com.alevel.persistence.enumeration.GroupType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class StudentGroupProfileDto extends AbstractProfileDto {

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String description;

    @NotNull
    private GroupType groupType;

    private Set<StudentTableDto> students = new HashSet<>();

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

    public Set<StudentTableDto> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentTableDto> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "StudentGroupProfileDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", groupType=" + groupType +
                ", students=" + students +
                "} " + super.toString();
    }
}
