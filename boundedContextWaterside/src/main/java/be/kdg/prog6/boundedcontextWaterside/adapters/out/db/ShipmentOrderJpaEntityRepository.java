package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShipmentOrderJpaEntityRepository extends JpaRepository<ShipmentOrderJpaEntity, UUID>{

    ShipmentOrderJpaEntity findShipmentOrderJpaEntityByShipmentOrderUUID(UUID shipmentUUID);
}
