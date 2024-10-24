package be.kdg.prog6.boundedcontextWarehouse.domain;

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
