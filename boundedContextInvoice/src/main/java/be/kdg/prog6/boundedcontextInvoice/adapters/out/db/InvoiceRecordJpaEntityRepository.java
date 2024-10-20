package be.kdg.prog6.boundedcontextInvoice.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvoiceRecordJpaEntityRepository extends JpaRepository<InvoiceRecordJpaEntity, UUID> {


}
