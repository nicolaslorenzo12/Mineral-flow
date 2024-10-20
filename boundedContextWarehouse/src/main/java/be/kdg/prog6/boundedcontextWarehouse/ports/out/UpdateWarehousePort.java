package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import be.kdg.prog6.boundedcontextWarehouse.domain.UpdateWarehouseAction;
import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;

import java.util.UUID;

public interface UpdateWarehousePort {
    void updateWarehouse(UpdateWarehouseAction updateWarehouseAction, Warehouse warehouse, WarehouseActivity warehouseActivity, UUID appointmentUUID);
}
