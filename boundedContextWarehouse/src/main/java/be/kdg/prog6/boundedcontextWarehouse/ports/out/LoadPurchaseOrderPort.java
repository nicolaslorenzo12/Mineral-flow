package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import be.kdg.prog6.boundedcontextWarehouse.domain.PurchaseOrder;

import java.util.UUID;

public interface LoadPurchaseOrderPort {

    PurchaseOrder loadPurchaseOrderByShipmentOrderUUID(UUID shipmentOrderUUID);
}
