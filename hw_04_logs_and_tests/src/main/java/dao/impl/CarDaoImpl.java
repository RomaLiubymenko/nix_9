package dao.impl;

import dao.CarDao;
import db.CarInMemoryDataBase;
import entity.Car;

import java.util.UUID;

public class CarDaoImpl implements CarDao {

    private final CarInMemoryDataBase carDataBase = CarInMemoryDataBase.getInstance();

    @Override
    public void create(Car entity) {
        carDataBase.create(entity);
    }

    @Override
    public void update(Car entity) {
        carDataBase.update(entity);
    }

    @Override
    public void delete(UUID uuid) {
        carDataBase.delete(uuid);
    }

    @Override
    public Car findByUuid(UUID uuid) {
        return carDataBase.findByUuid(uuid);
    }

    @Override
    public Car[] findAll() {
        return carDataBase.findAll();
    }
}
