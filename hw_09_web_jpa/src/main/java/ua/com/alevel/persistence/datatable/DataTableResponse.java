package ua.com.alevel.persistence.datatable;

import ua.com.alevel.persistence.entity.AbstractEntity;

import java.util.Collections;
import java.util.List;

public class DataTableResponse<ENTITY extends AbstractEntity> {

    private List<ENTITY> entities;
    private int pageNumber;
    private long totalElements;
    private int totalPages;
    private int pageSize;

    public DataTableResponse() {
        entities = Collections.emptyList();
        pageNumber = 1;
        totalPages = 1;
    }

    public List<ENTITY> getEntities() {
        return entities;
    }

    public void setEntities(List<ENTITY> entities) {
        this.entities = entities;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
