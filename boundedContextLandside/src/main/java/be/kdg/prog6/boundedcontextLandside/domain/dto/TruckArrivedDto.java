package be.kdg.prog6.boundedcontextLandside.domain.dto;

import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class TruckArrivedDto {

    private final String licensePlateNumber;
    private final TruckStatus truckStatus;
    private final LocalDateTime arrivalTime;

    public TruckArrivedDto(String licensePlateNumber, TruckStatus truckStatus, LocalDateTime arrivalTime) {
        this.licensePlateNumber = licensePlateNumber;
        this.truckStatus = truckStatus;
        this.arrivalTime = arrivalTime;
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
}
