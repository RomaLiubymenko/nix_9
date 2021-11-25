package ua.com.alevel.db;

import ua.com.alevel.entity.Car;
import ua.alevel.commons.util.BaseEntityParser;
import ua.alevel.commons.util.CSVHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class CarInMemoryDataBase {

    List<Car> cars;
    String fileName = "cars.csv";
    private static CarInMemoryDataBase instance;

    private CarInMemoryDataBase() {
        cars = new ArrayList<>();
    }

    public static CarInMemoryDataBase getInstance() {
        if (instance == null) {
            instance = new CarInMemoryDataBase();
        }
        return instance;
    }

    public List<Car> findAll() {
        List<List<String>> entityStrings = CSVHandler.readFromCSV(fileName);
        cars = BaseEntityParser.convertToEntities(entityStrings, Car.class);
        return new ArrayList<>(cars);
    }

    public void create(Car car) {
        List<String> carString = BaseEntityParser.convertToString(car);
        if (CSVHandler.isEmpty(fileName)) {
            CSVHandler.writeToCSV(List.of(BaseEntityParser.getFieldNames(Car.class), carString), fileName);
        } else {
            CSVHandler.writeToCSV(List.of(carString), fileName);
        }
    }

    public Car findByUuid(UUID uuid) {
        return cars.stream()
                .filter(Objects::nonNull)
                .filter(car -> car.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow();
    }

    public void update(Car car) {
        Car result = findByUuid(car.getUuid());
        result.setUuid(car.getUuid());
        result.setBrand(car.getBrand());
        result.setModel(car.getModel());
        result.setYear(car.getYear());
        result.setOwnerUuid(car.getOwnerUuid());
        overwriteCSV();
    }

    public void delete(UUID uuid) {
        Car car = findByUuid(uuid);
        cars.remove(car);
        overwriteCSV();
    }

    private void overwriteCSV() {
        CSVHandler.clearCSV(fileName);
        CSVHandler.writeToCSV(List.of(BaseEntityParser.getFieldNames(Car.class)), fileName);
        for (Car entity : cars) {
            List<String> carString = BaseEntityParser.convertToString(entity);
            CSVHandler.writeToCSV(List.of(carString), fileName);
        }
    }
}
