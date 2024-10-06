package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.domain.UpdateWarehouseAction;
import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UpdateWarehousePort {

    void updateWarehouse(Warehouse warehouse, UpdateWarehouseAction updateWarehouseAction, UUID appointmentUUID, LocalDateTime timeOfDelivery);
}
