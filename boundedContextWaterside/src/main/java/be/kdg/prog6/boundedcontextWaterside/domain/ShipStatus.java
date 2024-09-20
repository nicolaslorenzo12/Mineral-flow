package be.kdg.prog6.boundedcontextWaterside.domain;

public enum ShipStatus {

    NOTARRIVED(1),
    ARRIVED(2),
    INSPECTIONOPERATION(3),
    BUNKERINGOPERATION(4),
    LOADING(5),
    LOADED(6);

    private final int code;
    ShipStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
