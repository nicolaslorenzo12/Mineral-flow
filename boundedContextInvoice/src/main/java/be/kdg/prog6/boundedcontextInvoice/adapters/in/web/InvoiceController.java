package be.kdg.prog6.boundedcontextInvoice.adapters.in.web;

import be.kdg.prog6.common.facades.MatchShipmentOrderWithPurchaseOrderCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class InvoiceController {

//    private final RequestPdtsForInvoiceCalculationUseCase requestPdtsForInvoiceCalculationUseCase;
//
//    public InvoiceController(RequestPdtsForInvoiceCalculationUseCase requestPdtsForInvoiceCalculationUseCase) {
//        this.requestPdtsForInvoiceCalculationUseCase = requestPdtsForInvoiceCalculationUseCase;
//    }
//
//    @PostMapping("invoice/seller/{sellerUUID}}")
//    public void matchPurchaseOrderAndShipmentOrder(@PathVariable UUID sellerUUID) {
//
//        matchPurchaseAndShipmentOrderUseCase.matchPurchaseAndShipmentOrderWhenArriving
//                (new MatchShipmentOrderWithPurchaseOrderCommand(shipmentOrderUUID));
//    }
}
