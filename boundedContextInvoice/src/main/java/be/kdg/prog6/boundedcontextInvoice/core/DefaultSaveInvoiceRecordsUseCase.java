package be.kdg.prog6.boundedcontextInvoice.core;

import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceAction;
import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceRecord;
import be.kdg.prog6.boundedcontextInvoice.domain.Warehouse;
import be.kdg.prog6.boundedcontextInvoice.ports.in.CalculateAndSaveInvoiceRecordsCommand;
import be.kdg.prog6.boundedcontextInvoice.ports.in.CalculateAndSaveInvoiceRecordsUseCase;
import be.kdg.prog6.boundedcontextInvoice.ports.out.LoadSellerPort;
import be.kdg.prog6.boundedcontextInvoice.ports.out.LoadWarehousePort;
import be.kdg.prog6.boundedcontextInvoice.ports.out.UpdateInvoiceRecordPort;
import be.kdg.prog6.common.domain.Pdt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class DefaultSaveInvoiceRecordsUseCase implements CalculateAndSaveInvoiceRecordsUseCase {

    private final List<UpdateInvoiceRecordPort> updateInvoicePorts;
    private final LoadSellerPort loadSellerPort;
    private final LoadWarehousePort loadWarehousePort;

    public DefaultSaveInvoiceRecordsUseCase(List<UpdateInvoiceRecordPort> updateInvoicePorts, LoadSellerPort loadSellerPort, LoadWarehousePort loadWarehousePort) {
        this.updateInvoicePorts = updateInvoicePorts;
        this.loadSellerPort = loadSellerPort;
        this.loadWarehousePort = loadWarehousePort;
    }


    @Override
    public void saveInvoiceRecords(CalculateAndSaveInvoiceRecordsCommand calculateAndSaveInvoiceRecordsCommand) {

        List<Pdt> allPdt = calculateAndSaveInvoiceRecordsCommand.allPdt();

        allPdt.forEach(pdt -> {

            if(!pdt.isAllTonsConsumed()) {
                Warehouse warehouse = loadWarehousePort.findWarehouseByWarehouseNumber(pdt.getWarehouseNumber())
                        .orElseThrow(() -> new NoSuchElementException("Warehouse not found"));

                int amountOfTons = pdt.getAmountOfTonsDelivered() - pdt.getAmountOfTonsConsumed();
                InvoiceRecord.InvoiceUUID invoiceUUID = new InvoiceRecord.InvoiceUUID(UUID.randomUUID());

                InvoiceRecord invoiceRecord = new InvoiceRecord(warehouse.getSellerUUID(), amountOfTons, pdt.getTimeOfDelivery().toLocalDate(),
                        LocalDate.now(), warehouse.getMaterialType(), invoiceUUID);

                updateInvoicePorts.forEach(updateInvoiceRecordPort -> updateInvoiceRecordPort.updateInvoiceRecord(invoiceRecord,
                        InvoiceAction.CREATE_INVOICE_RECORD));
            }
        });
    }
}
