package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseActivityRepository extends JpaRepository<WarehouseJpaActivity, Integer> {

    List<WarehouseJpaActivity> findByWarehouseNumber(int warehouseNumber);
}
