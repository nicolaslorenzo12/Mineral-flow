package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;

import java.util.Optional;

public interface LoadWarehousePort {

    Optional<Warehouse> loadWarehouseByWarehouseNumber(int warehouseNumber);
}
