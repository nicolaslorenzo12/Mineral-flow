package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;

public interface UpdateWarehousePort {

    void warehouseActivityCreated(Warehouse warehouse, WarehouseActivity warehouseActivity);
}
