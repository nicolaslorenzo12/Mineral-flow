package be.kdg.prog6.boundedcontextWarehouse.domain.dto;

public class WarehouseStockDto {

    private final int warehouseNumber;
    private final int currentStock;

    public WarehouseStockDto(int warehouseNumber, int currentStock) {
        this.warehouseNumber = warehouseNumber;
        this.currentStock = currentStock;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public int getCurrentStock() {
        return currentStock;
    }

}
