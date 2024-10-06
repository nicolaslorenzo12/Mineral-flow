package be.kdg.prog6.boundedcontextWaterside.domain.dto;

import be.kdg.prog6.boundedcontextWaterside.domain.OrderLine;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;

import java.util.List;

public class PurchaseOrderLoadedDto {

    private final ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID;
    private final String poNumber;
    private final List<OrderLine> orderLineList;

    public PurchaseOrderLoadedDto(ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID, String poNumber, List<OrderLine> orderLineList) {
        this.shipmentOrderUUID = shipmentOrderUUID;
        this.poNumber = poNumber;
        this.orderLineList = orderLineList;
    }

    public ShipmentOrder.ShipmentOrderUUID getShipmentOrderUUID() {
        return shipmentOrderUUID;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public List<OrderLine> getOrderLineList() {
        return orderLineList;
    }
}
