package ua.com.alevel.dto.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentRequestDto extends RequestDto {

    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private List<UUID> studentGroupUuids = new ArrayList<>();

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
        return "StudentRequestDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", studentGroupUuids=" + studentGroupUuids +
                '}';
    }
}
