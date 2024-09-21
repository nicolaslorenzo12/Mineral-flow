package be.kdg.prog6.common.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Pdt {

    private LocalDateTime timeOfLoad;
    private int wareHouseNumber;
    private int dockNumber;
    private final Uom uom = Uom.T;
    public PdtUUID pdtUUID;

    public record PdtUUID(UUID uuid){

    }
}
