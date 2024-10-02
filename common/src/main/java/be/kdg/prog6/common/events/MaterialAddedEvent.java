package be.kdg.prog6.common.events;

import java.util.UUID;

public record MaterialAddedEvent(int initialWeight, int finalWeight, int warehouseNumber, UUID appointmentUUID) {
}
