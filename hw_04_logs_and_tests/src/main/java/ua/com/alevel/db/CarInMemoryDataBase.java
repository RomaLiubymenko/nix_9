package ua.com.alevel.db;

import ua.com.alevel.entity.Car;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public final class CarInMemoryDataBase {

    private Car[] cars;
    private int size;
    private final static int DEFAULT_SIZE = 10;
    private int arraySize;
    private static CarInMemoryDataBase instance;

    private CarInMemoryDataBase() {
        cars = new Car[DEFAULT_SIZE];
        this.arraySize = DEFAULT_SIZE;
    }

    public static CarInMemoryDataBase getInstance() {
        if (instance == null) {
            instance = new CarInMemoryDataBase();
        }
        return instance;
    }

    public void create(Car car) {
        resize();
        cars[size] = car;
        size++;
    }

    public Car findByUuid(UUID uuid) {
        return Arrays.stream(cars)
                .filter(Objects::nonNull)
                .filter(car -> car.getUuid() == uuid)
                .findFirst()
                .orElseThrow();
    }

    public Car[] findAll() {
        return Arrays.stream(cars)
                .filter(Objects::nonNull)
                .toArray(Car[]::new);
    }

    public void update(Car car) {
        Car result = findByUuid(car.getUuid());
        result.setUuid(car.getUuid());
        result.setBrand(car.getBrand());
        result.setModel(car.getModel());
        result.setYear(car.getYear());
        result.setOwnerUuid(car.getOwnerUuid());
    }

    public void delete(UUID uuid) {
        Car car = findByUuid(uuid);
        int index = indexOf(car);
        if (index >= 0) {
            System.arraycopy(cars, index + 1, cars, index, size - index - 1);
            size--;
            cars[size] = null;
        }
    }

    public int indexOf(Car car) {
        for (int i = 0; i < size; i++) {
            if (cars[i].equals(car)) {
                return i;
            }
        }
        return -1;
    }

    private void resize() {
        if (size >= arraySize) {
            Car[] newValues = new Car[size * 3 / 2 + 1];
            System.arraycopy(cars, 0, newValues, 0, size);
            cars = newValues;
        }
        if (size >= DEFAULT_SIZE && size < arraySize / 4) {
            Car[] newValues = new Car[size * 3 / 2 + 1];
            System.arraycopy(cars, 0, newValues, 0, size);
            cars = newValues;
        }
    }
}
