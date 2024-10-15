package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetWarehousesUseCase;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadMaterialPort;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.common.domain.Material;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;

@Service
public class DefaultGetWarehousesUseCase implements GetWarehousesUseCase {

    private final LoadWarehousePort loadWarehousePort;
    private final LoadMaterialPort loadMaterialPort;

    public DefaultGetWarehousesUseCase(LoadWarehousePort loadWarehousePort, LoadMaterialPort loadMaterialPort) {
        this.loadWarehousePort = loadWarehousePort;
        this.loadMaterialPort = loadMaterialPort;
    }

    @Override
    public List<AbstractMap.SimpleEntry<Warehouse, String>> getWarehousesWithMaterialDescription() {
        List<Warehouse> warehouses = loadWarehousePort.loadAllWarehouses();

        return warehouses.stream()
                .map(warehouse -> {
                    Material material  = loadMaterialPort.loadMaterialPort(warehouse.getMaterialType());
                    return new AbstractMap.SimpleEntry<>(warehouse, material.toString());
                })
                .toList();
    }
}
