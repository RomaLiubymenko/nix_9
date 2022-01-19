package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "student",
        indexes = {
                @Index(name = "student_uuid_index", columnList = "uuid", unique = true)
        }
)
public class Student extends AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentId")
    @SequenceGenerator(name = "studentId", sequenceName = "student_id", allocationSize = 1)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @ManyToMany(mappedBy = "students")
    private Set<StudentGroup> studentGroups = new HashSet<>();

    public void addStudentGroup(StudentGroup studentGroup) {
        if (!studentGroups.contains(studentGroup)) {
            studentGroups.add(studentGroup);
            studentGroup.addStudent(this);
        }
    }

    public void removeStudentGroup(StudentGroup studentGroup) {
        if (studentGroups.contains(studentGroup)) {
            studentGroups.remove(studentGroup);
            studentGroup.removeStudent(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<StudentGroup> getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(Set<StudentGroup> studentGroups) {
        this.studentGroups = studentGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getUuid().equals(student.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                "} " + super.toString();
    }
}
