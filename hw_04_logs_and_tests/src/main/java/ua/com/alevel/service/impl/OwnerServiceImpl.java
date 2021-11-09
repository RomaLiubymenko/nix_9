package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.OwnerDao;
import ua.com.alevel.dao.impl.OwnerDaoImpl;
import ua.com.alevel.entity.Owner;
import ua.com.alevel.service.OwnerService;

import java.util.UUID;

public class OwnerServiceImpl implements OwnerService {

    private final Logger log = LoggerFactory.getLogger(OwnerServiceImpl.class);
    private final OwnerDao ownerDao = new OwnerDaoImpl();

    @Override
    public void create(Owner entity) {
        log.info("Creating entity with uuid {}", entity.getUuid());
        ownerDao.create(entity);
    }

    @Override
    public void update(Owner entity) {
        log.info("Updating entity with uuid {}", entity.getUuid());
        ownerDao.update(entity);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("Deleting entity by uuid {}", uuid);
        ownerDao.delete(uuid);
    }

    @Override
    public Owner findByUuid(UUID uuid) {
        log.info("Find by uuid: {} the entity", uuid);
        return ownerDao.findByUuid(uuid);
    }

    @Override
    public Owner[] findAll() {
        log.info("Find all entities");
        return ownerDao.findAll();
    }
}
