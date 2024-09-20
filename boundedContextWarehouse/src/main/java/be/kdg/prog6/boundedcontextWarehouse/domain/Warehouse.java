package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.domain.Uom;

public class Warehouse {

    private Seller.CustomerUUID sellerUUID;
    private Material.MaterialUUID materialUUID;
    private int usedCapacity;
    private final Uom uom = Uom.T;
    private final int maxCapacity = 500000;
    private int wareHouseId;

    public Warehouse(Seller.CustomerUUID sellerUUID, Material.MaterialUUID materialUUID, int usedCapacity, int wareHouseId) {
        this.sellerUUID = sellerUUID;
        this.materialUUID = materialUUID;
        this.usedCapacity = usedCapacity;
        this.wareHouseId = wareHouseId;
    }

    public Seller.CustomerUUID getSellerUUID() {
        return sellerUUID;
    }

    public Material.MaterialUUID getMaterialUUID() {
        return materialUUID;
    }

    public int getUsedCapacity() {
        return usedCapacity;
    }

    public Uom getUom() {
        return uom;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getWareHouseId() {
        return wareHouseId;
    }
}
