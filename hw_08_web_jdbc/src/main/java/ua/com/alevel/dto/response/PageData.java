package ua.com.alevel.dto.response;

import java.util.ArrayList;
import java.util.List;

public class PageData<REQ extends ResponseDto> {

    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private List<REQ> items;
    private long totalElements;

    public PageData() {
        this.pageNumber = 0;
        this.pageSize = 10;
        this.totalPages = 0;
        this.totalElements = 0;
        this.items = new ArrayList<>();
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<REQ> getItems() {
        return items;
    }

    public void setItems(List<REQ> items) {
        this.items = items;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
