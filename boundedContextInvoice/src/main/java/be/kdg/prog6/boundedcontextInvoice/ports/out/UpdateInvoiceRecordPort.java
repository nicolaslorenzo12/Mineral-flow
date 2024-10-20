package be.kdg.prog6.boundedcontextInvoice.ports.out;

import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceRecord;
import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceAction;

public interface UpdateInvoiceRecordPort {

    void updateInvoiceRecord(InvoiceRecord invoice, InvoiceAction invoiceAction);
}
