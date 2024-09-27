package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.*;
import be.kdg.prog6.common.exception.InsufficientStockException;

public class Warehouse {

    private final int wareHouseNumber;
    private final Seller.CustomerUUID sellerUUID;
    private final MaterialType materialType;
    private final Uom uom = Uom.T;
    private final WarehouseActivityWindow warehouseActivityWindow;
    private final int maximumCapacity = 500000;

    public Warehouse(int wareHouseNumber, Seller.CustomerUUID sellerUUID, MaterialType materialType,final WarehouseActivityWindow warehouseActivityWindow) {
        this.wareHouseNumber = wareHouseNumber;
        this.sellerUUID = sellerUUID;
        this.materialType = materialType;
        this.warehouseActivityWindow = warehouseActivityWindow;
    }

    public int getWareHouseNumber() {
        return wareHouseNumber;
    }

    public WarehouseActivity addWarehouseActivity(int amountOfTons, int warehouseNumber, WarehouseAction action){

        int currentStock = calculateCurrentStock();

        if(action == WarehouseAction.DISPATCH && amountOfTons > currentStock){
            throw new InsufficientStockException("Not enough stock to dispatch " + amountOfTons + " tons.");
        }
        return warehouseActivityWindow.addWarehouseActivity(amountOfTons, warehouseNumber, action);
    }

    public int calculateCurrentStock(){

        //Delete the current stock printing at some point
        return warehouseActivityWindow.calculateCurrentStock();
    }

    public Seller.CustomerUUID getSellerUUID() {
        return sellerUUID;
    }
}
