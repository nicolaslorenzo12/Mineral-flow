package be.kdg.prog6.boundedcontextLandside.domain;

import be.kdg.prog6.common.domain.*;

public class Warehouse {

    private final int wareHouseNumber;
    private final Seller.CustomerUUID sellerUUID;
    private MaterialType materialType;
    private final int maximumCapacity = 500000;
    private int utilizationCapacity;
    private double utilizationPercentage;

    public Warehouse(int wareHouseNumber, Seller.CustomerUUID sellerUUID, MaterialType materialType, int utilizationCapacity, double utilizationPercentage) {
        this.wareHouseNumber = wareHouseNumber;
        this.sellerUUID = sellerUUID;
        this.materialType = materialType;
        this.utilizationCapacity = utilizationCapacity;
        this.utilizationPercentage = utilizationPercentage;
    }

    public Warehouse(int wareHouseNumber, Seller.CustomerUUID sellerUUID, int utilizationCapacity) {
        this.wareHouseNumber = wareHouseNumber;
        this.sellerUUID = sellerUUID;
        this.utilizationCapacity = utilizationCapacity;
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

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public int getUtilizationCapacity() {
        return utilizationCapacity;
    }

    public void setUtilizationCapacity(int utilizationCapacity) {
        this.utilizationCapacity = utilizationCapacity;
    }

    public double getUtilizationPercentage() {
        return utilizationPercentage;
    }

    public void setUtilizationPercentage(double utilizationPercentage) {
        this.utilizationPercentage = utilizationPercentage;
    }

    public void modifyStock(final int amountOfTons, final WarehouseAction warehouseAction){

        switch (warehouseAction){
            case RECEIVE -> setUtilizationCapacity(this.utilizationCapacity + amountOfTons);
            case DISPATCH -> setUtilizationCapacity(this.utilizationCapacity - amountOfTons);
        }
    }
}
