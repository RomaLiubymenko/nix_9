package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.AbstractEntity;

import java.util.List;
import java.util.UUID;

public interface AbstractService<ENTITY extends AbstractEntity> {

    void create(ENTITY entity);

    void update(ENTITY entity);

    void delete(UUID uuid);

    ENTITY findByUuid(UUID uuid);

    DataTableResponse<ENTITY> findAll(DataTableRequest request);

    List<ENTITY> findAll();
}
