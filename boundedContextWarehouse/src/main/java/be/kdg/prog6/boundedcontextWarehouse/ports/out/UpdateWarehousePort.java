package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UpdateWarehousePort {

    void warehouseCreateActivity(Warehouse warehouse, WarehouseActivity warehouseActivity, UUID appointmentUUID);
}
