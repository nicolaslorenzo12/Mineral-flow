package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Warehouse {

    private int wareHouseNumber;
    private Seller.CustomerUUID sellerUUID;
    private MaterialType materialType;
    private final UnitOfMeasurement uom = UnitOfMeasurement.T;
    private WarehouseActivityWindow warehouseActivityWindow;
    private List<Storage> storageList;
    private final int maximumCapacity = 500000;

    public Warehouse(int wareHouseNumber, Seller.CustomerUUID sellerUUID, MaterialType materialType,final WarehouseActivityWindow warehouseActivityWindow) {
        this.wareHouseNumber = wareHouseNumber;
        this.sellerUUID = sellerUUID;
        this.materialType = materialType;
        this.warehouseActivityWindow = warehouseActivityWindow;
        this.storageList = new ArrayList<>();
    }

    public Warehouse (WarehouseActivityWindow warehouseActivityWindow) {
        this.warehouseActivityWindow = warehouseActivityWindow;
    }

    public int getWareHouseNumber() {
        return wareHouseNumber;
    }

    public void recreateWarehouseActivity(int amountOfTons, WarehouseAction action){
        warehouseActivityWindow.addWarehouseActivity(amountOfTons, this.wareHouseNumber, action);
    }

    public WarehouseActivity addWarehouseActivity(int amountOfTons, WarehouseAction action){

        checkIfThereAreProblemsWithPossibleNewActivityForWarehouse(amountOfTons, action);

        if(action == WarehouseAction.DISPATCH){
            this.removeTonsFromOldestPdts(amountOfTons);
        }

        return warehouseActivityWindow.addWarehouseActivity(amountOfTons, this.wareHouseNumber, action);
    }


    private void checkIfThereAreProblemsWithPossibleNewActivityForWarehouse(int amountOfTons, WarehouseAction action){

        int currentStock = calculateAndGetCurrentStock();

        if(amountOfTons < 0){
            throw new IllegalArgumentException("Amount of tons cannot be negative");
        }

        if(action == WarehouseAction.DISPATCH && amountOfTons > currentStock){
            throw new IllegalArgumentException("Not enough stock to dispatch " + amountOfTons + " tons.");
        }
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

    public List<Storage> getStorageList(){
        return storageList;
    }

    public void addPdt(Storage pdt){
        storageList.add(pdt);
    }


    public void setStorageList(List<Storage> storageList) {
        this.storageList = storageList;
    }

    public WarehouseActivityWindow getWarehouseActivityWindow() {
        return warehouseActivityWindow;
    }

    private void removeTonsFromOldestPdts(int balanceOfAmountOfTonsToDispatch){

        List<Storage> filteredAndSortedPdtList = storageList.stream()
                .filter(storage -> !storage.isAllTonsConsumed())
                .sorted(Comparator.comparing(Storage::getTimeOfDelivery))
                .toList();

        int pdtIndexInFilteredAndSortedPdtList = 0;

        while(balanceOfAmountOfTonsToDispatch > 0){

            Storage pdt = filteredAndSortedPdtList.get(pdtIndexInFilteredAndSortedPdtList);
            balanceOfAmountOfTonsToDispatch = pdt.removeTonsFromPdt(balanceOfAmountOfTonsToDispatch);

            pdtIndexInFilteredAndSortedPdtList++;
        }
    }



    public double getWarehousePercentageUtilization(){
         return (double) (calculateAndGetCurrentStock() * 100) / maximumCapacity;
    }
}
