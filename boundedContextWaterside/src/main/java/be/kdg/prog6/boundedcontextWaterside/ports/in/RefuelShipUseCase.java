package be.kdg.prog6.boundedcontextWaterside.ports.in;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;

public interface RefuelShipUseCase {
    ShipmentOrder refuelShip(RefuelShipCommand refuelShipCommand);
}
