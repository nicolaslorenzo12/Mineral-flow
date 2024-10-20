package be.kdg.prog6.boundedcontextInvoice.ports.out;

import be.kdg.prog6.boundedcontextInvoice.domain.Warehouse;

import java.util.Optional;

public interface LoadWarehousePort {

    Optional<Warehouse> findWarehouseByWarehouseNumber(int warehouseNumber);
}
