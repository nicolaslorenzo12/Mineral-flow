package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;
import be.kdg.prog6.boundedcontextLandside.ports.in.WarehouseStockChangeProjector;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.domain.WarehouseAction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultWarehouseStockChangeProjector implements WarehouseStockChangeProjector {
    private final UpdateWarehousePort updateWarehousePort;

    public DefaultWarehouseStockChangeProjector(UpdateWarehousePort updateWarehousePort) {
        this.updateWarehousePort = updateWarehousePort;
    }
    @Override
    public void projectStockChange(int amountOfTons, int warehouseNumber, WarehouseAction warehouseAction, Seller.CustomerUUID sellerUuid,
                                   MaterialType materialType) {
        updateWarehousePort.updateWarehouse(new Warehouse(warehouseNumber, sellerUuid, amountOfTons, materialType));
    }
}
