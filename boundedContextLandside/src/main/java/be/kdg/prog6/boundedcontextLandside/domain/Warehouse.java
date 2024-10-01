package be.kdg.prog6.boundedcontextLandside.domain;

import be.kdg.prog6.common.domain.*;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class Warehouse {

    private final int wareHouseNumber;
    private final Seller.CustomerUUID sellerUUID;
    private final MaterialType materialType;
    private final int maximumCapacity = 500000;
    private int currentStockStorage;

    public Warehouse(int wareHouseNumber, Seller.CustomerUUID sellerUUID, MaterialType materialType, int utilizationCapacity) {
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

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public int getCurrentStockStorage() {
        return currentStockStorage;
    }

    public void setCurrentStockStorage(int currentStockStorage) {
        this.currentStockStorage = currentStockStorage;
    }

    public void checkIfMaximumStockPercentageExceeded() {

        double currentStockPercentage = (double) this.currentStockStorage / this.maximumCapacity * 100;

        if(currentStockPercentage >= 80){
            throw new CustomException(HttpStatus.CONFLICT, "Warehouse is at or above 80% capacity. Cannot schedule an appointment.");
        }
    }
    public int modifyStock(final int amountOfTons, final WarehouseAction warehouseAction){

        switch (warehouseAction){
            case RECEIVE -> setCurrentStockStorage(this.currentStockStorage + amountOfTons);
            case DISPATCH -> setCurrentStockStorage(this.currentStockStorage - amountOfTons);
        }

        return getCurrentStockStorage();
    }

}
