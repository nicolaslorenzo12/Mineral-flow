package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.boundedcontextWarehouse.domain.dto.WarehouseStockDto;

public interface GetCurrentStockOfAWarehouseUseCase {

    WarehouseStockDto getCurrentStockOfAWarehouse(GetCurrentStockOfAWarehouseCommand getCurrentStockOfAWarehouseCommand);
}
