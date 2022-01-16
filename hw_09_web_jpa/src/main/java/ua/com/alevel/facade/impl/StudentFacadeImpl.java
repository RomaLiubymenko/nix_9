package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.request.PageAndSizeData;
import ua.com.alevel.dto.request.SortData;
import ua.com.alevel.dto.request.StudentRequestDto;
import ua.com.alevel.dto.response.PageData;
import ua.com.alevel.dto.response.StudentResponseDto;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.StudentGroup;
import ua.com.alevel.service.StudentGroupService;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;
    private final StudentGroupService studentGroupService;

    public StudentFacadeImpl(StudentService studentService, StudentGroupService studentGroupService) {
        this.studentService = studentService;
        this.studentGroupService = studentGroupService;
    }

    @Override
    public void create(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setBirthDay(studentRequestDto.getBirthDay());
        if (!studentRequestDto.getStudentGroupUuids().isEmpty()) {
            Set<StudentGroup> studentGroups = studentRequestDto.getStudentGroupUuids().stream()
                    .map(studentGroupService::findByUuid)
                    .collect(Collectors.toSet());
            for (StudentGroup studentGroup : studentGroups) {
                student.addStudentGroup(studentGroup);
            }
        }
        studentService.create(student);
    }

    @Override
    public void update(StudentRequestDto studentRequestDto, UUID uuid) {
        Student student = studentService.findByUuid(uuid);
        student.setUpdated(LocalDateTime.now());
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setBirthDay(studentRequestDto.getBirthDay());
        if (!student.getStudentGroups().isEmpty()) {
            List<StudentGroup> studentGroupListForDelete = new ArrayList<>(student.getStudentGroups());
            for (StudentGroup studentGroup : studentGroupListForDelete) {
                student.removeStudentGroup(studentGroup);
            }
        }
        if (!studentRequestDto.getStudentGroupUuids().isEmpty()) {
            Set<StudentGroup> studentGroups = studentRequestDto.getStudentGroupUuids().stream()
                    .map(studentGroupService::findByUuid)
                    .collect(Collectors.toSet());
            for (StudentGroup studentGroup : studentGroups) {
                student.addStudentGroup(studentGroup);
            }
        }
        studentService.update(student);
    }

    @Override
    public void delete(UUID uuid) {
        studentService.delete(uuid);
    }

    @Override
    public StudentResponseDto findByUuid(UUID uuid) {
        return new StudentResponseDto(studentService.findByUuid(uuid));
    }

    @Override
    public PageData<StudentResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeDataRequestDto = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortDataRequestDto = WebRequestUtil.generateSortData(request);
        DataTableRequest requestDataTable = new DataTableRequest();
        requestDataTable.setOrder(sortDataRequestDto.getOrder());
        requestDataTable.setSort(sortDataRequestDto.getSort());
        requestDataTable.setCurrentPage(pageAndSizeDataRequestDto.getPage());
        requestDataTable.setPageSize(pageAndSizeDataRequestDto.getSize());
        DataTableResponse<Student> all = studentService.findAll(requestDataTable);
        List<StudentResponseDto> students = all.getEntities().stream()
                .map(StudentResponseDto::new)
                .toList();
        PageData<StudentResponseDto> pageData = WebResponseUtil.initPageData(all);
        pageData.setItems(students);
        return pageData;
    }

    @Override
    public List<StudentResponseDto> findAll() {
        Set<Student> students = studentService.findAll();
        return students.stream()
                .map(StudentResponseDto::new)
                .toList();
    }
}
