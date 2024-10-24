package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadMaterialPort;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdateInvoicePort;
import be.kdg.prog6.common.domain.Storage;
import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.PdtToBeRequestedForInvoiceUseCase;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultPdtToBeRequestedForInvoiceUseCase implements PdtToBeRequestedForInvoiceUseCase {

    private final LoadWarehousePort loadWarehousePort;
    private final LoadMaterialPort loadMaterialPort;
    private final UpdateInvoicePort updateInvoicePort;

    public DefaultPdtToBeRequestedForInvoiceUseCase(LoadWarehousePort loadWarehousePort, LoadMaterialPort loadMaterialPort, UpdateInvoicePort updateInvoicePort) {
        this.loadWarehousePort = loadWarehousePort;
        this.loadMaterialPort = loadMaterialPort;
        this.updateInvoicePort = updateInvoicePort;
    }

    @Override
    @Transactional
    public void requestAllPdt() {

        List<Warehouse> allWarehouses = loadWarehousePort.loadAllWarehouses();
        List<Storage> allPdt = new ArrayList<>();

        allWarehouses.forEach(warehouse -> allPdt.addAll(warehouse.getStorageList()));

        updateInvoicePort.sendAllPdtForBillingInInvoice(allPdt);

    }

}
