package be.kdg.prog6.boundedcontextInvoice.ports.in;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Pdt;

import java.util.List;

public record SaveInvoiceRecordsCommand(List<Pdt> allPdt) {
}
