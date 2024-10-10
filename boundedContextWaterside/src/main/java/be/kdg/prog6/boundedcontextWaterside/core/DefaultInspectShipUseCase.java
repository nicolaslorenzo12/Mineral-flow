package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
import be.kdg.prog6.boundedcontextWaterside.ports.in.InspectShipUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreateShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultInspectShipUseCase implements InspectShipUseCase {

    private final List<UpdateShipmentOrderPort> updateShipmentOrderPortList;
    private final LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort;

    public DefaultInspectShipUseCase(List<UpdateShipmentOrderPort> updateShipmentOrderPortList, LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort) {
        this.updateShipmentOrderPortList = updateShipmentOrderPortList;
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
    }


    @Override
    public ShipmentOrder inspect(ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID) {
        ShipmentOrder shipmentOrder = loadOrCreateShipmentOrderPort.loadOrCreateShipmentOrder(shipmentOrderUUID);
        shipmentOrder.setShipmentStatus(ShipmentStatus.INSPECTED);
        updateShipmentOrderPortList.forEach(updateShipmentOrderPort -> updateShipmentOrderPort.updateShipmentOrder(shipmentOrder, false));
        return shipmentOrder;
    }
}
