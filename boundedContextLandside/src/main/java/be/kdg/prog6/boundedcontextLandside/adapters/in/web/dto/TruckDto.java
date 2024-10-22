package be.kdg.prog6.boundedcontextLandside.adapters.in.web.dto;

import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;

import java.time.LocalDateTime;

public class TruckDto {

    private final int warehouseNumber;
    private final String sellerName;
    private final String licensePlateNumber;
    private final TruckStatus truckStatus;
    private final LocalDateTime arrivalTime;
    private final String materialDescription;

    public TruckDto(int warehouseNumber, String sellerName, String licensePlateNumber, TruckStatus truckStatus, LocalDateTime arrivalTime, String materialDescription) {
        this.warehouseNumber = warehouseNumber;
        this.sellerName = sellerName;
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

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public String getSellerName() {
        return sellerName;
    }
}
