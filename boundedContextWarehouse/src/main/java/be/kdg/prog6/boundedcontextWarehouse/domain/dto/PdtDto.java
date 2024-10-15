package be.kdg.prog6.boundedcontextWarehouse.domain.dto;

import java.time.LocalDateTime;

public class PdtDto {

    private final LocalDateTime timeOfDelivery;
    private final int amountOfTonsDelivered;

    public PdtDto(LocalDateTime timeOfDelivery, int amountOfTonsDelivered) {
        this.timeOfDelivery = timeOfDelivery;
        this.amountOfTonsDelivered = amountOfTonsDelivered;
    }
    
    public LocalDateTime getTimeOfDelivery() {
        return timeOfDelivery;
    }

    public int getAmountOfTonsDelivered() {return amountOfTonsDelivered;}
}
