package be.kdg.prog6.boundedcontextWaterside.adapters.in.web;

import be.kdg.prog6.boundedcontextWaterside.core.DefaultShipmentOrderMaterialLoaded;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.common.events.ShipmentOrderAndPurchaseOrderMatchedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MaterialLoadedListener {


    private final DefaultShipmentOrderMaterialLoaded defaultShipmentOrderMaterialLoaded;

    public MaterialLoadedListener(DefaultShipmentOrderMaterialLoaded defaultShipmentOrderMaterialLoaded) {
        this.defaultShipmentOrderMaterialLoaded = defaultShipmentOrderMaterialLoaded;
    }

    @RabbitListener(queues = "warehouse.material_loaded")
    public void shipmentOrderAndPurchaseOrderMatched(final ShipmentOrderAndPurchaseOrderMatchedEvent
                                                                 shipmentOrderAndPurchaseOrderMatchedEvent) {

        final ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID = new
                ShipmentOrder.ShipmentOrderUUID(shipmentOrderAndPurchaseOrderMatchedEvent.shipmentOrderUUID());

        defaultShipmentOrderMaterialLoaded.changeStatusOfShipToLoaded(shipmentOrderUUID);
    }
}
