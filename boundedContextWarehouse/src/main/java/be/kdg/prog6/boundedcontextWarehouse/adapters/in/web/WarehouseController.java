package be.kdg.prog6.boundedcontextWarehouse.adapters.in.web;

import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseAction;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddMaterialCommand;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddMaterialUseCase;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
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

    @PostMapping("/amount/{amount}/seller/{sellerUuid}/material/{materialUuid}/warehouse/{warehouseNumber}/action/{action}")
    public void addMaterial(@PathVariable("amount") int amountOfTons, @PathVariable("sellerUuid") UUID sellerUuid,
                            @PathVariable("materialUuid") UUID materialUuid,
                            @PathVariable("warehouseNumber") int warehouseNumber,
                            @PathVariable("action") WarehouseAction action){

        addMaterialUseCase.addMaterial(new AddMaterialCommand(amountOfTons, new Seller.CustomerUUID(sellerUuid), new Material.MaterialUUID(materialUuid),
                warehouseNumber, action));
    }
}
