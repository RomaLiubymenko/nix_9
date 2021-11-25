package ua.com.alevel.dao;

import ua.com.alevel.entity.Car;

import java.util.List;
import java.util.UUID;

public interface CarDao {

    void create(Car entity);

    void update(Car entity);

    void delete(UUID uuid);

    Car findByUuid(UUID uuid);

    List<Car> findAll();
}
