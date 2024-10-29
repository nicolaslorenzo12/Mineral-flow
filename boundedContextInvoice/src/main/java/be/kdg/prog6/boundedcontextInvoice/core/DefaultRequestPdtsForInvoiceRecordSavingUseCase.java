package be.kdg.prog6.boundedcontextInvoice.core;

import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceRecord;
import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceAction;
import be.kdg.prog6.boundedcontextInvoice.ports.out.UpdateInvoiceRecordPort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultRequestPdtsForInvoiceRecordSavingUseCase {

    private final List<UpdateInvoiceRecordPort> updateInvoicePorts;

    public DefaultRequestPdtsForInvoiceRecordSavingUseCase(List<UpdateInvoiceRecordPort> updateInvoicePorts) {
        this.updateInvoicePorts = updateInvoicePorts;
    }

    //@Scheduled(cron = "0 0 9 * * ?")
    @Scheduled(cron = "0 10 12 * * ?")
    public void requestPdtForInvoiceCalculation() {

        updateInvoicePorts.forEach(updateInvoiceRecordPort -> updateInvoiceRecordPort.updateInvoiceRecord
                (new InvoiceRecord(new InvoiceRecord.InvoiceUUID(UUID.randomUUID())), InvoiceAction.REQUEST_PDTS));
    }
}
