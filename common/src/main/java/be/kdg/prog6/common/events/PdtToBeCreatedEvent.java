package be.kdg.prog6.common.events;

import java.time.LocalDateTime;

public record PdtToBeCreatedEvent(int warehouseNumber, LocalDateTime timeOfDelivery) {

}
