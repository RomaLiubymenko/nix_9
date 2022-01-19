package ua.com.alevel.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.alevel.dto.profile.StudentGroupProfileDto;
import ua.com.alevel.dto.table.StudentGroupTableDto;
import ua.com.alevel.dto.table.helpdto.StudentGroupForStudentTableDto;
import ua.com.alevel.persistence.entity.StudentGroup;
import ua.com.alevel.service.StudentGroupService;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Mapper(
        componentModel = "spring",
        uses = {StudentMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class StudentGroupMapper {

    @Autowired
    private StudentGroupService studentGroupService;

    @Mapping(target = "students", qualifiedByName = "toStudentDto")
    public abstract StudentGroupProfileDto toProfileDto(StudentGroup studentGroup);

    @Mapping(target = "students", qualifiedByName = "toStudent")
    public abstract StudentGroup toEntity(StudentGroupProfileDto studentGroupDto, @MappingTarget StudentGroup studentGroup);

    @Mapping(target = "students", qualifiedByName = "toStudentTableDto")
    public abstract StudentGroupTableDto toTableDto(StudentGroup studentGroup);

    @Mapping(target = "students", ignore = true)
    abstract StudentGroupTableDto toDtoForStudentProfileDto(StudentGroup studentGroup);

    @IterableMapping(qualifiedByName = "toGroupDto")
    @Named("toGroupDto")
    Set<StudentGroupTableDto> studentGroupForStudentProfileDto(Set<StudentGroup> studentGroups) {
        if (studentGroups != null) {
            return studentGroups.stream()
                    .filter(Objects::nonNull)
                    .map(this::toDtoForStudentProfileDto)
                    .collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

    @IterableMapping(qualifiedByName = "toGroup")
    @Named("toGroup")
    Set<StudentGroup> studentGroupDtoForStudent(Set<StudentGroupTableDto> studentGroups) {
        if (studentGroups != null) {
            return studentGroups.stream()
                    .filter(Objects::nonNull)
                    .map(group -> studentGroupService.getByUuid(group.getUuid()))
                    .collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

    @IterableMapping(qualifiedByName = "toTableGroupDto")
    @Named("toTableGroupDto")
    Set<StudentGroupForStudentTableDto> studentGroupForStudentTableDto(Set<StudentGroup> studentGroups) {
        if (studentGroups != null) {
            Set<StudentGroupForStudentTableDto> groups = new HashSet<>();
            studentGroups.stream()
                    .filter(Objects::nonNull)
                    .forEach(group -> {
                        StudentGroupForStudentTableDto studentGroupDto = new StudentGroupForStudentTableDto();
                        studentGroupDto.setUuid(group.getUuid());
                        studentGroupDto.setName(group.getName());
                        groups.add(studentGroupDto);
                    });
            return groups;
        }
        return new HashSet<>();
    }
}
