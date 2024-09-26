package be.kdg.prog6.boundedcontextLandside.domain;

import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;

public class Warehouse {

    private final int wareHouseNumber;
    private final Seller.CustomerUUID sellerUUID;
    private Material.MaterialUUID materialUUID;
    private final int maximumCapacity = 500000;
    private int utilizationCapacity;
    private double utilizationPercentage;

    public Warehouse(int wareHouseNumber, Seller.CustomerUUID sellerUUID, Material.MaterialUUID materialUUID, int utilizationCapacity, double utilizationPercentage) {
        this.wareHouseNumber = wareHouseNumber;
        this.sellerUUID = sellerUUID;
        this.materialUUID = materialUUID;
        this.utilizationCapacity = utilizationCapacity;
        this.utilizationPercentage = utilizationPercentage;
    }

    public int getWareHouseNumber() {
        return wareHouseNumber;
    }

    public Seller.CustomerUUID getSellerUUID() {
        return sellerUUID;
    }

    public Material.MaterialUUID getMaterialUUID() {
        return materialUUID;
    }

    public void setMaterialUUID(Material.MaterialUUID materialUUID) {
        this.materialUUID = materialUUID;
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
}
