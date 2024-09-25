package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.domain.Uom;
import be.kdg.prog6.common.exception.InsufficientStockException;

import java.time.LocalDateTime;

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

    public int getWareHouseNumber() {
        return wareHouseNumber;
    }

    public WarehouseActivity addWarehouseActivity(int amountOfTons, int warehouseNumber, WarehouseAction action){

        int currentStock = warehouseActivityWindow.calculateCurrentStock();

        if(action == WarehouseAction.DISPATCH && amountOfTons > currentStock){
            throw new InsufficientStockException("Not enough stock to dispatch " + amountOfTons + " tons.");
        }
        return warehouseActivityWindow.addWarehouseActivity(amountOfTons, warehouseNumber, action);
    }

    public int calculateCurrentStock(){

        //Delete the current stock printing at some point
        int currentStock = warehouseActivityWindow.calculateCurrentStock();
        System.out.println("The current stock of warehouse number " + wareHouseNumber + " is " + currentStock + uom);
        return currentStock;
    }
}
