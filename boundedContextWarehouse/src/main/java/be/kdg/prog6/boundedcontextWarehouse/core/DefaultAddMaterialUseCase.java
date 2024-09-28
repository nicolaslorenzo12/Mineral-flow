package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddMaterialCommand;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddMaterialUseCase;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdateWarehousePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DefaultAddMaterialUseCase implements AddMaterialUseCase {

    private final LoadWarehousePort loadWarehousePort;
    private final List<UpdateWarehousePort> updateWarehousePort;

    public DefaultAddMaterialUseCase(LoadWarehousePort loadWarehousePort, final List<UpdateWarehousePort> updateWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
        this.updateWarehousePort = updateWarehousePort;
    }

    @Override
    @Transactional
    public void addOrDispatchMaterial(AddMaterialCommand addMaterialCommand) {

        final Warehouse warehouse = findWarehouse(addMaterialCommand.warehouseNumber());
        WarehouseActivity warehouseActivity = buildWarehouseActivityAndAddActivityToWarehouse(warehouse, addMaterialCommand);
        updateWarehousePort.forEach(port -> port.warehouseCreateActivity(warehouse, warehouseActivity));
    }

    private Warehouse findWarehouse(int warehouseNumber) {
        return loadWarehousePort.loadWarehouseByWarehouseNumber(warehouseNumber)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
    }

    private WarehouseActivity buildWarehouseActivityAndAddActivityToWarehouse(Warehouse warehouse, AddMaterialCommand addMaterialCommand){

        return warehouse.addWarehouseActivity(addMaterialCommand.amountOfTons(),
                addMaterialCommand.warehouseNumber(),
                addMaterialCommand.action());
    }
}
