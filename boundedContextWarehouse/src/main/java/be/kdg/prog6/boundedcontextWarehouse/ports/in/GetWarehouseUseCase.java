package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;

public interface GetWarehouseUseCase {

    Warehouse getWarehouseByWarehouseNumber(GetWarehouseCommand getWarehouseCommand);
}
