package be.kdg.prog6.boundedcontextWaterside.adapters.in.web.dto;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;

import java.time.LocalDate;

public class ShipmentOrderDto {

    private final String vesselNumber;
    private final ShipmentStatus shipmentStatus;
    private final LocalDate arrivalDate;

    public ShipmentOrderDto(String vesselNumber, ShipmentStatus shipmentStatus, LocalDate arrivalDate) {
        this.vesselNumber = vesselNumber;
        this.shipmentStatus = shipmentStatus;
        this.arrivalDate = arrivalDate;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }
}
