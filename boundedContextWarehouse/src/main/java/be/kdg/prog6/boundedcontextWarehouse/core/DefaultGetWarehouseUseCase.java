package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetWarehouseCommand;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetWarehouseUseCase;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DefaultGetWarehouseUseCase implements GetWarehouseUseCase {

    private final LoadWarehousePort loadWarehousePort;

    public DefaultGetWarehouseUseCase(LoadWarehousePort loadWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    public Warehouse getWarehouseByWarehouseNumber(GetWarehouseCommand getWarehouseCommand) {
        return loadWarehousePort.loadWarehouseByWarehouseNumber(getWarehouseCommand.warehouseNumber())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Warehouse not found"));
    }
}
