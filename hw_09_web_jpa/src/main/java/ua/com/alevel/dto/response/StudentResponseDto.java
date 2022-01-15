package ua.com.alevel.dto.response;


import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.StudentGroup;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class StudentResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private List<UUID> studentGroupUuids;

    public StudentResponseDto(Student student) {
        setUuid(student.getUuid());
        setCreated(student.getCreated());
        setUpdated(student.getUpdated());
        firstName = student.getFirstName();
        lastName = student.getLastName();
        birthDay = student.getBirthDay();
        if (student.getStudentGroups() != null) {
            studentGroupUuids = student.getStudentGroups().stream()
                    .map(StudentGroup::getUuid)
                    .toList();
        }
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

    public List<UUID> getStudentGroupUuids() {
        return studentGroupUuids;
    }

    public void setStudentGroupUuids(List<UUID> studentGroupUuids) {
        this.studentGroupUuids = studentGroupUuids;
    }

    @Override
    public String toString() {
        return "StudentResponseDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", studentGroupUuids=" + studentGroupUuids +
                '}';
    }
}
