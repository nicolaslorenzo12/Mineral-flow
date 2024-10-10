package be.kdg.prog6.common.events;

import java.util.UUID;

public record MaterialLoadedEvent(UUID shipmentOrderUUID) {
}
