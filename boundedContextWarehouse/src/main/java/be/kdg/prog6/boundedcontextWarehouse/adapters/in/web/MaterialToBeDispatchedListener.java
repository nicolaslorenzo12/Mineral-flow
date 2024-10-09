package be.kdg.prog6.boundedcontextWarehouse.adapters.in.web;

import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddedOrDispatchedMaterialProjector;
import be.kdg.prog6.common.events.MaterialToBeDispatchedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MaterialToBeDispatchedListener {

    private final AddedOrDispatchedMaterialProjector addedOrDispatchedMaterialProjector;

    public MaterialToBeDispatchedListener(AddedOrDispatchedMaterialProjector addedOrDispatchedMaterialProjector) {
        this.addedOrDispatchedMaterialProjector = addedOrDispatchedMaterialProjector;
    }

    @RabbitListener(queues = "waterside.material_dispatch")
    public void materialDispatched(final MaterialToBeDispatchedEvent materialDispatchedEvent){

        addedOrDispatchedMaterialProjector.dispatchMaterial(materialDispatchedEvent.shipmentOrderUUID());
    }
}
