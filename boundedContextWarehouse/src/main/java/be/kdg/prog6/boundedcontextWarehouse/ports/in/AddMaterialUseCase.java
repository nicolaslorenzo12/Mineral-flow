package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.common.domain.WarehouseAction;

public interface AddMaterialUseCase {

    void addOrDispatchMaterial(int amountOfTons, int warehouseNumber, WarehouseAction actio);
}
