package be.kdg.prog6.boundedcontextWarehouse.domain.dto;

import java.util.List;

public class WarehouseDto {

    private final int warehouseNumber;
    private final int quantity;
    private final double occupancy;
    private final String materialDescription;

    public WarehouseDto(int warehouseNumber, int quantity, double occupancy, String materialDescription) {
        this.warehouseNumber = warehouseNumber;
        this.quantity = quantity;
        this.occupancy = occupancy;
        this.materialDescription = materialDescription;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getOccupancy() {
        return occupancy;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

}
