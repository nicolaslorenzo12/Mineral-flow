package be.kdg.prog6.boundedcontextWarehouse.domain.dto;

public class WarehouseStockDto {

    private int warehouseNumber;
    private int currentStock;

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

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }
}
