package ua.com.alevel.dao.impl;

import org.junit.jupiter.api.*;
import ua.com.alevel.dao.OwnerDao;
import ua.com.alevel.entity.Owner;

import java.util.Arrays;
import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OwnerDaoImplTest {

    private static final OwnerDao ownerDao = new OwnerDaoImpl();
    public static final int OWNER_SIZE = 15;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < OWNER_SIZE; i++) {
            Owner owner = new Owner();
            owner.setUuid(UUID.randomUUID());
            owner.setAddress("Some address");
            owner.setFullName("Full name");
            owner.setPhoneNumber("+3806833214");
            ownerDao.create(owner);
        }
    }

    @AfterAll
    public static void tearDown() {
        Owner[] owners = ownerDao.findAll();
        for (Owner owner : owners) {
            ownerDao.delete(owner.getUuid());
        }
    }

    @Test
    @Order(1)
    void shouldFindAllOwners() {
        Owner[] owners = ownerDao.findAll();
        Assertions.assertEquals(OWNER_SIZE, owners.length);
    }

    @Test
    @Order(2)
    void shouldFindOwnerByUuid() {
        UUID ownerUuid = getRandomOwnerUUID();
        Owner owner = ownerDao.findByUuid(ownerUuid);
        Assertions.assertNotNull(owner);
        Assertions.assertEquals(ownerUuid, owner.getUuid());
    }

    @Test
    @Order(3)
    void shouldCreateOwner() {
        Owner owner = new Owner();
        owner.setUuid(UUID.randomUUID());
        owner.setAddress("Some address");
        owner.setFullName("Full name");
        owner.setPhoneNumber("+3806833214");
        ownerDao.create(owner);
        Owner[] owners = ownerDao.findAll();
        Assertions.assertEquals(OWNER_SIZE + 1, owners.length);
    }

    @Test
    @Order(4)
    void shouldCreateOwnerWithNullFields() {
        Owner owner = new Owner();
        owner.setUuid(null);
        owner.setAddress(null);
        owner.setFullName(null);
        owner.setPhoneNumber(null);
        ownerDao.create(owner);
        Owner[] owners = ownerDao.findAll();
        Assertions.assertEquals(OWNER_SIZE + 2, owners.length);
    }

    @Test
    @Order(5)
    void shouldDeleteCarByUuid() {
        UUID carUuid = getRandomOwnerUUID();
        ownerDao.delete(carUuid);
        Owner[] owners = ownerDao.findAll();
        Assertions.assertEquals(OWNER_SIZE + 1, owners.length);
    }

    @Test
    @Order(6)
    void shouldUpdateCar() {
        UUID carUuid = getRandomOwnerUUID();
        Owner owner = ownerDao.findByUuid(carUuid);
        owner.setUuid(UUID.randomUUID());
        owner.setAddress("Address");
        owner.setFullName("Fulllll");
        owner.setPhoneNumber("+3806855555");
        ownerDao.update(owner);
        Owner resultOwner = ownerDao.findByUuid(owner.getUuid());
        Assertions.assertEquals(owner, resultOwner);
    }

    private UUID getRandomOwnerUUID() {
        return Arrays.stream(ownerDao.findAll())
                .findFirst()
                .get()
                .getUuid();
    }
}
