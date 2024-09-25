package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;

import java.time.LocalDateTime;

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
