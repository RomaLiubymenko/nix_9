package ua.com.alevel.service.impl;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Owner;
import ua.com.alevel.service.OwnerService;

import java.util.Arrays;
import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OwnerServiceImplTest {

    private static final OwnerService ownerService = new OwnerServiceImpl();
    public static final int OWNER_SIZE = 15;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < OWNER_SIZE; i++) {
            Owner owner = new Owner();
            owner.setUuid(UUID.randomUUID());
            owner.setAddress("Some address");
            owner.setFullName("Full name");
            owner.setPhoneNumber("+3806833214");
            ownerService.create(owner);
        }
    }

    @AfterAll
    public static void tearDown() {
        Owner[] owners = ownerService.findAll();
        for (Owner owner : owners) {
            ownerService.delete(owner.getUuid());
        }
    }

    @Test
    @Order(1)
    void shouldFindAllOwners() {
        Owner[] owners = ownerService.findAll();
        Assertions.assertEquals(OWNER_SIZE, owners.length);
    }

    @Test
    @Order(2)
    void shouldFindOwnerByUuid() {
        UUID ownerUuid = getRandomOwnerUUID();
        Owner owner = ownerService.findByUuid(ownerUuid);
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
        ownerService.create(owner);
        Owner[] owners = ownerService.findAll();
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
        ownerService.create(owner);
        Owner[] owners = ownerService.findAll();
        Assertions.assertEquals(OWNER_SIZE + 2, owners.length);
    }

    @Test
    @Order(5)
    void shouldDeleteCarByUuid() {
        UUID carUuid = getRandomOwnerUUID();
        ownerService.delete(carUuid);
        Owner[] owners = ownerService.findAll();
        Assertions.assertEquals(OWNER_SIZE + 1, owners.length);
    }

    @Test
    @Order(6)
    void shouldUpdateCar() {
        UUID carUuid = getRandomOwnerUUID();
        Owner owner = ownerService.findByUuid(carUuid);
        owner.setUuid(UUID.randomUUID());
        owner.setAddress("Address");
        owner.setFullName("Fulllll");
        owner.setPhoneNumber("+3806855555");
        ownerService.update(owner);
        Owner resultOwner = ownerService.findByUuid(owner.getUuid());
        Assertions.assertEquals(owner, resultOwner);
    }

    private UUID getRandomOwnerUUID() {
        return Arrays.stream(ownerService.findAll())
                .findFirst()
                .get()
                .getUuid();
    }
}
