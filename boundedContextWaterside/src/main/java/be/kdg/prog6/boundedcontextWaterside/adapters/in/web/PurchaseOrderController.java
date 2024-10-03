package be.kdg.prog6.boundedcontextWaterside.adapters.in.web;

import be.kdg.prog6.boundedcontextWaterside.ports.in.LoadShipWithMaterialCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.LoadShipWithMaterialUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.in.MatchPurchaseAndShipmentOrderCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.MatchPurchaseAndShipmentOrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseOrderController {

    private final MatchPurchaseAndShipmentOrderUseCase matchPurchaseAndShipmentOrderUseCase;
    private final LoadShipWithMaterialUseCase loadShipWithMaterialUseCase;

    public PurchaseOrderController(MatchPurchaseAndShipmentOrderUseCase matchPurchaseAndShipmentOrderUseCase, LoadShipWithMaterialUseCase loadShipWithMaterialUseCase) {
        this.matchPurchaseAndShipmentOrderUseCase = matchPurchaseAndShipmentOrderUseCase;
        this.loadShipWithMaterialUseCase = loadShipWithMaterialUseCase;
    }

    @PostMapping("match-shipment-and-purchase-order/purchase-order/{purchaseNumber}")
    public ResponseEntity<String> makeAppointment(@PathVariable String purchaseNumber) {

        matchPurchaseAndShipmentOrderUseCase.matchPurchaseAndShipmentOrder(new MatchPurchaseAndShipmentOrderCommand(purchaseNumber));
        return ResponseEntity.ok("The shipment and purchase order match.");
    }

    @PostMapping("load-ship/purchase-order/{poNumber}")
    public ResponseEntity<String> loadShip(@PathVariable String poNumber) {

        loadShipWithMaterialUseCase.loadShipWithMaterial(new LoadShipWithMaterialCommand(poNumber));
        return ResponseEntity.ok("The shipment and purchase order match.");
    }
}
