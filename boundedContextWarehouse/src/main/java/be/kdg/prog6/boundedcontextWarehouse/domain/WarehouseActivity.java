package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;

public record WarehouseActivity(int amountOfTons, Seller.CustomerUUID sellerId, Material.MaterialUUID materialUUID , int warehouseNumber) {
    @Override
    public int amountOfTons() {
        return amountOfTons;
    }

    @Override
    public Seller.CustomerUUID sellerId() {
        return sellerId;
    }

    @Override
    public Material.MaterialUUID materialUUID() {
        return materialUUID;
    }

    @Override
    public int warehouseNumber() {
        return warehouseNumber;
    }
}
