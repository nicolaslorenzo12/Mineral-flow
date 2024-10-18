package be.kdg.prog6.boundedcontextWaterside.adapters.in.web;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.ports.in.*;
import be.kdg.prog6.common.facades.MatchShipmentOrderWithPurchaseOrderCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ShipmentOrderController {


    private final MatchShipmentOrderAndPurchaseOrderUseCase matchPurchaseAndShipmentOrderUseCase;
    private final LoadMaterialUseCase loadMaterialUseCase;
    private final InspectShipUseCase inspectShipUseCase;
    private final RefuelShipUseCase refuelShipUseCase;
    private final GetOutstandingOperationsUseCase getOutstandingPOSUseCase;

    public ShipmentOrderController(MatchShipmentOrderAndPurchaseOrderUseCase matchPurchaseAndShipmentOrderUseCase, LoadMaterialUseCase loadMaterialUseCase, InspectShipUseCase inspectShipUseCase, RefuelShipUseCase refuelShipUseCase, GetOutstandingOperationsUseCase getOutstandingPOSUseCase) {
        this.matchPurchaseAndShipmentOrderUseCase = matchPurchaseAndShipmentOrderUseCase;
        this.loadMaterialUseCase = loadMaterialUseCase;
        this.inspectShipUseCase = inspectShipUseCase;
        this.refuelShipUseCase = refuelShipUseCase;
        this.getOutstandingPOSUseCase = getOutstandingPOSUseCase;
    }

    @PostMapping("match-shipment-and-purchase-order/purchase-order/{shipmentOrderUUID}")
    public void matchPurchaseOrderAndShipmentOrder(@PathVariable UUID shipmentOrderUUID) {

        matchPurchaseAndShipmentOrderUseCase.matchPurchaseAndShipmentOrderWhenArriving
                (new MatchShipmentOrderWithPurchaseOrderCommand(shipmentOrderUUID));
    }

    @PostMapping("load-ship/shipment-order/{shipmentOrderUUID}")
    public void loadShip(@PathVariable UUID shipmentOrderUUID) {
        loadMaterialUseCase.loadMaterial(new LoadMaterialCommand(new ShipmentOrder.ShipmentOrderUUID(shipmentOrderUUID)));
    }

    @PostMapping("inspect/shipment-order/{shipmentOrderUUID}")
    public ResponseEntity<ShipmentOrder> inspect(@PathVariable UUID shipmentOrderUUID) {

        ShipmentOrder shipmentOrder = inspectShipUseCase.inspect(new
                InspectShipCommand(new ShipmentOrder.ShipmentOrderUUID(shipmentOrderUUID)));
        return ResponseEntity.ok(shipmentOrder);
    }

    @PostMapping("refuel/shipment-order/{shipmentOrderUUID}")
    public ResponseEntity<ShipmentOrder> refuel(@PathVariable UUID shipmentOrderUUID) {

        ShipmentOrder shipmentOrder = refuelShipUseCase.refuelShip(new RefuelShipCommand
                (new ShipmentOrder.ShipmentOrderUUID(shipmentOrderUUID)));
        return ResponseEntity.ok(shipmentOrder);
    }


    @GetMapping("pos/finished")
    public ResponseEntity<List<ShipmentOrder>> getFinishedPOS() {

        List<ShipmentOrder> shipmentOrders = getOutstandingPOSUseCase.getFinishedPOS();
        return ResponseEntity.ok(shipmentOrders);
    }

    @GetMapping("ios/outstanding")
    public ResponseEntity<List<ShipmentOrder>> getOutstandingIOS() {

        List<ShipmentOrder> shipmentOrders = getOutstandingPOSUseCase.getOutstandingIOS();
        return ResponseEntity.ok(shipmentOrders);
    }

    @GetMapping("bos/outstanding")
    public ResponseEntity<List<ShipmentOrder>> getOutstandingBOS() {

        List<ShipmentOrder> shipmentOrders = getOutstandingPOSUseCase.getOutstandingBOS();
        return ResponseEntity.ok(shipmentOrders);
    }

    @GetMapping("to-load-pos/outstanding")
    public ResponseEntity<List<ShipmentOrder>> getOutstandingToLoadPOS() {

        List<ShipmentOrder> shipmentOrders = getOutstandingPOSUseCase.getToLoadPOS();
        return ResponseEntity.ok(shipmentOrders);
    }

}
