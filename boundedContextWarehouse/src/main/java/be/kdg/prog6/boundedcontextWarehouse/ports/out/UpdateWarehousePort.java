package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;

import java.time.LocalDateTime;

public interface UpdateWarehousePort {

    void warehouseCreateActivity(Warehouse warehouse, WarehouseActivity warehouseActivity);
}
