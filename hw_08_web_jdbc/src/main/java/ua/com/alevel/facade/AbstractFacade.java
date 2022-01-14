package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.request.RequestDto;
import ua.com.alevel.dto.response.ResponseDto;
import ua.com.alevel.dto.response.PageData;

import java.util.List;
import java.util.UUID;

public interface AbstractFacade<REQUEST extends RequestDto, RESPONSE extends ResponseDto> {

    void create(REQUEST req);

    void update(REQUEST req, UUID uuid);

    void delete(UUID uuid);

    RESPONSE findByUuid(UUID uuid);

    PageData<RESPONSE> findAll(WebRequest request);

    List<RESPONSE> findAll();
}

