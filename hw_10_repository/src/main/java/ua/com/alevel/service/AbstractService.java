package ua.com.alevel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.alevel.persistence.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AbstractService<ENTITY extends AbstractEntity> {

    ENTITY save(ENTITY entity);

    void delete(UUID uuid);

    Optional<ENTITY> findByUuid(UUID uuid);

    ENTITY getByUuid(UUID uuid);

    Page<ENTITY> findAll(Pageable pageable);

    List<ENTITY> findAll();
}
