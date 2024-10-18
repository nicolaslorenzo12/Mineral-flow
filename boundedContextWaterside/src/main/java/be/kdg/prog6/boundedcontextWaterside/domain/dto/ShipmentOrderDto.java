package be.kdg.prog6.boundedcontextWaterside.domain.dto;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;

import java.util.UUID;

public class ShipmentOrderDto {

    private final UUID shipmentOrderUUID;
    private final ShipmentStatus shipmentStatus;

    public ShipmentOrderDto(UUID shipmentOrderUUID, ShipmentStatus shipmentStatus) {
        this.shipmentOrderUUID = shipmentOrderUUID;
        this.shipmentStatus = shipmentStatus;
    }

    public UUID getShipmentOrderUUID() {
        return shipmentOrderUUID;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }
}
