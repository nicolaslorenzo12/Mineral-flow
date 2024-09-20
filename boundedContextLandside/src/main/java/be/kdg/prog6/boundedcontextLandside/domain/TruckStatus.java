package be.kdg.prog6.boundedcontextLandside.domain;

public enum TruckStatus {

    NOTARRIVED(1),
    ARRIVED(2),
    WEIGHTINGFIRSTTIME(3),
    LOADING(4),
    WEIGHTINGLASTTIME(5),
    LEFT(6);
    private final int code;
    TruckStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
