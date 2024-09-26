package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseAction;
public record AddMaterialCommand(int amountOfTons, int warehouseNumber, WarehouseAction action) {

}
