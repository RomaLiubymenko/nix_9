package ua.com.alevel.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.alevel.dto.profile.AbstractProfileDto;
import ua.com.alevel.dto.table.AbstractTableDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AbstractFacade<PROFILE_DTO extends AbstractProfileDto, TABLE_DTO extends AbstractTableDto> {

    PROFILE_DTO create(PROFILE_DTO req);

    Optional<PROFILE_DTO> update(PROFILE_DTO req, UUID uuid);

    void delete(UUID uuid);

    Optional<PROFILE_DTO> findByUuid(UUID uuid);

    Page<TABLE_DTO> findAll(Pageable pageable);

    List<TABLE_DTO> findAll();
}

