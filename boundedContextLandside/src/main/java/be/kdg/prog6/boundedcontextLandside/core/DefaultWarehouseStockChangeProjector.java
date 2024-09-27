package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;
import be.kdg.prog6.boundedcontextLandside.ports.in.WarehouseStockChangeProjector;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateWarehousePort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.domain.WarehouseAction;
import org.springframework.stereotype.Service;

@Service
public class DefaultWarehouseStockChangeProjector implements WarehouseStockChangeProjector {

    private final LoadOrCreateWarehousePort loadOrCreateWarehousePort;
    private final UpdateWarehousePort updateWarehousePort;

    public DefaultWarehouseStockChangeProjector(LoadOrCreateWarehousePort loadOrCreateWarehousePort, UpdateWarehousePort updateWarehousePort) {
        this.loadOrCreateWarehousePort = loadOrCreateWarehousePort;
        this.updateWarehousePort = updateWarehousePort;
    }

    @Override
    public void projectStockChange(int amountOfTons, int warehouseNumber, WarehouseAction warehouseAction, Seller.CustomerUUID sellerUuid, MaterialType materialType) {

        final Warehouse warehouse = loadOrCreateWarehousePort.loadOrCreateWarehouseByWarehouseNumber(warehouseNumber, sellerUuid.uuid(), materialType);
        warehouse.modifyStock(amountOfTons, warehouseAction);
        System.out.println(warehouse.getCurrentStockStorage());
        updateWarehousePort.updateWarehouse(warehouse);
    }
}
