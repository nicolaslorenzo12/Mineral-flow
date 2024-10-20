package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetWarehousesUseCase;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultGetWarehousesUseCase implements GetWarehousesUseCase {

    private final LoadWarehousePort loadWarehousePort;

    public DefaultGetWarehousesUseCase(LoadWarehousePort loadWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    @Transactional
    public List<Warehouse> getWarehouses() {
        return loadWarehousePort.loadAllWarehouses();
    }
}
