package be.kdg.prog6.boundedcontextWarehouse.adapters.in.web;

import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddedOrDispatchedMaterialProjector;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseAction;
import be.kdg.prog6.common.events.MaterialAddedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AddedMaterialToWarehouseListener {

    private final AddedOrDispatchedMaterialProjector addedOrDispatchedMaterialProjector;

    public AddedMaterialToWarehouseListener(AddedOrDispatchedMaterialProjector addedMaterialProjection) {
        this.addedOrDispatchedMaterialProjector = addedMaterialProjection;
    }

    @RabbitListener(queues = "landside.material_added")
    public void activityCreated(final MaterialAddedEvent materialAddedEvent){

        addedOrDispatchedMaterialProjector.addMaterial(materialAddedEvent.initialWeight(), materialAddedEvent.finalWeight(),
                materialAddedEvent.warehouseNumber(), WarehouseAction.RECEIVE, materialAddedEvent.appointmentUUID());
    }
}
