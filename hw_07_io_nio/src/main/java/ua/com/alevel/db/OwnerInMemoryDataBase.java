package ua.com.alevel.db;

import ua.com.alevel.entity.Owner;
import ua.alevel.commons.util.BaseEntityParser;
import ua.alevel.commons.util.CSVHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class OwnerInMemoryDataBase {

    List<Owner> owners;
    String fileName = "owners.csv";
    private static OwnerInMemoryDataBase instance;

    private OwnerInMemoryDataBase() {
        owners = new ArrayList<>();
    }

    public static OwnerInMemoryDataBase getInstance() {
        if (instance == null) {
            instance = new OwnerInMemoryDataBase();
        }
        return instance;
    }

    public List<Owner> findAll() {
        List<List<String>> entityStrings = CSVHandler.readFromCSV(fileName);
        owners = BaseEntityParser.convertToEntities(entityStrings, Owner.class);
        return new ArrayList<>(owners);
    }

    public void create(Owner owner) {
        List<String> ownerString = BaseEntityParser.convertToString(owner);
        if (CSVHandler.isEmpty(fileName)) {
            CSVHandler.writeToCSV(List.of(BaseEntityParser.getFieldNames(Owner.class), ownerString), fileName);
        } else {
            CSVHandler.writeToCSV(List.of(ownerString), fileName);
        }
    }

    public Owner findByUuid(UUID uuid) {
        return owners.stream()
                .filter(Objects::nonNull)
                .filter(owner -> owner.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow();
    }

    public void update(Owner owner) {
        Owner result = findByUuid(owner.getUuid());
        result.setUuid(owner.getUuid());
        result.setAddress(owner.getAddress());
        result.setFullName(owner.getFullName());
        result.setPhoneNumber(owner.getPhoneNumber());
        overwriteCSV();
    }

    public void delete(UUID uuid) {
        Owner owner = findByUuid(uuid);
        owners.remove(owner);
        overwriteCSV();
    }

    private void overwriteCSV() {
        CSVHandler.clearCSV(fileName);
        CSVHandler.writeToCSV(List.of(BaseEntityParser.getFieldNames(Owner.class)), fileName);
        for (Owner entity : owners) {
            List<String> ownerString = BaseEntityParser.convertToString(entity);
            CSVHandler.writeToCSV(List.of(ownerString), fileName);
        }
    }
}
