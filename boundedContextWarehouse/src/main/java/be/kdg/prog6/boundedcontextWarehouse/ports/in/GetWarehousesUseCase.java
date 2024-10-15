package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;

import java.util.List;

public interface GetWarehousesUseCase {

    List<Warehouse> getWarehouses();
}
