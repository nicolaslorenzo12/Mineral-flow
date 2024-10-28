package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.common.domain.PurchaseOrder;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.MatchShipmentOrderWithPurchaseOrderUseCase;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadPurchaseOrderPort;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdatePurchaseOrderPort;
import be.kdg.prog6.common.commands.MatchShipmentOrderWithPurchaseOrderCommand;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class DefaultMatchShipmentOrderAndPurchaseOrderUseCase implements MatchShipmentOrderWithPurchaseOrderUseCase {

    private final LoadPurchaseOrderPort loadPurchaseOrderPort;
    private final UpdatePurchaseOrderPort updatePurchaseOrderPort;

    public DefaultMatchShipmentOrderAndPurchaseOrderUseCase(LoadPurchaseOrderPort loadPurchaseOrderPort, UpdatePurchaseOrderPort updatePurchaseOrderPort) {
        this.loadPurchaseOrderPort = loadPurchaseOrderPort;
        this.updatePurchaseOrderPort = updatePurchaseOrderPort;
    }

    @Override
    public void matchShipmentOrderAndPurchaseOrder(MatchShipmentOrderWithPurchaseOrderCommand matchShipmentOrderWithPurchaseOrderCommand) {
        UUID shipmentOrderUUID = matchShipmentOrderWithPurchaseOrderCommand.shipmentOrderUUID();

        PurchaseOrder purchaseOrder = loadPurchaseOrderPort.loadPurchaseOrderByShipmentOrderUUID
                (matchShipmentOrderWithPurchaseOrderCommand.shipmentOrderUUID());

        if(shipmentOrderUUID.equals(purchaseOrder.getShipmentOrderUUID())){
            System.out.println("Shipment order and purchase order match");
            updatePurchaseOrderPort.shipmentOrderAndPurchaseOrderMatched(shipmentOrderUUID, LocalDate.now());
        }
        else{
            System.out.println("do not match");
        }
    }
}
