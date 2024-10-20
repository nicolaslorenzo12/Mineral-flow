package be.kdg.prog6.boundedcontextInvoice.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository("invoiceWarehouseRepository")
public interface WarehouseJpaEntityRepository extends JpaRepository<WarehouseJpaEntity, Integer> {

    Optional<WarehouseJpaEntity> findWarehouseJpaEntityByWarehouseNumber(int warehouseNumber);

}
