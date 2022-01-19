package ua.com.alevel.facade.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.profile.StudentProfileDto;
import ua.com.alevel.dto.table.StudentGroupTableDto;
import ua.com.alevel.dto.table.StudentTableDto;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.mapper.StudentMapper;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.StudentGroup;
import ua.com.alevel.persistence.repository.StudentGroupRepository;
import ua.com.alevel.persistence.repository.StudentRepository;
import ua.com.alevel.service.StudentService;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final StudentGroupRepository studentGroupRepository;

    public StudentFacadeImpl(
            StudentService studentService,
            StudentMapper studentMapper,
            StudentRepository studentRepository,
            StudentGroupRepository studentGroupRepository) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
        this.studentGroupRepository = studentGroupRepository;
    }

    @Override
    public StudentProfileDto create(StudentProfileDto studentProfileDto) {
        Student student = studentMapper.toEntity(studentProfileDto, new Student());
        if (!student.getStudentGroups().isEmpty()) {
            for (StudentGroup studentGroup : student.getStudentGroups()) {
                studentGroup.addStudent(student);
            }
        }
        Student savedStudent = studentService.save(student);
        return studentMapper.toProfileDto(savedStudent);
    }

    @Override
    public Optional<StudentProfileDto> update(StudentProfileDto studentProfileDto, UUID studentUuid) {
        boolean isExistStudent = studentRepository.existsByUuid(studentUuid);
        if (isExistStudent) {
            Student student = studentService.findByUuid(studentUuid).get();
            Set<StudentGroup> groupForDelete;
            if (CollectionUtils.isNotEmpty(studentProfileDto.getStudentGroups())) {
                List<UUID> groupUuids = studentProfileDto.getStudentGroups().stream()
                        .map(StudentGroupTableDto::getUuid)
                        .toList();
                groupForDelete = student.getStudentGroups().stream()
                        .filter(group -> !groupUuids.contains(group.getUuid()))
                        .collect(Collectors.toSet());
            } else {
                groupForDelete = student.getStudentGroups();
            }
            for (StudentGroup studentGroup : groupForDelete) {
                studentGroup.removeStudent(student);
                studentGroupRepository.save(studentGroup);
            }
            Student mappedStudent = studentMapper.toEntity(studentProfileDto, student);
            for (StudentGroup studentGroup : mappedStudent.getStudentGroups()) {
                studentGroup.addStudent(mappedStudent);
            }
            Student savedStudent = studentService.save(mappedStudent);
            studentGroupRepository.saveAll(mappedStudent.getStudentGroups());
            return Optional.of(studentMapper.toProfileDto(savedStudent));
        }
        return Optional.empty();
    }

    @Override
    public void delete(UUID uuid) {
        studentService.findByUuid(uuid).ifPresent(student -> {
            Set<StudentGroup> groups = new HashSet<>(student.getStudentGroups());
            groups.forEach(student::removeStudentGroup);
            studentService.delete(uuid);
        });
    }

    @Override
    public Optional<StudentProfileDto> findByUuid(UUID uuid) {
        return studentService.findByUuid(uuid)
                .map(studentMapper::toProfileDto);
    }

    @Override
    public Page<StudentTableDto> findAll(Pageable pageable) {
        return studentService.findAll(pageable)
                .map(studentMapper::toTableDto);
    }

    @Override
    public List<StudentTableDto> findAll() {
        return studentService.findAll().stream()
                .map(studentMapper::toTableDto)
                .toList();
    }
}
