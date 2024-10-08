package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
import be.kdg.prog6.boundedcontextWaterside.domain.dto.ShipmentAndPurchaseOrderMatchedDto;
import be.kdg.prog6.boundedcontextWaterside.ports.in.MatchPurchaseAndShipmentOrderUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreateShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import be.kdg.prog6.common.exception.CustomException;
import be.kdg.prog6.common.facades.MatchShipmentOrderWithPurchaseOrderCommand;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class DefaultMatchPurchaseAndShipmentOrderUseCase implements MatchPurchaseAndShipmentOrderUseCase {

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


       updateShipmentOrderPorts.forEach(updateShipmentOrderPort ->
               updateShipmentOrderPort.updateShipmentOrder(new ShipmentOrder.ShipmentOrderUUID(shipmentOrderUUID)));
    }
}
