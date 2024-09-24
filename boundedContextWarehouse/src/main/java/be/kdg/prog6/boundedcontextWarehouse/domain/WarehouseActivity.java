package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;

import java.util.UUID;

public record WarehouseActivity(int amountOfTons, int warehouseNumber,Material.MaterialUUID materialUUID) {
}
