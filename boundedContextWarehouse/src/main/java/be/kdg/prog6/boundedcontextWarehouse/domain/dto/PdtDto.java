package be.kdg.prog6.boundedcontextWarehouse.domain.dto;

import java.time.LocalDateTime;

public class PdtDto {

    private final LocalDateTime timeOfDelivery;

    public PdtDto(LocalDateTime timeOfDelivery) {
        this.timeOfDelivery = timeOfDelivery;
    }
    
    public LocalDateTime getTimeOfDelivery() {
        return timeOfDelivery;
    }
}
