package be.kdg.prog6.boundedcontextWaterside.adapters.in.web;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.adapters.in.web.dto.ShipmentOrderDto;
import be.kdg.prog6.boundedcontextWaterside.ports.in.*;
import be.kdg.prog6.common.commands.MatchShipmentOrderWithPurchaseOrderCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ShipmentOrderController {


    private final MatchShipmentOrderAndPurchaseOrderUseCase matchPurchaseAndShipmentOrderUseCase;
    private final LoadMaterialUseCase loadMaterialUseCase;
    private final InspectShipUseCase inspectShipUseCase;
    private final RefuelShipUseCase refuelShipUseCase;
    private final GetOutstandingOperationsUseCase getOutstandingPOSUseCase;
    private final GetShipmentOrderUseCase getShipmentOrderUseCase;

    public ShipmentOrderController(MatchShipmentOrderAndPurchaseOrderUseCase matchPurchaseAndShipmentOrderUseCase, LoadMaterialUseCase loadMaterialUseCase, InspectShipUseCase inspectShipUseCase, RefuelShipUseCase refuelShipUseCase, GetOutstandingOperationsUseCase getOutstandingPOSUseCase, GetShipmentOrderUseCase getShipmentOrderUseCase) {
        this.matchPurchaseAndShipmentOrderUseCase = matchPurchaseAndShipmentOrderUseCase;
        this.loadMaterialUseCase = loadMaterialUseCase;
        this.inspectShipUseCase = inspectShipUseCase;
        this.refuelShipUseCase = refuelShipUseCase;
        this.getOutstandingPOSUseCase = getOutstandingPOSUseCase;
        this.getShipmentOrderUseCase = getShipmentOrderUseCase;
    }

    @PostMapping("match-shipment-and-purchase-order/purchase-order/{shipmentOrderUUID}")
    public void matchPurchaseOrderAndShipmentOrder(@PathVariable UUID shipmentOrderUUID) {

        matchPurchaseAndShipmentOrderUseCase.matchPurchaseAndShipmentOrderWhenArriving
                (new MatchShipmentOrderWithPurchaseOrderCommand(shipmentOrderUUID));
    }

    @GetMapping("status-shipment-order/{shipmentOrderUUID}")
    public ResponseEntity<ShipmentOrderDto> getShipmentOrder(@PathVariable UUID shipmentOrderUUID) {

        ShipmentOrder shipmentOrder = getShipmentOrderUseCase.getShipmentOrder(new GetShipmentOrderCommand(shipmentOrderUUID));

        return ResponseEntity.ok(buildShipmentOrderDto(shipmentOrder));
    }

    @PostMapping("load-ship/shipment-order/{vesselNumber}")
    public void loadShip(@PathVariable String vesselNumber) {
        loadMaterialUseCase.loadMaterial(new LoadMaterialCommand(vesselNumber));
    }

    @PostMapping("inspect/shipment-order/{vesselNumber}")
    public ResponseEntity<ShipmentOrderDto> inspect(@PathVariable String vesselNumber) {

        ShipmentOrder shipmentOrder = inspectShipUseCase.inspect(new InspectShipCommand(vesselNumber));

        return ResponseEntity.ok(buildShipmentOrderDto(shipmentOrder));
    }

    @PostMapping("refuel/shipment-order/{vesselNumber}")
    public ResponseEntity<ShipmentOrderDto> refuel(@PathVariable String vesselNumber) {

        ShipmentOrder shipmentOrder = refuelShipUseCase.refuelShip(new RefuelShipCommand
                (vesselNumber));
        return ResponseEntity.ok(buildShipmentOrderDto(shipmentOrder));
    }


    @GetMapping("pos/finished")
    public ResponseEntity<List<ShipmentOrderDto>> getFinishedPOS() {

        List<ShipmentOrder> shipmentOrders = getOutstandingPOSUseCase.getFinishedPOS();
        return ResponseEntity.ok(createShipmentOrderDtoList(shipmentOrders));
    }

    @GetMapping("ios/outstanding")
    public ResponseEntity<List<ShipmentOrderDto>> getOutstandingIOS() {

        List<ShipmentOrder> shipmentOrders = getOutstandingPOSUseCase.getOutstandingIOS();
        return ResponseEntity.ok(createShipmentOrderDtoList(shipmentOrders));
    }

    @GetMapping("bos/outstanding")
    public ResponseEntity<List<ShipmentOrderDto>> getOutstandingBOS() {

        List<ShipmentOrder> shipmentOrders = getOutstandingPOSUseCase.getOutstandingBOS();
        return ResponseEntity.ok(createShipmentOrderDtoList(shipmentOrders));
    }

    @GetMapping("to-load-pos/outstanding")
    public ResponseEntity<List<ShipmentOrderDto>> getOutstandingToLoadPOS() {

        List<ShipmentOrder> shipmentOrders = getOutstandingPOSUseCase.getToLoadPOS();
        return ResponseEntity.ok(createShipmentOrderDtoList(shipmentOrders));
    }

    private List<ShipmentOrderDto> createShipmentOrderDtoList(List<ShipmentOrder> shipmentOrders) {
        return shipmentOrders.stream()
                .map(this::buildShipmentOrderDto)
                .collect(Collectors.toList());
    }

    private ShipmentOrderDto buildShipmentOrderDto(ShipmentOrder shipmentOrder) {

        return new ShipmentOrderDto(
                shipmentOrder.getVesselNumber(),
                shipmentOrder.getShipmentStatus(),
                shipmentOrder.getActualArrivalDate()
        );
    }

}
