package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;

import java.util.AbstractMap;
import java.util.List;

public interface GetWarehousesUseCase {

    List<AbstractMap.SimpleEntry<Warehouse, String>> getWarehousesWithMaterialDescription();
}
