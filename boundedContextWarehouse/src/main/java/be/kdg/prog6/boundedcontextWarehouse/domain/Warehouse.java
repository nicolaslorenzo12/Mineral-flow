package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.domain.Uom;

public class Warehouse {

    private int wareHouseNumber;
    private Seller.CustomerUUID sellerUUID;
    private Material.MaterialUUID materialUUID;
    private final Uom uom = Uom.T;
    private WarehouseActivityWindow warehouseActivityWindow;
    private final int maximumCapacity = 500000;

    public Warehouse(int wareHouseNumber, Seller.CustomerUUID sellerUUID, Material.MaterialUUID materialUUID, WarehouseActivityWindow warehouseActivityWindow) {
        this.wareHouseNumber = wareHouseNumber;
        this.sellerUUID = sellerUUID;
        this.materialUUID = materialUUID;
        this.warehouseActivityWindow = warehouseActivityWindow;
    }

    //    public WarehouseActivity receiveMaterial(int amountOfTons, UUID sellerUUID, int warehouseNumber, WarehouseAction warehouseAction,
//                                             UUID materialUUId){
//        warehouseActivityWindow.addWarehouseActivity(w);
//        return warehouseActivity;
//    }

    public int getWareHouseNumber() {
        return wareHouseNumber;
    }

    public WarehouseActivity addWarehouseActivity(int amountOfTons, Seller.CustomerUUID sellerId, Material.MaterialUUID materialUUID , int warehouseNumber){
        return warehouseActivityWindow.addWarehouseActivity(amountOfTons, sellerId, materialUUID, warehouseNumber);
    }
}
