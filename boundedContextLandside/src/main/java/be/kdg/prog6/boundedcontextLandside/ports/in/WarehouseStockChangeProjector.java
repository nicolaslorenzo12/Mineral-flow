package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.domain.WarehouseAction;

public interface WarehouseStockChangeProjector {

    void projectStockChange(int amountOfTons, int warehouseNumber, Seller .CustomerUUID sellerUuid, MaterialType materialType);
}
