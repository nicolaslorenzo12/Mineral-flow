package be.kdg.prog6.boundedcontextWarehouse.domain.dto;

import java.util.List;

public class WarehouseDto {

    private final int warehouseNumber;
    private final int quantity;
    private final double occupancy;
    private final String materialDescription;
    private final String sellerName;
    private final List<PdtDto> pdtDtos;

    public WarehouseDto(int warehouseNumber, int quantity, double occupancy, String materialDescription, String sellerName, List<PdtDto> pdtDtos) {
        this.warehouseNumber = warehouseNumber;
        this.quantity = quantity;
        this.occupancy = occupancy;
        this.materialDescription = materialDescription;
        this.sellerName = sellerName;
        this.pdtDtos = pdtDtos;
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

    public String getSellerName() {
        return sellerName;
    }

    public List<PdtDto> getPdtDtos() {
        return pdtDtos;
    }
}
