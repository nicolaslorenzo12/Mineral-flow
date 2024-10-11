package be.kdg.prog6.boundedcontextWaterside.ports.out;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;

import java.util.List;
import java.util.Optional;

public interface LoadOrCreateShipmentOrderPort {

    ShipmentOrder loadOrCreateShipmentOrder(ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID);
    List<ShipmentOrder> getShipmentOrders();
}
