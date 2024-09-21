package be.kdg.prog6.boundedcontextInvoice.domain;

import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.domain.Uom;

import java.time.LocalDate;
import java.util.UUID;

public class Invoice {

    public Seller.CustomerUUID sellerUuid;
    public InvoiceUUID invoiceUUID;
    private final Uom uom = Uom.T;

    public record InvoiceUUID(UUID uuid){

    }

    public Invoice(Seller.CustomerUUID sellerUuid, InvoiceUUID invoiceUUID) {
        this.sellerUuid = sellerUuid;
        this.invoiceUUID = invoiceUUID;
    }

    public Uom getUom() {
        return uom;
    }
}
