package be.kdg.prog6.boundedcontextWarehouse.adapters.ampq;

import be.kdg.prog6.boundedcontextWarehouse.domain.UpdateWarehouseAction;
import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdatePurchaseOrderPort;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.events.ActivityCreatedEvent;
import be.kdg.prog6.common.events.MaterialLoadedEvent;
import be.kdg.prog6.common.events.ShipmentOrderAndPurchaseOrderMatchedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class WarehouseEventPublisher implements UpdateWarehousePort, UpdatePurchaseOrderPort {

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


    @Override
    public void shipmentOrderAndPurchaseOrderMatched(UUID shipmentUUID, LocalDate localDate) {

        final String routingKey = "matched. " + shipmentUUID.toString() + " .shipment_order_and_purchase_order";
        final String exchangeName = "warehouseExchange";
        final ShipmentOrderAndPurchaseOrderMatchedEvent body = new ShipmentOrderAndPurchaseOrderMatchedEvent(shipmentUUID, localDate);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, body);
    }

    @Override
    public void materialLoaded(UUID shipmentOrderUUID, LocalDate localDate) {

        final String routingKey = "warehouse. " + shipmentOrderUUID.toString() + " .material_loaded";
        final String exchangeName = "warehouseExchange";
        final MaterialLoadedEvent body = new MaterialLoadedEvent(shipmentOrderUUID, localDate);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, body);
    }
}