package ua.com.alevel.service.impl;

import ua.com.alevel.dao.OwnerDao;
import ua.com.alevel.dao.impl.OwnerDaoImpl;
import ua.com.alevel.entity.Owner;
import ua.com.alevel.service.OwnerService;

import java.util.List;
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
    public List<Owner> findAll() {
        return ownerDao.findAll();
    }
}
