package be.kdg.prog6.boundedcontextWaterside.domain;

import be.kdg.prog6.common.exception.CustomException;
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
    public record ShipmentOrderUUID(UUID uuid){

    }

    public ShipmentOrder(ShipmentOrderUUID shipmentOrderUUID, LocalDate estimatedArrivalDate, LocalDate estimatedDepartureDate) {
        this.shipmentOrderUUID = shipmentOrderUUID;
        this.estimatedArrivalDate = estimatedArrivalDate;
        this.estimatedDepartureDate = estimatedDepartureDate;
    }

    public ShipmentOrder(LocalDate estimatedArrivalDate, LocalDate estimatedDepartureDate, LocalDate actualArrivalDate,
                         LocalDate actualDepartureDate, ShipmentStatus shipmentStatus, ShipmentOrderUUID shipmentOrderUUID) {
        this.estimatedArrivalDate = estimatedArrivalDate;
        this.estimatedDepartureDate = estimatedDepartureDate;
        this.actualArrivalDate = actualArrivalDate;
        this.actualDepartureDate = actualDepartureDate;
        this.shipmentStatus = shipmentStatus;
        this.shipmentOrderUUID = shipmentOrderUUID;
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

    public void setActualDepartureDate(LocalDate actualDepartureDate) {
        this.actualDepartureDate = actualDepartureDate;
    }

    public void checkIfShipmentOrderHasAlreadyHadThisStatus(ShipmentStatus shipmentStatus){

        int currentStatusCode = this.shipmentStatus.getCode();

        if (shipmentStatus.getCode() - currentStatusCode != 1) {
            throw new CustomException(HttpStatus.CONFLICT, "The ship's status transitions must follow the correct order. Please ensure all necessary processes have been completed before moving to the next status.");
        }

        this.setShipmentStatus(shipmentStatus);
    }
}
