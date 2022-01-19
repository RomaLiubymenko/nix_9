package ua.com.alevel.dto.table;


import ua.com.alevel.dto.table.helpdto.StudentGroupForStudentTableDto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class StudentTableDto extends AbstractTableDto {

    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private Set<StudentGroupForStudentTableDto> studentGroups = new HashSet<>();

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

    public Set<StudentGroupForStudentTableDto> getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(Set<StudentGroupForStudentTableDto> studentGroups) {
        this.studentGroups = studentGroups;
    }

    @Override
    public String toString() {
        return "StudentTableDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", studentGroups=" + studentGroups +
                "} " + super.toString();
    }
}
