package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UpdatePdtPort {

    void createPdtPort(Warehouse warehouse, LocalDateTime timeOfDelivery, UUID appointmentUUID);
}
