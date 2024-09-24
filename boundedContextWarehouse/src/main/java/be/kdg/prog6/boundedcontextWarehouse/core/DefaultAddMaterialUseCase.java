package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddMaterialCommand;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddMaterialUseCase;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DefaultAddMaterialUseCase implements AddMaterialUseCase {

    private final LoadWarehousePort loadWarehousePort;

    public DefaultAddMaterialUseCase(LoadWarehousePort loadWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    @Transactional
    public void addMaterial(AddMaterialCommand addMaterialCommand) {

        final int warehouseNumber = addMaterialCommand.warehouseNumber();
        final Warehouse warehouse = loadWarehousePort.loadWarehouseByWarehouseNumber(warehouseNumber)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

         WarehouseActivity warehouseActivity= warehouse.addWarehouseActivity(addMaterialCommand.amount(), addMaterialCommand.warehouseNumber(), addMaterialCommand.materialUUID());

         System.out.println("This amount of tons were added" + warehouseActivity.amountOfTons());

    }
}

