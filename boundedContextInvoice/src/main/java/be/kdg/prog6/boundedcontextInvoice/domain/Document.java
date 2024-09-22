package be.kdg.prog6.boundedcontextInvoice.domain;

import java.util.List;
import java.util.UUID;

public class Document {

    private List<Invoice.InvoiceUUID> invoicesUUID;
    private DocumentUUID documentUUID;

    public record DocumentUUID(UUID uuid){

    }
}
