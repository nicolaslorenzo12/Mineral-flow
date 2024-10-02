package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import java.time.LocalDateTime;

public interface PdtToBeCreatedProjector {

    void createPdt(int warehouseNumber, LocalDateTime timeOfDelivery);
}
