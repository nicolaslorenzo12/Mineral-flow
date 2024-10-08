package be.kdg.prog6.boundedcontextWaterside.adapters.ampq;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import be.kdg.prog6.common.facades.MatchShipmentOrderWithPurchaseOrderCommand;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class WatersideEventPublisher implements UpdateShipmentOrderPort{

    private final RabbitTemplate rabbitTemplate;

    public WatersideEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public void updateShipmentOrder(ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID) {

        final String routingKey = "match. " + shipmentOrderUUID.toString() + " .shipment_order_and_purchase_order";
        final String exchangeName = "watersideExchange";
        final MatchShipmentOrderWithPurchaseOrderCommand body = new MatchShipmentOrderWithPurchaseOrderCommand(shipmentOrderUUID.uuid());

        System.out.println("Leaving now");
        rabbitTemplate.convertAndSend(exchangeName, routingKey, body);
    }
}