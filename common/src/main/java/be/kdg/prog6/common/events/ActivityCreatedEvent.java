package be.kdg.prog6.common.events;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;

import java.util.UUID;

public record ActivityCreatedEvent(int amountOfTons, int warehouseNumber, Seller.CustomerUUID sellerUuid, MaterialType materialType, UUID appointmentUUID) {
}
