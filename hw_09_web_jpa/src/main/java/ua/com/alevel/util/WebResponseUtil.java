package ua.com.alevel.util;

import ua.com.alevel.dto.response.PageData;
import ua.com.alevel.dto.response.ResponseDto;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.AbstractEntity;

public class WebResponseUtil {

    private WebResponseUtil() {
    }

    public static <DTO extends ResponseDto, ENTITY extends AbstractEntity> PageData<DTO> initPageData(DataTableResponse<ENTITY> tableResponse) {
        PageData<DTO> pageData = new PageData<>();
        pageData.setPageNumber(tableResponse.getPageNumber());
        pageData.setTotalElements(tableResponse.getTotalElements());
        pageData.setTotalPages(tableResponse.getTotalPages());
        pageData.setPageSize(tableResponse.getPageSize());
        return pageData;
    }
}
