package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.common.commands.MatchShipmentOrderWithPurchaseOrderCommand;

public interface MatchShipmentOrderWithPurchaseOrderUseCase {

    void matchShipmentOrderAndPurchaseOrder(MatchShipmentOrderWithPurchaseOrderCommand matchShipmentOrderWithPurchaseOrderCommand);
}
