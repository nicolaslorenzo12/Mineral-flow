package be.kdg.prog6.boundedcontextWaterside.ports.out;

import be.kdg.prog6.boundedcontextWaterside.domain.PurchaseOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;

import java.util.Optional;

public interface LoadOrCreateShipmentOrderPort {

    Optional<ShipmentOrder> loadOrCreateShipmentOrder(String poNumber);
    Optional<PurchaseOrder> loadPurchaseOrder(String poNumber);
}
