package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
import be.kdg.prog6.boundedcontextWaterside.ports.in.ShipmentOrderAndPurchaseOrderMatched;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreateShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultShipmentOrderAndPurchaseOrderMatched implements ShipmentOrderAndPurchaseOrderMatched {

    private final List<UpdateShipmentOrderPort> updateShipmentOrderPorts;
    private final LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort;

    public DefaultShipmentOrderAndPurchaseOrderMatched(List<UpdateShipmentOrderPort> updateShipmentOrderPorts, LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort) {
        this.updateShipmentOrderPorts = updateShipmentOrderPorts;
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
    }


    @Override
    public void changeStatusOfShipToArrived(ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID) {

        ShipmentOrder shipmentOrder = loadOrCreateShipmentOrderPort.loadOrCreateShipmentOrder(shipmentOrderUUID);
        shipmentOrder.setShipmentStatus(ShipmentStatus.ARRIVED);
        updateShipmentOrderPorts.forEach(updateShipmentOrderPort -> {updateShipmentOrderPort.updateShipmentOrder(shipmentOrder, false);});

    }
}
