package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import java.time.LocalDateTime;
import java.util.UUID;

public interface PdtToBeCreatedProjector {

    void createPdt(int warehouseNumber, LocalDateTime timeOfDelivery, UUID appointmentUUID);
}
