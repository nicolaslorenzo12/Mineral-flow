package be.kdg.prog6.boundedcontextWarehouse.domain;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WarehouseActivityWindow {

    private final int warehouseNumber;
    private final List<WarehouseActivity> warehouseActivityList = new ArrayList();

    public WarehouseActivityWindow(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }


    public WarehouseActivity addWarehouseActivity(int amountOfTons, int warehouseNumber, WarehouseAction action){

        WarehouseActivity warehouseActivity = new WarehouseActivity(amountOfTons, warehouseNumber, action);
        warehouseActivityList.add(warehouseActivity);
        return warehouseActivity;
    }
}
