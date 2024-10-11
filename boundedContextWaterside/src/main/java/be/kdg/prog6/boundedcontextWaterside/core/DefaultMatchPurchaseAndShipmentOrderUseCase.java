package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
import be.kdg.prog6.boundedcontextWaterside.ports.in.MatchShipmentOrderAndPurchaseOrderUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreateShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import be.kdg.prog6.common.facades.MatchShipmentOrderWithPurchaseOrderCommand;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultMatchPurchaseAndShipmentOrderUseCase implements MatchShipmentOrderAndPurchaseOrderUseCase {

    private final LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort;
    private final List<UpdateShipmentOrderPort> updateShipmentOrderPorts;

    public DefaultMatchPurchaseAndShipmentOrderUseCase(LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort,
                                                       List<UpdateShipmentOrderPort> updateShipmentOrderPorts) {
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
        this.updateShipmentOrderPorts = updateShipmentOrderPorts;
    }

    @Override
    public void matchPurchaseAndShipmentOrderWhenArriving
            (MatchShipmentOrderWithPurchaseOrderCommand matchShipmentOrderWithPurchaseOrderCommand) {

        UUID shipmentOrderUUID = matchShipmentOrderWithPurchaseOrderCommand.shipmentOrderUUID();
       ShipmentOrder shipmentOrder = loadOrCreateShipmentOrderPort.loadOrCreateShipmentOrder
               (new ShipmentOrder.ShipmentOrderUUID(shipmentOrderUUID));

       shipmentOrder.checkIfShipmentOrderHasAlreadyHadThisStatus(ShipmentStatus.ARRIVED);

       updateShipmentOrderPorts.forEach(updateShipmentOrderPort ->
               updateShipmentOrderPort.updateShipmentOrder(shipmentOrder, true));
    }
}
