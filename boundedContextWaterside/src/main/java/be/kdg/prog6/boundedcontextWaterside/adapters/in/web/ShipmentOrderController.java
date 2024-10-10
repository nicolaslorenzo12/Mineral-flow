package be.kdg.prog6.boundedcontextWaterside.adapters.in.web;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.dto.PurchaseOrderLoadedDto;
import be.kdg.prog6.boundedcontextWaterside.ports.in.InspectShipUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.in.LoadMaterialCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.LoadMaterialUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.in.MatchShipmentOrderAndPurchaseOrderUseCase;
import be.kdg.prog6.common.facades.MatchShipmentOrderWithPurchaseOrderCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ShipmentOrderController {


    private final MatchShipmentOrderAndPurchaseOrderUseCase matchPurchaseAndShipmentOrderUseCase;
    private final LoadMaterialUseCase loadMaterialUseCase;
    private final InspectShipUseCase inspectShipUseCase;

    public ShipmentOrderController(MatchShipmentOrderAndPurchaseOrderUseCase matchPurchaseAndShipmentOrderUseCase, LoadMaterialUseCase loadMaterialUseCase, InspectShipUseCase inspectShipUseCase) {
        this.matchPurchaseAndShipmentOrderUseCase = matchPurchaseAndShipmentOrderUseCase;
        this.loadMaterialUseCase = loadMaterialUseCase;
        this.inspectShipUseCase = inspectShipUseCase;
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

        ShipmentOrder shipmentOrder = inspectShipUseCase.inspect(new ShipmentOrder.ShipmentOrderUUID(shipmentOrderUUID));
        return ResponseEntity.ok(shipmentOrder);
    }
}
