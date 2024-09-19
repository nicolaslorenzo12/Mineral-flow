package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.domain.Uom;

import java.util.UUID;

public class Warehouse {

    private Seller seller;
    private Material material;
    private int usedCapacity;
    private final Uom uom = Uom.T;
    private final int maxCapacity = 500000;
    private int wareHouseId;

    public Warehouse(Seller seller, Material material, int usedCapacity, int wareHouseId) {
        this.seller = seller;
        this.material = material;
        this.usedCapacity = usedCapacity;
        this.wareHouseId = wareHouseId;
    }

    public Seller getSeller() {
        return seller;
    }

    public Material getMaterial() {
        return material;
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
