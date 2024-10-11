package be.kdg.prog6.boundedcontextWaterside.ports.in;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;

import java.time.LocalDate;

public interface ShipmentOrderMaterialLoaded {

    void changeStatusOfShipToLoaded(ShipmentOrder.ShipmentOrderUUID shipmentOrderUUID, LocalDate departureDate);
}
