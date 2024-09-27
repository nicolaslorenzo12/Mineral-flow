package be.kdg.prog6.boundedcontextWarehouse.adapters.in.web;

import be.kdg.prog6.common.domain.WarehouseAction;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddMaterialCommand;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddMaterialUseCase;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetCurrentStockOfAWarehouseCommand;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetCurrentStockOfAWarehouseUseCase;
import be.kdg.prog6.common.exception.InsufficientStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WarehouseController {

    private final AddMaterialUseCase addMaterialUseCase;
    private final GetCurrentStockOfAWarehouseUseCase getCurrentStockOfAWarehouseUseCase;
    public WarehouseController(AddMaterialUseCase addMaterialUseCase, GetCurrentStockOfAWarehouseUseCase getCurrentStockOfAWarehouseUseCase) {
        this.addMaterialUseCase = addMaterialUseCase;
        this.getCurrentStockOfAWarehouseUseCase = getCurrentStockOfAWarehouseUseCase;
    }
    @PostMapping("/amountOfTons/{amountOfTons}/warehouse/{warehouseNumber}/action/{action}")
    public ResponseEntity<String> receiveOrDispatchMaterial(@PathVariable("amountOfTons") int amountOfTons,
                                      @PathVariable("warehouseNumber") int warehouseNumber,
                                      @PathVariable("action") WarehouseAction action)
    {
        try {
            addMaterialUseCase.addOrDispatchMaterial(new AddMaterialCommand(amountOfTons, warehouseNumber, action));
            return ResponseEntity.ok("Material action processed successfully.");

        } catch(InsufficientStockException e){

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error: " + e.getMessage() + ". Please request a smaller amount or check stock availability.");
        }
    }

    @GetMapping("current-stock/warehouse/{warehouseNumber}")
    public ResponseEntity<Integer> getCurrentStock(@PathVariable int warehouseNumber) {

        int currentStockOfAWarehouse = getCurrentStockOfAWarehouseUseCase.
                getCurrentStockOfAWarehouse(new GetCurrentStockOfAWarehouseCommand(warehouseNumber));

        return ResponseEntity.ok(currentStockOfAWarehouse);
    }
}
