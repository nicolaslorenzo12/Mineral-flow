package be.kdg.prog6.boundedcontextWaterside.domain;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.UUID;

public class ShipmentOrder {

    private final  LocalDate estimatedArrivalDate;
    private final LocalDate estimatedDepartureDate;
    private LocalDate actualArrivalDate;
    private LocalDate actualDepartureDate;
    private ShipmentStatus shipmentStatus;
    private final ShipmentOrderUUID shipmentOrderUUID;
    private final String vesselNumber;
    public record ShipmentOrderUUID(UUID uuid){

    }

    public ShipmentOrder(ShipmentOrderUUID shipmentOrderUUID, LocalDate estimatedArrivalDate, LocalDate estimatedDepartureDate, String poNumber, String vesselNumber) {
        this.shipmentOrderUUID = shipmentOrderUUID;
        this.estimatedArrivalDate = estimatedArrivalDate;
        this.estimatedDepartureDate = estimatedDepartureDate;
        this.vesselNumber = vesselNumber;
    }

    public ShipmentOrder(LocalDate estimatedArrivalDate, LocalDate estimatedDepartureDate, LocalDate actualArrivalDate,
                         LocalDate actualDepartureDate, ShipmentStatus shipmentStatus, ShipmentOrderUUID shipmentOrderUUID, String vesselNumber) {
        this.estimatedArrivalDate = estimatedArrivalDate;
        this.estimatedDepartureDate = estimatedDepartureDate;
        this.actualArrivalDate = actualArrivalDate;
        this.actualDepartureDate = actualDepartureDate;
        this.shipmentStatus = shipmentStatus;
        this.shipmentOrderUUID = shipmentOrderUUID;
        this.vesselNumber = vesselNumber;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public LocalDate getEstimatedArrivalDate() {
        return estimatedArrivalDate;
    }

    public LocalDate getEstimatedDepartureDate() {
        return estimatedDepartureDate;
    }

    public LocalDate getActualArrivalDate() {
        return actualArrivalDate;
    }

    public LocalDate getActualDepartureDate() {
        return actualDepartureDate;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public ShipmentOrderUUID getShipmentOrderUUID() {
        return shipmentOrderUUID;
    }

    public void setActualArrivalDate(LocalDate actualArrivalDate) {
        this.actualArrivalDate = actualArrivalDate;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public void setActualDepartureDate(LocalDate actualDepartureDate) {
        this.actualDepartureDate = actualDepartureDate;
    }

    public void checkIfShipmentOrderHasAlreadyHadThisStatus(ShipmentStatus shipmentStatus){

        int currentStatusCode = this.shipmentStatus.getCode();

        if (shipmentStatus.getCode() - currentStatusCode != 1) {
            throw new IllegalArgumentException("The ship's status transitions must follow the correct order. Please ensure all necessary processes have been completed before moving to the next status.");
        }

        this.setShipmentStatus(shipmentStatus);
    }

}
