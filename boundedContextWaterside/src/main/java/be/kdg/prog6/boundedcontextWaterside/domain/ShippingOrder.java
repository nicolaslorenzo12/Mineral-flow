package be.kdg.prog6.boundedcontextWaterside.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class ShippingOrder {

    private String vesselNumber;
    private LocalDateTime estimatedArrivalTime;
    private LocalDateTime estimatedDepartureTime;
    private LocalDateTime actualArrivalTime;
    private LocalDateTime actualDepartureTime;
    private LocalDateTime inspectionTime;
    private boolean signedByInspector;
    private ShippingOrderUUID shippingOrderUUID;

    public record ShippingOrderUUID(UUID uuid){

    }
}
