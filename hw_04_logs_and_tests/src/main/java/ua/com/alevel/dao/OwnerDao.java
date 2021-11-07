package ua.com.alevel.dao;

import ua.com.alevel.entity.Owner;

import java.util.UUID;

public interface OwnerDao {

    void create(Owner entity);

    void update(Owner entity);

    void delete(UUID uuid);

    Owner findByUuid(UUID uuid);

    Owner[] findAll();
}
