package be.kdg.prog6.boundedcontextWaterside.domain;

import java.time.LocalDate;

public class ShipmentOrder {

    private final String poNumber;
    private final  LocalDate estimatedArrivalDate;
    private final LocalDate estimatedDepartureDate;
    private LocalDate actualArrivalDate;
    private LocalDate actualDepartureDate;
    private ShipmentStatus shipmentStatus;

    public ShipmentOrder(String poNumber, LocalDate estimatedArrivalDate, LocalDate estimatedDepartureDate) {
        this.poNumber = poNumber;
        this.estimatedArrivalDate = estimatedArrivalDate;
        this.estimatedDepartureDate = estimatedDepartureDate;
    }

    public ShipmentOrder(String poNumber, LocalDate estimatedArrivalDate, LocalDate estimatedDepartureDate,
                         LocalDate actualArrivalDate, LocalDate actualDepartureDate, ShipmentStatus shipmentStatus) {
        this.poNumber = poNumber;
        this.estimatedArrivalDate = estimatedArrivalDate;
        this.estimatedDepartureDate = estimatedDepartureDate;
        this.actualArrivalDate = actualArrivalDate;
        this.actualDepartureDate = actualDepartureDate;
        this.shipmentStatus = shipmentStatus;
    }
}
