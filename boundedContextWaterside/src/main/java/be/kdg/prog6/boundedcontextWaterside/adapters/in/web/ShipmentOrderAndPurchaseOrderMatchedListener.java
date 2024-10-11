package be.kdg.prog6.boundedcontextWaterside.adapters.in.web;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.ports.in.ShipmentOrderAndPurchaseOrderMatched;
import be.kdg.prog6.common.events.ShipmentOrderAndPurchaseOrderMatchedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ShipmentOrderAndPurchaseOrderMatchedListener {

    private final ShipmentOrderAndPurchaseOrderMatched shipmentOrderAndPurchaseOrderMatched;

    public ShipmentOrderAndPurchaseOrderMatchedListener(ShipmentOrderAndPurchaseOrderMatched shipmentOrderAndPurchaseOrderMatched) {
        this.shipmentOrderAndPurchaseOrderMatched = shipmentOrderAndPurchaseOrderMatched;
    }

    @RabbitListener(queues = "matched.shipment_order_and_purchase_order")
    public void shipmentOrderAndPurchaseOrderMatched(ShipmentOrderAndPurchaseOrderMatchedEvent shipmentOrderAndPurchaseOrderMatchedEvent) {

        final ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID = new
                ShipmentOrder.ShipmentOrderUUID(shipmentOrderAndPurchaseOrderMatchedEvent.shipmentOrderUUID());

        LocalDate actualArrivalDate = shipmentOrderAndPurchaseOrderMatchedEvent.actualArrivalDate();

        shipmentOrderAndPurchaseOrderMatched.changeStatusOfShipToArrived(shipmentOrderUUID, actualArrivalDate);
    }
}
