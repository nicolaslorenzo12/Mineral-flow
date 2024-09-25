package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseAction;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;

import java.time.LocalDateTime;

public record AddMaterialCommand(int amountOfTons, Seller.CustomerUUID sellerId, Material.MaterialUUID materialUUID ,
                                 int warehouseNumber, WarehouseAction action) {

}

