package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.domain.UpdateWarehouseAction;
import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;

public interface UpdateWarehousePort {

    void updateWarehouse(Warehouse warehouse, UpdateWarehouseAction updateWarehouseAction);
}
