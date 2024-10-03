package be.kdg.prog6.common.events;

import be.kdg.prog6.common.domain.MaterialType;

import java.util.UUID;

public record MaterialDispatchedEvent(UUID sellerUUID, UUID buyerUUID, MaterialType materialType, int quantity) {
}
