package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("landsideWarehouseRepository")
public interface WarehouseRepository  extends JpaRepository<WarehouseJpaEntity, Integer> {

    Optional<WarehouseJpaEntity> findByWareHouseNumber(int warehouseNumber);
}
