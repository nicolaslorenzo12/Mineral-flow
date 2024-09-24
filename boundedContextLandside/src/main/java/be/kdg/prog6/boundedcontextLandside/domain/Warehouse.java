package be.kdg.prog6.boundedcontextLandside.domain;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;

public class Warehouse {

    private int wareHouseNumber;
    private Seller.CustomerUUID sellerUUID;
    private Material.MaterialUUID materialUUID;
    private double warehouseUtilizationPercentage;
}
