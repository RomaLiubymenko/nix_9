package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.CarDao;
import ua.com.alevel.db.CarInMemoryDataBase;
import ua.com.alevel.entity.Car;

import java.util.List;
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
    public List<Car> findAll() {
        return carDataBase.findAll();
    }
}
