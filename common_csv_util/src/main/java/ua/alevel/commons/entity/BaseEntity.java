package ua.alevel.commons.entity;

import java.util.UUID;

public abstract class BaseEntity {

    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
