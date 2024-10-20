package be.kdg.prog6.boundedcontextInvoice.ports.in;

import java.util.UUID;

public record CreateInvoiceCommand(UUID sellerUUID) {
}
