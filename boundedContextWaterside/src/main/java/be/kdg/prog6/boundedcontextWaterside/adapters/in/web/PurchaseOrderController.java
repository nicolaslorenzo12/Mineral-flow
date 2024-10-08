package be.kdg.prog6.boundedcontextWaterside.adapters.in.web;

import be.kdg.prog6.boundedcontextWaterside.domain.dto.PurchaseOrderLoadedDto;
import be.kdg.prog6.boundedcontextWaterside.domain.dto.ShipmentAndPurchaseOrderMatchedDto;
import be.kdg.prog6.boundedcontextWaterside.ports.in.LoadShipWithMaterialUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.in.MatchPurchaseAndShipmentOrderUseCase;
import be.kdg.prog6.common.facades.MatchShipmentOrderWithPurchaseOrderCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PurchaseOrderController {


    private final MatchPurchaseAndShipmentOrderUseCase matchPurchaseAndShipmentOrderUseCase;

    public PurchaseOrderController(MatchPurchaseAndShipmentOrderUseCase matchPurchaseAndShipmentOrderUseCase) {
        this.matchPurchaseAndShipmentOrderUseCase = matchPurchaseAndShipmentOrderUseCase;
    }

    @PostMapping("match-shipment-and-purchase-order/purchase-order/{shipmentOrderUUID}")
    public void matchPurchaseOrderAndShipmentOrder(@PathVariable UUID shipmentOrderUUID) {

        matchPurchaseAndShipmentOrderUseCase.matchPurchaseAndShipmentOrderWhenArriving
                (new MatchShipmentOrderWithPurchaseOrderCommand(shipmentOrderUUID));
    }

    @PostMapping("load-ship/purchase-order/{poNumber}")
    public ResponseEntity<PurchaseOrderLoadedDto> loadShip(@PathVariable String poNumber) {

        //PurchaseOrderLoadedDto purchaseOrderLoadedDto = loadShipWithMaterialUseCase.loadShipWithMaterial(new LoadShipWithMaterialCommand(poNumber));
        //return ResponseEntity.ok(purchaseOrderLoadedDto);
        return null;
    }
}
