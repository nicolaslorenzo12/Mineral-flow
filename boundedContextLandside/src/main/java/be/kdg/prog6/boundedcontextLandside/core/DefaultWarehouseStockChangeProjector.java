package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.UpdateWarehouseAction;
import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;
import be.kdg.prog6.boundedcontextLandside.ports.in.WarehouseStockChangeProjector;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DefaultWarehouseStockChangeProjector implements WarehouseStockChangeProjector {
    private final List<UpdateWarehousePort> updateWarehousePorts;

    public DefaultWarehouseStockChangeProjector(List<UpdateWarehousePort> updateWarehousePorts) {
        this.updateWarehousePorts = updateWarehousePorts;
    }
    @Override
    public void projectStockChange(int amountOfTons, int warehouseNumber, Seller.CustomerUUID sellerUuid,
                                   MaterialType materialType) {

        Warehouse warehouse = new Warehouse(warehouseNumber, sellerUuid, amountOfTons, materialType);

        updateWarehousePorts.forEach(updateWarehousePort -> updateWarehousePort.updateWarehouse(warehouse,
                UpdateWarehouseAction.CHANGE_WAREHOUSE_STOCK
        , UUID.randomUUID(), LocalDateTime.now()));
    }
}
