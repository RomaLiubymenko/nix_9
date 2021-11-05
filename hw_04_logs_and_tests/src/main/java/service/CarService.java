package service;

import entity.Car;

import java.util.UUID;

public interface CarService {

    void create(Car entity);

    void update(Car entity);

    void delete(UUID uuid);

    Car findByUuid(UUID uuid);

    Car[] findAll();
}
