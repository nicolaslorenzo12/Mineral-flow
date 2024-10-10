package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
import be.kdg.prog6.boundedcontextWaterside.ports.in.ShipmentOrderMaterialLoaded;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreateShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultShipmentOrderMaterialLoaded implements ShipmentOrderMaterialLoaded {

    private final List<UpdateShipmentOrderPort> updateShipmentOrderPorts;
    private final LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort;

    public DefaultShipmentOrderMaterialLoaded(List<UpdateShipmentOrderPort> updateShipmentOrderPorts, LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort) {
        this.updateShipmentOrderPorts = updateShipmentOrderPorts;
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
    }

    @Override
    public void changeStatusOfShipToLoaded(ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID) {

        ShipmentOrder shipmentOrder = loadOrCreateShipmentOrderPort.loadOrCreateShipmentOrder(shipmentOrderUUID);
        shipmentOrder.setShipmentStatus(ShipmentStatus.LOADED);
        updateShipmentOrderPorts.forEach(updateShipmentOrderPort -> updateShipmentOrderPort.loadMaterial(shipmentOrder));
    }
}
