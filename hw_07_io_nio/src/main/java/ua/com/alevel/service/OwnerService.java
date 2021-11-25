package ua.com.alevel.service;

import ua.com.alevel.entity.Owner;

import java.util.List;
import java.util.UUID;

public interface OwnerService {

    void create(Owner entity);

    void update(Owner entity);

    void delete(UUID uuid);

    Owner findByUuid(UUID uuid);

    List<Owner> findAll();
}
