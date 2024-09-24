package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<WarehouseJpaEntity, Integer> {

    Optional<WarehouseJpaEntity> findByWareHouseNumber(int warehouseNumber);
}
