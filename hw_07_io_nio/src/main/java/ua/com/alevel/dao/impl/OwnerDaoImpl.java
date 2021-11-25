package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.CarDao;
import ua.com.alevel.dao.OwnerDao;
import ua.com.alevel.db.OwnerInMemoryDataBase;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Owner;

import java.util.List;
import java.util.UUID;

public class OwnerDaoImpl implements OwnerDao {

    private final OwnerInMemoryDataBase ownerDataBase = OwnerInMemoryDataBase.getInstance();
    private final CarDao carDao = new CarDaoImpl();

    @Override
    public void create(Owner entity) {
        ownerDataBase.create(entity);
    }

    @Override
    public void update(Owner entity) {
        ownerDataBase.update(entity);
    }

    @Override
    public void delete(UUID uuid) {
        List<Car> cars = carDao.findAll();
        cars.stream()
                .filter(car -> car.getOwnerUuid().equals(uuid))
                .forEach(car -> carDao.delete(car.getUuid()));
        ownerDataBase.delete(uuid);
    }

    @Override
    public Owner findByUuid(UUID uuid) {
        return ownerDataBase.findByUuid(uuid);
    }

    @Override
    public List<Owner> findAll() {
        return ownerDataBase.findAll();
    }
}
