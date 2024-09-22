package be.kdg.prog6.boundedcontextWarehouse.domain;
public class Dock {

    private int wareHouseNumber;
    private int dockNumber;

    public Dock(int wareHouseNumber, int dockNumber) {
        this.wareHouseNumber = wareHouseNumber;
        this.dockNumber = dockNumber;
    }

    public int getWareHouseNumber() {
        return wareHouseNumber;
    }

    public int getDockNumber() {
        return dockNumber;
    }
}
