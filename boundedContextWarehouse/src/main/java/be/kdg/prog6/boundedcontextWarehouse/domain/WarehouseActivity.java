package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.WarehouseAction;

public record WarehouseActivity(int amountOfTons, int warehouseNumber, WarehouseAction action) {
    @Override
    public int amountOfTons() {
        return amountOfTons;
    }
    @Override
    public int warehouseNumber() {
        return warehouseNumber;
    }
    @Override
    public WarehouseAction action() {
        return action;
    }
}
