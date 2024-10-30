import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;

public class StubWarehouse extends Warehouse {
    public StubWarehouse(int wareHouseNumber, Seller.CustomerUUID sellerUUID, MaterialType materialType) {
        super(wareHouseNumber, sellerUUID, materialType);
    }

    @Override
    public void checkIfMaximumStockPercentageExceeded() {
    }
}
