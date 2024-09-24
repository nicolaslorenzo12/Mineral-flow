package be.kdg.prog6.boundedcontextWarehouse.adapters.in.web;

import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddMaterialCommand;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddMaterialUseCase;
import be.kdg.prog6.common.domain.Material;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class WarehouseController {

    private final AddMaterialUseCase addMaterialUseCase;

    public WarehouseController(AddMaterialUseCase addMaterialUseCase) {
        this.addMaterialUseCase = addMaterialUseCase;
    }

    @PostMapping("/amount/{amount}/material/{uuid}/warehouseNumber/{warehouseNumber}")
    public void addMaterial(@PathVariable int amount, @PathVariable Material.MaterialUUID materialUUID, @PathVariable int warehouseNumber){

        addMaterialUseCase.addMaterial(new AddMaterialCommand(amount, materialUUID, warehouseNumber));
    }
}
