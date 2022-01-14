package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.StudentService;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void create(Student student) {
        studentDao.create(student);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void delete(UUID uuid) {
        studentDao.delete(uuid);
    }

    @Override
    public Student findByUuid(UUID uuid) {
        return studentDao.findByUuid(uuid);
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        DataTableResponse<Student> responseDataTable = studentDao.findAll(request);
        long count = studentDao.count();
        long totalPageSize = 0;
        if (count % request.getPageSize() == 0) {
            totalPageSize = count / request.getPageSize();
        } else {
            totalPageSize = count / request.getPageSize() + 1;
        }
        responseDataTable.setTotalElements(count);
        responseDataTable.setPageNumber(request.getCurrentPage());
        responseDataTable.setTotalPages((int) totalPageSize);
        responseDataTable.setPageSize(request.getPageSize());
        return responseDataTable;
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }
}
