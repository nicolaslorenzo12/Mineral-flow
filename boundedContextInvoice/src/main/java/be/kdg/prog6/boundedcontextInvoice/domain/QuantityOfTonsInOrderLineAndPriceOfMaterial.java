package be.kdg.prog6.boundedcontextInvoice.domain;

public class QuantityOfTonsInOrderLineAndPriceOfMaterial {

    private final int quantity;
    private final int pricePerTon;

    public QuantityOfTonsInOrderLineAndPriceOfMaterial(int quantity, int pricePerTon) {
        this.quantity = quantity;
        this.pricePerTon = pricePerTon;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPricePerTon() {
        return pricePerTon;
    }
}
