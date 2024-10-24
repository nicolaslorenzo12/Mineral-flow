package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
import be.kdg.prog6.boundedcontextWaterside.ports.in.MatchShipmentOrderAndPurchaseOrderUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import be.kdg.prog6.common.commands.MatchShipmentOrderWithPurchaseOrderCommand;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultMatchPurchaseAndShipmentOrderUseCase implements MatchShipmentOrderAndPurchaseOrderUseCase {

    private final LoadShipmentOrderPort loadOrCreateShipmentOrderPort;
    private final List<UpdateShipmentOrderPort> updateShipmentOrderPorts;

    public DefaultMatchPurchaseAndShipmentOrderUseCase(LoadShipmentOrderPort loadOrCreateShipmentOrderPort,
                                                       List<UpdateShipmentOrderPort> updateShipmentOrderPorts) {
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
        this.updateShipmentOrderPorts = updateShipmentOrderPorts;
    }

    @Override
    public void matchPurchaseAndShipmentOrderWhenArriving
            (MatchShipmentOrderWithPurchaseOrderCommand matchShipmentOrderWithPurchaseOrderCommand) {

        UUID shipmentOrderUUID = matchShipmentOrderWithPurchaseOrderCommand.shipmentOrderUUID();
       ShipmentOrder shipmentOrder = loadOrCreateShipmentOrderPort.loadShipmentOrder
               (new ShipmentOrder.ShipmentOrderUUID(shipmentOrderUUID));

       shipmentOrder.checkIfShipmentOrderHasAlreadyHadThisStatus(ShipmentStatus.ARRIVED);

       updateShipmentOrderPorts.forEach(updateShipmentOrderPort ->
               updateShipmentOrderPort.updateShipmentOrder(shipmentOrder, true));
    }
}
