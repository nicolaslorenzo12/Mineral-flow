package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("landsideSellerRepository")
public interface SellerRepository extends JpaRepository<SellerJpaEntity, UUID> {
    Optional<SellerJpaEntity> findBySellerUUID(UUID sellerUuid);
    Optional<SellerJpaEntity> findByName(String name);
}
