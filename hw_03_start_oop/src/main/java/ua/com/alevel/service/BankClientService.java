package ua.com.alevel.service;

import java.util.UUID;

public interface BankClientService<ENTITY> {
    void create(ENTITY entity);

    void update(ENTITY entity);

    void delete(UUID uuid);

    ENTITY findByUuid(UUID uuid);

    ENTITY[] findAll();
}
