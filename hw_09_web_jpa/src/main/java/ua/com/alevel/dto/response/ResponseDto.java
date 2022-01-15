package ua.com.alevel.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class ResponseDto {

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
}
