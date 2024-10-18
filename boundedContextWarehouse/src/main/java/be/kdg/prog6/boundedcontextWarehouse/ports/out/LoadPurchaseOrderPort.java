package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import be.kdg.prog6.common.domain.PurchaseOrder;

import java.util.List;
import java.util.UUID;

public interface LoadPurchaseOrderPort {

    PurchaseOrder loadPurchaseOrderByShipmentOrderUUID(UUID shipmentOrderUUID);
}
