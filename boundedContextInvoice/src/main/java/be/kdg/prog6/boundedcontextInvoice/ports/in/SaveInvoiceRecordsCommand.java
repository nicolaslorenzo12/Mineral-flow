package be.kdg.prog6.boundedcontextInvoice.ports.in;

import be.kdg.prog6.common.domain.Storage;

import java.util.List;

public record SaveInvoiceRecordsCommand(List<Storage> allPdt) {
}
