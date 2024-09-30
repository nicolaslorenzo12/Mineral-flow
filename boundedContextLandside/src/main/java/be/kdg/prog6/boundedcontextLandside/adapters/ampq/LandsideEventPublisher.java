package be.kdg.prog6.boundedcontextLandside.adapters.ampq;

import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.events.MaterialAddedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class LandsideEventPublisher implements UpdateWarehousePort {

    private final RabbitTemplate rabbitTemplate;

    public LandsideEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public void addAmountOfTonsOfMaterialToWarehouse(int amountOfTons, int warehouseNumber) {
        final String routingKey = "landside. " + warehouseNumber + " .material_added";
        final String exchangeName = "landsideExchange";
        final MaterialAddedEvent body = new MaterialAddedEvent(amountOfTons, warehouseNumber);

        rabbitTemplate.convertAndSend(exchangeName, routingKey, body);
    }
}
