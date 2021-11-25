package ua.com.alevel.service.impl;

import ua.com.alevel.dao.CarDao;
import ua.com.alevel.dao.impl.CarDaoImpl;
import ua.com.alevel.entity.Car;
import ua.com.alevel.service.CarService;

import java.util.List;
import java.util.UUID;

public class CarServiceImpl implements CarService {

    private final CarDao carDao = new CarDaoImpl();

    @Override
    public void create(Car entity) {
        carDao.create(entity);
    }

    @Override
    public void update(Car entity) {
        carDao.update(entity);
    }

    @Override
    public void delete(UUID uuid) {
        carDao.delete(uuid);
    }

    @Override
    public Car findByUuid(UUID uuid) {
        return carDao.findByUuid(uuid);
    }

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }
}
