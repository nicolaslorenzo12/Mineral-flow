package be.kdg.prog6.boundedcontextWaterside.adapters.ampq;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import be.kdg.prog6.common.events.MaterialToBeDispatchedEvent;
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
    public void matchShipmentOrderAndPurchaseOrder(ShipmentOrder shipmentOrder) {

        if(shipmentOrder.getShipmentStatus().equals(ShipmentStatus.NOTARRIVED)) {
            final String routingKey = "match. " + shipmentOrder.getShipmentOrderUUID().toString() + " .shipment_order_and_purchase_order";
            final String exchangeName = "watersideExchange";
            final MatchShipmentOrderWithPurchaseOrderCommand body = new MatchShipmentOrderWithPurchaseOrderCommand(shipmentOrder.getShipmentOrderUUID().uuid());

            rabbitTemplate.convertAndSend(exchangeName, routingKey, body);
        }
    }

    @Override
    public void loadMaterial(ShipmentOrder shipmentOrder) {


            final String routingKey = "waterside. " + shipmentOrder.getShipmentOrderUUID().toString() + " .material_dispatch";
            final String exchangeName = "watersideExchange";
            final MaterialToBeDispatchedEvent materialToDispatch = new MaterialToBeDispatchedEvent(shipmentOrder.getShipmentOrderUUID().uuid());

            rabbitTemplate.convertAndSend(exchangeName, routingKey, materialToDispatch);
    }
}