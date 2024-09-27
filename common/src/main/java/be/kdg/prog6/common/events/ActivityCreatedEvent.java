package be.kdg.prog6.common.events;

import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.domain.WarehouseAction;

public record ActivityCreatedEvent(int amountOfTons, int warehouseNumber, WarehouseAction warehouseAction, Seller.CustomerUUID sellerUuid) {
}
