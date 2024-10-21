package be.kdg.prog6.boundedcontextInvoice.ports.in;

import be.kdg.prog6.boundedcontextInvoice.domain.Invoice;

public interface CreateInvoiceUseCase {

    Invoice createInvoice(CreateInvoiceCommand createInvoiceCommand);
}
