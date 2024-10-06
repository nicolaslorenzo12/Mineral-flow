package be.kdg.prog6.boundedcontextWaterside.domain.dto;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;

import java.time.LocalDate;

public class ShipmentAndPurchaseOrderMatchedDto {

    private final ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID;
    private final String poNumber;
    private final LocalDate actualArrivalDate;
    private final ShipmentStatus shipmentStatus;

    public ShipmentAndPurchaseOrderMatchedDto(ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID, String poNumber, LocalDate actualArrivalDate, ShipmentStatus shipmentStatus) {
        this.shipmentOrderUUID = shipmentOrderUUID;
        this.poNumber = poNumber;
        this.actualArrivalDate = actualArrivalDate;
        this.shipmentStatus = shipmentStatus;
    }

    public ShipmentOrder.ShipmentOrderUUID getShipmentOrderUUID() {
        return shipmentOrderUUID;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public LocalDate getActualArrivalDate() {
        return actualArrivalDate;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }
}
