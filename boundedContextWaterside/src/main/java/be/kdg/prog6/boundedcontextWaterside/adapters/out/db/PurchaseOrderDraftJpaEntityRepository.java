package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderDraftJpaEntityRepository extends JpaRepository<PurchaseOrderDraftJpaEntity, String> {
}
