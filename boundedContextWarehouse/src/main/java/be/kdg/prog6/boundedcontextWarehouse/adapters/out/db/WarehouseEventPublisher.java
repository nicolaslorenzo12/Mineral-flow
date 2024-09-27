package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.events.ActivityCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class WarehouseEventPublisher implements UpdateWarehousePort {

    private final RabbitTemplate rabbitTemplate;

    public WarehouseEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void warehouseCreateActivity(Warehouse warehouse, WarehouseActivity warehouseActivity) {

        final int warehouseNumber = warehouse.getWareHouseNumber();
        final String routingKey = "warehouse. " + warehouseNumber + " .activity_created";
        final String exchangeName = "warehouseExchange";
        final ActivityCreatedEvent body = new ActivityCreatedEvent(warehouseActivity.amountOfTons(), warehouseNumber, warehouseActivity.action(),
                warehouse.getSellerUUID(), warehouse.getMaterialType());

        rabbitTemplate.convertAndSend(exchangeName, routingKey, body);
    }
}
