package be.kdg.prog6.boundedcontextLandside.ports.out;

public interface UpdateWarehousePort {
    void addAmountOfTonsToWarehouseInWarehouseContext(int amountOfTons, int warehouseNumber);
}
