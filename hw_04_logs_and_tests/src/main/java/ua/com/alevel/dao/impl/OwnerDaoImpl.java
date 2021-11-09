package ua.com.alevel.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.CarDao;
import ua.com.alevel.dao.OwnerDao;
import ua.com.alevel.db.OwnerInMemoryDataBase;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Owner;

import java.util.Arrays;
import java.util.UUID;

public class OwnerDaoImpl implements OwnerDao {

    private final Logger log = LoggerFactory.getLogger(OwnerDaoImpl.class);
    private final OwnerInMemoryDataBase ownerDataBase = OwnerInMemoryDataBase.getInstance();
    private final CarDao carDao = new CarDaoImpl();

    @Override
    public void create(Owner entity) {
        log.info("Creating entity with uuid {}", entity.getUuid());
        ownerDataBase.create(entity);
    }

    @Override
    public void update(Owner entity) {
        log.info("Updating entity with uuid {}", entity.getUuid());
        ownerDataBase.update(entity);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("Deleting entity by uuid {}", uuid);
        Car[] cars = carDao.findAll();
        Arrays.stream(cars)
                .filter(car -> car.getOwnerUuid() == uuid)
                .forEach(car -> carDao.delete(car.getUuid()));
        ownerDataBase.delete(uuid);
    }

    @Override
    public Owner findByUuid(UUID uuid) {
        log.info("Find by uuid: {} the entity", uuid);
        return ownerDataBase.findByUuid(uuid);
    }

    @Override
    public Owner[] findAll() {
        log.info("Find all entities");
        return ownerDataBase.findAll();
    }
}
