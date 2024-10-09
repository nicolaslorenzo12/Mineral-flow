package be.kdg.prog6.boundedcontextWaterside.adapters.in.web;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.ports.in.ShipmentOrderAndPurchaseOrderMatched;
import be.kdg.prog6.common.events.ShipmentOrderAndPurchaseOrderMatchedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ShipmentOrderAndPurchaseOrderListener {

    private final ShipmentOrderAndPurchaseOrderMatched shipmentOrderAndPurchaseOrderMatched;

    public ShipmentOrderAndPurchaseOrderListener(ShipmentOrderAndPurchaseOrderMatched shipmentOrderAndPurchaseOrderMatched) {
        this.shipmentOrderAndPurchaseOrderMatched = shipmentOrderAndPurchaseOrderMatched;
    }

    @RabbitListener(queues = "matched.shipment_order_and_purchase_order")
    public void shipmentOrderAndPurchaseOrderMatched(final ShipmentOrderAndPurchaseOrderMatchedEvent shipmentOrderAndPurchaseOrderMatchedEvent) {

        final ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID = new
                ShipmentOrder.ShipmentOrderUUID(shipmentOrderAndPurchaseOrderMatchedEvent.shipmentOrderUUID());

        shipmentOrderAndPurchaseOrderMatched.changeStatusOfShipToArrived(shipmentOrderUUID);
    }
}
