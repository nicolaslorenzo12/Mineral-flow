package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseOrderDraftJpaEntityRepository extends JpaRepository<PurchaseOrderDraftJpaEntity, String> {

    Optional<PurchaseOrderDraftJpaEntity> findPurchaseOrderDraftJpaEntitiesByPoNumber(String poNumber);
}
