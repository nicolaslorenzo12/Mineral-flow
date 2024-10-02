package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;

import java.time.LocalDateTime;

public interface UpdatePdtPort {

    void createPdtPort(Warehouse warehouse, LocalDateTime timeOfDelivery);
}
