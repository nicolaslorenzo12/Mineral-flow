package be.kdg.prog6.boundedcontextLandside.domain;

import be.kdg.prog6.common.domain.*;

public class Warehouse {

    private final int wareHouseNumber;
    private final Seller.CustomerUUID sellerUUID;
    private final MaterialType materialType;
    private final int maximumCapacity = 500000;
    private int currentStockStorage;

    public Warehouse(int wareHouseNumber, Seller.CustomerUUID sellerUUID, MaterialType materialType) {
        this.wareHouseNumber = wareHouseNumber;
        this.sellerUUID = sellerUUID;
        this.materialType = materialType;
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

    public int getCurrentStockStorage() {
        return currentStockStorage;
    }

    public void checkIfMaximumStockPercentageExceeded() {

        double currentStockPercentage = (double) this.currentStockStorage / this.maximumCapacity * 100;

        if(currentStockPercentage >= 80){
            throw new IllegalArgumentException("Warehouse is at or above 80% capacity. Cannot schedule an appointment.");
        }
    }



}
