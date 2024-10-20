package be.kdg.prog6.boundedcontextInvoice.domain;
;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;

public class Warehouse {

    private final int warehouseNumber;
    private final Seller.CustomerUUID sellerUUID;
    private final MaterialType materialType;

    public Warehouse(int warehouseNumber, Seller.CustomerUUID sellerUUID, MaterialType materialType) {
        this.warehouseNumber = warehouseNumber;
        this.sellerUUID = sellerUUID;
        this.materialType = materialType;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public Seller.CustomerUUID getSellerUUID() {
        return sellerUUID;
    }


    public MaterialType getMaterialType() {
        return materialType;
    }
}
