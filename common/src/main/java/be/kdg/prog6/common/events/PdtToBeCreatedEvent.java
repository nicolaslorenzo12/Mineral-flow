package be.kdg.prog6.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record PdtToBeCreatedEvent(int warehouseNumber, LocalDateTime timeOfDelivery, UUID appointmentUUID) {

}
