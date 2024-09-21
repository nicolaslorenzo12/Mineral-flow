package be.kdg.prog6.boundedcontextInvoice.domain;

import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Seller;

import java.time.LocalDateTime;

public class StorageInvoice extends Invoice{

    public LocalDateTime dateOfArrival;
    public int amountOfTons;


    public StorageInvoice(Seller.CustomerUUID sellerUuid, InvoiceUUID invoiceUUID) {
        super(sellerUuid, invoiceUUID);
    }
}
