package ua.com.alevel.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class AbstractEntity {

    private Long id;
    private UUID uuid;
    private LocalDateTime created;
    private LocalDateTime updated;

    protected AbstractEntity() {
        uuid = UUID.randomUUID();
        created = LocalDateTime.now();
        updated = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
