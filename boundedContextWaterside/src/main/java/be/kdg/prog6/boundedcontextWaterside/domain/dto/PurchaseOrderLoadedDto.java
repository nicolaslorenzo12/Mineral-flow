package be.kdg.prog6.boundedcontextWaterside.domain.dto;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;

import java.util.List;

public class PurchaseOrderLoadedDto {

    private final ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID;
    private final String poNumber;

    public PurchaseOrderLoadedDto(ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID, String poNumber) {
        this.shipmentOrderUUID = shipmentOrderUUID;
        this.poNumber = poNumber;
    }

    public ShipmentOrder.ShipmentOrderUUID getShipmentOrderUUID() {
        return shipmentOrderUUID;
    }

    public String getPoNumber() {
        return poNumber;
    }

}
