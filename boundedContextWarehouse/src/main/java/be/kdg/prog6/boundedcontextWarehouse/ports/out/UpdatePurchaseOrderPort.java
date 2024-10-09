package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import java.util.UUID;

public interface UpdatePurchaseOrderPort {

    void updatePurchase(UUID shipmentOrderUUID);
}
