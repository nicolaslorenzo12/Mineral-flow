package be.kdg.prog6.boundedcontextWaterside.ports.out;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;

public interface UpdateShipmentOrderPort {
    void updateShipmentOrder(ShipmentOrder shipmentOrder, boolean notPublished);
}
