package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseAction;

import java.util.UUID;

public interface AddedOrDispatchedMaterialProjector {

    void addMaterial(int intitalWeight, int finalWeight, int warehouseNumber, WarehouseAction action, UUID appointmentUUID);
    void dispatchMaterial(UUID shipmentOrderUUID);
}
