package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
import be.kdg.prog6.boundedcontextWaterside.ports.in.RefuelShipCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.RefuelShipUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultRefuelShipUseCase implements RefuelShipUseCase {

    private final LoadShipmentOrderPort loadOrCreateShipmentOrderPort;
    private final List<UpdateShipmentOrderPort> updateShipmentOrderPortList;

    public DefaultRefuelShipUseCase(LoadShipmentOrderPort loadOrCreateShipmentOrderPort, List<UpdateShipmentOrderPort> updateShipmentOrderPortList) {
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
        this.updateShipmentOrderPortList = updateShipmentOrderPortList;
    }

    @Override
    public ShipmentOrder refuelShip(RefuelShipCommand refuelShipCommand) {
        ShipmentOrder shipmentOrder = loadOrCreateShipmentOrderPort.loadShipmentOrderByVesselNumber(refuelShipCommand.vesselNumber());
        shipmentOrder.checkIfShipmentOrderHasAlreadyHadThisStatus(ShipmentStatus.BUNKERED);
        updateShipmentOrderPortList.forEach(updateShipmentOrderPort -> updateShipmentOrderPort.updateShipmentOrder(shipmentOrder, false));
        return shipmentOrder;
    }
}
