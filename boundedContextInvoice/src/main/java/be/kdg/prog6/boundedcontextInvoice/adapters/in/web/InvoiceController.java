package be.kdg.prog6.boundedcontextInvoice.adapters.in.web;

import be.kdg.prog6.boundedcontextInvoice.domain.dto.Invoice;
import be.kdg.prog6.boundedcontextInvoice.ports.in.CreateInvoiceCommand;
import be.kdg.prog6.boundedcontextInvoice.ports.in.CreateInvoiceUseCase;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.facades.MatchShipmentOrderWithPurchaseOrderCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class InvoiceController {


    private final CreateInvoiceUseCase createInvoiceUseCase;

    public InvoiceController(CreateInvoiceUseCase createInvoiceUseCase) {
        this.createInvoiceUseCase = createInvoiceUseCase;
    }

    @PostMapping("invoice/seller/{sellerUUID}")
    public ResponseEntity<Invoice> scanTruckForAppointment(@PathVariable UUID sellerUUID){


        Invoice invoice = createInvoiceUseCase.createInvoice(new CreateInvoiceCommand(sellerUUID));
        return ResponseEntity.ok(invoice);
    }
}
