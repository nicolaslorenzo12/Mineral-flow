package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Warehouse {

    private final int wareHouseNumber;
    private final Seller.CustomerUUID sellerUUID;
    private final MaterialType materialType;
    private final UnitOfMeasurement uom = UnitOfMeasurement.T;
    private final WarehouseActivityWindow warehouseActivityWindow;
    private List<Pdt> pdtList;
    private final int maximumCapacity = 500000;

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
            throw new IllegalArgumentException("Not enough stock to dispatch " + amountOfTons + " tons.");
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

    public Warehouse removeTonsFromOldestPdts(int balanceOfAmountOfTonsToDispatch){

        List<Pdt> filteredAndSortedPdtList = pdtList.stream()
                .filter(pdt -> !pdt.isAllTonsConsumed())
                .sorted(Comparator.comparing(Pdt::getTimeOfDelivery))
                .toList();

        int pdtIndexInFilteredAndSortedPdtList = 0;

        while(balanceOfAmountOfTonsToDispatch > 0){

            Pdt pdt = filteredAndSortedPdtList.get(pdtIndexInFilteredAndSortedPdtList);
            int amountNeeded = pdt.getAmountOfTonsConsumed() + balanceOfAmountOfTonsToDispatch;
            balanceOfAmountOfTonsToDispatch = pdt.removeTonsFromPdt(amountNeeded);

            pdtIndexInFilteredAndSortedPdtList++;
        }

        return this;
    }


    public double getWarehousePercentageUtilization(){
         return (double) (calculateAndGetCurrentStock() * 100) / maximumCapacity;
    }
}
