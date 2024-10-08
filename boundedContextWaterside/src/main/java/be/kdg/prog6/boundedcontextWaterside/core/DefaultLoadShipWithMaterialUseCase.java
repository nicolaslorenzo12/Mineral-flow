package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
import be.kdg.prog6.boundedcontextWaterside.domain.dto.PurchaseOrderLoadedDto;
import be.kdg.prog6.boundedcontextWaterside.ports.in.LoadShipWithMaterialCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.LoadShipWithMaterialUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreateShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DefaultLoadShipWithMaterialUseCase implements LoadShipWithMaterialUseCase {

    //private final LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort;
    //private final UpdateWarehousePort updateWarehousePort;
    //private final UpdateShipmentOrderPort updateShipmentOrderPort;
//
    //public DefaultLoadShipWithMaterialUseCase(LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort, UpdateWarehousePort updateWarehousePort,
    //                                          UpdateShipmentOrderPort updateShipmentOrderPort) {
    //    this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
    //    this.updateWarehousePort = updateWarehousePort;
    //    this.updateShipmentOrderPort = updateShipmentOrderPort;
    //}

    //@Override
    //public PurchaseOrderLoadedDto loadShipWithMaterial(LoadShipWithMaterialCommand loadShipWithMaterialCommand) {
//
    //    String poNumber = loadShipWithMaterialCommand.poNumber();
    //    PurchaseOrder purchaseOrder = loadOrCreateShipmentOrderPort.loadPurchaseOrder(poNumber)
    //            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Purchase order was not found"));
//
    //    ShipmentOrder shipmentOrder = loadOrCreateShipmentOrderPort.loadOrCreateShipmentOrder(poNumber)
    //            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Shipment order was not found"));
//
    //    shipmentOrder.checkIfShipmentOrderHasAlreadyHadThisStatus(ShipmentStatus.LOADED);
    //    purchaseOrder.getOrderLineList().forEach(orderLine -> updateWarehousePort.updateWarehouse(orderLine, purchaseOrder.getSellerUuid(), purchaseOrder.getBuyerUuid()));
//
    //    updateShipmentOrderPort.updateShipmentOrder(shipmentOrder);
//
    //    return new PurchaseOrderLoadedDto(shipmentOrder.getShipmentOrderUUID(), purchaseOrder.getPoNumber(), purchaseOrder.getOrderLineList());
    //}
}
