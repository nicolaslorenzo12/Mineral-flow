package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseOrderJpaEntityRepository extends JpaRepository<PurchaseOrderJpaEntity, String> {

    PurchaseOrderJpaEntity findByShipmentOrderUUID(UUID purchaseOrderUuid);
}
