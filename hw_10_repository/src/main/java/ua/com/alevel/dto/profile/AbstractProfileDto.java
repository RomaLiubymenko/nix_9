package ua.com.alevel.dto.profile;

import org.springframework.lang.Nullable;

import java.util.UUID;

public abstract class AbstractProfileDto {

    @Nullable
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "AbstractProfileDto{" +
                "uuid=" + uuid +
                '}';
    }
}
