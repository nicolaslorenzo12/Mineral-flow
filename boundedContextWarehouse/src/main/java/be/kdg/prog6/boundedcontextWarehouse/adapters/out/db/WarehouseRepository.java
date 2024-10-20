package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.common.domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<WarehouseJpaEntity, Integer> {

    Optional<WarehouseJpaEntity> findByWarehouseNumber(int warehouseNumber);
    Optional<WarehouseJpaEntity> findBySellerUUIDAndMaterialType(UUID sellerUUID, MaterialType materialType);

}
