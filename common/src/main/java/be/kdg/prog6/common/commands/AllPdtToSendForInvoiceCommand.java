package be.kdg.prog6.common.commands;

import be.kdg.prog6.common.domain.Storage;

import java.util.List;

public record AllPdtToSendForInvoiceCommand(List<Storage> allPdt) {
}
