package service.impl;

import dao.OwnerDao;
import dao.impl.OwnerDaoImpl;
import entity.Owner;
import service.OwnerService;

import java.util.UUID;

public class OwnerServiceImpl implements OwnerService {

    private final OwnerDao ownerDao = new OwnerDaoImpl();

    @Override
    public void create(Owner entity) {
        ownerDao.create(entity);
    }

    @Override
    public void update(Owner entity) {
        ownerDao.update(entity);
    }

    @Override
    public void delete(UUID uuid) {
        ownerDao.delete(uuid);
    }

    @Override
    public Owner findByUuid(UUID uuid) {
        return ownerDao.findByUuid(uuid);
    }

    @Override
    public Owner[] findAll() {
        return ownerDao.findAll();
    }
}
