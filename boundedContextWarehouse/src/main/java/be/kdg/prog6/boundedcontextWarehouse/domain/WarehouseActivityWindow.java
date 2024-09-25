package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import java.util.ArrayList;
import java.util.List;

public class WarehouseActivityWindow {

    private final List<WarehouseActivity> warehouseActivityList = new ArrayList();

    public WarehouseActivity addWarehouseActivity(int amountOfTons, Seller.CustomerUUID sellerId, Material.MaterialUUID materialUUID ,
                                                  int warehouseNumber, WarehouseAction action){

        WarehouseActivity warehouseActivity = new WarehouseActivity(amountOfTons, sellerId, materialUUID, warehouseNumber, action);
        warehouseActivityList.add(warehouseActivity);
        return warehouseActivity;
    }


}
