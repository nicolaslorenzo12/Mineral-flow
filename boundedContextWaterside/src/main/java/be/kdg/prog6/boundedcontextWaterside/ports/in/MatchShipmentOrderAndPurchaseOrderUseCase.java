package be.kdg.prog6.boundedcontextWaterside.ports.in;


import be.kdg.prog6.common.facades.MatchShipmentOrderWithPurchaseOrderCommand;

public interface MatchShipmentOrderAndPurchaseOrderUseCase {

    void matchPurchaseAndShipmentOrderWhenArriving(MatchShipmentOrderWithPurchaseOrderCommand matchShipmentOrderWithPurchaseOrderCommand);
}
