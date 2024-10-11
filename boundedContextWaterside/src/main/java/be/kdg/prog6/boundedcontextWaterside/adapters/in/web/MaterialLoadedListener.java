package be.kdg.prog6.boundedcontextWaterside.adapters.in.web;

import be.kdg.prog6.boundedcontextWaterside.core.DefaultShipmentOrderMaterialLoaded;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.common.events.MaterialLoadedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MaterialLoadedListener {


    private final DefaultShipmentOrderMaterialLoaded defaultShipmentOrderMaterialLoaded;

    public MaterialLoadedListener(DefaultShipmentOrderMaterialLoaded defaultShipmentOrderMaterialLoaded) {
        this.defaultShipmentOrderMaterialLoaded = defaultShipmentOrderMaterialLoaded;
    }

    @RabbitListener(queues = "warehouse.material_loaded")
    public void materialLoaded(MaterialLoadedEvent materialLoadedEvent) {

        final ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID = new
                ShipmentOrder.ShipmentOrderUUID(materialLoadedEvent.shipmentOrderUUID());

        LocalDate actualDepartureDate = materialLoadedEvent.actualDepartureDate();

        defaultShipmentOrderMaterialLoaded.changeStatusOfShipToLoaded(shipmentOrderUUID, actualDepartureDate);
    }
}
