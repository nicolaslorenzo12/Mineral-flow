package be.kdg.prog6.boundedcontextWarehouse.adapters.in.ampq;

import be.kdg.prog6.boundedcontextWarehouse.ports.in.MatchShipmentOrderWithPurchaseOrderUseCase;
import be.kdg.prog6.common.commands.MatchShipmentOrderWithPurchaseOrderCommand;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MatchShipmentOrderWithPurchaseOrderListener {

    private final MatchShipmentOrderWithPurchaseOrderUseCase matchShipmentOrderWithPurchaseOrderUseCase;


    public MatchShipmentOrderWithPurchaseOrderListener(MatchShipmentOrderWithPurchaseOrderUseCase matchShipmentOrderWithPurchaseOrderUseCase) {
        this.matchShipmentOrderWithPurchaseOrderUseCase = matchShipmentOrderWithPurchaseOrderUseCase;
    }


    @RabbitListener(queues = "match.shipment_order_and_purchase_order")
    public void matchShipmentOrderAndPurchaseOrder(final MatchShipmentOrderWithPurchaseOrderCommand matchShipmentOrderWithPurchaseOrderCommand) {

        matchShipmentOrderWithPurchaseOrderUseCase.matchShipmentOrderAndPurchaseOrder(matchShipmentOrderWithPurchaseOrderCommand);
    }
}
