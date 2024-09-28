package be.kdg.prog6.boundedcontextLandside.domain;

import be.kdg.prog6.common.domain.*;

public class Warehouse {

    private final int wareHouseNumber;
    private final Seller.CustomerUUID sellerUUID;
    private final MaterialType materialType;
    private final int maximumCapacity = 500000;
    private int currentStockStorage;
    private double currentStockPercentage;

    public Warehouse(int wareHouseNumber, Seller.CustomerUUID sellerUUID, MaterialType materialType, int utilizationCapacity, double utilizationPercentage) {
        this.wareHouseNumber = wareHouseNumber;
        this.sellerUUID = sellerUUID;
        this.materialType = materialType;
        this.currentStockStorage = utilizationCapacity;
        this.currentStockPercentage = utilizationPercentage;
    }

    public Warehouse(int wareHouseNumber, Seller.CustomerUUID sellerUUID, int utilizationCapacity, MaterialType materialType) {
        this.wareHouseNumber = wareHouseNumber;
        this.sellerUUID = sellerUUID;
        this.currentStockStorage = utilizationCapacity;
        this.materialType = materialType;
    }

    public int getWareHouseNumber() {
        return wareHouseNumber;
    }

    public Seller.CustomerUUID getSellerUUID() {
        return sellerUUID;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public int getCurrentStockStorage() {
        return currentStockStorage;
    }

    public void setCurrentStockStorage(int currentStockStorage) {
        this.currentStockStorage = currentStockStorage;
    }

    public double getCurrentStockPercentage() {
        return this.currentStockPercentage = (double) this.currentStockStorage / this.maximumCapacity * 100;
    }

    public void setCurrentStockPercentage(double currentStockPercentage) {
        this.currentStockPercentage = currentStockPercentage;
    }

    public void modifyStock(final int amountOfTons, final WarehouseAction warehouseAction){

        switch (warehouseAction){
            case RECEIVE -> setCurrentStockStorage(this.currentStockStorage + amountOfTons);
            case DISPATCH -> setCurrentStockStorage(this.currentStockStorage - amountOfTons);
        }
    }

}
