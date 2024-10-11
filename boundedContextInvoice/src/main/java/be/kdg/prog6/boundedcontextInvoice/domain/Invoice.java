package be.kdg.prog6.boundedcontextInvoice.domain;

import be.kdg.prog6.common.domain.*;

import java.time.LocalDate;
import java.util.UUID;

public class Invoice {

    public Seller.CustomerUUID sellerUuid;
    private final Uom uom = Uom.T;
    private double amount;
    private LocalDate invoiceDate;
    public InvoiceUUID invoiceUUID;

    public record InvoiceUUID(UUID uuid){

    }




}
