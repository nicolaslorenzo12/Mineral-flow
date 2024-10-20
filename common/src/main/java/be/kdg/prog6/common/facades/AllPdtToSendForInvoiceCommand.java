package be.kdg.prog6.common.facades;

import be.kdg.prog6.common.domain.Pdt;

import java.util.List;

public record AllPdtToSendForInvoiceCommand(List<Pdt> allPdt) {
}
