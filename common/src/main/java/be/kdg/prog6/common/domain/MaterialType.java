package be.kdg.prog6.common.domain;

public enum MaterialType {
    GP(1),
    IO(2),
    CM(3),
    PT(4),
    SG(5);

    private final int code;

    MaterialType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
