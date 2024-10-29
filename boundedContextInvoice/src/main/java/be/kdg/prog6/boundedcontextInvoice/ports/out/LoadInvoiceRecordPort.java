package be.kdg.prog6.boundedcontextInvoice.ports.out;

import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceRecord;
import be.kdg.prog6.common.domain.Seller;

import java.time.LocalDate;
import java.util.List;

public interface LoadInvoiceRecordPort {

    List<InvoiceRecord> loadInvoiceRecordsBySellerUUIDAndDate(Seller.CustomerUUID sellerUUID, LocalDate date);
}
