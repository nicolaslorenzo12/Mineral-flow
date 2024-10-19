package be.kdg.prog6.boundedcontextLandside.domain.dto;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;

import java.time.LocalDateTime;

public class LoadedMaterialDto {

    private final String licensePlateNumber;
    private final LocalDateTime timeOfDelivery;
    private final int warehouseNumber;

    public LoadedMaterialDto(String licensePlateNumber, LocalDateTime timeOfDelivery, int warehouseNumber) {
        this.licensePlateNumber = licensePlateNumber;
        this.timeOfDelivery = timeOfDelivery;
        this.warehouseNumber = warehouseNumber;
    }


    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public LocalDateTime getTimeOfDelivery() {
        return timeOfDelivery;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }
}
