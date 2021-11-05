package service;

import entity.Owner;

import java.util.UUID;

public interface OwnerService {

    void create(Owner entity);

    void update(Owner entity);

    void delete(UUID uuid);

    Owner findByUuid(UUID uuid);

    Owner[] findAll();
}
