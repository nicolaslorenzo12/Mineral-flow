package be.kdg.prog6.boundedcontextWaterside.domain;

import java.time.LocalDate;
import java.util.UUID;

public class ShipmentOrder {

    private final String poNumber;
    private final  LocalDate estimatedArrivalDate;
    private final LocalDate estimatedDepartureDate;
    private LocalDate actualArrivalDate;
    private LocalDate actualDepartureDate;
    private ShipmentStatus shipmentStatus;
    private final ShipmentOrderUUID shipmentOrderUUID;

    public record ShipmentOrderUUID(UUID uuid){

    }

    public ShipmentOrder(ShipmentOrderUUID shipmentOrderUUID, String poNumber, LocalDate estimatedArrivalDate, LocalDate estimatedDepartureDate) {
        this.shipmentOrderUUID = shipmentOrderUUID;
        this.poNumber = poNumber;
        this.estimatedArrivalDate = estimatedArrivalDate;
        this.estimatedDepartureDate = estimatedDepartureDate;
    }

    public ShipmentOrder(String poNumber, LocalDate estimatedArrivalDate, LocalDate estimatedDepartureDate, LocalDate actualArrivalDate,
                         LocalDate actualDepartureDate, ShipmentStatus shipmentStatus, ShipmentOrderUUID shipmentOrderUUID) {
        this.poNumber = poNumber;
        this.estimatedArrivalDate = estimatedArrivalDate;
        this.estimatedDepartureDate = estimatedDepartureDate;
        this.actualArrivalDate = actualArrivalDate;
        this.actualDepartureDate = actualDepartureDate;
        this.shipmentStatus = shipmentStatus;
        this.shipmentOrderUUID = shipmentOrderUUID;
    }

    public String getPoNumber() {
        return poNumber;
    }
}
