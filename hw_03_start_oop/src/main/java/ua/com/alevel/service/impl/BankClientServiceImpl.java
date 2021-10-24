package ua.com.alevel.service.impl;

import ua.com.alevel.dao.BankClientDao;
import ua.com.alevel.dao.impl.BankClientDaoImpl;
import ua.com.alevel.entity.BankClient;
import ua.com.alevel.service.BankClientService;

import java.util.UUID;

public class BankClientServiceImpl implements BankClientService<BankClient> {

    BankClientDao<BankClient> bankClientDao = new BankClientDaoImpl();

    @Override
    public void create(BankClient client) {
        bankClientDao.create(client);
    }

    @Override
    public void update(BankClient client) {
        bankClientDao.update(client);
    }

    @Override
    public void delete(UUID uuid) {
        bankClientDao.delete(uuid);
    }

    @Override
    public BankClient findByUuid(UUID uuid) {
        return bankClientDao.findByUuid(uuid);
    }

    @Override
    public BankClient[] findAll() {
        return bankClientDao.findAll();
    }
}
