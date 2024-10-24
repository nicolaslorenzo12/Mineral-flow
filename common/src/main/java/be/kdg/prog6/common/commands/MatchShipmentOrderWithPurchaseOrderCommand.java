package be.kdg.prog6.common.commands;

import java.util.UUID;

public record MatchShipmentOrderWithPurchaseOrderCommand(UUID shipmentOrderUUID) {
}
