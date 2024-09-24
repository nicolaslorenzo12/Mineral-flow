package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.Material;

import java.util.ArrayList;
import java.util.List;

public class WarehouseActivityWindow {

    private final List<WarehouseActivity> warehouseActivityList = new ArrayList();

    public WarehouseActivity addWarehouseActivity(int amountOfTons, int warehouseNumber, Material.MaterialUUID materialUUID){

        WarehouseActivity warehouseActivity = new WarehouseActivity(amountOfTons, warehouseNumber, materialUUID);
        warehouseActivityList.add(warehouseActivity);
        return warehouseActivity;
    }


}
