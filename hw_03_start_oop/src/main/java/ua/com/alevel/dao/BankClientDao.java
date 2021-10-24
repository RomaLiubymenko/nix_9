package ua.com.alevel.dao;

import java.util.UUID;

public interface BankClientDao<ENTITY> {
    void create(ENTITY entity);

    void update(ENTITY entity);

    void delete(UUID uuid);

    ENTITY findByUuid(UUID uuid);

    ENTITY[] findAll();
}
