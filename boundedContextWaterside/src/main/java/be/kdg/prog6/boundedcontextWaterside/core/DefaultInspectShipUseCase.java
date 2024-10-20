package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
import be.kdg.prog6.boundedcontextWaterside.ports.in.InspectShipCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.InspectShipUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultInspectShipUseCase implements InspectShipUseCase {

    private final List<UpdateShipmentOrderPort> updateShipmentOrderPortList;
    private final LoadShipmentOrderPort loadOrCreateShipmentOrderPort;

    public DefaultInspectShipUseCase(List<UpdateShipmentOrderPort> updateShipmentOrderPortList, LoadShipmentOrderPort loadOrCreateShipmentOrderPort) {
        this.updateShipmentOrderPortList = updateShipmentOrderPortList;
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
    }


    @Override
    public ShipmentOrder inspect(InspectShipCommand inspectShipCommand) {
        ShipmentOrder shipmentOrder = loadOrCreateShipmentOrderPort.loadShipmentOrderByVesselNumber(inspectShipCommand.vesselNumber());
        shipmentOrder.checkIfShipmentOrderHasAlreadyHadThisStatus(ShipmentStatus.INSPECTED);
        updateShipmentOrderPortList.forEach(updateShipmentOrderPort -> updateShipmentOrderPort.updateShipmentOrder(shipmentOrder, false));
        return shipmentOrder;
    }
}
