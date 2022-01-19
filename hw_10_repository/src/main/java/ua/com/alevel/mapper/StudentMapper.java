package ua.com.alevel.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.alevel.dto.profile.StudentProfileDto;
import ua.com.alevel.dto.table.StudentTableDto;
import ua.com.alevel.dto.table.helpdto.StudentForStudentGroupTableDto;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.StudentService;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Mapper(
        componentModel = "spring",
        uses = {StudentGroupMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class StudentMapper {

    @Autowired
    private StudentService studentService;

    @Mapping(target = "studentGroups", qualifiedByName = "toGroupDto")
    public abstract StudentProfileDto toProfileDto(Student student);

    @Mapping(target = "studentGroups", qualifiedByName = "toTableGroupDto")
    public abstract StudentTableDto toTableDto(Student student);

    @Mapping(target = "studentGroups", qualifiedByName = "toGroup")
    public abstract Student toEntity(StudentProfileDto studentDto, @MappingTarget Student student);

    @Mapping(target = "studentGroups", ignore = true)
    abstract StudentTableDto toDtoForStudentGroupProfileDto(Student student);

    @IterableMapping(qualifiedByName = "toStudentDto")
    @Named("toStudentDto")
    Set<StudentTableDto> studentForStudentGroupProfileDto(Set<Student> students) {
        if (students != null) {
            return students.stream()
                    .filter(Objects::nonNull)
                    .map(this::toDtoForStudentGroupProfileDto)
                    .collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

    @IterableMapping(qualifiedByName = "toStudent")
    @Named("toStudent")
    Set<Student> studentDtoForStudentGroup(Set<StudentTableDto> students) {
        if (students != null) {
            return students.stream()
                    .filter(Objects::nonNull)
                    .map(student -> studentService.getByUuid(student.getUuid()))
                    .collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

    @IterableMapping(qualifiedByName = "toStudentTableDto")
    @Named("toStudentTableDto")
    Set<StudentForStudentGroupTableDto> studentForStudentGroupTableDto(Set<Student> students) {
        if (students != null) {
            Set<StudentForStudentGroupTableDto> studentDtos = new HashSet<>();
            students.stream()
                    .filter(Objects::nonNull)
                    .forEach(student -> {
                        StudentForStudentGroupTableDto studentDto = new StudentForStudentGroupTableDto();
                        studentDto.setUuid(student.getUuid());
                        studentDto.setFirstName(student.getFirstName());
                        studentDto.setLastName(student.getLastName());
                        studentDtos.add(studentDto);
                    });
            return studentDtos;
        }
        return new HashSet<>();
    }
}
