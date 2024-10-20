package be.kdg.prog6.boundedcontextWaterside.ports.out;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;

import java.util.List;

public interface LoadShipmentOrderPort {

    ShipmentOrder loadShipmentOrder(ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID);
    ShipmentOrder loadShipmentOrderByVesselNumber(String vesselNumber);
    List<ShipmentOrder> getShipmentOrders();
}
