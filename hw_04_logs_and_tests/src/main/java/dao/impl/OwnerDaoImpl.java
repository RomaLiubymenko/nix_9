package dao.impl;

import dao.OwnerDao;
import db.OwnerInMemoryDataBase;
import entity.Owner;

import java.util.UUID;

public class OwnerDaoImpl implements OwnerDao {

    private final OwnerInMemoryDataBase ownerDataBase = OwnerInMemoryDataBase.getInstance();

    @Override
    public void create(Owner entity) {
        ownerDataBase.create(entity);
    }

    @Override
    public void update(Owner entity) {
        ownerDataBase.update(entity);
    }

    @Override
    public void delete(UUID uuid) {
        ownerDataBase.delete(uuid);
    }

    @Override
    public Owner findByUuid(UUID uuid) {
        return ownerDataBase.findByUuid(uuid);
    }

    @Override
    public Owner[] findAll() {
        return ownerDataBase.findAll();
    }
}
