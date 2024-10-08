package be.kdg.prog6.boundedcontextWaterside.adapters.in.web;

import be.kdg.prog6.boundedcontextWaterside.domain.dto.PurchaseOrderLoadedDto;
import be.kdg.prog6.boundedcontextWaterside.domain.dto.ShipmentAndPurchaseOrderMatchedDto;
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
    public ResponseEntity<ShipmentAndPurchaseOrderMatchedDto> makeAppointment(@PathVariable String purchaseNumber) {

        //ShipmentAndPurchaseOrderMatchedDto shipmentAndPurchaseOrderMatchedDto = matchPurchaseAndShipmentOrderUseCase.matchPurchaseAndShipmentOrderWhenArriving(new MatchPurchaseAndShipmentOrderCommand(purchaseNumber));
        //return ResponseEntity.ok(shipmentAndPurchaseOrderMatchedDto);
        return null;
    }

    @PostMapping("load-ship/purchase-order/{poNumber}")
    public ResponseEntity<PurchaseOrderLoadedDto> loadShip(@PathVariable String poNumber) {

        //PurchaseOrderLoadedDto purchaseOrderLoadedDto = loadShipWithMaterialUseCase.loadShipWithMaterial(new LoadShipWithMaterialCommand(poNumber));
        //return ResponseEntity.ok(purchaseOrderLoadedDto);
        return null;
    }
}
