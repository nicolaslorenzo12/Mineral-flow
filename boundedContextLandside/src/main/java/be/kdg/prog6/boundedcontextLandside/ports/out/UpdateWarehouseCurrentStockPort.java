package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;

public interface UpdateWarehouseCurrentStockPort {

    void updateWarehouse(Warehouse warehouse, int currentStock);
}
