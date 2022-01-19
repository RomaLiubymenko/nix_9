package ua.com.alevel.facade.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.profile.StudentGroupProfileDto;
import ua.com.alevel.dto.table.StudentGroupTableDto;
import ua.com.alevel.dto.table.StudentTableDto;
import ua.com.alevel.facade.StudentGroupFacade;
import ua.com.alevel.mapper.StudentGroupMapper;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.StudentGroup;
import ua.com.alevel.persistence.repository.StudentGroupRepository;
import ua.com.alevel.service.StudentGroupService;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class StudentGroupFacadeImpl implements StudentGroupFacade {

    private final StudentGroupMapper studentGroupMapper;
    private final StudentGroupService studentGroupService;
    private final StudentGroupRepository studentGroupRepository;

    public StudentGroupFacadeImpl(StudentGroupMapper studentGroupMapper, StudentGroupService studentGroupService, StudentGroupRepository studentGroupRepository) {
        this.studentGroupMapper = studentGroupMapper;
        this.studentGroupService = studentGroupService;
        this.studentGroupRepository = studentGroupRepository;
    }

    @Override
    public StudentGroupProfileDto create(StudentGroupProfileDto studentGroupProfileDto) {
        StudentGroup studentGroup = studentGroupMapper.toEntity(studentGroupProfileDto, new StudentGroup());
        if (!studentGroup.getStudents().isEmpty()) {
            for (Student student : studentGroup.getStudents()) {
                student.addStudentGroup(studentGroup);
            }
        }
        StudentGroup savedGroup = studentGroupService.save(studentGroup);
        return studentGroupMapper.toProfileDto(savedGroup);
    }


    @Override
    public Optional<StudentGroupProfileDto> update(StudentGroupProfileDto studentGroupProfileDto, UUID groupUuid) {
        boolean isExistGroup = studentGroupRepository.existsByUuid(groupUuid);
        if (isExistGroup) {
            StudentGroup studentGroup = studentGroupService.getByUuid(groupUuid);
            StudentGroup mappedStudentGroup = studentGroupMapper.toEntity(studentGroupProfileDto, studentGroup);
            if (!mappedStudentGroup.getStudents().isEmpty()) {
                for (Student student : mappedStudentGroup.getStudents()) {
                    student.addStudentGroup(studentGroup);
                }
            }
            StudentGroup savedGroup = studentGroupService.save(mappedStudentGroup);
            return Optional.of(studentGroupMapper.toProfileDto(savedGroup));
        }
        return Optional.empty();
    }

    @Override
    public void delete(UUID uuid) {
        studentGroupService.findByUuid(uuid).ifPresent(group -> {
            Set<Student> students = new HashSet<>(group.getStudents());
            students.forEach(group::removeStudent);
            studentGroupService.delete(uuid);
        });
    }

    @Override
    public Optional<StudentGroupProfileDto> findByUuid(UUID uuid) {
        return studentGroupService.findByUuid(uuid)
                .map(studentGroupMapper::toProfileDto);
    }

    @Override
    public Page<StudentGroupTableDto> findAll(Pageable pageable) {
        return studentGroupService.findAll(pageable)
                .map(studentGroupMapper::toTableDto);
    }

    @Override
    public List<StudentGroupTableDto> findAll() {
        return studentGroupService.findAll().stream()
                .map(studentGroupMapper::toTableDto)
                .toList();
    }
}
