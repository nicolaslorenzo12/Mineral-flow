package be.kdg.prog6.boundedcontextWaterside.ports.in;


import be.kdg.prog6.common.commands.MatchShipmentOrderWithPurchaseOrderCommand;

public interface MatchShipmentOrderAndPurchaseOrderUseCase {

    void matchPurchaseAndShipmentOrderWhenArriving(MatchShipmentOrderWithPurchaseOrderCommand matchShipmentOrderWithPurchaseOrderCommand);
}
