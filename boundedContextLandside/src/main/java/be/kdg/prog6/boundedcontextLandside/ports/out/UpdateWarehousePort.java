package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;

public interface UpdateWarehousePort {
    void addAmountOfTonsOfMaterialToWarehouse(int amountOfTons, int warehouseNumber);
}
