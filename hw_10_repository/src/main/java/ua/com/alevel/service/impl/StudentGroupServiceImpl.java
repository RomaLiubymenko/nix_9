package ua.com.alevel.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.StudentGroup;
import ua.com.alevel.persistence.repository.StudentGroupRepository;
import ua.com.alevel.service.StudentGroupService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class StudentGroupServiceImpl implements StudentGroupService {

    private final StudentGroupRepository studentGroupRepository;

    public StudentGroupServiceImpl(StudentGroupRepository studentGroupRepository) {
        this.studentGroupRepository = studentGroupRepository;
    }

    @Override
    public StudentGroup save(StudentGroup group) {
        return studentGroupRepository.save(group);
    }

    @Override
    public void delete(UUID uuid) {
        studentGroupRepository.deleteByUuid(uuid);
    }

    @Override
    public Optional<StudentGroup> findByUuid(UUID uuid) {
        return studentGroupRepository.findByUuid(uuid);
    }

    @Override
    public StudentGroup getByUuid(UUID uuid) {
        return studentGroupRepository.getByUuid(uuid);
    }

    @Override
    public Page<StudentGroup> findAll(Pageable pageable) {
        return studentGroupRepository.findAll(pageable);
    }

    @Override
    public List<StudentGroup> findAll() {
        return studentGroupRepository.findAll();
    }
}
