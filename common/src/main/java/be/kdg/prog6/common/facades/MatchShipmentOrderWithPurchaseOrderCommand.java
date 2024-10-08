package be.kdg.prog6.common.facades;

import java.util.UUID;

public record MatchShipmentOrderWithPurchaseOrderCommand(UUID shipmentOrderUUID) {
}
