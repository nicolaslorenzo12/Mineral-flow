package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.Material;
public record WarehouseActivity(int amountOfTons,Material.MaterialUUID materialUUID, WarehouseAction warehouseAction) {
}
