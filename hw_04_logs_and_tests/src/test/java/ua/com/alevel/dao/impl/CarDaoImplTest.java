package ua.com.alevel.dao.impl;

import org.junit.jupiter.api.*;
import ua.com.alevel.dao.CarDao;
import ua.com.alevel.entity.Car;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarDaoImplTest {

    private static final CarDao carDao = new CarDaoImpl();
    public static final int CAR_SIZE = 15;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < CAR_SIZE; i++) {
            Car car = new Car();
            car.setUuid(UUID.randomUUID());
            car.setOwnerUuid(UUID.randomUUID());
            car.setBrand("Some brand");
            car.setModel("Some model");
            car.setYear(LocalDate.now());
            carDao.create(car);
        }
    }

    @AfterAll
    public static void tearDown() {
        Car[] cars = carDao.findAll();
        for (Car car : cars) {
            carDao.delete(car.getUuid());
        }
    }

    @Test
    @Order(1)
    void shouldFindAllCars() {
        Car[] cars = carDao.findAll();
        Assertions.assertEquals(CAR_SIZE, cars.length);
    }

    @Test
    @Order(2)
    void shouldFindCarByUuid() {
        UUID carUuid = getRandomCarUUID();
        Car car = carDao.findByUuid(carUuid);
        Assertions.assertNotNull(car);
        Assertions.assertEquals(carUuid, car.getUuid());
    }


    @Test
    @Order(3)
    void shouldCreateCar() {
        Car car = new Car();
        car.setUuid(UUID.randomUUID());
        car.setOwnerUuid(UUID.randomUUID());
        car.setBrand("Some brand");
        car.setModel("Some model");
        car.setYear(LocalDate.now());
        carDao.create(car);
        Car[] cars = carDao.findAll();
        Assertions.assertEquals(CAR_SIZE + 1, cars.length);
    }

    @Test
    @Order(4)
    void shouldCreateCarWithNullFields() {
        Car car = new Car();
        car.setUuid(null);
        car.setOwnerUuid(null);
        car.setBrand(null);
        car.setModel(null);
        car.setYear(null);
        carDao.create(car);
        Car[] cars = carDao.findAll();
        Assertions.assertEquals(CAR_SIZE + 2, cars.length);
    }

    @Test
    @Order(5)
    void shouldDeleteCarByUuid() {
        UUID carUuid = getRandomCarUUID();
        carDao.delete(carUuid);
        Car[] cars = carDao.findAll();
        Assertions.assertEquals(CAR_SIZE + 1, cars.length);
    }

    @Test
    @Order(6)
    void shouldUpdateCar() {
        UUID carUuid = getRandomCarUUID();
        Car car = carDao.findByUuid(carUuid);
        car.setUuid(UUID.randomUUID());
        car.setOwnerUuid(UUID.randomUUID());
        car.setYear(LocalDate.now());
        car.setModel("Model");
        car.setBrand("Brand");
        carDao.update(car);
        Car resultCar = carDao.findByUuid(car.getUuid());
        Assertions.assertEquals(car, resultCar);
    }

    private UUID getRandomCarUUID() {
        return Arrays.stream(carDao.findAll())
                .findFirst()
                .get()
                .getUuid();
    }
}
