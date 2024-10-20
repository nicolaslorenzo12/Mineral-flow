package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.ports.in.ShipmentOrderAndPurchaseOrderMatched;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DefaultShipmentOrderAndPurchaseOrderMatched implements ShipmentOrderAndPurchaseOrderMatched {

    private final List<UpdateShipmentOrderPort> updateShipmentOrderPorts;
    private final LoadShipmentOrderPort loadOrCreateShipmentOrderPort;

    public DefaultShipmentOrderAndPurchaseOrderMatched(List<UpdateShipmentOrderPort> updateShipmentOrderPorts, LoadShipmentOrderPort loadOrCreateShipmentOrderPort) {
        this.updateShipmentOrderPorts = updateShipmentOrderPorts;
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
    }


    @Override
    public void changeStatusOfShipToArrived(ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID, LocalDate actualArrivalDate) {

        ShipmentOrder shipmentOrder = loadOrCreateShipmentOrderPort.loadShipmentOrder(shipmentOrderUUID);
        shipmentOrder.setActualArrivalDate(actualArrivalDate);
        updateShipmentOrderPorts.forEach(updateShipmentOrderPort -> {updateShipmentOrderPort.updateShipmentOrder(shipmentOrder, false);});

    }
}
