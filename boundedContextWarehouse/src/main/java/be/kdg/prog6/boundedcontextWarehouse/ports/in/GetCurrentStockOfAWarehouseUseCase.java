package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.dto.WarehouseStockDto;
public interface GetCurrentStockOfAWarehouseUseCase {

    Warehouse getCurrentStockOfAWarehouse(GetCurrentStockOfAWarehouseCommand getCurrentStockOfAWarehouseCommand);
}
