package be.kdg.prog6.boundedcontextWarehouse.ports.in;

public interface GetCurrentStockOfAWarehouseUseCase {

    int getCurrentStockOfAWarehouse(GetCurrentStockOfAWarehouseCommand getCurrentStockOfAWarehouseCommand);
}
