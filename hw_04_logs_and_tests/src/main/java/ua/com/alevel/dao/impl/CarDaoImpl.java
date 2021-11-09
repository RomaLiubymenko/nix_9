package ua.com.alevel.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.CarDao;
import ua.com.alevel.db.CarInMemoryDataBase;
import ua.com.alevel.entity.Car;

import java.util.UUID;

public class CarDaoImpl implements CarDao {

    private final Logger log = LoggerFactory.getLogger(CarDaoImpl.class);
    private final CarInMemoryDataBase carDataBase = CarInMemoryDataBase.getInstance();

    @Override
    public void create(Car entity) {
        log.info("Creating entity with uuid {}", entity.getUuid());
        carDataBase.create(entity);
    }

    @Override
    public void update(Car entity) {
        log.info("Updating entity with uuid {}", entity.getUuid());
        carDataBase.update(entity);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("Deleting entity by uuid {}", uuid);
        carDataBase.delete(uuid);
    }

    @Override
    public Car findByUuid(UUID uuid) {
        log.info("Find by uuid: {} the entity", uuid);
        return carDataBase.findByUuid(uuid);
    }

    @Override
    public Car[] findAll() {
        log.info("Find all entities");
        return carDataBase.findAll();
    }
}
