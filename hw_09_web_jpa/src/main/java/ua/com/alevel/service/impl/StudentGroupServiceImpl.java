package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.StudentGroupDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.StudentGroup;
import ua.com.alevel.service.StudentGroupService;

import java.util.List;
import java.util.UUID;


@Service
public class StudentGroupServiceImpl implements StudentGroupService {

    private final StudentGroupDao studentGroupDao;

    public StudentGroupServiceImpl(StudentGroupDao studentGroupDao) {
        this.studentGroupDao = studentGroupDao;
    }

    @Override
    public void create(StudentGroup group) {
        studentGroupDao.create(group);
    }

    @Override
    public void update(StudentGroup group) {
        studentGroupDao.update(group);
    }

    @Override
    public void delete(UUID uuid) {
        studentGroupDao.delete(uuid);
    }

    @Override
    public StudentGroup findByUuid(UUID uuid) {
        return studentGroupDao.findByUuid(uuid);
    }

    @Override
    public DataTableResponse<StudentGroup> findAll(DataTableRequest request) {
        DataTableResponse<StudentGroup> responseDataTable = studentGroupDao.findAll(request);
        long count = studentGroupDao.count();
        long totalPageSize;
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
    public List<StudentGroup> findAll() {
        return studentGroupDao.findAll();
    }
}
