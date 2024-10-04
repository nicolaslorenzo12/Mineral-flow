package be.kdg.prog6.boundedcontextWarehouse.adapters.ampq;

import be.kdg.prog6.boundedcontextWarehouse.domain.Pdt;
import be.kdg.prog6.boundedcontextWarehouse.domain.UpdateWarehouseAction;
import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.events.ActivityCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class WarehouseEventPublisher implements UpdateWarehousePort {

    private final RabbitTemplate rabbitTemplate;

    public WarehouseEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void updateWarehouse(UpdateWarehouseAction updateWarehouseAction, Warehouse warehouse, WarehouseActivity warehouseActivity, UUID appointmentUUID) {

        if(updateWarehouseAction.equals(UpdateWarehouseAction.CREATE_ACTIVIY)){

            final int warehouseNumber = warehouse.getWareHouseNumber();
            final int currentStock = warehouse.calculateAndGetCurrentStock();
            final String routingKey = "warehouse. " + warehouseNumber + " .activity_created";
            final String exchangeName = "warehouseExchange";
            final ActivityCreatedEvent body = new ActivityCreatedEvent(currentStock, warehouseNumber,
                    warehouse.getSellerUUID(), warehouse.getMaterialType(), appointmentUUID);

            rabbitTemplate.convertAndSend(exchangeName, routingKey, body);
        }

    }
}
