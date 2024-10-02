package be.kdg.prog6.boundedcontextWarehouse.adapters.in.web;

import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddedMaterialProjector;
import be.kdg.prog6.common.domain.WarehouseAction;
import be.kdg.prog6.common.events.MaterialAddedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AddedMaterialToWarehouseListener {

    private final AddedMaterialProjector addedMaterialProjector;

    public AddedMaterialToWarehouseListener(AddedMaterialProjector addedMaterialProjection) {
        this.addedMaterialProjector = addedMaterialProjection;
    }

    @RabbitListener(queues = "landside.material_added")
    public void activityCreated(final MaterialAddedEvent materialAddedEvent){

        addedMaterialProjector.addOrDispatchMaterial(materialAddedEvent.initialWeight(), materialAddedEvent.finalWeight(),
                materialAddedEvent.warehouseNumber(), WarehouseAction.RECEIVE, materialAddedEvent.appointmentUUID());
    }
}
