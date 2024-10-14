package be.kdg.prog6.boundedcontextLandside.domain.dto;

import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;

import java.time.LocalDateTime;

public class TruckDto {

    private final String licensePlateNumber;
    private final TruckStatus truckStatus;
    private final LocalDateTime arrivalTime;
    private final String materialDescription;

    public TruckDto(String licensePlateNumber, TruckStatus truckStatus, LocalDateTime arrivalTime, String materialDescription) {
        this.licensePlateNumber = licensePlateNumber;
        this.truckStatus = truckStatus;
        this.arrivalTime = arrivalTime;
        this.materialDescription = materialDescription;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public TruckStatus getTruckStatus() {
        return truckStatus;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }
}
