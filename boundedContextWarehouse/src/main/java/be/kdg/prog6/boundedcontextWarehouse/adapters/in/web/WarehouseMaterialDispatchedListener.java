package be.kdg.prog6.boundedcontextWarehouse.adapters.in.web;

import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddedOrDispatchedMaterialProjector;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.domain.WarehouseAction;
import be.kdg.prog6.common.events.MaterialDispatchedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WarehouseMaterialDispatchedListener {

    private final AddedOrDispatchedMaterialProjector addedOrDispatchedMaterialProjector;

    public WarehouseMaterialDispatchedListener(AddedOrDispatchedMaterialProjector addedOrDispatchedMaterialProjector) {
        this.addedOrDispatchedMaterialProjector = addedOrDispatchedMaterialProjector;
    }

    @RabbitListener(queues = "waterside.material_dispatched")
    public void materialDispatched(final MaterialDispatchedEvent materialDispatchedEvent){

        System.out.println("entering now");

        addedOrDispatchedMaterialProjector.dispatchMaterial(
                new Seller.CustomerUUID(materialDispatchedEvent.sellerUUID()),
                materialDispatchedEvent.materialType(),
                WarehouseAction.DISPATCH,
                materialDispatchedEvent.quantity()
        );
    }
}
