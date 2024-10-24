import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.common.domain.Pdt;

import java.util.ArrayList;
import java.util.List;

class WarehouseStub extends Warehouse {


    @Override
    public void setPdtList(List<Pdt> pdts) {
        super.setPdtList(pdts);
    }

    @Override
    public List<Pdt> getPdtList() {
        return super.getPdtList();
    }
}