package ua.com.alevel.dto.profile;

import ua.com.alevel.dto.table.StudentGroupTableDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class StudentProfileDto extends AbstractProfileDto {

    @NotBlank
    @NotEmpty
    private String firstName;

    @NotBlank
    @NotEmpty
    private String lastName;

    @Past
    @NotNull
    private LocalDate birthDay;

    private Set<StudentGroupTableDto> studentGroups = new HashSet<>();

    public Set<StudentGroupTableDto> getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(Set<StudentGroupTableDto> studentGroups) {
        this.studentGroups = studentGroups;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "StudentProfileDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", studentGroups=" + studentGroups +
                "} " + super.toString();
    }
}
