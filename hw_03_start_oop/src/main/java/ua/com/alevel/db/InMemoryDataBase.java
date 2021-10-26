package ua.com.alevel.db;

import ua.com.alevel.entity.BankClient;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public final class InMemoryDataBase {

    private BankClient[] clients;
    private int size;
    private final static int DEFAULT_SIZE = 10;
    private int arraySize;
    private static InMemoryDataBase instance;

    private InMemoryDataBase() {
        clients = new BankClient[DEFAULT_SIZE];
        this.arraySize = DEFAULT_SIZE;
    }

    public static InMemoryDataBase getInstance() {
        if (instance == null) {
            instance = new InMemoryDataBase();
        }
        return instance;
    }

    public void create(BankClient client) {
        resize();
        clients[size] = client;
        size++;
    }

    public BankClient findByUuid(UUID uuid) {
        return Arrays.stream(clients)
                .filter(Objects::nonNull)
                .filter(client -> client.getUuid() == uuid)
                .findFirst()
                .orElseThrow();
    }

    public BankClient[] findAll() {
        return  Arrays.stream(clients)
                .filter(Objects::nonNull)
                .toArray(BankClient[]::new);
    }

    public void update(BankClient client) {
        BankClient result = findByUuid(client.getUuid());
        result.setUuid(client.getUuid());
        result.setAddress(client.getAddress());
        result.setFullName(client.getFullName());
        result.setClientType(client.getClientType());
    }

    public void delete(UUID uuid) {
        BankClient client = findByUuid(uuid);
        int index = indexOf(client);
        if (index >= 0) {
            System.arraycopy(clients, index + 1, clients, index, size - index - 1);
            size--;
            clients[size] = null;
        }
    }

    public int indexOf(BankClient client) {
        for (int i = 0; i < size; i++) {
            if (clients[i].equals(client)) {
                return i;
            }
        }
        return -1;
    }

    private void resize() {
        if (size >= arraySize) {
            BankClient[] newValues = new BankClient[size * 3 / 2 + 1];
            System.arraycopy(clients, 0, newValues, 0, size);
            clients = newValues;
        }
        if (size >= DEFAULT_SIZE && size < arraySize / 4) {
            BankClient[] newValues = new BankClient[size * 3 / 2 + 1];
            System.arraycopy(clients, 0, newValues, 0, size);
            clients = newValues;
        }
    }
}
