package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.CarDao;
import ua.com.alevel.dao.impl.CarDaoImpl;
import ua.com.alevel.entity.Car;
import ua.com.alevel.service.CarService;

import java.util.UUID;

public class CarServiceImpl implements CarService {

    private final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);
    private final CarDao carDao = new CarDaoImpl();

    @Override
    public void create(Car entity) {
        log.info("Creating entity with uuid {}", entity.getUuid());
        carDao.create(entity);
    }

    @Override
    public void update(Car entity) {
        log.info("Updating entity with uuid {}", entity.getUuid());
        carDao.update(entity);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("Deleting entity by uuid {}", uuid);
        carDao.delete(uuid);
    }

    @Override
    public Car findByUuid(UUID uuid) {
        log.info("Find by uuid: {} the entity", uuid);
        return carDao.findByUuid(uuid);
    }

    @Override
    public Car[] findAll() {
        log.info("Find all entities");
        return carDao.findAll();
    }
}
