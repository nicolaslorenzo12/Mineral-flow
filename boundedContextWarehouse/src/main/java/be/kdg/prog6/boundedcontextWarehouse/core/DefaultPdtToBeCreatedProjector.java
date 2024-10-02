package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.PdtToBeCreatedProjector;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdatePdtPort;
import be.kdg.prog6.common.exception.CustomException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DefaultPdtToBeCreatedProjector implements PdtToBeCreatedProjector {

    private final UpdatePdtPort updatePdtPort;
    private final LoadWarehousePort loadWarehousePort;

    public DefaultPdtToBeCreatedProjector(UpdatePdtPort updatePdtPort, LoadWarehousePort loadWarehousePort) {
        this.updatePdtPort = updatePdtPort;
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    @Transactional
    public void createPdt(int warehouseNumber, LocalDateTime timeOfDelivery) {

        Warehouse warehouse = loadWarehousePort.loadWarehouseByWarehouseNumber(warehouseNumber)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Warehouse was not found"));

        updatePdtPort.createPdtPort(warehouse, timeOfDelivery);
    }
}
