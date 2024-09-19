package be.kdg.prog6.common.domain;

public enum Uom {

    T(1),
    KT(2);

    private final int code;

    Uom(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
