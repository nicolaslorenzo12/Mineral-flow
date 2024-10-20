package be.kdg.prog6.boundedcontextInvoice.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("invoiceSellerRepository")
public interface SellerJpaEntityRepository extends JpaRepository<SellerJpaEntity, UUID> {

    Optional<SellerJpaEntity> findBySellerUUID(UUID sellerUUID);
}