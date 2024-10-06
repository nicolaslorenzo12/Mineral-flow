package be.kdg.prog6.boundedcontextWarehouse.adapters.in.web;

import be.kdg.prog6.boundedcontextWarehouse.domain.dto.WarehouseStockDto;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetCurrentStockOfAWarehouseCommand;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetCurrentStockOfAWarehouseUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WarehouseController {
    private final GetCurrentStockOfAWarehouseUseCase getCurrentStockOfAWarehouseUseCase;
    public WarehouseController(GetCurrentStockOfAWarehouseUseCase getCurrentStockOfAWarehouseUseCase) {
        this.getCurrentStockOfAWarehouseUseCase = getCurrentStockOfAWarehouseUseCase;
    }

    @GetMapping("current-stock/warehouse/{warehouseNumber}")
    public ResponseEntity<WarehouseStockDto> getCurrentStock(@PathVariable int warehouseNumber) {

        WarehouseStockDto warehouseStockDto = getCurrentStockOfAWarehouseUseCase.
                getCurrentStockOfAWarehouse(new GetCurrentStockOfAWarehouseCommand(warehouseNumber));

        return ResponseEntity.ok(warehouseStockDto);
    }
}
