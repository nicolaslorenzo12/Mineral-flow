package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShipmentOrderJpaEntityRepository extends JpaRepository<ShipmentOrderJpaEntity, UUID>{

    ShipmentOrderJpaEntity findShipmentOrderJpaEntityByShipmentOrderUUID(UUID shipmentUUID);
}
