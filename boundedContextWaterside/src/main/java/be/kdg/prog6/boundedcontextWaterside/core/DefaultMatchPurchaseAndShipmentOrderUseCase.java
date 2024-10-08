package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
import be.kdg.prog6.boundedcontextWaterside.domain.dto.ShipmentAndPurchaseOrderMatchedDto;
import be.kdg.prog6.boundedcontextWaterside.ports.in.MatchPurchaseAndShipmentOrderCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.MatchPurchaseAndShipmentOrderUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreateShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DefaultMatchPurchaseAndShipmentOrderUseCase implements MatchPurchaseAndShipmentOrderUseCase {

    private final LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort;
    private final UpdateShipmentOrderPort updateShipmentOrderPort;

    public DefaultMatchPurchaseAndShipmentOrderUseCase(LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort,
                                                       UpdateShipmentOrderPort updateShipmentOrderPort) {
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
        this.updateShipmentOrderPort = updateShipmentOrderPort;
    }

    //@Override
    //public ShipmentAndPurchaseOrderMatchedDto matchPurchaseAndShipmentOrderWhenArriving(MatchPurchaseAndShipmentOrderCommand matchPurchaseAndShipmentOrderCommand) {
//
    //    String poNumber = matchPurchaseAndShipmentOrderCommand.poNumber();
    //    ShipmentOrder shipmentOrder = loadOrCreateShipmentOrderPort.loadOrCreateShipmentOrder(matchPurchaseAndShipmentOrderCommand.poNumber())
    //            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Shipment order was not found"));
//
    //    shipmentOrder.checkIfPoNumberIsTheSame(poNumber);
    //    shipmentOrder.checkIfShipmentOrderHasAlreadyHadThisStatus(ShipmentStatus.ARRIVED);
    //    shipmentOrder.setActualArrivalDate(LocalDate.now());
//
    //    updateShipmentOrderPort.updateShipmentOrder(shipmentOrder);
//
    //    return new ShipmentAndPurchaseOrderMatchedDto(shipmentOrder.getShipmentOrderUUID(), shipmentOrder.getPoNumber(),
    //            shipmentOrder.getActualArrivalDate(), shipmentOrder.getShipmentStatus());
    //}
}
