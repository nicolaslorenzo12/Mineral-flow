package be.kdg.prog6.boundedcontextWarehouse.domain;

import java.util.ArrayList;
import java.util.List;

public class WarehouseActivityWindow {

    private final List<WarehouseActivity> warehouseActivityList = new ArrayList<>();

    public WarehouseActivityWindow() {
    }

    public WarehouseActivity addWarehouseActivity(int amountOfTons, int warehouseNumber, WarehouseAction action){

        WarehouseActivity warehouseActivity = new WarehouseActivity(amountOfTons, warehouseNumber, action);
        warehouseActivityList.add(warehouseActivity);
        return warehouseActivity;
    }

    public int calculateCurrentStock() {
        int totalTons = 0;
        for (WarehouseActivity activity : warehouseActivityList) {
            if (activity.action() == WarehouseAction.RECEIVE) {
                totalTons += activity.amountOfTons();
            } else if (activity.action() == WarehouseAction.DISPATCH) {
                totalTons -= activity.amountOfTons();
            }
        }
        return totalTons;
    }

}
