package be.kdg.prog6.common.events;

import java.time.LocalDate;
import java.util.UUID;

public record MaterialLoadedEvent(UUID shipmentOrderUUID, LocalDate actualDepartureDate) {
}
