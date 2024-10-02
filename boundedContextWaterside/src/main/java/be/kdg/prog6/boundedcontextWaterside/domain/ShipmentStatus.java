package be.kdg.prog6.boundedcontextWaterside.domain;

public enum ShipmentStatus {

    NOTARRIVED(1),
    ARRIVED(2),
    LOADED(3),
    INSPECTED(4),
    REFUELED(5);

    private final int code;
    ShipmentStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
