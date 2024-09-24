package be.kdg.prog6.boundedcontextWaterside.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ShippingOrder {

    private PurchaseOrder.PurchaseOrderUUID purchaseOrderUUID;
    private String vesselNumber;
    private LocalDateTime estimatedArrivalTime;
    private LocalDateTime estimatedDepartureTime;
    private LocalDateTime actualArrivalTime;
    private LocalDateTime actualDepartureTime;
    private ShipStatus shipStatus;
    List<OrderLine> shipmentLines;
    private ShippingOrderUUID shippingOrderUUID;

    public record ShippingOrderUUID(UUID uuid){

    }

    public ShippingOrder(PurchaseOrder.PurchaseOrderUUID purchaseOrderUUID, String vesselNumber, LocalDateTime estimatedArrivalTime,
                         LocalDateTime estimatedDepartureTime, ShipStatus shipStatus, List<OrderLine> shipmentLines, ShippingOrderUUID shippingOrderUUID) {
        this.purchaseOrderUUID = purchaseOrderUUID;
        this.vesselNumber = vesselNumber;
        this.estimatedArrivalTime = estimatedArrivalTime;
        this.estimatedDepartureTime = estimatedDepartureTime;
        this.shipStatus = shipStatus;
        this.shipmentLines = shipmentLines;
        this.shippingOrderUUID = shippingOrderUUID;
    }

    public PurchaseOrder.PurchaseOrderUUID getPurchaseOrderUUID() {
        return purchaseOrderUUID;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public LocalDateTime getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public LocalDateTime getEstimatedDepartureTime() {
        return estimatedDepartureTime;
    }

    public LocalDateTime getActualArrivalTime() {
        return actualArrivalTime;
    }

    public LocalDateTime getActualDepartureTime() {
        return actualDepartureTime;
    }

    public ShipStatus getShipStatus() {
        return shipStatus;
    }

    public List<OrderLine> getShipmentLineUUIDS() {
        return shipmentLines;
    }

    public ShippingOrderUUID getShippingOrderUUID() {
        return shippingOrderUUID;
    }
}
