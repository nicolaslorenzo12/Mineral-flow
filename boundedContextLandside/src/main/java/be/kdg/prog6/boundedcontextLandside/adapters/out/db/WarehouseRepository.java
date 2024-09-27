package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.common.domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("landsideWarehouseRepository")
public interface WarehouseRepository  extends JpaRepository<WarehouseJpaEntity, Integer> {
    Optional<WarehouseJpaEntity> findByWareHouseNumber(int warehouseNumber);
    Optional<WarehouseJpaEntity> findBySellerUUIDAndMaterialType(UUID sellerUuid, MaterialType materialType);
}
