package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.common.domain.WarehouseAction;

public interface AddedMaterialProjector {

    void addOrDispatchMaterial(int intitalWeight, int finalWeight, int warehouseNumber, WarehouseAction actio);
}
