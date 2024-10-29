package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SellerJpaRepository extends JpaRepository<SellerJpaEntity, UUID> {
    
    Optional<SellerJpaEntity> findBySellerUUID(UUID sellerId);
}
