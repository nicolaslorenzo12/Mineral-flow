package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.boundedcontextWarehouse.domain.PurchaseOrder;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.MatchShipmentOrderWithPurchaseOrderUseCase;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadPurchaseOrderPort;
import be.kdg.prog6.common.facades.MatchShipmentOrderWithPurchaseOrderCommand;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultMatchShipmentOrderAndPurchaseOrderUseCase implements MatchShipmentOrderWithPurchaseOrderUseCase {

    public final LoadPurchaseOrderPort loadPurchaseOrderPort;

    public DefaultMatchShipmentOrderAndPurchaseOrderUseCase(LoadPurchaseOrderPort loadPurchaseOrderPort) {
        this.loadPurchaseOrderPort = loadPurchaseOrderPort;
    }

    @Override
    public void matchShipmentOrderAndPurchaseOrder(MatchShipmentOrderWithPurchaseOrderCommand matchShipmentOrderWithPurchaseOrderCommand) {
        UUID shipmentOrderUUID = matchShipmentOrderWithPurchaseOrderCommand.shipmentOrderUUID();

        PurchaseOrder purchaseOrder = loadPurchaseOrderPort.loadPurchaseOrderByShipmentOrderUUID
                (matchShipmentOrderWithPurchaseOrderCommand.shipmentOrderUUID());

        if(shipmentOrderUUID.equals(purchaseOrder.getShipmentOrderUUID())){
            System.out.println("Shipment order and purchase order match");
        }
        else{
            System.out.println("do not match");
        }
    }
}
