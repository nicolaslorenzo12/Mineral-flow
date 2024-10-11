package be.kdg.prog6.common.domain;

public class OrderLine {

    private int lineNumber;
    private MaterialType materialType;
    private int quantity;
    private Uom uom = Uom.T;


    public OrderLine(){

    }
    public OrderLine(MaterialType materialType, int quantity) {
        this.materialType = materialType;
        this.quantity = quantity;
    }

    public OrderLine(int lineNumber,MaterialType materialType, int quantity) {
        this.lineNumber = lineNumber;
        this.materialType = materialType;
        this.quantity = quantity;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public int getQuantity() {
        return quantity;
    }

    public Uom getUom() {
        return uom;
    }
}
