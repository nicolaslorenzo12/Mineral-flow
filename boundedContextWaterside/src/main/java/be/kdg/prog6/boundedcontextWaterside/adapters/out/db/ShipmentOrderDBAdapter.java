package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreateShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShipmentOrderDBAdapter implements LoadOrCreateShipmentOrderPort, UpdateShipmentOrderPort {

    private final ShipmentOrderJpaEntityRepository shipmentOrderJpaEntityRepository;

    public ShipmentOrderDBAdapter(ShipmentOrderJpaEntityRepository shipmentOrderJpaEntityRepository) {
        this.shipmentOrderJpaEntityRepository = shipmentOrderJpaEntityRepository;
    }


    @Override
    public ShipmentOrder loadOrCreateShipmentOrder(ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID) {

        ShipmentOrderJpaEntity shipmentOrderJpaEntity = shipmentOrderJpaEntityRepository.findShipmentOrderJpaEntityByShipmentOrderUUID(shipmentOrderUUID.uuid());
        return buildShipmentOrderObject(shipmentOrderJpaEntity);
    }

    @Override
    public ShipmentOrder loadShipmentOrderByVesselNumber(String vesselNumber) {
        ShipmentOrderJpaEntity shipmentOrderJpaEntity = shipmentOrderJpaEntityRepository.findShipmentOrderJpaEntityByVesselNumber(vesselNumber);
        return buildShipmentOrderObject(shipmentOrderJpaEntity);
    }

    @Override
    public List<ShipmentOrder> getShipmentOrders() {
        List<ShipmentOrderJpaEntity> shipmentOrderJpaEntities = shipmentOrderJpaEntityRepository.findAll();

        return shipmentOrderJpaEntities.stream()
                .map(this::buildShipmentOrderObject).toList();
    }

    private ShipmentOrder buildShipmentOrderObject(ShipmentOrderJpaEntity jpaEntity) {

        return new ShipmentOrder(
                jpaEntity.getEstimatedArrivalDate(),
                jpaEntity.getEstimatedDepartureDate(),
                jpaEntity.getActualArrivalDate(),
                jpaEntity.getActualDepartureDate(),
                jpaEntity.getShipmentStatus(),
                new ShipmentOrder.ShipmentOrderUUID(jpaEntity.getShipmentOrderUUID()),
                jpaEntity.getVesselNumber()
        );
    }

    @Override
    public void updateShipmentOrder(ShipmentOrder shipmentOrder, boolean notPublished) {
        shipmentOrderJpaEntityRepository.save(buildShipmentOrderJpaEntity(shipmentOrder));
    }

    private ShipmentOrderJpaEntity buildShipmentOrderJpaEntity(ShipmentOrder shipmentOrder) {

        return new ShipmentOrderJpaEntity(
                shipmentOrder.getShipmentOrderUUID().uuid(),
                shipmentOrder.getEstimatedArrivalDate(),
                shipmentOrder.getEstimatedDepartureDate(),
                shipmentOrder.getActualArrivalDate(),
                shipmentOrder.getActualDepartureDate(),
                shipmentOrder.getShipmentStatus(),
                shipmentOrder.getVesselNumber()
        );
    }
}
