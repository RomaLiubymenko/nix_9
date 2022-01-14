package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.request.PageAndSizeData;
import ua.com.alevel.dto.request.SortData;
import ua.com.alevel.dto.request.StudentGroupRequestDto;
import ua.com.alevel.dto.response.PageData;
import ua.com.alevel.dto.response.StudentGroupResponseDto;
import ua.com.alevel.facade.StudentGroupFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.StudentGroup;
import ua.com.alevel.service.StudentGroupService;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentGroupFacadeImpl implements StudentGroupFacade {

    private final StudentService studentService;
    private final StudentGroupService studentGroupService;

    public StudentGroupFacadeImpl(StudentService studentService, StudentGroupService studentGroupService) {
        this.studentService = studentService;
        this.studentGroupService = studentGroupService;
    }

    @Override
    public void create(StudentGroupRequestDto studentGroupRequestDto) {
        StudentGroup studentGroup = new StudentGroup();
        studentGroup.setName(studentGroupRequestDto.getName());
        studentGroup.setDescription(studentGroupRequestDto.getDescription());
        studentGroup.setGroupType(studentGroupRequestDto.getGroupType());
        if (!studentGroupRequestDto.getStudentUuids().isEmpty()) {
            Set<Student> students = studentGroupRequestDto.getStudentUuids().stream()
                    .map(studentService::findByUuid)
                    .collect(Collectors.toSet());
            studentGroup.setStudents(students);
        }
        studentGroupService.create(studentGroup);
    }

    @Override
    public void update(StudentGroupRequestDto studentGroupRequestDto, UUID uuid) {
        StudentGroup studentGroup = studentGroupService.findByUuid(uuid);
        studentGroup.setUpdated(LocalDateTime.now());
        studentGroup.setName(studentGroupRequestDto.getName());
        studentGroup.setDescription(studentGroupRequestDto.getDescription());
        studentGroup.setGroupType(studentGroupRequestDto.getGroupType());
        if (!studentGroupRequestDto.getStudentUuids().isEmpty()) {
            Set<Student> students = studentGroupRequestDto.getStudentUuids().stream()
                    .map(studentService::findByUuid)
                    .collect(Collectors.toSet());
            studentGroup.setStudents(students);
        }
        studentGroupService.update(studentGroup);
    }

    @Override
    public void delete(UUID uuid) {
        studentGroupService.delete(uuid);
    }

    @Override
    public StudentGroupResponseDto findByUuid(UUID uuid) {
        return new StudentGroupResponseDto(studentGroupService.findByUuid(uuid));
    }

    @Override
    public PageData<StudentGroupResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeDataRequestDto = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortDataRequestDto = WebRequestUtil.generateSortData(request);
        DataTableRequest requestDataTable = new DataTableRequest();
        requestDataTable.setOrder(sortDataRequestDto.getOrder());
        requestDataTable.setSort(sortDataRequestDto.getSort());
        requestDataTable.setCurrentPage(pageAndSizeDataRequestDto.getPage());
        requestDataTable.setPageSize(pageAndSizeDataRequestDto.getSize());
        DataTableResponse<StudentGroup> all = studentGroupService.findAll(requestDataTable);
        List<StudentGroupResponseDto> groups = all.getEntities().stream()
                .map(StudentGroupResponseDto::new)
                .toList();
        PageData<StudentGroupResponseDto> pageData = WebResponseUtil.initPageData(all);
        pageData.setItems(groups);
        return pageData;
    }

    @Override
    public List<StudentGroupResponseDto> findAll() {
        List<StudentGroup> groups = studentGroupService.findAll();
        return groups.stream()
                .map(StudentGroupResponseDto::new)
                .toList();
    }
}
