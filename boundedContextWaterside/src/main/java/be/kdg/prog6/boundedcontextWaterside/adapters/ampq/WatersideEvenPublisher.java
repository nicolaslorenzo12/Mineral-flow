package be.kdg.prog6.boundedcontextWaterside.adapters.ampq;

import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.Buyer;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.events.MaterialDispatchedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class WatersideEvenPublisher implements UpdateWarehousePort {
    private final RabbitTemplate rabbitTemplate;

    public WatersideEvenPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    //@Override
    //public void updateWarehouse(OrderLine orderLine, Seller.CustomerUUID sellerUUID, Buyer.CustomerUUID buyerUUID) {
//
//
    //    final String routingKey = "waterside. " + orderLine.getLineNumber() + " .material_dispatched";
    //    final String exchangeName = "watersideExchange";
    //    final MaterialDispatchedEvent body = new MaterialDispatchedEvent(sellerUUID.uuid(), buyerUUID.uuid(), orderLine.getMaterialType(), orderLine.getQuantity());
//
    //    rabbitTemplate.convertAndSend(exchangeName, routingKey, body);
    //}
}
