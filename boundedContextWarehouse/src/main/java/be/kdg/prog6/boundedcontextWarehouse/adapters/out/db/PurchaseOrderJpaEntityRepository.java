package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.common.domain.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PurchaseOrderJpaEntityRepository extends JpaRepository<PurchaseOrderJpaEntity, String> {

    PurchaseOrderJpaEntity findByShipmentOrderUUID(UUID purchaseOrderUuid);
}
