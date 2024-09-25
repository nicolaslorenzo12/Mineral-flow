package be.kdg.prog6.boundedcontextWarehouse.adapters.in.web;

import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseAction;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddMaterialCommand;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddMaterialUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class WarehouseController {

    private final AddMaterialUseCase addMaterialUseCase;
    public WarehouseController(AddMaterialUseCase addMaterialUseCase) {
        this.addMaterialUseCase = addMaterialUseCase;
    }
    @PostMapping("/amountOfTons/{amountOfTons}/warehouse/{warehouseNumber}/action/{action}")
    public void addOrDispatchMaterial(@PathVariable("amountOfTons") int amountOfTons,
                                      @PathVariable("warehouseNumber") int warehouseNumber,
                                      @PathVariable("action") WarehouseAction action){

        addMaterialUseCase.addOrDispatchMaterial(new AddMaterialCommand(amountOfTons, warehouseNumber, action));
    }
}
