package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import java.time.LocalDate;
import java.util.UUID;

public interface UpdatePurchaseOrderPort {

    void shipmentOrderAndPurchaseOrderMatched(UUID shipmentOrderUUID, LocalDate localDate);
    void materialLoaded(UUID shipmentOrderUUID, LocalDate localDate);
}
