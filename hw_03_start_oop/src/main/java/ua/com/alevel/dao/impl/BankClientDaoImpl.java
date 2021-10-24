package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.BankClientDao;
import ua.com.alevel.db.InMemoryDataBase;
import ua.com.alevel.entity.BankClient;

import java.util.UUID;

public class BankClientDaoImpl implements BankClientDao<BankClient> {

    private final InMemoryDataBase instanceDataBase = InMemoryDataBase.getInstance();

    @Override
    public void create(BankClient client) {
        instanceDataBase.create(client);
    }

    @Override
    public void update(BankClient client) {
        instanceDataBase.update(client);
    }

    @Override
    public void delete(UUID uuid) {
        instanceDataBase.delete(uuid);
    }

    @Override
    public BankClient findByUuid(UUID uuid) {
        return instanceDataBase.findByUuid(uuid);
    }

    @Override
    public BankClient[] findAll() {
        return instanceDataBase.findAll();
    }
}
