package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.PurchaseOrder;
import be.kdg.prog6.boundedcontextWaterside.ports.in.LoadShipWithMaterialCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.LoadShipWithMaterialUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreateShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateWarehousePort;
import org.springframework.stereotype.Service;

@Service
public class DefaultLoadShipWithMaterialUseCase implements LoadShipWithMaterialUseCase {

    private final LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort;
    private final UpdateWarehousePort updateWarehousePort;

    public DefaultLoadShipWithMaterialUseCase(LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort, UpdateWarehousePort updateWarehousePort) {
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
        this.updateWarehousePort = updateWarehousePort;
    }

    @Override
    public void loadShipWithMaterial(LoadShipWithMaterialCommand loadShipWithMaterialCommand) {

        String poNumber = loadShipWithMaterialCommand.poNumber();
        PurchaseOrder purchaseOrder = loadOrCreateShipmentOrderPort.loadPurchaseOrder(poNumber);

        purchaseOrder.getOrderLineList().forEach(orderLine -> updateWarehousePort.updateWarehouse(orderLine, purchaseOrder.getSellerUuid(), purchaseOrder.getBuyerUuid()));
    }
}
