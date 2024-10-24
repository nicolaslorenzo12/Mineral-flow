import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivityWindow;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Pdt;
import be.kdg.prog6.common.domain.Seller;

import java.util.ArrayList;
import java.util.List;

class WarehouseStub extends Warehouse {


    public WarehouseStub(int wareHouseNumber, Seller.CustomerUUID sellerUUID, MaterialType materialType, WarehouseActivityWindow warehouseActivityWindow) {
        super(wareHouseNumber, sellerUUID, materialType, warehouseActivityWindow);
    }

    public WarehouseStub(WarehouseActivityWindow warehouseActivityWindow) {
        super(warehouseActivityWindow);
    }

    @Override
    public void setPdtList(List<Pdt> pdts) {
        super.setPdtList(pdts);
    }

    @Override
    public List<Pdt> getPdtList() {
        return super.getPdtList();
    }
}