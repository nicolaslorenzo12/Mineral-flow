package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.*;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class Warehouse {

    private final int wareHouseNumber;
    private final Seller.CustomerUUID sellerUUID;
    private final MaterialType materialType;
    private final Uom uom = Uom.T;
    private final WarehouseActivityWindow warehouseActivityWindow;
    private List<Pdt> pdtList;

    public Warehouse(int wareHouseNumber, Seller.CustomerUUID sellerUUID, MaterialType materialType,final WarehouseActivityWindow warehouseActivityWindow) {
        this.wareHouseNumber = wareHouseNumber;
        this.sellerUUID = sellerUUID;
        this.materialType = materialType;
        this.warehouseActivityWindow = warehouseActivityWindow;
    }

    public Warehouse(int wareHouseNumber, Seller.CustomerUUID sellerUUID, MaterialType materialType,final WarehouseActivityWindow warehouseActivityWindow,
                     List<Pdt> pdtList) {
        this.wareHouseNumber = wareHouseNumber;
        this.sellerUUID = sellerUUID;
        this.materialType = materialType;
        this.warehouseActivityWindow = warehouseActivityWindow;
        this.pdtList = pdtList;
    }

    public int getWareHouseNumber() {
        return wareHouseNumber;
    }

    public void recreateWarehouseActivity(int amountOfTons, int warehouseNumber, WarehouseAction action){
        warehouseActivityWindow.addWarehouseActivity(amountOfTons, warehouseNumber, action);
    }

    public WarehouseActivity addWarehouseActivity(int amountOfTons, int warehouseNumber, WarehouseAction action){

        int currentStock = calculateAndGetCurrentStock();

        if(action == WarehouseAction.DISPATCH && amountOfTons > currentStock){
            throw new CustomException(HttpStatus.CONFLICT, "Not enough stock to dispatch " + amountOfTons + " tons.");
        }
        return warehouseActivityWindow.addWarehouseActivity(amountOfTons, warehouseNumber, action);
    }

    public int calculateAndGetCurrentStock(){

        return warehouseActivityWindow.calculateCurrentStock();
    }

    public Seller.CustomerUUID getSellerUUID() {
        return sellerUUID;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public int calculateNetWeight(int inititialWeight, int finalWeight){
        return inititialWeight - finalWeight;
    }

    private List<Pdt> getPdtList(){
        return pdtList;
    }
}
