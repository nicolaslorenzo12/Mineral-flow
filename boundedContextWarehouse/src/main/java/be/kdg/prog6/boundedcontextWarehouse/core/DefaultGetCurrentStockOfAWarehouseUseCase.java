package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.dto.WarehouseStockDto;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetCurrentStockOfAWarehouseCommand;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetCurrentStockOfAWarehouseUseCase;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DefaultGetCurrentStockOfAWarehouseUseCase implements GetCurrentStockOfAWarehouseUseCase {

    private final LoadWarehousePort loadWarehousePort;

    public DefaultGetCurrentStockOfAWarehouseUseCase(LoadWarehousePort loadWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
    }
    @Override
    public Warehouse getCurrentStockOfAWarehouse(GetCurrentStockOfAWarehouseCommand getCurrentStockOfAWarehouseCommand) {

        final int warehouseNumber = getCurrentStockOfAWarehouseCommand.warehouseNumber();
        return findWarehouse(warehouseNumber);

    }

    private Warehouse findWarehouse(int warehouseNumber) {
        return loadWarehousePort.loadWarehouseByWarehouseNumber(warehouseNumber)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Warehouse not found"));
    }
}
