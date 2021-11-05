package db;

import entity.Owner;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public final class OwnerInMemoryDataBase {

    private Owner[] owners;
    private int size;
    private final static int DEFAULT_SIZE = 10;
    private int arraySize;
    private static OwnerInMemoryDataBase instance;

    private OwnerInMemoryDataBase() {
        owners = new Owner[DEFAULT_SIZE];
        this.arraySize = DEFAULT_SIZE;
    }

    public static OwnerInMemoryDataBase getInstance() {
        if (instance == null) {
            instance = new OwnerInMemoryDataBase();
        }
        return instance;
    }

    public void create(Owner owner) {
        resize();
        owners[size] = owner;
        size++;
    }

    public Owner findByUuid(UUID uuid) {
        return Arrays.stream(owners)
                .filter(Objects::nonNull)
                .filter(owner -> owner.getUuid() == uuid)
                .findFirst()
                .orElseThrow();
    }

    public Owner[] findAll() {
        return Arrays.stream(owners)
                .filter(Objects::nonNull)
                .toArray(Owner[]::new);
    }

    public void update(Owner owner) {
        Owner result = findByUuid(owner.getUuid());
        result.setUuid(owner.getUuid());
        result.setAddress(owner.getAddress());
        result.setFullName(owner.getFullName());
        result.setPhoneNumber(owner.getPhoneNumber());
    }

    public void delete(UUID uuid) {
        Owner owner = findByUuid(uuid);
        int index = indexOf(owner);
        if (index >= 0) {
            System.arraycopy(owners, index + 1, owners, index, size - index - 1);
            size--;
            owners[size] = null;
        }
    }

    public int indexOf(Owner owner) {
        for (int i = 0; i < size; i++) {
            if (owners[i].equals(owner)) {
                return i;
            }
        }
        return -1;
    }

    private void resize() {
        if (size >= arraySize) {
            Owner[] newValues = new Owner[size * 3 / 2 + 1];
            System.arraycopy(owners, 0, newValues, 0, size);
            owners = newValues;
        }
        if (size >= DEFAULT_SIZE && size < arraySize / 4) {
            Owner[] newValues = new Owner[size * 3 / 2 + 1];
            System.arraycopy(owners, 0, newValues, 0, size);
            owners = newValues;
        }
    }
}
