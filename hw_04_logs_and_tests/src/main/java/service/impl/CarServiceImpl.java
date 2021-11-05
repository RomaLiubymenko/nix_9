package service.impl;

import dao.CarDao;
import dao.impl.CarDaoImpl;
import entity.Car;
import service.CarService;

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
    public Car[] findAll() {
        return carDao.findAll();
    }
}
