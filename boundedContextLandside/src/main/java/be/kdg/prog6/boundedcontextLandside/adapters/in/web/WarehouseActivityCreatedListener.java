package be.kdg.prog6.boundedcontextLandside.adapters.in.web;

import be.kdg.prog6.boundedcontextLandside.ports.in.WarehouseStockChangeProjector;
import be.kdg.prog6.common.events.ActivityCreatedEvent;
import be.kdg.prog6.common.events.MaterialDispatchedEvent;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.List;

@Component
public class WarehouseActivityCreatedListener {

    private final WarehouseStockChangeProjector warehouseUtilizationProjector;

    public WarehouseActivityCreatedListener(WarehouseStockChangeProjector warehouseUtilizationProjector) {
        this.warehouseUtilizationProjector = warehouseUtilizationProjector;
    }

    @RabbitListener(queues = "warehouse.activity_created")
    public void activityCreated(final ActivityCreatedEvent activityCreatedEvent){
        warehouseUtilizationProjector.projectStockChange(activityCreatedEvent.amountOfTons(), activityCreatedEvent.warehouseNumber(),
                activityCreatedEvent.sellerUuid(), activityCreatedEvent.materialType());
    }

}
