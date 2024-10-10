package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import java.util.UUID;

public interface UpdatePurchaseOrderPort {

    void shipmentOrderAndPurchaseOrderMatched(UUID shipmentOrderUUID);
    void materialLoaded(UUID shipmentOrderUUID);
}
