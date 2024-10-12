package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.*;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Comparator;
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
        this.pdtList = new ArrayList<>();
    }


    public int getWareHouseNumber() {
        return wareHouseNumber;
    }

    public void recreateWarehouseActivity(int amountOfTons, WarehouseAction action){
        warehouseActivityWindow.addWarehouseActivity(amountOfTons, this.wareHouseNumber, action);
    }

    public WarehouseActivity addWarehouseActivity(int amountOfTons, WarehouseAction action){

        int currentStock = calculateAndGetCurrentStock();

        if(action == WarehouseAction.DISPATCH && amountOfTons > currentStock){
            throw new CustomException(HttpStatus.CONFLICT, "Not enough stock to dispatch " + amountOfTons + " tons.");
        }
        return warehouseActivityWindow.addWarehouseActivity(amountOfTons, this.wareHouseNumber, action);
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

    public List<Pdt> getPdtList(){
        return pdtList;
    }

    public void addPdt(Pdt pdt){
        pdtList.add(pdt);
    }

    public WarehouseActivityWindow getWarehouseActivityWindow() {
        return warehouseActivityWindow;
    }

    public Warehouse removeTonsFromOldestPdts(int amountOfTonsToDispatch){

        pdtList.sort(Comparator.comparing(Pdt::getTimeOfDelivery));

        int x = 0;

        while(amountOfTonsToDispatch > 0){

            Pdt pdt = pdtList.get(x);

            amountOfTonsToDispatch = pdt.getAmountOfTonsDelivered() - amountOfTonsToDispatch;

            if(amountOfTonsToDispatch >= 0){
                pdt.setAmountOfTonsDelivered(amountOfTonsToDispatch);
                amountOfTonsToDispatch = 0;
            }
            else{
                pdt.setAmountOfTonsDelivered(0);
                amountOfTonsToDispatch = amountOfTonsToDispatch * (-1);
            }
            x++;
        }

        pdtList.removeIf(pdt -> pdt.getAmountOfTonsDelivered() == 0);

        return this;
    }
}
