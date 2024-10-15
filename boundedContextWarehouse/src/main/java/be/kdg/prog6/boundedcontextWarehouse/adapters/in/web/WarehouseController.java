package be.kdg.prog6.boundedcontextWarehouse.adapters.in.web;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.dto.WarehouseDto;
import be.kdg.prog6.boundedcontextWarehouse.domain.dto.WarehouseStockDto;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetCurrentStockOfAWarehouseCommand;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetCurrentStockOfAWarehouseUseCase;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetWarehousesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;
import java.util.List;

@RestController
public class WarehouseController {
    private final GetCurrentStockOfAWarehouseUseCase getCurrentStockOfAWarehouseUseCase;
    private final GetWarehousesUseCase getWarehousesUseCase;
    public WarehouseController(GetCurrentStockOfAWarehouseUseCase getCurrentStockOfAWarehouseUseCase, GetWarehousesUseCase getWarehousesUseCase) {
        this.getCurrentStockOfAWarehouseUseCase = getCurrentStockOfAWarehouseUseCase;
        this.getWarehousesUseCase = getWarehousesUseCase;
    }

    @GetMapping("current-stock/warehouse/{warehouseNumber}")
    public ResponseEntity<WarehouseStockDto> getCurrentStock(@PathVariable int warehouseNumber) {

        WarehouseStockDto warehouseStockDto = getCurrentStockOfAWarehouseUseCase.
                getCurrentStockOfAWarehouse(new GetCurrentStockOfAWarehouseCommand(warehouseNumber));

        return ResponseEntity.ok(warehouseStockDto);
    }

    @GetMapping("warehouses")
    public ResponseEntity<List<WarehouseDto>> getWarehouses(){


        List<AbstractMap.SimpleEntry<Warehouse, String>> warehousesWithMaterialDescription =
                getWarehousesUseCase.getWarehousesWithMaterialDescription();

        List<WarehouseDto> warehouseDtos = createWarehouseDtos(warehousesWithMaterialDescription);

        return ResponseEntity.ok(warehouseDtos);
    }

    private List<WarehouseDto> createWarehouseDtos(List<AbstractMap.SimpleEntry<Warehouse, String>> warehousesWithMaterialDescription) {
        return warehousesWithMaterialDescription.stream()
                .map(entry -> {
                    Warehouse warehouse = entry.getKey();
                    String materialDescription = entry.getValue();

                    return createWarehouseDto(warehouse, materialDescription);
                })
                .toList();
    }

    private WarehouseDto createWarehouseDto(Warehouse warehouse, String materialDescription) {

        return new WarehouseDto(
                warehouse.getWareHouseNumber(),
                warehouse.calculateAndGetCurrentStock(),
                warehouse.getWarehousePercentageUtilization(),
                materialDescription);
    }

}
