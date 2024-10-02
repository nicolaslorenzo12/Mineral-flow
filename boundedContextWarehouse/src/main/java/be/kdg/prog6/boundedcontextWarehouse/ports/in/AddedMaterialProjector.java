package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.common.domain.WarehouseAction;

import java.util.UUID;

public interface AddedMaterialProjector {

    void addOrDispatchMaterial(int intitalWeight, int finalWeight, int warehouseNumber, WarehouseAction action, UUID appointmentUUID);
}
