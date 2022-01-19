package ua.com.alevel.dto.table;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class AbstractTableDto {

    private UUID uuid;
    private LocalDateTime created;
    private LocalDateTime updated;

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

    @Override
    public String toString() {
        return "AbstractTableDto{" +
                "uuid=" + uuid +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
