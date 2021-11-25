package ua.com.alevel.entity;

import ua.alevel.commons.entity.BaseEntity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Car extends BaseEntity {

    private String brand;
    private String model;
    private LocalDate year;
    private UUID ownerUuid;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public UUID getOwnerUuid() {
        return ownerUuid;
    }

    public void setOwnerUuid(UUID ownerUuid) {
        this.ownerUuid = ownerUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return getUuid().equals(car.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return "Car{" +
                "uuid=" + getUuid() +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", ownerUuid=" + ownerUuid +
                '}';
    }
}
