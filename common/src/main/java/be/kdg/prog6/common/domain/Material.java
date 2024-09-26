package be.kdg.prog6.common.domain;

import java.util.UUID;

public class Material {

    private MaterialType materialType;
    private String description;
    private int storagePricePerTonPerDay;
    private int pricePerTon;

    public Material(MaterialType materialType, String description, int storagePricePerTonPerDay, int pricePerTon) {
        this.materialType = materialType;
        this.description = description;
        this.storagePricePerTonPerDay = storagePricePerTonPerDay;
        this.pricePerTon = pricePerTon;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public String getDescription() {
        return description;
    }

    public int getStoragePricePerTonPerDay() {
        return storagePricePerTonPerDay;
    }

    public int getPricePerTon() {
        return pricePerTon;
    }

}
