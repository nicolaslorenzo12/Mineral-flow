package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.common.domain.Storage;
import be.kdg.prog6.boundedcontextWarehouse.domain.UpdateWarehouseAction;
import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.PdtToBeCreatedProjector;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdateWarehousePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class DefaultPdtToBeCreatedProjector implements PdtToBeCreatedProjector {
    private final List<UpdateWarehousePort> updateWarehousePorts;
    private final LoadWarehousePort loadWarehousePort;

    public DefaultPdtToBeCreatedProjector(List<UpdateWarehousePort> updateWarehousePorts, LoadWarehousePort loadWarehousePort) {
        this.updateWarehousePorts = updateWarehousePorts;
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    @Transactional
    public void createPdt(int warehouseNumber, LocalDateTime timeOfDelivery, UUID appointmentUUID) {

        Warehouse warehouse = loadWarehousePort.loadWarehouseByWarehouseNumber(warehouseNumber)
                .orElseThrow(() -> new NoSuchElementException(("Warehouse not found exception")));

        warehouse.addPdt(new Storage(warehouseNumber, timeOfDelivery, 0, new Storage.PdtUUID(appointmentUUID), 0, false));
        updateWarehousePorts.forEach(updateWarehousePort -> updateWarehousePort.updateWarehouse(UpdateWarehouseAction.CREATE_PDT, warehouse,
                null, null));
    }
}
