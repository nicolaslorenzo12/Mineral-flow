package be.kdg.prog6.common.events;

import java.time.LocalDate;
import java.util.UUID;

public record ShipmentOrderAndPurchaseOrderMatchedEvent(UUID shipmentOrderUUID, LocalDate actualArrivalDate) {
}
